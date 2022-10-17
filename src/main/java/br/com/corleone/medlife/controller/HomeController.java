package br.com.corleone.medlife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.corleone.medlife.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

  private final MedicoRepository medicoRepository;

  @GetMapping(path = "/home")
  public String home() {
    return "home";
  }

  @GetMapping(path = "/contatos")
  public ModelAndView contatos() {
    ModelAndView mv = new ModelAndView("/contatos");
    mv.addObject("medicos", medicoRepository.findAll());
    return mv;
  }
}
