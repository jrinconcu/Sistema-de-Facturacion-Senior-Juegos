package com.unab.app.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "factura")
public class Factura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_factura")
	private Long id;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "observacion")
	private String observacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "create_at")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;

	@Column(name = "valor_total")
	private Long valorTotal;

	@ManyToOne()
	@JoinColumn(name = "cliente_id_cliente", referencedColumnName = "id_cliente")
	private Cliente cliente;

	public Factura() {
	}

	public Factura(Long id, String descripcion, String observacion, Date createAt, Long valorTotal, Cliente cliente) {
		this.id = id;
		this.descripcion = descripcion;
		this.observacion = observacion;
		this.createAt = createAt;
		this.valorTotal = valorTotal;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Long getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Long valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
