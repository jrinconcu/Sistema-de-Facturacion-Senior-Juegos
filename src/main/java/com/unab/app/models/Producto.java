package com.unab.app.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "producto")
public class Producto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto")
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "precio")
	private Long precio;

	@Column(name = "create_at")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String createAt;

	public Producto() {
	}

	public Producto(Long id, String nombre, Long precio, String createAt) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.createAt = createAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getPrecio() {
		return precio;
	}

	public void setPrecio(Long precio) {
		this.precio = precio;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

}
