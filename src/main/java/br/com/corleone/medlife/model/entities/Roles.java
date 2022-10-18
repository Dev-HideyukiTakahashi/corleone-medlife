package br.com.corleone.medlife.model.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import br.com.corleone.medlife.model.enums.RoleType;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_roles")
public class Roles implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private RoleType roleType;

  @Override
  public String getAuthority() {
    return roleType.toString();
  }

  public String getRoleType() {
    String role = roleType.toString().replace("ROLE_", "");
    return role;
  }
}
