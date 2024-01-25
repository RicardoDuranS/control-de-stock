package com.tienda.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import com.tienda.factory.ConectionFactory;

public class ProductoController {

	public void modificar(String nombre, String descripcion, Integer id, Integer cantidad) throws SQLException {
		Connection con = new ConectionFactory().recuperarConexion();

		Statement statement = con.createStatement();

		statement.execute("UPDATE PRODUCTO SET "
				+ " NOMBRE = '" + nombre + "'"
				+ ", DESCRIPCION = '" + descripcion + "'"
				+ ", CANTIDAD = '" + cantidad + "'"
				+ " WHERE ID = " + id);
	}

	public int eliminar(Integer id) throws SQLException {
		Connection con = new ConectionFactory().recuperarConexion();

		Statement statement = con.createStatement();

		statement.execute("DELETE FROM PRODUCTO WHERE ID = " + id);

		// getUpdateCount() nos devuelve cuantas filas fueron modificadas luego de hacer
		// un statemente execute
		return statement.getUpdateCount();
	}

	public List<Map<String, String>> listar() throws SQLException {
		Connection con = new ConectionFactory().recuperarConexion();

		Statement statement = con.createStatement();
		statement.execute("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");

		ResultSet resultSet = statement.getResultSet();
		List<Map<String, String>> resultado = new ArrayList<>();

		while (resultSet.next()) {
			Map<String, String> columna = new HashMap<>();

			columna.put("ID", String.valueOf(resultSet.getInt("ID")));
			columna.put("NOMBRE", resultSet.getString("NOMBRE"));
			columna.put("DESCRIPCION", resultSet.getString("DESCRIPCION"));
			columna.put("CANTIDAD", String.valueOf(resultSet.getInt("CANTIDAD")));

			resultado.add(columna);
		}

		con.close();

		return resultado;
	}

	public void guardar(Map<String, String> producto) throws SQLException {
		Connection con = new ConectionFactory().recuperarConexion();

		PreparedStatement statement = con.prepareStatement("INSERT INTO PRODUCTO "
				+ "(nombre, descripcion, cantidad)"
				+ " VALUES (?,?,?)",
				Statement.RETURN_GENERATED_KEYS);

		statement.setString(1, producto.get("NOMBRE"));
		statement.setString(2, producto.get("DESCRIPCION"));
		statement.setInt(3, Integer.valueOf(producto.get("CANTIDAD")));

		statement.execute();
		con.close();

		ResultSet resultSet = statement.getGeneratedKeys();

		while (resultSet.next()) {
			System.out.println(String.format("Fue insertado el producto de ID %d", resultSet.getInt(1)));
		}

	}

}
