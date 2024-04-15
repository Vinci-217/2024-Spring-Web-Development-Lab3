package com.example.weblab3.mapper;

import com.example.weblab3.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("select * from student order by sid")
    List<Student> findAll();

    @Delete("delete from student where sid =#{sid}")
    void delete(String sid);

    @Insert("insert into student (sid,name,gender,age,birthday) values (#{sid},#{name},#{gender},#{age},#{birthday})")
    void add(Student student);

    @Update("update student set name=#{name},gender=#{gender},age=#{age},birthday=#{birthday} where sid=#{sid}")
    void modify(Student student);


}
