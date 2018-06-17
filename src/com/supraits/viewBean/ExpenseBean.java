package com.supraits.viewBean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
public class ExpenseBean {
	private String expenseCode;
	private String expenseName;
	private String billNumber;
	@DateTimeFormat
	private String billDate;
	@DateTimeFormat
	private String LastModifiedOn;
	private double billAmount;
	private double requestAmount;
	private double approvedAmount;
	private String applicantRemark;
	private String reviewerRemark;
	private String approverRemark;
	private String expDocName;
	private String codeName;
	private String projectName;
	List<ExpenseDocumentBean> expenseDocumentList = new ArrayList<ExpenseDocumentBean>();
	
	
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getExpDocName() {
		return expDocName;
	}
	public void setExpDocName(String expDocName) {
		this.expDocName = expDocName;
	}
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	public double getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}
	public String getExpenseName() {
		return expenseName;
	}
	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}
	public String getExpenseCode() {
		return expenseCode;
	}
	public void setExpenseCode(String expenseCode) {
		this.expenseCode = expenseCode;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getLastModifiedOn() {
		return LastModifiedOn;
	}
	public void setLastModifiedOn(String lastModifiedOn) {
		LastModifiedOn = lastModifiedOn;
	}
	public double getRequestAmount() {
		return requestAmount;
	}
	public void setRequestAmount(double requestAmount) {
		this.requestAmount = requestAmount;
	}
	public double getApprovedAmount() {
		return approvedAmount;
	}
	public void setApprovedAmount(double approvedAmount) {
		this.approvedAmount = approvedAmount;
	}
	public String getApplicantRemark() {
		return applicantRemark;
	}
	public void setApplicantRemark(String applicantRemark) {
		this.applicantRemark = applicantRemark;
	}
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
	public List<ExpenseDocumentBean> getExpenseDocumentList() {
		return expenseDocumentList;
	}
	public void setExpenseDocumentList(List<ExpenseDocumentBean> expenseDocumentList) {
		this.expenseDocumentList = expenseDocumentList;
	}
	
	
	
}
