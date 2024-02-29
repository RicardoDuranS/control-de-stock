package com.tienda.tests;

import java.sql.Connection;
import java.sql.SQLException;

import com.tienda.factory.ConnectionFactory;

public class PruebaPoolDeConexiones {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory con = new ConnectionFactory();

        for (int i = 0; i < 20; i++) {
            Connection conexion = con.recuperaConexion();
            System.out.println("Abriendo la conexion" + (i + 1));
        }
    }
}
