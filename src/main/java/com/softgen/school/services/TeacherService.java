package com.softgen.school.services;

import com.softgen.school.dtos.TeacherDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface TeacherService {
    TeacherDto createTeacher(TeacherDto teacherDto);

    TeacherDto getTeacherById(Long teacherId);

    List<TeacherDto> searchTeachers(String firstName, String lastName, String pin, LocalDate birthDate);

    void updateTeacher(Long teacherId, TeacherDto updatedTeacherDto);

    void deleteTeacher(Long teacherId);
}
