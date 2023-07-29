package by.boitman.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static by.boitman.web.util.PagesUtil.MAIN;


@Controller
@RequestMapping(MAIN)
@RequiredArgsConstructor
public class MainController {
    @GetMapping
    public String getMainPage() {
        return "main";
    }
}
