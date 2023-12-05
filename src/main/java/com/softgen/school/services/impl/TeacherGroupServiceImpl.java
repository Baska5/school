package com.softgen.school.services.impl;

import com.softgen.school.entities.Group;
import com.softgen.school.entities.Teacher;
import com.softgen.school.exceptions.DuplicateMembershipException;
import com.softgen.school.exceptions.EntityNotFoundException;
import com.softgen.school.exceptions.NotMemberException;
import com.softgen.school.repositories.GroupRepository;
import com.softgen.school.repositories.TeacherRepository;
import com.softgen.school.services.TeacherGroupService;
import org.springframework.stereotype.Service;

@Service
public class TeacherGroupServiceImpl implements TeacherGroupService {
    private final TeacherRepository teacherRepository;
    private final GroupRepository groupRepository;


    public TeacherGroupServiceImpl(TeacherRepository teacherRepository, GroupRepository groupRepository) {
        this.teacherRepository = teacherRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public void addTeacherToGroup(Long teacherId, Long groupId) {
        Teacher teacher = findTeacherById(teacherId);
        Group group = findGroupById(groupId);
        if (isMember(group, teacherId))
            throw new DuplicateMembershipException(String.format("Teacher with ID %d is already a member of group %d", teacherId, groupId));
        group.getTeachers().add(teacher);
        groupRepository.save(group);
    }

    @Override
    public void removeTeacherFromGroup(Long teacherId, Long groupId) {
        Teacher teacher = findTeacherById(teacherId);
        Group group = findGroupById(groupId);
        if (!isMember(group, teacherId))
            throw new NotMemberException(String.format("Teacher with ID %d is not a member of group %d", teacherId, groupId));
        group.getTeachers().removeIf(s -> s.getId().equals(teacherId));
        groupRepository.save(group);
    }

    private boolean isMember(Group group, Long teacherId) {
        return group.getTeachers().stream()
                .anyMatch(teacher -> teacher.getId().equals(teacherId));
    }

    private Group findGroupById(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Group with ID %d doesn't exist", groupId)));
    }

    private Teacher findTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Teacher with ID %d doesn't exist", teacherId)));
    }
}
