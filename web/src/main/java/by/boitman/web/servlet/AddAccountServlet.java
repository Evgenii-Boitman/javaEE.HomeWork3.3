package by.boitman.web.servlet;

import by.boitman.database.entity.AccountEntity;
import by.boitman.database.entity.enam.Gender;
import by.boitman.service.AccountService;
import by.boitman.web.util.PagesUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public class AddAccountServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher(PagesUtil.ADDACCOUNT).forward(req, resp);
//    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        ApplicationContext context = (ApplicationContext) getServletContext().getAttribute("applicationContext");
//        AccountService accountService = context.getBean(AccountService.class);
//        AccountEntity saved = accountService.save(
//                AccountEntity.builder()
//                        .ownerNameAccount(req.getParameter("name"))
//                        .ownerSurnameAccount(req.getParameter("surname"))
//                        .gender(Gender.valueOf(req.getParameter("gender")))
//                        .numberAccount(Long.valueOf(req.getParameter("numberAccount")))
//                        .accountBalance(Integer.parseInt(req.getParameter("accountBalance")))
//                        .build());
//        resp.sendRedirect("/login");
//
//    }
}
