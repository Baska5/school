package com.softgen.school.dtos;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class TeacherDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String pin;
    private String email;
    private LocalDate birthDate;
}
