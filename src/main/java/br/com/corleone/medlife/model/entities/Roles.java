package br.com.corleone.medlife.model.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.corleone.medlife.model.enums.RoleType;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_roles")
public class Roles {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private RoleType roleType;
}
