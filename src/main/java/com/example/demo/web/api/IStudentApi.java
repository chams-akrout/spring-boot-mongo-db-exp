package com.example.demo.web.api;

import com.example.demo.dto.StudentDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface IStudentApi {

    @Operation(summary = "save a student")
    @PostMapping(value = "/students")
    ResponseEntity<?> saveStudent(StudentDto studentDto);

    @Operation(summary= "update a student")
    @PutMapping(value = "/students/{id}")
    ResponseEntity<?> updateStudent(int id, StudentDto studentDto);

    @Operation(summary= "get all students")
    @GetMapping(value = "/students")
    ResponseEntity<?> getAllStudents();

    @Operation(summary = "delete a student by id")
    @DeleteMapping(value = "/students/{id}")
    ResponseEntity<?> deleteStudentById(int id);

    @Operation(summary = "get students by Gpa")
    @GetMapping(value = "/students/orderedByGpa")
    ResponseEntity<?> getStudentsByOrderedGpa();

    @Operation(summary = "get student by id")
    @GetMapping(value = "/students/{id}")
    ResponseEntity<?> getStudentById(int id);

    @Operation(summary = "get students by name")
    @GetMapping(value = "/studentsByName")
    ResponseEntity<?> getStudentsByName(String name);

    @Operation(summary = "get students by page")
    @GetMapping(value = "/studentsByPage")
    ResponseEntity<?> getStudentsByPage(int offset,int size);

    @Operation(summary = "get students by specific field")
    @GetMapping(value = "/studentsBySpecificField")
    ResponseEntity<?> getStudentsBySpecificField(String fieldName);

    @Operation(summary = "group students by name and sum gpa")
    @GetMapping(value = "/getStudentListWithGpaGreaterThanGivenValue")
    ResponseEntity<?> getStudentListWithGpaGreaterThanGivenValue(float value);
}
