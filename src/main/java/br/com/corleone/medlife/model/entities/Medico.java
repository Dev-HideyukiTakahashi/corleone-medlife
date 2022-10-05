package br.com.corleone.medlife.model.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
public class Medico extends Usuario {

  public Medico() {
  }

  public Medico(String email, String telefone, Integer crm, String username, String password, String nome) {
    super(username, password, nome, telefone);
    this.email = email;
    this.crm = crm;
  }

  @Email(message = "Digite um email válido")
  private String email;

  @NotNull
  private Integer crm;

}
