package org.zix.crudtarea.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Estudiantes")
//Lombok
@Data //genera los setter y getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode //codigo de autenticacion de la entidad

public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer codigoEstudiante;
    @Column
    private String nombre;
    private String apellido;
    private String correo;

    @ManyToOne
    @JoinColumn(name = "codigo_curso")
    private Curso curso; // Relación con Curso
}
