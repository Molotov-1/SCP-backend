package com.scp.api.Controller;

import com.scp.api.DTO.ScpDTO;
import com.scp.api.Model.Scp;
import com.scp.api.Service.ScpService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/scps")
public class ScpController {

    @Autowired
    private ScpService scpService;

    @GetMapping
    public ResponseEntity<List<ScpDTO>> obtenerTodos() {
        return ResponseEntity.ok(scpService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScpDTO> buscarPorID(@PathVariable Integer id) {
        return ResponseEntity.ok(scpService.buscarPorID(id));
    }

    @PostMapping
    public ResponseEntity<ScpDTO> guardar(@Valid @RequestBody Scp nuevoScp) {
        ScpDTO scpGuardado = scpService.guardar(nuevoScp);
        return new ResponseEntity<>(scpGuardado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScpDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Scp scpActualizado) {
        return ResponseEntity.ok(scpService.actualizar(id, scpActualizado));
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ScpDTO> actualizarStock(
            @PathVariable Integer id,
            @RequestBody Map<String, Integer> body) {
        Integer cantidad = body.get("cantidad");
        if (cantidad == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(scpService.actualizarStock(id, cantidad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        scpService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}