package br.com.ajvideira.osworks.api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ajvideira.osworks.api.models.CadastroOrdemServicoDTO;
import br.com.ajvideira.osworks.api.models.OrdemServicoDTO;
import br.com.ajvideira.osworks.domain.model.Cliente;
import br.com.ajvideira.osworks.domain.model.OrdemServico;
import br.com.ajvideira.osworks.exception.DomainException;
import br.com.ajvideira.osworks.services.ClienteService;
import br.com.ajvideira.osworks.services.OrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
  
  @Autowired
  private OrdemServicoService ordemServicoService;

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping
  public List<OrdemServicoDTO> listar() {
    return toListModel( ordemServicoService.findAll() );
  }

  @GetMapping("/{ordemServicoId}")
  public ResponseEntity<OrdemServicoDTO> buscar(@PathVariable Long ordemServicoId) {
    Optional<OrdemServico> ordemServico = ordemServicoService.findById(ordemServicoId);

    return ordemServico.isPresent() ? ResponseEntity.ok(
      toModel( ordemServico.get() ) ) : ResponseEntity.notFound().build();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OrdemServicoDTO criar(@Valid @RequestBody CadastroOrdemServicoDTO cadastroOrdemServico) {
    OrdemServico ordemServico = toEntity(cadastroOrdemServico);

    Cliente cliente = clienteService.findById(ordemServico.getCliente().getId())
      .orElseThrow(() -> new DomainException("Cliente não encontrado."));
    ordemServico.setCliente(cliente);

    return toModel(ordemServicoService.criar(ordemServico));
  }

  @PutMapping("/{ordemServicoId}/finalizacao")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void finalizar(@PathVariable Long ordemServicoId) {
    ordemServicoService.finalizar(ordemServicoId);
  }

  private OrdemServicoDTO toModel(OrdemServico ordemServico) {
    return modelMapper.map(ordemServico, OrdemServicoDTO.class);
  }

  private List<OrdemServicoDTO> toListModel(List<OrdemServico> ordemServicoList) {
    return ordemServicoList
      .stream()
      .map(ordemServico -> toModel(ordemServico))
      .collect(Collectors.toList());
  }

  private OrdemServico toEntity(CadastroOrdemServicoDTO cadastroOrdemServico) {
    return modelMapper.map(cadastroOrdemServico, OrdemServico.class);
  }

}
