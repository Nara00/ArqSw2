package ar.ucc.edu.ArqSW.Parcial.jira.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ar.ucc.edu.ArqSW.Parcial.common.model.GenericObject;


@Entity
@Table(name = "STATE")
public class State extends GenericObject{
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "NAME")
	private String name;
	
	@OneToMany(mappedBy="state", fetch = FetchType.LAZY)
	private Set<Task> task;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Task> getTask() {
		return task;
	}

	public void setTask(Set<Task> task) {
		this.task = task;
	}
	


}
