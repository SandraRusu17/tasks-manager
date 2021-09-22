package com.stefanini.taskmanager;

import com.stefanini.taskmanager.command.AddTaskCommand;
import com.stefanini.taskmanager.command.AddUserCommand;
import com.stefanini.taskmanager.command.GetAllUsersCommand;
import com.stefanini.taskmanager.command.GetTasksCommand;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class App {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {


        final ExecutorService executor = Executors.newFixedThreadPool(4);
        Scanner scanner = new Scanner(System.in);
        log.info("Let's create a user:");
        log.info(" * First name : ");
        final String firstName = scanner.nextLine();
        log.info(" * Last name : ");
        final String lastName = scanner.nextLine();
        log.info(" * Username : ");
        final String username = scanner.nextLine();
        log.info("Let's add a task for him:");
        log.info(" * Task title : ");
        final String taskTitle = scanner.nextLine();
        log.info(" * Task Description : ");
        final String taskDescription = scanner.nextLine();

        CompletableFuture
                .runAsync(new AddUserCommand(username, firstName, lastName), executor)
                .thenRunAsync(new AddTaskCommand(taskTitle, taskDescription, username), executor)
                .thenRunAsync(new GetAllUsersCommand(), executor)
                .thenRunAsync(new GetTasksCommand(username), executor)
                .join();
        System.out.println("Finished !");
        executor.shutdownNow();
    }
}

