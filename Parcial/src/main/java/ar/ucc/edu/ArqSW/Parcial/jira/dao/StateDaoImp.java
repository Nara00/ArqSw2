package ar.ucc.edu.ArqSW.Parcial.jira.dao;

import org.springframework.stereotype.Repository;

import ar.ucc.edu.ArqSW.Parcial.common.dao.GenericDaoImp;
import ar.ucc.edu.ArqSW.Parcial.jira.model.State;

@Repository
public class StateDaoImp extends GenericDaoImp<State, Long> implements StateDao{

}