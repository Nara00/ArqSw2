package ar.ucc.edu.ArqSW.Parcial.jira.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ar.ucc.edu.ArqSW.Parcial.common.model.GenericObject;

public class State extends GenericObject{
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="TASK_ID")
	private Task task;
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "NAME")
	private String name;
	
	@NotNull
	@Column(name = "START_DATE")
	private Date startdate;
	
	@NotNull
	@Column(name = "LAST_UPDATE")
	private Date last_update;
	
	
	

}
