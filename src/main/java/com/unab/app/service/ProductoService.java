package com.unab.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.unab.app.dao.IProductoDAO;
import com.unab.app.interfaces.IProductoService;
import com.unab.app.models.Producto;
@Service
public class ProductoService implements IProductoService{

	@Autowired
	private IProductoDAO productoDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> findByNombre(String nombre) {
		try {
			return (List<Producto>) productoDao.findByNombre(nombre);
		}catch(Exception e) {
			return new ArrayList<Producto>();
		}
	}

	@Override
	public Producto findProductoById(Long id) {
		return productoDao.findById(id).orElse(new Producto());
	}

	@Override
	public void save(Producto producto) {
		productoDao.save(producto);
	}

	@Override
	public Page<Producto> findAll(Pageable pageable) {
		return productoDao.findAll(pageable);
	}

}
