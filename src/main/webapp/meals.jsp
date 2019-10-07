<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meals</title>
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
    <%--@elvariable id="mealList" type="java.util.concurrent.CopyOnWriteArrayList"--%>
    <c:forEach var="meal" items="${meals}">
        <tr style="background-color:${(meal.excess == true ? 'salmon' : 'palegreen')}">
            <td><c:out value="${meal.id}"/> </td>
            <td><c:out value="${meal.dateTime}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td><button><a href="meals?action=edit&mealId=<c:out value="${meal.id}"/>">EDIT</a></button></td>
            <td><button><a href="meals?action=delete&mealId=<c:out value="${meal.id}"/>">DELETE</a></button></td>
        </tr>
    </c:forEach>
</table>
<p>Count is: ${count}</p>
</body>
</html>