package com.scp.api.Service;

import com.scp.api.DTO.ScpDTO;
import com.scp.api.Repository.repositoryScp;
import com.scp.api.Model.scp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScpService {

    @Autowired
    private repositoryScp scpRepository;

    public List<ScpDTO> obtenerTodos() {
        List<ScpDTO> listaDTOs = new ArrayList<>();
        List<scp> listaScps = scpRepository.findAll();
            for (scp j : listaScps) {
                listaDTOs.add(convertirADTO(j));
            }
        return listaDTOs;
    }

    public ScpDTO buscarPorID(Integer id) {
        scp j = scpRepository.findById(id).orElseThrow(() -> new RuntimeException("SCP no encontrado con ID: " + id));
        return convertirADTO(j);
    }

    public ScpDTO guardar(scp nuevoScp) {
        scp scpGuardado = scpRepository.save(nuevoScp);
        return convertirADTO(scpGuardado);
    }

    private ScpDTO convertirADTO(scp j) {
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