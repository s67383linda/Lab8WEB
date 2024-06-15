/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.Model.Car;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CarDAO {
    Connection connection=null;
    private String jdbcURL = "jdbc:mysql://localhost:3306/carshop";
    private String jdbcUsername = "root";
    private String jdbcPassword = "admin";
    
    private static final String INSERT_CARS_SQL = "INSERT INTO carpricelist (brand, model, cyclinder, price) VALUES (?, ?, ?, ?)";
    private static final String SELECT_CARS_BY_ID = "SELECT carid, brand, model, cyclinder, price from carpricelist where carid=?";
    private static final String SELECT_ALL_CARS = "SELECT * FROM carpricelist";
    private static final String DELETE_CARS_SQL = "DELETE FROM carpricelist where carid = ?";
    private static final String UPDATE_CARS_SQL = "UPDATE carpricelist set brand = ?, model = ?, cyclinder = ?, price = ? where carid = ?";
    
    public CarDAO() {}

    protected Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL ,jdbcUsername , jdbcPassword);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
    
    
    public void insertCar(Car car) throws SQLException{
        System.out.println(INSERT_CARS_SQL);
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CARS_SQL);){
            
            preparedStatement.setString(1, car.getBrand());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setInt(3, car.getCyclinder());
            preparedStatement.setDouble(4, car.getPrice());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public Car selectCar(int carId) {
        Car car = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CARS_BY_ID);){
            preparedStatement.setInt(1, carId);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                int cyclinder = rs.getInt("cyclinder");
                double price = rs.getDouble("price");
                car = new Car(carId, brand, model, cyclinder, price);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return car;
    }

    public List<Car> selectAllCars() {
        List<Car> cars = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CARS);){
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int carId = rs.getInt("carid");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                int cyclinder = rs.getInt("cyclinder");
                double price = rs.getDouble("price");
                cars.add(new Car(carId, brand, model, cyclinder, price));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return cars;
    }

    public boolean deleteCar(int carId) throws SQLException{
        boolean rowDeleted;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CARS_SQL);){
            preparedStatement.setInt(1, carId);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateCar(Car car) throws SQLException{
        boolean rowUpdated;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CARS_SQL);){
            preparedStatement.setString(1, car.getBrand());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setInt(3, car.getCyclinder());
            preparedStatement.setDouble(4, car.getPrice());
            preparedStatement.setInt(5, car.getCarId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    
    private void printSQLException(SQLException ex){
        for(Throwable e : ex){
            if(e instanceof SQLException){
                e.printStackTrace(System.err);
                System.out.println("SQLState : " + ((SQLException) e).getSQLState());
                System.out.println("Error Code : " + ((SQLException) e).getErrorCode());
                System.out.println("Message : " + e.getMessage());
                Throwable t = ex.getCause();
                while(t != null){
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
