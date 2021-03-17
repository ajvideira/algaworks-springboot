package br.com.ajvideira.osworks.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ajvideira.osworks.api.models.ComentarioCadastroDTO;
import br.com.ajvideira.osworks.api.models.ComentarioDTO;
import br.com.ajvideira.osworks.domain.model.Comentario;
import br.com.ajvideira.osworks.domain.model.OrdemServico;
import br.com.ajvideira.osworks.exception.EntityNotFoundException;
import br.com.ajvideira.osworks.services.OrdemServicoService;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {
  
  @Autowired
  private OrdemServicoService ordemServicoService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ComentarioDTO criar(@PathVariable Long ordemServicoId, @Valid @RequestBody ComentarioCadastroDTO comentarioCadastro ) {
    return toModel(ordemServicoService.incluirComentario(ordemServicoId, comentarioCadastro.getDescricao()));
  }

  @GetMapping
  public List<ComentarioDTO> listar(@PathVariable Long ordemServicoId) {
    OrdemServico ordemServico = ordemServicoService.findById(ordemServicoId)
      .orElseThrow(() -> new EntityNotFoundException("Ordem de serviço não encontrada."));
    
    return toListModel(ordemServico.getComentarios());
  }

  private ComentarioDTO toModel(Comentario comentario) {
    return modelMapper.map(comentario, ComentarioDTO.class);
  }

  private List<ComentarioDTO> toListModel(List<Comentario> comentarioList) {
    return comentarioList
      .stream()
      .map(comentario -> toModel(comentario))
      .collect(Collectors.toList());
  }

}
