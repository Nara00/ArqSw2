package ar.ucc.edu.ArqSW.Parcial.jira.dao;

import org.springframework.stereotype.Repository;

import ar.ucc.edu.ArqSW.Parcial.common.dao.GenericDaoImp;
import ar.ucc.edu.ArqSW.Parcial.jira.model.User;

@Repository
public class UserDaoImp extends GenericDaoImp<User, Long> implements UserDao{

}
