package com.softgen.school.services;

public interface TeacherGroupService {

    void addTeacherToGroup(Long teacherId, Long groupId);

    void removeTeacherFromGroup(Long teacherId, Long groupId);
}
