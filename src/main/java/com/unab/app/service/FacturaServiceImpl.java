package com.unab.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unab.app.dao.FacturaDAO;
import com.unab.app.interfaces.IDetalleFacturaService;
import com.unab.app.interfaces.IFacturaService;
import com.unab.app.interfaces.IProductoService;
import com.unab.app.models.DetalleFactura;
import com.unab.app.models.Factura;
import com.unab.app.models.Producto;

@Service
public class FacturaServiceImpl implements IFacturaService{

	@Autowired
	private FacturaDAO facturaDao;
	
	@Autowired
	private IProductoService productoDao;
	
	@Autowired
	private IDetalleFacturaService detalleFacturaService;
	
	@Override
	public void save(Factura factura) {
		facturaDao.save(factura);
	}

	@Override
	public Factura findFacturById(Long id) {
		return facturaDao.findById(id).orElse(new Factura());
	}

	@Override
	public void deleteFactura(Long id) {
		facturaDao.deleteById(id);
	}

	@Override
	public List<Factura> fetchFacturaByIDCliente(Long id) {
		return facturaDao.fetchFacturaByIDCliente(id);
	}

	@Override
	public void updateFactura(Long idFactura, Long idProducto) {
		Producto producto=productoDao.findProductoById(idProducto);
		System.out.println("Producto a actualizar: "+producto.getNombre());
		
		List<DetalleFactura> listDetalleFactura=detalleFacturaService.findDetalleFacturaByIdFacturaByIdProducto(idFactura, idProducto);
		
		Factura factura=this.findFacturById(idFactura);
	
		listDetalleFactura.forEach(t->{
			t.setCantidad(10);
			Integer cantidad=t.getCantidad();
			Long precio=t.getProducto().getPrecio();
			Long newValor=cantidad*precio;
			factura.setValorTotal(newValor);
			facturaDao.save(factura);
		});
		
		//Utilizacion Bucle For
		
		for(int x=0; x<listDetalleFactura.size();x++) {
			System.out.println("Lista con for tradicional: "+listDetalleFactura.get(x));
		}
		
		for(DetalleFactura d: listDetalleFactura) {
			System.out.println("Lista for 1: "+d);
		}
		
	}

}
