<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: 27248
  Date: 12/25/2023
  Time: 1:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Homepage</title>
</head>
<body>

<table border="1">
    <tr>
        <td><a href="/contact">Contact</a></td>
        <td><a href="/user/homepage">User HomePage</a></td>
        <td>
            <%
                HttpSession sessionS = request.getSession(false);

                if (sessionS != null && sessionS.getAttribute("user") != null) {
            %>

            <a href="/logout">Logout</a>
            <%
                }
                // Otherwise, show nothing
            %>
        </td>
    </tr>
</table>
    <h2>Quiz Category List</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Category ID</th>
                    <th>Category Name</th>
                    <th>New Quiz</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="category" items="${categories}">
                    <tr>
                        <td>${category.category_id}</td>
                        <td>${category.name}</td>
                        <td><a href="/quiz/new?category_id=${category.category_id}">Start</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    <h2>Recent Quiz Result</h2>
    <table border="1">
        <thead>
        <tr>
            <th>Quiz Name</th>
            <th>Time Taken</th>
            <th>Detail</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="quiz" items="${pastQuiz}">
            <tr>
                <td>${quiz.name}</td>
                <td>${quiz.time_start}</td>
                <td><a href="/user/quizDetail?quiz_id=${quiz.quiz_id}">Detail Link</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</body>
</html>
