package ar.ucc.edu.ArqSW.Parcial.jira.dto;
import java.util.Date;
import java.util.Set;

import ar.ucc.edu.ArqSW.Parcial.common.dto.DtoEntity;
import ar.ucc.edu.ArqSW.Parcial.jira.model.Priority;

public class TaskResponseDto implements DtoEntity{

	private Long id;
	private String task_name;
	private String description;
    private Date last_update;
	private Long userid;
	private Long projectid;
	private String project_name;
	private Long stateid;
	private String state_name;
	private Priority priority;
	private Set<CommentResponseDto> comment;
	
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
	public Date getLast_update() {
		return last_update;
	}
	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getProjectid() {
		return projectid;
	}
	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}
	public Long getStateid() {
		return stateid;
	}
	public void setStateid(Long stateid) {
		this.stateid = stateid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public Set<CommentResponseDto> getComment() {
		return comment;
	}
	public void setComment(Set<CommentResponseDto> comment) {
		this.comment = comment;
	}
	
	
}
