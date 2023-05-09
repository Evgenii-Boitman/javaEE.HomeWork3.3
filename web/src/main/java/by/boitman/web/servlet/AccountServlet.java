package by.boitman.web.servlet;
import by.boitman.database.AccountService;
import by.boitman.database.entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import by.boitman.web.util.PagesUtil;
import lombok.SneakyThrows;

import java.io.IOException;
@WebServlet("/accounts")
public class AccountServlet extends HttpServlet{
    private final AccountService accountService = AccountService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String id = req.getParameter("id");
//        if (id == null) {
//            req.setAttribute("cards", cardService.getFindByFilter(new CardFilter(
//                    Integer.parseInt(req.getParameter("balance") != null ? req.getParameter("balance") : "1000"),
//                    Integer.parseInt(req.getParameter("limit") != null ? req.getParameter("limit") : "100")
//                    )));
//            req.getRequestDispatcher(PagesUtil.CARDS).forward(req, resp);
//        } else {
//            redirectToCardPage(req, resp, cardService.getById(Long.parseLong(id)));
        String id = req.getParameter("id");
        if (id == null) {
            req.setAttribute("accounts", accountService.getAllAccount());
            req.getRequestDispatcher(PagesUtil.ACCOUNTS).forward(req, resp);
        } else {
            req.setAttribute("account", accountService.getByIdAccount(Long.parseLong(id)));
            req.getRequestDispatcher(PagesUtil.ACCOUNT).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ownerNameAccount = req.getParameter("owner_name_account");
        String ownerSurnameAccount = req.getParameter("owner_surname_account");
        String numberAccount = req.getParameter("account_number");
        String balanceAccount = req.getParameter("balance_account");
        Account accountForCreation = Account.builder()
                .ownerNameAccount(ownerNameAccount)
                .ownerSurnameAccount(ownerSurnameAccount)
                .numberAccount(Long.valueOf(numberAccount))
                .balanceAccount(Double.valueOf(balanceAccount))
                .build();
        accountService.createAccount(accountForCreation)
                .ifPresentOrElse(
                        account -> redirectToAccountPage(req, resp, account),
                        () -> onFailedCreation(req, resp)
                );
        super.doPost(req, resp);
    }


    @SneakyThrows
    private static void redirectToAccountPage(HttpServletRequest req, HttpServletResponse resp, Account account) {
        req.setAttribute("account", account);
        req.getRequestDispatcher(PagesUtil.ACCOUNT).forward(req, resp);
    }

    @SneakyThrows
    private static void onFailedCreation(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("error", true);
        req.getRequestDispatcher(PagesUtil.ACCOUNT).forward(req, resp);
    }
}
