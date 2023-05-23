package com.example.springunittest.entities.repositories;

import com.example.springunittest.entities.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findOneById(Long id);
}
