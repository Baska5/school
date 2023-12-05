package com.softgen.school.services.impl;

import com.softgen.school.criterias.TeacherCriteria;
import com.softgen.school.dtos.StudentDto;
import com.softgen.school.dtos.TeacherDto;
import com.softgen.school.entities.Teacher;
import com.softgen.school.exceptions.DuplicateEntityException;
import com.softgen.school.exceptions.EntityNotFoundException;
import com.softgen.school.mappers.TeacherMapper;
import com.softgen.school.repositories.TeacherRepository;
import com.softgen.school.services.TeacherService;
import com.softgen.school.specifications.TeacherSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository repository;
    private final TeacherMapper mapper;

    public TeacherServiceImpl(TeacherRepository repository, TeacherMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TeacherDto getTeacherById(Long teacherId) {
        return mapper.mapTeacherDao(findTeacherById(teacherId));
    }

    @Override
    public List<TeacherDto> getAllTeachers() {
        return repository.findAll().stream().map(mapper::mapTeacherDao).toList();
    }

    @Override
    public List<TeacherDto> searchTeachers(String firstName, String lastName, String pin, LocalDate birthDate) {
        TeacherCriteria criteria = TeacherCriteria.builder()
                .firstName(firstName)
                .lastName(lastName)
                .pin(pin)
                .birthDate(birthDate)
                .build();
        Specification<Teacher> spec = TeacherSpecification.getSpecification(criteria);
        return repository.findAll(spec).stream().map(mapper::mapTeacherDao).toList();
    }

    @Override
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        String email = teacherDto.getEmail();
        String pin = teacherDto.getPin();
        if (repository.existsByEmail(email))
            throw new DuplicateEntityException(String.format("Teacher with email %s already exists", email));
        if (repository.existsByPin(pin))
            throw new DuplicateEntityException(String.format("Teacher with pin %s already exists", pin));
        Teacher createdTeacher = repository.save(mapper.mapCreateTeacherDto(teacherDto));
        return mapper.mapTeacherDao(createdTeacher);
    }

    @Override
    public TeacherDto updateTeacher(Long teacherId, TeacherDto teacherDto) {
        Teacher teacher = findTeacherById(teacherId);
        String email = teacherDto.getEmail();
        String pin = teacherDto.getPin();
        if (repository.existsByEmailAndIdNot(email, teacherId))
            throw new DuplicateEntityException(String.format("Teacher with email %s already exists", email));
        if (repository.existsByPinAndIdNot(pin, teacherId))
            throw new DuplicateEntityException(String.format("Teacher with pin %s already exists", pin));
        mapper.mapUpdateTeacherDto(teacher, teacherDto);
        repository.save(teacher);
        return mapper.mapTeacherDao(teacher);
    }

    @Override
    public void deleteTeacher(Long teacherId) {
        Teacher teacher = findTeacherById(teacherId);
        teacher.getGroups().forEach(group -> group.getTeachers().remove(teacher));
        repository.deleteById(teacherId);
    }

    private Teacher findTeacherById(Long teacherId) {
        return repository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Teacher with ID %d doesn't exist", teacherId)));
    }
}
