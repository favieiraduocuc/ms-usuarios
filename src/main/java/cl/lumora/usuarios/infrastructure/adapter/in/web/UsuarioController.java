package cl.lumora.usuarios.infrastructure.adapter.in.web;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import cl.lumora.usuarios.application.service.UsuarioService;
import cl.lumora.usuarios.domain.model.Usuario;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Usuario> listar() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Usuario obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @GetMapping("/correo/{correo}")
    public Usuario obtenerPorCorreo(@PathVariable String correo) {
        return service.buscarPorCorreo(correo);
    }

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return service.crearUsuario(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizar(
            @PathVariable Long id,
            @RequestBody Usuario usuario) {

        return service.actualizar(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}