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
                Medico m2 = new Medico("teste2@mail.com", "11-921012578", "87485-2", "medico2", "123",
                                "Dra. Celestina");
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
                c4.setTriagem("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
                c4.setHistorico(
                                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                c4.setStatus(Status.MARCADA);

                Consulta c5 = new Consulta();
                c5.setData(LocalDateTime.parse("2022-10-13T22:30:26"));
                c5.setTriagem("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
                c5.setHistorico(
                                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                c5.setStatus(Status.MARCADA);

                Consulta c6 = new Consulta();
                c6.setData(LocalDateTime.parse("2022-10-13T22:30:26"));
                c6.setTriagem("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
                c6.setHistorico(
                                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                c6.setStatus(Status.MARCADA);

                Consulta c7 = new Consulta();
                c7.setData(LocalDateTime.parse("2022-10-13T22:30:26"));
                c7.setTriagem("Lorem ipsum dolor sit amet, consectetur adipiscing elit Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                c7.setHistorico(
                                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                c7.setStatus(Status.MARCADA);

                Consulta c8 = new Consulta();
                c8.setData(LocalDateTime.parse("2022-10-13T22:30:26"));
                c8.setTriagem("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
                c8.setHistorico(
                                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                c8.setStatus(Status.MARCADA);
                Consulta c9 = new Consulta();
                c9.setData(LocalDateTime.parse("2022-10-13T22:30:26"));
                c9.setTriagem("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
                c9.setHistorico(
                                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                c9.setStatus(Status.MARCADA);

                Consulta c10 = new Consulta();
                c10.setData(LocalDateTime.parse("2022-10-13T22:30:26"));
                c10.setTriagem("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
                c10.setHistorico(
                                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                c10.setStatus(Status.MARCADA);

                Consulta c11 = new Consulta();
                c11.setData(LocalDateTime.parse("2022-10-13T22:30:26"));
                c11.setTriagem("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
                c11.setHistorico(
                                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                c11.setStatus(Status.MARCADA);

                Consulta c12 = new Consulta();
                c12.setData(LocalDateTime.parse("2022-10-13T22:30:26"));
                c12.setTriagem("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
                c12.setHistorico(
                                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                c12.setStatus(Status.MARCADA);
                Consulta c13 = new Consulta();
                c13.setData(LocalDateTime.parse("2022-10-13T22:30:26"));
                c13.setTriagem("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
                c13.setHistorico(
                                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                c13.setStatus(Status.MARCADA);

                Consulta c14 = new Consulta();
                c14.setData(LocalDateTime.parse("2022-10-13T22:30:26"));
                c14.setTriagem("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
                c14.setHistorico(
                                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                c14.setStatus(Status.MARCADA);

                Consulta c15 = new Consulta();
                c15.setData(LocalDateTime.parse("2022-10-13T22:30:26"));
                c15.setTriagem("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
                c15.setHistorico(
                                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                c15.setStatus(Status.MARCADA);

                Consulta c16 = new Consulta();
                c16.setData(LocalDateTime.parse("2022-10-13T22:30:26"));
                c16.setTriagem("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
                c16.setHistorico(
                                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                c16.setStatus(Status.REMARCADA);

                Consulta c17 = new Consulta();
                c17.setData(LocalDateTime.parse("2022-10-13T22:30:26"));
                c17.setTriagem("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
                c17.setHistorico(
                                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                c17.setStatus(Status.REMARCADA);

                Consulta c18 = new Consulta();
                c18.setData(LocalDateTime.parse("2022-10-13T22:30:26"));
                c18.setTriagem("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
                c18.setHistorico(
                                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                c18.setStatus(Status.REMARCADA);

                Consulta c19 = new Consulta();
                c19.setData(LocalDateTime.parse("2022-10-13T22:30:26"));
                c19.setTriagem("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
                c19.setHistorico(
                                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                c19.setStatus(Status.REMARCADA);

                Paciente p1 = new Paciente("Maria do Socorro", "848.377.750-98", "11985744547", "maria@gmail.com",
                                "FEMININO",
                                58,
                                LocalDate.parse("1965-11-25"));
                p1.setRua("Duis aute irure dolor in reprehenderit");
                p1.setNumero("111");
                p1.setBairro("voluptate velit");
                p1.setCidade("cillum dolore");
                Paciente p2 = new Paciente("João da Silva Mendes", "627.466.460-21", "11974854147", "joao@gmail.com",
                                "MASCULINO",
                                28,
                                LocalDate.parse("1994-11-25"));
                p2.setRua("Duis aute irure dolor in reprehenderit");
                p2.setNumero("111");
                p2.setBairro("voluptate velit");
                p2.setCidade("cillum dolore");
                Paciente p3 = new Paciente("Lucia Meneguel", "651.692.180-60", "11936587458", "lucia@gmail.com",
                                "FEMININO", 38,
                                LocalDate.parse("1984-12-25"));
                p3.setRua("Duis aute irure dolor in reprehenderit");
                p3.setNumero("111");
                p3.setBairro("voluptate velit");
                p3.setCidade("cillum dolore");
                Paciente p4 = new Paciente("Ana Paula Padrão", "877.496.680-47", "11932521454", "ana@gmail.com",
                                "FEMININO", 50,
                                LocalDate.parse("1972-05-11"));
                p4.setRua("Duis aute irure dolor in reprehenderit");
                p4.setNumero("111");
                p4.setBairro("voluptate velit");
                p4.setCidade("cillum dolore");
                Paciente p5 = new Paciente("Marcelo Ferreira", "617.258.260-70", "11932522514", "marcelo@gmail.com",
                                "MASCULINO",
                                42,
                                LocalDate.parse("1980-08-11"));
                p5.setRua("Duis aute irure dolor in reprehenderit");
                p5.setNumero("111");
                p5.setBairro("voluptate velit");
                p5.setCidade("cillum dolore");
                Paciente p6 = new Paciente("Joycelene Farias Dias", "077.288.290-84", "11985745841",
                                "joycelene@gmail.com",
                                "FEMININO", 33,
                                LocalDate.parse("1989-02-02"));
                p6.setRua("Duis aute irure dolor in reprehenderit");
                p6.setNumero("111");
                p6.setBairro("voluptate velit");
                p6.setCidade("cillum dolore");
                Paciente p7 = new Paciente("Rogerio Dias Gonçalves", "202.601.270-94", "11985412523",
                                "rogerio@gmail.com",
                                "MASCULINO", 77,
                                LocalDate.parse("1942-01-28"));
                p7.setRua("Duis aute irure dolor in reprehenderit");
                p7.setNumero("111");
                p7.setBairro("voluptate velit");
                p7.setCidade("cillum dolore");

                Paciente p8 = new Paciente("Jucelene da Silva Pereira", "912.789.140-21", "11985474585",
                                "jucelene@gmail.com",
                                "FEMININO", 20,
                                LocalDate.parse("2002-11-09"));
                p8.setRua("Duis aute irure dolor in reprehenderit");
                p8.setNumero("111");
                p8.setBairro("voluptate velit");
                p8.setCidade("cillum dolore");
                Paciente p9 = new Paciente("Celestina Nascimento", "419.508.100-97", "11985412522",
                                "celestina@gmail.com",
                                "FEMININO", 30,
                                LocalDate.parse("1992-09-30"));
                p9.setRua("Duis aute irure dolor in reprehenderit");
                p9.setNumero("111");
                p9.setBairro("voluptate velit");
                p9.setCidade("cillum dolore");
                Paciente p10 = new Paciente("Mario de Andrade", "405.263.170-60", "11985541252", "mario@gmail.com",
                                "MASCULINO",
                                50,
                                LocalDate.parse("1972-05-11"));
                p10.setRua("Duis aute irure dolor in reprehenderit");
                p10.setNumero("111");
                p10.setBairro("voluptate velit");
                p10.setCidade("cillum dolore");
                Paciente p11 = new Paciente("Bruno Dias", "869.627.770-86", "11985412522", "bruno@gmail.com",
                                "MASCULINO", 30,
                                LocalDate.parse("1992-02-11"));
                p11.setRua("Duis aute irure dolor in reprehenderit");
                p11.setNumero("111");
                p11.setBairro("voluptate velit");
                p11.setCidade("cillum dolore");
                Paciente p12 = new Paciente("Maria do Rosario", "565.547.790-43", "11985415256", "maria@gmail.com",
                                "FEMININO",
                                60,
                                LocalDate.parse("1962-09-22"));
                p12.setRua("Duis aute irure dolor in reprehenderit");
                p12.setNumero("111");
                p12.setBairro("voluptate velit");
                p12.setCidade("cillum dolore");
                Paciente p13 = new Paciente("Maria da Silva Pereira", "988.574.700-16", "11984125852",
                                "maria@gmail.com",
                                "FEMININO", 32,
                                LocalDate.parse("1990-07-08"));
                p13.setRua("Duis aute irure dolor in reprehenderit");
                p13.setNumero("111");
                p13.setBairro("voluptate velit");
                p13.setCidade("cillum dolore");
                Paciente p14 = new Paciente("Maria das Dores", "599.459.010-01", "11974512585", "maria@gmail.com",
                                "FEMININO",
                                82,
                                LocalDate.parse("1940-01-01"));
                p14.setRua("Duis aute irure dolor in reprehenderit");
                p14.setNumero("111");
                p14.setBairro("voluptate velit");
                p14.setCidade("cillum dolore");
                Paciente p15 = new Paciente("João das Dores", "119.172.370-49", "11985415424", "joao@gmail.com",
                                "MASCULINO",
                                82,
                                LocalDate.parse("1940-06-02"));
                p15.setRua("Duis aute irure dolor in reprehenderit");
                p15.setNumero("111");
                p15.setBairro("voluptate velit");
                p15.setCidade("cillum dolore");

                c1.setMedico(m1);
                c2.setMedico(m2);
                c3.setMedico(m1);
                c4.setMedico(m2);
                c5.setMedico(m1);
                c6.setMedico(m2);
                c7.setMedico(m1);
                c8.setMedico(m2);
                c9.setMedico(m1);
                c10.setMedico(m2);
                c11.setMedico(m1);
                c12.setMedico(m2);
                c13.setMedico(m2);
                c14.setMedico(m1);
                c15.setMedico(m2);
                c16.setMedico(m1);
                c17.setMedico(m1);
                c18.setMedico(m1);
                c19.setMedico(m1);

                c1.setPaciente(p1);
                c2.setPaciente(p2);
                c3.setPaciente(p3);
                c4.setPaciente(p4);
                c5.setPaciente(p5);
                c6.setPaciente(p6);
                c7.setPaciente(p7);
                c8.setPaciente(p8);

                c9.setPaciente(p9);
                c10.setPaciente(p10);
                c11.setPaciente(p11);
                c12.setPaciente(p12);
                c13.setPaciente(p13);
                c14.setPaciente(p14);
                c15.setPaciente(p15);
                c16.setPaciente(p4);
                c17.setPaciente(p4);
                c18.setPaciente(p4);
                c19.setPaciente(p4);

                pacienteRepository.saveAll(
                                Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15));
                medicoRepository.saveAll(Arrays.asList(m1, m2));

                usuarioRepository.saveAll(Arrays.asList(u1, u2));

                consultaRepository.saveAll(
                                Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16,
                                                c17, c18, c19));
        }

}
