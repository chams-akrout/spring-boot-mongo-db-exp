package com.example.demo.service;

import com.example.demo.dao.IStudentDao;
import com.example.demo.dto.StudentDto;
import com.example.demo.entities.Student;
import com.example.demo.mapper.StudentMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService implements IStudentService {

    private IStudentDao studentDao;
    private SequenceGeneratorService sequenceGenerator;

    public StudentService(IStudentDao studentDao, SequenceGeneratorService sequenceGenerator) {
        this.studentDao = studentDao;
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public List<StudentDto> findAll() {
        List<Student> students = studentDao.findAll();
        return getStudentDtos(students);
    }

    @Override
    public Optional<StudentDto> saveStudent(StudentDto studentDto) {
        Student studentToBeSaved = StudentMapper.toStudent(studentDto);
        studentToBeSaved.setId(sequenceGenerator.generateSequence(Student.SEQUENCE_NAME));
        Student savedStudent = studentDao.save(studentToBeSaved);
        return Optional.of(StudentMapper.toStudentDto(savedStudent));
    }

    @Override
    public Optional<StudentDto> updateStudent(long id, StudentDto studentDto) {
        Optional<Student> fetchedStudent = studentDao.findById(id);
        if (fetchedStudent.isPresent()) {
            Student studentToBeUpdated = StudentMapper.toStudent(studentDto);
            studentToBeUpdated.setId(id);
            Student updatedStudent = studentDao.save(studentToBeUpdated);
            return Optional.of(StudentMapper.toStudentDto(updatedStudent));
        }
        return Optional.empty();
    }

    @Override
    public List<StudentDto> findAllByOrderByGpaDesc() {
        List<Student> studentsByOrderByGpaDesc = studentDao.findAllByOrderByGpaAsc();
        return getStudentDtos(studentsByOrderByGpaDesc);
    }

    @Override
    public boolean deleteStudentById(long id) {
        Optional<Student> fetchedStudent = studentDao.findById(id);
        if (fetchedStudent.isPresent()) {
            studentDao.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<StudentDto> getStudentById(long id) {
        Optional<Student> fetchedStudent = studentDao.findById(id);
        return fetchedStudent.map(StudentMapper::toStudentDto);
    }

    @Override
    public List<StudentDto> getStudentsByName(String name) {
        List<Student> studentsByName = studentDao.getStudentListByStudentName(name);
        return getStudentDtos(studentsByName);
    }

    @Override
    public List<StudentDto> getStudentsByPage(int offset, int size) {
        Pageable pageableRequest = PageRequest.of(offset, size);
        List<Student> studentsByPage = studentDao.getStudentListByPage(pageableRequest);
        return getStudentDtos(studentsByPage);
    }

    @Override
    public List<StudentDto> getStudentListWithSpecificField(String fieldName) {
        List<Student> studentsBySpecificFiled = studentDao.getStudentListWithSpecificField(fieldName);
        return getStudentDtos(studentsBySpecificFiled);
    }

    @Override
    public List<StudentDto> getStudentListWithGpaGreaterThanGivenValue(float value) {
        List<Student> studentsBySpecificFiled = studentDao.getStudentListWithGpaGreaterThanGivenValue(value);
        return getStudentDtos(studentsBySpecificFiled);
    }

    private List<StudentDto> getStudentDtos(List<Student> students) {
        return !students.isEmpty() ? students.stream().map(StudentMapper::toStudentDto).collect(Collectors.toList()) : Collections.emptyList();
    }
}
