package cl.lumora.usuarios.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.lumora.usuarios.infrastructure.adapter.out.persistence.entity.RolEntity;

public interface RolJpaRepository extends JpaRepository<RolEntity, Long> {

}