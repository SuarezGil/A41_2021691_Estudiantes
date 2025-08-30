package org.zix.crudtarea.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import org.zix.crudtarea.persistence.entity.Curso;
import org.zix.crudtarea.dominio.service.ICursoService;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@Data
public class IndexCursoController implements Serializable {

    private final ICursoService cursoService;  // Inyectado por constructor

    private List<Curso> cursos;
    private Curso cursoSeleccionado;

    // Constructor inyectado por Spring
    public IndexCursoController(ICursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostConstruct
    public void init() {
        cursos = cursoService.listarCursos();
        cursoSeleccionado = new Curso();
    }

    public void agregarCurso() {
        cursoSeleccionado = new Curso();
    }

    public void guardarCurso() {
        cursoService.guardarCurso(cursoSeleccionado);
        cursos = cursoService.listarCursos(); // recarga
    }

    public void eliminarCurso() {
        cursoService.eliminarCurso(cursoSeleccionado);
        cursos = cursoService.listarCursos();
    }
}
