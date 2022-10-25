package br.com.corleone.medlife.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.corleone.medlife.model.entities.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

  Medico findByNome(String nome);

  Medico findByCrm(String crm);

}
