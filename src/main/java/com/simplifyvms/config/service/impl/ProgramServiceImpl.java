package com.simplifyvms.config.service.impl;


import com.simplifyvms.config.entity.Program;
import com.simplifyvms.config.repository.ProgramDao;
import com.simplifyvms.config.service.ProgramService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Program getProgramById(UUID id) {
        logger.info("Fetching program with ID: {}", id);
        Optional<Program> programById = programDao.findById(id);
        if (programById.isPresent()) {
            logger.info("Program found: {}", programById);

            Program program = programById.get();
            if (!program.isDeleted()) {
                return programById.get();
            } else {
                logger.warn("Program with ID: {} is marked as deleted", id);

                throw new EntityNotFoundException("Program not found" + id);
            }
        }
        logger.warn("Program with ID: {} not found", id);

        return new Program();
    }

    @Override
    public Program createProgram(Program program) {

        try {
            if (program.getUniqueId() == null && program.getId() == null) {
                program.setId(UUID.randomUUID());
                program.setUniqueId(UUID.randomUUID());
                if (program.getUniqueId() != null && program.getId() != null) {
                    logger.info("Creating new program: {}", program);

                    return programDao.save(program);
                }
            }

        } catch (Exception e) {
            logger.error("Failed to save program: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to save program", e);
        }
        logger.warn("Program creation failed for input: {}", program);

        return null;
    }

    @Override
    public String deleteProgram(UUID id) {
        logger.info("Deleting program with ID: {}", id);

        Optional<Program> optionalProgram = programDao.findById(id);
        if (optionalProgram.isPresent()) {
            Program program = optionalProgram.get();
            program.setDeleted(true);
            programDao.save(program);
            if (program.isDeleted()) {
                logger.info("Program with ID: {} marked as deleted", id);

                return "Program deleted";

            } else {
                logger.warn("Failed to mark program with ID: {} as deleted", id);

                return "Program not deleted";
            }
        } else {
            logger.error("Program with ID: {} not found", id);

            throw new EntityNotFoundException("Program not found with ID: " + id);

        }
    }

    @Override
    public List<Program> getAllPrograms() {
        logger.info("Fetching all programs");

        List<Program> allPrograms = programDao.findAll();

        List<Program> activePrograms = allPrograms.stream()
                .filter(program -> !program.isDeleted())
                .collect(Collectors.toList());

        logger.info("Active programs: {}", activePrograms);
        return activePrograms;
    }

    @Override
    public Program updateProgram(UUID id, Program program) {
        logger.info("Updating program with ID: {}", id);

        Optional<Program> optionalProgram = programDao.findById(id);
        if (optionalProgram.isPresent()) {
            Program programToUpdate = optionalProgram.get();
            program.setId(id);
            program.setUniqueId(programToUpdate.getUniqueId());
            logger.info("Program updated: {}", program);

            return programDao.save(program);
        }
        logger.warn("Program with ID: {} not found for update", id);

        return null;
    }

}
