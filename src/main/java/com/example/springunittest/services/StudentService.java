package com.example.springunittest.services;

import com.example.springunittest.entities.dtos.StudentDTO;
import com.example.springunittest.entities.models.Student;

public interface StudentService {

    Student saveStudent(StudentDTO dto);

    Student editStudent(StudentDTO dto);
}
