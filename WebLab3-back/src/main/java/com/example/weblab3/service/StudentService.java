package com.example.weblab3.service;

import com.example.weblab3.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    void delete(String sid);

    void add(Student student);

    void modify(Student student);
}
