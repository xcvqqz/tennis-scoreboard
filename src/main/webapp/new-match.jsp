<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 02.10.2025
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>New Match</title>
</head>
<body>


<form action = "new-match" method="post">

    <label for="playerOneName">Player 1 Name:</label>
    <input type="text" id="playerOneName" name = "playerOneName" required><br>

    <label for="playerTwoName">Player 2 Name:</label>
    <input type="text" id="playerTwoName" name = "playerTwoName" required><br>

    <input type = "submit" value="СТАРТ">
</form>


</body>
</html>
