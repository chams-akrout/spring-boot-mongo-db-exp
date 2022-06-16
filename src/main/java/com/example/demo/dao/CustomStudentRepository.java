package com.example.demo.dao;

import com.example.demo.entities.Student;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomStudentRepository {

    List<Student> getStudentListByStudentName(String name);

    List<Student> getStudentListByPage(Pageable pageableRequest);

    List<Student> getStudentListWithSpecificField(String fieldName);

    List<Student> getStudentListWithGpaGreaterThanGivenValue(float value);



}
