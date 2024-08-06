package com.simplifyvms.config.repository;

import com.simplifyvms.config.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ProgramDao extends JpaRepository<Program, String> {
}