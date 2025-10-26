package controller;



import dto.MatchDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;
import model.Match;
import model.Player;
import service.FinishedMatchesPersistenceService;
import service.MatchScoreCalculationService;
import service.OngoingMatchesService;
import util.UUIDUtil;
import util.mapper.PlayerMapper;

import java.io.IOException;
import java.util.UUID;


@WebServlet("/match-score")
public class MatchScoreController extends BasicController {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        UUID uuid = uuidUtil.parseUUID(request.getParameter("uuid"));


        MatchDTO match = ongoingMatchesService.getOngoingMatch(uuid);

        request.setAttribute("match", match);
        request.setAttribute("uuid", uuid);

        request.getRequestDispatcher("/match-score.jsp").forward(request, response);

//        Адрес - /match-score?uuid=$match_id. GET параметр uuid содержит UUID матча.
//
//        Интерфейс:
//        Таблица с именами игроков, текущим счётом
//        Формы и кнопки для действий - “игрок 1 выиграл текущее очко”, “игрок 2 выиграл текущее очко”
//        Нажатие кнопок приводит к POST запросу по адресу /match-score?uuid=$match_id,
//        в полях отправленной формы содержится айди выигравшего очко игрока
    }


    @Override
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String playerName = request.getParameter("playerName");
        UUID uuid = uuidUtil.parseUUID(request.getParameter("uuid"));

        MatchDTO match = ongoingMatchesService.getOngoingMatch(uuid);

        MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService(match);


        //тут ОШИБКА
        boolean isPlayerOne = playerName.equals(match.getPlayerOne().getName());
        matchScoreCalculationService.addPoint(isPlayerOne ? match.getPlayerOne() : match.getPlayerTwo());


        if(match.isMatchOver()){
            finishedMatchesPersistenceService.save(match);
            ongoingMatchesService.deleteOngoingMatch(match);
        }

        response.sendRedirect(request.getContextPath() + "/match-score?uuid=" + uuid);


//        Обработчик POST запроса:

//        Извлекает из коллекции экземпляр класса Match
//        В соответствии с тем, какой игрок выиграл очко, обновляет счёт матча
//        Если матч не закончился - рендерится таблица счёта матча с кнопками, описанными выше
//        Если матч закончился:
//        Удаляем матч из коллекции текущих матчей
//        Записываем законченный матч в SQL базу данных
//        Рендерим финальный счёт

    }


}