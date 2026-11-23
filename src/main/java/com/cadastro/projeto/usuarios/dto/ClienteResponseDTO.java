package com.cadastro.projeto.usuarios.dto;

import com.cadastro.projeto.usuarios.model.Cliente;



/*
 * ClienteResponseDTO é o dto de saída estrutura os dados 
 * que serão recebidos pelas requisições http
 * 
 * */
public record ClienteResponseDTO( 
		
		//Campos para os dados do cliente
		Long id, 
		String nome, 
		String email, 
		String telefone, 
		EnderecoResponseDTO endereco ) { 
	
	
	public static ClienteResponseDTO fromEntity(Cliente cliente) { 
		
		return new ClienteResponseDTO( cliente.getId(), 
				cliente.getNome(), 
				cliente.getEmail(), 
				cliente.getTelefone(), 
				EnderecoResponseDTO.fromEntity(cliente.getEndereco()) ); } }