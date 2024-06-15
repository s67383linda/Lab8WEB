
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Employee Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" 
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
            <div>
                <a href="" class="navbar-brand">Employee Management App</a>
            </div> 
            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Employees</a></li>
            </ul>
        </nav>
    </header>
    <br> 
    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <c:set var="formAction" value="${employee != null ? 'update' : 'insert'}" />
                <form action="${formAction}" method="post">

                    <h2>
                        <c:if test="${employee != null}">
                            Edit Employee
                        </c:if>
                        <c:if test="${employee == null}">                
                            Add New Employee
                        </c:if>
                    </h2>

                    <c:if test="${employee != null}">
                        <input type="hidden" name="id" value="<c:out value='${employee.id}'/>"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label>Employee Name</label>
                        <input type="text" value="<c:out value='${employee.name}'/>" class="form-control" name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Employee E-mail</label>
                        <input type="text" value="<c:out value='${employee.email}'/>" class="form-control" name="email">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Employee Position</label>
                        <input type="text" value="<c:out value='${employee.position}'/>" class="form-control" readonly>
                        <input list="positionList" id="position" class="form-control" name="position">
                        <datalist id="positionList">
                            <option value="Manager">
                            <option value="Head of Dept">
                            <option value="Supervisor">
                            <option value="Director"> 
                            <option value="INA"> 
                        </datalist>
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>

