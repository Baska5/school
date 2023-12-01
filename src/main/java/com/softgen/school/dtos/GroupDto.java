package com.softgen.school.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class GroupDto {
    private Long id;
    private String groupName;
    private String groupNumber;
}
