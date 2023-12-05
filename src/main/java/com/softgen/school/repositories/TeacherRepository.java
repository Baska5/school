package com.softgen.school.repositories;

import com.softgen.school.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {
    boolean existsByEmail(String email);

    boolean existsByPin(String pin);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByPinAndIdNot(String pin, Long id);
}
