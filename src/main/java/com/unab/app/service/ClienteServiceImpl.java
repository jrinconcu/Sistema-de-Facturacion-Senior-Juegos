package com.unab.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.unab.app.dao.IClienteDAO;
import com.unab.app.interfaces.IClienteService;
import com.unab.app.models.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDAO clienteDao;

	@Override
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	@Override
	public ResponseEntity<?> save(Cliente cliente) {
		Map<String, Object> response = new HashMap<>();
		try {
			Cliente clienteNew = clienteDao.save(cliente);
			response.put("mensaje", "El cliente " + clienteNew.getNombre() + " ha sido creado con éxito");
			response.put("cliente", clienteNew);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage() + ": " + e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> update(Long id, Cliente cliente) {
		Map<String, Object> response = new HashMap<>();
		try {
			System.out.println(cliente.getNombre());
			Cliente clienteActual = clienteDao.findById(id).orElse(new Cliente());
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEmail(cliente.getEmail());

			Cliente clienteNew = clienteDao.save(clienteActual);
			response.put("mensaje", "El cliente " + clienteNew.getNombre() + " ha sido actualizado con éxito");
			response.put("cliente", clienteNew);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage() + ": " + e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Cliente cliente = clienteDao.findById(id).orElse(new Cliente());
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		} catch (Exception e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage() + ": " + e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Cliente cliente = clienteDao.findById(id).orElse(new Cliente());
			clienteDao.deleteById(id);
			response.put("mensaje", "El cliente " + cliente.getNombre() + " fue eliminado con éxito");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("mensaje", "El registro no pudo ser eliminado, porque no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

	}

}
