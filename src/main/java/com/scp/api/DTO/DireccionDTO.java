package com.scp.api.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DireccionDTO {
    private Integer id;

    @NotNull
    private Integer usuarioId;

    @NotBlank
    @Size(max = 150)
    private String calle;

    @NotBlank
    @Size(max = 100)
    private String ciudad;

    @NotBlank
    @Size(max = 100)
    private String region;
}
