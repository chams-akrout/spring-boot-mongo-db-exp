package com.example.demo.service;

import com.example.demo.dto.StudentDto;

import java.util.List;
import java.util.Optional;

public interface IStudentService {

    List<StudentDto> findAll();

    List<StudentDto> findAllByOrderByGpaDesc();

    Optional<StudentDto> updateStudent(long id, StudentDto studentDto);

    Optional<StudentDto> saveStudent(StudentDto studentDto);

    boolean deleteStudentById(long id);

    Optional<StudentDto> getStudentById(long id);

    List<StudentDto> getStudentsByName(String name);

    List<StudentDto> getStudentsByPage(int offset, int size);

    List<StudentDto> getStudentListWithSpecificField(String fieldName);

    List<StudentDto> getStudentListWithGpaGreaterThanGivenValue(float value);

}
