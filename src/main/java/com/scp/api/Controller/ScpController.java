package com.scp.api.Controller;

import com.scp.api.DTO.ScpDTO;
import com.scp.api.Model.scp;
import com.scp.api.Service.ScpService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/scps")
public class ScpController {

    @Autowired
    private ScpService scpService;

    @GetMapping 
    public ResponseEntity<List<ScpDTO>> obtenerTodos() {
        List<ScpDTO> listaScps = scpService.obtenerTodos();
        return new ResponseEntity<>(listaScps, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScpDTO> buscarPorID(@PathVariable Integer id) {
        ScpDTO scpDTO = scpService.buscarPorID(id);
        return new ResponseEntity<>(scpDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ScpDTO> guardar(@Valid @RequestBody scp nuevoScp) {
        ScpDTO scpGuardado = scpService.guardar(nuevoScp);
        return new ResponseEntity<>(scpGuardado, HttpStatus.CREATED);
    }
}