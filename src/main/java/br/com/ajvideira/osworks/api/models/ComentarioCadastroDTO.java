package br.com.ajvideira.osworks.api.models;

import javax.validation.constraints.NotBlank;

public class ComentarioCadastroDTO {
  
  @NotBlank
  private String descricao;

  public String getDescricao() {
    return this.descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

}
