package com.supraits.viewBean;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class EmployeeWorkBean {
	private String companyId;
	private String companyName;
	@DateTimeFormat
	private String startDate;
	@DateTimeFormat
	private String endDate;
	private String lastDesignation;
	private String durationMonth;
	
	
	
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	public String getLastDesignation() {
		return lastDesignation;
	}
	public void setLastDesignation(String lastDesignation) {
		this.lastDesignation = lastDesignation;
	}
	public String getDurationMonth() {
		return durationMonth;
	}
	public void setDurationMonth(String durationMonth) {
		this.durationMonth = durationMonth;
	}
	
	
}
