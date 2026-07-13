package cl.lumora.usuarios.infrastructure.adapter.out.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import cl.lumora.usuarios.application.mapper.UsuarioMapper;
import cl.lumora.usuarios.domain.model.Usuario;
import cl.lumora.usuarios.domain.port.out.UsuarioRepositoryPort;
import cl.lumora.usuarios.infrastructure.adapter.out.persistence.repository.UsuarioJpaRepository;

@Component
public class UsuarioPersistenceAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository repository;

    public UsuarioPersistenceAdapter(UsuarioJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario save(Usuario usuario) {

        return UsuarioMapper.toDomain(
                repository.save(
                        UsuarioMapper.toEntity(usuario)));
    }

    @Override
    public List<Usuario> findAll() {

        return repository.findAll()
                .stream()
                .map(UsuarioMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Usuario> findById(Long id) {

        return repository.findById(id)
                .map(UsuarioMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {

        repository.deleteById(id);
    }

    @Override
    public Optional<Usuario> findByCorreo(String correo) {

        return repository.findByCorreo(correo)
                .map(UsuarioMapper::toDomain);
    }
}