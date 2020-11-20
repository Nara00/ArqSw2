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

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ar.ucc.edu.ArqSW.Parcial.common.model.GenericObject;

@Entity
@Table(name = "USER")
public class User extends GenericObject{
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "NAME")
	private String name;
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "LAST_NAME")
	private String lastname;
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "EMAIL")
	private String email;

	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private Set<Task> task;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private Set<Task> comment;

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Project> project;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Task> getTask() {
		return task;
	}

	public void setTask(Set<Task> task) {
		this.task = task;
	}

	public Set<Task> getComment() {
		return comment;
	}

	public void setComment(Set<Task> comment) {
		this.comment = comment;
	}

	public Set<Project> getProject() {
		return project;
	}

	public void setProject(Set<Project> project) {
		this.project = project;
	}

}
