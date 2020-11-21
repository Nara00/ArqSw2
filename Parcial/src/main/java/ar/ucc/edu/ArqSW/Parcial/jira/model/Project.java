package ar.ucc.edu.ArqSW.Parcial.jira.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ar.ucc.edu.ArqSW.Parcial.common.model.GenericObject;

@Entity
@Table(name = "PROJECT")
public class Project extends GenericObject {
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "NAME")
	private String name;
	
	@Size(min = 1, max = 250)
	@Column(name = "DESCRIPTION")
	private String description;
	
	@OneToMany(mappedBy="project", fetch = FetchType.LAZY)
	private Set<Task> task;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<User> user;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
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

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}
	
	public void addUser(User user) {
		this.user.add(user);
	}
	
	
	
	
}
