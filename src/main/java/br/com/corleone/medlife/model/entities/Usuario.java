package br.com.corleone.medlife.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

  public Usuario() {
  }

  public Usuario(String username, String password, String nome, String telefone) {
    this.username = username;
    this.password = password;
    this.nome = nome;
    this.telefone = telefone;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;
  private String password;

  @NotNull
  @Size(min = 2, message = "Nome deve ter no mínimo 2 caracteres")
  private String nome;

  @NotNull
  @Size(min = 8, max = 14, message = "Preencha um telefone válido")
  private String telefone;

  @Lob
  private Byte foto;

}
