package by.boitman.web.controller;

import by.boitman.database.dto.RegistrationDto;
import by.boitman.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static by.boitman.web.util.PagesUtil.REGISTRATION;

@Controller
@RequestMapping(REGISTRATION)
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping
    public String register(RegistrationDto registration) {
        return userService.createUser(registration)
                .map(user -> "redirect:/login")
                .orElse("redirect:/registration?error");
    }
}
