package ar.ucc.edu.ArqSW.Parcial.jira.dao;

import org.springframework.stereotype.Repository;

import ar.ucc.edu.ArqSW.Parcial.common.dao.GenericDaoImp;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Comment;

@Repository
public class CommentDaoImp extends GenericDaoImp<Comment, Long> implements CommentDao{

}