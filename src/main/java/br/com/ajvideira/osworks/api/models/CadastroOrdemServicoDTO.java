package br.com.ajvideira.osworks.api.models;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CadastroOrdemServicoDTO {
  @NotBlank
  private String descricao;

  @NotNull
  private BigDecimal preco;

  @Valid
  @NotNull
  private ClienteIdDTO cliente;

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

  public ClienteIdDTO getCliente() {
    return this.cliente;
  }

  public void setCliente(ClienteIdDTO cliente) {
    this.cliente = cliente;
  }

}
