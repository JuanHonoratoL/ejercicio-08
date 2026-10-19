package com.upiiz.ejercicio08.cursos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upiiz.ejercicio08.cursos.entities.CursoEntity;

public interface CursoRepository extends JpaRepository<CursoEntity, Long> {
    Optional<CursoEntity> findByNombre(String nombre);
}
