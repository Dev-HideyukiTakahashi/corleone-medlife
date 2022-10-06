package br.com.corleone.medlife.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.corleone.medlife.model.entities.Usuario;
import br.com.corleone.medlife.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class AdminController {

  private final UsuarioRepository usuarioRepository;

  @GetMapping
  public ModelAndView adminAreaPage() {

    // TODO - checar se o usuario tem o ROLE ADMIN
    ModelAndView mv = new ModelAndView("/auth/admin/area-admin");
    List<Usuario> usuarios = usuarioRepository.findAll();
    mv.addObject("usuarios", usuarios);

    return mv;
  }
}
