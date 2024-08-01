package com.simplifyvms.config.service;

import com.simplifyvms.config.entity.Group;
import java.util.List;
import java.util.Optional;

public interface GroupService {
    Group createGroup(Group group);
    List<Group> findAll();
    Optional<Group> findById(String id);
    boolean deleteGroupById(String id);
    Group updateGroup(String id, Group group);
}
