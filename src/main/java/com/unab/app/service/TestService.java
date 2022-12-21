package com.unab.app.service;

import org.springframework.stereotype.Service;

import com.unab.app.interfaces.IServicio;
import com.unab.app.models.Usuario;

@Service("TestService")
public class TestService implements IServicio {
	@Override
	public Usuario operacion(Usuario usuario) {
		System.out.println("Prueba Servicio");
		System.out.println(usuario);
		System.out.println(usuario.getUsername());
		System.out.println(usuario.getPassword());
		return usuario;
	}
}
