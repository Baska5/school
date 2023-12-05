package com.softgen.school.services;

public interface StudentGroupService {

    void addStudentToGroup(Long studentId, Long groupId);

    void removeStudentFromGroup(Long studentId, Long groupId);
}
