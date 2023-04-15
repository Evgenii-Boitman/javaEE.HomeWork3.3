package servlet;

import by.boitman.database.CardService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.PagesUtil;

import java.io.IOException;

@WebServlet("/cards")
public class CardServlet extends HttpServlet {

    private final CardService cardService = CardService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            req.setAttribute("cards", cardService.getAll());
            req.getRequestDispatcher(PagesUtil.CARDS).forward(req, resp);
        } else {
            req.setAttribute("card", cardService.getById(Long.parseLong(id)));
            req.getRequestDispatcher(PagesUtil.CARD).forward(req, resp);
        }
    }
}
