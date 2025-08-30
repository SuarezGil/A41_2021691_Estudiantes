package org.zix.crudtarea.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.zix.crudtarea.dominio.service.ICursoService;
import org.zix.crudtarea.dominio.service.IEstudianteService;
import org.zix.crudtarea.persistence.entity.Curso;
import org.zix.crudtarea.persistence.entity.Estudiante;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
@Data
public class IndexController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private IEstudianteService estudianteService;

    @Autowired
    private ICursoService cursoService;
    private Integer idCursoSeleccionado;


    private List<Estudiante> estudiantes = new ArrayList<>();
    private List<Curso> cursosDisponibles = new ArrayList<>();
    private Estudiante estudianteSeleccionado = new Estudiante();

    @PostConstruct
    public void init() {
        cargarDatos();
        cursosDisponibles = cursoService.listarCursos();
    }

    public void cargarDatos() {
        List<Estudiante> lista = estudianteService.listarEstudiantes();
        if (lista != null) {
            this.estudiantes = lista;
        }
    }

    public void agregarEstudiante() {
        this.estudianteSeleccionado = new Estudiante();
    }

    public void guardarEstudiante() {
        if (idCursoSeleccionado != null) {
            Curso curso = cursoService.buscarCursoPorId(idCursoSeleccionado);
            estudianteSeleccionado.setCurso(curso);
        }

        estudianteService.guardarEstudiante(estudianteSeleccionado);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage((estudianteSeleccionado.getCodigoEstudiante() == null) ?
                        "Estudiante agregado" : "Estudiante actualizado"));

        cargarDatos();
        PrimeFaces.current().executeScript("PF('ventanaModalEstudiante').hide()");
        PrimeFaces.current().ajax().update("formulario-estudiantes:mensaje_emergente", "formulario-estudiantes:tabla-estudiantes");

        // Reiniciar
        estudianteSeleccionado = new Estudiante();
        idCursoSeleccionado = null;
    }



    public void eliminarEstudiante() {
        estudianteService.eliminarEstudiante(estudianteSeleccionado);

        // Recargar la lista
        cargarDatos();

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Estudiante eliminado", null));

        // Actualizar componentes
        PrimeFaces.current().ajax().update("formulario-estudiantes:mensaje_emergente",
                "formulario-estudiantes:tabla-estudiantes");

        estudianteSeleccionado = new Estudiante();
    }
}
