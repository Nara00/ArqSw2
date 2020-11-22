package ar.ucc.edu.ArqSW.Parcial.jira.service;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import ar.ucc.edu.ArqSW.Parcial.common.dto.ModelDtoConverter;
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

	
	public TaskResponseDto getTaskById(Long id) throws EntityNotFoundException {
		Task task = taskDao.load(id);
		taskDao.insert(task);

		TaskResponseDto response = new TaskResponseDto();
//
//		response.setId(task.getId());
//		response.setLast_update(task.getLast_update());
//		response.setTask_name(task.getTask_name());
//		response.setDescription(task.getDescription());
//		response.setPriority(task.getPriority());
//		response.setUserid(task.getUser().getId());
//		response.setProject_name(task.getProject().getName());
//		response.setProjectid(task.getProject().getId());
//		response.setState_name(task.getState().getName());
//		response.setStateid(task.getState().getId());
//		
//		List<Comment> comments = commentDao.getAllByTaskId(task.getId());
//		
//		List<CommentResponseDto> comment_response = new ArrayList<CommentResponseDto>();
//		
//		CommentResponseDto crdto = new CommentResponseDto();
//
//		for (Comment comment : comments) {
//			System.out.println(comment.getId());
//			crdto.setId(comment.getId());
//			crdto.setDescription(comment.getDescription());
//			crdto.setTaskid(comment.getTask().getId());
//			crdto.setUserid(comment.getUser().getId());
//			System.out.println(crdto.getId());
//			comment_response.add(crdto);
//		}
//		response.setComment(comment_response);

		return taskResponseGenerator(response, task);
	}

	public List<TaskResponseDto> getAllTask() {
		List<Task> tasks = taskDao.getAll();

		List<TaskResponseDto> response = new ArrayList<TaskResponseDto>();

		for (Task task : tasks) {
			response.add((TaskResponseDto) new ModelDtoConverter().convertToDto(task, new TaskResponseDto()));
		}

		return response;
	}

	public TaskResponseDto insertTask(TaskRequestDto request) throws EntityNotFoundException {

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

//		response.setId(task.getId());
//		response.setLast_update(task.getLast_update());
//		response.setTask_name(task.getTask_name());
//		response.setDescription(task.getDescription());
//		response.setPriority(task.getPriority());
//		response.setUserid(task.getUser().getId());
//		response.setProject_name(task.getProject().getName());
//		response.setProjectid(task.getProject().getId());
//		response.setState_name(task.getState().getName());
//		response.setStateid(task.getState().getId());
//		
//		List<Comment> comments = commentDao.getAllByTaskId(task.getId());
//		
//		List<CommentResponseDto> comment_response = new ArrayList<CommentResponseDto>();
//
//		for (Comment comment : comments) {
//			comment_response.add((CommentResponseDto) new ModelDtoConverter().convertToDto(comment, new CommentResponseDto()));
//		}
//		response.setComment(comment_response);

		return taskResponseGenerator(response, task);
	}

	public TaskResponseDto changeState(Long id, Long request) throws EntityNotFoundException {
		Task task = taskDao.load(id);

		task.setState(stateDao.load(request));
		taskDao.update(task);

		Task task_after_update = taskDao.load(id);
		TaskResponseDto response = new TaskResponseDto();

//		response.setId(task_after_update.getId());
//		response.setLast_update(task_after_update.getLast_update());
//		response.setTask_name(task_after_update.getTask_name());
//		response.setDescription(task_after_update.getDescription());
//		response.setPriority(task_after_update.getPriority());
//		response.setUserid(task_after_update.getUser().getId());
//		response.setProject_name(task_after_update.getProject().getName());
//		response.setProjectid(task_after_update.getProject().getId());
//		response.setState_name(task_after_update.getState().getName());
//		response.setStateid(task_after_update.getState().getId());
		return taskResponseGenerator(response, task_after_update);
	}
	
	public TaskResponseDto changeUser(Long id, Long request) throws EntityNotFoundException, ForbiddenException {
		Task task = taskDao.load(id);
		Project project = task.getProject();
		Set<User> project_users= project.getUser();
		User user = userDao.load(request);
		if(!project_users.contains(user))
			throw new ForbiddenException();
		
		task.setUser(user);
		taskDao.update(task);
		Task task_after_update = taskDao.load(id);
		TaskResponseDto response = new TaskResponseDto();
		
//		response.setId(task_after_update.getId());
//		response.setLast_update(task_after_update.getLast_update());
//		response.setTask_name(task_after_update.getTask_name());
//		response.setDescription(task_after_update.getDescription());
//		response.setPriority(task_after_update.getPriority());
//		response.setUserid(task_after_update.getUser().getId());
//		response.setProject_name(task_after_update.getProject().getName());
//		response.setProjectid(task_after_update.getProject().getId());
//		response.setState_name(task_after_update.getState().getName());
//		response.setStateid(task_after_update.getState().getId());
		return taskResponseGenerator(response, task_after_update);
	}
	
	public TaskResponseDto taskResponseGenerator(TaskResponseDto response, Task task)
	{

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
		
		List<Comment> comments = commentDao.getAllByTaskId(task.getId());
		
		List<CommentResponseDto> comment_response = new ArrayList<CommentResponseDto>();
		
		CommentResponseDto crdto = new CommentResponseDto();

		for (Comment comment : comments) {
			System.out.println(comment.getId());
			crdto.setId(comment.getId());
			crdto.setDescription(comment.getDescription());
			crdto.setTaskid(comment.getTask().getId());
			crdto.setUserid(comment.getUser().getId());
			System.out.println(crdto.getId());
			comment_response.add(crdto);
		}
		response.setComment(comment_response);
		return response;
	}
}