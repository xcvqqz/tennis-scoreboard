package controller;

import jakarta.servlet.http.HttpServlet;
import service.FinishedMatchesPersistenceService;
import service.OngoingMatchesService;
import util.UUIDUtil;

public class BasicController extends HttpServlet {

    protected final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    protected final UUIDUtil uuidUtil = new UUIDUtil();
    protected final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

}
