<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 02.11.2025
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>WINNER PAGE</title>
</head>
<body>

<c:if test="${not empty playerWinner}">
    <p><strong>У НАС ПОЯВИЛСЯ ПОБЕДИТЕЛЬ: ${playerWinner}. ПОЗДРАВЛЯЕМ!!!!</strong></p>
</c:if>
<br>
<br>
<a href="new-match">Сыграть новый матч</a>
<a href="matches">Посмотреть завершённые матчи</a>

</body>
</html>
