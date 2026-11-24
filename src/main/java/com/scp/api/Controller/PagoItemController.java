package com.scp.api.Controller;

import com.scp.api.DTO.PagoItemDTO;
import com.scp.api.Service.PagoItemService;
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
@RequestMapping("/api/v1/pago-items")
public class PagoItemController {
    private final PagoItemService pagoItemService;

    public PagoItemController(PagoItemService pagoItemService) {
        this.pagoItemService = pagoItemService;
    }

    @GetMapping
    public ResponseEntity<List<PagoItemDTO>> obtenerTodos() {
        return ResponseEntity.ok(pagoItemService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoItemDTO> buscarPorID(@PathVariable Integer id) {
        return ResponseEntity.ok(pagoItemService.buscarPorID(id));
    }

    @PostMapping
    public ResponseEntity<PagoItemDTO> guardar(@Valid @RequestBody PagoItemDTO datos) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pagoItemService.guardar(datos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoItemDTO> actualizar(
            @PathVariable Integer id, @Valid @RequestBody PagoItemDTO datos) {
        return ResponseEntity.ok(pagoItemService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        pagoItemService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
