package com.stefanini.taskmanager.repository;

import com.stefanini.taskmanager.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserJDBCRepositoryImpl implements UserRepository {

    private final String URL = "jdbc:mysql://localhost:3306/";
    private final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private final String DATABASE = "taskmanager";
    private final String USERNAME = "root";
    private final String PASSWORD = "mysqleight";

    private static final String DELETE = "DELETE FROM users WHERE id=?";
    private static final String FIND_BY_USERNAME = "SELECT * FROM users WHERE userName=?";
    private static final String FIND_ALL = "SELECT * FROM users ORDER BY id";
    private static final String UPDATE = "UPDATE users SET firstName=?, lastName=?, userName=? WHERE id=?";
    private static final String SAVE = "INSERT INTO users(firstName, lastName, userName) VALUES (?, ?, ?)";

    public static UserJDBCRepositoryImpl INSTANCE;

    private UserJDBCRepositoryImpl() {
    }

    public static UserJDBCRepositoryImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserJDBCRepositoryImpl();
        }

        return INSTANCE;
    }

    @Override
    public int saveUser(User user) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection(URL + DATABASE, USERNAME, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getUserName());
            result = ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {

                if (generatedKeys.next())
                    user.setId(generatedKeys.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(URL + DATABASE, USERNAME, PASSWORD);
            PreparedStatement ps = connection.prepareStatement(FIND_ALL)) {

            try(ResultSet r = ps.executeQuery()) {
                while (r.next()) {
                    User user = new User(r.getLong("id"),
                                        r.getString("firstName"),
                                        r.getString("lastName"),
                                        r.getString("userName"));
                    users.add(user);
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void update(User user) {

    }


}
