package com.softgen.school.services;

import com.softgen.school.dtos.TeacherDto;

import java.time.LocalDate;
import java.util.List;

public interface TeacherService {
    TeacherDto createTeacher(TeacherDto teacherDto);

    TeacherDto getTeacherById(Long teacherId);

    List<TeacherDto> getAllTeachers();

    List<TeacherDto> searchTeachers(String firstName, String lastName, String pin, LocalDate birthDate);

    TeacherDto updateTeacher(Long teacherId, TeacherDto updatedTeacherDto);

    void deleteTeacher(Long teacherId);
}
