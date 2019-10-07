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
</head>
<body>
    <h1>Meal Management</h1>
    <h2>
        &nbsp;&nbsp;&nbsp;
       <button><a href="meals?action=meals">LIST ALL</a></button>
    </h2>

    <form action="meals" method="POST">
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${meal != null}">
                            Edit Meal
                        </c:if>
                        <c:if test="${meal == null}">
                            Add New Meal
                        </c:if>
                    </h2>
                </caption>
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
                    </td>
                </tr>
            </table>
       </form>
</body>
</html>
