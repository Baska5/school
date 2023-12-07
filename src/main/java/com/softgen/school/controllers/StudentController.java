package com.softgen.school.controllers;

import com.softgen.school.dtos.StudentDto;
import com.softgen.school.services.StudentGroupService;
import com.softgen.school.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final StudentGroupService studentGroupService;

    public StudentController(StudentService studentService, StudentGroupService studentGroupService) {
        this.studentService = studentService;
        this.studentGroupService = studentGroupService;
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody @Valid StudentDto StudentDto) {
        StudentDto createdStudent = studentService.createStudent(StudentDto);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @PostMapping("{studentId}/group/{groupId}")
    public ResponseEntity<String> addStudentToGroup(@PathVariable Long studentId, @PathVariable Long groupId) {
        studentGroupService.addStudentToGroup(studentId, groupId);
        return new ResponseEntity<>("Student was added to group successfully", HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long studentId) {
        StudentDto student = studentService.getStudentById(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentDto>> searchStudents(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String pin,
            @RequestParam(required = false) LocalDate birthDate) {
        List<StudentDto> students = studentService.searchStudents(firstName, lastName, pin, birthDate);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long studentId, @RequestBody @Valid StudentDto studentDto) {
        StudentDto updatedDto = studentService.updateStudent(studentId, studentDto);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>("Student was deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping("{studentId}/group/{groupId}")
    public ResponseEntity<String> removeStudentFromGroup(@PathVariable Long studentId, @PathVariable Long groupId) {
        studentGroupService.removeStudentFromGroup(studentId, groupId);
        return new ResponseEntity<>("Student was removed from group successfully", HttpStatus.OK);
    }
}
