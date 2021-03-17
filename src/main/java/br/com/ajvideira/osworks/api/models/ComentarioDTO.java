package br.com.ajvideira.osworks.api.models;

import java.time.OffsetDateTime;

public class ComentarioDTO {
  
  private Long id;
  private String descricao;
  private OffsetDateTime dataCriacao;
  private Long ordemServicoId;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescricao() {
    return this.descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public OffsetDateTime getDataCriacao() {
    return this.dataCriacao;
  }

  public void setDataCriacao(OffsetDateTime dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public Long getOrdemServicoId() {
    return this.ordemServicoId;
  }

  public void setOrdemServicoId(Long ordemServicoId) {
    this.ordemServicoId = ordemServicoId;
  }

}
