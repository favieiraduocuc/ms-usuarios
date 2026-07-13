package cl.lumora.usuarios.domain.port.in;

import cl.lumora.usuarios.domain.model.Usuario;

public interface ActualizarUsuarioUseCase {

    Usuario actualizar(Long id, Usuario usuario);

}