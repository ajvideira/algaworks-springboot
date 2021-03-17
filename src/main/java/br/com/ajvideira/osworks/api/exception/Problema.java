package br.com.ajvideira.osworks.api.exception;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Problema {
  private int status;
  private String titulo;
  private OffsetDateTime dataHora;
  private List<Campo> campos;

  public static class Campo {
    private String nome;
    private String mensagem;

    public Campo(String nome, String mensagem) {
      this.nome = nome;
      this.mensagem = mensagem;
    } 
    
    public String getNome() {
      return this.nome;
    }    

    public void setNome(String nome) {
      this.nome = nome;
    }

    public String getMensagem() {
      return this.mensagem;
    }

    public void setMensagem(String mensagem) {
      this.mensagem = mensagem;
    }
  }

  public int getStatus() {
    return this.status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getTitulo() {
    return this.titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public OffsetDateTime getDataHora() {
    return this.dataHora;
  }

  public void setDataHora(OffsetDateTime dataHora) {
    this.dataHora = dataHora;
  }

  public List<Campo> getCampos() {
    return this.campos;
  }

  public void setCampos(List<Campo> campos) {
    this.campos = campos;
  }

}
