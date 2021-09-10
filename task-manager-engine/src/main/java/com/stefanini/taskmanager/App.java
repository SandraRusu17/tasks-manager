package com.stefanini.taskmanager;


import com.stefanini.taskmanager.command.Command;
import com.stefanini.taskmanager.command.CommandFactory;
import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import com.stefanini.taskmanager.entity.Email;
import com.stefanini.taskmanager.service.EmailService;
import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;
import com.stefanini.taskmanager.service.impl.EmailServiceImpl;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class App {
    public static void main(String[] args) throws  InvalidCommandException {
        EmailService emailService = ServiceFactory.getInstance().getEmailService();

        Email email = new Email("sandra.rusu17@gmail.com", "email subject", "email content");
        emailService.sendEmail(email);


        Command command = CommandFactory.parseCommandArguments(args);
        try {
            command.execute();
        } catch (InvalidCommandException | UserNotFoundException e) {
            log.error("Something bad happened during entering command",e.getMessage());
            System.exit(0);
        }






    }
}

