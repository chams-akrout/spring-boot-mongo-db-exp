package com.example.demo.web.impl;

import com.example.demo.common.ApiMessage;
import com.example.demo.dto.StudentDto;
import com.example.demo.service.IStudentService;
import com.example.demo.web.api.IStudentApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/")
public class StudentApi implements IStudentApi {

    private IStudentService studentService;

    private Object httpResponseBody;
    private HttpStatus httpStatus;

    public StudentApi(IStudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public ResponseEntity<?> saveStudent(@RequestBody StudentDto studentDto) {

        try {
            httpResponseBody = studentService.saveStudent(studentDto);
            httpStatus = HttpStatus.CREATED;
        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURRED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);
    }

    @Override
    public ResponseEntity<?> updateStudent(@PathVariable("id") int id, @RequestBody StudentDto studentDto) {
        try {
            Optional<StudentDto> updatedStudent = studentService.updateStudent(id, studentDto);
            if (updatedStudent.isPresent()) {
                httpResponseBody = updatedStudent.get();
                httpStatus = HttpStatus.OK;
            } else {
                httpResponseBody = ApiMessage.STUDENT_NOT_FOUND;
                httpStatus = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURRED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);
    }

    @Override
    public ResponseEntity<?> getAllStudents() {
        try {
            httpResponseBody = studentService.findAll();
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURRED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);
    }

    @Override
    public ResponseEntity<?> deleteStudentById(@PathVariable("id") int id) {
        try {
            boolean isStudentDeleted = studentService.deleteStudentById(id);
            if (isStudentDeleted) {
                httpResponseBody = ApiMessage.STUDENT_DELETED_SUCCESSFULLY;
                httpStatus = HttpStatus.OK;
            } else {
                httpResponseBody = ApiMessage.STUDENT_NOT_FOUND;
                httpStatus = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURRED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);
    }

    @Override
    public ResponseEntity<?> getStudentsByOrderedGpa() {
        try {
            httpResponseBody = studentService.findAllByOrderByGpaDesc();
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURRED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);
    }

    @Override
    public ResponseEntity<?> getStudentById(@PathVariable("id") int id) {
        try {
            Optional<StudentDto> fetchedStudent = studentService.getStudentById(id);
            if (fetchedStudent.isPresent()) {
                httpResponseBody = fetchedStudent.get();
                httpStatus = HttpStatus.OK;
            } else {
                httpResponseBody = ApiMessage.STUDENT_NOT_FOUND;
                httpStatus = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURRED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);
    }

    @Override
    public ResponseEntity<?> getStudentsByName(@RequestParam("name") String name) {
        try {
            httpResponseBody = studentService.getStudentsByName(name);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURRED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);
    }

    @Override
    public ResponseEntity<?> getStudentsByPage(@RequestParam("offset") int offset, @RequestParam("size") int size) {
        try {
            httpResponseBody = studentService.getStudentsByPage(offset, size);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURRED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);
    }

    @Override
    public ResponseEntity<?> getStudentsBySpecificField(@RequestParam("fieldName") String fieldName) {
        try {
            httpResponseBody = studentService.getStudentListWithSpecificField(fieldName);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURRED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);
    }

    @Override
    public ResponseEntity<?> getStudentListWithGpaGreaterThanGivenValue(@RequestParam("value") float value) {
        try {
            httpResponseBody = studentService.getStudentListWithGpaGreaterThanGivenValue(value);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpResponseBody = e.getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);
    }
}

