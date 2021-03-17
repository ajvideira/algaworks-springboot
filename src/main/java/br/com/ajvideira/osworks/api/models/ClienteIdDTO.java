package br.com.ajvideira.osworks.api.models;

import javax.validation.constraints.NotNull;

public class ClienteIdDTO {
  @NotNull
  private Long id;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
