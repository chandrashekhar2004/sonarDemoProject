package com.simplifyvms.config.repository;

import com.simplifyvms.config.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, String> {
}
