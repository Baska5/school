package com.softgen.school.services.impl;

import com.softgen.school.criterias.StudentCriteria;
import com.softgen.school.dtos.StudentDto;
import com.softgen.school.entities.Student;
import com.softgen.school.exceptions.DuplicateEntityException;
import com.softgen.school.exceptions.EntityNotFoundException;
import com.softgen.school.mappers.StudentMapper;
import com.softgen.school.repositories.StudentRepository;
import com.softgen.school.services.GroupService;
import com.softgen.school.services.StudentService;
import com.softgen.school.specifications.StudentSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;
    private final GroupService groupService;
    private final StudentMapper mapper;

    public StudentServiceImpl(StudentRepository repository, GroupService groupService, StudentMapper mapper) {
        this.repository = repository;
        this.groupService = groupService;
        this.mapper = mapper;
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        return mapper.mapStudentDao(findStudentById(studentId));
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return repository.findAll().stream().map(mapper::mapStudentDao).toList();
    }

    @Override
    public List<StudentDto> searchStudents(String firstName, String lastName, String pin, LocalDate birthDate) {
        StudentCriteria criteria = StudentCriteria.builder()
                .firstName(firstName)
                .lastName(lastName)
                .pin(pin)
                .birthDate(birthDate)
                .build();
        Specification<Student> spec = StudentSpecification.getSpecification(criteria);
        return repository.findAll(spec).stream().map(mapper::mapStudentDao).toList();
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        String email = studentDto.getEmail();
        String pin = studentDto.getPin();
        if (repository.existsByEmail(email))
            throw new DuplicateEntityException(String.format("Student with email %s already exists", email));
        if (repository.existsByPin(pin))
            throw new DuplicateEntityException(String.format("Student with pin %s already exists", pin));
        Student createdStudent = repository.save(mapper.mapCreateStudentDto(studentDto));
        return mapper.mapStudentDao(createdStudent);
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto studentDto) {
        Student student = findStudentById(studentId);
        String email = studentDto.getEmail();
        String pin = studentDto.getPin();
        if (repository.existsByEmailAndIdNot(email, studentId))
            throw new DuplicateEntityException(String.format("Student with email %s already exists", email));
        if (repository.existsByPinAndIdNot(pin, studentId))
            throw new DuplicateEntityException(String.format("Student with pin %s already exists", pin));
        mapper.mapUpdateStudentDto(student, studentDto);
        repository.save(student);
        return mapper.mapStudentDao(student);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = findStudentById(studentId);
        student.getGroups().forEach(group -> group.getStudents().remove(student));
        repository.deleteById(studentId);
    }

    private Student findStudentById(Long studentId) {
        return repository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Student with ID %d doesn't exist", studentId)));
    }
}
