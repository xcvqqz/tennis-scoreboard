<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | New Match</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">

    <style>
        .error-toast {
            position: fixed;
            bottom: 20px;
            right: 20px;
            background: #ff4444;
            color: white;
            padding: 16px 20px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
            max-width: 350px;
            z-index: 1000;
            animation: slideIn 0.3s ease-out;
        }

        .error-toast h4 {
            margin: 0 0 8px 0;
            font-size: 16px;
            font-weight: 600;
            display: flex;
            align-items: center;
            gap: 8px;
        }

        .error-toast h4:before {
            content: "⚠️";
        }

        .error-toast p {
            margin: 4px 0;
            font-size: 14px;
            line-height: 1.4;
        }

        .error-code {
            background: rgba(255, 255, 255, 0.2);
            padding: 2px 6px;
            border-radius: 4px;
            font-family: monospace;
            font-size: 12px;
        }

        .close-btn {
            position: absolute;
            top: 8px;
            right: 12px;
            background: none;
            border: none;
            color: white;
            font-size: 18px;
            cursor: pointer;
            padding: 0;
            width: 20px;
            height: 20px;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .close-btn:hover {
            background: rgba(255, 255, 255, 0.2);
            border-radius: 50%;
        }

        @keyframes slideIn {
            from {
                transform: translateX(100%);
                opacity: 0;
            }
            to {
                transform: translateX(0);
                opacity: 1;
            }
        }
    </style>

    <script src="js/app.js"></script>
</head>
<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="images/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="index">Home</a>
                <a class="nav-link" href="matches">Matches</a>
            </nav>
        </div>
    </section>
</header>

<main>
    <div class="container">
        <div>
            <h1>Start new match</h1>
            <div class="new-match-image"></div>
            <div class="form-container center">
                <form method="post" action="new-match">
                    <label class="label-player" for="playerOneName">Player one</label>
                    <input class="input-player" id="playerOneName" name="playerOneName" placeholder="Player 1 name" type="text" required title="Enter a name">
                    <label class="label-player" for="playerTwoName">Player two</label>
                    <input class="input-player" id="playerTwoName" name="playerTwoName" placeholder="Player 2 name" type="text" required title="Enter a name">
                    <input class="form-button" type="submit" value="Start">
                </form>
            </div>
        </div>
    </div>
</main>

<!-- Окошко ошибки в правом нижнем углу -->
<c:if test="${not empty errorMessage}">
    <div class="error-toast">
        <button class="close-btn" onclick="this.parentElement.style.display='none'">×</button>
        <h4>Ошибка</h4>
        <p><strong>Код:</strong> <span class="error-code">${statusCode}</span></p>
        <p><strong>Описание:</strong> ${errorMessage}</p>
    </div>
</c:if>

<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a> roadmap.</p>
    </div>
</footer>

<script>
    // Автоматическое скрытие ошибки через 10 секунд
    document.addEventListener('DOMContentLoaded', function() {
        const errorToast = document.querySelector('.error-toast');
        if (errorToast) {
            setTimeout(() => {
                errorToast.style.display = 'none';
            }, 10000);
        }
    });
</script>
</body>
</html>