package com.example.demo.mapper;

import com.example.demo.dto.StudentDto;
import com.example.demo.entities.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {


    public static Student toStudent(StudentDto studentDto){
        return Student.builder()
                .id(studentDto.getId())
                .courseList(studentDto.getCourseList())
                .email(studentDto.getEmail())
                .gpa(studentDto.getGpa())
                .studentNumber(studentDto.getStudentNumber())
                .name(studentDto.getName())
                .build();
    }
    public static StudentDto toStudentDto(Student student){
        return StudentDto.builder()
                .id(student.getId())
                .courseList(student.getCourseList())
                .email(student.getEmail())
                .gpa(student.getGpa())
                .studentNumber(student.getStudentNumber())
                .name(student.getName())
                .build();
    }

}
