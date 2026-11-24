package com.scp.api.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PagoItemDTO {
    private Integer id;

    @NotNull
    private Integer pagoId;

    @NotNull
    private Integer scpId;

    @NotNull
    @Min(1)
    private Integer cantidad;

    @NotNull
    @Min(0)
    private Integer precioUnitario;
}
