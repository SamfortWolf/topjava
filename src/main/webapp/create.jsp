<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ant_n
  Date: 06.10.2019
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Meal Management</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<h1>Meal Management</h1>

<h1>
    <c:if test="${meal != null}">
        Edit Meal
    </c:if>
    <c:if test="${meal == null}">
        Add New Meal
    </c:if>
</h1>
<form action="meals" method="POST">
    <table border="1" cellpadding="5">

        <c:if test="${meal != null}">
            <input type="hidden" name="id" value="<c:out value='${meal.id}' />" />
        </c:if>
        <tr>
            <th>Meal Date: </th>
            <td>
                <input type="datetime-local" name="date" size="45"
                       value="<c:out value='${meal.dateTime}' />"
                />
            </td>
        </tr>
        <tr>
            <th>Meal Description: </th>
            <td>
                <input type="text" name="description" size="45"
                       value="<c:out value='${meal.description}' />"
                />
            </td>
        </tr>
        <tr>
            <th>Calories: </th>
            <td>
                <input type="text" name="calories" size="15"
                       value="<c:out value='${meal.calories}' />"
                />
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Save"/>
                <button><a href="meals?action=meals">LIST ALL</a></button>
            </td>
        </tr>
    </table>
</form>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
