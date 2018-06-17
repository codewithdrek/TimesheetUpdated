package com.supraits.viewBean;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;


@Component
public class LeaveBean {
	
	private String leaveCode;
	private String leaveName;
	@DateTimeFormat
	private String leaveStartDate;
	@DateTimeFormat
	private String leaveEndDate;
	private String leavePurpose;
	private String fullDayFlag;
	private String leaveDays;
	private String username;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLeaveCode() {
		return leaveCode;
	}
	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}
	public String getLeaveName() {
		return leaveName;
	}
	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}
	public String getLeavePurpose() {
		return leavePurpose;
	}
	public void setLeavePurpose(String leavePurpose) {
		this.leavePurpose = leavePurpose;
	}
	public String getFullDayFlag() {
		return fullDayFlag;
	}
	public void setFullDayFlag(String fullDayFlag) {
		this.fullDayFlag = fullDayFlag;
	}
	public String getLeaveStartDate() {
		return leaveStartDate;
	}
	public void setLeaveStartDate(String leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}
	public String getLeaveEndDate() {
		return leaveEndDate;
	}
	public void setLeaveEndDate(String leaveEndDate) {
		this.leaveEndDate = leaveEndDate;
	}
	public String getLeaveDays() {
		return leaveDays;
	}
	public void setLeaveDays(String leaveDays) {
		this.leaveDays = leaveDays;
	}
	
}
