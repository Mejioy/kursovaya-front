package ru.kafpin.lr6_bzz.utils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Collection;
import java.util.Properties;

public class DBHelper {
    private static Connection connection;

    public static Connection getConnection() throws SQLException{
        if(connection!=null){
            return connection;
        }
        URL url = DBHelper
                .class
                .getResource("/ru/kafpin/lr6_bzz/config.properties");
        Properties property = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(url.getFile());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            property.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String dbUrl = property.getProperty("db.url");
        String user = property.getProperty("db.user");
        String pass = property.getProperty("db.password");
        connection =  DriverManager.getConnection(dbUrl, user, pass);
        return connection;
    }
}
