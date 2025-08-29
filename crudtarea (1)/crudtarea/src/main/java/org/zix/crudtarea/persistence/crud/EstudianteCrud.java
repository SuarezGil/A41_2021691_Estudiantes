package org.zix.crudtarea.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zix.crudtarea.persistence.entity.Estudiante;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteCrud extends JpaRepository<Estudiante,Integer> {

}
