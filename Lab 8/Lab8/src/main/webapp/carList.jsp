<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Car Management Application</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" 
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand md navbar-dark" style="background-color: #6F7378">
                <div>
                    <a href="carForm.jsp" class="navbar-brand">Car Manager</a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%request.getContextPath();%>/list" class="nav-link">Car List</a></li>
                </ul>
            </nav>
        </header>
        <br>
        
        <div class="row">
            <div class="container">
                <h3 class="text-center">List of Cars</h3>
                <hr>
                <div class="container text-left">
                    <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New Car</a>
                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Car ID</th>
                            <th>Brand</th>
                            <th>Model</th>
                            <th>Cyclinder</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="car" items="${listCar}">
                            <tr>
                                <td>
                                    <c:out value="${car.carId}"/>
                                </td>
                                
                                <td>
                                    <c:out value="${car.brand}"/>
                                </td>
                                
                                <td>
                                    <c:out value="${car.model}"/>
                                </td>
                                
                                <td>
                                    <c:out value="${car.cyclinder}"/>
                                </td>
                                
                                <td>
                                    <c:out value="${car.price}"/>
                                </td>
                                
                                <td>
                                    <a href="edit?carId=<c:out value='${car.carId}'/>">Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="delete?carId=<c:out value='${car.carId}'/>">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
    </body>
</html>
