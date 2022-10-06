package br.com.corleone.medlife.model.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.corleone.medlife.model.enums.Status;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_consultas")
public class Consulta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime data;

  private String triagem;
  private String historico;

  @ManyToOne
  private Medico medico;

  @ManyToOne
  private Paciente paciente;

  @Enumerated(EnumType.STRING)
  private Status status;
}
