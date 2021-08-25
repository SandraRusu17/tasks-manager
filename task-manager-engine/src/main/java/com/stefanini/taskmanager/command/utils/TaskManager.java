package com.stefanini.taskmanager.command.utils;

import com.stefanini.taskmanager.command.*;
import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskManager {

    //    -createUser -fn='FirstName' -ln='LastName' -un='UserName'
    public static final String CREATE_USER_COMMAND = "-createUser";
    //    -showAllUsers
    public static final String SHOW_ALL_USERS_COMMAND = "-showAllUsers";
    //    -addTask -un='UserName' -tt='TaskTitle' -td='TaskDescription'
    public static final String ADD_TASK_FOR_COMMAND = "-addTask";
    //    -showTasks -un='UserName'
    public static final String SHOW_TASKS_FOR_COMMAND = "-showTasks";
    //    -deleteTask -un='UserName' -tt='TaskTitle'
    public static final String DELETE_TASK_BY_TITLE_FOR_COMMAND = "-deleteTask";

    public static final int COMMAND = 0;


    public Command parseCommandArguments(String[] arguments) throws InvalidCommandException {
        String joinedArguments = String.join(" ", arguments); //so we can have arguments with spaces
        String[] commandAndParameters = joinedArguments.split(" -"); //split by ' -' again so we have the command and its parameters
        final String command = commandAndParameters[COMMAND];

              switch (command) {
                case CREATE_USER_COMMAND:
                    return new AddUserCommand(commandAndParameters);

                case SHOW_ALL_USERS_COMMAND:
                    return new GetAllUsersCommand();

                case ADD_TASK_FOR_COMMAND:
                    return new AddTaskCommand(commandAndParameters);

                case DELETE_TASK_BY_TITLE_FOR_COMMAND:
                    return new DeleteTaskCommand(commandAndParameters);

                case SHOW_TASKS_FOR_COMMAND:
                    return new GetTasksCommand(commandAndParameters);
                  default:
                      throw new InvalidCommandException("Oops. Unknown command [" + command + "] Please use one of the following commands: -createUser -showAllUsers -addTask");
            }
        }
    }
