package com.stefanini.taskmanager.repository;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceProvider {
    private static String URL, DATABASE, USERNAME, PASSWORD;

    private static void setConnectionParams() throws IOException {
        Properties properties = new Properties();
        FileInputStream ip = new FileInputStream("application.properties");
        properties.load(ip);

        URL = properties.getProperty("db.url");
        DATABASE = properties.getProperty("db.database");
        USERNAME = properties.getProperty("db.username");
        PASSWORD = properties.getProperty("db.password");
    }

    public static Connection getMysqlConnection() throws SQLException {
        return DriverManager.getConnection(URL + DATABASE, USERNAME, PASSWORD);
    }
}
