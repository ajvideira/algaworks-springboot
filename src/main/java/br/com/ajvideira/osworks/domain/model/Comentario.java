package br.com.ajvideira.osworks.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Comentario {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Column
  private String descricao;

  @ManyToOne
  private OrdemServico ordemServico;

  @Column
  private OffsetDateTime dataCriacao;

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

  public OrdemServico getOrdemServico() {
    return this.ordemServico;
  }

  public void setOrdemServico(OrdemServico ordemServico) {
    this.ordemServico = ordemServico;
  }


  public OffsetDateTime getDataCriacao() {
    return this.dataCriacao;
  }

  public void setDataCriacao(OffsetDateTime dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", descricao='" + getDescricao() + "'" +
      ", ordemServico='" + getOrdemServico() + "'" +
      ", dataCriacao='" + getDataCriacao() + "'" +
      "}";
  }

}
