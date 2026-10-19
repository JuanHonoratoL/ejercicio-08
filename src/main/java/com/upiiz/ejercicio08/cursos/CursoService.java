package com.upiiz.ejercicio08.cursos;

import java.util.List;

import com.upiiz.ejercicio08.cursos.dto.CursoDto;
import com.upiiz.ejercicio08.cursos.dto.NuevoCursoDto;

// Capa de servicio
public interface CursoService {

    CursoDto save(NuevoCursoDto nuevoCursoDto);

    List<CursoDto> findAll();

    CursoDto findById(Long id);

    CursoDto update(Long id, NuevoCursoDto updateCursoDto);

    void delete(Long id);
}
