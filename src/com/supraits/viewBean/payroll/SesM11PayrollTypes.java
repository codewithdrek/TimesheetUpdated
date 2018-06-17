package com.supraits.viewBean.payroll;

import java.io.Serializable;
import java.sql.Date;



public class SesM11PayrollTypes implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer payrollTypeId ; // Id or Primary Key

    private String typeName;
    private String description  ;
    private String status       ;
    private Integer entityId     ;
    private Integer clientId     ;
    private String lastUpdatedBy ;
    private Date lastUpdateDate ;
    private String createdBy    ;
    private Date creationDate ;
    private Integer lastUpdateSessionId ;
    
    
    
	public SesM11PayrollTypes() {
		super();
	}
	public Integer getPayrollTypeId() {
		return payrollTypeId;
	}
	public void setPayrollTypeId(Integer payrollTypeId) {
		this.payrollTypeId = payrollTypeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
