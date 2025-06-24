package com.ejemplo.catalogo;

import com.ejemplo.catalogo.ui.MenuInteractivo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CatalogoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(MenuInteractivo menu) {
		return args -> menu.iniciar();
	}
}
