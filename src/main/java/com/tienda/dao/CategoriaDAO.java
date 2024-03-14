package com.tienda.dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tienda.modelo.Categoria;

public class CategoriaDAO {
    private Connection con;

    public CategoriaDAO(Connection recuperaConexion) {
        this.con = recuperaConexion;
    }

    public List<Categoria> listar() {
        List<Categoria> resultado = new ArrayList<>();
        try {
            final PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE FROM CATEGORIA");

            try (statement) {
                final ResultSet resultSet = statement.executeQuery();
                try (resultSet) {
                    while (resultSet.next()) {
                        var categoria = new Categoria(resultSet.getInt("ID"), resultSet.getString("NOMBRE"));
                        resultado.add(categoria);
                    }
                }
                ;
            }

            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public List<Categoria> listarConProductos() {
        List<Categoria> resultado = new ArrayList<>();
        try {
            final PreparedStatement statement = con.prepareStatement("SELECT C.ID, C.NOMBRE, P.ID, P.NOMBRE, P.CANTIDAD"
                    + " FROM CATEGORIA C"
                    + " INNER JOIN PRODUCTO P ON C.ID = P.CATEGORIA_ID ");

            try (statement) {
                final ResultSet resultSet = statement.executeQuery();
                try (resultSet) {
                    while (resultSet.next()) {
                        Integer categoriaId = resultSet.getInt("ID");
                        String categoriaNombre = resultSet.getString("NOMBRE");

                        var categoria = resultado.stream()
                                .filter(cat -> cat.getId().equals(categoriaId))
                                .findAny().orElseGet(() -> {
                                    Categoria cat = new Categoria(categoriaId, categoriaNombre);
                                    resultado.add(cat);
                                    return cat;
                                });
                    }
                }
                ;
            }

            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
