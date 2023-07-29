package by.boitman.web.controller;

import by.boitman.database.dto.AccountCreationDto;
import by.boitman.database.dto.AccountReadDto;
import by.boitman.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static by.boitman.web.util.PagesUtil.ACCOUNT;
import static by.boitman.web.util.PagesUtil.API;


@RestController
@RequestMapping(API + ACCOUNT)
@RequiredArgsConstructor
public class AccountRestController {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountReadDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AccountReadDto> getAccount(@PathVariable Long id) {
        return accountService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    public ResponseEntity<Long> createAccount(@RequestBody AccountCreationDto newAccount) {
        return ResponseEntity.ok(accountService.create(newAccount));
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping(path = "/{id}")
    public ResponseEntity<AccountReadDto> updateAccount(@PathVariable Long id, @RequestBody AccountCreationDto newAccount) {
        return accountService.update(id, newAccount)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable Long id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
