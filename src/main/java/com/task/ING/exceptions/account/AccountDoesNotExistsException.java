package com.task.ING.exceptions.account;

import com.task.ING.exceptions.global.ResourceDoesNotExistsException;

public class AccountDoesNotExistsException extends ResourceDoesNotExistsException {
    public AccountDoesNotExistsException(String message) {
        super(message);
    }
}
