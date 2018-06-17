package com.supra.imanager.bean;


public class ProjectListDetails {

	String username;
	String projectId;
	String projectName;
	String projectStatus;
	String taskId;
	String taskName;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	@Override
	public String toString() {
		return "{ username:" + username + ", projectId:" + projectId + ", projectName:" + projectName
				+ ", projectStatus:" + projectStatus + ", taskId:" + taskId + ", taskName=" + taskName + "}";
	}

	
	
	
	
}
