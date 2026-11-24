package com.scp.api.Service;

import com.scp.api.DTO.EntregaDTO;
import com.scp.api.Model.Direccion;
import com.scp.api.Model.Entrega;
import com.scp.api.Model.Pago;
import com.scp.api.Repository.RepositoryDireccion;
import com.scp.api.Repository.RepositoryEntrega;
import com.scp.api.Repository.RepositoryPago;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EntregaService {
    private final RepositoryEntrega entregaRepository;
    private final RepositoryPago pagoRepository;
    private final RepositoryDireccion direccionRepository;

    public EntregaService(
            RepositoryEntrega entregaRepository,
            RepositoryPago pagoRepository,
            RepositoryDireccion direccionRepository) {
        this.entregaRepository = entregaRepository;
        this.pagoRepository = pagoRepository;
        this.direccionRepository = direccionRepository;
    }

    public List<EntregaDTO> obtenerTodos() {
        return entregaRepository.findAll().stream().map(this::convertirADTO).toList();
    }

    public EntregaDTO buscarPorID(Integer id) {
        return convertirADTO(buscarEntidad(id));
    }

    public EntregaDTO guardar(EntregaDTO datos) {
        Entrega entrega = new Entrega();
        aplicarDatos(entrega, datos);
        return convertirADTO(entregaRepository.save(entrega));
    }

    public EntregaDTO actualizar(Integer id, EntregaDTO datos) {
        Entrega entrega = buscarEntidad(id);
        aplicarDatos(entrega, datos);
        return convertirADTO(entregaRepository.save(entrega));
    }

    public void eliminar(Integer id) {
        if (!entregaRepository.existsById(id)) {
            throw noEncontrado(id);
        }
        entregaRepository.deleteById(id);
    }

    private Entrega buscarEntidad(Integer id) {
        return entregaRepository.findById(id).orElseThrow(() -> noEncontrado(id));
    }

    private ResponseStatusException noEncontrado(Integer id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrega con ID " + id + " no existe");
    }

    private Pago buscarPago(Integer id) {
        return pagoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Pago con ID " + id + " no existe"));
    }

    private Direccion buscarDireccion(Integer id) {
        return direccionRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Dirección con ID " + id + " no existe"));
    }

    private void aplicarDatos(Entrega entrega, EntregaDTO datos) {
        entrega.setPago(buscarPago(datos.getPagoId()));
        entrega.setDireccion(buscarDireccion(datos.getDireccionId()));
        entrega.setEstado(datos.getEstado());
        entrega.setCodigoSeguimiento(datos.getCodigoSeguimiento());
    }

    private EntregaDTO convertirADTO(Entrega entrega) {
        EntregaDTO dto = new EntregaDTO();
        dto.setId(entrega.getId());
        dto.setPagoId(entrega.getPago().getId());
        dto.setDireccionId(entrega.getDireccion().getId());
        dto.setEstado(entrega.getEstado());
        dto.setCodigoSeguimiento(entrega.getCodigoSeguimiento());
        return dto;
    }
}
