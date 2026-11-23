package com.scp.api.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "sitios_contencion")
public class SitioContencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre del sitio de contencion no puede quedar vacio")
    @Size(min = 2, max = 100)
    private String nombre;

    @NotBlank(message = "La ubicacion es obligatoria")
    @Size(max = 150)
    private String ubicacion;

    @NotNull
    @Min(0)
    private Integer capacidad;
}