package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

public interface Command {
    public void execute() throws UserNotFoundException;
}
