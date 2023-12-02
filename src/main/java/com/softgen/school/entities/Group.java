package com.softgen.school.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "group_name", nullable = false)
    private String groupName;

    @Column(name = "group_number", nullable = false, unique = true)
    private Integer groupNumber;

    @ManyToMany(mappedBy = "groups")
    private Set<Student> students;

    @ManyToMany(mappedBy = "groups")
    private Set<Teacher> teachers;
}
