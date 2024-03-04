<%@ page import="com.example.quizproject.service.UserService" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: 27248
  Date: 12/25/2023
  Time: 11:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz Management</title>
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

<h2>Quiz List</h2>
<table border="1">
  <thead>
  <tr>
    <th>Quiz Name</th>
    <th>Time Taken</th>
    <th>User ID</th>
    <th>Category ID</th>
    <th>Detail</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="quiz" items="${quizs}">
    <tr>
      <td>${quiz.name}</td>
      <td>${quiz.time_start}</td>
      <td>${quiz.user_id}</td>
      <td>${quiz.category_id}</td>
      <td><a href="/admin/quizDetail?quiz_id=${quiz.quiz_id}">Detail Link</a></td>
    </tr>
  </c:forEach>
  </tbody>
</table>


<form action="/admin/quizResult" method="get">

  <label for="input1">Optional Filter For User ID:</label>
  <input type="text" id="input1" name="user_id">

  <label for="input2">Optional Filter For Category ID:</label>
  <input type="text" id="input2" name="category_id">

  <input type="submit" value="Submit">
</form>


</body>
</html>
