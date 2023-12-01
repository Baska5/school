package com.softgen.school.controllers;

import com.softgen.school.dtos.GroupDto;
import com.softgen.school.services.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/groups")
public class GroupController {

    private
    GroupService groupService;

    @PostMapping
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDtO) {
        GroupDto createdGroup = groupService.createGroup(groupDtO);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }
    @GetMapping("/{groupId}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Long groupId) {
        GroupDto group = groupService.getGroupById(groupId);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<GroupDto> searchGroup(@RequestParam String groupNumber) {
        GroupDto group = groupService.searchGroup(groupNumber);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }
    @PutMapping("/{groupId}")
    public ResponseEntity<Void> updateGroup(@PathVariable Long groupId, @RequestBody GroupDto updatedGroupDto) {
        groupService.updateGroup(groupId, updatedGroupDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long groupId) {
        groupService.deleteGroup(groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}