package com.scp.api.Service;

import com.scp.api.DTO.DireccionDTO;
import com.scp.api.Model.Direccion;
import com.scp.api.Model.Usuario;
import com.scp.api.Repository.RepositoryDireccion;
import com.scp.api.Repository.RepositoryUsuario;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DireccionService {
    private final RepositoryDireccion direccionRepository;
    private final RepositoryUsuario usuarioRepository;

    public DireccionService(RepositoryDireccion direccionRepository, RepositoryUsuario usuarioRepository) {
        this.direccionRepository = direccionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<DireccionDTO> obtenerTodos() {
        return direccionRepository.findAll().stream().map(this::convertirADTO).toList();
    }

    public DireccionDTO buscarPorID(Integer id) {
        return convertirADTO(buscarEntidad(id));
    }

    public DireccionDTO guardar(DireccionDTO datos) {
        Direccion direccion = new Direccion();
        aplicarDatos(direccion, datos);
        return convertirADTO(direccionRepository.save(direccion));
    }

    public DireccionDTO actualizar(Integer id, DireccionDTO datos) {
        Direccion direccion = buscarEntidad(id);
        aplicarDatos(direccion, datos);
        return convertirADTO(direccionRepository.save(direccion));
    }

    public void eliminar(Integer id) {
        if (!direccionRepository.existsById(id)) {
            throw noEncontrado(id);
        }
        direccionRepository.deleteById(id);
    }

    private Direccion buscarEntidad(Integer id) {
        return direccionRepository.findById(id).orElseThrow(() -> noEncontrado(id));
    }

    private ResponseStatusException noEncontrado(Integer id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Dirección con ID " + id + " no existe");
    }

    private Usuario buscarUsuario(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario con ID " + id + " no existe"));
    }

    private void aplicarDatos(Direccion direccion, DireccionDTO datos) {
        direccion.setUsuario(buscarUsuario(datos.getUsuarioId()));
        direccion.setCalle(datos.getCalle());
        direccion.setCiudad(datos.getCiudad());
        direccion.setRegion(datos.getRegion());
    }

    private DireccionDTO convertirADTO(Direccion direccion) {
        DireccionDTO dto = new DireccionDTO();
        dto.setId(direccion.getId());
        dto.setUsuarioId(direccion.getUsuario().getId());
        dto.setCalle(direccion.getCalle());
        dto.setCiudad(direccion.getCiudad());
        dto.setRegion(direccion.getRegion());
        return dto;
    }
}
