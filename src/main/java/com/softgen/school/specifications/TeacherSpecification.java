package com.softgen.school.specifications;

import com.softgen.school.criterias.TeacherCriteria;
import com.softgen.school.entities.Teacher;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TeacherSpecification {

    public static Specification<Teacher> getSpecification(TeacherCriteria criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria.getFirstName() != null && !criteria.getFirstName().isEmpty())
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("firstName")),
                        "%" + criteria.getFirstName().toLowerCase() + "%"
                ));

            if (criteria.getLastName() != null && !criteria.getLastName().isEmpty())
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("lastName")),
                        "%" + criteria.getLastName().toLowerCase() + "%"
                ));

            if (criteria.getPin() != null && !criteria.getPin().isEmpty())
                predicates.add(criteriaBuilder.equal(root.get("pin"), criteria.getPin()));

            if (criteria.getBirthDate() != null)
                predicates.add(criteriaBuilder.equal(root.get("birthDate"), criteria.getBirthDate()));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}