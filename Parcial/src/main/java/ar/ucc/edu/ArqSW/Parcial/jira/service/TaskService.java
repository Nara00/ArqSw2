package ar.ucc.edu.ArqSW.Parcial.jira.service;

import java.util.List;
import java.util.Set;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import ar.ucc.edu.ArqSW.Parcial.common.exception.BadRequestException;
import ar.ucc.edu.ArqSW.Parcial.common.exception.EntityNotFoundException;
import ar.ucc.edu.ArqSW.Parcial.common.exception.ForbiddenException;
import ar.ucc.edu.ArqSW.Parcial.common.exception.InvalidTransitionException;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.CommentDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.ProjectDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.StateDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.TaskDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.UserDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.CommentResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.TaskRequestDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.TaskResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Task;
import ar.ucc.edu.ArqSW.Parcial.jira.model.User;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Comment;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Project;


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

		task.setStartDate(Calendar.getInstance().getTime());
		task.setLastUpdate(Calendar.getInstance().getTime());
		task.setTaskName(request.getTaskName());
		task.setDescription(request.getDescription());
		task.setProject(projectDao.load(request.getProjectId()));
		task.setPriority(request.getPriority());

		if (request.getStateId() != null)
			task.setState(stateDao.load(request.getStateId()));
		else {
			if (request.getUserId() != null)
				task.setState(stateDao.load((long) 2));
			else {
				task.setState(stateDao.load((long) 1));
			}
		}
		if (request.getUserId() != null)
			task.setUser(userDao.load(request.getUserId()));

		taskDao.insert(task);

		Comment comment = new Comment();
		comment.setDescription("Se creó una nueva tarea / fecha: " + Calendar.getInstance().getTime());
		comment.setTask(task);
		commentDao.insert(comment);

		TaskResponseDto response = new TaskResponseDto();

		return taskResponseGenerator(response, task);

	}

	public TaskResponseDto changeState(Long id, Long request)
			throws EntityNotFoundException, BadRequestException, InvalidTransitionException {
		Task task = taskDao.load(id);
		if ((task.getState().getId() == 4) || (request == 1)) {
			throw new InvalidTransitionException();
		}
		Comment comment = new Comment();
		comment.setDescription("Se cambió el estado de la tarea de " + task.getState().getId() + " a " + request
				+ " / fecha " + Calendar.getInstance().getTime());
		task.setState(stateDao.load(request));
		task.setLastUpdate(Calendar.getInstance().getTime());
		taskDao.update(task);
		comment.setTask(task);
		commentDao.insert(comment);

		Task task_after_update = taskDao.load(id);
		TaskResponseDto response = new TaskResponseDto();

		return taskResponseGenerator(response, task_after_update);
	}

	public TaskResponseDto changeUser(Long id, Long request)
			throws EntityNotFoundException, ForbiddenException, BadRequestException, InvalidTransitionException {
		Task task = taskDao.load(id);
		if (task.getState().getId() == 4)
			throw new InvalidTransitionException();
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
		task.setLastUpdate(Calendar.getInstance().getTime());
		taskDao.update(task);

		comment.setTask(task);
		commentDao.insert(comment);
		Task task_after_update = taskDao.load(id);
		TaskResponseDto response = new TaskResponseDto();

		return taskResponseGenerator(response, task_after_update);
	}

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		
	private TaskResponseDto taskResponseGenerator(TaskResponseDto response, Task task) {

		response.setId(task.getId());
		response.setLastUpdate(sdf.format(task.getLastUpdate()));
		response.setTaskName(task.getTaskName());
		response.setDescription(task.getDescription());
		response.setPriority(task.getPriority());
		response.setProjectName(task.getProject().getName());
		response.setProjectId(task.getProject().getId());
		response.setStateName(task.getState().getName());
		response.setStateId(task.getState().getId());
		if (task.getUser() != null)
		{
			response.setUserId(task.getUser().getId());
			response.setUserName(task.getUser().getName());
			response.setUserLastname(task.getUser().getLastname());
		}

		List<Comment> comments = commentDao.getAllByTaskId(task.getId());

		List<CommentResponseDto> comment_response = new ArrayList<CommentResponseDto>();

		CommentResponseDto crdto = new CommentResponseDto();

		for (Comment comment : comments) {
			crdto.setId(comment.getId());
			crdto.setDescription(comment.getDescription());
			crdto.setTaskId(comment.getTask().getId());
			if (comment.getUser() != null)
				crdto.setUserId(comment.getUser().getId());
			comment_response.add(crdto);
		}
		response.setComment(comment_response);
		return response;
	}
}