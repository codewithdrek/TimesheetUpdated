package com.supra.imanager.bean;

public class DashboardQuickLink {

	
	String quickLinkName;
    String quickLinkId;
    String quickLinkUrl;
    
    
	public String getQuickLinkUrl() {
		return quickLinkUrl;
	}

	public void setQuickLinkUrl(String quickLinkUrl) {
		this.quickLinkUrl = quickLinkUrl;
	}

	public String getQuickLinkId() {
		return quickLinkId;
	}

	public void setQuickLinkId(String quickLinkId) {
		this.quickLinkId = quickLinkId;
	}

	public String getQuickLinkName() {
		return quickLinkName;
	}

	public void setQuickLinkName(String quickLinkName) {
		this.quickLinkName = quickLinkName;
	}

	@Override
	public String toString() {
		return "{ quickLinkName:" + quickLinkName + ", quickLinkId:" + quickLinkId + ", quickLinkUrl:"
				+ quickLinkUrl + "}";
	}

	
	
}
