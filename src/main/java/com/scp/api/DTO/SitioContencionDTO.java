package com.scp.api.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SitioContencionDTO {
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 100)
    private String nombre;

    @NotBlank
    @Size(max = 150)
    private String ubicacion;

    @NotNull
    @Min(0)
    private Integer capacidad;
}
