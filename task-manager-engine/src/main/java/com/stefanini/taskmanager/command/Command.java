package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

public interface Command {
    void execute() throws UserNotFoundException, InvalidCommandException;
}
