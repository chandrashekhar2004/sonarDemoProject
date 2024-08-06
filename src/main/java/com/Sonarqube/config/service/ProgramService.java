package com.simplifyvms.config.service;

import com.simplifyvms.config.dto.ProgramDto;
import com.simplifyvms.config.dto.ProgramRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ProgramService {

    ResponseEntity<ProgramDto> getProgramById(String id);
    ResponseEntity<ProgramDto> createProgram(ProgramRequest programRequest);
    ResponseEntity<ProgramDto> updateProgram(String id, ProgramRequest programRequest);
    ResponseEntity<String> deleteProgram(String id);
    List<ProgramDto> getAllPrograms();
}

