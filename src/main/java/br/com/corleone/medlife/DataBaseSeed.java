package br.com.corleone.medlife;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.corleone.medlife.model.entities.Medico;
import br.com.corleone.medlife.model.entities.Usuario;
import br.com.corleone.medlife.repository.MedicoRepository;
import br.com.corleone.medlife.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@Profile("test")
@RequiredArgsConstructor
public class DataBaseSeed implements CommandLineRunner {

  private final MedicoRepository medicoRepository;
  private final UsuarioRepository usuarioRepository;

  @Override
  public void run(String... args) throws Exception {

    Medico m1 = new Medico("teste@mail.com", "11-985745412", 123, "medico1", "123", "Medico Um");
    Medico m2 = new Medico("teste2@mail.com", "11-985745412", 123, "medico2", "123", "Medico Dois");
    Medico m3 = new Medico("teste3@mail.com", "11-985745412", 123, "medico3", "123", "Medico Três");

    Usuario u1 = new Usuario("usuario1", "123", "Usuario Um", "11-98574514");
    Usuario u2 = new Usuario("usuario2", "123", "Usuario Dois", "11-98574514");
    medicoRepository.saveAll(Arrays.asList(m1, m2, m3));
    usuarioRepository.saveAll(Arrays.asList(m1, m2, m3, u1, u2));
  }

}
