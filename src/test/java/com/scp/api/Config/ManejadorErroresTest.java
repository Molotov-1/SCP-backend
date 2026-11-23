package com.scp.api.Config;

import com.scp.api.Model.Scp;
import jakarta.validation.Valid;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ManejadorErroresTest {

    private final MockMvc mockMvc = MockMvcBuilders
            .standaloneSetup(new ControladorPrueba())
            .setControllerAdvice(new ManejadorErrores())
            .build();

    @Test
    void validacionDevuelveFormatoComunYErroresPorCampo() throws Exception {
        mockMvc.perform(post("/prueba/scps")
                        .contentType("application/json")
                        .content("""
                                {
                                  "nombre": "A",
                                  "descripcion": "Descripción",
                                  "categoria": "Euclid",
                                  "stock": 1,
                                  "precio": 100
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.mensaje").value("Hay campos inválidos"))
                .andExpect(jsonPath("$.ruta").value("/prueba/scps"))
                .andExpect(jsonPath("$.campos.nombre").isNotEmpty());
    }

    @Test
    void idConTipoIncorrectoDevuelveBadRequest() throws Exception {
        mockMvc.perform(get("/prueba/scps/no-es-numero"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.ruta").value("/prueba/scps/no-es-numero"));
    }

    @Test
    void responseStatusExceptionConservaElCodigoYElMensaje() throws Exception {
        mockMvc.perform(get("/prueba/no-encontrado"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.mensaje").value("SCP no existe"));
    }

    @RestController
    static class ControladorPrueba {

        @PostMapping("/prueba/scps")
        void validar(@Valid @RequestBody Scp nuevoScp) {
        }

        @GetMapping("/prueba/scps/{id}")
        void buscar(@PathVariable Integer id) {
        }

        @GetMapping("/prueba/no-encontrado")
        void noEncontrado() {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SCP no existe");
        }
    }
}
