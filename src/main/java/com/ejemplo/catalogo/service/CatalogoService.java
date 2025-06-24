package com.ejemplo.catalogo.service;

import com.ejemplo.catalogo.model.Autor;
import com.ejemplo.catalogo.model.Libro;
import com.ejemplo.catalogo.repository.AutorRepository;
import com.ejemplo.catalogo.repository.LibroRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CatalogoService {

    private final LibroRepository libroRepo;
    private final AutorRepository autorRepo;
    private final GutendexService gutendexService;

    public CatalogoService(LibroRepository libroRepo, AutorRepository autorRepo, GutendexService gutendexService) {
        this.libroRepo = libroRepo;
        this.autorRepo = autorRepo;
        this.gutendexService = gutendexService;
    }

    public void buscarYGuardarLibro(String titulo) {
        JsonNode libroJson = gutendexService.buscarLibroPorTitulo(titulo);
        if (libroJson == null) {
            System.out.println("No se encontró el libro.");
            return;
        }

        JsonNode autorJson = libroJson.get("authors").get(0);
        String nombre = autorJson.get("name").asText();
        int nacimiento = autorJson.get("birth_year").asInt(0);
        int fallecimiento = autorJson.get("death_year").asInt(3000);

        Autor autor = autorRepo.findAll()
                .stream()
                .filter(a -> a.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElseGet(() -> {
                    Autor nuevo = new Autor();
                    nuevo.setNombre(nombre);
                    nuevo.setNacimiento(nacimiento);
                    nuevo.setFallecimiento(fallecimiento);
                    return autorRepo.save(nuevo);
                });

        Libro libro = new Libro();
        libro.setTitulo(libroJson.get("title").asText());
        libro.setIdioma(libroJson.get("languages").get(0).asText());
        libro.setAutor(autor);
        libroRepo.save(libro);

        System.out.println("Libro guardado correctamente.");
    }

    public void listarLibros() {
        libroRepo.findAll().forEach(libro -> {
            System.out.println(libro.getTitulo() + " - " + libro.getIdioma() + " - " + libro.getAutor().getNombre());
        });
    }

    public void listarAutores() {
        autorRepo.findAll().forEach(autor -> {
            System.out.println(autor.getNombre() + " (" + autor.getNacimiento() + " - " + autor.getFallecimiento() + ")");
            autor.getLibros().forEach(libro -> System.out.println("    • " + libro.getTitulo()));
        });
    }

    public void listarLibrosPorIdioma(String idioma) {
        libroRepo.findByIdioma(idioma).forEach(libro -> System.out.println(libro.getTitulo()));
    }

    public void listarAutoresVivosEn(int año) {
        autorRepo.findByNacimientoLessThanEqualAndFallecimientoGreaterThanEqual(año, año)
                .forEach(autor -> System.out.println(autor.getNombre()));
    }
}
