package com.softgen.school.controllers;

import com.softgen.school.dtos.TeacherDto;
import com.softgen.school.services.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private TeacherService teacherService;

    // Create
    @PostMapping
    public ResponseEntity<TeacherDto> createTeacher(@RequestBody TeacherDto teacherDto) {
        TeacherDto createdTeacher = teacherService.createTeacher(teacherDto);
        return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/{teacherId}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long teacherId) {
        TeacherDto teacher = teacherService.getTeacherById(teacherId);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TeacherDto>> searchTeachers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String pin,
            @RequestParam(required = false) LocalDate birthDate
    ) {
        List<TeacherDto> teachers = teacherService.searchTeachers(firstName, lastName, pin, birthDate);
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    // Update
    @PutMapping("/{teacherId}")
    public ResponseEntity<Void> updateTeacher(@PathVariable Long teacherId, @RequestBody TeacherDto updatedTeacherDto) {
        teacherService.updateTeacher(teacherId, updatedTeacherDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long teacherId) {
        teacherService.deleteTeacher(teacherId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}