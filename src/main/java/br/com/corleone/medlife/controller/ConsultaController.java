package br.com.corleone.medlife.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.corleone.medlife.model.entities.Consulta;
import br.com.corleone.medlife.model.enums.Status;
import br.com.corleone.medlife.repository.ConsultaRepository;
import br.com.corleone.medlife.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(path = "/consultas")
@RequiredArgsConstructor
public class ConsultaController {

  private final ConsultaRepository consultaRepository;
  private final MedicoRepository medicoRepository;

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

  @GetMapping(path = "/editar/{id}")
  public ModelAndView editar(@PathVariable Long id) {
    ModelAndView mv = new ModelAndView("/auth/usuario/consultas/editar-consulta");
    Consulta consulta = consultaRepository.findById(id).get();

    mv.addObject("consulta", consulta);
    mv.addObject("medicos", medicoRepository.findAll());
    mv.addObject("status", Arrays.asList(Status.values()));

    return mv;
  }

  @PostMapping(path = "/salvar")
  public ModelAndView salvar(Consulta consulta,
      String novaData, String novaHora) {

    if (!novaData.isEmpty() && !novaHora.isEmpty()) {
      String updateData = novaData + "T" + novaHora + ":00";
      consulta.setData((LocalDateTime.parse(updateData)));
      consulta.setStatus(Status.REMARCADA);
    }

    consulta.setMedico(medicoRepository.findByNome(consulta.getMedico().getNome()));
    consultaRepository.save(consulta);

    ModelAndView mv = new ModelAndView("redirect:/consultas");
    return mv;
  }
}
