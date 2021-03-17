package br.com.ajvideira.osworks.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.ajvideira.osworks.exception.DomainException;
import br.com.ajvideira.osworks.validations.ValidationGroups;

@Entity
public class OrdemServico {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  @NotBlank
  private String descricao;

  @Column
  @NotNull
  private BigDecimal preco;

  @Column
  @Enumerated(EnumType.STRING)
  @JsonProperty(access = Access.READ_ONLY)
  private StatusOrdemServico status;

  @Column
  @JsonProperty(access = Access.READ_ONLY)
  private OffsetDateTime dataAbertura;

  @Column
  @JsonProperty(access = Access.READ_ONLY)
  private OffsetDateTime dataFinalizacao;

  @Valid
  @NotNull
  @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
  @ManyToOne
  private Cliente cliente;

  @OneToMany(mappedBy = "ordemServico")
  private List<Comentario> comentarios;

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

  public Cliente getCliente() {
    return this.cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public List<Comentario> getComentarios() {
    return this.comentarios;
  }

  public void setComentarios(List<Comentario> comentarios) {
    this.comentarios = comentarios;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
        return true;
    if (!(o instanceof OrdemServico)) {
        return false;
    }
    OrdemServico ordemServico = (OrdemServico) o;
    return Objects.equals(id, ordemServico.id);
  }

  public boolean podeSerFinalizada() {
    return getStatus().equals(StatusOrdemServico.ABERTA);
  }

  public boolean naoPodeSerFinalizada() {
    return !podeSerFinalizada();
  }

  public void finalizar() {
    if (naoPodeSerFinalizada()) {
      throw new DomainException("A ordem de serviço não pode ser finalizada, "
        + "porque já se encontra " + getStatus().toString().toLowerCase()+".");
    }

    setStatus(StatusOrdemServico.FINALIZADA);
    setDataFinalizacao(OffsetDateTime.now());
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", descricao='" + getDescricao() + "'" +
      ", preco='" + getPreco() + "'" +
      ", status='" + getStatus() + "'" +
      ", dataAbertura='" + getDataAbertura() + "'" +
      ", dataFinalizacao='" + getDataFinalizacao() + "'" +
      ", cliente='" + getCliente() + "'" +
      ", comentarios='" + getComentarios() + "'" +
      "}";
  }

}
