package com.task.ING.service;


import com.task.ING.exceptions.account.AccountDoesNotExistsException;
import com.task.ING.exceptions.account.AccountStillContainsMoneyException;
import com.task.ING.exceptions.account.NotEnoughMoneyException;
import com.task.ING.model.*;
import com.task.ING.repository.AccountRepository;
import com.task.ING.validator.AccountValidator;
import com.task.ING.validator.ValidatorTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    Logger logger = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepository accountRepository;
    private final UserService userService;

    @Autowired
    public AccountService(AccountRepository accountRepository, UserService userService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
    }

    public Account createAccount(AccountDTO accountDTO) {
        logger.info("creating account");
        AccountValidator.validate(accountDTO);
        checkIfUserIdExists(accountDTO.getUserId());
        Account account = createNewAccount(accountDTO);
        return accountRepository.save(account);
    }

    private void checkIfUserIdExists(String userId) {
      userService.checkExistUser(userId);
    }

    private Account createNewAccount(AccountDTO accountDTO) {
        return Account.builder()
                .createdDate(LocalDateTime.now())
                .availableAmount(Double.parseDouble(accountDTO.getAvailableAmount()))
                .userId(accountDTO.getUserId())
                .status(true).build();
    }

    public Account updateAccount(AccountStatusDTO accountUpdate) {
        logger.info("Updating account status");
        AccountValidator.validateUpdateStatus(accountUpdate);
        Optional<Account> accountForUpdate = accountRepository.findById(accountUpdate.getAccountId());
        checkIfAccountExists(accountUpdate, accountForUpdate);
        checkIfItPossibleToChangeStatus(accountForUpdate.get(), accountUpdate);
        Account account = accountForUpdate.get();
        setAccountStatus(accountUpdate, account);
        return accountRepository.save(account);
    }

    private void checkIfItPossibleToChangeStatus(Account account, AccountStatusDTO accountStatusDTO) {
        if (AccountStatus.INACTIVE.name().equals(accountStatusDTO.getStatus()) && account.getAvailableAmount() > 0) {
            String message = "The amount of the account should be 0 to close it ";
            logger.info(message);
            throw new AccountStillContainsMoneyException(message);
        }
    }

    private void checkIfAccountExists(AccountStatusDTO accountUpdate, Optional<Account> accountForUpdate) {
        if (!accountForUpdate.isPresent()) {
            String message = "Account " + accountUpdate.getAccountId() + " does not exists";
            logger.info(message);
            throw new AccountDoesNotExistsException(message);
        }
    }

    private void setAccountStatus(AccountStatusDTO accountUpdate, Account account) {
        if (AccountStatus.ACTIVE.name().equals(accountUpdate.getStatus())) {
            account.setStatus(true);
        } else if (AccountStatus.INACTIVE.name().equals(accountUpdate.getStatus())) {
            account.setStatus(false);
        }

    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }


    public void processAmountForAccount(Transaction transaction) {
        ValidatorTransaction.validateTransaction(transaction);
        logger.info("processing transaction");
        switch (transaction.getType()) {
            case "TRANSFER": {
                Optional<Account> accountFrom = accountRepository.findById(transaction.getFromAccount());
                Optional<Account> accountTo = accountRepository.findById(transaction.getToAccount());
                checkIfAccountExists(accountFrom);
                checkIfAccountExists(accountTo);
                checkIfAccountHasEnoughMoney(accountFrom.get(), transaction);
                Account accountFromUpdate = accountRepository.getOne(accountFrom.get().getAccountId());
                Account accountToUpdate = accountRepository.getOne(accountTo.get().getAccountId());
                accountFromUpdate.setAvailableAmount(accountFromUpdate.getAvailableAmount() - transaction.getAmount());
                accountToUpdate.setAvailableAmount(accountToUpdate.getAvailableAmount() + transaction.getAmount());
                break;
            }
            case "DRAWDOWN": {
                Optional<Account> accountTo = accountRepository.findById(transaction.getToAccount());
                checkIfAccountExists(accountTo);
                checkIfAccountHasEnoughMoney(accountTo.get(), transaction);
                Account accountToUpdate = accountRepository.getOne(accountTo.get().getAccountId());
                accountToUpdate.setAvailableAmount(accountToUpdate.getAvailableAmount() - transaction.getAmount());
                break;
            }
            case "TOPUP": {
                Optional<Account> accountTo = accountRepository.findById(transaction.getToAccount());
                Account accountToUpdate = accountRepository.getOne(accountTo.get().getAccountId());
                accountToUpdate.setAvailableAmount(accountToUpdate.getAvailableAmount() + transaction.getAmount());
                checkIfAccountExists(accountTo);
                break;
            }
        }
    }

    private void checkIfAccountExists(Optional<Account> account) {
        if (!account.isPresent()) {
            throw new AccountDoesNotExistsException("Account+" + account + " doesn't exists");
        }
    }

    private void checkIfAccountHasEnoughMoney(Account account, Transaction transaction) {
        if (account.getAvailableAmount() < transaction.getAmount()) {
            throw new NotEnoughMoneyException("Not enough money in account " + account.getAccountId());
        }
    }
}
