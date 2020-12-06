package ar.ucc.edu.ArqSW.Parcial.jira.dto;

import ar.ucc.edu.ArqSW.Parcial.common.dto.DtoEntity;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Priority;

public class TaskRequestDto implements DtoEntity{

	private String taskName;
	
	private String description;
	
	private Long userId;
	
	private Long projectId;
	
	private Long stateId;
	
	private Priority priority;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	
			
}
