package com.scp.api.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CarritoDTO {
    private Integer id;

    @NotNull
    private Integer usuarioId;

    @NotBlank
    @Size(max = 30)
    private String estado;
}
