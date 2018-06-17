package com.supraits.viewBean.payroll;

/*
 * Java bean class for entity "ses_m11_payroll" 
 * Created on 2018-04-17 ( Date ISO 2018-04-17 - Time 15:56:08 )
 * Generated by abhinav.gupta
 */


import java.io.Serializable;

import java.util.Date;
/**
 * Java bean for entity "ses_m11_payroll"
 * 
 * @author Telosys Tools Generator
 *
 */
public class SesM11Payroll implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer payrollId    ; // Id or Primary Key

    private Integer payrollTypeId ;
    private String payrollName;
    private String status;
    private Integer entityId     ;
    private Integer clientId     ;
    private String lastUpdatedBy ;
    private Date lastUpdateDate ;
    private String createdBy    ;
    private Date creationDate ;
    private Integer lastUpdateSessionId ;

    /**
     * Default constructor
     */
    public SesM11Payroll() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    
    
    
    /**
     * Set the "payrollId" field value
     * This field is mapped on the database column "PAYROLL_ID" ( type "INT", NotNull : true ) 
     * @param payrollId
     */
	public void setPayrollId( Integer payrollId ) {
        this.payrollId = payrollId ;
    }
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
     * Get the "payrollId" field value
     * This field is mapped on the database column "PAYROLL_ID" ( type "INT", NotNull : true ) 
     * @return the field value
     */
	public Integer getPayrollId() {
        return this.payrollId;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS
    //----------------------------------------------------------------------
    /**
     * Set the "payrollTypeId" field value
     * This field is mapped on the database column "PAYROLL_TYPE_ID" ( type "INT", NotNull : false ) 
     * @param payrollTypeId
     */
    public void setPayrollTypeId( Integer payrollTypeId ) {
        this.payrollTypeId = payrollTypeId;
    }
    /**
     * Get the "payrollTypeId" field value
     * This field is mapped on the database column "PAYROLL_TYPE_ID" ( type "INT", NotNull : false ) 
     * @return the field value
     */
    public Integer getPayrollTypeId() {
        return this.payrollTypeId;
    }

    /**
     * Set the "payrollName" field value
     * This field is mapped on the database column "PAYROLL_NAME" ( type "VARCHAR", NotNull : false ) 
     * @param payrollName
     */
    public void setPayrollName( String payrollName ) {
        this.payrollName = payrollName;
    }
    /**
     * Get the "payrollName" field value
     * This field is mapped on the database column "PAYROLL_NAME" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getPayrollName() {
        return this.payrollName;
    }

    /**
     * Set the "entityId" field value
     * This field is mapped on the database column "ENTITY_ID" ( type "INT", NotNull : false ) 
     * @param entityId
     */
    public void setEntityId( Integer entityId ) {
        this.entityId = entityId;
    }
    /**
     * Get the "entityId" field value
     * This field is mapped on the database column "ENTITY_ID" ( type "INT", NotNull : false ) 
     * @return the field value
     */
    public Integer getEntityId() {
        return this.entityId;
    }

    /**
     * Set the "clientId" field value
     * This field is mapped on the database column "CLIENT_ID" ( type "INT", NotNull : false ) 
     * @param clientId
     */
    public void setClientId( Integer clientId ) {
        this.clientId = clientId;
    }
    /**
     * Get the "clientId" field value
     * This field is mapped on the database column "CLIENT_ID" ( type "INT", NotNull : false ) 
     * @return the field value
     */
    public Integer getClientId() {
        return this.clientId;
    }

    /**
     * Set the "lastUpdatedBy" field value
     * This field is mapped on the database column "LAST_UPDATED_BY" ( type "VARCHAR", NotNull : false ) 
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy( String lastUpdatedBy ) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**
     * Get the "lastUpdatedBy" field value
     * This field is mapped on the database column "LAST_UPDATED_BY" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    /**
     * Set the "lastUpdateDate" field value
     * This field is mapped on the database column "LAST_UPDATE_DATE" ( type "DATE", NotNull : false ) 
     * @param lastUpdateDate
     */
    public void setLastUpdateDate( Date lastUpdateDate ) {
        this.lastUpdateDate = lastUpdateDate;
    }
    /**
     * Get the "lastUpdateDate" field value
     * This field is mapped on the database column "LAST_UPDATE_DATE" ( type "DATE", NotNull : false ) 
     * @return the field value
     */
    public Date getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    /**
     * Set the "createdBy" field value
     * This field is mapped on the database column "CREATED_BY" ( type "VARCHAR", NotNull : false ) 
     * @param createdBy
     */
    public void setCreatedBy( String createdBy ) {
        this.createdBy = createdBy;
    }
    /**
     * Get the "createdBy" field value
     * This field is mapped on the database column "CREATED_BY" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Set the "creationDate" field value
     * This field is mapped on the database column "CREATION_DATE" ( type "DATE", NotNull : false ) 
     * @param creationDate
     */
    public void setCreationDate( Date creationDate ) {
        this.creationDate = creationDate;
    }
    /**
     * Get the "creationDate" field value
     * This field is mapped on the database column "CREATION_DATE" ( type "DATE", NotNull : false ) 
     * @return the field value
     */
    public Date getCreationDate() {
        return this.creationDate;
    }

    /**
     * Set the "lastUpdateSessionId" field value
     * This field is mapped on the database column "LAST_UPDATE_SESSION_ID" ( type "INT", NotNull : false ) 
     * @param lastUpdateSessionId
     */
    public void setLastUpdateSessionId( Integer lastUpdateSessionId ) {
        this.lastUpdateSessionId = lastUpdateSessionId;
    }
    /**
     * Get the "lastUpdateSessionId" field value
     * This field is mapped on the database column "LAST_UPDATE_SESSION_ID" ( type "INT", NotNull : false ) 
     * @return the field value
     */
    public Integer getLastUpdateSessionId() {
        return this.lastUpdateSessionId;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(payrollId);
        sb.append("|");
        sb.append(payrollTypeId);
        sb.append("|");
        sb.append(payrollName);
        sb.append("|");
        sb.append(entityId);
        sb.append("|");
        sb.append(clientId);
        sb.append("|");
        sb.append(lastUpdatedBy);
        sb.append("|");
        sb.append(lastUpdateDate);
        sb.append("|");
        sb.append(createdBy);
        sb.append("|");
        sb.append(creationDate);
        sb.append("|");
        sb.append(lastUpdateSessionId);
        return sb.toString(); 
    }
}