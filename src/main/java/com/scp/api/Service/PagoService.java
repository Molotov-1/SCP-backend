package com.scp.api.Service;

import com.scp.api.DTO.PagoDTO;
import com.scp.api.Model.Pago;
import com.scp.api.Model.Usuario;
import com.scp.api.Repository.RepositoryPago;
import com.scp.api.Repository.RepositoryUsuario;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PagoService {
    private final RepositoryPago pagoRepository;
    private final RepositoryUsuario usuarioRepository;

    public PagoService(RepositoryPago pagoRepository, RepositoryUsuario usuarioRepository) {
        this.pagoRepository = pagoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<PagoDTO> obtenerTodos() {
        return pagoRepository.findAll().stream().map(this::convertirADTO).toList();
    }

    public PagoDTO buscarPorID(Integer id) {
        return convertirADTO(buscarEntidad(id));
    }

    public PagoDTO guardar(PagoDTO datos) {
        Pago pago = new Pago();
        aplicarDatos(pago, datos);
        return convertirADTO(pagoRepository.save(pago));
    }

    public PagoDTO actualizar(Integer id, PagoDTO datos) {
        Pago pago = buscarEntidad(id);
        aplicarDatos(pago, datos);
        return convertirADTO(pagoRepository.save(pago));
    }

    public void eliminar(Integer id) {
        if (!pagoRepository.existsById(id)) {
            throw noEncontrado(id);
        }
        pagoRepository.deleteById(id);
    }

    private Pago buscarEntidad(Integer id) {
        return pagoRepository.findById(id).orElseThrow(() -> noEncontrado(id));
    }

    private ResponseStatusException noEncontrado(Integer id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Pago con ID " + id + " no existe");
    }

    private Usuario buscarUsuario(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario con ID " + id + " no existe"));
    }

    private void aplicarDatos(Pago pago, PagoDTO datos) {
        pago.setUsuario(buscarUsuario(datos.getUsuarioId()));
        pago.setTotal(datos.getTotal());
        pago.setEstado(datos.getEstado());
        pago.setMetodoPago(datos.getMetodoPago());
        pago.setFechaCreacion(datos.getFechaCreacion());
    }

    private PagoDTO convertirADTO(Pago pago) {
        PagoDTO dto = new PagoDTO();
        dto.setId(pago.getId());
        dto.setUsuarioId(pago.getUsuario().getId());
        dto.setTotal(pago.getTotal());
        dto.setEstado(pago.getEstado());
        dto.setMetodoPago(pago.getMetodoPago());
        dto.setFechaCreacion(pago.getFechaCreacion());
        return dto;
    }
}
