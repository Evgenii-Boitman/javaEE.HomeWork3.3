package by.boitman.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static by.boitman.web.util.PagesUtil.BANK;

@Controller
@RequestMapping(BANK)
@RequiredArgsConstructor
public class BankController {
    @GetMapping
    public String getBankPage() {
        return "bank";
    }
}
