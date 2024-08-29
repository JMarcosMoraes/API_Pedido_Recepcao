package com.kata.pedidos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.kata.pedidos.domain.Cliente;
import com.kata.pedidos.domain.Pedido;
import com.kata.pedidos.domain.Produtos;
import com.kata.pedidos.repositories.PedidoRepository;
import com.kata.pedidos.services.ClienteService;
import com.kata.pedidos.services.PedidoService;
import com.kata.pedidos.services.exceptions.CustomException;

@SpringBootTest
class PedidoServiceTest {

	@InjectMocks
	private PedidoService pedidoService;
	@InjectMocks
	private ClienteService clienteService;
	

	@Mock
	private PedidoRepository pedidoRepository;
	
	@Test
	public void testRealizarPedidoSucesso() throws Exception {
		Cliente cliente_01 = new Cliente(1, "José Marcos", "CPF", "email01@mail.com");
		Cliente cliente_02 = new Cliente(2, "Luciano", "CPF", "email01@mail.com");
		
		String retornoSucesso = "Recebidos 8 pedidos.";
		Pedido pedido1 = new Pedido(1001, LocalDate.now(), Produtos.X_SALADA.getNome(), new BigDecimal(30.00), 2, cliente_01);
		Pedido pedido2 = new Pedido(1002, LocalDate.now(), Produtos.PIZZA_CALABRESA.getNome(), new BigDecimal(30.00), 2, cliente_01);
		Pedido pedido3 = new Pedido(1003, LocalDate.now(), Produtos.X_BACON.getNome(), new BigDecimal(30.00), 2, cliente_01);
		Pedido pedido4 = new Pedido(1004, LocalDate.now(), Produtos.CX_SKOL.getNome(), new BigDecimal(20.00), 5, cliente_01);
		Pedido pedido5 = new Pedido(1005, LocalDate.now(), Produtos.X_SALADA.getNome(), new BigDecimal(20.00), 10, cliente_01);
		Pedido pedido6 = new Pedido(1006, LocalDate.now(), Produtos.X_SALADA.getNome(), new BigDecimal(20.00), 100, cliente_02);
		Pedido pedido7 = new Pedido(1007, LocalDate.now(), Produtos.X_SALADA.getNome(), new BigDecimal(20.00), 1000, cliente_02);
		Pedido pedido8 = new Pedido(1008, LocalDate.now(), Produtos.X_SALADA.getNome(), new BigDecimal(20.00), 4, cliente_02);

		String retorno = pedidoService
				.fazerPedido(Arrays.asList(pedido1, pedido2, pedido3, pedido4, pedido5, pedido6, pedido7, pedido8));
		assertNotNull(retorno);
		assertEquals(retornoSucesso, retorno);
	}

	@Test
	public void testRealizarControleRepetido() throws Exception {
		Cliente cliente_01 = new Cliente(1, "José Marcos", "CPF", "email01@mail.com");
		
		Pedido pedido1 = new Pedido(1001, LocalDate.now(), Produtos.X_SALADA.getNome(), new BigDecimal(30.00), 2, cliente_01);
		Pedido pedido2 = new Pedido(1001, LocalDate.now(), Produtos.X_BACON.getNome(), new BigDecimal(30.00), 2, cliente_01);

		assertThrows(CustomException.class, () -> {
			pedidoService.fazerPedido(Arrays.asList(pedido1, pedido2));
		});

	}
	
		
	@Test
	public void testRealizarQuantidadeZero() throws Exception {
		assertThrows(CustomException.class, () -> {
			pedidoService.fazerPedido(Arrays.asList());
		});
	}
	
	@Test
	public void testRealizarQuantidadeMaisQueDez() throws Exception {
		Cliente cliente_01 = new Cliente(1, "José Marcos", "CPF", "email01@mail.com");
		
		Pedido pedido1 = new Pedido(1001, LocalDate.now(), Produtos.X_SALADA.getNome(), new BigDecimal(30.00), 2, cliente_01);
		Pedido pedido2 = new Pedido(1002, LocalDate.now(), Produtos.PIZZA_CALABRESA.getNome(), new BigDecimal(30.00), 1, cliente_01);
		Pedido pedido3 = new Pedido(1003, LocalDate.now(), Produtos.X_BACON.getNome(), new BigDecimal(30.00), 2, cliente_01);
		Pedido pedido4 = new Pedido(1004, LocalDate.now(), Produtos.CX_SKOL.getNome(), new BigDecimal(20.00), 5, cliente_01);
		Pedido pedido5 = new Pedido(1005, LocalDate.now(), Produtos.X_SALADA.getNome(), new BigDecimal(20.00), 10, cliente_01);
		Pedido pedido6 = new Pedido(1006, LocalDate.now(), Produtos.X_SALADA.getNome(), new BigDecimal(20.00), 100, cliente_01);
		Pedido pedido7 = new Pedido(1007, LocalDate.now(), Produtos.X_SALADA.getNome(), new BigDecimal(20.00), 1000, cliente_01);
		Pedido pedido8 = new Pedido(1008, LocalDate.now(), Produtos.X_SALADA.getNome(), new BigDecimal(20.00), 4, cliente_01);
		Pedido pedido9 = new Pedido(1009, LocalDate.now(), Produtos.X_SALADA.getNome(), new BigDecimal(20.00), 4, cliente_01);
		Pedido pedido10 = new Pedido(1010, LocalDate.now(), Produtos.X_SALADA.getNome(), new BigDecimal(20.00), 4, cliente_01);
		Pedido pedido11 = new Pedido(1011, LocalDate.now(), Produtos.X_SALADA.getNome(), new BigDecimal(20.00), 4, cliente_01);


		assertThrows(CustomException.class, () -> {
			pedidoService.fazerPedido(Arrays.asList(
					pedido1, pedido2, pedido3, pedido4, pedido5, pedido6, pedido7, pedido8, pedido9, pedido10, pedido11));
		});

	}

}
