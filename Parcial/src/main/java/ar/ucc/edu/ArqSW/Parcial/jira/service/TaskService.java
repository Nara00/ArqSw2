package ar.ucc.edu.ArqSW.Parcial.jira.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import ar.ucc.edu.ArqSW.Parcial.common.dto.ModelDtoConverter;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.ProjectDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.StateDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.TaskDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.UserDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.TaskRequestDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.TaskResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Task;

@Service
@Transactional
public class TaskService {

	@Autowired
	private TaskDao taskDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private StateDao stateDao;
	
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

		Task task = new Task();

		task.setStartdate(Calendar.getInstance().getTime());
		task.setLast_update(Calendar.getInstance().getTime());
		task.setTask_name(request.getTask_name());
		task.setDescription(request.getDescription());
		task.setUser(userDao.load(request.getUserid()));
		task.setProject(projectDao.load(request.getProjectid()));
		task.setState(stateDao.load(request.getStateid()));
		task.setPriority(request.getPriority());

		taskDao.insert(task);

		TaskResponseDto response = new TaskResponseDto();

		response.setId(task.getId());
		response.setLast_update(task.getLast_update());
		response.setTask_name(task.getTask_name());
		response.setDescription(task.getDescription());
		response.setPriority(task.getPriority());
		response.setUserid(task.getUser().getId());
		response.setProject_name(task.getProject().getName());
		response.setProjectid(task.getProject().getId());
		response.setState_name(task.getState().getName());
		response.setStateid(task.getState().getId());
	
		return response;
	}
}
