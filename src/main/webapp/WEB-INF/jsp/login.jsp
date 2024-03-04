<%--
  Created by IntelliJ IDEA.
  User: 27248
  Date: 12/24/2023
  Time: 7:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Login</title>
</head>
<body>
<div>
    <form method="post" action="/login">
        <div>
            <label>Email</label>
            <input type="text" name="email">
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password">
        </div>
        <button type="submit">Submit</button>
    </form>
</div>
<a href="/register"> Register </a>
<div>

</div>

</body>
</html>
