package com.upiiz.ejercicio08.cursos;

import java.util.List;

import org.springframework.stereotype.Service;

import com.upiiz.ejercicio08.common.ConflictoException;
import com.upiiz.ejercicio08.common.RecursoNoEncontradoException;
import com.upiiz.ejercicio08.common.SolicitudInvalidaException;
import com.upiiz.ejercicio08.cursos.dto.CursoDto;
import com.upiiz.ejercicio08.cursos.dto.NuevoCursoDto;
import com.upiiz.ejercicio08.cursos.entities.CursoEntity;

@Service
public class CursoServiceImpl implements CursoService {
    private final CursoRepository cursoRepository;

    // Constructor para la inyección de dependencias
    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public CursoDto save(NuevoCursoDto nuevoCursoDto) {
        cursoRepository.findByNombre(nuevoCursoDto.nombre())
                .ifPresent(c -> {
                    throw new ConflictoException("Curso ya registrado");
                });
        return toDomain(cursoRepository.save(toEntity(nuevoCursoDto)));
    }

    @Override
    public List<CursoDto> findAll() {
        return cursoRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public CursoDto findById(Long id) {
        validarId(id);
        return toDomain(buscar(id));
    }

    @Override
    public CursoDto update(Long id, NuevoCursoDto updateCursoDto) {
        validarId(id);
        CursoEntity curso = buscar(id);

        cursoRepository.findByNombre(updateCursoDto.nombre())
                .filter(otro -> !otro.getId().equals(id))
                .ifPresent(otro -> {
                    throw new ConflictoException("Ya existe otro curso con ese nombre");
                });

        curso.setNombre(updateCursoDto.nombre());
        curso.setDuracionHoras(updateCursoDto.duracionHoras());
        curso.setNivel(updateCursoDto.nivel());
        curso.setPrecio(updateCursoDto.precio());
        curso.setFechaInicio(updateCursoDto.fechaInicio());

        return toDomain(cursoRepository.save(curso));
    }

    @Override
    public void delete(Long id) {
        validarId(id);
        cursoRepository.delete(buscar(id));
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new SolicitudInvalidaException("El id debe ser un número positivo");
        }
    }

    private CursoEntity buscar(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Curso no encontrado"));
    }

    private CursoEntity toEntity(NuevoCursoDto dto) {
        return new CursoEntity(dto.nombre(), dto.duracionHoras(), dto.nivel(), dto.precio(), dto.fechaInicio());
    }

    private CursoDto toDomain(CursoEntity entity) {
        return new CursoDto(entity.getId(), entity.getNombre(), entity.getDuracionHoras(), entity.getNivel(),
                entity.getPrecio(), entity.getFechaInicio());
    }
}
