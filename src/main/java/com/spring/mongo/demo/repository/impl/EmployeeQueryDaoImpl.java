package com.spring.mongo.demo.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.spring.mongo.demo.model.Employee;
import com.spring.mongo.demo.repository.EmployeeQueryDao;


@Repository
class EmployeeQueryDaoImpl implements EmployeeQueryDao {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Employee> getAll() {
        System.out.println("Inside Employee Query DAO Imp's get()");
        return mongoTemplate.findAll(Employee.class);
    }


    @Override
    public List<Employee> getEmployeeByFirstName(String firstName) {

        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is(firstName));
        return new ArrayList<>(mongoTemplate.find(query, Employee.class));
    }


    @Override
    public Employee getSingleEmployeeByFirstName(String name) {

        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is(name));

        return mongoTemplate.findOne(query, Employee.class);
    }


    // "firstName"  --> database property (not related to java code)
    // select * from employee where lastName like %Gurung% limit 1 --> (case insensitive)
    @Override
    public List<Employee> getEmployeeByFirstNameLike(String firstName) {

        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").regex(firstName, "i"));

        return mongoTemplate.find(query, Employee.class);
    }


    @Override
    public Employee getSingleEmployeeByLastName(String lastName) {

        Query query = new Query();
        // where作用是
        // 1. 指定查询的集合
        // 2. 指定查询的条件
        query.addCriteria(Criteria.where("lastName").regex(lastName, "i"));// i是不区分大小写的意思

        return mongoTemplate.findOne(query, Employee.class);
    }




    @Override
    public List<Employee> getEmployeeBySalaryGreaterThan(int salary) {

        Query query = new Query();
        query.addCriteria(Criteria.where("salary").gt(salary));// gt作用是
        // 1. 指定查询的集合
        // 2. 指定查询的条件
        query.with(Sort.by(Sort.Direction.ASC, "firstName"));//作用
        // 1. 指定排序的字段
        query.with(Sort.by(new Sort.Order(Sort.Direction.ASC, "firstName").ignoreCase()));

        return mongoTemplate.find(query, Employee.class);
    }


}
