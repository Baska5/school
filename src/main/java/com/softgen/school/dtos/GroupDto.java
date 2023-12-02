package com.softgen.school.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDto {

    private Long id;

    @NotBlank(message = "Invalid group name: Empty group name")
    @NotNull(message = "Invalid group name: Group name is null")
    private String groupName;

    @NotBlank(message = "Invalid group number: Empty group number")
    @NotNull(message = "Invalid group number: Group number is null")
    private Integer groupNumber;
}
