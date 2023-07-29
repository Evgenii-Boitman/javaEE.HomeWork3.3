package by.boitman.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static by.boitman.web.util.PagesUtil.ADDACCOUNT;

@Controller
@RequestMapping(ADDACCOUNT)
@RequiredArgsConstructor
public class AddAccountController {
    @GetMapping
    public String getAddAccountPage() {
        return "add-account";
    }
}
