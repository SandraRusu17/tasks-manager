package com.stefanini.taskmanager.service.exceptions;

public class InvalidCommandException extends Exception {
    public InvalidCommandException(final String message) {
        super(message);
    }
}
