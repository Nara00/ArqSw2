package ar.ucc.edu.ArqSW.Parcial.jira.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import ar.ucc.edu.ArqSW.Parcial.common.dao.GenericDaoImp;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Comment;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Project;
import ar.ucc.edu.ArqSW.Parcial.jira.model.User;

@Repository
public class UserDaoImp extends GenericDaoImp<User, Long> implements UserDao{
	

}
