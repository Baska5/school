package com.softgen.school.mappers;

import com.softgen.school.dtos.StudentDto;
import com.softgen.school.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student mapCreateDto(StudentDto studentDto);

    @Mapping(target = "id", ignore = true)
    void mapUpdateDto(@MappingTarget Student student, StudentDto studentDto);

    StudentDto mapDao(Student student);
}
