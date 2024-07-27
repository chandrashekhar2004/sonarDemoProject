package com.simplifyvms.config.controller;

import com.simplifyvms.config.entity.Program;
import com.simplifyvms.config.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/programs")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @GetMapping("/{id}")
    public Program getProgramById(@PathVariable UUID id) {
        return programService.getProgramById(id);
    }

    @PostMapping
    public Program createProgram(@RequestBody Program program) {
        return programService.createProgram(program);
    }

    @PutMapping("/{id}")
    public Program updateProgram(@PathVariable UUID id, @RequestBody Program program) {
        return programService.updateProgram(id, program);
    }

    @DeleteMapping("/deleteById/{id}")
    public String deleteProgram(@PathVariable UUID id) {
        return programService.deleteProgram(id);
    }

    @GetMapping("/allProgram")
    public List<Program> getAllPrograms() {
        return programService.getAllPrograms();
    }
}

