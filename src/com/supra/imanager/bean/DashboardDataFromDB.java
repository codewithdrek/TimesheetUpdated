package com.supra.imanager.bean;

public class DashboardDataFromDB {

	int id;
	String panelId;
	String panelCode;
	String panelName;
	String statusName;
	String moduleName;
	int statusCount;

	public static final String PANEL_CODE_SELF="SELF";
	public static final String PANEL_CODE_OTHERS="OTHERS";


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getPanelId() {
		return panelId;
	}
	public void setPanelId(String panelId) {
		this.panelId = panelId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public int getStatusCount() {
		return statusCount;
	}
	public void setStatusCount(int statusCount) {
		this.statusCount = statusCount;
	}
	public String getPanelCode() {
		return panelCode;
	}
	public void setPanelCode(String panelCode) {
		this.panelCode = panelCode;
	}
	public String getPanelName() {
		return panelName;
	}
	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}

	
}
