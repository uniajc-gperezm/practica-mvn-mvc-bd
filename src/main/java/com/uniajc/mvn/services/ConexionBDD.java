package com.uniajc.mvn.services;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.IOException;
import javax.swing.JOptionPane; 

public class ConexionBDD {
    
    private static Connection connection = null;

    public static Connection getConnection() {
        Connection conn = null; 

        try {
            if (connection == null || connection.isClosed()) {
                
                Properties properties = new Properties();
                
             
                properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")); 

                Class.forName("org.postgresql.Driver");

                String url = properties.getProperty("URL"); 
                String username = properties.getProperty("USERNAME");
                String password = properties.getProperty("PASSWORD");
                
                
                conn = DriverManager.getConnection(url, username, password);
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el driver JDBC.", "Error de Driver", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Driver no encontrado", e); 
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, 
                "Error al conectar a la base de datos:\n" + e.getMessage() + "\nVerifique la URL y credenciales de NeonDB.", 
                "Error de SQL (Conexión)", 
                JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Fallo de conexión", e);
        } catch (IOException e) {
             JOptionPane.showMessageDialog(null, "Error al leer config.properties.", "Error de Archivo (IO)", JOptionPane.ERROR_MESSAGE);
             throw new RuntimeException("Fallo al leer config.properties", e);
        }

        if (conn != null) {
            connection = conn;
        }
        return connection;
    }
}