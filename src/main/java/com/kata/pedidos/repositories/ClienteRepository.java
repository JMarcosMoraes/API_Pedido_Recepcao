package com.kata.pedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kata.pedidos.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	
	
}
