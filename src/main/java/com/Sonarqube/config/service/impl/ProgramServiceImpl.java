package com.simplifyvms.config.service.impl;


import com.simplifyvms.config.dto.ProgramDto;
import com.simplifyvms.config.dto.ProgramRequest;
import com.simplifyvms.config.entity.Program;
import com.simplifyvms.config.repository.ProgramDao;
import com.simplifyvms.config.service.ProgramService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramDao programDao;
    private static final Logger logger = LoggerFactory.getLogger(ProgramServiceImpl.class);

    @Autowired
    public ProgramServiceImpl(ProgramDao programDao) {
        this.programDao = programDao;
    }

    @Override
    public ResponseEntity<ProgramDto> getProgramById(String id) {
        logger.info("Fetching program with ID: {}", id);
        Optional<Program> programById = programDao.findById(id);
        if (programById.isPresent()) {
            logger.info("Program found: {}", programById);
            Program program = programById.get();
            if (!program.isDeleted()) {
                ProgramDto programResponse = new ProgramDto(program);
                return ResponseEntity.ok(programResponse);
            } else {
                logger.warn("Program with ID: {} is marked as deleted", id);
                return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
        logger.warn("Program with ID: {} not found", id);
        return  ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<String> deleteProgram(String id) {
        logger.info("Deleting program with ID: {}", id);
        Optional<Program> optionalProgram = programDao.findById(id);
        if (optionalProgram.isPresent()) {
            Program program = optionalProgram.get();
            program.setDeleted(true);
            programDao.save(program);
            if (program.isDeleted()) {
                logger.info("Program with ID: {} marked as deleted", id);

                return ResponseEntity.ok("Program deleted");

            } else {
                logger.warn("Failed to mark program with ID: {} as deleted", id);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            logger.error("Program with ID: {} not found", id);
            throw new EntityNotFoundException("Program not found with ID: " + id);
        }
    }

    @Override
    public List<ProgramDto> getAllPrograms() {
        logger.info("Fetching all programs");

        List<Program> allPrograms = programDao.findAll();

        List<ProgramDto> activePrograms = allPrograms.stream()
                .filter(program -> !program.isDeleted())
                .map(program -> new ProgramDto(program))
                .collect(Collectors.toList());
        logger.info("Active programs: {}", activePrograms);
        return activePrograms;
    }

    @Override
    public ResponseEntity<ProgramDto> updateProgram(String id, ProgramRequest program) {
        logger.info("Updating program with ID: {}", id);
        Optional<Program> optionalProgram = programDao.findById(id);
        if (optionalProgram.isPresent()) {
            Program programToUpdate = optionalProgram.get();

            programToUpdate.setServiceType(program.getServiceType());
            programToUpdate.setDescription(program.getDescription());
            programToUpdate.setName(program.getName());
            programToUpdate.setContacts(program.getContacts());
            programToUpdate.setStatus(program.getStatus());
            programToUpdate.setDeleted(program.isDeleted());
            programToUpdate.setData(program.getData());
            programToUpdate.setDomain(program.getDomain());
            programToUpdate.setActive(program.isActive());
            programToUpdate.setCreatedBy(program.getCreatedBy());
            programToUpdate.setDeleted(program.isDeleted());
            programToUpdate.setModifiedBy(program.getModifiedBy());
            programToUpdate.setModifiedOn(program.getModifiedOn());
            programToUpdate.setRefId(program.getRefId());

            logger.info("Program updated: {}", programToUpdate);
            ProgramDto programResponse = new ProgramDto(programToUpdate);
            return ResponseEntity.ok(programResponse);
        }
        logger.warn("Program with ID: {} not found for update", id);
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<ProgramDto> createProgram(ProgramRequest programRequest) {
        try {

            Program program = new Program();
            program.setId(UUID.randomUUID().toString());
            program.setUniqueId(UUID.randomUUID().toString());
            program.setServiceType(programRequest.getServiceType());
            program.setName(programRequest.getName());
            program.setContacts(programRequest.getContacts());
            program.setDescription(programRequest.getDescription());
            program.setStatus(programRequest.getStatus());
            program.setData(programRequest.getData());
            program.setDomain(programRequest.getDomain());
            program.setActive(programRequest.isActive());
            program.setRefId(programRequest.getRefId());
            program.setDeleted(programRequest.isDeleted());
            program.setCreatedOn(programRequest.getCreatedOn());
            program.setModifiedOn(programRequest.getModifiedOn());
            program.setCreatedBy(programRequest.getCreatedBy());
            program.setModifiedBy(programRequest.getModifiedBy());

            Program savedProgram = programDao.save(program);

            System.out.println(savedProgram.toString());
            ProgramDto programDto = new ProgramDto(savedProgram);
            logger.info("Created new program: {}", programDto);
            return ResponseEntity.ok(programDto);

        } catch (Exception e) {
            logger.error("Failed to save program: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
