package com.unab.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unab.app.interfaces.IDetalleFacturaService;
import com.unab.app.models.DetalleFactura;

@RestController
@RequestMapping(value = "/detalleFactura")
public class DetalleFacturaController {
	@Autowired
	private IDetalleFacturaService detalleFacturaService;

	@PostMapping("/save")
	public DetalleFactura save(@RequestBody DetalleFactura detalleFactura) {
		detalleFacturaService.save(detalleFactura);
		return detalleFactura;
	}

	@GetMapping("/listDetail/{idFactura}/{idProducto}")
	public List<DetalleFactura> findDetalleFacturaByIdFacturaByIdProducto(@PathVariable("idFactura") Long idFactura,
			@PathVariable("idProducto") Long idProducto) {
		return detalleFacturaService.findDetalleFacturaByIdFacturaByIdProducto(idFactura, idProducto);
	}

}
