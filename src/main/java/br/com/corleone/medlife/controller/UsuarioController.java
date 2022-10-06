package br.com.corleone.medlife.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.corleone.medlife.model.entities.Usuario;
import br.com.corleone.medlife.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(path = "/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

  private final UsuarioRepository usuarioRepository;

  @GetMapping
  public List<Usuario> findAll() {
    List<Usuario> usuarios = usuarioRepository.findAll();
    return usuarios;
  }

  @GetMapping("/{id}")
  public Usuario findById(@PathVariable Long id) {
    Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado, id: " + id));
    return usuario;
  }
}
