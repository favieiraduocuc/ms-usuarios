package cl.lumora.usuarios.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.lumora.usuarios.domain.model.Rol;
import cl.lumora.usuarios.domain.model.Usuario;
import cl.lumora.usuarios.domain.port.out.UsuarioRepositoryPort;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepositoryPort repository;

    private UsuarioService service;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        service = new UsuarioService(repository);
        usuario = crearUsuario(1L, "test@lumora.cl");
    }

    @Test
    void deberiaCrearUsuario() {
        when(repository.save(usuario)).thenReturn(usuario);

        Usuario resultado = service.crearUsuario(usuario);

        assertSame(usuario, resultado);
        verify(repository).save(usuario);
    }

    @Test
    void deberiaListarUsuarios() {
        when(repository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> resultado = service.obtenerTodos();

        assertEquals(1, resultado.size());
        assertEquals("test@lumora.cl", resultado.get(0).getCorreo());
    }

    @Test
    void deberiaObtenerUsuarioPorId() {
        when(repository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario resultado = service.obtenerPorId(1L);

        assertEquals(1L, resultado.getIdUsuario());
    }

    @Test
    void deberiaLanzarExcepcionCuandoUsuarioNoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.obtenerPorId(99L));

        assertEquals("Usuario no encontrado", ex.getMessage());
    }

    @Test
    void deberiaActualizarUsuarioAsignandoId() {
        Usuario entrada = crearUsuario(null, "actualizado@lumora.cl");
        when(repository.save(entrada)).thenAnswer(invocation -> invocation.getArgument(0));

        Usuario resultado = service.actualizar(7L, entrada);

        assertEquals(7L, resultado.getIdUsuario());
        verify(repository).save(entrada);
    }

    @Test
    void deberiaEliminarUsuario() {
        service.eliminar(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void deberiaBuscarUsuarioPorCorreo() {
        when(repository.findByCorreo("test@lumora.cl")).thenReturn(Optional.of(usuario));

        Usuario resultado = service.buscarPorCorreo("test@lumora.cl");

        assertEquals("Usuario Prueba", resultado.getNombre());
    }

    @Test
    void deberiaLanzarExcepcionCuandoCorreoNoExiste() {
        when(repository.findByCorreo("noexiste@lumora.cl")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.buscarPorCorreo("noexiste@lumora.cl"));

        assertTrue(ex.getMessage().contains("noexiste@lumora.cl"));
    }

    private Usuario crearUsuario(Long id, String correo) {
        Usuario u = new Usuario();
        u.setIdUsuario(id);
        u.setAzureObjectId("azure-test");
        u.setNombre("Usuario Prueba");
        u.setCorreo(correo);
        u.setTelefono("+56911111111");
        u.setActivo(true);
        u.setRol(new Rol(1L, "ADMIN", "Administrador"));
        return u;
    }
}
