package com.stefanini.taskmanager;

//import com.stefanini.taskmanager.service.ServiceFactory;

import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.UserService;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

@Slf4j
public class App {
    public static void main(String[] args) throws UserNotFoundException, InvalidCommandException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        TaskService taskService = ServiceFactory.getInstance().getTaskService();

        userService.saveUser(new User("sandra17","sandra","rusu"));
        final Optional<User> byUsername = userService.findByUsername("sandra17");
        System.out.println(byUsername);

    }


}

