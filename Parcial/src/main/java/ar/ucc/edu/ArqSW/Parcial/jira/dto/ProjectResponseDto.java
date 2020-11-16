package ar.ucc.edu.ArqSW.Parcial.jira.dto;

import ar.ucc.edu.ArqSW.Parcial.common.dto.DtoEntity;

public class ProjectResponseDto implements DtoEntity{
	
	private Long id;
    private String name;
	private String description;
	//private Set<Task> task;
	//private Set<User> user;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
}
