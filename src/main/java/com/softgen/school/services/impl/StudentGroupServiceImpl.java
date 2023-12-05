package com.softgen.school.services.impl;

import com.softgen.school.entities.Group;
import com.softgen.school.entities.Student;
import com.softgen.school.exceptions.DuplicateMembershipException;
import com.softgen.school.exceptions.EntityNotFoundException;
import com.softgen.school.exceptions.NotMemberException;
import com.softgen.school.repositories.GroupRepository;
import com.softgen.school.repositories.StudentRepository;
import com.softgen.school.services.StudentGroupService;
import org.springframework.stereotype.Service;

@Service
public class StudentGroupServiceImpl implements StudentGroupService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;


    public StudentGroupServiceImpl(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public void addStudentToGroup(Long studentId, Long groupId) {
        Student student = findStudentById(studentId);
        Group group = findGroupById(groupId);
        if (isMember(group, studentId))
            throw new DuplicateMembershipException(String.format("Student with ID %d is already a member of group %d", studentId, groupId));
        group.getStudents().add(student);
        groupRepository.save(group);
    }

    @Override
    public void removeStudentFromGroup(Long studentId, Long groupId) {
        Student student = findStudentById(studentId);
        Group group = findGroupById(groupId);
        if (!isMember(group, studentId))
            throw new NotMemberException(String.format("Student with ID %d is not a member of group %d", studentId, groupId));
        group.getStudents().removeIf(s -> s.getId().equals(studentId));
        groupRepository.save(group);
    }

    private boolean isMember(Group group, Long studentId) {
        return group.getStudents().stream()
                .anyMatch(student -> student.getId().equals(studentId));
    }

    private Group findGroupById(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Group with ID %d doesn't exist", groupId)));
    }

    private Student findStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Student with ID %d doesn't exist", studentId)));
    }
}
