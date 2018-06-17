package com.supra.imanager.bean;

public class TaskDetails {

	String taskId;
	String taskName;

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
		return "{ taskId:" + taskId + ", taskName:" + taskName + "}";
	}

}
