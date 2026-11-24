package com.scp.api.Controller;

import com.scp.api.DTO.EntregaDTO;
import com.scp.api.Service.EntregaService;
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
@RequestMapping("/api/v1/entregas")
public class EntregaController {
    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @GetMapping
    public ResponseEntity<List<EntregaDTO>> obtenerTodos() {
        return ResponseEntity.ok(entregaService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaDTO> buscarPorID(@PathVariable Integer id) {
        return ResponseEntity.ok(entregaService.buscarPorID(id));
    }

    @PostMapping
    public ResponseEntity<EntregaDTO> guardar(@Valid @RequestBody EntregaDTO datos) {
        return ResponseEntity.status(HttpStatus.CREATED).body(entregaService.guardar(datos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntregaDTO> actualizar(
            @PathVariable Integer id, @Valid @RequestBody EntregaDTO datos) {
        return ResponseEntity.ok(entregaService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        entregaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
