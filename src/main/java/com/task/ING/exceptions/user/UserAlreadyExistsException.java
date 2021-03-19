package com.task.ING.exceptions.user;

import com.task.ING.exceptions.global.ResourceAlreadyExistsException;

public class UserAlreadyExistsException extends ResourceAlreadyExistsException {
    public UserAlreadyExistsException(String username) {
        super("User with username " + username + " already exists. Please try another username in order to create an account");
    }
}
