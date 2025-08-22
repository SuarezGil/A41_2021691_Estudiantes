package org.zix.crudtarea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.zix.crudtarea.dominio.service.IEstudianteService;
import org.zix.crudtarea.persistence.entity.Estudiante;

import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class CrudtareaApplication implements CommandLineRunner {
    //Inyeccion de dependecia
    // @Autowired
    private IEstudianteService estudianteService;
    // crear nuestro objet(herramamienta) Logger para interactuar con la consola

    private static final Logger logger = LoggerFactory.getLogger(CrudtareaApplication.class);

    //Crear un Objeto String para saltos de line
    String sl = System.lineSeparator(); // salto de linea

    public static void main(String[] args) {
        logger.info("AQUI INICIA NUESTRA APLICACION");
        SpringApplication.run(CrudtareaApplication.class, args);
        logger.info("AQUI TERMINO LA APLICACION");
    }
    @Override
    public void run(String... args) throws Exception {
        crudTareaApp();
    }

    private void crudTareaApp(){
        logger.info("++++++APLICACION DE REGISTRO DE CLIENTE");
        var salir = false;
        var consola = new Scanner(System.in);
        while(!salir){

            var opcion = mostrarMenu(consola);
            salir = ejecutarOpciones(consola, opcion);
            logger.info(sl);
        }

    }
    private int mostrarMenu(Scanner consola){
        logger.info("""
                \n ***Aplicacion***
                1. Listar todos los clientes.
                2. Buscar cliente por codigo.
                3. Agregar nuevo cliente.
                4. Modificar cliente
                5. Eliminar cliente
                6. Salir.
                
                Elije una opcion: \s """);
        var opcion = Integer.parseInt(consola.nextLine());
        return opcion;


    }
    private boolean ejecutarOpciones(Scanner consola, int opcion) {
        var salir = false;
        switch (opcion) {
            case 1 -> {
                logger.info(sl + "***Listado de todos los Clientes***" + sl);
                List<Estudiante> estudiantes = estudianteService.listarEstudiantes();
                estudiantes.forEach(estudiante -> logger.info(estudiante.toString() + sl));
            }
            case 2 -> {
                logger.info(sl + "*** Buscar Cliente por su codigo ***" + sl);
                var codigo = Integer.parseInt(consola.nextLine());
                Estudiante estudiante = estudianteService.buscarEstudiantePorId(codigo);
                if (estudiante != null) {
                    logger.info("Cliente encontrado: " + sl + estudiante + sl);
                } else {
                    logger.info("Cliente No Encontrado: " + sl + estudiante + sl);

                }

            }
            case 3 -> {
                logger.info(sl + "***Agregar nuevo Cliente ***" + sl);
                logger.info("Ingrese el nombre");
                var nombre = consola.nextLine();
                logger.info("Ingrese el apellido");
                var apellido = consola.nextLine();
                logger.info("Ingrese el correo");
                var correo = consola.nextLine();
                logger.info("Ingrese el genero");
                var genero = consola.nextLine();
                logger.info("Ingrese la edad");
                var estudiante = Integer.parseInt(consola.nextLine());

                estudiante.setNombre(nombre);
                estudiante.setApellido(apellido);
                estudiante.setCorreo(correo);
                estudiante.setGenero(genero);
                estudiante.setEdad(edad);
                estudianteService.guardarEstudiante(estudiante);
            }
            case 4 -> {
                logger.info(sl + "***Modificar Estudiante ***" + sl);
                logger.info("Ingrese el codigo del estudiante a editar");
                var codigo = Integer.parseInt(consola.nextLine());
                Estudiante estudiante = estudianteService.buscarEstudiantePorId(codigo);
                if (estudiante != null) {
                    logger.info("Ingrese el nombre");
                    var nombre = consola.nextLine();
                    logger.info("Ingrese el apellido");
                    var apellido = consola.nextLine();
                    logger.info("Ingrese el correo");
                    var correo = consola.nextLine();
                    logger.info("Ingrese el genero");
                    var genero = consola.nextLine();
                    logger.info("Ingrese la edad");
                    var edad = Integer.parseInt(consola.nextLine());

                    estudiante.setNombre(nombre);
                    estudiante.setApellido(apellido);
                    estudiante.setCorreo(correo);
                    estudiante.setGenero(genero);
                    estudiante.setEdad(edad);
                    estudianteService.guardarEstudiante(estudiante);
                    logger.info("Estudiante Modificado: " + sl + estudiante + sl);
                }else {
                logger.info("Cliente No encotrado: " + sl + estudiante + sl);

            }




            case 5 -> {
                logger.info(sl+"***Eliminar Cliente***"+sl);
                logger.info("Ingrese el codigo de cliente a eliminar");
                var codigo = Integer.parseInt(consola.nextLine());
                var estudiantes = estudianteService.buscarEstudiantePorId(codigo);
                if (estudiante !=null){
                    estudianteService.eliminarEstudiante(estudiante);
                    logger.info("Cleinte eliminado, adios"+sl+estudiante+sl);

                }else{
                    logger.info("Estudiante no encotrado"+sl+estudiante+sl);
                }

            }
            case 6 -> {
                logger.info("Hasta pronto, Vaquero"+sl+sl);
                salir = true;

            }
            default -> logger.info("Opcion no valida");

        }
        return false;
    }


}

