package ar.ucc.edu.ArqSW.Parcial.jira.dto;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import ar.ucc.edu.ArqSW.Parcial.common.dto.DtoEntity;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Priority;

public class TaskResponseDto implements DtoEntity{

	private Long id;
	
	private String taskName; //taskName para clase task_name para Json
	
	private String description;
    
	private String lastUpdate;
	
	private Long userId;
	
	private String userName;
	
	private String userLastname;
	
	private Long projectId;
	
	private String projectName;
	
	private Long stateId;
	
	private String stateName;
	
	private Priority priority;
	
	private List<CommentResponseDto> comment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@JsonProperty("task_name")
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
	
	@JsonProperty("last_update")
	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	@JsonProperty("user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@JsonProperty("user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@JsonProperty("user_lastname")
	public String getUserLastname() {
		return userLastname;
	}

	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}
	@JsonProperty("project_id")
	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	@JsonProperty("project_name")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	@JsonProperty("state_id")
	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	@JsonProperty("state_name")
	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public List<CommentResponseDto> getComment() {
		return comment;
	}

	public void setComment(List<CommentResponseDto> comment) {
		this.comment = comment;
	}

	
}
