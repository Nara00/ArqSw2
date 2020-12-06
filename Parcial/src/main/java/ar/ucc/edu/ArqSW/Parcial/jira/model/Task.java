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
	private String taskName;
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "DESCRIPTION")
	private String description;
	
	@NotNull
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Column(name = "LAST_UPDATE")
	private Date lastUpdate;
	

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

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String task_name) {
		this.taskName = task_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startdate) {
		this.startDate = startdate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date last_update) {
		this.lastUpdate = last_update;
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
