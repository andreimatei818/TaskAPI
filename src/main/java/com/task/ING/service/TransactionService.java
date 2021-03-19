package com.task.ING.service;

import com.task.ING.model.Transaction;
import com.task.ING.repository.TransactionRepository;
import com.task.ING.validator.ValidatorRequestTransactions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(Transaction transaction) {
        logger.info("creating transaction");
        transaction.setCreatedDate(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactionForAccountInTimeFrame(String accountId, String lastHours, String lastDays) {
        logger.info("getting transactions");
        ValidatorRequestTransactions.validateParameters(accountId, lastHours, lastDays);
        LocalDateTime localDateTime = LocalDateTime.now().minusHours(Long.parseLong(lastHours)).minusDays(Long.parseLong(lastDays));
        return transactionRepository.findByFromAccountOrToAccount(Integer.parseInt(accountId), localDateTime, LocalDateTime.now());
    }
}
