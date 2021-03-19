package com.task.ING.exceptions.account;

public class AccountStillContainsMoneyException extends RuntimeException{
    public AccountStillContainsMoneyException(String message) {
        super(message);
    }
}
