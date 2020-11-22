-- You can use this file to load seed data into the database using SQL statements
INSERT INTO `parcial`.`user` (`EMAIL`, `LAST_NAME`, `NAME`) VALUES ('jr@gmail.com', 'Jornales', 'Jorge');
INSERT INTO `parcial`.`project` (`DESCRIPTION`, `NAME`) VALUES ('Segundo parcial con el que vamos a promocionar arquitectura de Sw', 'Parcial-Jira');
INSERT INTO `parcial`.`project_user` (`user_ID`, `project_ID`) VALUES ('1', '1');
INSERT INTO `parcial`.`state` (`NAME`) VALUES ('Nueva');
INSERT INTO `parcial`.`state` (`NAME`) VALUES ('Asignada');
INSERT INTO `parcial`.`state` (`NAME`) VALUES ('En proceso');
INSERT INTO `parcial`.`state` (`NAME`) VALUES ('Cerrada');
INSERT INTO `parcial`.`task` (`DESCRIPTION`, `PRIORITY`, `TASK_NAME`, `PROJECT_ID`, `STATE_ID`, `USER_ID`, `START_DATE`, `LAST_UPDATE`) VALUES ('Controlar Controllers', '0', 'CC', '1', '2', '1', CURRENT_TIME(), CURRENT_TIME());
INSERT INTO `parcial`.`comment` (`DESCRIPTION`, `TASK_ID`, `USER_ID`) VALUES ('Casi terminado todo ahre', '1', '1');
