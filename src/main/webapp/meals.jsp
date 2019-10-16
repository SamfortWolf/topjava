<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: #67c062;
        }

        .excess {
            color: #ff5555;
        }
    </style>
</head>
<body>
<br>
<h3><a href="index.html">Home</a></h3>
<p>Current userId is: ${userId}</p>

<hr/>
<h2>Meals</h2>
<h3>Filters:</h3>
<form method="post" action="meals">
    <input type="hidden" name="action" value="filter">
    <table cellpadding="8" cellspacing="0">
        <jsp:useBean id="filter" class="ru.javawebinar.topjava.util.Filter" scope="request"/>
        <tr>
            <td align="right" width="100">Date From:</td>
            <td><input type="date" name="dateFrom" value="${filter.dateFrom}"></td>
            <td>&nbsp&nbsp&nbsp&nbsp</td>
            <td align="right" width="100">Time From:</td>
            <td><input type="time" name="timeFrom" value="${filter.timeFrom}"></td>
        </tr>
        <tr>
            <td align="right" width="100">Date To:</td>
            <td><input type="date" name="dateTo" value="${filter.dateTo}"></td>
            <td>&nbsp&nbsp&nbsp&nbsp</td>
            <td align="right" width="100">Time To:</td>
            <td><input type="time" name="timeTo" value="${filter.timeTo}"></td>
        </tr>
    </table>
    <table cellpadding="8" cellspacing="0">
        <tr>
            <td>
                <button type="submit">Filter</button>
            </td>
            <td>
                <button type="submit" name="clearButton" value="clear">Clear</button>
            </td>
        </tr>
    </table>
</form>
<br>
<a href="meals?action=create">Add Meal</a>
<br><br>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${meals}" var="meal">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
        <tr class="${meal.excess ? 'excess' : 'normal'}">
            <td>
                    <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                    <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                    <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                    ${fn:formatDateTime(meal.dateTime)}
            </td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</section>
</body>
</html>