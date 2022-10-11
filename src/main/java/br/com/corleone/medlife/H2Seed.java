package br.com.corleone.medlife;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.corleone.medlife.model.entities.Consulta;
import br.com.corleone.medlife.model.entities.Medico;
import br.com.corleone.medlife.model.entities.Paciente;
import br.com.corleone.medlife.model.entities.Roles;
import br.com.corleone.medlife.model.entities.Usuario;
import br.com.corleone.medlife.model.enums.RoleType;
import br.com.corleone.medlife.model.enums.Status;
import br.com.corleone.medlife.repository.ConsultaRepository;
import br.com.corleone.medlife.repository.MedicoRepository;
import br.com.corleone.medlife.repository.PacienteRepository;
import br.com.corleone.medlife.repository.RoleRepository;
import br.com.corleone.medlife.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@Profile("test")
@RequiredArgsConstructor
public class H2Seed implements CommandLineRunner {

  private final MedicoRepository medicoRepository;
  private final UsuarioRepository usuarioRepository;
  private final RoleRepository rolesRepository;
  private final ConsultaRepository consultaRepository;
  private final PacienteRepository pacienteRepository;

  @Override
  @Transactional
  public void run(String... args) throws Exception {

    Roles r1 = new Roles();
    r1.setRoleType(RoleType.ADMIN);
    Roles r2 = new Roles();
    r2.setRoleType(RoleType.MEDICO);
    Roles r3 = new Roles();
    r3.setRoleType(RoleType.ATENDENTE);

    rolesRepository.saveAll(Arrays.asList(r1, r2, r3));

    Medico m1 = new Medico("teste@mail.com", "11-985745412", "2283-2", "medico1", "123", "Dr. Now");
    m1.setRole(r2);
    Medico m2 = new Medico("teste2@mail.com", "11-921012578", "87485-2", "medico2", "123", "Dra. Celestina");
    m2.setRole(r2);

    Usuario u1 = new Usuario("admin", "123", "Administrador", "11-947145854");
    u1.setRole(r1);
    Usuario u2 = new Usuario("usuario2", "123", "Valentina Silva", "11-98574514");
    u2.setRole(r3);

    Consulta c1 = new Consulta();
    c1.setData(LocalDateTime.parse("2022-10-05T23:30:26"));
    c1.setTriagem("Paciente está com febre alta, tosse e dor de garganta.");
    c1.setHistorico("Primeira consulta");
    c1.setStatus(Status.MARCADA);

    Consulta c2 = new Consulta();
    c2.setData(LocalDateTime.parse("2022-10-03T22:30:26"));
    c2.setTriagem("Paciente continua doente");
    c2.setHistorico("Retorno da consulta");
    c2.setStatus(Status.REMARCADA);

    Consulta c3 = new Consulta();
    c3.setData(LocalDateTime.parse("2022-10-22T22:30:26"));
    c3.setTriagem("Paciente não compareceu a consulta.");
    c3.setHistorico("...");
    c3.setStatus(Status.CANCELADA);

    Consulta c4 = new Consulta();
    c4.setData(LocalDateTime.parse("2022-10-13T22:30:26"));
    c4.setTriagem("Paciente cm muita dor na coluna");
    c4.setHistorico("Primeira consulta");
    c4.setStatus(Status.MARCADA);

    Paciente p1 = new Paciente("Maria", "41547485785", "11985744547", "maria@gmail.com",
        "Feminino", 58, LocalDate.parse("1965-11-25"));
    Paciente p2 = new Paciente("João", "74854154782", "11974854147", "joao@gmail.com",
        "Masculino", 28, LocalDate.parse("1994-11-25"));
    Paciente p3 = new Paciente("Lucia", "89585975412", "11936587458", "lucia@gmail.com",
        "Feminino", 38, LocalDate.parse("1984-12-25"));
    Paciente p4 = new Paciente("Ana", "25145748522", "11932521454", "ana@gmail.com",
        "Masculino", 50, LocalDate.parse("1972-05-11"));

    c1.setMedico(m1);
    c2.setMedico(m2);
    c3.setMedico(m1);
    c4.setMedico(m2);

    c1.setPaciente(p1);
    c2.setPaciente(p2);
    c3.setPaciente(p3);
    c4.setPaciente(p4);

    pacienteRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
    medicoRepository.saveAll(Arrays.asList(m1, m2));

    usuarioRepository.saveAll(Arrays.asList(u1, u2));

    consultaRepository.saveAll(Arrays.asList(c1, c2, c3, c4));
  }

}
