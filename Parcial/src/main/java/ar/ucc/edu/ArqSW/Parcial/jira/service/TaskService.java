package ar.ucc.edu.ArqSW.Parcial.jira.service;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import ar.ucc.edu.ArqSW.Parcial.common.dto.ModelDtoConverter;
import ar.ucc.edu.ArqSW.Parcial.common.exception.BadRequestException;
import ar.ucc.edu.ArqSW.Parcial.common.exception.EntityNotFoundException;
import ar.ucc.edu.ArqSW.Parcial.common.exception.ForbiddenException;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.CommentDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.ProjectDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.StateDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.TaskDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.UserDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.CommentResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.TaskRequestDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.TaskResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.UserResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Task;
import ar.ucc.edu.ArqSW.Parcial.jira.model.User;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Comment;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Project;
import java.util.stream.Stream;

//Completar todos los response
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

	@Autowired
	private CommentDao commentDao;

	public TaskResponseDto getTaskById(Long id) throws EntityNotFoundException, BadRequestException {
		if (id <= 0) {
			throw new BadRequestException();
		}
		Task task = taskDao.load(id);
		taskDao.insert(task);

		TaskResponseDto response = new TaskResponseDto();

		return taskResponseGenerator(response, task);
	}

	public List<TaskResponseDto> getAllTask() {
		List<Task> tasks = taskDao.getAll();

		List<TaskResponseDto> response = new ArrayList<TaskResponseDto>();

		for (Task task : tasks) {
			response.add(taskResponseGenerator(new TaskResponseDto(), task));
		}

		return response;
	}

	public TaskResponseDto insertTask(TaskRequestDto request) throws EntityNotFoundException, BadRequestException {

		Task task = new Task();

		task.setStartdate(Calendar.getInstance().getTime());
		task.setLast_update(Calendar.getInstance().getTime());
		task.setTask_name(request.getTask_name());
		task.setDescription(request.getDescription());
		task.setProject(projectDao.load(request.getProjectid()));
		task.setPriority(request.getPriority());

		if (request.getStateid() != null)
			task.setState(stateDao.load(request.getStateid()));
		else {
			if (request.getUserid() != null)
				task.setState(stateDao.load((long) 2));
			else {
				task.setState(stateDao.load((long) 1));
			}
		}
		if (request.getUserid() != null)
			task.setUser(userDao.load(request.getUserid()));

		taskDao.insert(task);

		Comment comment = new Comment();
		comment.setDescription("Se creó una nueva tarea / fecha: " + Calendar.getInstance().getTime());
		comment.setTask(task);
		commentDao.insert(comment);

		TaskResponseDto response = new TaskResponseDto();

		return taskResponseGenerator(response, task);

	}

	public TaskResponseDto changeState(Long id, Long request) throws EntityNotFoundException, BadRequestException {
		Task task = taskDao.load(id);
		Comment comment = new Comment();
		comment.setDescription("Se cambió el estado de la tarea de " + task.getState().getId() + " a " + request
				+ " / fecha " + Calendar.getInstance().getTime());
		task.setState(stateDao.load(request));
		task.setLast_update(Calendar.getInstance().getTime());
		taskDao.update(task);
		comment.setTask(task);
		commentDao.insert(comment);

		Task task_after_update = taskDao.load(id);
		TaskResponseDto response = new TaskResponseDto();

		return taskResponseGenerator(response, task_after_update);
	}

	public TaskResponseDto changeUser(Long id, Long request)
			throws EntityNotFoundException, ForbiddenException, BadRequestException {
		Task task = taskDao.load(id);
		Comment comment = new Comment();
		comment.setDescription("Se cambió el usuario asignado de " + task.getUser().getId() + " a " + request
				+ " /fecha: " + Calendar.getInstance().getTime());
		Project project = task.getProject();
		Set<User> project_users = project.getUser();
		User user = userDao.load(request);
		if (!project_users.contains(user))
			throw new ForbiddenException();

		task.setUser(user);
		task.setState(stateDao.load((long) 2));
		task.setLast_update(Calendar.getInstance().getTime());
		taskDao.update(task);
		
		comment.setTask(task);
		commentDao.insert(comment);
		Task task_after_update = taskDao.load(id);
		TaskResponseDto response = new TaskResponseDto();

		return taskResponseGenerator(response, task_after_update);
	}

	public TaskResponseDto taskResponseGenerator(TaskResponseDto response, Task task) {

		response.setId(task.getId());
		response.setLast_update(task.getLast_update());
		response.setTask_name(task.getTask_name());
		response.setDescription(task.getDescription());
		response.setPriority(task.getPriority());
		response.setProject_name(task.getProject().getName());
		response.setProjectid(task.getProject().getId());
		response.setState_name(task.getState().getName());
		response.setStateid(task.getState().getId());
//		try
//		{
//			response.setUserid(task.getUser().getId());
//		}
//		catch(NullPointerException())
//		{
//			
//		}			

		List<Comment> comments = commentDao.getAllByTaskId(task.getId());

		List<CommentResponseDto> comment_response = new ArrayList<CommentResponseDto>();

		CommentResponseDto crdto = new CommentResponseDto();

		for (Comment comment : comments) {
			crdto.setId(comment.getId());
			crdto.setDescription(comment.getDescription());
			crdto.setTaskid(comment.getTask().getId());
			//crdto.setUserid(comment.getUser().getId());
			comment_response.add(crdto);
		}
		response.setComment(comment_response);
		return response;
	}
}