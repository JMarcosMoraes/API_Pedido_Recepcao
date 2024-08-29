package com.kata.pedidos.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kata.pedidos.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

	Optional<Pedido> findByNumeroControle(Integer numeroControle);

	List<Pedido> findByDataCadastro(LocalDate dataCadastro);
	
}
