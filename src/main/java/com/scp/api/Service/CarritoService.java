package com.scp.api.Service;

import com.scp.api.DTO.CarritoDTO;
import com.scp.api.Model.Carrito;
import com.scp.api.Model.Usuario;
import com.scp.api.Repository.RepositoryCarrito;
import com.scp.api.Repository.RepositoryUsuario;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CarritoService {
    private final RepositoryCarrito carritoRepository;
    private final RepositoryUsuario usuarioRepository;

    public CarritoService(RepositoryCarrito carritoRepository, RepositoryUsuario usuarioRepository) {
        this.carritoRepository = carritoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<CarritoDTO> obtenerTodos() {
        return carritoRepository.findAll().stream().map(this::convertirADTO).toList();
    }

    public CarritoDTO buscarPorID(Integer id) {
        return convertirADTO(buscarEntidad(id));
    }

    public CarritoDTO guardar(CarritoDTO datos) {
        Carrito carrito = new Carrito();
        aplicarDatos(carrito, datos);
        return convertirADTO(carritoRepository.save(carrito));
    }

    public CarritoDTO actualizar(Integer id, CarritoDTO datos) {
        Carrito carrito = buscarEntidad(id);
        aplicarDatos(carrito, datos);
        return convertirADTO(carritoRepository.save(carrito));
    }

    public void eliminar(Integer id) {
        if (!carritoRepository.existsById(id)) {
            throw noEncontrado(id);
        }
        carritoRepository.deleteById(id);
    }

    private Carrito buscarEntidad(Integer id) {
        return carritoRepository.findById(id).orElseThrow(() -> noEncontrado(id));
    }

    private ResponseStatusException noEncontrado(Integer id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito con ID " + id + " no existe");
    }

    private Usuario buscarUsuario(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario con ID " + id + " no existe"));
    }

    private void aplicarDatos(Carrito carrito, CarritoDTO datos) {
        carrito.setUsuario(buscarUsuario(datos.getUsuarioId()));
        carrito.setEstado(datos.getEstado());
    }

    private CarritoDTO convertirADTO(Carrito carrito) {
        CarritoDTO dto = new CarritoDTO();
        dto.setId(carrito.getId());
        dto.setUsuarioId(carrito.getUsuario().getId());
        dto.setEstado(carrito.getEstado());
        return dto;
    }
}
