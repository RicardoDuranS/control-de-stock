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
		final Connection con = new ConectionFactory().recuperarConexion();
		try (con) {
			final PreparedStatement statement = con.prepareStatement("UPDATE PRODUCTO SET"
					+ "NOMBRE = ?, "
					+ "DESCRIPCION = ?,"
					+ "CANTIDAD = ?,"
					+ "WHERE ID = ?");
			try (statement) {
				statement.setString(1, nombre);
				statement.setString(2, descripcion);
				statement.setInt(3, cantidad);
				statement.setInt(4, id);

				statement.execute("UPDATE PRODUCTO SET "
						+ " NOMBRE = '" + nombre + "'"
						+ ", DESCRIPCION = '" + descripcion + "'"
						+ ", CANTIDAD = '" + cantidad + "'"
						+ " WHERE ID = " + id);
			}
		}
	}

	public int eliminar(Integer id) throws SQLException {
		final Connection con = new ConectionFactory().recuperarConexion();
		try (con) {
			final PreparedStatement statement = con
					.prepareStatement("DELETE FROM PRODUCTO WHERE ID = ?");
			try (statement) {
				statement.setInt(1, id);
				statement.execute();

				int updateCount = statement.getUpdateCount();
				// un statemente execute
				return updateCount;
			}
		}
		// getUpdateCount() nos devuelve cuantas filas fueron modificadas luego de hacer
	}

	public List<Map<String, String>> listar() throws SQLException {
		final Connection con = new ConectionFactory().recuperarConexion();

		try (con) {
			final PreparedStatement statement = con
					.prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
			try (statement) {
				statement.execute();

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
				return resultado;

			}
		}

	}

	public void guardar(Map<String, String> producto) throws SQLException {
		String nombre = producto.get("NOMBRE");
		String descripcion = producto.get("DESCRIPCION");
		Integer cantidad = Integer.valueOf(producto.get("CANTIDAD"));
		Integer maximoCantidad = 50;

		final Connection con = new ConectionFactory().recuperarConexion();
		con.setAutoCommit(false);

		try (con) {
			PreparedStatement statement = con.prepareStatement("INSERT INTO PRODUCTO "
					+ "(nombre, descripcion, cantidad)"
					+ " VALUES (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				do {
					int cantidadParaGuardar = Math.min(cantidad, maximoCantidad);
					ejecutaRegistro(nombre, descripcion, cantidadParaGuardar, statement);
					cantidad -= maximoCantidad;
				} while (cantidad > 0);
				con.commit();
			} catch (Exception e) {
				con.rollback();
			}
		}

	}

	private void ejecutaRegistro(String nombre, String descripcion, Integer cantidad, PreparedStatement statement)
			throws SQLException {

		statement.setString(1, nombre);
		statement.setString(2, descripcion);
		statement.setInt(3, cantidad);
		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();
		try (resultSet) {
			while (resultSet.next()) {
				System.out.println(String.format("Fue insertado el producto de ID %d", resultSet.getInt(1)));
			}
		}
	}

}
