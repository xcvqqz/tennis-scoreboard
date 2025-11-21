<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>🏆 Победитель объявлен!</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #6a11cb 0%, #2575fc 100%);
            color: white;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 2rem;
        }

        .winner-container {
            text-align: center;
            max-width: 800px;
            background: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
            border-radius: 20px;
            padding: 2.5rem;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
            margin-top: 2rem;
            animation: fadeIn 1s ease-out;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        h1 {
            font-size: 2.8rem;
            margin-bottom: 1.5rem;
            text-shadow: 0 2px 10px rgba(0, 0, 0, 0.3);
            animation: pulse 1.5s infinite;
        }

        @keyframes pulse {
            0% { transform: scale(1); }
            50% { transform: scale(1.05); }
            100% { transform: scale(1); }
        }

        .winner-name {
            font-size: 3.2rem;
            font-weight: 700;
            color: #ffeb3b;
            text-shadow: 0 0 15px rgba(255, 235, 59, 0.7);
            margin: 1.5rem 0;
        }

        .winner-image-container {
            margin: 2rem auto;
            max-width: 400px;
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3);
            border: 5px solid rgba(255, 255, 255, 0.2);
        }

        .winner-image {
            width: 100%;
            display: block;
            transition: transform 0.3s ease;
        }

        .winner-image:hover {
            transform: scale(1.03);
        }

        .links-container {
            margin-top: 2.5rem;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 1.5rem;
        }

        .action-link {
            display: inline-block;
            padding: 1rem 2rem;
            background: rgba(255, 255, 255, 0.2);
            color: white;
            text-decoration: none;
            border-radius: 50px;
            font-weight: 600;
            font-size: 1.1rem;
            transition: all 0.3s ease;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }

        .action-link:hover {
            background: rgba(255, 255, 255, 0.3);
            transform: translateY(-3px);
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.3);
        }

         {
            position: fixed;
            width: 10px;
            height: 10px;
            background-color: #f0f;
            opacity: 0.7;
            animation: fall linear forwards;
        }

        @keyframes fall {
            to { transform: translateY(100vh) rotate(360deg); }
        }

        @media (max-width: 768px) {
            h1 { font-size: 2rem; }
            .winner-name { font-size: 2.2rem; }
            .winner-image-container { max-width: 90%; }
            .links-container { flex-direction: column; align-items: center; }
            .action-link { width: 80%; text-align: center; }
        }
    </style>
</head>
<body>
<div class="winner-container">
    <h1>🎉 ПОЗДРАВЛЯЕМ! 🎉</h1>

    <c:choose>
        <c:when test="${not empty playerWinner}">
            <p>Теннисный бог выбрал:</p>
            <div class="winner-name">${fn:escapeXml(playerWinner)}</div>
            <p>🏆 ИСТИННЫЙ ПОБЕДИТЕЛЬ МАТЧА! 🏆</p>
        </c:when>
        <c:otherwise>
            <p>Кто-то победил... но мы не знаем кто! 😅</p>
        </c:otherwise>
    </c:choose>

    <!-- Мем-картинка -->
    <div class="winner-image-container">
        <img
                src="${pageContext.request.contextPath}/images/winner_rat.jpg"
                alt="Ratatouille: Опа, а ты победитель!"
                class="winner-image"
                onerror="this.style.display='none'; this.previousElementSibling.textContent='Картинка не загрузилась, но ты всё равно крут!';"
        >
    </div>

    <div class="links-container">
        <a href="${pageContext.request.contextPath}/new-match" class="action-link">🔥 Сыграть новый матч</a>
        <a href="${pageContext.request.contextPath}/matches" class="action-link">📊 Посмотреть завершённые</a>
    </div>
</div>

<script>
    function createConfetti() {
        const colors = ['#ff0000', '#00ff00', '#0000ff', '#ffff00', '#ff00ff', '#00ffff'];
        const container = document.body;
        for (let i = 0; i < 100; i++) {
            const confetti = document.createElement('div');
            confetti.className = 'confetti';
            confetti.style.left = Math.random() * 100 + 'vw';
            confetti.style.backgroundColor = colors[Math.floor(Math.random() * colors.length)];
            confetti.style.width = Math.random() * 10 + 5 + 'px';
            confetti.style.height = Math.random() * 10 + 5 + 'px';
            confetti.style.animationDuration = (Math.random() * 3 + 2) + 's';
            container.appendChild(confetti);


            setTimeout(() => confetti.remove(), 5000);
        }
    }

    document.addEventListener('DOMContentLoaded', createConfetti);
</script>
</body>
</html>