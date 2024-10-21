package oi.github.mthsilva98.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String url = "jdbc:mysql://localhost:3306/biblioteca_livros";
    private static final String user = "root";
    private static final String password = "123456Ma";

    // Método para obter uma nova conexão sempre que for chamado
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
