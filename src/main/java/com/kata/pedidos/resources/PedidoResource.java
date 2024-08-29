package com.kata.pedidos.resources;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kata.pedidos.domain.Pedido;
import com.kata.pedidos.services.PedidoService;
import com.kata.pedidos.services.exceptions.CustomException;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Integer id) {
		Pedido obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
    public ResponseEntity<List<Pedido>> obterTodosPedidos() {
        List<Pedido> pedidos = service.obterTodosPedidos();
        return ResponseEntity.ok(pedidos);
    }
	
	   @GetMapping("/data/{dataCadastro}")
	    public ResponseEntity<List<Pedido>> buscarPorDataCadastro(@PathVariable String dataCadastro) {
	        LocalDate data = LocalDate.parse(dataCadastro);
	        List<Pedido> pedidos = service.buscarPorDataCadastro(data);
	        return ResponseEntity.ok(pedidos);
	    }

    
    @PostMapping(path = "/xml", consumes = {"application/xml"})
    public ResponseEntity<String> criarPedido(@RequestBody List<Pedido> pedidos) {
        try {
        	String retorno = service.fazerPedido(pedidos);
    		return new ResponseEntity<>(retorno, HttpStatus.OK);
        } catch (CustomException customEx) {
			throw new CustomException(customEx.getMessage());
		}
    }
    
    @PostMapping(path = "/json", consumes = "application/json")
    public ResponseEntity<String> fazerPedidosJson(@RequestBody List<Pedido> pedidos) {
    		try {
    			String retorno = service.fazerPedido(pedidos);
        		return new ResponseEntity<>(retorno, HttpStatus.OK);
    		} catch (CustomException customEx) {
    			throw new CustomException(customEx.getMessage());
    		}
    		
    }

	

}
