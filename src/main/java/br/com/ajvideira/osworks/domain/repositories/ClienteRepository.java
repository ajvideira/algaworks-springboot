package br.com.ajvideira.osworks.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ajvideira.osworks.domain.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  public List<Cliente> findByNome(String nome);
  public List<Cliente> findByNomeContaining(String nome);
  public Cliente findByEmail(String email);
}
