package com.stefanini.taskmanager;

//import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.command.Command;
import com.stefanini.taskmanager.command.CommandFactory;
import com.stefanini.taskmanager.service.ServiceFactory;
        import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.UserService;
import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) throws UserNotFoundException, InvalidCommandException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        TaskService taskService = ServiceFactory.getInstance().getTaskService();

        Command command = CommandFactory.parseCommandArguments(args);
        try {
             command.execute();
        } catch (InvalidCommandException | UserNotFoundException e) {
            log.error("Something bad happened during entering command",e.getMessage());
            System.exit(0);
        }

    }
}
