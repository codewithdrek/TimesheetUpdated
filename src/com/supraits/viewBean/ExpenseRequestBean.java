package com.supraits.viewBean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
@Component
public class ExpenseRequestBean {
	private String username;
	private String requestNumber;
	private String requestStatus;
	private String lastModifiedBy;
	private String createdBy;
	private double requestedAmount;
	private double approvedAmount;
	@DateTimeFormat
	private String createdOn;
	@DateTimeFormat
	private String lastModifiedOn;
	private String reviewerRemark;
	private String approverRemark;
	List<ExpenseBean> expenseList = new ArrayList<ExpenseBean>();

	
	public String getReviewerRemark() {
		return reviewerRemark;
	}

	public void setReviewerRemark(String reviewerRemark) {
		this.reviewerRemark = reviewerRemark;
	}

	public String getApproverRemark() {
		return approverRemark;
	}

	public void setApproverRemark(String approverRemark) {
		this.approverRemark = approverRemark;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public double getRequestedAmount() {
		return requestedAmount;
	}

	public void setRequestedAmount(double requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	public double getApprovedAmount() {
		return approvedAmount;
	}

	public void setApprovedAmount(double approvedAmount) {
		this.approvedAmount = approvedAmount;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getLastModifiedOn() {
		return lastModifiedOn;
	}

	public void setLastModifiedOn(String lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}

	public List<ExpenseBean> getExpenseList() {
		return expenseList;
	}

	public void setExpenseList(List<ExpenseBean> expenseList) {
		this.expenseList = expenseList;
	}
}
