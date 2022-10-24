package br.com.corleone.medlife.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.corleone.medlife.model.entities.Consulta;
import br.com.corleone.medlife.repository.ConsultaRepository;
import br.com.corleone.medlife.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/medico")
@RequiredArgsConstructor
public class MedicoController {

  private final MedicoRepository medicoRepository;
  private final ConsultaRepository consultaRepository;

  @GetMapping
  public ModelAndView medicoAreaView() {
    ModelAndView mv = new ModelAndView("/auth/medico/area-medico");

    mv.addObject("consultas", consultaRepository.findAll());
    return mv;
  }
}
