package ar.ucc.edu.ArqSW.Parcial.jira.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ar.ucc.edu.ArqSW.Parcial.common.model.GenericObject;

@Entity
@Table(name = "TASK")
public class Task extends GenericObject {
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "TASK_NAME")
	private String task_name;
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "DESCRIPTION")
	private String description;
	
	@NotNull
	@Column(name = "START_DATE")
	private Date startdate;
	
	@Column(name = "LAST_UPDATE")
	private Date last_update;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_ID", nullable = true)
	private User user;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PROJECT_ID")
	private Project project;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="STATE_ID")
	private State state;
	
	@NotNull
	@Enumerated(value = EnumType.ORDINAL)
	@Column(name = "PRIORITY")
	private Priority priority;
	
	@OneToMany(mappedBy="task", fetch = FetchType.LAZY)
	private Set<Comment> comment;

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getLast_update() {
		return last_update;
	}

	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Set<Comment> getComment() {
		return comment;
	}

	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}
	
}
