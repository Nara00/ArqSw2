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

import ar.ucc.edu.ArqSW.Parcial.jira.dto.TaskRequestDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.TaskResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.service.TaskService;

@Controller
@RequestMapping("/task")

public class TaskController {
	
	@Autowired
    private TaskService taskService;

    @RequestMapping(method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<TaskResponseDto> getAllTask()
    {
        return taskService.getAllTask();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody TaskResponseDto lookupStateById(@PathVariable("id") Long id)
    {
        return taskService.getTaskById(id);
    }
    
    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody TaskResponseDto saveTask(@RequestBody TaskRequestDto request)
    {
        return taskService.insertTask(request);
    }
    
    @RequestMapping(value="/changeStateFromTask/{id}", method=RequestMethod.PUT, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody TaskResponseDto changeState(@PathVariable("id") Long id, @RequestBody Long request)
    {
        return taskService.changeState(id, request);
    }
    
    @RequestMapping(value="/setUserToTask/{id}", method=RequestMethod.PUT, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody TaskResponseDto changeUser(@PathVariable("id") Long id, @RequestBody Long request)
    {
        return taskService.changeUser(id, request);
    }

}
