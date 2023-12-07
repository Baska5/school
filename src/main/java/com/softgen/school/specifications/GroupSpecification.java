package com.softgen.school.specifications;

import com.softgen.school.entities.Group;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GroupSpecification {

    public static Specification<Group> getSpecification(Integer groupNumber) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (groupNumber != null) {
                predicates.add(criteriaBuilder.equal(root.get("groupNumber"), groupNumber));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}