package com.scp.api.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "scps")
public class Scp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre no puede quedar vacio")
    @Size(min = 2, max = 100)
    private String nombre;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(max = 500)
    private String descripcion;

    @NotBlank(message = "La categoria es obligatoria")
    @Size(max = 50)
    private String categoria;

    @NotNull
    @Min(0)
    private Integer stock;

    @NotNull
    @Min(0)
    private Integer precio;

    @ManyToOne
    @JoinColumn(name = "sitio_id")
    private SitioContencion sitio;
}
