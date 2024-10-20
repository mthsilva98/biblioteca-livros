package oi.github.mthsilva98.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String url = "jdbc:mysql://localhost:3306/biblioteca_livros";
    private static final String user = "root";
    private static final String password = "123456Ma";

    private static Connection conn;

    public static Connection getConexao() {

        try {
            if(conn == null) {
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            }else {
                return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}