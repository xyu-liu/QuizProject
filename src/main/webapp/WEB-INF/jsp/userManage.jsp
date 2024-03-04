<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: 27248
  Date: 12/25/2023
  Time: 10:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
</head>
<body>

<table border="1">
    <tr>

        <td><a href="/admin/homepage">Admin HomePage</a></td>
        <td><a href="/admin/userManagement">User Management</a></td>
        <td><a href="/admin/quizResult">Quiz Result Management</a></td>
        <td><a href="/admin/questionManagement">Question Management</a></td>
        <td><a href="/admin/contactManagement">Contact Us Management</a></td>
        <td>
            <%
                HttpSession sessionS = request.getSession(false);
                if (sessionS != null && sessionS.getAttribute("user") != null) {
            %>
            <a href="/logout">Logout</a>
            <%
                }
            %>
        </td>
    </tr>
</table>


<h2>Users List</h2>


<table border="1">
    <thead>
    <tr>
        <th>Email</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Status: Active Or Not</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.email}</td>
            <td>${user.firstname}</td>
            <td>${user.lastname}</td>
            <td>${user.is_active}</td>
            <td><a href="/admin/userManagement/changeStatus?user_id=${user.user_id}">Change Status</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
