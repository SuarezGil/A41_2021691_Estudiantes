package org.zix.crudtarea.dominio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zix.crudtarea.persistence.crud.CursoCrud;
import org.zix.crudtarea.persistence.entity.Curso;

import java.util.List;

@Service
public class CursoService implements ICursoService {

    @Autowired
    private CursoCrud crud;

    @Override
    public List<Curso> listarCursos() {
        return crud.findAll();
    }

    @Override
    public Curso buscarCursoPorId(Integer codigo) {
        return crud.findById(codigo).orElse(null);
    }

    @Override
    public void guardarCurso(Curso curso) {
        crud.save(curso);
    }

    @Override
    public void eliminarCurso(Curso curso) {
        crud.delete(curso);
    }
}
