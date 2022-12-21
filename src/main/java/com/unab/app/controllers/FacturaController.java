package com.unab.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unab.app.interfaces.IFacturaService;
import com.unab.app.models.Factura;

@RestController
@RequestMapping(value="/factura")
public class FacturaController {
	@Autowired
	private IFacturaService facturaService;
	
	@PostMapping("/save")
	public Factura save(@RequestBody Factura factura) {
		facturaService.save(factura);
		return factura;
	}
	
	@GetMapping("/listar/{id}")
	public Factura findFacturById(@PathVariable("id") Long id) {
		return facturaService.findFacturById(id);
	}
	
	@GetMapping("/delete/{id}")
	public void deleteFactura(@PathVariable("id") Long id) {
		facturaService.deleteFactura(id);
	}
	
	@GetMapping("/listwithClient/{id}")
	public List<Factura> fetchFacturaByIDCliente(@PathVariable("id") Long id){
		return facturaService.fetchFacturaByIDCliente(id);
	}
	
	@GetMapping("/update/{idFactura}/{idProducto}")
	public void updateFactura(@PathVariable("idFactura") Long idFactura, @PathVariable("idProducto") Long idProducto) {
		facturaService.updateFactura(idFactura, idProducto);
	}
	
}
