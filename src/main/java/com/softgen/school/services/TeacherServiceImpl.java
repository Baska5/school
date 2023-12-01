package com.softgen.school.services;

import com.softgen.school.dtos.TeacherDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService{
    @Override
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        return null;
    }

    @Override
    public TeacherDto getTeacherById(Long teacherId) {
        return null;
    }

    @Override
    public List<TeacherDto> searchTeachers(String firstName, String lastName, String pin, LocalDate birthDate) {
        return null;
    }

    @Override
    public void updateTeacher(Long teacherId, TeacherDto updatedTeacherDto) {

    }

    @Override
    public void deleteTeacher(Long teacherId) {

    }
}
