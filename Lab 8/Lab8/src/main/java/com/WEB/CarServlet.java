/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.WEB;

import java.io.IOException;
import java.sql.SQLException;
import com.DAO.CarDAO;
import com.Model.Car;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;

@WebServlet("/")
public class CarServlet extends HttpServlet {

    private CarDAO carDAO;

    public void init() {
        carDAO = new CarDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try{
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertCar(request, response);
                    break;
                case "/delete":
                    deleteCar(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateCar(request, response);
                    break;
                default:
                    listCar(request, response);
                    break;
            }
        }catch(SQLException ex){
            throw new ServletException(ex);
        }
    }
    
    private void listCar(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException{
        List<Car> listCar = carDAO.selectAllCars();
        request.setAttribute("listCar", listCar);
        RequestDispatcher rd = request.getRequestDispatcher("carList.jsp");
        rd.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher rd = request.getRequestDispatcher("carForm.jsp");
        rd.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        int carId = Integer.parseInt(request.getParameter("carId"));
        Car existingCar = carDAO.selectCar(carId);
        RequestDispatcher rd = request.getRequestDispatcher("carForm.jsp");
        request.setAttribute("car", existingCar);
        rd.forward(request, response);
    }
    
    private void insertCar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        int cyclinder = Integer.parseInt(request.getParameter("cyclinder"));
        double price = Double.parseDouble(request.getParameter("price"));
        Car newCar = new Car (brand, model, cyclinder, price);
        carDAO.insertCar(newCar);
        response.sendRedirect("list");
    }
    
    private void updateCar(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException{
        int carId = Integer.parseInt(request.getParameter("carId"));
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        int cyclinder = Integer.parseInt(request.getParameter("cyclinder"));
        double price = Double.parseDouble(request.getParameter("price"));
        
        Car car = new Car(carId, brand, model, cyclinder, price);
        carDAO.updateCar(car);
        response.sendRedirect("list");
    }
    
    private void deleteCar(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException{
        int carId = Integer.parseInt(request.getParameter("carId"));
        carDAO.deleteCar(carId);
        response.sendRedirect("list");
    }
}