package com.unab.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unab.app.interfaces.IClienteService;
import com.unab.app.models.Cliente;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
//@SessionAttributes("cliente")
@RequestMapping(value="/cliente")
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class ClienteController {
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/listar")
	public List<Cliente> getClientes(){
		return clienteService.findAll();
	}
	
	@GetMapping("/listarPageable")
	public Page<Cliente> findAll(){
		Pageable pageable=Pageable.ofSize(10);
		return clienteService.findAll(pageable);
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody Cliente cliente, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return "Error en el campo '" + err.getField() + "'. Mensaje de error: " + err.getDefaultMessage();
			}).collect(Collectors.toList());

			response.put("mensaje", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}else {
			return clienteService.save(cliente);
		}
	}
	
	@PutMapping("/save/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable("id") Long id) {
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return "Error en el campo '" + err.getField() + "'. Mensaje de error: " + err.getDefaultMessage();
			}).collect(Collectors.toList());

			response.put("mensaje", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}else {
			return clienteService.update(id, cliente);
		}
	}
	
	@GetMapping("/findOne/{id}")
	public ResponseEntity<?> findOne(@PathVariable("id") Long id) {
		return clienteService.findOne(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable("id") Long id){
		return clienteService.delete(id);
	}
}
