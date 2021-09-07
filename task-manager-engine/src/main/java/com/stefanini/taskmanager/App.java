package com.stefanini.taskmanager;

//import com.stefanini.taskmanager.service.ServiceFactory;

import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.UserService;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class App {
    public static void main(String[] args) throws UserNotFoundException{
        UserService userService = ServiceFactory.getInstance().getUserService();
        TaskService taskService = ServiceFactory.getInstance().getTaskService();

//
//        userService.saveUser(new User("sandra","rusu","bbb"));
//        final User byUsername = userService.findByUsername("bbb");
//        log.info(byUsername.toString());
//
//        final Task task = new Task("titluuu", "descriereee");
//        taskService.saveTask(task);
//
//        taskService.findAllTasks();

//        final User byUsername = userService.findByUsername("qqq");
//        System.out.println(byUsername);

//        final List<User> allUsers = userService.getAllUsers();
//        System.out.println(allUsers);

//        final Optional<User> findById = userService.getById(1L);
//        findById.ifPresent(System.out::println);





    }


}

