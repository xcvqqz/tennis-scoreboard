<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 21.10.2025
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        ${match.getPlayerOne()} | ${match.getPlayerTwo()} | ${match.getWinner()}
    </c:forEach>

</c:if>









</body>
</html>
