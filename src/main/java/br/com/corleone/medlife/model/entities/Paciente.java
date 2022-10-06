package br.com.corleone.medlife.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_pacientes")
public class Paciente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String cpf;
  private String telefone;
  private String email;
  private String endereco;
  private String sexo;
  private Integer idade;

  private LocalDate dataNascimento;

  @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
  private List<Consulta> consultas = new ArrayList<>();

  public Paciente() {
  }

  public Paciente(String nome, String cpf, String telefone, String email, String endereco, String sexo, Integer idade,
      LocalDate dataNascimento) {
    this.nome = nome;
    this.cpf = cpf;
    this.telefone = telefone;
    this.email = email;
    this.endereco = endereco;
    this.sexo = sexo;
    this.idade = idade;
    this.dataNascimento = dataNascimento;
  }

}
