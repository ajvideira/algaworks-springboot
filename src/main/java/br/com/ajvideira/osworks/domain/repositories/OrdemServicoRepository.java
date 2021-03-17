package br.com.ajvideira.osworks.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ajvideira.osworks.domain.model.OrdemServico;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
}
