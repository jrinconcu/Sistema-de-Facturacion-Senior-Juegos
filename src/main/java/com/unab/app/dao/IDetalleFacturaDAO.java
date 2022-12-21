package com.unab.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unab.app.models.DetalleFactura;
@Repository
public interface IDetalleFacturaDAO extends CrudRepository<DetalleFactura, Long>{
	
	@Query("select d from DetalleFactura d where d.factura.id= ?1 and d.producto.id= ?2")
	public List<DetalleFactura> findDetalleFacturaByIdFacturaByIdProducto(Long id_factura, Long id_producto);
}
