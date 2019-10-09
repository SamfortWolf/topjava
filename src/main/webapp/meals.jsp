<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<h3><a href="index.jsp">Home</a></h3>
<hr>
<h2>Meals</h2>

<button><a href="meals?action=new">ADD NEW</a></button>
<table border="1">
    <tr>
        <th>ID</th>
        <th>DATE</th>
        <th>DESCRIPTION</th>
        <th>CALORIES</th>
        <th>EDIT</th>
        <th>DELETE</th>
    </tr>
    <jsp:useBean id="meals" scope="request" type="java.util.Map"/>
    <c:forEach var="meal" items="${meals}">
        <jsp:useBean id="meal" type="java.util.Map.Entry"/>
        <tr style="background-color:${(meal.value.excess == true ? 'salmon' : 'palegreen')}">
            <td><c:out value="${meal.key}"/></td>
            <td><c:out value="${TimeUtil.toStringDate(meal.value.dateTime)}"/></td>
            <td><c:out value="${meal.value.description}"/></td>
            <td><c:out value="${meal.value.calories}"/></td>
            <td>
                <button><a href="meals?action=edit&mealId=<c:out value="${meal.key}"/>">EDIT</a></button>
            </td>
            <td>
                <button><a href="meals?action=delete&mealId=<c:out value="${meal.key}"/>">DELETE</a></button>
            </td>
        </tr>
    </c:forEach>
</table>
<p>Count of meals is: ${count}</p>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>
