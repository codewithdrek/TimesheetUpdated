package com.supraits.viewBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Component
public class EmployeeBean {
	private String userName;
	private String salutation;
	private String firstName;
	private String middleName;
	private String lastName;
	private String motherName;
	private String fatherName;
	private String motherTongue;
	private String gender;
	@DateTimeFormat
	private String dob;
	private String age;
	private String aadharNo;
	private String pancardNo;
	private String passportNo;
	private String bloodGroup;
	private String maritalStatus;
	private String spouseName;
	
	private String primaryPhoneNum;
	private String secondaryPhoneNum;
	private String emergencyPhoneNum;
	@NotEmpty
    @Email
	private String primaryEmailId;
	private String secondaryEmailId;
	private String webOrLinkedInLink;
	
	private String baseLocation;
	private String department;
	private String designation;
	private String role;
	private String reportingPerson;
	private String reportingPersonFullName;
	private String hrManager;
	private String hrManagerFullName;
	private String accountUnit;
	@DateTimeFormat
	private String joiningDate;
	@DateTimeFormat
	private String lastWorkingDate;
	
	private String permanentAddress;
	private String permanentAddressCountry;
	private String permanentAddressState;
	private String permanentAddressCity;
	private String permanentAddressZipCode;
	
	private String communicationAddress;
	private String communicationAddressCountry;
	private String communicationAddressState;
	private String communicationAddressCity;
	private String communicationAddressZipCode;
	
	private String officeAddress;
	private String officeAddressCountry;
	private String officeAddressState;
	private String officeAddressCity;
	private String officeAddressZipCode;
	
	private String clientOfficeAddress;
	private String clientOfficeAddressCountry;
	private String clientOfficeAddressState;
	private String clientOfficeAddressCity;
	private String clientOfficeAddressZipCode;
	
	List<EmployeeAccountBean> empAccountList = new LinkedList<EmployeeAccountBean>();
	List<EmployeeEducationBean> empEducationList = new LinkedList<EmployeeEducationBean>();
	List<EmployeeSkillBean> empSkilltList = new LinkedList<EmployeeSkillBean>();
	List<EmployeeWorkBean> empWorkHistoryList = new LinkedList<EmployeeWorkBean>();
	
