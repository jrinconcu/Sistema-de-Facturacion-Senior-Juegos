package com.unab.app.interfaces;

import java.util.List;

import com.unab.app.models.DetalleFactura;

public interface IDetalleFacturaService {
	
	public void save(DetalleFactura detalleFactura);
	
	public List<DetalleFactura> findDetalleFacturaByIdFacturaByIdProducto(Long id_factura, Long id_producto);
}
