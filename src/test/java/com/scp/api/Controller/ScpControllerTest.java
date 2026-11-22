package com.scp.api.Controller;

import com.scp.api.DTO.ScpDTO;
import com.scp.api.Service.ScpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ScpControllerTest {

    private final ScpService scpService = mock(ScpService.class);
    private MockMvc mockMvc;

    @BeforeEach
    void configurar() {
        ScpController controller = new ScpController();
        org.springframework.test.util.ReflectionTestUtils.setField(controller, "scpService", scpService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void putActualizaUnScpYDevuelveElResultado() throws Exception {
        ScpDTO actualizado = scpDTO();
        when(scpService.actualizar(org.mockito.ArgumentMatchers.eq(1),
                org.mockito.ArgumentMatchers.any())).thenReturn(actualizado);

        mockMvc.perform(put("/api/v1/scps/1")
                        .contentType("application/json")
                        .content("""
                                {
                                  "nombre": "SCP actualizado",
                                  "descripcion": "Nueva descripción",
                                  "categoria": "Euclid",
                                  "stock": 8,
                                  "precio": 1500
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("SCP actualizado"));
    }

    @Test
    void patchStockSinCantidadDevuelve400() throws Exception {
        mockMvc.perform(patch("/api/v1/scps/1/stock")
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void patchStockConCantidadDevuelveElScpActualizado() throws Exception {
        ScpDTO actualizado = scpDTO();
        actualizado.setStock(7);
        when(scpService.actualizarStock(1, 2)).thenReturn(actualizado);

        mockMvc.perform(patch("/api/v1/scps/1/stock")
                        .contentType("application/json")
                        .content("{\"cantidad\":2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stock").value(7));

        verify(scpService).actualizarStock(1, 2);
    }

    @Test
    void deleteEliminaUnScpYDevuelve204() throws Exception {
        mockMvc.perform(delete("/api/v1/scps/1"))
                .andExpect(status().isNoContent());

        verify(scpService).eliminar(1);
    }

    private ScpDTO scpDTO() {
        ScpDTO dto = new ScpDTO();
        dto.setId(1);
        dto.setNombre("SCP actualizado");
        dto.setDescripcion("Nueva descripción");
        dto.setCategoria("Euclid");
        dto.setStock(5);
        dto.setPrecio(1500);
        return dto;
    }
}
