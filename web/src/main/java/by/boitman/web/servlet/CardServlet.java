package by.boitman.web.servlet;

import by.boitman.service.CardService;
import by.boitman.service.dto.CardFilter;
import by.boitman.service.entity.Card;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import by.boitman.web.util.PagesUtil;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet("/cards")
public class CardServlet extends HttpServlet {

    private final CardService cardService = CardService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            req.setAttribute("cards", cardService.getFindByFilter(new CardFilter(
                    Integer.parseInt(req.getParameter("balances") != null ? req.getParameter("balances") : "2000"),
                    Integer.parseInt(req.getParameter("limit") != null ? req.getParameter("limit") : "10"),
                    Integer.parseInt(req.getParameter("page") != null ? req.getParameter("page") : "1")
            )));
            req.getRequestDispatcher(PagesUtil.CARDS).forward(req, resp);
        } else {
            redirectToCardPage(req, resp, cardService.getById(Long.parseLong(id)));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ownerName = req.getParameter("owner_name");
        String ownerSurname = req.getParameter("owner_surname");
        String dateCard = req.getParameter("date_card");
        String cardNumber = req.getParameter("card_number");
        String balance = req.getParameter("balance");
        Card cardForCreation = Card.builder()
                .ownerName(ownerName)
                .ownerSurname(ownerSurname)
                .dateCard(dateCard)
                .cardNumber(Long.valueOf(cardNumber))
                .balance(Double.valueOf(balance))
                .build();
        cardService.create(cardForCreation)
                .ifPresentOrElse(
                        card -> redirectToCardPage(req, resp, card),
                        () -> onFailedCreation(req, resp)
                );
        super.doPost(req, resp);
    }
    @SneakyThrows
    private static void redirectToCardPage(HttpServletRequest req, HttpServletResponse resp, Card card) {
        req.setAttribute("card", card);
        req.getRequestDispatcher(PagesUtil.CARD).forward(req, resp);
    }

    @SneakyThrows
    private static void onFailedCreation(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("error", true);
        req.getRequestDispatcher(PagesUtil.CARD).forward(req, resp);
    }

}
