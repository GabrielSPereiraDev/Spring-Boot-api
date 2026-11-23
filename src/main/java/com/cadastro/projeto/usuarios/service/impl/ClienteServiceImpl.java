package com.cadastro.projeto.usuarios.service.impl;

import java.util.List;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadastro.projeto.usuarios.dto.ClienteRequestDTO;
import com.cadastro.projeto.usuarios.dto.ClienteResponseDTO;
import com.cadastro.projeto.usuarios.model.Cliente;
import com.cadastro.projeto.usuarios.model.ClienteRepository;
import com.cadastro.projeto.usuarios.model.Endereco;
import com.cadastro.projeto.usuarios.model.EnderecoRepository;
import com.cadastro.projeto.usuarios.service.ClienteService;
import com.cadastro.projeto.usuarios.service.ViaCepService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteServiceImpl implements ClienteService {

	
	// Usa o padrão Singleton ao Injetar os componentes do Spring com @Autowired.
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ViaCepService viaCepService;

	
	
	// Usa o padrão Strategy ao implementar os métodos definidos na interface.
	// Usa o padrão Facade ao abstrair integrações com subsistemas, provendo uma interface simples.
	@Override
	public List<ClienteResponseDTO> buscarTodos() {
		return StreamSupport.stream(clienteRepository.findAll().spliterator(), false)
				.map(ClienteResponseDTO::fromEntity)
				.toList();
	}

	@Override
	public ClienteResponseDTO buscarPorId(Long id) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));
		return ClienteResponseDTO.fromEntity(cliente);
	}

	@Override
	public ClienteResponseDTO inserir(ClienteRequestDTO dto) {
		Cliente cliente = new Cliente();
		cliente.setNome(dto.nome());
		cliente.setEmail(dto.email());
		cliente.setTelefone(dto.telefone());

		Cliente clienteSalvo = salvarClienteComCep(cliente, dto.cep());
		return ClienteResponseDTO.fromEntity(clienteSalvo);
	}

	@Override
	public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {
		Cliente clienteExistente = clienteRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));

		clienteExistente.setNome(dto.nome());
		clienteExistente.setEmail(dto.email());
		clienteExistente.setTelefone(dto.telefone());

		Cliente clienteAtualizado = salvarClienteComCep(clienteExistente, dto.cep());
		return ClienteResponseDTO.fromEntity(clienteAtualizado);
	}

	@Override
	public void deletar(Long id) {
		if (!clienteRepository.existsById(id)) {
			throw new EntityNotFoundException("Cliente não encontrado com ID: " + id);
		}
		clienteRepository.deleteById(id);
	}

	private Cliente salvarClienteComCep(Cliente cliente, String cep) {
		String cepLimpo = cep.replaceAll("\\D", "");

		Endereco endereco = enderecoRepository.findById(cepLimpo).orElseGet(() -> {
			Endereco novoEndereco = viaCepService.consultarCep(cepLimpo);

			if (novoEndereco == null || novoEndereco.getCep() == null) {
				throw new IllegalArgumentException("CEP inválido ou não encontrado: " + cep);
			}

			// Normaliza o CEP sem hífen para manter consistência com a chave primária
			novoEndereco.setCep(cepLimpo);
			return enderecoRepository.save(novoEndereco);
		});

		cliente.setEndereco(endereco);
		return clienteRepository.save(cliente);
	}
}


////////////////////////////////////////////////////////////


