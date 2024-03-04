<%--
  Created by IntelliJ IDEA.
  User: 27248
  Date: 12/25/2023
  Time: 1:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register A New User</title>
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

<div>
    <form method="post" action="/register">
        <div>
            <label>Email</label>
            <input type="text" name="email">
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password">
        </div>
        <div>
            <label>First Name</label>
            <input type="text" name="firstname">
        </div>
        <div>
            <label>Last Name</label>
            <input type="text" name="lastname">
        </div>
        <button type="submit">Submit</button>
    </form>
</div>

</body>
</html>
