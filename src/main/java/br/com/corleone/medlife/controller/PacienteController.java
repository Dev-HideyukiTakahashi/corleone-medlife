package br.com.corleone.medlife.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
  public ModelAndView pacientesView() {
    ModelAndView mv = new ModelAndView("/pacientes/lista-pacientes");
    mv.addObject("pacientes", pacienteRepository.findAll());
    return mv;

  }

  @GetMapping(path = "/buscar")
  public ModelAndView buscarPorNome(@RequestParam(name = "nome") String nome) {
    List<Paciente> pacientes = pacienteRepository.findByNomeContainsIgnoreCase(nome);
    ModelAndView mv = new ModelAndView("/pacientes/lista-pacientes");
    if (pacientes.size() < 1) {
      mv.addObject("pacientes", null);
      mv.addObject("registros", "Sem registros de pacientes.");
    } else {
      mv.addObject("registros", pacientes.size() + " registros em sistema");
      mv.addObject("pacientes", pacientes);
    }

    return mv;
  }

  @GetMapping(path = "/salvar")
  public ModelAndView salvar() {
    ModelAndView mv = new ModelAndView("/pacientes/form-pacientes");
    mv.addObject("paciente", new Paciente());
    return mv;

  }

  @PostMapping(path = "/salvar")
  public ModelAndView salvar(Paciente paciente) {
    ModelAndView mv = new ModelAndView("/pacientes/form-pacientes");

    if (pacienteRepository.existsByCpf(paciente.getCpf())) {
      mv.addObject("erro", "Já existe um usuário com esse CPF");
      return mv;
    } else {

      pacienteRepository.save(paciente);
      mv.setViewName("redirect:/pacientes");
      return mv;
    }

  }
}
