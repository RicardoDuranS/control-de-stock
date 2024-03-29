package com.tienda.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

    private DataSource dataSource;

    public ConnectionFactory() {
        var pooledDataSource = new ComboPooledDataSource();
        pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/control_de_stock");
        pooledDataSource.setUser("root");
        pooledDataSource.setPassword("12345678");
        pooledDataSource.setMaxPoolSize(10); // Tamaño máximo del pool
        this.dataSource = pooledDataSource;

    }

    public Connection recuperaConexion() {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
