package com.scp.api.DTO;

import lombok.Data;

@Data
public class ScpDTO {
    private Integer id;
    private String nombre;
    private String categoria;
    private String descripcion;
    private Integer stock;
    private Integer precio;
}