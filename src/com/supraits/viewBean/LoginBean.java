package com.supraits.viewBean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class LoginBean
{
		private String username;
		private String password;
		private String firstName;
		private String lastName;
		private String primaryMail;
		@DateTimeFormat
		private String dob;
		@DateTimeFormat
		private String lastModifiedDate;
		private String role;
		private String userGroup;
		private String userStatus;
		private String creationDate;
		private String submittedWeek;
		private String remark;
		private String adminRemark;
		//private String usernameproxymail;
		private String usernameproxyid;
		private String fullnameproxyid;
		private boolean userNameProxyflag;
		private String reportingManager;
		private String hrManager;
		private String baseLocation;
		private String designation;
		private String empDepartment;
		private String accountUnit;
		@DateTimeFormat
		private String joiningDate;
		
		private List<ProjectBean> projectList = new ArrayList<ProjectBean>();
		private List<TaskMappingBean> taskMappingList = new ArrayList<TaskMappingBean>();
		
		private String policyGroup;
		private String loginMessage;
		private String userCode;
		private int clientId;
		
		
		public int getClientId() {
			return clientId;
		}
		public void setClientId(int clientId) {
			this.clientId = clientId;
		}
		public String getUserCode() {
			return userCode;
		}
		public void setUserCode(String userCode) {
			this.userCode = userCode;
		}
		public String getLoginMessage() {
			return loginMessage != null?loginMessage:"";
		}
		public void setLoginMessage(String loginMessage) {
			this.loginMessage = loginMessage;
		}
		public String getFullnameproxyid() {
			return fullnameproxyid;
		}
		public void setFullnameproxyid(String fullnameproxyid) {
			this.fullnameproxyid = fullnameproxyid;
		}
		public String getPolicyGroup() {
			return policyGroup;
		}
		public void setPolicyGroup(String policyGroup) {
			this.policyGroup = policyGroup;
		}
		public String getBaseLocation() {
			return baseLocation;
		}
		public void setBaseLocation(String baseLocation) {
			this.baseLocation = baseLocation;
		}
		public String getDesignation() {
			return designation;
		}
		public void setDesignation(String designation) {
			this.designation = designation;
		}
		public String getEmpDepartment() {
			return empDepartment;
		}
		public void setEmpDepartment(String empDepartment) {
			this.empDepartment = empDepartment;
		}
		public String getAccountUnit() {
			return accountUnit;
		}
		public void setAccountUnit(String accountUnit) {
			this.accountUnit = accountUnit;
		}
		public String getJoiningDate() {
			return joiningDate;
		}
		public void setJoiningDate(String joiningDate) {
			this.joiningDate = joiningDate;
		}
		/*public String getUsernameproxymail() {
			return usernameproxymail;
		}
		public void setUsernameproxymail(String usernameproxymail) {
			this.usernameproxymail = usernameproxymail;
		}*/
		public String getUsernameproxyid() {
			return usernameproxyid;
		}
		public void setUsernameproxyid(String usernameproxyid) {
			this.usernameproxyid = usernameproxyid;
		}
		public String getReportingManager() {
			return reportingManager;
		}
		public void setReportingManager(String reportingManager) {
			this.reportingManager = reportingManager;
		}
		public String getHrManager() {
			return hrManager;
		}
		public void setHrManager(String hrManager) {
			this.hrManager = hrManager;
		}
		public boolean isUserNameProxyflag() {
			return userNameProxyflag;
		}
		public void setUserNameProxyflag(boolean userNameProxyflag) {
			this.userNameProxyflag = userNameProxyflag;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getAdminRemark() {
			return adminRemark;
		}
		public void setAdminRemark(String adminRemark) {
			this.adminRemark = adminRemark;
		}
		public String getLastModifiedDate() {
			return lastModifiedDate;
		}
		public void setLastModifiedDate(String lastModifiedDate) {
			this.lastModifiedDate = lastModifiedDate;
		}
		public String getCreationDate() {
			return creationDate;
		}
		public void setCreationDate(String creationDate) {
			this.creationDate = creationDate;
		}
		public String getPrimaryMail() {
			return primaryMail;
		}
		public void setPrimaryMail(String primaryMail) {
			this.primaryMail = primaryMail;
		}
		public String getUserStatus() {
			return userStatus;
		}
		public void setUserStatus(String userStatus) {
			this.userStatus = userStatus;
		}
		public String getUserGroup() {
			return userGroup;
		}
		public void setUserGroup(String userGroup) {
			this.userGroup = userGroup;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getDob() {
			return dob;
		}
		public void setDob(String dob) {
			this.dob = dob;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public String getSubmittedWeek() {
			return submittedWeek;
		}
		public void setSubmittedWeek(String submittedWeek) {
			this.submittedWeek = submittedWeek;
		}
		public List<ProjectBean> getProjectList() {
			return projectList;
		}
		public void setProjectList(List<ProjectBean> projectList) {
			this.projectList = projectList;
		}
		public List<TaskMappingBean> getTaskMappingList() {
			return taskMappingList;
		}
		public void setTaskMappingList(List<TaskMappingBean> taskMappingList) {
			this.taskMappingList = taskMappingList;
		}


}
