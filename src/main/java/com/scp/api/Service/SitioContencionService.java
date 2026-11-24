package com.scp.api.Service;

import com.scp.api.DTO.SitioContencionDTO;
import com.scp.api.Model.SitioContencion;
import com.scp.api.Repository.RepositorySitioContencion;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SitioContencionService {
    private final RepositorySitioContencion sitioRepository;

    public SitioContencionService(RepositorySitioContencion sitioRepository) {
        this.sitioRepository = sitioRepository;
    }

    public List<SitioContencionDTO> obtenerTodos() {
        return sitioRepository.findAll().stream().map(this::convertirADTO).toList();
    }

    public SitioContencionDTO buscarPorID(Integer id) {
        return convertirADTO(buscarEntidad(id));
    }

    public SitioContencionDTO guardar(SitioContencionDTO datos) {
        SitioContencion sitio = new SitioContencion();
        aplicarDatos(sitio, datos);
        return convertirADTO(sitioRepository.save(sitio));
    }

    public SitioContencionDTO actualizar(Integer id, SitioContencionDTO datos) {
        SitioContencion sitio = buscarEntidad(id);
        aplicarDatos(sitio, datos);
        return convertirADTO(sitioRepository.save(sitio));
    }

    public void eliminar(Integer id) {
        if (!sitioRepository.existsById(id)) {
            throw noEncontrado(id);
        }
        sitioRepository.deleteById(id);
    }

    private SitioContencion buscarEntidad(Integer id) {
        return sitioRepository.findById(id).orElseThrow(() -> noEncontrado(id));
    }

    private ResponseStatusException noEncontrado(Integer id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Sitio de contención con ID " + id + " no existe");
    }

    private void aplicarDatos(SitioContencion sitio, SitioContencionDTO datos) {
        sitio.setNombre(datos.getNombre());
        sitio.setUbicacion(datos.getUbicacion());
        sitio.setCapacidad(datos.getCapacidad());
    }

    private SitioContencionDTO convertirADTO(SitioContencion sitio) {
        SitioContencionDTO dto = new SitioContencionDTO();
        dto.setId(sitio.getId());
        dto.setNombre(sitio.getNombre());
        dto.setUbicacion(sitio.getUbicacion());
        dto.setCapacidad(sitio.getCapacidad());
        return dto;
    }
}
