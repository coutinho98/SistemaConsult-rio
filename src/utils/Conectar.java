package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus Couto
 */
public class Conectar {

    private static final String user = "root";
    private static final String password = "";
    private static final String url = "jdbc:mysql://localhost/consultorio2";

    public static Connection getConectar() {
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Failed to connect to database " + ex.getMessage());
        }
        return connect;
    }
}
