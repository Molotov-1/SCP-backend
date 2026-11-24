package com.scp.api.Controller;

import com.scp.api.DTO.SitioContencionDTO;
import com.scp.api.Service.SitioContencionService;
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
@RequestMapping("/api/v1/sitios-contencion")
public class SitioContencionController {
    private final SitioContencionService sitioService;

    public SitioContencionController(SitioContencionService sitioService) {
        this.sitioService = sitioService;
    }

    @GetMapping
    public ResponseEntity<List<SitioContencionDTO>> obtenerTodos() {
        return ResponseEntity.ok(sitioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SitioContencionDTO> buscarPorID(@PathVariable Integer id) {
        return ResponseEntity.ok(sitioService.buscarPorID(id));
    }

    @PostMapping
    public ResponseEntity<SitioContencionDTO> guardar(@Valid @RequestBody SitioContencionDTO datos) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sitioService.guardar(datos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SitioContencionDTO> actualizar(
            @PathVariable Integer id, @Valid @RequestBody SitioContencionDTO datos) {
        return ResponseEntity.ok(sitioService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        sitioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
