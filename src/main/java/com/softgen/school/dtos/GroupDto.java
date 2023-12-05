package com.softgen.school.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDto {

    private Long id;

    @NotBlank(message = "Invalid group name: Empty group name")
    @NotNull(message = "Invalid group name: Group name is null")
    private String groupName;

    @NotNull(message = "Invalid group number: Group number is null")
    private Integer groupNumber;

    private List<StudentDto> students = new ArrayList<>();

    private List<TeacherDto> teachers = new ArrayList<>();
}
