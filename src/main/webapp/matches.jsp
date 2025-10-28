
<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 21.10.2025
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <title>Finished Matches</title>
</head>
<body>


<form action="matches" method="get">
<label for="filter_by_player_name">Фильтрация по имени игрока</label>
    <input type="text" id="filter_by_player_name" name="filter_by_player_name" required>
</form>

<c:if test="${not empty matches}">
    <c:forEach items="${matches}" var="match">
        ${match.playerOne.name} vs ${match.playerTwo.name} | Winner: ${match.winner.name}<br><br>
    </c:forEach>
</c:if>

<c:if test="${empty matches}">
    <p>Матчи не найдены</p>
</c:if>


</body>
</html>
