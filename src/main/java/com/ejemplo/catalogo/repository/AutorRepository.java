package com.ejemplo.catalogo.repository;

import com.ejemplo.catalogo.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByNacimientoLessThanEqualAndFallecimientoGreaterThanEqual(Integer año1, Integer año2);
}
