package ar.ucc.edu.ArqSW.Parcial.jira.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import ar.ucc.edu.ArqSW.Parcial.common.dto.DtoEntity;

public class CommentResponseDto implements DtoEntity{
	
	private Long id;

	private String description;
	
	private Long taskId;
	
	private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("task_id")
	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	@JsonProperty("user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
    
	

}
