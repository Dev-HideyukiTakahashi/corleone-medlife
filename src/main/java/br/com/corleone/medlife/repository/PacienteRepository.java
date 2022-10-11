package br.com.corleone.medlife.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.corleone.medlife.model.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

  List<Paciente> findByNomeContainsIgnoreCase(String nome);

}
