package by.boitman.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static by.boitman.web.util.PagesUtil.CONTACT;

@Controller
@RequestMapping(CONTACT)
@RequiredArgsConstructor
public class ContactController {
    @GetMapping
    public String getContactPage() {
        return "contacts";
    }
}
