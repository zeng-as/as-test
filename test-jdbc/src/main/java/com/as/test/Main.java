package com.as.test;

import sun.security.tools.policytool.PolicyTool;

import java.sql.*;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {

//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Enumeration<Driver> drivers = DriverManager.getDrivers();
//        while (drivers.hasMoreElements()) {
//            Driver driver = drivers.nextElement();
//            System.out.println(driver.getClass());
//            System.out.println(driver.getClass().getClassLoader());
//        }
        Connection root = DriverManager.getConnection("jdbc:mysql://localhost:3306/as", "root", "123456");
        PreparedStatement ps = root.prepareStatement("select * from student");
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()) {
//            System.out.println(resultSet.getRow());
//            System.out.println(resultSet.getInt(1));
//            System.out.println(resultSet.getString(2));
        }
        Thread.sleep(3600000);
//        String insertSql = "insert into student(name) values ('%s')";
//        for (int i = 0; i < 10000; i++) {
//            PreparedStatement ps = root.prepareStatement(String.format(insertSql, "name" + i));
//            ps.executeUpdate();
//        }
    }
}