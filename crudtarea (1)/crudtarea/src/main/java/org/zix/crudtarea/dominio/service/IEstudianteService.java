package org.zix.crudtarea.dominio.service;

import org.zix.crudtarea.persistence.entity.Estudiante;

import java.util.List;
import java.util.Scanner;

public interface IEstudianteService {
    //firmas del metodo
    public  List<Estudiante> listarEstudiantes();
    public Estudiante buscarEstudiantePorId(Integer codigo);
    public void guardarEstudiante(Estudiante estudiante);
    public void eliminarEstudiante(Estudiante estudiante);

}
