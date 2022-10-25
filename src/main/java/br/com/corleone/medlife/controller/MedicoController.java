package br.com.corleone.medlife.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
  public ModelAndView medicoAreaView(@PageableDefault(size = 7, sort = "id") Pageable pageable) {
    ModelAndView mv = new ModelAndView("/auth/medico/area-medico");
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    Page<Consulta> pageConsulta = consultaRepository.findConsultasByMedicoid(
        medicoRepository.findByLogin(username).getId(), pageable);

    mv.addObject("consultas", pageConsulta);
    return mv;
  }
}
