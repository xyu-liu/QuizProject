<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: 27248
  Date: 12/25/2023
  Time: 10:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contact Manage</title>
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


<h2>Contact List</h2>
<table border="1">
    <thead>
    <tr>
        <th>Subject</th>
        <th>Message</th>
        <th>Email</th>
        <th>Time</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="contact" items="${contacts}">
        <tr>
            <td>${contact.subject}</td>
            <td>${contact.message}</td>
            <td>${contact.email}</td>
            <td>${contact.time}</td>

        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
