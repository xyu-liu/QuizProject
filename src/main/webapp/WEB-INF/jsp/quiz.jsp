<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: 27248
  Date: 12/25/2023
  Time: 4:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz</title>
</head>
<body>

<h1>Quiz: ${ongoing}</h1>

<form action="/quiz/submitAnswers" method="post"> <!-- Adjust the action attribute as needed -->
    <table border="1">
        <thead>
        <tr>
            <th>Question</th>
            <th>Options</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="entry" items="${QA}" varStatus="loop">
            <tr>
                <td>${entry.key.description}</td>
                <td>
                    <ul>
                        <c:forEach var="option" items="${entry.value}" >
                            <li>
                                <input type="radio" name="answer_${loop.index}" value="${option.choice_id}" id="${entry.key.qq_id}">
                                <label for="${entry.key.qq_id}">${option.description}</label>
                            </li>
                        </c:forEach>
                    </ul>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>

    <input type="hidden" name="quiz_id" value= ${ongoing}>
    <input type="submit" value="Submit Answers">
</form>

</body>
</html>
