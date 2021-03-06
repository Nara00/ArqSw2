package ar.ucc.edu.ArqSW.Parcial.jira.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.ucc.edu.ArqSW.Parcial.common.dto.ModelDtoConverter;
import ar.ucc.edu.ArqSW.Parcial.common.exception.BadRequestException;
import ar.ucc.edu.ArqSW.Parcial.common.exception.EntityNotFoundException;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.ProjectDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.UserDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.ProjectRequestDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.ProjectResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Project;

@Service
@Transactional
public class ProjectService {
	
    @Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private UserDao userDao;

    public ProjectResponseDto getProjectById(Long id) throws EntityNotFoundException, BadRequestException {
		if (id <= 0)
		{
			throw new BadRequestException();
		}
    	Project project = projectDao.load(id);
                
    	ProjectResponseDto response = (ProjectResponseDto) new ModelDtoConverter().convertToDto(project, new ProjectResponseDto());	
        return response;
    }
	
    public List<ProjectResponseDto> getProjectByName(String name) {
    	List<Project> projects = projectDao.findByName(name);
		
		List<ProjectResponseDto> response = new ArrayList<ProjectResponseDto>();
		 
		for (Project project : projects) {
			response.add((ProjectResponseDto) new ModelDtoConverter().convertToDto(project, new ProjectResponseDto()));
		}
		
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
	
	public ProjectResponseDto insertProject(ProjectRequestDto request) throws BadRequestException {
		
		Project project = (Project) new ModelDtoConverter().convertToEntity(new Project(), request);
		
		try {
			projectDao.insert(project);
			}
			catch(BadRequestException e){
				throw new BadRequestException();
			}
		
		ProjectResponseDto response = (ProjectResponseDto) new ModelDtoConverter().convertToDto(project, new ProjectResponseDto());	
		
		return response;
	}
	
	public ProjectResponseDto setUser(Long id, Long request) throws EntityNotFoundException, BadRequestException
	{
	Project project = projectDao.load(id);
	project.addUser(userDao.load(request));
	
	projectDao.insert(project);
	
	Project project_after_update = projectDao.load(id);
	
	ProjectResponseDto response = (ProjectResponseDto) new ModelDtoConverter().convertToDto(project_after_update, new ProjectResponseDto());	
	
	return response;
	
	}

}
