package com.scp.api.Service;

import com.scp.api.DTO.ScpDTO;
import com.scp.api.Model.scp;
import com.scp.api.Repository.repositoryScp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScpServiceTest {

    @Mock
    private repositoryScp scpRepository;

    @InjectMocks
    private ScpService scpService;

    @Test
    void actualizarGuardaLosCamposActualizados() {
        scp existente = scpExistente();
        scp datos = new scp(1, "SCP actualizado", "Nueva descripción",
                "Euclid", 8, 1500);
        when(scpRepository.findById(1)).thenReturn(Optional.of(existente));
        when(scpRepository.save(existente)).thenReturn(existente);

        ScpDTO resultado = scpService.actualizar(1, datos);

        assertEquals("SCP actualizado", resultado.getNombre());
        assertEquals("Nueva descripción", resultado.getDescripcion());
        assertEquals("Euclid", resultado.getCategoria());
        assertEquals(8, resultado.getStock());
        assertEquals(1500, resultado.getPrecio());
        verify(scpRepository).save(existente);
    }

    @Test
    void actualizarStockNoPermiteUnValorNegativo() {
        scp existente = scpExistente();
        existente.setStock(2);
        when(scpRepository.findById(1)).thenReturn(Optional.of(existente));
        when(scpRepository.save(existente)).thenReturn(existente);

        ScpDTO resultado = scpService.actualizarStock(1, -5);

        assertEquals(0, resultado.getStock());
        verify(scpRepository).save(existente);
    }

    @Test
    void eliminarBorraElScpExistente() {
        when(scpRepository.existsById(1)).thenReturn(true);

        scpService.eliminar(1);

        verify(scpRepository).deleteById(1);
    }

    @Test
    void eliminarUnScpInexistenteDevuelve404() {
        when(scpRepository.existsById(99)).thenReturn(false);

        ResponseStatusException error = assertThrows(
                ResponseStatusException.class,
                () -> scpService.eliminar(99));

        assertEquals(404, error.getStatusCode().value());
    }

    private scp scpExistente() {
        return new scp(1, "SCP original", "Descripción original",
                "Safe", 5, 1000);
    }
}
