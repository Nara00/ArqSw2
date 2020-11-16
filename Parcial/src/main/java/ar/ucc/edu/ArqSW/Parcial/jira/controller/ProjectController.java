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

import ar.ucc.edu.ArqSW.Parcial.jira.dto.CommentRequestDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.CommentResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.ProjectRequestDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.ProjectResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.service.CommentService;
import ar.ucc.edu.ArqSW.Parcial.jira.service.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {

    	@Autowired
    private ProjectService projectService;

    @RequestMapping(method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<ProjectResponseDto> getAllProject()
    {
        return projectService.getAllProject();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ProjectResponseDto lookupStateById(@PathVariable("id") Long id)
    {
        return projectService.getProjectById(id);
    }
    
    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody ProjectResponseDto saveProject(@RequestBody ProjectRequestDto request)
    {
        return projectService.insertProject(request);
    }

}
