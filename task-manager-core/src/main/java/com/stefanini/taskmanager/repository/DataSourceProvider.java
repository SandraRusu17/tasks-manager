package com.stefanini.taskmanager.repository;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceProvider {
    private static String URL = "jdbc:mysql://localhost:3306/";
    private static String DATABASE = "taskmanager";
    private static String USERNAME = "root";
    private static String PASSWORD = "mysqleight";

    public static Connection getMysqlConnection() throws SQLException {
        return DriverManager.getConnection(URL + DATABASE, USERNAME, PASSWORD);
    }
}
