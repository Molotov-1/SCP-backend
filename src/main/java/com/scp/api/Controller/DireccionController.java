package com.scp.api.Controller;

import com.scp.api.DTO.DireccionDTO;
import com.scp.api.Service.DireccionService;
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
@RequestMapping("/api/v1/direcciones")
public class DireccionController {
    private final DireccionService direccionService;

    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @GetMapping
    public ResponseEntity<List<DireccionDTO>> obtenerTodos() {
        return ResponseEntity.ok(direccionService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionDTO> buscarPorID(@PathVariable Integer id) {
        return ResponseEntity.ok(direccionService.buscarPorID(id));
    }

    @PostMapping
    public ResponseEntity<DireccionDTO> guardar(@Valid @RequestBody DireccionDTO datos) {
        return ResponseEntity.status(HttpStatus.CREATED).body(direccionService.guardar(datos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DireccionDTO> actualizar(
            @PathVariable Integer id, @Valid @RequestBody DireccionDTO datos) {
        return ResponseEntity.ok(direccionService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        direccionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
