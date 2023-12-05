package com.softgen.school.services;

import com.softgen.school.dtos.GroupDto;

import java.util.List;

public interface GroupService {
    GroupDto createGroup(GroupDto groupDtO);

    GroupDto getGroupById(Long groupId);

    List<GroupDto> getAllGroups();

    List<GroupDto> searchGroups(Integer groupNumber);

    GroupDto updateGroup(Long groupId, GroupDto updatedGroupDTO);

    void deleteGroup(Long groupId);
}
