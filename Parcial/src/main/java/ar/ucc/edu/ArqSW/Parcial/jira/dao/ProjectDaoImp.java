package ar.ucc.edu.ArqSW.Parcial.jira.dao;

import org.springframework.stereotype.Repository;

import ar.ucc.edu.ArqSW.Parcial.common.dao.GenericDaoImp;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Project;

@Repository
public class ProjectDaoImp extends GenericDaoImp<Project, Long> implements ProjectDao{

}