package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.factory.ServiceFactory;
import com.stefanini.taskmanager.service.UserService;

import static com.stefanini.taskmanager.command.utils.CommandParameterParser.*;

public class AddUserCommand implements Command {

    private UserService userService = ServiceFactory.getInstance().getUserService();
    private String[] commandAndParameters;

   public AddUserCommand(final String[] commandParameters) {
       this.commandAndParameters = commandParameters;
   }

    @Override
    public void execute() throws InvalidCommandException {
        if ( commandAndParameters.length < 4) {
            throw new InvalidCommandException("Oops. Please refer to the usage of the command : " + "-createUser -fn='FirstName' -ln='LastName' -un='UserName'");
        }
        final User user = new User(getUsername(commandAndParameters),
                                    getFirstName(commandAndParameters),
                                    getLastName(commandAndParameters));
        userService.saveUser(user);
        System.out.println(user + "created successfully");
    }
}
