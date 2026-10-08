package archivo.municipal.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/archivo_municipal";
    private static final String USUARIO = "root";
    private static final String PASSWORD = ""; // Cambia por la contraseña de tu MySQL si la tienes

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}