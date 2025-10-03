<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 02.10.2025
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Match</title>
</head>
<body>


<form action = "new-match" method="post">

    <label for="playerOne">Player 1 Name:</label>
    <input type="text" id="playerOne" name = "playerOneName" required><br>

    <label for="playerTwo">Player 2 Name:</label>
    <input type="text" id="playerTwo" name = "playerTwoName" required><br>

    <input type = "submit" value="Нажимай кнопку для старта матча">
</form>


</body>
</html>
