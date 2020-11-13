package ar.ucc.edu.ArqSW.Parcial.jira.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ar.ucc.edu.ArqSW.Parcial.common.model.GenericObject;

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
	
	@NotNull
	@Column(name = "LAST_UPDATE")
	private Date last_update;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	private User user;
	
	@OneToMany(mappedBy="task", fetch = FetchType.LAZY)
	private Set<State> state;
	
	@Enumerated(value = EnumType.ORDINAL)
	@Column(name = "PRIORITY")
	private Priority priority;
	
	@OneToMany(mappedBy="task", fetch = FetchType.LAZY)
	private Set<Comment> comment;
	
	
	
}
