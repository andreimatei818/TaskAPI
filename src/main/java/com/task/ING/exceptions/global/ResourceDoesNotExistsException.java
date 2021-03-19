package com.task.ING.exceptions.global;

public class ResourceDoesNotExistsException extends  RuntimeException{
    public ResourceDoesNotExistsException(String message) {
        super(message);
    }
}
