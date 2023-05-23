package com.example.springunittest.entities.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentDTO {

    private Long id;
    private String name;
    private String address;
    private String country;
    private String phoneNumber;
    private Integer rollNumber;
    private Integer regNumber;
}
