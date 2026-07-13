package cl.lumora.usuarios.infrastructure.adapter.in.web;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.lumora.usuarios.application.service.UsuarioService;
import cl.lumora.usuarios.domain.model.Rol;
import cl.lumora.usuarios.domain.model.Usuario;
import cl.lumora.usuarios.infrastructure.exception.GlobalExceptionHandler;

class UsuarioControllerTest {

    private UsuarioService service;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        service = mock(UsuarioService.class);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new UsuarioController(service))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
        usuario = crearUsuario();
    }

    @Test
    void deberiaListarUsuarios() throws Exception {
        when(service.obtenerTodos()).thenReturn(List.of(usuario));

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].correo").value("test@lumora.cl"));
    }

    @Test
    void deberiaObtenerUsuarioPorId() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUsuario").value(1));
    }

    @Test
    void deberiaObtenerUsuarioPorCorreo() throws Exception {
        when(service.buscarPorCorreo("test@lumora.cl")).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/correo/test@lumora.cl"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Usuario Prueba"));
    }

    @Test
    void deberiaCrearUsuario() throws Exception {
        when(service.crearUsuario(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correo").value("test@lumora.cl"));
    }

    @Test
    void deberiaActualizarUsuario() throws Exception {
        when(service.actualizar(eq(1L), any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(put("/api/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUsuario").value(1));
    }

    @Test
    void deberiaEliminarUsuario() throws Exception {
        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isOk());

        verify(service).eliminar(1L);
    }

    @Test
    void deberiaRetornarNotFoundCuandoServicioLanzaExcepcion() throws Exception {
        when(service.obtenerPorId(99L)).thenThrow(new RuntimeException("Usuario no encontrado"));

        mockMvc.perform(get("/api/usuarios/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Usuario no encontrado"));
    }

    private Usuario crearUsuario() {
        Usuario u = new Usuario();
        u.setIdUsuario(1L);
        u.setAzureObjectId("azure-test");
        u.setNombre("Usuario Prueba");
        u.setCorreo("test@lumora.cl");
        u.setActivo(true);
        u.setRol(new Rol(1L, "ADMIN", "Administrador"));
        return u;
    }
}
