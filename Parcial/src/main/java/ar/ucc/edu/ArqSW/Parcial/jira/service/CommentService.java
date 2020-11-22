package ar.ucc.edu.ArqSW.Parcial.jira.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.ucc.edu.ArqSW.Parcial.common.dto.ModelDtoConverter;
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

	public CommentResponseDto getCommentById(Long id) throws EntityNotFoundException {
		Comment comment = commentDao.load(id);

		CommentResponseDto response = (CommentResponseDto) new ModelDtoConverter().convertToDto(comment,
				new CommentResponseDto());
		return response;
	}

	public List<CommentResponseDto> getAllComment() {
		List<Comment> comments = commentDao.getAll();

		List<CommentResponseDto> response = new ArrayList<CommentResponseDto>();

		for (Comment comment : comments) {
			response.add((CommentResponseDto) new ModelDtoConverter().convertToDto(comment, new CommentResponseDto()));
		}

		return response;
	}

	public CommentResponseDto insertComment(CommentRequestDto request) throws EntityNotFoundException {

		Comment comment = new Comment();

		comment.setDescription(request.getDescription());
		comment.setTask(taskDao.load(request.getTaskid()));
		comment.setUser(userDao.load(request.getUserid()));

		commentDao.insert(comment);

		CommentResponseDto response = new CommentResponseDto();

		response.setId(comment.getId());
		response.setDescription(comment.getDescription());
		response.setTaskid(comment.getTask().getId());
		response.setUserid(comment.getUser().getId());

		return response;

	}

}
