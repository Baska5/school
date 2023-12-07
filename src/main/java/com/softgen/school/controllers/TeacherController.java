package com.softgen.school.controllers;

import com.softgen.school.dtos.TeacherDto;
import com.softgen.school.services.TeacherGroupService;
import com.softgen.school.services.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    private final TeacherGroupService teacherGroupService;

    public TeacherController(TeacherService teacherService, TeacherGroupService teacherGroupService) {
        this.teacherService = teacherService;
        this.teacherGroupService = teacherGroupService;
    }

    @PostMapping
    public ResponseEntity<TeacherDto> createTeacher(@RequestBody @Valid TeacherDto teacherDto) {
        TeacherDto createdTeacher = teacherService.createTeacher(teacherDto);
        return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
    }

    @PostMapping("{teacherId}/group/{groupId}")
    public ResponseEntity<String> addTeacherToGroup(@PathVariable Long teacherId, @PathVariable Long groupId) {
        teacherGroupService.addTeacherToGroup(teacherId, groupId);
        return new ResponseEntity<>("Teacher was added to group successfully", HttpStatus.OK);
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long teacherId) {
        TeacherDto teacher = teacherService.getTeacherById(teacherId);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        return new ResponseEntity<>(teacherService.getAllTeachers(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TeacherDto>> searchTeachers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String pin,
            @RequestParam(required = false) LocalDate birthDate) {
        List<TeacherDto> teachers = teacherService.searchTeachers(firstName, lastName, pin, birthDate);
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @PutMapping("/{teacherId}")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable Long teacherId, @RequestBody @Valid TeacherDto updatedTeacherDto) {
        TeacherDto updatedTeacher = teacherService.updateTeacher(teacherId, updatedTeacherDto);
        return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long teacherId) {
        teacherService.deleteTeacher(teacherId);
        return new ResponseEntity<>("Teacher was deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping("{teacherId}/group/{groupId}")
    public ResponseEntity<String> removeTeacherFromGroup(@PathVariable Long teacherId, @PathVariable Long groupId) {
        teacherGroupService.removeTeacherFromGroup(teacherId, groupId);
        return new ResponseEntity<>("Teacher was removed from group successfully", HttpStatus.OK);
    }
}