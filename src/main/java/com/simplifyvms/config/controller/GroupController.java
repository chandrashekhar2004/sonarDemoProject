package com.simplifyvms.config.controller;

import com.simplifyvms.config.entity.Group;
import com.simplifyvms.config.service.GroupService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping
    public Group createGroup(@RequestBody Group group){
        return groupService.save(group);
    }

    @RolesAllowed("admin")
    @GetMapping
    public List<Group> getAllGroups(){
        return groupService.findAll();
    }

    @RolesAllowed("user")
    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupsById(@PathVariable UUID id){
        Optional<Group> group = groupService.findById(id);
        if (group.isPresent()) {
            return ResponseEntity.ok(group.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Group> getGroupsById(@PathVariable UUID id, @RequestBody Group group){
        Optional<Group> groups = groupService.findById(id);
        if (groups.isPresent()) {
            Group existingGroup = groups.get();
            existingGroup.setGroupId(group.getGroupId());
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
            Group updatedGroup = groupService.save(existingGroup);
            return ResponseEntity.ok(updatedGroup);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable UUID id) {
        Optional<Group> group = groupService.findById(id);
        if (group.isPresent()) {
            groupService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
