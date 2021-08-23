package com.stefanini.taskmanager.repository;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class TaskRepositoryImpl implements TaskRepository {

    private final String URL = "jdbc:mysql://localhost:3306/";
    private final String DATABASE = "taskmanager";
    private final String USERNAME = "root";
    private final String PASSWORD = "mysqleight";


    public static TaskRepositoryImpl INSTANCE;

    private TaskRepositoryImpl() {
    }

    public static TaskRepositoryImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TaskRepositoryImpl();
        }

        return INSTANCE;
    }


    @Override
    public int addTaskFor(String taskTitle, String taskDescription, String username) {
        Task task = new Task();
        int result = 0;
        User user = null;
        try (Connection connection = DriverManager.getConnection(URL + DATABASE, USERNAME, PASSWORD);
             PreparedStatement ps1 = connection.prepareStatement("SELECT * FROM users WHERE username=?");
             PreparedStatement ps2 = connection.prepareStatement("INSERT INTO tasks(title, description, user_id) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)){

            ps1.setString(1, username);

            try (ResultSet r = ps1.executeQuery()) {
                while (r.next()) {
                    user = new User(r.getLong("id"),
                            r.getString("firstName"),
                            r.getString("lastName"),
                            r.getString("userName"));
                }
                ps2.setLong(3, user.getId());
                ps2.setString(1,taskTitle);
                ps2.setString(2,taskDescription);
                result = ps2.executeUpdate();

                try (ResultSet generatedKeys = ps2.getGeneratedKeys()) {

                    if (generatedKeys.next())
                        task.setId(generatedKeys.getLong(1));
                }
            }

        }catch (SQLException e) {
            log.error("Something bad happened during fetching a task with username = {}", username, e);
        }
        return result;
    }

    @Override
    public List<Task> getTasksFor(String username) {
        List<Task> tasks = new ArrayList<>();
        User user = null;
        try (Connection connection = DriverManager.getConnection(URL + DATABASE, USERNAME, PASSWORD);
             PreparedStatement ps1 = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
             PreparedStatement ps2 = connection.prepareStatement("SELECT * FROM tasks WHERE user_id = ?")) {

            ps1.setString(1, username);

            try (ResultSet r = ps1.executeQuery()) {
                while (r.next()) {
                    user = new User(r.getLong("id"),
                            r.getString("firstName"),
                            r.getString("lastName"),
                            r.getString("userName"));
                }

                ps2.setLong(1, user.getId());

                try (ResultSet r2 = ps2.executeQuery()) {
                    while (r2.next()) {
                        Task task = new Task(r2.getLong("id"),
                                r2.getString("title"),
                                r2.getString("description"),
                                r2.getLong("user_id"));
                        tasks.add(task);
                    }
                }
            }

        } catch (SQLException e) {
            log.error("Something bad happened during fetching a task with username = {}", username, e);
        }

        return tasks;
    }


    @Override
    public void deleteTaskByTitleFor(String taskTitle, String username) {

        User user = null;
        Task task = null;
        try (Connection connection = DriverManager.getConnection(URL + DATABASE, USERNAME, PASSWORD);
             PreparedStatement ps1 = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
             PreparedStatement ps2 = connection.prepareStatement("SELECT * FROM tasks WHERE title = ?");
             PreparedStatement ps3 = connection.prepareStatement("DELETE FROM tasks WHERE title = ? AND user_id = ?")) {

            ps1.setString(1, username);

            try (ResultSet r = ps1.executeQuery()) {
                while (r.next()) {
                     user = new User(r.getLong("id"),
                            r.getString("firstName"),
                            r.getString("lastName"),
                            r.getString("userName"));
                }
                ps3.setLong(2, user.getId());

                ps2.setString(1, taskTitle);

                try (ResultSet r2 = ps2.executeQuery()) {
                    while (r2.next()) {
                        task = new Task(r2.getLong("id"),
                                r2.getString("title"),
                                r2.getString("description"),
                                r2.getLong("user_id"));
                    }

                    ps3.setString(1, task.getTitle());

                }
            }
            ps3.executeUpdate();
        } catch (SQLException e) {
            log.error("Something bad happened during fetching a task with username = {} and title = {}", username, taskTitle, e);
        }
    }
}
