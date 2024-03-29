package com.tienda.controller;

import java.util.List;

import com.tienda.dao.ProductoDAO;
import com.tienda.factory.ConnectionFactory;
import com.tienda.modelo.Categoria;
import com.tienda.modelo.Producto;

public class ProductoController {

	private ProductoDAO productoDao;

	public ProductoController() {
		var factory = new ConnectionFactory();
		this.productoDao = new ProductoDAO(factory.recuperaConexion());
	}

	public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) {
		return productoDao.modificar(nombre, descripcion, cantidad, id);
	}

	public int eliminar(Integer id) {
		return productoDao.eliminar(id);
	}

	public List<Producto> listar() {
		return productoDao.listar();
	}

	public List<Producto> listar(Categoria categoria) {
		return productoDao.listar(categoria.getId());
	}

	public void guardar(Producto producto, Integer categoriaId) {
		producto.setCategoriaId(categoriaId);
		productoDao.guardar(producto);
	}

}
