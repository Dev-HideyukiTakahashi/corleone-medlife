package br.com.corleone.medlife.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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

  public Medico(String email, String telefone, String crm, String login, String senha, String nome) {
    super(login, senha, nome, telefone);
    this.email = email;
    this.crm = crm;
  }

  private String email;

  @NotEmpty(message = "CRM obrigatório!")
  private String crm;

  @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
  private List<Consulta> consultas = new ArrayList<>();

  public Medico usuarioParaMedico(Usuario usuario) {
    Medico medico = new Medico();
    if (usuario.getId() != null) {
      super.setId(usuario.getId());
    }
    super.setLogin(usuario.getLogin());
    super.setSenha(usuario.getSenha());
    super.setNome(usuario.getNome());
    super.setTelefone(usuario.getTelefone());
    super.setRole(usuario.getRole());

    return medico;
  }

}
