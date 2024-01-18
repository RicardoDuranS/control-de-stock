package com.tienda.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionFactory {
    public Connection recuperarConnexion() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost/control_de_stock",
                "root",
                "12345678");
    }
}
