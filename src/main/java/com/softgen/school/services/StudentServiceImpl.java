package com.softgen.school.services;

import com.softgen.school.dtos.StudentDto;
import com.softgen.school.entities.Student;
import com.softgen.school.exceptions.EntityNotFoundException;
import com.softgen.school.mappers.StudentMapper;
import com.softgen.school.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;
    private final StudentMapper mapper;

    public StudentServiceImpl(StudentRepository repository, StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = repository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Student with ID %d doesn't exist", studentId)));
        return mapper.mapDao(student);
    }

    @Override
    public List<StudentDto> searchStudents(String firstName, String lastName, String pin, LocalDate birthDate) {
        return null;
    }

    @Override
    public StudentDto createStudent(StudentDto student) {
        Student createdStudent = repository.save(mapper.mapCreateDto(student));
        return mapper.mapDao(createdStudent);
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto updatedStudentDto) {
        Student student = repository.findById(studentId).orElse(null);
        mapper.mapUpdateDto(student, updatedStudentDto);
        repository.save(student);
        return mapper.mapDao(student);
    }

    @Override
    public void deleteStudent(Long studentId) {
        repository.deleteById(studentId);
    }
}
