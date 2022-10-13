package br.com.corleone.medlife.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.corleone.medlife.model.entities.Paciente;
import br.com.corleone.medlife.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

  private final PacienteRepository pacienteRepository;

  @GetMapping
  public ModelAndView pacientesView(@PageableDefault(size = 7, sort = "nome") Pageable pageable) {
    ModelAndView mv = new ModelAndView("/pacientes/lista-pacientes");
    Page<Paciente> pagePaciente = pacienteRepository.findAll(pageable);

    mv.addObject("pacientes", pagePaciente);
    mv.addObject("lista", true);

    return mv;
  }

  @GetMapping(path = "/buscar")
  public ModelAndView buscarPorNome(@RequestParam(name = "nomeBusca") String nomeBusca,
      @PageableDefault(size = 7, sort = "nome") Pageable pageable) {
    ModelAndView mv = new ModelAndView("/pacientes/lista-pacientes");
    Page<Paciente> pagePaciente = pacienteRepository.findAllByNomeContainsIgnoreCase(nomeBusca, pageable);

    if (!pagePaciente.hasContent()) {
      mv.addObject("pacientes", null);
      mv.addObject("registros", "Sem registros de pacientes.");
      mv.addObject("lista", false);
    } else {
      mv.addObject("registros", pagePaciente.getTotalElements() + " registros em sistema");
      mv.addObject("nomeBusca", nomeBusca);
      mv.addObject("pacientes", pagePaciente);
      mv.addObject("lista", true);
    }

    return mv;
  }

  @GetMapping(path = "/salvar")
  public ModelAndView salvar() {
    ModelAndView mv = new ModelAndView("/pacientes/form-pacientes");
    mv.addObject("paciente", new Paciente());

    return mv;
  }

  @GetMapping(path = "/editar/{id}")
  public ModelAndView editar(@PathVariable Long id) {
    ModelAndView mv = new ModelAndView("/pacientes/form-pacientes");
    Paciente paciente = pacienteRepository.findById(id).get();
    mv.addObject("paciente", paciente);

    return mv;
  }

  @PostMapping(path = "/salvar")
  public ModelAndView salvar(@Valid Paciente paciente, BindingResult result) {
    ModelAndView mv = new ModelAndView("/pacientes/form-pacientes");

    if (result.hasErrors()) {
      return mv;
    }

    if (pacienteRepository.existsByCpf(paciente.getCpf()) && paciente.getId() == null) {
      mv.addObject("erro", "Já existe um usuário com esse CPF");
      return mv;
    } else {
      mv.setViewName("redirect:/pacientes");
      pacienteRepository.save(paciente);
      return mv;
    }
  }

}
