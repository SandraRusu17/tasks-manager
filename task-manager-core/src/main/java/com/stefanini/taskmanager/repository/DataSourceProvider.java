package com.stefanini.taskmanager.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceProvider {

    private final String URL = "jdbc:mysql://localhost:3306/";
    private final String DATABASE = "taskmanager";
    private final String USERNAME = "root";
    private final String PASSWORD = "mysqleight";

    public Connection getMysqlConnection() throws SQLException {
        return DriverManager.getConnection(URL + DATABASE, USERNAME, PASSWORD);
    }
}
