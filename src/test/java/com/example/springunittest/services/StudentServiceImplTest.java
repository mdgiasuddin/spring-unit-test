package com.example.springunittest.services;

import com.example.springunittest.entities.dtos.StudentDTO;
import com.example.springunittest.entities.models.Student;
import com.example.springunittest.entities.repositories.StudentRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ComponentScan
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class StudentServiceImplTest {

    private static final Faker faker = new Faker();

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @DisplayName("Should create new student")
    @ParameterizedTest
    @MethodSource("createStudentDTOArg")
    void test_saveStudent(StudentDTO studentDTO) {
        Student student = studentService.saveStudent(studentDTO);

        assertEquals(student.getName(), studentDTO.getName());
        assertEquals(student.getAddress(), studentDTO.getAddress());
        assertEquals(student.getCountry(), studentDTO.getCountry());
        assertEquals(student.getPhoneNumber(), studentDTO.getPhoneNumber());
        assertEquals(student.getRollNumber(), studentDTO.getRollNumber());
        assertEquals(student.getRegNumber(), studentDTO.getRegNumber());
    }

    @DisplayName("Should edit student info")
    @ParameterizedTest
    @MethodSource("createStudentDTOArg")
    void test_editStudent(StudentDTO studentDTO) {
        Student studentObj = studentRepository.save(new Student());
        studentDTO.setId(studentObj.getId());

        Student student = studentService.editStudent(studentDTO);

        assertEquals(student.getName(), studentDTO.getName());
        assertEquals(student.getAddress(), studentDTO.getAddress());
        assertEquals(student.getCountry(), studentDTO.getCountry());
        assertEquals(student.getPhoneNumber(), studentDTO.getPhoneNumber());
        assertEquals(student.getRollNumber(), studentDTO.getRollNumber());
        assertEquals(student.getRegNumber(), studentDTO.getRegNumber());
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
