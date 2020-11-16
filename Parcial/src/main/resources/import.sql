-- You can use this file to load seed data into the database using SQL statements
insert into SOCIO (nombre, apellido, email, dni) values ('Jorge', 'Jira', 'jr@gmail.com', '27036985');
insert into SOCIO (nombre, apellido, email, dni) values ('Juan', 'Jornales', 'jj@gmail.com', '27036910');

insert into PELICULA (titulo, sinopsis, anio, genero) values ('Toy Story 4', 'Secuela de Toy Story', '2019', 1);
insert into PELICULA (titulo, sinopsis, anio, genero) values ('El maravilloso mundo de Jack', 'Pelicula de Halloween', '1994', 0);

insert into ALQUILER (socio_id, pelicula_id, fecha) values (1, 1, '2020-08-10');
