package cl.lumora.usuarios.infrastructure.adapter.in.web;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import cl.lumora.usuarios.domain.model.Rol;
import cl.lumora.usuarios.domain.port.out.RolRepositoryPort;
import cl.lumora.usuarios.infrastructure.exception.GlobalExceptionHandler;

class RolControllerTest {

    private RolRepositoryPort repository;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        repository = mock(RolRepositoryPort.class);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new RolController(repository))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void deberiaListarRoles() throws Exception {
        when(repository.findAll()).thenReturn(List.of(new Rol(1L, "ADMIN", "Administrador")));

        mockMvc.perform(get("/api/roles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("ADMIN"));
    }

    @Test
    void deberiaObtenerRolPorId() throws Exception {
        when(repository.findById(1L)).thenReturn(Optional.of(new Rol(1L, "ADMIN", "Administrador")));

        mockMvc.perform(get("/api/roles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idRol").value(1));
    }

    @Test
    void deberiaRetornarNotFoundCuandoRolNoExiste() throws Exception {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/roles/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Rol no encontrado"));
    }
}
