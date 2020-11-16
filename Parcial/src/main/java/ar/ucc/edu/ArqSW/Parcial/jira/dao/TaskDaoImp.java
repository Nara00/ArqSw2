package ar.ucc.edu.ArqSW.Parcial.jira.dao;

import org.springframework.stereotype.Repository;

import ar.ucc.edu.ArqSW.Parcial.common.dao.GenericDaoImp;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Task;

@Repository
public class TaskDaoImp extends GenericDaoImp<Task, Long> implements TaskDao{

}