package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;


    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Properties dbProperty = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream("src/main/java/configurations/config");
            dbProperty.load(fis);
            String dbUrl = dbProperty.getProperty("db_url");
            String dbLogin = dbProperty.getProperty("db_login");
            String dbPassword = dbProperty.getProperty("db_password");
            connection = DriverManager.getConnection(dbUrl, dbLogin, dbPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDB() throws SQLException {
        try {
            this.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS customers(id int primary key ,telegram_id text, first_name text, last_name text, phone text)");
    }
}
