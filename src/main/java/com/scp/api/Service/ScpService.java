package com.scp.api.Service;

import com.scp.api.DTO.ScpDTO;
import com.scp.api.Model.Scp;
import com.scp.api.Repository.RepositoryScp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScpService {

    @Autowired
    private RepositoryScp scpRepository;

    public List<ScpDTO> obtenerTodos() {
        List<ScpDTO> listaDTOs = new ArrayList<>();
        for (Scp j : scpRepository.findAll()) {
            listaDTOs.add(convertirADTO(j));
        }
        return listaDTOs;
    }

    public ScpDTO buscarPorID(Integer id) {
        Scp j = scpRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SCP con ID " + id + " no existe"));
        return convertirADTO(j);
    }

    public ScpDTO guardar(Scp nuevoScp) {
        Scp scpGuardado = scpRepository.save(nuevoScp);
        return convertirADTO(scpGuardado);
    }

    public ScpDTO actualizar(Integer id, Scp datos) {
        Scp existente = scpRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SCP con ID " + id + " no existe"));

        existente.setNombre(datos.getNombre());
        existente.setDescripcion(datos.getDescripcion());
        existente.setCategoria(datos.getCategoria());
        existente.setStock(datos.getStock());
        existente.setPrecio(datos.getPrecio());

        return convertirADTO(scpRepository.save(existente));
    }

    public ScpDTO actualizarStock(Integer id, Integer cantidad) {
        Scp existente = scpRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SCP con ID " + id + " no existe"));

        int nuevoStock = Math.max(0, (existente.getStock() == null ? 0 : existente.getStock()) + cantidad);
        existente.setStock(nuevoStock);

        return convertirADTO(scpRepository.save(existente));
    }

    public void eliminar(Integer id) {
        if (!scpRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SCP con ID " + id + " no existe");
        }
        scpRepository.deleteById(id);
    }

    private ScpDTO convertirADTO(Scp j) {
        ScpDTO dto = new ScpDTO();
        dto.setId(j.getId());
        dto.setNombre(j.getNombre());
        dto.setCategoria(j.getCategoria());
        dto.setDescripcion(j.getDescripcion());
        dto.setStock(j.getStock());
        dto.setPrecio(j.getPrecio());
        return dto;
    }
}