<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 03.10.2025
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Match score</title>
</head>
<body>

<%--<c:set var="uuidParam" value="${param.uuid}" />--%>

<c:set var="onGoingMatch" value="${param.match}" /><br><br>


jsp
<p><strong>Player One:</strong> ${match.playerOne.name}</p>
<p><strong>Score:</strong> ${match.playerOne.matchScore}</p>
<p><strong>Game:</strong> ${match.playerOne.matchGame}</p>
<p><strong>Set:</strong> ${match.playerOne.matchSet}</p>
<p><strong>Advantage:</strong> ${match.playerOne.advantage}</p>
<p><strong>TieBreakPoint:</strong> ${match.playerOne.tieBreakPoint}</p>
<form action="match-score" method="post">
    <input type="hidden" name="playerName" value="${match.playerOne.name}">
    <button type="submit">Игрок 1 выиграл очко</button>
</form>
<br><br>

<p><strong>Player Two:</strong> ${match.playerTwo.name}</p>
<p><strong>Score:</strong> ${match.playerTwo.matchScore}</p>
<p><strong>Game:</strong> ${match.playerTwo.matchGame}</p>
<p><strong>Set:</strong> ${match.playerTwo.matchSet}</p>
<p><strong>Advantage:</strong> ${match.playerTwo.advantage}</p>
<p><strong>TieBreakPoint:</strong> ${match.playerTwo.tieBreakPoint}</p>
<form action="match-score" method="post">
    <input type="hidden" name="playerName" value="${match.playerTwo.name}">
    <button type="submit">Игрок 2 выиграл очко</button>
</form>
<br><br>



<%--<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Match score</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--<p>Player 1: <c:out value="${match.playerOne.name}"/></p>--%>
<%--<p>Player 2: <c:out value="${match.playerTwo.name}"/></p>--%>
<%--<p><strong>Winner: <c:out value="${match.winner.name}"/></strong></p>--%>


<%--</body>--%>
<%--</html>--%>