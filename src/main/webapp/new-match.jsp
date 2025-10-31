<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 02.10.2025
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>New Match</title>
</head>
<body>


<c:if test="${not empty errorMessage}">
    <p>Код ошибки: ${statusCode}</p><br><br>
    <p>У вас ошибка: ${errorMessage}</p><br><br>
</c:if>


<form action = "new-match" method="post">

    <label for="playerOneName">Player 1 Name:</label>
    <input type="text" id="playerOneName" name = "playerOneName" required><br>

    <label for="playerTwoName">Player 2 Name:</label>
    <input type="text" id="playerTwoName" name = "playerTwoName" required><br>

    <input type = "submit" value="СТАРТ">
</form>


</body>
</html>
