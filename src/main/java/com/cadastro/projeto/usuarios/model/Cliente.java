package com.cadastro.projeto.usuarios.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
/*
 * Entidade de domíniio que representa a tabela
 * do banco de dados
 * 
 * 
 * */
@Entity
public class Cliente {
	
	//Atributos da classe

	//Id: Chave primária da tabela
	@Id
	//configurada para auto-incremento 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Email
	@NotBlank(message = "O email é obrigatório")
	private String email;
	
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	 @NotBlank(message = "O telefone é obrigatório")
	 @Pattern(regexp = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$", 
	 message = "O telefone deve estar no formato (XX) XXXXX-XXXX"
	    )
	private String telefone;
	

	@ManyToOne
	private Endereco endereco;
	
	
	//Getters e setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
