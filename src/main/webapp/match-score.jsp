<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Match Score</title>

    <!-- Подключение шрифтов как в шаблоне 1 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@300&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/app.js"></script>

    <style>
        .special-situation {
            background: #f8f9fa;
            border: 3px solid #dc3545;
            border-radius: 10px;
            padding: 20px;
            margin: 20px 0;
            text-align: center;
        }

        .tiebreak-situation {
            border-color: #28a745;
        }

        .situation-title {
            font-family: 'Poppins', sans-serif;
            font-size: 1.5rem;
            font-weight: 700;
            margin-bottom: 15px;
            color: #dc3545;
        }

        .tiebreak-title {
            color: #28a745;
        }

        .situation-table {
            width: 100%;
            margin: 0 auto;
            border-collapse: collapse;
            background: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        .situation-table th {
            background: #343a40;
            color: white;
            padding: 12px;
            font-family: 'Poppins', sans-serif;
            font-weight: 500;
        }

        .situation-table td {
            padding: 15px;
            font-family: 'Roboto Mono', monospace;
            font-size: 1.3rem;
            font-weight: bold;
            color: #495057;
            border-bottom: 1px solid #dee2e6;
        }

        .advantage-status {
            color: #dc3545;
            font-weight: 700;
        }

        .tiebreak-points {
            color: #28a745;
            font-size: 1.4rem;
        }
    </style>
</head>
<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="${pageContext.request.contextPath}/images/menu.png" alt="Menu Toggle" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <nav class="nav-links">
            <a class="nav-link" href="${pageContext.request.contextPath}/index">Home</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/matches">Matches</a>
        </nav>
    </section>
</header>

<main>
    <div class="container">
        <h1 class="table-text">Current Match</h1>
        <div class="current-match-image">
        </div>

        <section class="score">
            <table class="table">
                <thead class="result">
                <tr>
                    <th class="table-text">Player</th>
                    <th class="table-text">Sets</th>
                    <th class="table-text">Games</th>
                    <th class="table-text">Points</th>
                </tr>
                </thead>
                <tbody>
                <tr class="player1">
                    <td class="table-text">${match.playerOne.name}</td>
                    <td class="table-text"><c:out value="${match.playerOne.matchScoreDTO.sets}"/></td>
                    <td class="table-text"><c:out value="${match.playerOne.matchScoreDTO.games}"/></td>
                    <td class="table-text"><c:out value="${match.playerOne.matchScoreDTO.points}"/></td>
                </tr>
                <tr class="player2">
                    <td class="table-text">${match.playerTwo.name}</td>
                    <td class="table-text"><c:out value="${match.playerTwo.matchScoreDTO.sets}"/></td>
                    <td class="table-text"><c:out value="${match.playerTwo.matchScoreDTO.games}"/></td>
                    <td class="table-text"><c:out value="${match.playerTwo.matchScoreDTO.points}"/></td>
                </tr>
                </tbody>
            </table>

            <!-- Формы для добавления очков -->
            <div class="score-controls">
                <form action="${pageContext.request.contextPath}/match-score" method="post" style="display: inline-block; margin-right: 10px;">
                    <input type="hidden" name="playerName" value="${match.playerOne.name}">
                    <input type="hidden" name="uuid" value="${param.uuid}">
                    <button type="submit" class="score-btn">Point for ${fn:escapeXml(match.playerOne.name)}</button>
                </form>

                <form action="${pageContext.request.contextPath}/match-score" method="post" style="display: inline-block;">
                    <input type="hidden" name="playerName" value="${match.playerTwo.name}">
                    <input type="hidden" name="uuid" value="${param.uuid}">
                    <button type="submit" class="score-btn">Point for ${fn:escapeXml(match.playerTwo.name)}</button>
                </form>
            </div>

            <!-- Секция Deuce -->
            <c:if test="${match.deuceSituation}">
                <div class="special-situation">
                    <h3 class="situation-title">DEUCE SITUATION</h3>
                    <table class="situation-table">
                        <thead>
                        <tr>
                            <th>${fn:escapeXml(match.playerOne.name)}</th>
                            <th>${fn:escapeXml(match.playerTwo.name)}</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td class="advantage-status">${match.playerOne.matchScoreDTO.hasAdvantage ? 'ADVANTAGE' : 'DEUCE'}</td>
                            <td class="advantage-status">${match.playerTwo.matchScoreDTO.hasAdvantage ? 'ADVANTAGE' : 'DEUCE'}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </c:if>

            <!-- Секция Tie-Break -->
            <c:if test="${match.openTieBreak}">
                <div class="special-situation tiebreak-situation">
                    <h3 class="situation-title tiebreak-title">TIE-BREAK IN PROGRESS</h3>
                    <table class="situation-table">
                        <thead>
                        <tr>
                            <th>${fn:escapeXml(match.playerOne.name)}</th>
                            <th>${fn:escapeXml(match.playerTwo.name)}</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td class="tiebreak-points"><c:out value="${match.playerOne.matchScoreDTO.tieBreakPoints}"/></td>
                            <td class="tiebreak-points"><c:out value="${match.playerTwo.matchScoreDTO.tieBreakPoints}"/></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </section>
    </div>

    <form id="winnerRedirectForm" method="get" style="display: none;">
        <input type="hidden" id="winnerNameInput" name="playerWinner" value="">
    </form>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const playerOneSets = parseInt(document.getElementById('playerOneSets').textContent, 10);
            const playerTwoSets = parseInt(document.getElementById('playerTwoSets').textContent, 10);
            const playerOneName = document.getElementById('playerOneName').textContent;
            const playerTwoName = document.getElementById('playerTwoName').textContent;

            let winnerFound = false;
            let winnerName = '';

            if (playerOneSets === 2) {
                winnerName = playerOneName;
                winnerFound = true;
            } else if (playerTwoSets === 2) {
                winnerName = playerTwoName;
                winnerFound = true;
            }

            if (winnerFound) {
                const form = document.getElementById('winnerRedirectForm');
                document.getElementById('winnerNameInput').value = winnerName;
                form.action = '${pageContext.request.contextPath}/winner-match';
                form.submit();
            }
        });
    </script>
</main>

<footer>
    <div class="footer">
        <p class="table-text">&copy; <fmt:formatDate value="${now}" pattern="yyyy" /> Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/" target="_blank">zhukovsd/java-backend-learning-course</a> roadmap.</p>
    </div>
</footer>
</body>
</html>