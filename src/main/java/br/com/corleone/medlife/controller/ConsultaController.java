package br.com.corleone.medlife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.corleone.medlife.repository.ConsultaRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(path = "/consultas")
@RequiredArgsConstructor
public class ConsultaController {

  private final ConsultaRepository consultaRepository;

  @GetMapping
  public ModelAndView consultasView() {
    ModelAndView mv = new ModelAndView("/consultas/lista-consultas");

    mv.addObject("consultas", consultaRepository.findAll());
    return mv;
  }
}
