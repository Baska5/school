package com.softgen.school.services;

import com.softgen.school.dtos.StudentDto;

import java.time.LocalDate;
import java.util.List;

public interface StudentService {
    StudentDto getStudentById(Long studentId);

    List<StudentDto> searchStudents(String firstName, String lastName, String pin, LocalDate birthDate);

    StudentDto createStudent(StudentDto student);

    StudentDto updateStudent(Long studentId, StudentDto updatedStudentDto);

    void deleteStudent(Long studentId);
}
