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
    <title>Question Management</title>
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

<a href="/admin/userManagement/add">Add A New Question</a>

<table border="1">
    <thead>
    <tr>
        <th>Question Category</th>
        <th>Question description</th>
        <th>Status: Active Or Not</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="question" items="${questions}">
        <tr>
            <td>${question.category_id}</td>
            <td>${question.description}</td>
            <td>${question.is_active}</td>
            <td><a href="/admin/questionManagement/changeStatus?question_id=${question.question_id}">Change Status</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>



</body>
</html>
