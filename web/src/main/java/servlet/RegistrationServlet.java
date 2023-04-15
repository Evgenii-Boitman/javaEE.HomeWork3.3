package servlet;

import by.boitman.database.UserService;
import by.boitman.database.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.PagesUtil;

import java.io.IOException;

import static by.boitman.database.UserService.getInstance;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private UserService userService = getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PagesUtil.REGISTRATION).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.save(
                User.builder()
                        .email(req.getParameter("email"))
                        .password(req.getParameter("password"))
                        .name(req.getParameter("name"))
                        .surname(req.getParameter("surname"))
                        .gender(req.getParameter("gender"))
                        .build());
        resp.sendRedirect("/login");
    }
}
