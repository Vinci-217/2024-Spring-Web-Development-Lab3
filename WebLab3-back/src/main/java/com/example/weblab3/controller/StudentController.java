package com.example.weblab3.controller;

import com.example.weblab3.entity.Student;
import com.example.weblab3.service.StudentService;
import com.example.weblab3.util.Result;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @DeleteMapping("/{sid}")
    public Result delete(@PathVariable String sid) {
        studentService.delete(sid);
        return Result.ok("OK");
    }

    @PostMapping("/add")
    public Result add(@RequestBody Student student) {
        studentService.add(student);
        return Result.ok("OK");
    }

    @PutMapping("/modify")
    public Result modify(@RequestBody Student student) {
        studentService.modify(student);
        return Result.ok(("OK"));
    }

}
