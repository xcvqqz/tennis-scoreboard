package controller;



import dto.MatchDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;
import service.OngoingMatchesService;
import util.UUIDUtil;

import java.io.IOException;
import java.util.UUID;


@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {

    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    private final UUIDUtil uuidUtil = new UUIDUtil();


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        String uuid = request.getParameter("uuid");
        request.setAttribute("uuid", uuid);

        //получение матча по uuid
        MatchDTO match = ongoingMatchesService.getOngoingMatch(UUID.fromString(uuid));
        request.setAttribute("match", match);
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
        System.out.println("Hello From MatchScoreController doPost method");

        String name = request.getParameter("name");



        String playerOneName = ongoingMatchesService.
        String playerTwoName;




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