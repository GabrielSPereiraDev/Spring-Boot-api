package com.cadastro.projeto.usuarios.dto;

public record EnderecoRequestDTO(
		
		String cep, 
		String logradouro, 
		String complemento, 
		String bairro, 
		String localidade, 
		String uf,
		String ddd) {

}