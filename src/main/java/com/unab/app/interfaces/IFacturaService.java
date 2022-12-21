package com.unab.app.interfaces;

import java.util.List;

import com.unab.app.models.Factura;

public interface IFacturaService {
	
	public void save(Factura factura);
	
	public Factura findFacturById(Long id);
	
	public void deleteFactura(Long id);
	
	public List<Factura> fetchFacturaByIDCliente(Long id);
	
	public void updateFactura(Long idFactura, Long idProducto);
}
