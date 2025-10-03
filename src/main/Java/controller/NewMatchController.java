package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Match;
import model.Player;

import java.io.IOException;




@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        request.getRequestDispatcher("/new-match.jsp").forward(request, response);

    }



    @Override
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {


        String playerOneName = request.getParameter("playerOneName");
        String playerTwoName = request.getParameter("playerTwoName");

        Player playerOne = new Player(playerOneName);
        Player playerTwo = new Player(playerTwoName);
        Match match = new Match(playerOne,playerTwo,playerOne);

        request.setAttribute("playerOne", playerOne);
        request.setAttribute("playerTwo", playerTwo);
        request.setAttribute("match", match);

//        Проверяет существование игроков в таблице Players. Если игрока с таким именем не существует, то создаём
//        Создаём экземпляр класса, содержащего айди игроков и текущий счёт,
//        и кладём в коллекцию текущих матчей (существующую только в памяти приложения, либо в key-value storage).
//        Ключом коллекции является UUID, значением - счёт в матче
//        Редирект на страницу /match-score?uuid=$match_id



    }



}