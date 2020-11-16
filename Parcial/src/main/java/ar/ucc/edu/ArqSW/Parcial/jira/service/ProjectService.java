package ar.ucc.edu.ArqSW.Parcial.jira.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.ucc.edu.ArqSW.Parcial.common.dto.ModelDtoConverter;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.ProjectDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.ProjectRequestDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.ProjectResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Project;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Task;

@Service
@Transactional
public class ProjectService {
	
    @Autowired
	private ProjectDao projectDao;

    public ProjectResponseDto getProjectById(Long id) {
    	Project project = projectDao.load(id);
                
    	ProjectResponseDto response = (ProjectResponseDto) new ModelDtoConverter().convertToDto(project, new ProjectResponseDto());	
        return response;
    }
	
	public List<ProjectResponseDto> getAllProject() {
		List<Project> projects = projectDao.getAll();
		
		List<ProjectResponseDto> response = new ArrayList<ProjectResponseDto>();
		 
		for (Project project : projects) {
			response.add((ProjectResponseDto) new ModelDtoConverter().convertToDto(project, new ProjectResponseDto()));
		}
		
		return response;
	}
	
	public ProjectResponseDto insertProject(ProjectRequestDto request) {
		
		Project project = (Project) new ModelDtoConverter().convertToEntity(new Task(), request);
		
		projectDao.insert(project);
		
		ProjectResponseDto response = (ProjectResponseDto) new ModelDtoConverter().convertToDto(project, new ProjectResponseDto());	
		
		return response;
	}

}
