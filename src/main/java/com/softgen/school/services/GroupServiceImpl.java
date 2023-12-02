package com.softgen.school.services;

import com.softgen.school.dtos.GroupDto;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
    @Override
    public GroupDto createGroup(GroupDto groupDtO) {
        return null;
    }

    @Override
    public GroupDto getGroupById(Long groupId) {
        return null;
    }

    @Override
    public GroupDto searchGroup(String groupNumber) {
        return null;
    }

    @Override
    public void updateGroup(Long groupId, GroupDto updatedGroupDTO) {

    }

    @Override
    public void deleteGroup(Long groupId) {

    }
}
