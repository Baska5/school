package com.softgen.school.criterias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCriteria {
    String firstName;
    String lastName;
    String pin;
    LocalDate birthDate;
}
