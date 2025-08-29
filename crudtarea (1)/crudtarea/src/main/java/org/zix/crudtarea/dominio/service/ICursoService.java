package org.zix.crudtarea.dominio.service;

import org.zix.crudtarea.persistence.entity.Curso;
import java.util.List;

public interface ICursoService {
    List<Curso> listarCursos();
    Curso buscarCursoPorId(Integer codigo);
    void guardarCurso(Curso curso);
    void eliminarCurso(Curso curso);
}
