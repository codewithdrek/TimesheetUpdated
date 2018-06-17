package com.supra.imanager.bean;

import java.util.List;

public class DashboardPanel {

	String panelId;
	String panelName;
	String panelUrl;
	List<DashboardStatus> statusList;
	
	
	public String getPanelUrl() {
		return panelUrl;
	}
	public void setPanelUrl(String panelUrl) {
		this.panelUrl = panelUrl;
	}
	public String getPanelId() {
		return panelId;
	}
	public void setPanelId(String panelId) {
		this.panelId = panelId;
	}
	public String getPanelName() {
		return panelName;
	}
	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}
	public List<DashboardStatus> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<DashboardStatus> statusList) {
		this.statusList = statusList;
	}
	@Override
	public String toString() {
		return "{ panelId:" + panelId + ","
				+ " panelName:" + panelName +
				", panelUrl:" + panelUrl
				+ ", statusList:" + statusList + "}";
	}
	
	

	
}
