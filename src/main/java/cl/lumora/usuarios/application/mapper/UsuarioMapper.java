package cl.lumora.usuarios.application.mapper;

import cl.lumora.usuarios.domain.model.Rol;
import cl.lumora.usuarios.domain.model.Usuario;
import cl.lumora.usuarios.infrastructure.adapter.out.persistence.entity.RolEntity;
import cl.lumora.usuarios.infrastructure.adapter.out.persistence.entity.UsuarioEntity;

public class UsuarioMapper {

    private UsuarioMapper() {
    }

    public static Usuario toDomain(UsuarioEntity entity) {

        if (entity == null) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setIdUsuario(entity.getIdUsuario());
        usuario.setAzureObjectId(entity.getAzureObjectId());
        usuario.setNombre(entity.getNombre());
        usuario.setCorreo(entity.getCorreo());
        usuario.setTelefono(entity.getTelefono());
        usuario.setActivo(entity.getActivo());
        usuario.setFechaCreacion(entity.getFechaCreacion());
        usuario.setFechaModificacion(entity.getFechaModificacion());

        if (entity.getRol() != null) {

            Rol rol = new Rol();

            rol.setIdRol(entity.getRol().getIdRol());
            rol.setNombre(entity.getRol().getNombre());
            rol.setDescripcion(entity.getRol().getDescripcion());

            usuario.setRol(rol);
        }

        return usuario;
    }

    public static UsuarioEntity toEntity(Usuario usuario) {

        if (usuario == null) {
            return null;
        }

        UsuarioEntity entity = new UsuarioEntity();

        entity.setIdUsuario(usuario.getIdUsuario());
        entity.setAzureObjectId(usuario.getAzureObjectId());
        entity.setNombre(usuario.getNombre());
        entity.setCorreo(usuario.getCorreo());
        entity.setTelefono(usuario.getTelefono());
        entity.setActivo(usuario.getActivo());
        entity.setFechaCreacion(usuario.getFechaCreacion());
        entity.setFechaModificacion(usuario.getFechaModificacion());

        if (usuario.getRol() != null) {

            RolEntity rol = new RolEntity();

            rol.setIdRol(usuario.getRol().getIdRol());
            rol.setNombre(usuario.getRol().getNombre());
            rol.setDescripcion(usuario.getRol().getDescripcion());

            entity.setRol(rol);
        }

        return entity;
    }
}