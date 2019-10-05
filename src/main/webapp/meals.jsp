<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<table border="1">
    <tr>
        <th>DATE</th>
        <th>DESCRIPTION</th>
        <th>CALORIES</th>
    </tr>
    <%--@elvariable id="mealList" type="java.util.List"--%>
    <c:forEach var="meal" items="${mealList}">
        <tr style="background-color:${(meal.excess == true ? 'red' : 'green')}">
            <td><c:out value="${meal.dateTime}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>