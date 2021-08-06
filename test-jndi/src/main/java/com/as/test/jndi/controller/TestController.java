package com.as.test.jndi.controller;

import com.as.test.jndi.dao.domain.Student;
import com.as.test.jndi.dao.mapper.StudentMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author as.
 * @since 2020/9/8
 */
@RestController
@MapperScan("com.as.test.jndi.dao.mapper")
public class TestController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("hello")
    public String hello() throws Exception {
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("select name from t_student where id=112");
        ResultSet rs = ps.executeQuery();
        rs.next();
        return "hello " + rs.getString(1);
    }

    @GetMapping("hello2")
    public String hello2() {
        Student student = studentMapper.selectByPrimaryKey("112");
        return "hello2 " + student.getName();
    }
}
