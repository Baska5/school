package com.softgen.school.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherDto {

    private Long id;

    @NotBlank(message = "Invalid first name: Empty first name")
    @NotNull(message = "Invalid first name: First name is null")
    private String firstName;

    @NotBlank(message = "Invalid last name: Empty last name")
    @NotNull(message = "Invalid last name: Last name is null")
    private String lastName;

    @NotBlank(message = "Invalid PIN: Empty PIN")
    @NotNull(message = "Invalid PIN: PIN is null")
    private String pin;

    @Email(message = "Invalid email")
    private String email;

    @NotNull(message = "Invalid birth date: Birth date is null")
    private LocalDate birthDate;
}
