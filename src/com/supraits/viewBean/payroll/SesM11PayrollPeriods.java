package com.supraits.viewBean.payroll;

import java.util.Date;

public class SesM11PayrollPeriods {
	private Integer payrollPeriodId ; // Id or Primary Key
    private String periodName;
    private Date startDate;
    private Date endDate;
    private String status;
    private Integer entityId;
    private Integer clientId;
    private String lastUpdatedBy;
    private Date lastUpdateDate;
    private String createdBy;
    private Date creationDate;
    private Integer lastUpdateSessionId ;
	public Integer getPayrollPeriodId() {
		return payrollPeriodId;
	}
	public void setPayrollPeriodId(Integer payrollPeriodId) {
		this.payrollPeriodId = payrollPeriodId;
	}
	public String getPeriodName() {
		return periodName;
	}
	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getEntityId() {
		return entityId;
	}
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getLastUpdateSessionId() {
		return lastUpdateSessionId;
	}
	public void setLastUpdateSessionId(Integer lastUpdateSessionId) {
		this.lastUpdateSessionId = lastUpdateSessionId;
	}
}
