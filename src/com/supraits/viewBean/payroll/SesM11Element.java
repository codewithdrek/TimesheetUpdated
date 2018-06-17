package com.supraits.viewBean.payroll;


import java.io.Serializable;

import java.util.Date;

/**
 * Java bean for entity "ses_m11_element"
 * 
 * @author Telosys Tools Generator
 *
 */
public class SesM11Element implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer    elementId    ; // Id or Primary Key

    private String     elementName  ;
    private String     description  ;
    private Integer    elementGroupId  ;
    private String     formulaAlias ;
    private String     elementType  ;
    private String     payFrequency ;
    private String     billVerification ;
    private String     taxExemted   ;
    private String     formulaFlag  ;
    private String     formulaText  ;
    private String     formulaType  ;
    private Integer    maxLimit     ;
    private String     activeFlag   ;
    private Integer    entityId     ;
    private Integer    clientId     ;
    private Date       lastUpdatedBy ;
    private Date       lastUpdateDate ;
    private String     createdBy    ;
    private Date       creationDate ;
    private Integer    lastUpdateSessionId ;

    /**
     * Default constructor
     */
    public SesM11Element() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    
    public Integer getElementGroupId() {
		return elementGroupId;
	}

	public void setElementGroupId(Integer elementGroupId) {
		this.elementGroupId = elementGroupId;
	}    
    /**
     * Set the "elementId" field value
     * This field is mapped on the database column "ELEMENT_ID" ( type "INT", NotNull : true ) 
     * @param elementId
     */
    
	public void setElementId( Integer elementId ) {
        this.elementId = elementId ;
    }
	/**
     * Get the "elementId" field value
     * This field is mapped on the database column "ELEMENT_ID" ( type "INT", NotNull : true ) 
     * @return the field value
     */
	public Integer getElementId() {
        return this.elementId;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS
    //----------------------------------------------------------------------
    /**
     * Set the "elementName" field value
     * This field is mapped on the database column "ELEMENT_NAME" ( type "VARCHAR", NotNull : false ) 
     * @param elementName
     */
    public void setElementName( String elementName ) {
        this.elementName = elementName;
    }
    /**
     * Get the "elementName" field value
     * This field is mapped on the database column "ELEMENT_NAME" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getElementName() {
        return this.elementName;
    }

    /**
     * Set the "description" field value
     * This field is mapped on the database column "DESCRIPTION" ( type "VARCHAR", NotNull : false ) 
     * @param description
     */
    public void setDescription( String description ) {
        this.description = description;
    }
    /**
     * Get the "description" field value
     * This field is mapped on the database column "DESCRIPTION" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the "formulaAlias" field value
     * This field is mapped on the database column "FORMULA_ALIAS" ( type "VARCHAR", NotNull : false ) 
     * @param formulaAlias
     */
    public void setFormulaAlias( String formulaAlias ) {
        this.formulaAlias = formulaAlias;
    }
    /**
     * Get the "formulaAlias" field value
     * This field is mapped on the database column "FORMULA_ALIAS" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getFormulaAlias() {
        return this.formulaAlias;
    }

    /**
     * Set the "elementType" field value
     * This field is mapped on the database column "ELEMENT_TYPE" ( type "VARCHAR", NotNull : false ) 
     * @param elementType
     */
    public void setElementType( String elementType ) {
        this.elementType = elementType;
    }
    /**
     * Get the "elementType" field value
     * This field is mapped on the database column "ELEMENT_TYPE" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getElementType() {
        return this.elementType;
    }

    /**
     * Set the "payFrequency" field value
     * This field is mapped on the database column "PAY_FREQUENCY" ( type "VARCHAR", NotNull : false ) 
     * @param payFrequency
     */
    public void setPayFrequency( String payFrequency ) {
        this.payFrequency = payFrequency;
    }
    /**
     * Get the "payFrequency" field value
     * This field is mapped on the database column "PAY_FREQUENCY" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getPayFrequency() {
        return this.payFrequency;
    }

    /**
     * Set the "billVerification" field value
     * This field is mapped on the database column "BILL_Verification" ( type "VARCHAR", NotNull : false ) 
     * @param billVerification
     */
    public void setBillVerification( String billVerification ) {
        this.billVerification = billVerification;
    }
    /**
     * Get the "billVerification" field value
     * This field is mapped on the database column "BILL_Verification" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getBillVerification() {
        return this.billVerification;
    }

    /**
     * Set the "taxExemted" field value
     * This field is mapped on the database column "TAX_EXEMTED" ( type "VARCHAR", NotNull : false ) 
     * @param taxExemted
     */
    public void setTaxExemted( String taxExemted ) {
        this.taxExemted = taxExemted;
    }
    /**
     * Get the "taxExemted" field value
     * This field is mapped on the database column "TAX_EXEMTED" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getTaxExemted() {
        return this.taxExemted;
    }

    /**
     * Set the "formulaFlag" field value
     * This field is mapped on the database column "FORMULA_FLAG" ( type "VARCHAR", NotNull : false ) 
     * @param formulaFlag
     */
    public void setFormulaFlag( String formulaFlag ) {
        this.formulaFlag = formulaFlag;
    }
    /**
     * Get the "formulaFlag" field value
     * This field is mapped on the database column "FORMULA_FLAG" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getFormulaFlag() {
        return this.formulaFlag;
    }

    /**
     * Set the "formulaText" field value
     * This field is mapped on the database column "FORMULA_TEXT" ( type "VARCHAR", NotNull : false ) 
     * @param formulaText
     */
    public void setFormulaText( String formulaText ) {
        this.formulaText = formulaText;
    }
    /**
     * Get the "formulaText" field value
     * This field is mapped on the database column "FORMULA_TEXT" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getFormulaText() {
        return this.formulaText;
    }

    /**
     * Set the "formulaType" field value
     * This field is mapped on the database column "FORMULA_TYPE" ( type "VARCHAR", NotNull : false ) 
     * @param formulaType
     */
    public void setFormulaType( String formulaType ) {
        this.formulaType = formulaType;
    }
    /**
     * Get the "formulaType" field value
     * This field is mapped on the database column "FORMULA_TYPE" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getFormulaType() {
        return this.formulaType;
    }

    /**
     * Set the "maxLimit" field value
     * This field is mapped on the database column "MAX_LIMIT" ( type "INT", NotNull : false ) 
     * @param maxLimit
     */
    public void setMaxLimit( Integer maxLimit ) {
        this.maxLimit = maxLimit;
    }
    /**
     * Get the "maxLimit" field value
     * This field is mapped on the database column "MAX_LIMIT" ( type "INT", NotNull : false ) 
     * @return the field value
     */
    public Integer getMaxLimit() {
        return this.maxLimit;
    }

    /**
     * Set the "activeFlag" field value
     * This field is mapped on the database column "ACTIVE_FLAG" ( type "VARCHAR", NotNull : false ) 
     * @param activeFlag
     */
    public void setActiveFlag( String activeFlag ) {
        this.activeFlag = activeFlag;
    }
    /**
     * Get the "activeFlag" field value
     * This field is mapped on the database column "ACTIVE_FLAG" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getActiveFlag() {
        return this.activeFlag;
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
     * This field is mapped on the database column "LAST_UPDATED_BY" ( type "DATE", NotNull : false ) 
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy( Date lastUpdatedBy ) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**
     * Get the "lastUpdatedBy" field value
     * This field is mapped on the database column "LAST_UPDATED_BY" ( type "DATE", NotNull : false ) 
     * @return the field value
     */
    public Date getLastUpdatedBy() {
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
        sb.append(elementId);
        sb.append("|");
        sb.append(elementName);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(formulaAlias);
        sb.append("|");
        sb.append(elementType);
        sb.append("|");
        sb.append(payFrequency);
        sb.append("|");
        sb.append(billVerification);
        sb.append("|");
        sb.append(taxExemted);
        sb.append("|");
        sb.append(formulaFlag);
        sb.append("|");
        sb.append(formulaText);
        sb.append("|");
        sb.append(formulaType);
        sb.append("|");
        sb.append(maxLimit);
        sb.append("|");
        sb.append(activeFlag);
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

