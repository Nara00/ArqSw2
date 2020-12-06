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
import ar.ucc.edu.ArqSW.Parcial.jira.dto.ProjectRequestDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.ProjectResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.service.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;


	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ProjectResponseDto> getAllProject() {
		return projectService.getAllProject();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> lookupStateById(@PathVariable("id") Long id){
		try {
			ProjectResponseDto dto = projectService.getProjectById(id);
			return new ResponseEntity<Object>(dto, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("404", "No se encontró la entidad buscada");
			return new ResponseEntity<Object>(exDto, HttpStatus.NOT_FOUND);
		} catch (BadRequestException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("400", "El parámetro id ingresado no es válido");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			GenericExceptionDto exDto = new GenericExceptionDto("400", "Error en la solicitud");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		}
	}
		
	@RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ProjectResponseDto> lookupStateById(@PathVariable("name") String name) {
		return projectService.getProjectByName(name);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public @ResponseBody ResponseEntity<Object> saveProject(@RequestBody ProjectRequestDto request) {
		try {
			ProjectResponseDto dto = projectService.insertProject(request);
			return new ResponseEntity<Object>(dto, HttpStatus.OK);
		}catch (BadRequestException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("400", "Error en la solicitud");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			GenericExceptionDto exDto = new GenericExceptionDto("400", "Error en la solicitud");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		}
		
	}

	@RequestMapping(value = "/setUserToProject/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> setUser(@PathVariable("id") Long id, @RequestBody Long request) {
		try {
			ProjectResponseDto dto = projectService.setUser(id, request);
			return new ResponseEntity<Object>(dto, HttpStatus.OK);
		} catch (BadRequestException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("400", "Error en la solicitud");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		}catch (EntityNotFoundException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("404", "El projecto o el usuario no se encontraron");
			return new ResponseEntity<Object>(exDto, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			GenericExceptionDto exDto = new GenericExceptionDto("400", "Error en la solicitud");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		}
	}

}
