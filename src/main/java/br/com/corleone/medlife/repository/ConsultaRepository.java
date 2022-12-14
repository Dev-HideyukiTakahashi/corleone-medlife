package br.com.corleone.medlife.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.corleone.medlife.model.entities.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

  @Query(value = "SELECT * FROM TB_CONSULTAS  WHERE PACIENTE_ID= :id", nativeQuery = true)
  List<Consulta> findByPacienteId(Long id);

  @Query(nativeQuery = true, value = "SELECT * FROM tb_consultas WHERE (CAST(data AS DATE) = :data)")
  List<Consulta> findByData(Date data);

  @Query(nativeQuery = true, value = "SELECT * FROM tb_consultas WHERE (CAST(data AS DATE) = :data)")
  Page<Consulta> findByData(Date data, Pageable pageable);

  @Query(nativeQuery = true, value = "SELECT * FROM tb_consultas WHERE status = 'ENCAMINHADA' AND medico_id = :id")
  Page<Consulta> findConsultasByMedicoid(Long id, Pageable pageable);
}
