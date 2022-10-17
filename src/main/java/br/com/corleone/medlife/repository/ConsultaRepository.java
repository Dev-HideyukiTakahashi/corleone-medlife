package br.com.corleone.medlife.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.corleone.medlife.model.entities.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

  @Query(value = "SELECT * FROM TB_CONSULTAS  WHERE PACIENTE_ID= :id", nativeQuery = true)
  List<Consulta> findByPacienteId(Long id);
}
