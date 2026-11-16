package com.scp.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "scps")
public class scp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre del SCP no puede quedar vacío")
    @Size(min = 3, max = 50)
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotBlank (message = "La categoría es obligatoria")
    private String categoria;

    @NotNull
    @Min(0)
    private Integer stock;

    @NotNull
    @Min(0)
    private Integer precio;
}