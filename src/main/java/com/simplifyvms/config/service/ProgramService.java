package com.simplifyvms.config.service;


import com.simplifyvms.config.entity.Program;

import java.util.List;
import java.util.UUID;

public interface ProgramService {

    Program getProgramById(UUID id);
    Program createProgram(Program program);
    Program updateProgram(UUID id, Program program);
    String deleteProgram(UUID id);
    List<Program> getAllPrograms();
}
