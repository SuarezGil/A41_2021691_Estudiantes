drop database if exists estudiantes_db;
create database estudiantes_db;
use estudiantes_db;
 
 
create table Cursos(
	codigo_curso integer auto_increment,
    nombre_curso varchar(64),
    constraint pk_cursos primary key(codigo_curso)
); 
  
create table Estudiantes(
	codigo_estudiante integer auto_increment,
    nombre varchar (64),
    apellido varchar (64),
    correo varchar (128),
    codigo_curso integer,
    constraint pk_estudiantes primary key (codigo_estudiante),
    constraint fk_estuidantes_cursos foreign key (codigo_curso)
		references Cursos(codigo_curso)
);


insert into Cursos (codigo_curso, nombre_curso)
values(1,'Matematica'),
	   (2,'Fisica');
 
INSERT INTO Estudiantes (codigo_estudiante, nombre, apellido, correo, codigo_curso)
VALUES
(1, 'Carlos', 'Ramírez','carlos.ramirez@example.com',1),
(2, 'Lucía', 'Pérez','lucia.perez@example.com',1),
(3, 'Andrés', 'Gómez', 'andres.gomez@example.com',1),
(4, 'Mariana', 'Lozano', 'mariana.lozano@example.com',1),
(5, 'Sam', 'Martínez', 'sam.martinez@example.com',1),
(6, 'Juan', 'Perez', 'dada@k.com',1);
select*from Estudiantes;