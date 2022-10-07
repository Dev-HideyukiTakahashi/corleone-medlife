package br.com.corleone.medlife.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.corleone.medlife.model.entities.Roles;
import br.com.corleone.medlife.model.enums.RoleType;

public interface RoleRepository extends JpaRepository<Roles, Long> {

  Roles findByRoleType(RoleType roleType);

}
