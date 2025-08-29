package org.zix.crudtarea.dominio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zix.crudtarea.persistence.crud.EstudianteCrud;
import org.zix.crudtarea.persistence.entity.Estudiante;

import java.util.List;

@Service
public class EstudianteService implements IEstudianteService {

    //Inyeccion de dependencia de mi Repositorio (ClienteRepository)
    @Autowired
    private EstudianteCrud crud;

    @Override
    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> estudiantes = crud.findAll();
        return estudiantes;
    }

    @Override
    public Estudiante buscarEstudiantePorId(Integer codigo) {
        Estudiante estudiante = crud.findById(codigo).orElse(null);
        return estudiante;
    }

    @Override
    public void guardarEstudiante(Estudiante estudiante) {
        crud.save(estudiante);

    }

    @Override
    public void eliminarEstudiante(Estudiante estudiante) {
        crud.delete(estudiante);

    }
}

