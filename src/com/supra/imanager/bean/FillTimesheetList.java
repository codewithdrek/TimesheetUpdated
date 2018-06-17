package com.supra.imanager.bean;

import java.util.List;

public class FillTimesheetList implements ResponseMarker{

	List<ProjectListFillTimesheet> listOfProject;

	
	public List<ProjectListFillTimesheet> getListOfProject() {
		return listOfProject;
	}


	public void setListOfProject(List<ProjectListFillTimesheet> listOfProject) {
		this.listOfProject = listOfProject;
	}


	@Override
	public String toString() {
		return "{ listOfProject=" + listOfProject + "}";
	}
	
	



	
	
 }
