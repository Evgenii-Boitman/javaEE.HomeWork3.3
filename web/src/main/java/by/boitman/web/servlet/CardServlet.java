package by.boitman.web.servlet;

import by.boitman.database.dto.CardFilter;
import by.boitman.database.entity.CardEntity;
import by.boitman.service.CardService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import by.boitman.web.util.PagesUtil;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@WebServlet("/cards")
public class CardServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = (ApplicationContext) getServletContext().getAttribute("applicationContext");
        CardService cardService = context.getBean(CardService.class);
        String id = req.getParameter("id");
        if (id == null) {
            req.setAttribute("cards", cardService.getFindByFilter(
                            CardFilter.builder()
                                    .balance(req.getParameter("balance"))
                                    .ownerName(req.getParameter("ownerName"))
                                    .limit(req.getParameter("limit"))
                                    .page(req.getParameter("page"))
                                    .build()
                    )
            );
            req.getRequestDispatcher(PagesUtil.CARDS).forward(req, resp);
        } else {
            redirectToCardPage(req, resp, cardService.getById(Long.parseLong(id)));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = (ApplicationContext) getServletContext().getAttribute("applicationContext");
        CardService cardService = context.getBean(CardService.class);
        String ownerName = req.getParameter("name");
        String ownerSurname = req.getParameter("surname");
        String cardNumber = req.getParameter("card_number");
        String balance = req.getParameter("card_balance");
        CardEntity cardForCreation = CardEntity.builder()
                .ownerName(ownerName)
                .ownerSurname(ownerSurname)
                .cardNumber(Long.valueOf(cardNumber))
                .balance(Float.valueOf(balance))
                .build();
        redirectToCardPage(req, resp, cardService.create(cardForCreation));
        super.doPost(req, resp);
    }

    @SneakyThrows
    private static void redirectToCardPage(HttpServletRequest req, HttpServletResponse resp, CardEntity card) {
        req.setAttribute("card", card);
        req.getRequestDispatcher(PagesUtil.CARD).forward(req, resp);
    }

    @SneakyThrows
    private static void onFailedCreation(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("error", true);
        req.getRequestDispatcher(PagesUtil.CARD).forward(req, resp);
    }

}
