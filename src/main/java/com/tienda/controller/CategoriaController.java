package com.tienda.controller;

import java.util.ArrayList;
import java.util.List;

import com.tienda.factory.ConnectionFactory;
import com.tienda.modelo.Categoria;
import com.tienda.dao.CategoriaDAO;

public class CategoriaController {

    private CategoriaDAO categoriaDAO;

    public CategoriaController() {
        var factory = new ConnectionFactory();
        this.categoriaDAO = new CategoriaDAO(factory.recuperaConexion());
    }

    public List<Categoria> listar() {
        return categoriaDAO.listar();
    }

    public List<Categoria> cargaReporte() {
        return this.categoriaDAO.listarConProductos();
    }

}
