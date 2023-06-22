package by.boitman.web.servlet;

import by.boitman.database.dto.AccountFilter;
import by.boitman.database.entity.AccountEntity;
import by.boitman.database.entity.enam.Gender;
import by.boitman.service.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import by.boitman.web.util.PagesUtil;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet("/accounts")
public class AccountServlet extends HttpServlet {
    private final AccountService accountService = AccountService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            req.setAttribute("account", accountService.getFindByFilter(
                    AccountFilter.builder()
                            .accountBalance(req.getParameter("account_balance"))
                            .userName(req.getParameter("name"))
                            .limit(req.getParameter("limit"))
                            .page(req.getParameter("page"))
                            .build()
            ));
            req.getRequestDispatcher(PagesUtil.ACCOUNTS).forward(req, resp);
        } else {
            redirectToAccountPage(req, resp, accountService.getById(Long.parseLong(id)));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ownerNameAccount = req.getParameter("name");
        String ownerSurnameAccount = req.getParameter("surname");
        String gender = req.getParameter("gender");
        String numberAccount = req.getParameter("number_account");
        String accountBalance = req.getParameter("account_balance");
        AccountEntity accountForCreation = AccountEntity.builder()
                .ownerNameAccount(ownerNameAccount)
                .ownerSurnameAccount(ownerSurnameAccount)
                .gender(Gender.valueOf(gender))
                .numberAccount(Long.valueOf(numberAccount))
                .accountBalance(Float.valueOf(accountBalance))
                .build();
        accountService.create(accountForCreation)
                .ifPresentOrElse(
                        account -> redirectToAccountPage(req, resp, account),
                        () -> onFailedCreation(req, resp)
                );
        super.doPost(req, resp);
    }

    @SneakyThrows
    private static void redirectToAccountPage(HttpServletRequest req, HttpServletResponse resp, AccountEntity account) {
        req.setAttribute("account", account);
        req.getRequestDispatcher(PagesUtil.ACCOUNT).forward(req, resp);
    }

    @SneakyThrows
    private static void onFailedCreation(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("error", true);
        req.getRequestDispatcher(PagesUtil.ACCOUNT).forward(req, resp);
    }

}
