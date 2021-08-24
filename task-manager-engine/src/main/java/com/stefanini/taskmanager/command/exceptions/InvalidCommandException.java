package com.stefanini.taskmanager.command.exceptions;

public class InvalidCommandException extends Exception {
    public InvalidCommandException(final String message) {
        super(message);
    }
}
