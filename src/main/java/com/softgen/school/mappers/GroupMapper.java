package com.softgen.school.mappers;

import com.softgen.school.dtos.GroupDto;
import com.softgen.school.dtos.StudentDto;
import com.softgen.school.dtos.TeacherDto;
import com.softgen.school.entities.Group;
import com.softgen.school.entities.Student;
import com.softgen.school.entities.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    @Mapping(target = "id", ignore = true)
    Group mapCreateGroupDto(GroupDto groupDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "teachers", ignore = true)
    void mapUpdateGroupDto(@MappingTarget Group group, GroupDto groupDto);

    @Named("def")
    Group mapGroupDto(GroupDto groupDto);

    GroupDto mapGroupDao(Group group);

    default List<StudentDto> mapStudentsToDtos(List<Student> students) {
        if (students == null)
            return new ArrayList<>();
        return students.stream()
                .map(StudentMapper.INSTANCE::mapStudentDao)
                .collect(Collectors.toList());
    }

    default List<Student> mapDtosToStudents(List<StudentDto> studentDtos) {
        if (studentDtos == null)
            return new ArrayList<>();
        return studentDtos.stream()
                .map(StudentMapper.INSTANCE::mapStudentDto)
                .collect(Collectors.toList());
    }

    default List<TeacherDto> mapTeachersToDtos(List<Teacher> teachers) {
        if (teachers == null)
            return new ArrayList<>();
        return teachers.stream()
                .map(TeacherMapper.INSTANCE::mapTeacherDao)
                .collect(Collectors.toList());
    }

    default List<Teacher> mapDtosToTeachers(List<TeacherDto> teacherDtos) {
        if (teacherDtos == null)
            return new ArrayList<>();
        return teacherDtos.stream()
                .map(TeacherMapper.INSTANCE::mapTeacherDto)
                .collect(Collectors.toList());
    }
}
