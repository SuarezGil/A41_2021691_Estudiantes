package org.zix.crudtarea.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zix.crudtarea.persistence.entity.Curso;

@Repository
public interface CursoCrud extends JpaRepository<Curso, Integer> {
    // Aquí puedes agregar métodos personalizados si los necesitas
}
