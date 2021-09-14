package com.stefanini.taskmanager;


import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.service.EmailService;
import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.service.UserService;


public class App {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws InvalidCommandException {
        EmailService emailService = ServiceFactory.getInstance().getEmailService();
        UserService userService = ServiceFactory.getInstance().getUserService();

        User user = new User("nanana", "lala", "ttt");
        userService.saveUser(user);

//
//        Command command = CommandFactory.parseCommandArguments(args);
//        try {
//            command.execute();
//        } catch (InvalidCommandException | UserNotFoundException e) {
//            log.error("Something bad happened during entering command",e.getMessage());
//            System.exit(0);
//        }
//


    }
}

