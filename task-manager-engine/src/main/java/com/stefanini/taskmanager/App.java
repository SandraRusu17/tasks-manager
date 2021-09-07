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
    public static void main(String[] args) throws UserNotFoundException, InvalidCommandException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        TaskService taskService = ServiceFactory.getInstance().getTaskService();


        userService.saveUser(new User("sandra","rusu","bbb"));
//        final Optional<User> byUsername = userService.findByUsername("bbb");
//        byUsername.ifPresent(System.out::println);

//        final Optional<User> byUsername = userService.findByUsername("sandrarusu");
//        byUsername.ifPresent(System.out::println);

//        final List<User> allUsers = userService.getAllUsers();
//        System.out.println(allUsers);

//        final Optional<User> findById = userService.getById(1L);
//        findById.ifPresent(System.out::println);

//        final Task tasks = taskService.deleteTaskByTitleFor("titlu","qqq");
//        System.out.println(tasks);

//        taskService.getTasksFor("qqq");

//        final Task task = new Task("this is title","this is description");



    }


}

