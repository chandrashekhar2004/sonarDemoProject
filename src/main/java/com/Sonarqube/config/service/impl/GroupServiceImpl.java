package com.simplifyvms.config.service.impl;

import com.simplifyvms.config.entity.Group;
import com.simplifyvms.config.repository.GroupRepository;
import com.simplifyvms.config.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public Optional<Group> findById(String id) {
        return groupRepository.findById(id);
    }

    @Override
    public Group updateGroup(String id, Group group) {
        return groupRepository.findById(id).map(existingGroup -> {
            existingGroup.setName(group.getName());
            existingGroup.setDescription(group.getDescription());
            existingGroup.setType(group.getType());
            existingGroup.setLogo(group.getLogo());
            existingGroup.setData(group.getData());
            existingGroup.setDomain(group.getDomain());
            existingGroup.setActive(group.isActive());
            existingGroup.setRefId(group.getRefId());
            existingGroup.setDeleted(group.isDeleted());
            existingGroup.setCreatedOn(group.getCreatedOn());
            existingGroup.setModifiedOn(group.getModifiedOn());
            existingGroup.setCreatedBy(group.getCreatedBy());
            existingGroup.setModifiedBy(group.getModifiedBy());
            return groupRepository.save(existingGroup);
        }).orElseThrow(() -> new RuntimeException("Group not found with id " + id));
    }

    @Override
    public boolean deleteGroupById(String id) {
        return groupRepository.findById(id).map(group -> {
            groupRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
