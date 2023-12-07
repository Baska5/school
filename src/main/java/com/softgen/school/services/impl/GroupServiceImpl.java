package com.softgen.school.services.impl;

import com.softgen.school.dtos.GroupDto;
import com.softgen.school.entities.Group;
import com.softgen.school.exceptions.DuplicateEntityException;
import com.softgen.school.exceptions.EntityNotFoundException;
import com.softgen.school.mappers.GroupMapper;
import com.softgen.school.repositories.GroupRepository;
import com.softgen.school.services.GroupService;
import com.softgen.school.specifications.GroupSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository repository;
    private final GroupMapper mapper;

    public GroupServiceImpl(GroupRepository repository, GroupMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public GroupDto getGroupById(Long groupId) {
        return mapper.mapGroupDao(findGroupById(groupId));
    }

    @Override
    public List<GroupDto> getAllGroups() {
        return repository.findAll().stream().map(mapper::mapGroupDao).toList();
    }

    @Override
    public List<GroupDto> searchGroups(Integer groupNumber) {
        Specification<Group> spec = GroupSpecification.getSpecification(groupNumber);
        return repository.findAll(spec).stream().map(mapper::mapGroupDao).toList();
    }

    @Override
    public GroupDto createGroup(GroupDto groupDto) {
        Integer groupNumber = groupDto.getGroupNumber();
        if (repository.existsByGroupNumber(groupNumber))
            throw new DuplicateEntityException(String.format("Group with number %d already exists", groupNumber));
        Group createdGroup = repository.save(mapper.mapCreateGroupDto(groupDto));
        return mapper.mapGroupDao(createdGroup);
    }

    @Override
    public GroupDto updateGroup(Long groupId, GroupDto groupDto) {
        Group group = findGroupById(groupId);
        Integer groupNumber = groupDto.getGroupNumber();
        if (repository.existsByGroupNumberAndIdNot(groupNumber, groupId))
            throw new DuplicateEntityException(String.format("Group with number %d already exists", groupNumber));
        mapper.mapUpdateGroupDto(group, groupDto);
        repository.save(group);
        return mapper.mapGroupDao(group);
    }

    @Override
    public void deleteGroup(Long groupId) {
        if (repository.existsById(groupId))
            repository.deleteById(groupId);
        else
            throw new EntityNotFoundException(String.format("Group with id %d doesnt exist", groupId));
    }

    private Group findGroupById(Long groupId) {
        return repository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Group with ID %d doesn't exist", groupId)));
    }
}
