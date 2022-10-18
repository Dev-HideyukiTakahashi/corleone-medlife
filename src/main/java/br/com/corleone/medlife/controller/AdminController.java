package br.com.corleone.medlife.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.corleone.medlife.model.entities.Medico;
import br.com.corleone.medlife.model.entities.Roles;
import br.com.corleone.medlife.model.entities.Usuario;
import br.com.corleone.medlife.model.enums.RoleType;
import br.com.corleone.medlife.repository.MedicoRepository;
import br.com.corleone.medlife.repository.RoleRepository;
import br.com.corleone.medlife.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class AdminController {

  private final UsuarioRepository usuarioRepository;
  private final RoleRepository roleRepository;
  private final MedicoRepository medicoRepository;

  @GetMapping
  public ModelAndView adminAreaView() {
    ModelAndView mv = new ModelAndView("/auth/admin/area-admin");

    mv.addObject("roles", roleRepository.findAll());
    mv.addObject("usuarios", usuarioRepository.findAll());
    mv.addObject("medicos", medicoRepository.findAll());

    return mv;
  }

  @GetMapping(path = "/buscar")
  public ModelAndView buscarPorNome(@RequestParam(name = "nome") String nome) {
    List<Usuario> usuarios = usuarioRepository.findByNomeContainsIgnoreCase(nome);
    ModelAndView mv = new ModelAndView("/auth/admin/area-admin");

    mv.addObject("roles", roleRepository.findAll());
    mv.addObject("medicos", medicoRepository.findAll());

    if (usuarios.size() < 1) {
      mv.addObject("registros", "Sem registros de usuário.");
      mv.addObject("usuarios", null);
    } else {
      mv.addObject("registros", usuarios.size() + " registros em sistema");
      mv.addObject("usuarios", usuarios);
    }

    return mv;
  }

  @PostMapping(path = "/salvar")
  public ModelAndView salvar(Usuario usuario, RoleType roleType, String crm) {
    ModelAndView mv = adminAreaView();

    if (usuarioRepository.existsByLogin(usuario.getLogin())) {
      mv.addObject("erro", "Já existe um usuário registrado com esse login : " + "'" + usuario.getLogin() + "'");

      return mv;
    } else {
      Roles role = roleRepository.findByRoleType(roleType);
      usuario.setRole(role);

      if (roleType.toString().equals("MEDICO")) {
        Medico medico = new Medico();
        medico.usuarioParaMedico(usuario);
        medico.setCrm(crm);
        medicoRepository.save(medico);
      } else {
        usuarioRepository.save(usuario);
      }
      mv.setViewName("redirect:/admin");

      return mv;
    }
  }

  @GetMapping(path = "/excluir/{id}")
  public ModelAndView excluir(@PathVariable Long id) {
    usuarioRepository.deleteById(id);

    ModelAndView mv = adminAreaView();
    mv.setViewName("redirect:/admin");

    return mv;
  }

  @PostMapping(path = "/editar")
  public ModelAndView editarView(Usuario usuario, RoleType roleType, BindingResult result) {
    Roles role = roleRepository.findByRoleType(roleType);
    usuario.setRole(role);

    if (roleType.toString().equals("MEDICO")) {
      Medico medico = new Medico();
      medico.usuarioParaMedico(usuario);
      String crm = medicoRepository.findById(usuario.getId()).get().getCrm();
      medico.setCrm(crm);
      medicoRepository.save(medico);
    } else {
      usuarioRepository.save(usuario);
    }

    ModelAndView mv = adminAreaView();
    mv.setViewName("redirect:/admin");

    return mv;
  }

}
