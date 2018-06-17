package com.supraits.viewBean;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class LeaveRequestBean {
	private String requestNumber;
	private String username;
	private String requestedBy;
	private String purpose;
	private String managerRemark;
	private String approvedBy;
	private String leaveStatus;
	private String totalDays;
	@DateTimeFormat
	private String requestedOn;
	private List<LeaveBean> leaveList;
	
	
	
	public String getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(String totalDays) {
		this.totalDays = totalDays;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getManagerRemark() {
		return managerRemark;
	}

	public void setManagerRemark(String managerRemark) {
		this.managerRemark = managerRemark;
	}

	public String getRequestedOn() {
		return requestedOn;
	}

	public void setRequestedOn(String requestedOn) {
		this.requestedOn = requestedOn;
	}

	public List<LeaveBean> getLeaveList() {
		return leaveList;
	}

	public void setLeaveList(List<LeaveBean> leaveList) {
		this.leaveList = leaveList;
	}

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
}
