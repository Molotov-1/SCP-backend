package com.scp.api.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarritoItemDTO {
    private Integer id;

    @NotNull
    private Integer carritoId;

    @NotNull
    private Integer scpId;

    @NotNull
    @Min(1)
    private Integer cantidad;
}
