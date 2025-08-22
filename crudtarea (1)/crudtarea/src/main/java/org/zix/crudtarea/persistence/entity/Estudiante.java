package org.zix.crudtarea.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Estudiante")
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
    private String telefono;
    private String correo;
    private String genero;
    private Integer edad;
}
