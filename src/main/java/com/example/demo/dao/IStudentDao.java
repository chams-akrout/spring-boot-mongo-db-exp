package com.example.demo.dao;

import com.example.demo.entities.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IStudentDao extends MongoRepository<Student,Long>,CustomStudentRepository {

    List<Student> findAllByOrderByGpaAsc();

}
