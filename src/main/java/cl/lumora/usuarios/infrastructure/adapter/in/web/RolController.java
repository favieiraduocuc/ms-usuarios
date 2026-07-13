package cl.lumora.usuarios.infrastructure.adapter.in.web;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import cl.lumora.usuarios.domain.model.Rol;
import cl.lumora.usuarios.domain.port.out.RolRepositoryPort;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolRepositoryPort repository;

    public RolController(RolRepositoryPort repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Rol> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Rol obtener(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Rol no encontrado"));
    }
}