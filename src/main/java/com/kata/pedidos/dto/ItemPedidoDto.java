package com.kata.pedidos.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ItemPedidoDto implements Serializable {

	private static final long serialVersionUID = 2502533197315436963L;


	private Integer numControle;
	private LocalDate dataCadastro;
	private String nome;
	private BigDecimal valor;
	private Integer quantidade;

	

	public Integer getNumControle() {
		return numControle;
	}

	public void setNumControle(Integer numControle) {
		this.numControle = numControle;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}


}
