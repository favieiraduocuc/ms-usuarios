package cl.lumora.usuarios.domain.port.in;

import java.util.List;

import cl.lumora.usuarios.domain.model.Usuario;

public interface ObtenerUsuarioUseCase {

    List<Usuario> obtenerTodos();

    Usuario obtenerPorId(Long id);

}