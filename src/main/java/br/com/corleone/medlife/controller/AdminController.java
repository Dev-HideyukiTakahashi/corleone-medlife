package br.com.corleone.medlife.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.corleone.medlife.model.entities.Roles;
import br.com.corleone.medlife.model.entities.Usuario;
import br.com.corleone.medlife.repository.RoleRepository;
import br.com.corleone.medlife.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class AdminController {

  private final UsuarioRepository usuarioRepository;
  private final RoleRepository roleRepository;

  @GetMapping
  public ModelAndView adminAreaPage() {
    // TODO - checar se o usuario tem o ROLE ADMIN
    ModelAndView mv = new ModelAndView("/auth/admin/area-admin");

    List<Usuario> usuarios = usuarioRepository.findAll();
    List<Roles> roles = roleRepository.findAll();

    mv.addObject("roles", roles);
    mv.addObject("usuarios", usuarios);

    return mv;
  }

  @GetMapping(path = "/buscar")
  public ModelAndView buscarPorNome(@RequestParam(name = "nome") String nome) {

    List<Usuario> usuarios = usuarioRepository.findByNomeContainsIgnoreCase(nome);
    ModelAndView mv = new ModelAndView("/auth/admin/area-admin");
    mv.addObject("roles", roleRepository.findAll());

    if (usuarios.size() < 1) {
      mv.addObject("msg", "Sem registros de usuário.");
      mv.addObject("usuarios", null);
    } else {
      mv.addObject("msg", usuarios.size() + " registros em sistema.");
      mv.addObject("usuarios", usuarios);
    }

    return mv;
  }
}
