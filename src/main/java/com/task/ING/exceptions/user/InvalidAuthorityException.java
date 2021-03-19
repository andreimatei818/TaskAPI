package com.task.ING.exceptions.user;

import com.task.ING.exceptions.global.InvalidFormatException;

public class InvalidAuthorityException extends InvalidFormatException {
    public InvalidAuthorityException(String message) {
        super(message);
    }
}
