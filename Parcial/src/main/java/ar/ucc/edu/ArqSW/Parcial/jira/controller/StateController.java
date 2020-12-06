package ar.ucc.edu.ArqSW.Parcial.jira.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.ucc.edu.ArqSW.Parcial.common.dto.GenericExceptionDto;
import ar.ucc.edu.ArqSW.Parcial.common.exception.BadRequestException;
import ar.ucc.edu.ArqSW.Parcial.common.exception.EntityNotFoundException;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.StateResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.service.StateService;

@Controller
@RequestMapping("/state")
public class StateController {
	@Autowired
    private StateService stateService;

    @RequestMapping(method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<StateResponseDto> getAllState()
    {
        return stateService.getAllState();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> lookupStateById(@PathVariable("id") Long id) throws EntityNotFoundException
    {
        try {
			StateResponseDto dto = stateService.getStateById(id);
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
    
//    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(code = HttpStatus.CREATED)
//    public @ResponseBody StateResponseDto saveState(@RequestBody StateRequestDto request)
//    {
//        return stateService.insertState(request);
//    }

}
