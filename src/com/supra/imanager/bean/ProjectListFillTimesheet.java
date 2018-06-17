package com.supra.imanager.bean;

import java.util.List;

public class ProjectListFillTimesheet {

	String username;
	String projectId;
	String projectName;
	String projectStatus;
	List<TaskDetails> listOfTask;

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

	public List<TaskDetails> getListOfTask() {
		return listOfTask;
	}

	public void setListOfTask(List<TaskDetails> listOfTask) {
		this.listOfTask = listOfTask;
	}

	@Override
	public String toString() {
		return "{ username:" + username + ", projectId:" + projectId + ", projectName:" + projectName
				+ ", projectStatus:" + projectStatus + ", listOfTask:" + listOfTask + "}";
	}

}
