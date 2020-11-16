package ar.ucc.edu.ArqSW.Parcial.jira.dto;

import ar.ucc.edu.ArqSW.Parcial.common.dto.DtoEntity;

public class StateResponseDto implements DtoEntity{
	
	private Long id;
	private String name;
	//private Set<Task> task;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
