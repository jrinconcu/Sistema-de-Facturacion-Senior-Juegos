package com.unab.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.unab.app.models.Producto;

@Repository
public interface IProductoDAO extends PagingAndSortingRepository<Producto, Long> {
	
	@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> findByNombre(String nombre);
	
}
