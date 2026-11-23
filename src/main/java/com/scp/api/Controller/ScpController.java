package com.scp.api.Controller;

import com.scp.api.DTO.ActualizarStockRequest;
import com.scp.api.DTO.ScpDTO;
import com.scp.api.Model.Scp;
import com.scp.api.Service.ScpService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/scps")
@Tag(name = "SCP", description = "Operaciones del catálogo de SCP")
public class ScpController {

    @Autowired
    private ScpService scpService;

    @GetMapping
    @Operation(summary = "Listar SCP", description = "Obtiene todos los SCP del catálogo.")
    @ApiResponse(responseCode = "200", description = "Lista de SCP obtenida correctamente")
    public ResponseEntity<List<ScpDTO>> obtenerTodos() {
        return ResponseEntity.ok(scpService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar un SCP", description = "Obtiene un SCP por su identificador.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SCP encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe un SCP con ese ID")
    })
    public ResponseEntity<ScpDTO> buscarPorID(@PathVariable Integer id) {
        return ResponseEntity.ok(scpService.buscarPorID(id));
    }

    @PostMapping
    @Operation(summary = "Crear un SCP", description = "Agrega un SCP al catálogo.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "SCP creado"),
            @ApiResponse(responseCode = "400", description = "El cuerpo contiene datos inválidos")
    })
    public ResponseEntity<ScpDTO> guardar(@Valid @RequestBody Scp nuevoScp) {
        ScpDTO scpGuardado = scpService.guardar(nuevoScp);
        return new ResponseEntity<>(scpGuardado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un SCP", description = "Reemplaza los datos de un SCP existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SCP actualizado"),
            @ApiResponse(responseCode = "400", description = "El cuerpo contiene datos inválidos"),
            @ApiResponse(responseCode = "404", description = "No existe un SCP con ese ID")
    })
    public ResponseEntity<ScpDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Scp scpActualizado) {
        return ResponseEntity.ok(scpService.actualizar(id, scpActualizado));
    }

    @PatchMapping("/{id}/stock")
    @Operation(
            summary = "Actualizar el stock",
            description = "Suma la cantidad indicada al stock actual. El stock final no será menor que cero.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock actualizado"),
            @ApiResponse(responseCode = "400", description = "Falta el campo cantidad o su valor no es válido"),
            @ApiResponse(responseCode = "404", description = "No existe un SCP con ese ID")
    })
    public ResponseEntity<ScpDTO> actualizarStock(
            @PathVariable Integer id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Cantidad que se suma al stock actual.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ActualizarStockRequest.class)))
            @RequestBody ActualizarStockRequest body) {
        Integer cantidad = body.cantidad();
        if (cantidad == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(scpService.actualizarStock(id, cantidad));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un SCP", description = "Elimina un SCP del catálogo.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "SCP eliminado"),
            @ApiResponse(responseCode = "404", description = "No existe un SCP con ese ID")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        scpService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}