package br.com.corleone.medlife.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@Table(name = "tb_medicos")
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

  @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
  private List<Consulta> consultas = new ArrayList<>();

}
