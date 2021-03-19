package com.task.ING.exceptions.account;

import com.task.ING.exceptions.global.ParameterCannotBeNullException;

public class AccountParameterCannotBeNullException extends ParameterCannotBeNullException {

    public AccountParameterCannotBeNullException(String parameter) {
        super("Parameter " + parameter + " is required and cannot be null");
    }
}
