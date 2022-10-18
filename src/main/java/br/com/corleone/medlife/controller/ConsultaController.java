package br.com.corleone.medlife.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public ModelAndView consultasView(@PageableDefault(size = 7, sort = "id") Pageable pageable) {
    ModelAndView mv = new ModelAndView("/auth/usuario/consultas/lista-consultas");
    Page<Consulta> pageConsulta = consultaRepository.findAll(pageable);

    mv.addObject("consultas", pageConsulta);
    mv.addObject("lista", true);

    return mv;
  }

  @GetMapping(path = "/buscar")
  public ModelAndView buscarPorId(@RequestParam(name = "id") Long id) {
    ModelAndView mv = new ModelAndView("/auth/usuario/consultas/lista-consultas");
    Optional<Consulta> optional = consultaRepository.findById(id);

    if (optional.isPresent()) {
      mv.addObject("consultas", optional.get());
      mv.addObject("registros", "Consulta localizada.");
    } else {
      mv.addObject("registros", "Consulta " + id + " não existe.");
      mv.addObject("consultas", null);
    }
    mv.addObject("lista", false);

    return mv;

  }

  @GetMapping(path = "/detalhes/{id}")
  public ModelAndView detalhes(@PathVariable Long id) {
    ModelAndView mv = new ModelAndView("/auth/usuario/consultas/detalhes-consulta");
    Consulta consulta = consultaRepository.findById(id).get();
    mv.addObject("consulta", consulta);

    return mv;
  }
}
