package com.kata.pedidos.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kata.pedidos.domain.Pedido;
import com.kata.pedidos.repositories.PedidoRepository;
import com.kata.pedidos.services.exceptions.CustomException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido findById(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new CustomException("Pedido não encontrado! ID = " + id));
	}

	public String fazerPedido(List<Pedido> pedidos) {
		validarItemPedido(pedidos);
		checarNumeroControle(pedidos);
		calcularValorPedidos(pedidos);
		salvarPedidos(pedidos);

		String mensagemRetorno = "Recebidos " + pedidos.size() + " pedidos.";

		return mensagemRetorno;
	}

	private void calcularValorPedidos(List<Pedido> pedidos) {
		for (Pedido pedido : pedidos) {
			pedido.setValorTotal(pedido.getValor().multiply(new BigDecimal(pedido.getQuantidade())));
			if (pedido.getQuantidade() >= 5 && pedido.getQuantidade() < 10) {
				pedido.setValorTotal(calcularDesconto(pedido.getValorTotal(), "5"));
			}
			if (pedido.getQuantidade() >= 10) {
				pedido.setValorTotal(calcularDesconto(pedido.getValorTotal(), "10"));
			}
		}
	}

	private void salvarPedidos(List<Pedido> pedidos) {
		pedidoRepository.saveAll(pedidos);
	}

	private BigDecimal calcularDesconto(BigDecimal valorTotal, String desconto) {
		BigDecimal percentualDesconto = new BigDecimal(desconto);
		BigDecimal valorDesconto = valorTotal.multiply(percentualDesconto).divide(new BigDecimal("100.00"),
				RoundingMode.HALF_UP);

		BigDecimal valorFinal = valorTotal.subtract(valorDesconto);
		return valorFinal;
	}

	private void checarNumeroControle(List<Pedido> pedidos) {
		for (Pedido pedido : pedidos) {
			Optional<Pedido> pedidoRetorno = pedidoRepository.findByNumeroControle(pedido.getNumeroControle());
			if (pedidoRetorno.isPresent()) {
				throw new CustomException("Numero de Controle já existe!!" + pedido.getNumeroControle());
			}
		}
	}

	private void validarItemPedido(List<Pedido> pedidos) {
		if (pedidos == null || pedidos.size() < 1 || pedidos.size() > 10) {
			throw new CustomException("Pedido cancelado, quantidade de pedidos fora do esperado !");
		}

		Map<Integer, Pedido> mapPedidos = new HashMap<>();
		for (Pedido pedido : pedidos) {
			if (pedido.getNome() == null) {
				throw new CustomException("Pedido cancelado, Pedido faltando Nome !");
			}
			if (pedido.getNumeroControle() == null) {
				throw new CustomException("Pedido cancelado, Pedido faltando Número Controle !" + pedido.getNome());
			}
			if (pedido.getDataCadastro() == null) {
				pedido.setDataCadastro(LocalDate.now());
			}
			if (pedido.getQuantidade() == null) {
				pedido.setQuantidade(1);
			}
			if (mapPedidos.containsKey(pedido.getNumeroControle())) {
				throw new CustomException("Pedido cancelado, Número de controle Repetido: " + pedido.getNumeroControle());
			}
			mapPedidos.put(pedido.getNumeroControle(), pedido);
		}

	}

	public List<Pedido> obterTodosPedidos() {
		return pedidoRepository.findAll();
	}

	public List<Pedido> buscarPorDataCadastro(LocalDate dataCadastro) {
		return pedidoRepository.findByDataCadastro(dataCadastro);
	}

}
