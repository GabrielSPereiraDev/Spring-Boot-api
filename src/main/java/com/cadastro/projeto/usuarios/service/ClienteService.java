package com.cadastro.projeto.usuarios.service;

import java.util.List;

import com.cadastro.projeto.usuarios.dto.ClienteRequestDTO;
import com.cadastro.projeto.usuarios.dto.ClienteResponseDTO;


/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cliente podendo
 * ter implementações dos métodos da interface.
 * 
 * 
 */

public interface ClienteService {
	
List<ClienteResponseDTO> buscarTodos();
	
	ClienteResponseDTO buscarPorId(Long id);
	
	ClienteResponseDTO inserir(ClienteRequestDTO dto);
	
	ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto);
	
	void deletar(Long id);
}
