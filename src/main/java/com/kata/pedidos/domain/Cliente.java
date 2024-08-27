package com.kata.pedidos.domain;

import jakarta.persistence.Entity;

@Entity
public class Cliente extends Pessoa {

	public Cliente() {
		super();
	}

	public Cliente(Integer id, String nome, String cpf, String email) {
		super(id, nome, cpf, email);
	}

	
	
}
