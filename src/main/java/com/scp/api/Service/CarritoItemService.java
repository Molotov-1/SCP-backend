package com.scp.api.Service;

import com.scp.api.DTO.CarritoItemDTO;
import com.scp.api.Model.Carrito;
import com.scp.api.Model.CarritoItem;
import com.scp.api.Model.Scp;
import com.scp.api.Repository.RepositoryCarrito;
import com.scp.api.Repository.RepositoryCarritoItem;
import com.scp.api.Repository.RepositoryScp;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CarritoItemService {
    private final RepositoryCarritoItem carritoItemRepository;
    private final RepositoryCarrito carritoRepository;
    private final RepositoryScp scpRepository;

    public CarritoItemService(
            RepositoryCarritoItem carritoItemRepository,
            RepositoryCarrito carritoRepository,
            RepositoryScp scpRepository) {
        this.carritoItemRepository = carritoItemRepository;
        this.carritoRepository = carritoRepository;
        this.scpRepository = scpRepository;
    }

    public List<CarritoItemDTO> obtenerTodos() {
        return carritoItemRepository.findAll().stream().map(this::convertirADTO).toList();
    }

    public CarritoItemDTO buscarPorID(Integer id) {
        return convertirADTO(buscarEntidad(id));
    }

    public CarritoItemDTO guardar(CarritoItemDTO datos) {
        CarritoItem item = new CarritoItem();
        aplicarDatos(item, datos);
        return convertirADTO(carritoItemRepository.save(item));
    }

    public CarritoItemDTO actualizar(Integer id, CarritoItemDTO datos) {
        CarritoItem item = buscarEntidad(id);
        aplicarDatos(item, datos);
        return convertirADTO(carritoItemRepository.save(item));
    }

    public void eliminar(Integer id) {
        if (!carritoItemRepository.existsById(id)) {
            throw noEncontrado(id);
        }
        carritoItemRepository.deleteById(id);
    }

    private CarritoItem buscarEntidad(Integer id) {
        return carritoItemRepository.findById(id).orElseThrow(() -> noEncontrado(id));
    }

    private ResponseStatusException noEncontrado(Integer id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Item de carrito con ID " + id + " no existe");
    }

    private Carrito buscarCarrito(Integer id) {
        return carritoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito con ID " + id + " no existe"));
    }

    private Scp buscarScp(Integer id) {
        return scpRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "SCP con ID " + id + " no existe"));
    }

    private void aplicarDatos(CarritoItem item, CarritoItemDTO datos) {
        item.setCarrito(buscarCarrito(datos.getCarritoId()));
        item.setScp(buscarScp(datos.getScpId()));
        item.setCantidad(datos.getCantidad());
    }

    private CarritoItemDTO convertirADTO(CarritoItem item) {
        CarritoItemDTO dto = new CarritoItemDTO();
        dto.setId(item.getId());
        dto.setCarritoId(item.getCarrito().getId());
        dto.setScpId(item.getScp().getId());
        dto.setCantidad(item.getCantidad());
        return dto;
    }
}
