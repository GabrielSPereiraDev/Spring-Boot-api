package com.cadastro.projeto.usuarios.model;

import org.springframework.data.repository.CrudRepository;

/*
 * Interface de acesso à dados da classe Endereco que herda de
 * CrudRepository, métodos para operações básicas de um CRUD
 * e obriga a seguir o padrão de projetos Strategy.
 * 
 * */
public interface EnderecoRepository extends CrudRepository<Endereco, String> {

}