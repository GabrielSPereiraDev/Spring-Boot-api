package com.cadastro.projeto.usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cadastro.projeto.usuarios.dto.ClienteRequestDTO;
import com.cadastro.projeto.usuarios.dto.ClienteResponseDTO;
import com.cadastro.projeto.usuarios.service.ClienteService;

import jakarta.validation.Valid;

/**
 * Esse RestController representa o padrão <b>Facade</b>, pois abstrai toda
 * a complexidade de integrações (Banco de Dados H2 e API do ViaCEP);
 * 
 * A classe é gerenciada pelo Spring Framework como uma classe de instância 
 * única seguindo o padrão <b>Singleton</b>
 */
@RestController
@RequestMapping("clientes")
public class ClienteRestController {

	//Essa notação está injetando a Classe ClienteService
	@Autowired
	private ClienteService clienteService;

	//mapeamento dos métodos HTTP
	
	//GET
	@GetMapping
	public ResponseEntity<List<ClienteResponseDTO>> buscarTodos() {
		return ResponseEntity.ok(clienteService.buscarTodos());
	}

	//GET, buscar cliente pelo id
	@GetMapping("/{id}")
	public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(clienteService.buscarPorId(id));
	}

	//POST
	@PostMapping
	public ResponseEntity<ClienteResponseDTO> inserir(@RequestBody @Valid ClienteRequestDTO dto) {
		
		ClienteResponseDTO clienteCriado = clienteService.inserir(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriado);
	}

	
	//PUT
	@PutMapping("/{id}")
	public ResponseEntity<ClienteRequestDTO> atualizar(@PathVariable Long id, 
			@RequestBody @Valid ClienteRequestDTO dto) {
		
		clienteService.atualizar(id, dto);
		return ResponseEntity.ok(dto);
	}

	
	//DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		clienteService.deletar(id);
		return ResponseEntity.ok().build();
	}
}
