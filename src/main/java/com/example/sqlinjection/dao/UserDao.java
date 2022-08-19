package com.example.sqlinjection.dao;


import com.example.sqlinjection.model.User;
import com.example.sqlinjection.utils.ConnectionUtils;

import java.sql.*;

public class UserDao {
    Connection connection = ConnectionUtils.getConnection();

    public User getUser(String pName, String password) {
        User user = null;
//        String sql = "select * from users where username = '"+pName +"' and password = " +password;
        String sql = "select * from users where username = ? and password = ?";
        try {
            Statement statement = connection.createStatement();
            String sqlStatement = String.format("select * from users where username = '"+pName +"' and password = " +password);
            ResultSet rs = statement.executeQuery(sqlStatement);
            while (rs.next()){
                String name = rs.getString("username");
                String pass = rs.getString("password");
                user = new User(name, pass);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

}
