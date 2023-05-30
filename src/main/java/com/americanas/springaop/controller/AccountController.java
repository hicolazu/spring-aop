package com.americanas.springaop.controller;

import com.americanas.springaop.model.Account;
import com.americanas.springaop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<Set<Account>> findAllAccounts() {
        return ResponseEntity.ok(accountService.findAllAccounts());
    }

    @PostMapping
    public ResponseEntity<Void> saveAccount(@RequestBody final Account account) {
        try {
            accountService.saveAccount(account);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
