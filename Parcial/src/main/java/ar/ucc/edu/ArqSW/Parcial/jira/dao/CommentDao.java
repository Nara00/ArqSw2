package ar.ucc.edu.ArqSW.Parcial.jira.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ar.ucc.edu.ArqSW.Parcial.common.dao.GenericDao;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Comment;

@Repository
public interface CommentDao extends GenericDao<Comment, Long>{
	
	public List<Comment> getAllByTaskId(Long taskid);

}
