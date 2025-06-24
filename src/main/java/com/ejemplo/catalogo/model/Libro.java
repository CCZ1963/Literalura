package com.ejemplo.catalogo.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;

    @ManyToOne
    private Autor autor;

    // Getters, setters y constructor vacío
}
