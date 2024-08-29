package com.kata.pedidos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kata.pedidos.domain.Cliente;
import com.kata.pedidos.repositories.ClienteRepository;
import com.kata.pedidos.services.exceptions.CustomException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new CustomException("Pedido não encontrado! " + id));
	}
	
	public List<Cliente> saveAll(List<Cliente> listaCliente){
		return  clienteRepository.saveAll(listaCliente);
	}
	
	
}
