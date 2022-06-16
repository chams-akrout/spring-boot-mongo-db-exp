package com.example.demo.dao;

import com.example.demo.entities.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class CustomStudentRepositoryImpl implements CustomStudentRepository {

    private final MongoTemplate mongoTemplate;

    public CustomStudentRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Student> getStudentListByStudentName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.find(query, Student.class);
    }

    @Override
    public List<Student> getStudentListByPage(Pageable pageableRequest) {
        Query query = new Query();
        query.with(pageableRequest);
        return mongoTemplate.find(query, Student.class);
    }

    @Override
    public List<Student> getStudentListWithSpecificField(String fieldName) {
        Query query = new Query();
        query.fields().include(fieldName);
        return mongoTemplate.find(query, Student.class);
    }

    @Override
    public List<Student> getStudentListWithGpaGreaterThanGivenValue(float value) {
        GroupOperation groupByNameAndSumGpa = group("name")
                .sum("gpa").as("studentGpa");
        MatchOperation filterStudents = match(new Criteria("studentGpa").gt(value));
        SortOperation sortByGpaDesc = sort(Sort.by(Sort.Direction.DESC, "studentGpa"));
        Aggregation aggregation = newAggregation(groupByNameAndSumGpa, filterStudents, sortByGpaDesc);

        AggregationResults<Student> result = mongoTemplate.aggregate(aggregation, "students", Student.class);
        List<Student> mappedResults = result.getMappedResults();
        return result.getMappedResults();
    }
}
