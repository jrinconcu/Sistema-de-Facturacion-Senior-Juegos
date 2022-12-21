package com.unab.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unab.app.models.Usuario;

@Repository
public interface IUsuarioDAO extends CrudRepository<Usuario, Long>{
	public Usuario findByUsername(String username);
}
