package cl.lumora.usuarios.infrastructure.adapter.out.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import cl.lumora.usuarios.application.mapper.UsuarioMapper;
import cl.lumora.usuarios.domain.model.Rol;
import cl.lumora.usuarios.domain.port.out.RolRepositoryPort;
import cl.lumora.usuarios.infrastructure.adapter.out.persistence.repository.RolJpaRepository;

@Component
public class RolPersistenceAdapter implements RolRepositoryPort {

    private final RolJpaRepository repository;

    public RolPersistenceAdapter(RolJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Rol> findAll() {

        return repository.findAll()
                .stream()
                .map(entity -> {
                    Rol rol = new Rol();
                    rol.setIdRol(entity.getIdRol());
                    rol.setNombre(entity.getNombre());
                    rol.setDescripcion(entity.getDescripcion());
                    return rol;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Rol> findById(Long id) {

        return repository.findById(id)
                .map(entity -> {
                    Rol rol = new Rol();
                    rol.setIdRol(entity.getIdRol());
                    rol.setNombre(entity.getNombre());
                    rol.setDescripcion(entity.getDescripcion());
                    return rol;
                });
    }
}