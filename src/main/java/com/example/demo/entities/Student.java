package com.example.demo.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Transient
    public static final String SEQUENCE_NAME = "students_sequence";


    private long id;
    private String name;
    private long studentNumber;
    private String email;
    private List<String> courseList;
    private float gpa;
}
