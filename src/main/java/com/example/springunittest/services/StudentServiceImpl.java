package com.example.springunittest.services;

import com.example.springunittest.entities.dtos.StudentDTO;
import com.example.springunittest.entities.models.Student;
import com.example.springunittest.entities.repositories.StudentRepository;
import com.example.springunittest.exceptions.ResourcesNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;


    @Override
    public Student saveStudent(StudentDTO dto) {

        Student student = Student.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .country(dto.getCountry())
                .phoneNumber(dto.getPhoneNumber())
                .rollNumber(dto.getRollNumber())
                .regNumber(dto.getRegNumber())
                .build();
        return studentRepository.save(student);
    }

    @Override
    public Student editStudent(StudentDTO dto) {
        Student student = studentRepository.findOneById(dto.getId()).orElseThrow(
                () -> new ResourcesNotFound("No student found with id: " + dto.getId()));

        student.setName(dto.getName());
        student.setAddress(dto.getAddress());
        student.setCountry(dto.getCountry());
        student.setPhoneNumber(dto.getPhoneNumber());
        student.setRollNumber(dto.getRollNumber());
        student.setRegNumber(dto.getRegNumber());

        return studentRepository.save(student);
    }
}
