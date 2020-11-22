package ar.ucc.edu.ArqSW.Parcial.jira.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ar.ucc.edu.ArqSW.Parcial.common.dao.GenericDao;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Project;

@Repository
public interface ProjectDao extends GenericDao<Project, Long>{
	
	public List<Project> findByName(String name);
	

}
