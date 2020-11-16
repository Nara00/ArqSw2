package ar.ucc.edu.ArqSW.Parcial.jira.dto;

import ar.ucc.edu.ArqSW.Parcial.common.dto.DtoEntity;

public class CommentResponseDto implements DtoEntity{
	
	private Long id;

	private String description;
	
	private Long taskid;
	
	private Long userid;
    
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getTaskid() {
		return taskid;
	}
	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	

}
