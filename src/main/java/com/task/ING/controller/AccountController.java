package com.task.ING.controller;

import com.task.ING.model.Account;
import com.task.ING.model.AccountDTO;
import com.task.ING.model.AccountStatusDTO;
import com.task.ING.repository.AccountRepository;
import com.task.ING.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService, AccountRepository accountRepository) {
        this.accountService = accountService;
    }


    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO accountDTO) {
        return ResponseEntity.ok(accountService.createAccount(accountDTO));
    }

    @PatchMapping("/update/status")
    public ResponseEntity<Account> closeOrReopenAccount(@RequestBody AccountStatusDTO accountUpdate) {
        return ResponseEntity.ok(accountService.updateAccount(accountUpdate));
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accountList = accountService.getAllAccounts();
        return ResponseEntity.ok(accountList);
    }
}
