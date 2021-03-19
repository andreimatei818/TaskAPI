package com.task.ING.exceptions.transaction;

import com.task.ING.exceptions.global.ParameterCannotBeNullException;

public class TransactionParameterCannotBeNullExceptionException extends ParameterCannotBeNullException {

    public TransactionParameterCannotBeNullExceptionException(String field) {
        super("Field "+ field +" cannot be empty");
    }
}
