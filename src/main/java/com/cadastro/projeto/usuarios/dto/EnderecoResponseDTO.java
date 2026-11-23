package com.cadastro.projeto.usuarios.dto;

import com.cadastro.projeto.usuarios.model.Endereco;

public record EnderecoResponseDTO(
		
		String cep, 
		String logradouro, 
		String complemento, 
		String bairro, 
		String localidade, 
		String uf,
		String ddd) { 
	
	public static EnderecoResponseDTO fromEntity(Endereco endereco) { 
		
		if (endereco == null) return null; 
		
		return new EnderecoResponseDTO( endereco.getCep(), 
				endereco.getLogradouro(), 
				endereco.getComplemento(), 
				endereco.getBairro(), 
				endereco.getLocalidade(), 
				endereco.getUf(),
				endereco.getDdd()); 
		
	} 
	
}

