package ar.ucc.edu.ArqSW.Parcial.jira.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.ucc.edu.ArqSW.Parcial.common.dto.ModelDtoConverter;

import ar.ucc.edu.ArqSW.Parcial.jira.dao.CommentDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.CommentRequestDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.CommentResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Comment;

@Service
@Transactional
public class CommentService {

@Autowired
	private CommentDao commentDao;

    public CommentResponseDto getCommentById(Long id) {
    	Comment comment = commentDao.load(id);
                
    	CommentResponseDto response = (CommentResponseDto) new ModelDtoConverter().convertToDto(comment, new CommentResponseDto());	
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
	
	public CommentResponseDto insertComment(CommentRequestDto request) {
		
		Comment comment = (Comment) new ModelDtoConverter().convertToEntity(new Comment(), request);
		
		commentDao.insert(comment);
		
		CommentResponseDto response = (CommentResponseDto) new ModelDtoConverter().convertToDto(comment, new CommentResponseDto());	
		
		return response;
	}

}
