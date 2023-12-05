package com.softgen.school.mappers;

import com.softgen.school.dtos.TeacherDto;
import com.softgen.school.entities.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    @Mapping(target = "id", ignore = true)
    Teacher mapCreateTeacherDto(TeacherDto teacherDto);

    @Mapping(target = "id", ignore = true)
    void mapUpdateTeacherDto(@MappingTarget Teacher teacher, TeacherDto teacherDto);

    Teacher mapTeacherDto(TeacherDto teacherDto);

    TeacherDto mapTeacherDao(Teacher teacher);

}
