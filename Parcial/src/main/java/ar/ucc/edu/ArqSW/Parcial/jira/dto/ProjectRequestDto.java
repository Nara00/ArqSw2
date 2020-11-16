package ar.ucc.edu.ArqSW.Parcial.jira.dto;

import ar.ucc.edu.ArqSW.Parcial.common.dto.DtoEntity;

public class ProjectRequestDto implements DtoEntity{

	private String name;
	private String description;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
