package com.docubyte.test.app01.dao;

import com.docubyte.test.app01.beans.User;
import com.docubyte.test.app01.factory.ConnectionFactory;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class UserDaoImpl implements UserDao {


    @Override
    public String add(User user) {
        String status = "";
        try{
            Connection connection = ConnectionFactory.getConnection();
            User user1 = search(user.getId());
            if(user1 == null){
                PreparedStatement preparedStatement = connection.prepareStatement("insert into tb_users values(?,?,?,?,?,?,?,?)");
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getUsername());
                preparedStatement.setString(4, user.getPassword());
                preparedStatement.setString(5, user.getAddress());
                preparedStatement.setString(6, user.getMobileno());
                preparedStatement.setString(7, user.getEmailid());
                preparedStatement.setString(8, user.getIsactive());
                int rowCount = preparedStatement.executeUpdate();
                if(rowCount == 1){
                    status = "success";
                }else{
                    status = "failure";
                }


            }else{
                status = "existed";
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return status;
    }




    @Override
    public User search(int id) {
        User user = null;
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();;
            ResultSet resultSet = statement.executeQuery("select * from tb_users where id = "+id);
            boolean b = resultSet.next();
            if(b == true){
                user = new User();
                user.setId(resultSet.getInt("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setUsername(resultSet.getString("USERNAME"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setAddress(resultSet.getString("ADDRESS"));
                user.setMobileno(resultSet.getString("MOBILENO"));
                user.setEmailid(resultSet.getString("EMAILID"));
                user.setIsactive(resultSet.getString("ISACTIVE"));
            }else{
                user = null;
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return user;
    }


    @Override
    public String update(User user) {
        String status = "";
        try{
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update tb_users set NAME = ?, USERNAME=?, PASSWORD = ?, ADDRESS = ?, MOBILENO = ?, EMAILID = ?, IS ACTIVE = ? where ID = ?");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getMobileno());
            preparedStatement.setString(7, user.getEmailid());
            preparedStatement.setString(8, user.getIsactive());
            int rowCount = preparedStatement.executeUpdate();
            if(rowCount == 1){
                status = "success";
            }else{
                status = "failure";
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return status;
    }


    @Override
    public String delete(int id) {
        String status = "";
        try{
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete tb_users from  where ID = ?");
            preparedStatement.setInt(1, id);
            int rowCount = preparedStatement.executeUpdate();
            if(rowCount == 1){
                status = "success";
            }else{
                status = "failure";
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return status;
    }
}
