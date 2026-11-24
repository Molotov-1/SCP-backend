package com.scp.api.Controller;

import com.scp.api.DTO.CarritoDTO;
import com.scp.api.Service.CarritoService;
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
@RequestMapping("/api/v1/carritos")
public class CarritoController {
    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping
    public ResponseEntity<List<CarritoDTO>> obtenerTodos() {
        return ResponseEntity.ok(carritoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarritoDTO> buscarPorID(@PathVariable Integer id) {
        return ResponseEntity.ok(carritoService.buscarPorID(id));
    }

    @PostMapping
    public ResponseEntity<CarritoDTO> guardar(@Valid @RequestBody CarritoDTO datos) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carritoService.guardar(datos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarritoDTO> actualizar(
            @PathVariable Integer id, @Valid @RequestBody CarritoDTO datos) {
        return ResponseEntity.ok(carritoService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        carritoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
