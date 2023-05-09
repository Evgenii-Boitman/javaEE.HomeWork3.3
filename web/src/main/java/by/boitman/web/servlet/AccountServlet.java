package by.boitman.web.servlet;

import by.boitman.database.AccountService;
import by.boitman.database.entity.Account;
import by.boitman.database.dto.AccountFilter;
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
            req.setAttribute("accounts", accountService.getFindByFilterAccount(new AccountFilter(
                    Integer.parseInt(req.getParameter("balancesAccount") != null ? req.getParameter("balancesAccount") : "1000"),
                    Integer.parseInt(req.getParameter("limitAccount") != null ? req.getParameter("limitAccount") : "10"),
                    Integer.parseInt(req.getParameter("pageAccount") != null ? req.getParameter("pageAccount") : "1")
            )));
            req.getRequestDispatcher(PagesUtil.ACCOUNTS).forward(req, resp);
        } else {
            redirectToAccountPage(req, resp, AccountService.getByIdAccount(Long.parseLong(id)));
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
