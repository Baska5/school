package com.softgen.school.mappers;

import com.softgen.school.dtos.StudentDto;
import com.softgen.school.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(target = "id", ignore = true)
    Student mapCreateStudentDto(StudentDto studentDto);

    @Mapping(target = "id", ignore = true)
    void mapUpdateStudentDto(@MappingTarget Student student, StudentDto studentDto);

    Student mapStudentDto(StudentDto studentDto);

    StudentDto mapStudentDao(Student student);

}
