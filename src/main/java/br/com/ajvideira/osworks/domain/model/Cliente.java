package br.com.ajvideira.osworks.domain.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.ajvideira.osworks.validations.ValidationGroups;

@Entity
public class Cliente {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NotNull(groups = {ValidationGroups.ClienteId.class})
  private Long id;

  @Column
  @NotBlank(message = "O nome não pode ficar em branco.")
  @Size(min = 1, max = 60)
  private String nome;

  @Column
  @NotBlank
  @Email
  @Size(min = 1, max = 255)
  private String email;

  @Column
  @NotBlank
  @Size(min = 1, max = 20)
  private String telefone;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelefone() {
    return this.telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }  


  @Override
  public boolean equals(Object o) {
    if (o == this)
        return true;
    if (!(o instanceof Cliente)) {
        return false;
    }
    Cliente cliente = (Cliente) o;
    return Objects.equals(id, cliente.id);
  }
  
  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", nome='" + getNome() + "'" +
      ", email='" + getEmail() + "'" +
      ", telefone='" + getTelefone() + "'" +
      "}";
  }

}
