package com.tienda.tests;

import java.sql.Connection;
import java.sql.SQLException;

import com.tienda.factory.ConectionFactory;

public class PruebaConexion {
    public static void main(String[] args) throws SQLException {
        Connection con = new ConectionFactory().recuperarConexion();
        con.close();
    }
}
