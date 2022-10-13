package br.com.corleone.medlife.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.corleone.medlife.model.entities.Consulta;
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

  @GetMapping(path = "/buscar")
  public ModelAndView buscarPorId(@RequestParam(name = "id") Long id) {
    ModelAndView mv = new ModelAndView("/consultas/lista-consultas");

    Optional<Consulta> optional = consultaRepository.findById(id);
    if (optional.isPresent()) {
      mv.addObject("consultas", optional.get());
      mv.addObject("registros", "Consulta localizada.");
    } else {
      mv.addObject("registros", "Consulta " + id + " não existe.");
      mv.addObject("consultas", null);
    }
    return mv;

  }
}
