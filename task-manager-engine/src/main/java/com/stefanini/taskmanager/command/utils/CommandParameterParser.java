package com.stefanini.taskmanager.command.utils;

import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;

import java.util.Arrays;

public class CommandParameterParser {
    public static String getUsername(String[] commandParameters) throws InvalidCommandException {
        return getParameterValue(commandParameters, "un");
    }

    public static String getFirstName(String[] commandParameters) throws InvalidCommandException {
        return getParameterValue(commandParameters, "fn");
    }

    public static String getLastName(String[] commandParameters) throws InvalidCommandException {
        return getParameterValue(commandParameters, "ln");
    }

    public static String getTaskDescription(String[] commandParameters) throws InvalidCommandException {
        return getParameterValue(commandParameters, "td");
    }

    public static String getTaskTitle(String[] commandParameters) throws InvalidCommandException {
        return getParameterValue(commandParameters, "tt");
    }

    public static String getParameterValue(String[] commandParameters, String commandName) throws InvalidCommandException {
        final String parameterValue = Arrays.stream(commandParameters)
                .filter(parameter -> parameter.startsWith(commandName))
                .findFirst()
                .orElseThrow(() -> new InvalidCommandException(
                        "Oops. Parameter [-" + commandName + "] not found in " + Arrays.toString(commandParameters)));
        return getStringValue(parameterValue);
    }

    private static String getStringValue(String s) throws InvalidCommandException {
        int startIndexOfQuote = s.indexOf('\'');
        int endIndexOfQuote = s.lastIndexOf('\'');
        if ((startIndexOfQuote == -1 || endIndexOfQuote == -1) || (startIndexOfQuote  == endIndexOfQuote)) {
            throw new InvalidCommandException("Oops. Invalid parameter [" + s + "] please refer to the usage of the command : -command -un='param'." +
                    "Every parameter must be enclosed in single quotes.");
        }
        return s.substring(startIndexOfQuote + 1, endIndexOfQuote);
    }
}
