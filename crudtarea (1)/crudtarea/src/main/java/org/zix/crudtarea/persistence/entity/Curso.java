package org.zix.crudtarea.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity(name = "Cursos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "estudiantes") @EqualsAndHashCode
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoCurso;

    @Column
    private String nombreCurso;

    @OneToMany(mappedBy = "curso", fetch = FetchType.EAGER)
    private List<Estudiante> estudiantes; // Lista de alumnos inscritos
}
