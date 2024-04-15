package com.example.weblab3.controller;

import com.example.weblab3.entity.User;
import com.example.weblab3.util.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @PostMapping("/login")
    public Result login(User user) {
        if (user.getUsername().equals("admin") && user.getPassword().equals("123")) {
            return Result.ok("OK");
        } else
            return Result.fail("用户名或密码错误");
    }
}
