package com.tienda.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionFactory {
    public Connection recuperarConexion() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost/control_de_stock",
                "root",
                "12345678");
    }
}
