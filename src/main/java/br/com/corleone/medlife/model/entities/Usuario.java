package br.com.corleone.medlife.model.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tb_usuarios")
public class Usuario implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String login;

  private String senha;

  private String nome;

  private String telefone;

  @Lob
  private Byte foto;

  @ManyToOne
  @JoinColumn(name = "role_id")
  private Roles role;

  public Usuario() {

  }

  public Usuario(String login, String senha, String nome, String telefone) {
    this.login = login;
    this.senha = senha;
    this.nome = nome;
    this.telefone = telefone;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<Roles> pp = new ArrayList<>();
    pp.add(role);
    return pp;
  }

  @Override
  public String getPassword() {
    return senha;
  }

  @Override
  public String getUsername() {
    return login;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
