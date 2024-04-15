package com.example.weblab3.service.impl;

import com.example.weblab3.entity.Student;
import com.example.weblab3.mapper.StudentMapper;
import com.example.weblab3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    @Override
    public void delete(String sid) {
        studentMapper.delete(sid);
    }

    @Override
    public void add(Student student) {
        studentMapper.add(student);
    }

    @Override
    public void modify(Student student) {
        studentMapper.modify(student);
    }

}
