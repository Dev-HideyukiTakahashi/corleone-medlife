package br.com.corleone.medlife.model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_pacientes")
public class Paciente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Preencha o nome do paciente.")
  private String nome;

  @CPF(message = "Número de CPF inválido.")
  private String cpf;

  @NotBlank(message = "Preencha o número de telefone do paciente.")
  private String telefone;

  @NotBlank(message = "Preencha o número de telefone do paciente.")
  @Email(message = "Preencha um email válido.")
  private String email;

  @NotBlank(message = "Preencha o sexo do paciente.")
  private String sexo;

  @NotNull(message = "Prencha a idade do paciente.")
  @Min(value = 10, message = "Idade precisa ser acima de 10 anos")
  @Max(value = 110, message = "Idade precisa ser acima de 110 anos")
  private Integer idade;
  private String Rua;
  private String bairro;
  private String cidade;
  private String numero;

  private LocalDate dataNascimento;

  @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
  private List<Consulta> consultas = new ArrayList<>();

  public Paciente() {
  }

  public Paciente(String nome, String cpf, String telefone, String email, String sexo, Integer idade,
      LocalDate dataNascimento) {
    this.nome = nome;
    this.cpf = cpf;
    this.telefone = telefone;
    this.email = email;
    this.sexo = sexo;
    this.idade = idade;
    this.dataNascimento = dataNascimento;

  }

  public void setDataNascimento(String data) {
    this.dataNascimento = LocalDate.parse(data);
  }

  public String getDataNascimento() {
    if (dataNascimento != null) {
      return dataNascimento.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    return "";
  }

}
