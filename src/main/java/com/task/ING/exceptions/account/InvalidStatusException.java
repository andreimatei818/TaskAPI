package com.task.ING.exceptions.account;

import com.task.ING.exceptions.global.InvalidFormatException;

public class InvalidStatusException extends InvalidFormatException {
    public InvalidStatusException(String message) {
        super(message);
    }
}
