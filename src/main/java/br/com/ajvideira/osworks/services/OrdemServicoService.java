package br.com.ajvideira.osworks.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ajvideira.osworks.domain.model.Comentario;
import br.com.ajvideira.osworks.domain.model.OrdemServico;
import br.com.ajvideira.osworks.domain.model.StatusOrdemServico;
import br.com.ajvideira.osworks.domain.repositories.ComentarioRepository;
import br.com.ajvideira.osworks.domain.repositories.OrdemServicoRepository;
import br.com.ajvideira.osworks.exception.EntityNotFoundException;

@Service
public class OrdemServicoService {
  
  @Autowired
  private OrdemServicoRepository ordemServicoRepository;

  @Autowired
  private ComentarioRepository comentarioRepository;

  public OrdemServico criar(OrdemServico ordemServico) {
    ordemServico.setStatus(StatusOrdemServico.ABERTA);
    ordemServico.setDataAbertura(OffsetDateTime.now());

    return ordemServicoRepository.save(ordemServico);
  }

  public List<OrdemServico> findAll() {
    return ordemServicoRepository.findAll();
  }

  public Optional<OrdemServico> findById(Long ordemServicoId) {
    return ordemServicoRepository.findById(ordemServicoId);
  }

  public void finalizar(Long ordemServicoId) {
    OrdemServico ordemServico = findById(ordemServicoId).orElseThrow(() -> 
      new EntityNotFoundException("Ordem de serviço não encontrada."));

    ordemServico.finalizar();

    ordemServicoRepository.save(ordemServico);
  }

  public Comentario incluirComentario(Long ordemServicoId, String descricao) {
    OrdemServico ordemServico = findById(ordemServicoId).orElseThrow(() -> 
      new EntityNotFoundException("Ordem de serviço não encontrada."));

    Comentario comentario = new Comentario();
    comentario.setDescricao(descricao);
    comentario.setOrdemServico(ordemServico);
    comentario.setDataCriacao(OffsetDateTime.now());

    return comentarioRepository.save(comentario);
  }

}
