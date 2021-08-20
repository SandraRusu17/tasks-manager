package com.stefanini.taskmanager;

import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.factory.RepositoryFactory;
//import com.stefanini.taskmanager.factory.ServiceFactory;
import com.stefanini.taskmanager.factory.ServiceFactory;
import com.stefanini.taskmanager.repository.UserJDBCRepositoryImpl;
import com.stefanini.taskmanager.repository.UserRepository;
import com.stefanini.taskmanager.service.UserService;

import java.util.Optional;

public class App {
    public static void main(String[] args) {
        UserService userService = ServiceFactory.getInstance().getUserService();

        String username = "bbb";
        Optional<User> userByUsername = userService.findByUsername(username);
        System.out.println(userByUsername);

//        User user = new User("qqqq","wwwww","eee");
//        userService.saveUser(user);
//        userService.findByUsername("ccc");

//        userService.getAllUsers().forEach(u -> System.out.println(u));




    }
}
