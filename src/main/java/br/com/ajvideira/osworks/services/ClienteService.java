package br.com.ajvideira.osworks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ajvideira.osworks.domain.model.Cliente;
import br.com.ajvideira.osworks.domain.repositories.ClienteRepository;
import br.com.ajvideira.osworks.exception.DomainException;

@Service
public class ClienteService {
  
  @Autowired
  private ClienteRepository clienteRepository;

  public Cliente salvar(Cliente cliente) {
    Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());

    if (clienteExistente != null && !clienteExistente.equals(cliente)) {
      throw new DomainException("Já existe um cliente cadastrado com esse e-mail.");
    }

    return clienteRepository.save(cliente);
  }

  public void remover(Long clienteId) {
    clienteRepository.deleteById(clienteId);
  }

  public boolean existsById(Long clienteId) {
    return clienteRepository.existsById(clienteId);
  }

  public Optional<Cliente> findById(Long clienteId) {
    return clienteRepository.findById(clienteId);
  }

  public List<Cliente> findAll() {
    return clienteRepository.findAll();
  }

}
