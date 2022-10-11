package br.com.corleone.medlife.controller;

import java.util.List;

import javax.validation.Valid;

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
    return view();
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

    String status, msg;

    if (usuarioRepository.existsByUsername(usuario.getUsername())) {
      status = "erro";
      msg = "Já existe um usuário registrado com esse username : " + "'" + usuario.getUsername() + "'";
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
      status = "sucesso";
      msg = "Cadastro de '" + usuario.getNome() + "' realizado com sucesso!";
    }

    ModelAndView mv = view();
    mv.addObject(status, msg);
    return mv;
  }

  @GetMapping(path = "/excluir/{id}")
  public ModelAndView excluir(@PathVariable Long id) {

    usuarioRepository.deleteById(id);
    ModelAndView mv = view();
    mv.addObject("sucesso", "Usuário deletado com sucesso!");

    return mv;
  }

  @PostMapping(path = "/editar")
  public ModelAndView editarView(@Valid Usuario usuario, RoleType roleType, BindingResult result) {

    if (result.hasErrors()) {
      ModelAndView mv = view();
      mv.addObject("erro", "Todos os campos devem ser preenchidos.");
    }

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

    ModelAndView mv = view();
    mv.addObject("sucesso", "Cadastro de '" + usuario.getNome() + "' atualizado com sucesso!");
    return mv;
  }

  private ModelAndView view() {
    ModelAndView mv = new ModelAndView("/auth/admin/area-admin");
    mv.addObject("roles", roleRepository.findAll());
    mv.addObject("usuarios", usuarioRepository.findAll());
    mv.addObject("medicos", medicoRepository.findAll());
    return mv;
  }

}
