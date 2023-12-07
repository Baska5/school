package com.softgen.school.repositories;

import com.softgen.school.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GroupRepository extends JpaRepository<Group, Long>, JpaSpecificationExecutor<Group> {
    boolean existsByGroupNumber(Integer groupNumber);
    boolean existsByGroupNumberAndIdNot(Integer groupNumber, Long id);
}
