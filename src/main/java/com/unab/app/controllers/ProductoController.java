package com.unab.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unab.app.interfaces.IProductoService;
import com.unab.app.models.Producto;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/producto")
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class ProductoController {
	@Autowired
	private IProductoService productoService;

	@GetMapping("/findByNombre/{nombre}")
	public List<Producto> findByNombre(@PathVariable("nombre") String nombre) {
		return productoService.findByNombre(nombre);
	}
	
	@GetMapping("/findById/{id}")
	public Producto findProductoById(@PathVariable("id") Long id) {
		System.out.println("Test Productos");
		return productoService.findProductoById(id);
	}
	
	@PostMapping("/save")
	public Producto findProductoById(@RequestBody Producto producto) {
		productoService.save(producto);
		return producto;
	}
	
	@GetMapping("/listar")
	public Page<Producto> findAll() {
		Pageable pageable=PageRequest.of(0, 5);
		return productoService.findAll(pageable);
	}
}
