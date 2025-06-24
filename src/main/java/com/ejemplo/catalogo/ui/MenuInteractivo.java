package com.ejemplo.catalogo.ui;

import com.ejemplo.catalogo.service.CatalogoService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuInteractivo {

    private final CatalogoService catalogoService;
    private final Scanner scanner = new Scanner(System.in);

    public MenuInteractivo(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }

    public void iniciar() {
        while (true) {
            System.out.println("""
                \n--- Catálogo de Libros ---
                1. Buscar libro por título
                2. Listar libros registrados
                3. Listar autores registrados
                4. Listar libros por idioma
                5. Listar autores vivos en un año determinado
                0. Salir
                """);
            System.out.print("Opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> {
                    System.out.print("Título del libro: ");
                    catalogoService.buscarYGuardarLibro(scanner.nextLine());
                }
                case 2 -> catalogoService.listarLibros();
                case 3 -> catalogoService.listarAutores();
                case 4 -> {
                    System.out.print("Código de idioma (ej: 'en'): ");
                    catalogoService.listarLibrosPorIdioma(scanner.nextLine());
                }
                case 5 -> {
                    System.out.print("Año: ");
                    catalogoService.listarAutoresVivosEn(Integer.parseInt(scanner.nextLine()));
                }
                case 0 -> {
                    System.out.println("¡Hasta luego!");
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }
}
