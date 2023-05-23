package com.example.springunittest.controllers;

import com.example.springunittest.entities.dtos.StudentDTO;
import com.example.springunittest.entities.models.Student;
import com.example.springunittest.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentController {

    private final StudentService studentService;


    @PostMapping
    public StudentDTO saveStudent(@RequestBody @Validated StudentDTO dto) {
        Student student = studentService.saveStudent(dto);
        dto.setId(student.getId());
        return dto;
    }

    @PutMapping
    public StudentDTO editStudent(@RequestBody @Validated StudentDTO dto) {
        studentService.editStudent(dto);
        return dto;
    }
}
