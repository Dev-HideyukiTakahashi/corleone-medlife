package br.com.corleone.medlife.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.corleone.medlife.model.entities.Consulta;
import br.com.corleone.medlife.repository.ConsultaRepository;
import br.com.corleone.medlife.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

  private final MedicoRepository medicoRepository;
  private final ConsultaRepository consultaRepository;

  @GetMapping(path = { "/", "/login" })
  public ModelAndView login(Boolean error) {
    ModelAndView mv = new ModelAndView("/login");
    if (error != null && error) {
      mv.addObject("msg", "Usuário ou senha inválido!");
    }

    return mv;
  }

  @GetMapping(path = "/home")
  public ModelAndView home(@PageableDefault(size = 5, sort = "id") Pageable pageable) {
    ModelAndView mv = new ModelAndView("/auth/home");

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date dataHoje = new Date(System.currentTimeMillis());
    String data = sdf.format(dataHoje);

    Page<Consulta> pageConsulta = consultaRepository.findByData(Date.valueOf(data), pageable);

    mv.addObject("consultas", pageConsulta);
    mv.addObject("lista", true);
    return mv;
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
