package com.simplifyvms.config.service;

import com.simplifyvms.config.entity.Group;
import com.simplifyvms.config.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public Optional<Group> findById(UUID id) {
        return groupRepository.findById(id);
    }

    public void deleteById(UUID id) {
        groupRepository.deleteById(id);
    }
}
