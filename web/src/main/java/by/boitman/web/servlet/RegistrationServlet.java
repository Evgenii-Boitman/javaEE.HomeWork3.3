package by.boitman.web.servlet;

import by.boitman.database.UserService;
import by.boitman.database.entity.User;
import by.boitman.database.entity.enam.Gender;
import by.boitman.database.entity.enam.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import by.boitman.web.util.PagesUtil;

import java.io.IOException;
import java.util.Optional;

import static by.boitman.database.UserService.getInstance;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private UserService userService = getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PagesUtil.REGISTRATION).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<User> saved = userService.save(
                User.builder()
                        .email(req.getParameter("email"))
                        .password(req.getParameter("password"))
                        .name(req.getParameter("name"))
                        .surname(req.getParameter("surname"))
                        .gender(Gender.valueOf(req.getParameter("gender")))
                        .role(Role.valueOf(req.getParameter("roles")))
                        .build());
        resp.sendRedirect("/login");
    }
}
