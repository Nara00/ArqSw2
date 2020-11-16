package ar.ucc.edu.ArqSW.Parcial.jira.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ar.ucc.edu.ArqSW.Parcial.jira.dto.StateRequestDto;
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
    public @ResponseBody StateResponseDto lookupStateById(@PathVariable("id") Long id)
    {
        return stateService.getStateById(id);
    }
    
    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody StateResponseDto saveState(@RequestBody StateRequestDto request)
    {
        return stateService.insertState(request);
    }

}
