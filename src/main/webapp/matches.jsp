<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">

    <style>
        .no-matches {
            text-align: center;
            padding: 40px;
            color: #666;
            font-style: italic;
        }

        .pagination {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 8px;
            margin-top: 20px;
        }

        .pagination a {
            padding: 8px 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            text-decoration: none;
            color: #333;
            transition: all 0.3s ease;
        }

        .pagination a:hover {
            background-color: #f5f5f5;
        }

        .pagination .current {
            background-color: #007bff;
            color: white;
            border-color: #007bff;
        }

        .pagination .disabled {
            color: #ccc;
            pointer-events: none;
            border-color: #eee;
        }

        .filter-form {
            display: flex;
            gap: 10px;
            align-items: center;
            margin-bottom: 20px;
        }

        .btn-filter {
            padding: 8px 16px;
            background-color: #6c757d;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
        }

        .btn-filter:hover {
            background-color: #5a6268;
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
        <h1>Finished Matches</h1>

        <!-- Форма фильтрации -->
        <form class="filter-form" method="get" action="matches">
            <input class="input-filter"
                   name="filter_by_player_name"
                   placeholder="Filter by player name"
                   type="text"
                   value="${param.filter_by_player_name}" />
            <button type="submit" class="btn-filter">Apply Filter</button>
            <c:if test="${not empty param.filter_by_player_name}">
                <a href="matches?page=1" class="btn-filter">Reset Filter</a>
            </c:if>
        </form>

        <!-- Таблица матчей -->
        <c:choose>
            <c:when test="${empty matches}">
                <div class="no-matches">
                    <c:choose>
                        <c:when test="${not empty param.filter_by_player_name}">
                            No matches found for player "${param.filter_by_player_name}"
                        </c:when>
                        <c:otherwise>
                            No finished matches found
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:when>
            <c:otherwise>
                <table class="table-matches">
                    <tr>
                        <th>Player One</th>
                        <th>Player Two</th>
                        <th>Winner</th>
                    </tr>
                    <c:forEach var="match" items="${matches}">
                        <tr>
                            <td>${match.playerOne.name}</td>
                            <td>${match.playerTwo.name}</td>
                            <td>
                                <span class="winner-name-td">${match.winner.name}</span>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <!-- Пагинация -->
                <c:if test="${totalPages > 1}">
                    <div class="pagination">
                        <!-- Кнопка "Назад" -->
                        <c:choose>
                            <c:when test="${page > 1}">
                                <a class="prev" href="matches?page=${page - 1}<c:if test='${not empty param.filter_by_player_name}'>&filter_by_player_name=${param.filter_by_player_name}</c:if>">
                                    &lt;
                                </a>
                            </c:when>
                            <c:otherwise>
                                <span class="prev disabled">&lt;</span>
                            </c:otherwise>
                        </c:choose>

                        <!-- Номера страниц -->
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <c:choose>
                                <c:when test="${i == page}">
                                    <a class="num-page current" href="#">${i}</a>
                                </c:when>
                                <c:otherwise>
                                    <a class="num-page" href="matches?page=${i}<c:if test='${not empty param.filter_by_player_name}'>&filter_by_player_name=${param.filter_by_player_name}</c:if>">
                                            ${i}
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <!-- Кнопка "Вперед" -->
                        <c:choose>
                            <c:when test="${page < totalPages}">
                                <a class="next" href="matches?page=${page + 1}<c:if test='${not empty param.filter_by_player_name}'>&filter_by_player_name=${param.filter_by_player_name}</c:if>">
                                    &gt;
                                </a>
                            </c:when>
                            <c:otherwise>
                                <span class="next disabled">&gt;</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:if>
            </c:otherwise>
        </c:choose>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.</p>
    </div>
</footer>

<script>
    // Автоматическая отправка формы при изменении фильтра (опционально)
    document.addEventListener('DOMContentLoaded', function() {
        const filterInput = document.querySelector('input[name="filter_by_player_name"]');
        const filterForm = document.querySelector('.filter-form');
    });
</script>
</body>
</html>
