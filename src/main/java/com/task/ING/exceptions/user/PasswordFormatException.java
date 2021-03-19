package com.task.ING.exceptions.user;

import com.task.ING.exceptions.global.InvalidFormatException;

public class PasswordFormatException extends InvalidFormatException {
    public PasswordFormatException(String message) {
        super(message);
    }
}
