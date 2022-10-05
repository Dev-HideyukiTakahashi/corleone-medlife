package br.com.corleone.medlife.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.corleone.medlife.model.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
