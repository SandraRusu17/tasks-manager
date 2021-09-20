package com.stefanini.taskmanager.repository.impl;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.repository.DataSourceProvider;
import com.stefanini.taskmanager.repository.UserRepository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


public class UserJDBCRepositoryImpl<T> implements UserRepository {


    private static final String DELETE_USER = "DELETE FROM users WHERE id=?";
    private static final String FIND_BY_USERNAME = "SELECT * FROM users WHERE userName=?";
    private static final String FIND_ALL = "SELECT * FROM users ORDER BY id";


    public static UserJDBCRepositoryImpl INSTANCE;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserJDBCRepositoryImpl.class);

    private UserJDBCRepositoryImpl() {
    }

    public static UserJDBCRepositoryImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserJDBCRepositoryImpl();
        }

        return INSTANCE;
    }


    @Override
    public void saveUser(User user) {
        try (Connection connection = DataSourceProvider.getMysqlConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO users(firstName, lastName, userName) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getUserName());
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {

                if (generatedKeys.next())
                    user.setId(generatedKeys.getLong(1));
            }

        } catch (SQLException e) {
            log.error("Something bad happened during fetching user = {} ", user, e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {

        Optional<User> result;
        try (Connection connection = DataSourceProvider.getMysqlConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
             PreparedStatement ps2 = connection.prepareStatement("SELECT * FROM tasks WHERE user_id = ?")) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                final User user = new User(
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("userName"));
                result = Optional.of(user);

                ps2.setLong(1, id);

                try (ResultSet r2 = ps2.executeQuery()) {
                    while (r2.next()) {
                        Task task = new Task(r2.getString("title"),
                                r2.getString("description"));
                        task.setId(r2.getLong("id"));
                        user.addTask(task);
                    }
                }
                return result;
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByUsername(String username) {

        User result;
        try (Connection connection = DataSourceProvider.getMysqlConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_USERNAME);
             PreparedStatement ps2 = connection.prepareStatement("SELECT * FROM tasks WHERE user_id = ?")) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                final User user = new User(
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("userName"));
                result = user;
                ps2.setLong(1, user.getId());

                try (ResultSet r2 = ps2.executeQuery()) {
                    while (r2.next()) {
                        Task task = new Task(r2.getString("title"),
                                r2.getString("description"));
                        task.setId(r2.getLong("id"));
                        user.addTask(task);
                    }
                }
                return result;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getMysqlConnection();
             PreparedStatement ps1 = connection.prepareStatement(FIND_ALL);
             PreparedStatement ps2 = connection.prepareStatement("SELECT * FROM tasks WHERE user_id = ?")) {

            try (ResultSet r = ps1.executeQuery()) {
                while (r.next()) {
                    User user = new User(
                            r.getString("firstName"),
                            r.getString("lastName"),
                            r.getString("userName"));
                    users.add(user);
                }
            }

            for (User user : users) {
                ps2.setLong(1, user.getId());
                try (ResultSet r2 = ps2.executeQuery()) {
                    while (r2.next()) {
                        Task task = new Task(r2.getString("title"),
                                r2.getString("description"));
                        task.setId(r2.getLong("id"));
                        user.addTask(task);
                    }

                }
            }

        } catch (SQLException e) {
            log.error("Something bad happened during fetching users = {} ", users, e);
        }
        return users;
    }

    @Override
    public void update(User user) {
    }

    @Override
    public void deleteUserById(Long id) {
        try (Connection connection = DataSourceProvider.getMysqlConnection();
             PreparedStatement ps1 = connection.prepareStatement(DELETE_USER)) {

            ps1.setLong(1, id);
            ps1.executeUpdate();

        } catch (SQLException e) {
            log.error("Something bad happened during fetching a user with userName = {} ", id, e);
        }
    }

    @Override
    public Stream<User> streamAll() {
        return null;
    }

}
