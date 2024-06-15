package com.dao;
/**
 *
 * @author ASUS
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import com.model.User;
import com.util.DBConnection;
        


public class UserDao {
    private Connection connection;
    
    public UserDao()throws ClassNotFoundException{
        connection= DBConnection.getConnection();
    }
    
    public void addUser (User user){
      try{
          PreparedStatement preparedStatement = connection
                  .prepareStatement("insert into users(userid, firstname, listname) values (?, ?, ?");
          preparedStatement.setString(1, user.getUserid());
            preparedStatement.setString(2, user.getFirstName());
          preparedStatement.setString(3, user.getLastName());
          preparedStatement.executeUpdate();
          
      }  catch (SQLException e){
          e.printStackTrace();
      }
    }
    
    public void deleteUser(String userId){
        try{
            PreparedStatement preparedStatemet = connection
                    .prepareStatement("delete from users where userid=?");
            
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
            
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
