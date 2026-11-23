package com.scp.api.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

public record ActualizarStockRequest(
        @Schema(description = "Cantidad que se suma al stock actual.", requiredMode = Schema.RequiredMode.REQUIRED)
        Integer cantidad
) {
}
