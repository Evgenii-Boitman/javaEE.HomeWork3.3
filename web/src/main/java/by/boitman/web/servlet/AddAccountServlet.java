package by.boitman.web.servlet;

import by.boitman.database.AccountService;
import by.boitman.database.entity.Account;
import by.boitman.web.util.PagesUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

import static by.boitman.database.AccountService.getInstance;

@WebServlet("/addAccount")
public class AddAccountServlet extends HttpServlet {

    private AccountService accountService = getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PagesUtil.ADD).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String ownerNameAccount = req.getParameter("owner_name_account");
        String ownerSurnameAccount = req.getParameter("owner_surname_account");
        String numberAccount = req.getParameter("account_number");
        String balanceAccount = req.getParameter("balance_account");
        Optional<Account> saved = accountService.save(
        Account.builder()
                .ownerNameAccount(ownerNameAccount)
                .ownerSurnameAccount(ownerSurnameAccount)
                .numberAccount(Long.valueOf(numberAccount))
                .balanceAccount(Double.valueOf(balanceAccount))
                .build());
        resp.sendRedirect("/login");
    }
}
