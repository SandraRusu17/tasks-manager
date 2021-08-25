package com.stefanini.taskmanager.command.utils;

import com.stefanini.taskmanager.command.*;
import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommandFactory {

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


    public static Command parseCommandArguments(String[] arguments) throws InvalidCommandException {
//        String joinedArguments = String.join(" ", arguments); //so we can have arguments with spaces
//        String[] commandAndParameters = joinedArguments.split(" -"); //split by ' -' again so we have the command and its parameters
        final String command = arguments[COMMAND];

              switch (command) {
                case CREATE_USER_COMMAND:
                    return new AddUserCommand(arguments);

                case SHOW_ALL_USERS_COMMAND:
                    return new GetAllUsersCommand();

                case ADD_TASK_FOR_COMMAND:
                    return new AddTaskCommand(arguments);

                case DELETE_TASK_BY_TITLE_FOR_COMMAND:
                    return new DeleteTaskCommand(arguments);

                case SHOW_TASKS_FOR_COMMAND:
                    return new GetTasksCommand(arguments);
                  default:
                      throw new InvalidCommandException("Oops. Unknown command [" + command + "] Please use one of the following commands: -createUser -showAllUsers -addTask");
            }
        }
    }
