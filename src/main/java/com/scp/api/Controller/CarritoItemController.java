package com.scp.api.Controller;

import com.scp.api.DTO.CarritoItemDTO;
import com.scp.api.Service.CarritoItemService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/carrito-items")
public class CarritoItemController {
    private final CarritoItemService carritoItemService;

    public CarritoItemController(CarritoItemService carritoItemService) {
        this.carritoItemService = carritoItemService;
    }

    @GetMapping
    public ResponseEntity<List<CarritoItemDTO>> obtenerTodos() {
        return ResponseEntity.ok(carritoItemService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarritoItemDTO> buscarPorID(@PathVariable Integer id) {
        return ResponseEntity.ok(carritoItemService.buscarPorID(id));
    }

    @PostMapping
    public ResponseEntity<CarritoItemDTO> guardar(@Valid @RequestBody CarritoItemDTO datos) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carritoItemService.guardar(datos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarritoItemDTO> actualizar(
            @PathVariable Integer id, @Valid @RequestBody CarritoItemDTO datos) {
        return ResponseEntity.ok(carritoItemService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        carritoItemService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
