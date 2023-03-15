package com.example.lab5.dao;

import com.example.lab5.connection.DBConnection;
import com.example.lab5.model.Product;
import com.example.lab5.model.User;

import java.sql.*;

public class UserDAO
{
    public static Connection conn = DBConnection.getConnection();
    public int register(User u)
    {
        try {
            PreparedStatement pr = conn.prepareStatement("insert into user(username,email,password) values (?,?,?)");
            pr.setNString(1,u.getName());
            pr.setNString(2,u.getEmail());
            pr.setNString(3,u.getPass());
            return pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return 0; //insert thất bại
        }

    }
    public boolean getUserbyEmail(String email)
    {
        try {
            PreparedStatement pr = conn.prepareStatement("select * from user where email = ?");
            pr.setNString(1,email);
            ResultSet resultSet = pr.executeQuery(); //hứng kết quả trả về
            //nếu có một dòng dữ liệu
            if(resultSet.next())
            {
                return true;
            }
            else
                return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getUser(String email, String password)
    {
        try {
            PreparedStatement pr = conn.prepareStatement("select * from user where email = ? and password=?");
            pr.setNString(1,email);
            pr.setNString(2,password);
            ResultSet resultSet = pr.executeQuery();
            if(resultSet.next()){
                return true;
            }
            else
                return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public User readUser(String email)
    {
        User user = new User();
        String sql = "select * from user where email=?";
        try{
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setNString(1,email);
            ResultSet resultSet=ps.executeQuery();
            if(resultSet.next())
            {
                user.setName(resultSet.getNString("username"));
                user.setEmail(resultSet.getNString("email"));
                user.setPass(resultSet.getNString("password"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }
}
