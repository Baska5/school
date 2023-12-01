package com.softgen.school.services;

import com.softgen.school.dtos.GroupDto;
import org.springframework.stereotype.Service;

public interface GroupService {
    GroupDto createGroup(GroupDto groupDtO);

    GroupDto getGroupById(Long groupId);

    GroupDto searchGroup(String groupNumber);

    void updateGroup(Long groupId, GroupDto updatedGroupDTO);

    void deleteGroup(Long groupId);
}
