package br.com.corleone.medlife.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.corleone.medlife.model.entities.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
