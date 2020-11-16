package ar.ucc.edu.ArqSW.Parcial.jira.service;

import java.util.List;
import java.util.ArrayList;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import ar.ucc.edu.ArqSW.Parcial.common.dto.ModelDtoConverter;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.TaskDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.TaskRequestDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.TaskResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Task;

@Service
@Transactional
public class TaskService {
    
    @Autowired
	private TaskDao taskDao;

    public TaskResponseDto getTaskById(Long id) {
        Task task = taskDao.load(id);
                
        TaskResponseDto response = (TaskResponseDto) new ModelDtoConverter().convertToDto(task, new TaskResponseDto());	
        return response;
    }
	
	public List<TaskResponseDto> getAllTask() {
		List<Task> tasks = taskDao.getAll();
		
		List<TaskResponseDto> response = new ArrayList<TaskResponseDto>();
		 
		for (Task task : tasks) {
			response.add((TaskResponseDto) new ModelDtoConverter().convertToDto(task, new TaskResponseDto()));
		}
		
		return response;
	}
	
	public TaskResponseDto insertTask(TaskRequestDto request) {
		
		Task task = (Task) new ModelDtoConverter().convertToEntity(new Task(), request);
		
		taskDao.insert(task);
		
		TaskResponseDto response = (TaskResponseDto) new ModelDtoConverter().convertToDto(task, new TaskResponseDto());	
		
		return response;
	}
}
