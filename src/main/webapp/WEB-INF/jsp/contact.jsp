<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: 27248
  Date: 12/25/2023
  Time: 9:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contact Pages</title>
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
