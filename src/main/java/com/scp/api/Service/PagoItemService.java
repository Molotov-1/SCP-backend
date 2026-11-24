package com.scp.api.Service;

import com.scp.api.DTO.PagoItemDTO;
import com.scp.api.Model.Pago;
import com.scp.api.Model.PagoItem;
import com.scp.api.Model.Scp;
import com.scp.api.Repository.RepositoryPago;
import com.scp.api.Repository.RepositoryPagoItem;
import com.scp.api.Repository.RepositoryScp;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PagoItemService {
    private final RepositoryPagoItem pagoItemRepository;
    private final RepositoryPago pagoRepository;
    private final RepositoryScp scpRepository;

    public PagoItemService(
            RepositoryPagoItem pagoItemRepository,
            RepositoryPago pagoRepository,
            RepositoryScp scpRepository) {
        this.pagoItemRepository = pagoItemRepository;
        this.pagoRepository = pagoRepository;
        this.scpRepository = scpRepository;
    }

    public List<PagoItemDTO> obtenerTodos() {
        return pagoItemRepository.findAll().stream().map(this::convertirADTO).toList();
    }

    public PagoItemDTO buscarPorID(Integer id) {
        return convertirADTO(buscarEntidad(id));
    }

    public PagoItemDTO guardar(PagoItemDTO datos) {
        PagoItem item = new PagoItem();
        aplicarDatos(item, datos);
        return convertirADTO(pagoItemRepository.save(item));
    }

    public PagoItemDTO actualizar(Integer id, PagoItemDTO datos) {
        PagoItem item = buscarEntidad(id);
        aplicarDatos(item, datos);
        return convertirADTO(pagoItemRepository.save(item));
    }

    public void eliminar(Integer id) {
        if (!pagoItemRepository.existsById(id)) {
            throw noEncontrado(id);
        }
        pagoItemRepository.deleteById(id);
    }

    private PagoItem buscarEntidad(Integer id) {
        return pagoItemRepository.findById(id).orElseThrow(() -> noEncontrado(id));
    }

    private ResponseStatusException noEncontrado(Integer id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Item de pago con ID " + id + " no existe");
    }

    private Pago buscarPago(Integer id) {
        return pagoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Pago con ID " + id + " no existe"));
    }

    private Scp buscarScp(Integer id) {
        return scpRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "SCP con ID " + id + " no existe"));
    }

    private void aplicarDatos(PagoItem item, PagoItemDTO datos) {
        item.setPago(buscarPago(datos.getPagoId()));
        item.setScp(buscarScp(datos.getScpId()));
        item.setCantidad(datos.getCantidad());
        item.setPrecioUnitario(datos.getPrecioUnitario());
    }

    private PagoItemDTO convertirADTO(PagoItem item) {
        PagoItemDTO dto = new PagoItemDTO();
        dto.setId(item.getId());
        dto.setPagoId(item.getPago().getId());
        dto.setScpId(item.getScp().getId());
        dto.setCantidad(item.getCantidad());
        dto.setPrecioUnitario(item.getPrecioUnitario());
        return dto;
    }
}
