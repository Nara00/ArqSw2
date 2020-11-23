package ar.ucc.edu.ArqSW.Parcial.jira.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import ar.ucc.edu.ArqSW.Parcial.jira.dto.UserRequestDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.UserResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
    private UserService userService;

    @RequestMapping(method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<UserResponseDto> getAllUser()
    {
        return userService.getAllUser();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> lookupStateById(@PathVariable("id") Long id)
    {
        try {
			UserResponseDto dto = userService.getUserById(id);
			return new ResponseEntity<Object>(dto, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("404", "No se encontró la entidad buscada");
			return new ResponseEntity<Object>(exDto, HttpStatus.NOT_FOUND);
		} catch (BadRequestException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("400", "El parámetro id ingresado no es válido");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		}
    }
    
    @RequestMapping(value="usersFromProject/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> UsersFromProject(@PathVariable("id") Long id)
    {
        try {
        	List<UserResponseDto> dto = userService.getUsersByProject(id);
			return new ResponseEntity<Object>(dto, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("404", "No se encontró la entidad buscada");
			return new ResponseEntity<Object>(exDto, HttpStatus.NOT_FOUND);
		} catch (BadRequestException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("400", "El parámetro id ingresado no es válido");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		}
    }
    
    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<Object> saveSocio(@RequestBody UserRequestDto request)
    {
        try {
			UserResponseDto dto = userService.insertUser(request);
			return new ResponseEntity<Object>(dto, HttpStatus.OK);
			
		} catch (BadRequestException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("400", "Error en la solicitud");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		}
    } 
}
