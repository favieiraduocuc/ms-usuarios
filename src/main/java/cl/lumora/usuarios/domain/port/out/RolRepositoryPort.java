package cl.lumora.usuarios.domain.port.out;

import java.util.List;
import java.util.Optional;

import cl.lumora.usuarios.domain.model.Rol;

public interface RolRepositoryPort {

    List<Rol> findAll();

    Optional<Rol> findById(Long id);

}