package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de gestionar la conexión a la base de datos.
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/tienda";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    /**
     * Método que obtiene una conexión a la base de datos.
     *
     * @return Connection conexión a la base de datos tienda
     */
    public static Connection getConnection(){

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) { throw new RuntimeException("Error al conectar con la base de datos: " + e.getMessage()); }
    }
}