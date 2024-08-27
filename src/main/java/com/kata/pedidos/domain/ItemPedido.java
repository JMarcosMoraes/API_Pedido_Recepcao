package com.kata.pedidos.domain;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer numControle;
	private Date dataCadastro;
	private String nome;
	private BigDecimal valor;
	private Integer quantidade;
	private Cliente cliente;
	
	
	
	
}
