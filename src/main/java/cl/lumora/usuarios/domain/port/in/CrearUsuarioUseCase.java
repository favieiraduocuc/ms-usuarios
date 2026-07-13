package cl.lumora.usuarios.domain.port.in;

import cl.lumora.usuarios.domain.model.Usuario;

public interface CrearUsuarioUseCase {

    Usuario crearUsuario(Usuario usuario);

}