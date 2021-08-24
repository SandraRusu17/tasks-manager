package com.stefanini.taskmanager.command.utils;

//import com.stefanini.taskmanager.factory.ServiceFactory;
import com.stefanini.taskmanager.factory.ServiceFactory;
        import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.UserService;
import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

public class App {
    public static void main(String[] args) throws UserNotFoundException, InvalidCommandException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        TaskService taskService = ServiceFactory.getInstance().getTaskService();

        try {
            new TaskManager().parseCommandArguments(args).execute();
        } catch (InvalidCommandException | UserNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }


//        // add a task for a specific user
//        taskService.addTaskFor("title", " description", "ccc");
//
//        // delete task by title for a specific username
//        taskService.deleteTaskByTitleFor("lalala", "ccc");

//        // get tasks for a user by username
//        taskService.getTasksFor("ccc").forEach(u-> System.out.println(u));

//        // show all the users
//        userService.getAllUsers().forEach(u -> System.out.println(u));




//        //delete user by id
//        userService.deleteUserById(2L);
//
//
//        // find by username
//        String username = "aaaa";
//        Optional<User> userByUsername = userService.findByUsername(username);
//        userByUsername.ifPresent(System.out::println);
//

//        // add a user
//        User user = new User("qqqq","wwwww","eee");
//        userService.saveUser(user);


//          userService.addTask("this is title", "this is description", 7L);

//         // delete task by title
//        userService.deleteTaskByTitle("ppp");


//         // find user by username
//        userService.findByUsername("ccc");



    }
}
