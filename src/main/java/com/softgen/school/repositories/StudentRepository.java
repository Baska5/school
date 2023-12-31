package com.softgen.school.repositories;

import com.softgen.school.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    boolean existsByEmail(String email);

    boolean existsByPin(String pin);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByPinAndIdNot(String pin, Long id);
}
