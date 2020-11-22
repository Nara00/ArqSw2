package ar.ucc.edu.ArqSW.Parcial.jira.dao;


import org.springframework.stereotype.Repository;

import ar.ucc.edu.ArqSW.Parcial.common.dao.GenericDao;
import ar.ucc.edu.ArqSW.Parcial.jira.model.User;

@Repository
public interface UserDao extends GenericDao<User, Long>{
	

}
