package by.boitman.web.servlet;

import by.boitman.database.entity.UserEntity;
import by.boitman.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import by.boitman.web.util.PagesUtil;
import org.springframework.context.ApplicationContext;
import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PagesUtil.LOGIN).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = (ApplicationContext) getServletContext().getAttribute("applicationContext");
        UserService userService = context.getBean(UserService.class);
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        userService.getBy(email, password)
                .ifPresentOrElse(
                        user -> successLogin(req, resp, user),
                        () -> failedLogin(req, resp));
    }

    @SneakyThrows
    private static void successLogin(HttpServletRequest req, HttpServletResponse resp, UserEntity user) {
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/accounts");
    }

    @SneakyThrows
    private static void failedLogin(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/login?error=true");
    }
}
