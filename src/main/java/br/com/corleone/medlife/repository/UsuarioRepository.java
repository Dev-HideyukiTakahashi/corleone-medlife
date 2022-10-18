package br.com.corleone.medlife.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.corleone.medlife.model.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  public List<Usuario> findByNomeContainsIgnoreCase(String nome);

  public Boolean existsByLogin(String login);

  public Usuario findByLogin(String login);

}
