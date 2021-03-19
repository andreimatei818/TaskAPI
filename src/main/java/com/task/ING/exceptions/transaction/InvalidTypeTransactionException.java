package com.task.ING.exceptions.transaction;

import com.task.ING.exceptions.global.InvalidFormatException;

public class InvalidTypeTransactionException extends InvalidFormatException {
    public InvalidTypeTransactionException(String message) {
        super(message);
    }
}
