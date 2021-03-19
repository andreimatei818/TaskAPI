package com.task.ING.exceptions.account;

import com.task.ING.exceptions.global.InvalidFormatException;

public class InvalidAmountFormatException extends InvalidFormatException {
    public InvalidAmountFormatException(String message) {
        super(message);
    }
}
