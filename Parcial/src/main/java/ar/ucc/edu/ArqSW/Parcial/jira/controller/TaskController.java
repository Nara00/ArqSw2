package ar.ucc.edu.ArqSW.Parcial.jira.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ar.ucc.edu.ArqSW.Parcial.common.dto.GenericExceptionDto;
import ar.ucc.edu.ArqSW.Parcial.common.exception.BadRequestException;
import ar.ucc.edu.ArqSW.Parcial.common.exception.EntityNotFoundException;
import ar.ucc.edu.ArqSW.Parcial.common.exception.ForbiddenException;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.TaskRequestDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.TaskResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.UserResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.service.TaskService;

@Controller
@RequestMapping("/task")

public class TaskController {

	@Autowired
	private TaskService taskService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<TaskResponseDto> getAllTask() {
		return taskService.getAllTask();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> lookupStateById(@PathVariable("id") Long id) {
		try {
			TaskResponseDto dto = taskService.getTaskById(id);
			return new ResponseEntity<Object>(dto, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("404", "No se encontró la entidad buscada");
			return new ResponseEntity<Object>(exDto, HttpStatus.NOT_FOUND);
		} catch (BadRequestException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("400", "El parámetro id ingresado no es válido");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public @ResponseBody ResponseEntity<Object> saveTask(@RequestBody TaskRequestDto request) {
		try {
			TaskResponseDto dto = taskService.insertTask(request);
			return new ResponseEntity<Object>(dto, HttpStatus.OK);
		} catch (BadRequestException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("400", "Error en la solicitud");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		} catch (EntityNotFoundException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("404",
					"Algunas de las entidades asociadas no se encontraron");
			return new ResponseEntity<Object>(exDto, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/changeStateFromTask/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> changeState(@PathVariable("id") Long id, @RequestBody Long request) {
		try {
			TaskResponseDto dto = taskService.changeState(id, request);
			return new ResponseEntity<Object>(dto, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("404", "El estado o tarea no se encontraron");
			return new ResponseEntity<Object>(exDto, HttpStatus.NOT_FOUND);
		} catch (BadRequestException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("400", "Error en la solicitud");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/setUserToTask/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> changeUser(@PathVariable("id") Long id, @RequestBody Long request) {
		try {
			TaskResponseDto dto = taskService.changeUser(id, request);
			return new ResponseEntity<Object>(dto, HttpStatus.OK);
		} catch (ForbiddenException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("403", "Esta persona no pertecene al proyecto");
			return new ResponseEntity<Object>(exDto, HttpStatus.FORBIDDEN);
		} catch (EntityNotFoundException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("404", "La tarea o el usuario no se encontraron");
			return new ResponseEntity<Object>(exDto, HttpStatus.NOT_FOUND);
		} catch (BadRequestException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("400", "Error en la solicitud");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		}
	}
}
