package ar.ucc.edu.ArqSW.Parcial.jira.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.ucc.edu.ArqSW.Parcial.common.exception.BadRequestException;
import ar.ucc.edu.ArqSW.Parcial.common.exception.EntityNotFoundException;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.CommentDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.TaskDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.UserDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.CommentRequestDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.CommentResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Comment;

@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private TaskDao taskDao;

	@Autowired
	private UserDao userDao;

	public CommentResponseDto getCommentById(Long id) throws EntityNotFoundException, BadRequestException {
		if (id <= 0)
		{
			throw new BadRequestException();
		}
		Comment comment = commentDao.load(id);
		
		CommentResponseDto response = new CommentResponseDto();

		return commentResponseGenerator(response, comment);
	}

	public List<CommentResponseDto> getAllComment() {
		List<Comment> comments = commentDao.getAll();

		List<CommentResponseDto> response = new ArrayList<CommentResponseDto>();

		for (Comment comment : comments) {
			response.add(commentResponseGenerator( new CommentResponseDto(),comment));
		}

		return response;
	}

	public CommentResponseDto insertComment(CommentRequestDto request) throws EntityNotFoundException, BadRequestException {

		Comment comment = new Comment();
		
		comment.setDescription(request.getDescription());
		comment.setTask(taskDao.load(request.getTaskId()));
		comment.setUser(userDao.load(request.getUserId()));
		
		try {
			commentDao.insert(comment);
			}
			catch(BadRequestException e){
				throw new BadRequestException();
			}

		CommentResponseDto response = new CommentResponseDto();

		return commentResponseGenerator(response, comment);

	}
	
	private CommentResponseDto commentResponseGenerator(CommentResponseDto response, Comment comment)
	{
		response.setId(comment.getId());
		response.setDescription(comment.getDescription());
		response.setTaskId(comment.getTask().getId());
		if (comment.getUser() != null)
			response.setUserId(comment.getUser().getId());

		return response;
	}

}
