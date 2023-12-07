package com.softgen.school.controllers;

import com.softgen.school.dtos.GroupDto;
import com.softgen.school.services.GroupService;
import com.softgen.school.services.StudentGroupService;
import com.softgen.school.services.TeacherGroupService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;
    private final StudentGroupService studentGroupService;
    private final TeacherGroupService teacherGroupService;

    public GroupController(GroupService groupService,
                           StudentGroupService studentGroupService,
                           TeacherGroupService teacherGroupService) {
        this.groupService = groupService;
        this.studentGroupService = studentGroupService;
        this.teacherGroupService = teacherGroupService;
    }

    @PostMapping
    public ResponseEntity<GroupDto> createGroup(@RequestBody @Valid GroupDto groupDtO) {
        GroupDto createdGroup = groupService.createGroup(groupDtO);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }

    @PostMapping("{groupId}/student/{studentId}")
    public ResponseEntity<String> addStudentToGroup(@PathVariable Long studentId, @PathVariable Long groupId) {
        studentGroupService.addStudentToGroup(studentId, groupId);
        return new ResponseEntity<>("Student was added to group successfully", HttpStatus.OK);
    }

    @PostMapping("{groupId}/teacher/{teacherId}")
    public ResponseEntity<String> addTeacherToGroup(@PathVariable Long teacherId, @PathVariable Long groupId) {
        teacherGroupService.addTeacherToGroup(teacherId, groupId);
        return new ResponseEntity<>("Teacher was added to group successfully", HttpStatus.OK);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Long groupId) {
        GroupDto group = groupService.getGroupById(groupId);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        return new ResponseEntity<>(groupService.getAllGroups(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<GroupDto>> searchGroup(@RequestParam(required = false) Integer groupNumber) {
        List<GroupDto> groupList = groupService.searchGroups(groupNumber);
        return new ResponseEntity<>(groupList, HttpStatus.OK);
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable Long groupId, @RequestBody @Valid GroupDto updatedGroupDto) {
        GroupDto groupDto = groupService.updateGroup(groupId, updatedGroupDto);
        return new ResponseEntity<>(groupDto, HttpStatus.OK);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<String> deleteGroup(@PathVariable Long groupId) {
        groupService.deleteGroup(groupId);
        return new ResponseEntity<>("Group was deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping("{groupId}/student/{studentId}")
    public ResponseEntity<String> removeStudentFromGroup(@PathVariable Long studentId, @PathVariable Long groupId) {
        studentGroupService.removeStudentFromGroup(studentId, groupId);
        return new ResponseEntity<>("Student was removed from group successfully", HttpStatus.OK);
    }

    @DeleteMapping("{groupId}/teacher/{teacherId}")
    public ResponseEntity<String> removeTeacherFromGroup(@PathVariable Long teacherId, @PathVariable Long groupId) {
        teacherGroupService.removeTeacherFromGroup(teacherId, groupId);
        return new ResponseEntity<>("Teacher was removed from group successfully", HttpStatus.OK);
    }

}