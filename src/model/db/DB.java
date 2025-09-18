package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DB {
    private static Connection conn = null;
    private static String url = "jdbc:sqlite:contactD.db";

    public static Connection getConnection(){
        if (conn == null){
            try {
                conn = DriverManager.getConnection(url);
                createTableIfNotExists();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void closeConnection(){
        try {
            if (conn != null){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void createTableIfNotExists() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS contact (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "email TEXT NOT NULL," +
                    "phone TEXT NOT NULL" +
                    ")";
            conn.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
