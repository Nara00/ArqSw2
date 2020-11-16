package ar.ucc.edu.ArqSW.Parcial.jira.dto;
import java.util.Date;
import ar.ucc.edu.ArqSW.Parcial.common.dto.DtoEntity;

public class TaskResponseDto implements DtoEntity{

	private Long id;
	private String task_name;
	private String description;
    private Date last_update;
	private Long userid;
	private Long projectid;
	private Long stateid;
	//private Set<Comment> comment;
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

	
}
