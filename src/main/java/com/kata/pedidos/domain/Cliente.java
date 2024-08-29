package com.kata.pedidos.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Cliente extends Pessoa implements Serializable {
	
	private static final long serialVersionUID = 4151905320375212935L;
	
	@OneToMany
	private List<Pedido> listaPedido;
	
	public Cliente() {
		super();
	}

	public Cliente(Integer id, String nome, String cpf, String email) {
		super(id, nome, cpf, email);
	}

	public List<Pedido> getListaPedido() {
		return listaPedido;
	}

	public void setListaPedido(List<Pedido> listaPedido) {
		this.listaPedido = listaPedido;
	}

	
	
}
