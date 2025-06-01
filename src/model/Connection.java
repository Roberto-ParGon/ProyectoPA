package model;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

    private static Connection instance = null;

    private java.sql.Connection con;

    private final String URL = "jdbc:mysql://localhost:3306/banco_db";
    private final String USER = "root";
    private final String PASS = "root";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private Connection() {
        try {
            Class.forName(DRIVER);
            this.con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión a la base de datos realizada");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    public java.sql.Connection getDatabaseConnection() {
        return this.con;
    }

    public static void closeConnection() {
        try {
            if (instance != null && instance.getDatabaseConnection() != null) {
                instance.getDatabaseConnection().close();
                System.out.println("Conexión cerrada");
            }
            instance = null;
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }
}