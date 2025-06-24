package com.ejemplo.catalogo.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer nacimiento;
    private Integer fallecimiento;

    @OneToMany(mappedBy = "autor")
    private Set<Libro> libros;

    // Getters, setters y constructor vacío
}
