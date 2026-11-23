package com.cadastro.projeto.usuarios.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/*
 * Interface de acesso à dados da classe Cliente que herda de
 * CrudRepository, métodos para operações básicas de um CRUD
 * e implementa o padrão de projeto repository.
 * 
 * */
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
