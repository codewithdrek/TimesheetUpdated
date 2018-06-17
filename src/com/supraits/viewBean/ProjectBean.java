package com.supraits.viewBean;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
@Component
public class ProjectBean {
	private String projectId;
	private String projectName;
	private String projectDesc;
	private String projectOwner;
	private String projectType;
	@DateTimeFormat
	private String creationDate;
	private String projectStatus;
	private List<TaskBean> taskList;
	private List<LoginBean> userList;
	@DateTimeFormat
	private String startDate;
	@DateTimeFormat
	private String endDate;
	private String timesheetApprover;
	
	
	public String getTimesheetApprover() {
		return timesheetApprover;
	}
	public void setTimesheetApprover(String timesheetApprover) {
		this.timesheetApprover = timesheetApprover;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public List<TaskBean> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<TaskBean> taskList) {
		this.taskList = taskList;
	}
	public List<LoginBean> getUserList() {
		return userList;
	}
	public void setUserList(List<LoginBean> userList) {
		this.userList = userList;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDesc() {
		return projectDesc;
	}
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}
	public String getProjectOwner() {
		return projectOwner;
	}
	public void setProjectOwner(String projectOwner) {
		this.projectOwner = projectOwner;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	
}
