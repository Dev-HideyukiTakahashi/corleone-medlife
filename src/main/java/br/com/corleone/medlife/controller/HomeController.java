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

  @GetMapping(path = { "/", "/login" })
  public ModelAndView login(Boolean error) {
    ModelAndView mv = new ModelAndView("/login");
    if (error != null && error) {
      mv.addObject("msg", "Usuário ou senha inválido!");
    }

    return mv;
  }

  @GetMapping(path = "/home")
  public String home() {
    return "/auth/home";
  }

  @GetMapping(path = "/contatos")
  public ModelAndView contatos() {
    ModelAndView mv = new ModelAndView("/auth/usuario/contatos");
    mv.addObject("medicos", medicoRepository.findAll());
    return mv;
  }

  @GetMapping(path = "/negado")
  public ModelAndView acessoNegado() {
    ModelAndView mv = new ModelAndView("/auth-acesso-negado");
    mv.addObject("medicos", medicoRepository.findAll());
    return mv;
  }

}
