package cl.lumora.usuarios.domain.port.out;

import java.util.List;
import java.util.Optional;

import cl.lumora.usuarios.domain.model.Usuario;

public interface UsuarioRepositoryPort {

    Usuario save(Usuario usuario);

    List<Usuario> findAll();

    Optional<Usuario> findById(Long id);

    void deleteById(Long id);

    Optional<Usuario> findByCorreo(String correo);

}