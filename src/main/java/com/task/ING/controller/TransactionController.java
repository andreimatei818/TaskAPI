package com.task.ING.controller;

import com.task.ING.model.Transaction;
import com.task.ING.service.AccountService;
import com.task.ING.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {


    private final TransactionService transactionService;
    private final AccountService accountService;

    @Autowired
    public TransactionController(TransactionService transactionService, AccountService accountService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        accountService.processAmountForAccount(transaction);
        Transaction transactionCreated = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(transactionCreated);
    }

    @GetMapping("/transactions/{accountId}/{lastHours}/{lastDays}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable String accountId, @PathVariable String lastHours, @PathVariable String lastDays) {
        return ResponseEntity.ok(transactionService.getAllTransactionForAccountInTimeFrame(accountId, lastHours, lastDays));
    }


}
