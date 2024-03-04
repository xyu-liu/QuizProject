<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: 27248
  Date: 12/25/2023
  Time: 8:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Quiz Result Detail</title>
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

<h2>Quiz Result Detail</h2>

<h3>Quiz Name: ${quiz.name}   User Name: ${user.firstname} ${user.lastname}
  Start Time: ${quiz.time_start} End Time:${quiz.time_end} Pass:${pass}
</h3>
<table border="1">
  <thead>
  <tr>
    <th>Question Content</th>
    <th>Question Options</th>
    <th>User Selected Option</th>
    <th>Correct Option</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="entry" items="${QAMap}">
    <tr>
      <td>${entry.key}</td>
      <td>${entry.value[0]}</td>
      <td>${entry.value[1]}</td>
      <td>${entry.value[2]}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</body>
</html>
