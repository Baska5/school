package com.softgen.school.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Entity
@Table(name="group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "group_name", nullable = false)
    private String groupName;

    @Column(name = "group_number", nullable = false, unique = true)
    private String groupNumber;

    @ManyToMany(mappedBy = "groups")
    private Set<Student> students;

    @ManyToMany(mappedBy = "groups")
    private Set<Teacher> teachers;
}