	private String policyGroupId;
	private String policyGroupName;
	private String attendanceId;
	private String userCode;
	private int clientId;
	
	
	public int getClientId() {
		return clientId;
	}
		
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getReportingPersonFullName() {
		return reportingPersonFullName;
	}
	public void setReportingPersonFullName(String reportingPersonFullName) {
		this.reportingPersonFullName = reportingPersonFullName;
	}
	public String getHrManagerFullName() {
		return hrManagerFullName;
	}
	public void setHrManagerFullName(String hrManagerFullName) {
		this.hrManagerFullName = hrManagerFullName;
	}
	public String getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(String attendanceId) {
		this.attendanceId = attendanceId;
	}
	public String getPolicyGroupId() {
		return policyGroupId;
	}
	public void setPolicyGroupId(String policyGroupId) {
		this.policyGroupId = policyGroupId;
	}
	public String getPolicyGroupName() {
		return policyGroupName;
	}
	public void setPolicyGroupName(String policyGroupName) {
		this.policyGroupName = policyGroupName;
	}
	public String getPrimaryPhoneNum() {
		return primaryPhoneNum;
	}
	public void setPrimaryPhoneNum(String primaryPhoneNum) {
		if(null == primaryPhoneNum || "".equals(primaryPhoneNum))
			this.primaryPhoneNum = "";
		else
			this.primaryPhoneNum = primaryPhoneNum;
	}
	public String getSecondaryPhoneNum() {
		return (null == secondaryPhoneNum) ? "" : secondaryPhoneNum;
	}
	public void setSecondaryPhoneNum(String secondaryPhoneNum) {
		this.secondaryPhoneNum = secondaryPhoneNum;
	}
	public String getEmergencyPhoneNum() {
		return (null == emergencyPhoneNum) ? "" : emergencyPhoneNum ;
	}
	public void setEmergencyPhoneNum(String emergencyPhoneNum) {
		this.emergencyPhoneNum = emergencyPhoneNum;
	}
	public String getPrimaryEmailId() {
		return (null == primaryEmailId) ? "" : primaryEmailId ;
	}
	public void setPrimaryEmailId(String primaryEmailId) {
		this.primaryEmailId = primaryEmailId;
	}
	public String getSecondaryEmailId() {
		return (null == secondaryEmailId) ? "" : secondaryEmailId ;
	}
	public void setSecondaryEmailId(String secondaryEmailId) {
		this.secondaryEmailId = secondaryEmailId;
	}
	public String getWebOrLinkedInLink() {
		return (null == webOrLinkedInLink) ? "" : webOrLinkedInLink ;
	}
	public void setWebOrLinkedInLink(String webOrLinkedInLink) {
		this.webOrLinkedInLink = webOrLinkedInLink;
	}
	public String getBaseLocation() {
		return (null == baseLocation) ? "" : baseLocation ;
	}
	public void setBaseLocation(String baseLocation) {
		this.baseLocation = baseLocation;
	}
	public String getDepartment() {
		return (null == department) ? "" : department ;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return (null == designation) ? "" : designation ;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getRole() {
		return (null == role) ? "" : role ;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getReportingPerson() {
		return (null == reportingPerson) ? "" : reportingPerson ;
	}
	public void setReportingPerson(String reportingPerson) {
		this.reportingPerson = reportingPerson;
	}
	public String getHrManager() {
		return (null == hrManager) ? "" : hrManager ;
	}
	public void setHrManager(String hrManager) {
		this.hrManager = hrManager;
	}
	public String getAccountUnit() {
		return (null == accountUnit) ? "SUPRA-Noida" : accountUnit ;
	}
	public void setAccountUnit(String accountUnit) {
		this.accountUnit = accountUnit;
	}
	public String getJoiningDate() {
		return (null == joiningDate) ? new SimpleDateFormat("yyyy-MM-dd").format(new Date()) : joiningDate ;
	}
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	public String getLastWorkingDate() {
		return (null == lastWorkingDate) ? "2099-12-12" : lastWorkingDate ;
	}
	public void setLastWorkingDate(String lastWorkingDate) {
		this.lastWorkingDate = lastWorkingDate;
	}
	public String getPermanentAddress() {
		return (null == permanentAddress) ? "" : permanentAddress ;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public String getPermanentAddressCountry() {
		return permanentAddressCountry;
	}
	public void setPermanentAddressCountry(String permanentAddressCountry) {
		this.permanentAddressCountry = permanentAddressCountry;
	}
	public String getPermanentAddressState() {
		return permanentAddressState;
	}
	public void setPermanentAddressState(String permanentAddressState) {
		this.permanentAddressState = permanentAddressState;
	}
	public String getPermanentAddressCity() {
		return permanentAddressCity;
	}
	public void setPermanentAddressCity(String permanentAddressCity) {
		this.permanentAddressCity = permanentAddressCity;
	}
	public String getPermanentAddressZipCode() {
		return permanentAddressZipCode;
	}
	public void setPermanentAddressZipCode(String permanentAddressZipCode) {
		this.permanentAddressZipCode = permanentAddressZipCode;
	}
	public String getCommunicationAddress() {
		return communicationAddress;
	}
	public void setCommunicationAddress(String communicationAddress) {
		this.communicationAddress = communicationAddress;
	}
	public String getCommunicationAddressCountry() {
		return communicationAddressCountry;
	}
	public void setCommunicationAddressCountry(String communicationAddressCountry) {
		this.communicationAddressCountry = communicationAddressCountry;
	}
	public String getCommunicationAddressState() {
		return communicationAddressState;
	}
	public void setCommunicationAddressState(String communicationAddressState) {
		this.communicationAddressState = communicationAddressState;
	}
	public String getCommunicationAddressCity() {
		return communicationAddressCity;
	}
	public void setCommunicationAddressCity(String communicationAddressCity) {
		this.communicationAddressCity = communicationAddressCity;
	}
	public String getCommunicationAddressZipCode() {
		return communicationAddressZipCode;
	}
	public void setCommunicationAddressZipCode(String communicationAddressZipCode) {
		this.communicationAddressZipCode = communicationAddressZipCode;
	}
	public String getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
	public String getOfficeAddressCountry() {
		return officeAddressCountry;
	}
	public void setOfficeAddressCountry(String officeAddressCountry) {
		this.officeAddressCountry = officeAddressCountry;
	}
	public String getOfficeAddressState() {
		return officeAddressState;
	}
	public void setOfficeAddressState(String officeAddressState) {
		this.officeAddressState = officeAddressState;
	}
	public String getOfficeAddressCity() {
		return officeAddressCity;
	}
	public void setOfficeAddressCity(String officeAddressCity) {
		this.officeAddressCity = officeAddressCity;
	}
	public String getOfficeAddressZipCode() {
		return officeAddressZipCode;
	}
	public void setOfficeAddressZipCode(String officeAddressZipCode) {
		this.officeAddressZipCode = officeAddressZipCode;
	}
	public String getClientOfficeAddress() {
		return clientOfficeAddress;
	}
	public void setClientOfficeAddress(String clientOfficeAddress) {
		this.clientOfficeAddress = clientOfficeAddress;
	}
	public String getClientOfficeAddressCountry() {
		return clientOfficeAddressCountry;
	}
	public void setClientOfficeAddressCountry(String clientOfficeAddressCountry) {
		this.clientOfficeAddressCountry = clientOfficeAddressCountry;
	}
	public String getClientOfficeAddressState() {
		return clientOfficeAddressState;
	}
	public void setClientOfficeAddressState(String clientOfficeAddressState) {
		this.clientOfficeAddressState = clientOfficeAddressState;
	}
	public String getClientOfficeAddressCity() {
		return clientOfficeAddressCity;
	}
	public void setClientOfficeAddressCity(String clientOfficeAddressCity) {
		this.clientOfficeAddressCity = clientOfficeAddressCity;
	}
	public String getClientOfficeAddressZipCode() {
		return clientOfficeAddressZipCode;
	}
	public void setClientOfficeAddressZipCode(String clientOfficeAddressZipCode) {
		this.clientOfficeAddressZipCode = clientOfficeAddressZipCode;
	}
	public List<EmployeeAccountBean> getEmpAccountList() {
		return empAccountList;
	}
	public void setEmpAccountList(List<EmployeeAccountBean> empAccountList) {
		this.empAccountList = empAccountList;
	}
	public List<EmployeeEducationBean> getEmpEducationList() {
		return empEducationList;
	}
	public void setEmpEducationList(List<EmployeeEducationBean> empEducationList) {
		this.empEducationList = empEducationList;
	}
	public List<EmployeeSkillBean> getEmpSkilltList() {
		return empSkilltList;
	}
	public void setEmpSkilltList(List<EmployeeSkillBean> empSkilltList) {
		this.empSkilltList = empSkilltList;
	}
	public List<EmployeeWorkBean> getEmpWorkHistoryList() {
		return empWorkHistoryList;
	}
	public void setEmpWorkHistoryList(List<EmployeeWorkBean> empWorkHistoryList) {
		this.empWorkHistoryList = empWorkHistoryList;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return (null == middleName) ? "" : middleName ;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMotherName() {
		return (null == motherName) ? "" : motherName ;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getFatherName() {
		return (null == fatherName) ? "" : fatherName ;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherTongue() {
		return motherTongue;
	}
	public void setMotherTongue(String motherTongue) {
		this.motherTongue = motherTongue;
	}
	public String getGender() {
		return (null == gender)? "" : gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return (null == dob) ? "1900-01-01" : dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getPancardNo() {
		return pancardNo;
	}
	public void setPancardNo(String pancardNo) {
		this.pancardNo = pancardNo;
	}
	public String getPassportNo() {
		return passportNo;
	}
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getMaritalStatus() {
		return (null == maritalStatus) ? "": maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getSpouseName() {
		return (null == spouseName) ? "": spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
}
