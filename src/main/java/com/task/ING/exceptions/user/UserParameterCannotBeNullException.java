package com.task.ING.exceptions.user;


import com.task.ING.exceptions.global.ParameterCannotBeNullException;

public class UserParameterCannotBeNullException extends ParameterCannotBeNullException {
    public UserParameterCannotBeNullException(String field) {
        super("Field "+ field +" cannot be empty");
    }
}
