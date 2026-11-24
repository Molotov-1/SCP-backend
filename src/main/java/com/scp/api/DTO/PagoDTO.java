package com.scp.api.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PagoDTO {
    private Integer id;

    @NotNull
    private Integer usuarioId;

    @NotNull
    @Min(0)
    private Integer total;

    @NotBlank
    @Size(max = 30)
    private String estado;

    @NotBlank
    @Size(max = 50)
    private String metodoPago;

    @NotNull
    private LocalDateTime fechaCreacion;
}
