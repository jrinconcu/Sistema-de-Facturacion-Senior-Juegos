package com.unab.app.service;

import org.springframework.stereotype.Service;

import com.unab.app.interfaces.IServicio;
import com.unab.app.models.Usuario;
@Service("HomeService")
//@Primary
public class HomeService implements IServicio{

	@Override
	public Usuario operacion(Usuario usuario) {	
		usuario.setUsername(usuario.getUsername()+" Fernando Galindez");
		return usuario;
	}

}
