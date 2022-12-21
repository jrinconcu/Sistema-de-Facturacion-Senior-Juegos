package com.unab.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unab.app.interfaces.IServicio;
import com.unab.app.models.Usuario;

@RestController
@RequestMapping(value="/test")
public class IndexController {
	
	@Autowired
	@Qualifier("TestService")
	private IServicio testService1;
	
	@Autowired
	@Qualifier("HomeService")
	private IServicio testService2;
	
	@Value("${text.indexController.test}")
	private String test;
	
	@Value("${text.mysql}")
	private String mysql;
	
	private static final String USERNAME="Jhonnyer"; 
	
	@PostMapping("/testService1")
	public Usuario getPrueba1(@RequestBody Usuario usuario) {
		return testService1.operacion(usuario);
	}
	
	
	@PostMapping("/testService2")
	public Usuario getPrueba2(@RequestBody Usuario usuario) {
		return testService2.operacion(usuario);
	}
	
	@GetMapping("/pruebaget/{nombre}")
	public String getPrueba3(@PathVariable("nombre") String nombre) {
		System.out.println("Hola "+nombre+",  bienvenido");
		return "Hola "+nombre+",  bienvenido";
	}
	
	@PostMapping("/requestBodypost")
	public Object getPrueba4(@RequestBody Object object) {
		System.out.println(object);
		return object;
	}
	
	@PutMapping("/requestBodyput")
	public Object getPrueba5(@RequestBody Object object) {
		System.out.println("Variable estatica "+USERNAME);
		System.out.println("Variable obtenida desde textos.properties: "+test);
		System.out.println("Test conflicto ramas github");
		System.out.println("test ramas de git");
		System.out.println("Conexión a la base de datos de: "+mysql);
		System.out.println(object);
		return object;
	}
	
}
