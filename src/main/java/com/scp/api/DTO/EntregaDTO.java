package com.scp.api.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EntregaDTO {
    private Integer id;

    @NotNull
    private Integer pagoId;

    @NotNull
    private Integer direccionId;

    @NotBlank
    @Size(max = 30)
    private String estado;

    @Size(max = 100)
    private String codigoSeguimiento;
}
