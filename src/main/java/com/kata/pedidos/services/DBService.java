package com.kata.pedidos.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kata.pedidos.domain.Cliente;
import com.kata.pedidos.domain.Pedido;
import com.kata.pedidos.domain.Produtos;
import com.kata.pedidos.repositories.ClienteRepository;
import com.kata.pedidos.repositories.PedidoRepository;

@Service
public class DBService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ClienteRepository clienteRepository;

	public void instanciaDB() {
		Cliente cliente_01 = new Cliente(1, "José Marcos", "CPF", "email01@mail.com");
		Cliente cliente_02 = new Cliente(2, "Luciano", "CPF", "email01@mail.com");
		Cliente cliente_03 = new Cliente(3, "Matteo", "CPF", "email01@mail.com");
		Cliente cliente_04 = new Cliente(4, "Marcelo", "CPF", "email01@mail.com");
		Cliente cliente_05 = new Cliente(5, "Fernanda", "CPF", "email01@mail.com");
		Cliente cliente_06 = new Cliente(6, "Manuela", "CPF", "email01@mail.com");
		Cliente cliente_07 = new Cliente(7, "Neuza Maria", "CPF", "email01@mail.com");
		Cliente cliente_08 = new Cliente(8, "Adriana", "CPF", "email01@mail.com");
		Cliente cliente_09 = new Cliente(9, "Valeria", "CPF", "email01@mail.com");
		Cliente cliente_10 = new Cliente(10,"Andrea", "CPF", "email01@mail.com");
		
		clienteRepository.saveAll(Arrays.asList(cliente_01,cliente_02,cliente_03,cliente_04,cliente_05,cliente_06,cliente_07,cliente_08,cliente_09,cliente_10));
		
		
		Pedido pedido1 = new Pedido(1001, LocalDate.now(), Produtos.X_SALADA.getNome(), new BigDecimal(30.00), 2, cliente_01);
		Pedido pedido2 = new Pedido(1002, LocalDate.now(), Produtos.PIZZA_CALABRESA.getNome(), new BigDecimal(30.00), 2, cliente_01);
		Pedido pedido3 = new Pedido(1003, LocalDate.now(), Produtos.X_BACON.getNome(), new BigDecimal(30.00), 2, cliente_01);
		Pedido pedido4 = new Pedido(1004, LocalDate.now(), Produtos.CX_SKOL.getNome(), new BigDecimal(20.00), 5, cliente_01);
		Pedido pedido5 = new Pedido(1005, LocalDate.now(), Produtos.X_SALADA.getNome(), new BigDecimal(20.00), 10, cliente_02);
		Pedido pedido6 = new Pedido(1006, LocalDate.now(), Produtos.X_SALADA.getNome(), new BigDecimal(20.00), 100, cliente_02);
		Pedido pedido7 = new Pedido(1007, LocalDate.now(), Produtos.X_SALADA.getNome(), new BigDecimal(20.00), 1000, cliente_02);
		Pedido pedido8 = new Pedido(1008, LocalDate.now(), Produtos.X_SALADA.getNome(), new BigDecimal(20.00), 4, cliente_03);
		
		//pedidoRepository.saveAll(Arrays.asList(pedido1,pedido2,pedido3,pedido4,pedido5,pedido6,pedido7,pedido8));
		
		pedidoService.fazerPedido(Arrays.asList(pedido1,pedido2,pedido3,pedido4,pedido5,pedido6,pedido7,pedido8));
	}

}
