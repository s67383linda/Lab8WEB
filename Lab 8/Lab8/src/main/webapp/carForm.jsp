<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Car Management Application</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>

    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #6F7378">
                <div>
                    <a href="carForm.jsp" class="navbar-brand">Car Manager</a>
                </div>
                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Car List</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:set var="formAction" value="${car != null ? 'update' : 'insert'}" />
                    <form action="${formAction}" method="post">

                        <h2>
                            <c:if test="${car != null}">
                                Edit Car
                            </c:if>
                            <c:if test="${car == null}">
                                Add New Car
                            </c:if>
                        </h2>

                        <c:if test="${car != null}">
                            <input type="hidden" name="carId" value="<c:out value='${car.carId}'/>"/>
                        </c:if>

                        <fieldset class="form-group">
                            <label>Car Brand</label>
                            <input type="text" value="<c:out value='${car.brand}'/>" class="form-control" name="brand" required="required"/>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Car Model</label>
                            <input type="text" value="<c:out value='${car.model}'/>" class="form-control" name="model" required="required"/>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Car Cyclinder</label>
                            <input type="text" value="<c:out value='${car.cyclinder}'/>" class="form-control" name="cyclinder" required="required"/>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Car Price</label>
                            <input type="text" value="<c:out value='${car.price}'/>" class="form-control" name="price" required="required"/>
                        </fieldset>
                        <br>
                        <button type="submit" class="btn btn-success">Save</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

