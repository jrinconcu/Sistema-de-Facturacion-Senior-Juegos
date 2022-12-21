package com.unab.app.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.unab.app.models.Cliente;

public interface IClienteService {
	public List<Cliente> findAll();

	public Page<Cliente> findAll(Pageable pageable);

	public ResponseEntity<?> save(Cliente cliente);
	
	public ResponseEntity<?> update(Long id, Cliente cliente);

	public ResponseEntity<?> findOne(Long id);

	public ResponseEntity<?> delete(Long id);
}
