package com.cadastro.projeto.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
/**
 * ClienteRequestDTO recebe, transporta e valida os dados
 * de um cliente enviados pelas requisições http
 * 
 * */
public record ClienteRequestDTO(

		//Validação do email
		@Email(message = "E-mail inválido")
        @NotBlank(message = "O email é obrigatório")
        String email,

        //Validação do nome
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        //Validaão do telefone
        @NotBlank(message = "O telefone é obrigatório")
        @Pattern(
            regexp = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$",
            message = "O telefone deve estar no formato (XX) XXXXX-XXXX"
        )
        String telefone,

        //Validação do cep
        @Pattern(
            regexp = "\\d{8}",
            message = "O CEP deve conter 8 dígitos numéricos"
        )
        @NotBlank(message = "O CEP é obrigatório")
        String cep
		
		) {
	
}
