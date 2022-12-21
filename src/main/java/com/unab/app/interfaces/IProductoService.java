package com.unab.app.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.unab.app.models.Producto;

public interface IProductoService {
	public List<Producto> findByNombre(String nombre);
	
	public Producto findProductoById(Long id);
	
	public void save(Producto producto);
	
	public Page<Producto> findAll(Pageable pageable);
}
