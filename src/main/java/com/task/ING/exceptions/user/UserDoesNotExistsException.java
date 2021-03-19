package com.task.ING.exceptions.user;

import com.task.ING.exceptions.global.ResourceDoesNotExistsException;

public class UserDoesNotExistsException extends ResourceDoesNotExistsException {
    public UserDoesNotExistsException(String userId) {
        super("Does not exist a user with id: " + userId);
    }
}
