package com.tienda.tests;

import java.sql.Connection;
import java.sql.SQLException;

import com.tienda.factory.ConnectionFactory;

public class PruebaConexion {
    public static void main(String[] args) throws SQLException {
        Connection con = new ConnectionFactory().recuperarConexion();
        con.close();
    }
}
