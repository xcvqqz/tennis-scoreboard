<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 03.10.2025
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Match score</title>
</head>
<body>



<p><strong>Player One:</strong> ${match.playerOne.name}</p>
<p><strong>Score:</strong> ${match.playerOne.matchScoreDTO.matchScore}</p>
<p><strong>Game:</strong> ${match.playerOne.matchScoreDTO.matchGame}</p>
<p><strong>Set:</strong> ${match.playerOne.matchScoreDTO.matchSet}</p>
<p><strong>Advantage:</strong> ${match.playerOne.matchScoreDTO.advantage}</p>
<p><strong>TieBreakPoint:</strong> ${match.playerOne.matchScoreDTO.tieBreakPoint}</p>
<form action="match-score" method="post">
    <input type="hidden" name="playerName" value="${match.playerOne.name}">
    <input type="hidden" name="uuid" value="${param.uuid}">
    <button type="submit">Игрок 1 выиграл очко</button>
</form>
<br><br>

<p><strong>Player Two:</strong> ${match.playerTwo.name}</p>
<p><strong>Score:</strong> ${match.playerTwo.matchScoreDTO.matchScore}</p>
<p><strong>Game:</strong> ${match.playerTwo.matchScoreDTO.matchGame}</p>
<p><strong>Set:</strong> ${match.playerTwo.matchScoreDTO.matchSet}</p>
<p><strong>Advantage:</strong> ${match.playerTwo.matchScoreDTO.advantage}</p>
<p><strong>TieBreakPoint:</strong> ${match.playerTwo.matchScoreDTO.tieBreakPoint}</p>
<form action="match-score" method="post">
    <input type="hidden" name="playerName" value="${match.playerTwo.name}">
    <input type="hidden" name="uuid" value="${param.uuid}">
    <button type="submit">Игрок 2 выиграл очко</button>
</form>
<br><br>
</body>

