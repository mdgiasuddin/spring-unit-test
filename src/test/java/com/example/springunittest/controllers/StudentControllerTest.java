package com.example.springunittest.controllers;

import com.example.springunittest.entities.dtos.StudentDTO;
import com.example.springunittest.entities.models.Student;
import com.example.springunittest.services.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    private static final Faker faker = new Faker();

    @MockBean
    StudentService studentService;

    @Autowired
    MockMvc mockMvc;

    @DisplayName("Should create loan with positive case")
    @ParameterizedTest
    @MethodSource("createStudentDTOArg")
    void test_saveStudent(StudentDTO studentDTO) throws Exception {
        when(studentService.saveStudent(any(StudentDTO.class)))
                .thenReturn(Student.builder().build());

        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(studentDTO);

        this.mockMvc.perform(post("/api/student").contentType("application/json").content(body))
                .andExpect(status().isOk());
    }

    private Stream<Arguments> createStudentDTOArg() {
        return Stream.of(
                Arguments.of(createStudentDTO()),
                Arguments.of(createStudentDTO()),
                Arguments.of(createStudentDTO()),
                Arguments.of(createStudentDTO())
        );
    }

    private StudentDTO createStudentDTO() {
        return StudentDTO.builder()
                .name(faker.name().name())
                .address(faker.address().fullAddress())
                .country(faker.country().name())
                .phoneNumber(faker.phoneNumber().phoneNumber())
                .rollNumber(faker.random().nextInt(121101, 923900))
                .regNumber(faker.random().nextInt(121101, 923900))
                .build();
    }
}
