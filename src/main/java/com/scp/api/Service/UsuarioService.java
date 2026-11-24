package com.scp.api.Service;

import com.scp.api.DTO.UsuarioDTO;
import com.scp.api.DTO.UsuarioRequestDTO;
import com.scp.api.Model.Usuario;
import com.scp.api.Repository.RepositoryUsuario;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {
    private final RepositoryUsuario usuarioRepository;

    public UsuarioService(RepositoryUsuario usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> obtenerTodos() {
        return usuarioRepository.findAll().stream().map(this::convertirADTO).toList();
    }

    public UsuarioDTO buscarPorID(Integer id) {
        return convertirADTO(buscarEntidad(id));
    }

    public UsuarioDTO guardar(UsuarioRequestDTO datos) {
        Usuario usuario = new Usuario();
        aplicarDatos(usuario, datos);
        return convertirADTO(usuarioRepository.save(usuario));
    }

    public UsuarioDTO actualizar(Integer id, UsuarioRequestDTO datos) {
        Usuario usuario = buscarEntidad(id);
        aplicarDatos(usuario, datos);
        return convertirADTO(usuarioRepository.save(usuario));
    }

    public void eliminar(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw noEncontrado(id);
        }
        usuarioRepository.deleteById(id);
    }

    private Usuario buscarEntidad(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(() -> noEncontrado(id));
    }

    private ResponseStatusException noEncontrado(Integer id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario con ID " + id + " no existe");
    }

    private void aplicarDatos(Usuario usuario, UsuarioRequestDTO datos) {
        usuario.setNombre(datos.getNombre());
        usuario.setCorreo(datos.getCorreo());
        usuario.setClave(datos.getClave());
        usuario.setRol(datos.getRol());
    }

    private UsuarioDTO convertirADTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        dto.setRol(usuario.getRol());
        return dto;
    }
}
