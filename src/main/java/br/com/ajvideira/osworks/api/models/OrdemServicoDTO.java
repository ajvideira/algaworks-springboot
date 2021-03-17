package br.com.ajvideira.osworks.api.models;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import br.com.ajvideira.osworks.domain.model.StatusOrdemServico;

public class OrdemServicoDTO {
  private Long id;
  private String nomeCliente;
  private String descricao;
  private BigDecimal preco;
  private StatusOrdemServico status;
  private OffsetDateTime dataAbertura;
  private OffsetDateTime dataFinalizacao;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNomeCliente() {
    return this.nomeCliente;
  }

  public void setNomeCliente(String nomeCliente) {
    this.nomeCliente = nomeCliente;
  }

  public String getDescricao() {
    return this.descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public BigDecimal getPreco() {
    return this.preco;
  }

  public void setPreco(BigDecimal preco) {
    this.preco = preco;
  }

  public StatusOrdemServico getStatus() {
    return this.status;
  }

  public void setStatus(StatusOrdemServico status) {
    this.status = status;
  }

  public OffsetDateTime getDataAbertura() {
    return this.dataAbertura;
  }

  public void setDataAbertura(OffsetDateTime dataAbertura) {
    this.dataAbertura = dataAbertura;
  }

  public OffsetDateTime getDataFinalizacao() {
    return this.dataFinalizacao;
  }

  public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
    this.dataFinalizacao = dataFinalizacao;
  }

}
