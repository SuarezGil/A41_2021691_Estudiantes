package org.zix.crudtarea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.zix.crudtarea.dominio.service.ICursoService;
import org.zix.crudtarea.dominio.service.IEstudianteService;
import org.zix.crudtarea.persistence.entity.Curso;
import org.zix.crudtarea.persistence.entity.Estudiante;

import java.util.List;
import java.util.Scanner;

public class CrudtareaApplication implements CommandLineRunner {

    @Autowired
    private IEstudianteService estudianteService;

    @Autowired
    private ICursoService cursoService;

    private static final Logger logger = LoggerFactory.getLogger(CrudtareaApplication.class);

    String sl = System.lineSeparator(); // Salto de línea

    public static void main(String[] args) {
        logger.info("AQUI INICIA NUESTRA APLICACION");
        SpringApplication.run(CrudtareaApplication.class, args);
        logger.info("AQUI TERMINO LA APLICACION");
    }

    @Override
    public void run(String... args) {
        menuPrincipal();
    }

    // Menú principal
    private void menuPrincipal() {
        var salir = false;
        var consola = new Scanner(System.in);

        while (!salir) {
            logger.info("""
                    \n***Menu Principal***
                    1. CRUD de Estudiantes
                    2. CRUD de Cursos
                    3. Salir
                    Elige una opción: \s""");
            int opcion = Integer.parseInt(consola.nextLine());
            switch (opcion) {
                case 1 -> menuEstudiantes(consola);
                case 2 -> menuCursos(consola);
                case 3 -> salir = true;
                default -> logger.info("Opción no válida");
            }
        }
    }

    // Menú de Estudiantes
    private void menuEstudiantes(Scanner consola) {
        var salir = false;
        while (!salir) {
            logger.info("""
                    \n***CRUD de Estudiantes***
                    1. Listar todos los estudiantes
                    2. Buscar estudiante por código
                    3. Agregar nuevo estudiante
                    4. Modificar estudiante
                    5. Eliminar estudiante
                    6. Volver al menú principal
                    Elige una opción: \s""");
            int opcion = Integer.parseInt(consola.nextLine());
            switch (opcion) {
                case 1 -> {
                    logger.info(sl + "***Listado de todos los Estudiantes***" + sl);
                    List<Estudiante> estudiantes = estudianteService.listarEstudiantes();
                    estudiantes.forEach(e -> logger.info(e.toString() + sl));
                }
                case 2 -> {
                    logger.info("Ingrese el código del estudiante:");
                    int codigo = Integer.parseInt(consola.nextLine());
                    Estudiante e = estudianteService.buscarEstudiantePorId(codigo);
                    logger.info(e != null ? e.toString() : "Estudiante no encontrado");
                }
                case 3 -> {
                    Estudiante e = new Estudiante();
                    logger.info("Nombre:");
                    e.setNombre(consola.nextLine());
                    logger.info("Apellido:");
                    e.setApellido(consola.nextLine());
                    logger.info("Correo:");
                    e.setCorreo(consola.nextLine());

                    // Selección de curso
                    logger.info("Código del curso:");
                    int codigoCurso = Integer.parseInt(consola.nextLine());
                    e.setCurso(cursoService.buscarCursoPorId(codigoCurso));

                    estudianteService.guardarEstudiante(e);
                    logger.info("Estudiante agregado");
                }
                case 4 -> {
                    logger.info("Código del estudiante a modificar:");
                    int codigo = Integer.parseInt(consola.nextLine());
                    Estudiante e = estudianteService.buscarEstudiantePorId(codigo);
                    if (e != null) {
                        logger.info("Nombre:");
                        e.setNombre(consola.nextLine());
                        logger.info("Apellido:");
                        e.setApellido(consola.nextLine());
                        logger.info("Correo:");
                        e.setCorreo(consola.nextLine());

                        logger.info("Código del curso:");
                        int codigoCurso = Integer.parseInt(consola.nextLine());
                        e.setCurso(cursoService.buscarCursoPorId(codigoCurso));

                        estudianteService.guardarEstudiante(e);
                        logger.info("Estudiante modificado");
                    } else {
                        logger.info("Estudiante no encontrado");
                    }
                }
                case 5 -> {
                    logger.info("Código del estudiante a eliminar:");
                    int codigo = Integer.parseInt(consola.nextLine());
                    Estudiante e = estudianteService.buscarEstudiantePorId(codigo);
                    if (e != null) {
                        estudianteService.eliminarEstudiante(e);
                        logger.info("Estudiante eliminado");
                    } else {
                        logger.info("Estudiante no encontrado");
                    }
                }
                case 6 -> salir = true;
                default -> logger.info("Opción no válida");
            }
        }
    }

    // Menú de Cursos
    private void menuCursos(Scanner consola) {
        var salir = false;
        while (!salir) {
            logger.info("""
                    \n***CRUD de Cursos***
                    1. Listar todos los cursos
                    2. Buscar curso por código
                    3. Agregar nuevo curso
                    4. Modificar curso
                    5. Eliminar curso
                    6. Ver estudiantes de un curso
                    7. Volver al menú principal
                    Elige una opción: \s""");
            int opcion = Integer.parseInt(consola.nextLine());
            switch (opcion) {
                case 1 -> cursoService.listarCursos().forEach(c -> logger.info(c.toString()));
                case 2 -> {
                    logger.info("Código del curso:");
                    int codigo = Integer.parseInt(consola.nextLine());
                    Curso c = cursoService.buscarCursoPorId(codigo);
                    logger.info(c != null ? c.toString() : "Curso no encontrado");
                }
                case 3 -> {
                    Curso c = new Curso();
                    logger.info("Nombre del curso:");
                    c.setNombreCurso(consola.nextLine());
                    cursoService.guardarCurso(c);
                    logger.info("Curso agregado");
                }
                case 4 -> {
                    logger.info("Código del curso a modificar:");
                    int codigo = Integer.parseInt(consola.nextLine());
                    Curso c = cursoService.buscarCursoPorId(codigo);
                    if (c != null) {
                        logger.info("Nombre del curso:");
                        c.setNombreCurso(consola.nextLine());
                        cursoService.guardarCurso(c);
                        logger.info("Curso modificado");
                    } else {
                        logger.info("Curso no encontrado");
                    }
                }
                case 5 -> {
                    logger.info("Código del curso a eliminar:");
                    int codigo = Integer.parseInt(consola.nextLine());
                    Curso c = cursoService.buscarCursoPorId(codigo);
                    if (c != null) {
                        cursoService.eliminarCurso(c);
                        logger.info("Curso eliminado");
                    } else {
                        logger.info("Curso no encontrado");
                    }
                }
                case 6 -> {
                    logger.info("Código del curso para ver estudiantes:");
                    int codigo = Integer.parseInt(consola.nextLine());
                    Curso c = cursoService.buscarCursoPorId(codigo);
                    if (c != null && c.getEstudiantes() != null) {
                        logger.info("Estudiantes del curso " + c.getNombreCurso() + ":");
                        c.getEstudiantes().forEach(e -> logger.info(e.toString()));
                    } else {
                        logger.info("Curso no encontrado o sin estudiantes");
                    }
                }
                case 7 -> salir = true;
                default -> logger.info("Opción no válida");
            }
        }
    }
}
