package com.example.weblab3.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String sid;
    private String name;
    private String gender;
    private int age;
    private Date birthday;

}
