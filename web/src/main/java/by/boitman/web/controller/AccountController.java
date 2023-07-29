package by.boitman.web.controller;


import by.boitman.database.dto.AccountCreationDto;
import by.boitman.database.dto.AccountFilter;
import by.boitman.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static by.boitman.web.util.PagesUtil.ACCOUNT;

@Controller
@RequestMapping(ACCOUNT)
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String getAllAccountsPage(Model model, AccountFilter accountFilter) {
        model.addAttribute("accounts", accountService.getFindByFilter(accountFilter));
        return "accounts";
    }

    @GetMapping(path = "/{id}")
    public String getAccountPage(Model model, @PathVariable Long id) {
        model.addAttribute("account", accountService.findById(id));
        return "account";
//
//        return accountService.getById(id).
//                map(account -> {
//                    model.addAttribute("account", account);
//                    return "account";
//                })
//                .orElse("redirect:/account");
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(path = "/addAccount")
    public String createAccountPage(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("ownerNameAccount", accountService.getAllOwnerNameAccount());
        model.addAttribute("ownerSurnameAccount", accountService.getAllOwnerSurnameAccount());
        model.addAttribute("gender", accountService.getAllGender());
        model.addAttribute("numberAccount", accountService.getAllNumberAccount());
        model.addAttribute("accountBalance", accountService.getAllAccountBalance());
        return "addAccount";
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(path = "/addAccount")
    public String createAccount(AccountCreationDto account) {
        return "redirect:/account/" + accountService.create(account);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(path = "/{id}/update")
    public String updateAccount(@PathVariable Long id, AccountCreationDto account) {
        return accountService.update(id, account).map(
                        updatedAccount -> "redirect:/account/" + id
                )
                .orElse("redirect:/account/?error=true");
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(path = "/{id}/delete")
    public String deleteAccount(@PathVariable Long id) {
        accountService.delete(id);
        return "redirect:/account";
    }
}
