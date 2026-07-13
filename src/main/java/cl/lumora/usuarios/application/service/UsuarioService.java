package cl.lumora.usuarios.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.lumora.usuarios.domain.model.Usuario;
import cl.lumora.usuarios.domain.port.in.ActualizarUsuarioUseCase;
import cl.lumora.usuarios.domain.port.in.CrearUsuarioUseCase;
import cl.lumora.usuarios.domain.port.in.EliminarUsuarioUseCase;
import cl.lumora.usuarios.domain.port.in.ObtenerUsuarioUseCase;
import cl.lumora.usuarios.domain.port.out.UsuarioRepositoryPort;

@Service
public class UsuarioService implements
        CrearUsuarioUseCase,
        ObtenerUsuarioUseCase,
        ActualizarUsuarioUseCase,
        EliminarUsuarioUseCase {

    private final UsuarioRepositoryPort repository;

    public UsuarioService(UsuarioRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {

        return repository.save(usuario);
    }

    @Override
    public List<Usuario> obtenerTodos() {

        return repository.findAll();
    }

    @Override
    public Usuario obtenerPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public Usuario actualizar(Long id, Usuario usuario) {

        usuario.setIdUsuario(id);

        return repository.save(usuario);
    }

    @Override
    public void eliminar(Long id) {

        repository.deleteById(id);
    }
    
    public Usuario buscarPorCorreo(String correo) {
        return repository.findByCorreo(correo)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado con correo: " + correo));
    }
}