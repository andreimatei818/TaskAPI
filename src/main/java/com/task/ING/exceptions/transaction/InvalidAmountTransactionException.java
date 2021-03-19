package com.task.ING.exceptions.transaction;

import com.task.ING.exceptions.global.InvalidFormatException;

public class InvalidAmountTransactionException extends InvalidFormatException {
    public InvalidAmountTransactionException(String message) {
        super(message);
    }
}
