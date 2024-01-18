package com.tienda.jdbc.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

public class ProductoController {

	public void modificar(String nombre, String descripcion, Integer id) {
		// TODO
	}

	public void eliminar(Integer id) {
		// TODO
	}

	public List<Map<String, String>> listar() throws SQLException {
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost/control_de_stock",
				"root",
				"12345678");

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

	public void guardar(Object producto) {
		// TODO
	}

}
