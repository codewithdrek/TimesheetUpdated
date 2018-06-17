<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='<c:url value="/resources/images/favicon.ico" />' rel="shortcut icon">
<title>Employee Details SupraITS</title>
	<link href='<c:url value="/resources/css/bootstrap.min3.css" />' rel="stylesheet">
	<link href="<c:url value="/resources/css/datepicker3.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
	<link href="<c:url value="/resources/css/googleapi-fonts.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
	
	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-table.js"/>"></script>
	<script src="<c:url value="/resources/js/moment.min.js"/>"></script>
	<script src="<c:url value="/resources/js/lumino.glyphs.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap-filestyle.min.js" />"></script>
	<script src="<c:url value="/resources/scripts/userProfile.js"/>"></script>
<script type="text/javascript">
var sessionuser = '${sessionScope.loggedInUser}';
if(sessionuser == "" ||  sessionuser == null ||sessionuser == undefined){
	alert("Session Expired.");
	window.close();
}
</script>
<style>

/*  bhoechie tab */
div.bhoechie-tab-container{
  z-index: 10;
  background-color: #ffffff;
  padding: 0 !important;
  border-radius: 4px;
  -moz-border-radius: 4px;
  border:1px solid #ddd;
  margin-top: 0px;
  margin-left: 0px;
  -webkit-box-shadow: 0 6px 12px rgba(0,0,0,.175);
  box-shadow: 0 6px 12px rgba(0,0,0,.175);
  -moz-box-shadow: 0 6px 12px rgba(0,0,0,.175);
  background-clip: padding-box;
  opacity: 0.97;
  filter: alpha(opacity=97);
}
div.bhoechie-tab-menu{
  padding-right: 0;
  padding-left: 0;
  padding-bottom: 0;
}
div.bhoechie-tab-menu div.list-group{
  margin-bottom: 0;
}
div.bhoechie-tab-menu div.list-group>a{
  margin-bottom: 0;
}
div.bhoechie-tab-menu div.list-group>a .glyphicon,
div.bhoechie-tab-menu div.list-group>a .fa {
  color: #30a5ff;
}
div.bhoechie-tab-menu div.list-group>a:first-child{
  border-top-right-radius: 0;
  -moz-border-top-right-radius: 0;
}
div.bhoechie-tab-menu div.list-group>a:last-child{
  border-bottom-right-radius: 0;
  -moz-border-bottom-right-radius: 0;
}
div.bhoechie-tab-menu div.list-group>a.active,
div.bhoechie-tab-menu div.list-group>a.active .glyphicon,
div.bhoechie-tab-menu div.list-group>a.active .fa{
  background-color: #30a5ff;
  background-image: #30a5ff;
  color: #ffffff;
}
div.bhoechie-tab-menu div.list-group>a.active:after{
  content: '';
  position: absolute;
  left: 100%;
  top: 50%;
  margin-top: -13px;
  border-left: 0;
  border-bottom: 13px solid transparent;
  border-top: 13px solid transparent;
  border-left: 10px solid #30a5ff;
}

div.bhoechie-tab-content{
  background-color: #ffffff;
  /* border: 1px solid #eeeeee; */
  padding-left: 0px;
  padding-top: 5px;
}

div.bhoechie-tab div.bhoechie-tab-content:not(.active){
  display: none;
}
.loader {
    border: 16px solid #f3f3f3; /* Light grey */
    border-top: 16px solid #3498db; /* Blue */
    border-radius: 50%;
    width: 60px;
    height: 60px;
    animation: spin 2s linear infinite;
    margin-top:300px;
    margin-left:700px;
}
</style>	
</head>
<body style="padding-top: 5px;">
<div class="container" style="width:100%;">
<div>
<h3>
	<strong>Employee Detail Management</strong>
</h3>
</div>
<form:form cssClass="form-horizontal" role="form"
				id="empForm" data-toggle="validator" method="post"
				modelAttribute="employeeBean" enctype="multipart/form-data" name="empForm">
	<c:if test="${not empty successMsg}">
			<script type="text/javascript">
				alert('${successMsg}');
				window.close();
			</script>
		</c:if>
		<c:if test="${not empty errorMsg}">
			<script type="text/javascript">
				alert('${errorMsg}');
				window.close();
			</script>
		</c:if>
	 <div class="row">
        <div class="col-lg-12 col-md-5 col-sm-8 col-xs-9 bhoechie-tab-container" style="max-height:560px;">
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 bhoechie-tab-menu">
              <div class="list-group">
                <a href="#" class="list-group-item active text-center">
                  <h4 class="glyphicon glyphicon-user"></h4><br/>Current Work Information
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="glyphicon glyphicon-road"></h4><br/>Basic Detail
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="glyphicon glyphicon-tasks"></h4><br/>Contact Information
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="glyphicon glyphicon-star"></h4><br/>Education Details
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="glyphicon glyphicon-globe"></h4><br/>Geographical Details
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="glyphicon glyphicon-wrench"></h4><br/>Skill Set
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="glyphicon glyphicon-calendar"></h4><br/>Employment History
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="glyphicon glyphicon-tasks"></h4><br/>Account Detail
                </a>
              </div>
            </div>
            <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 bhoechie-tab" style="max-height:560px;overflow-y: auto;">
                <div class="bhoechie-tab-content active">
                <div class="panel-heading">
							<strong>Update Employee Work Information ::</strong> ${employeeBean.firstName} ${employeeBean.lastName} 
						</div>
     <div class="panel-body">
        <div class="col-sm-12">
        				<div class="row">
							<div class="col-sm-6 form-group">
								<label>Joining Date</label>
								<a href="#l" onclick="saveUserDetails();return false;">
						          <span class="glyphicon glyphicon-pencil"></span>
						        </a>
								<form:input type="text" path="joiningDate" name="joiningDate" id="joiningDateId" class="form-control" onkeydown="return false;"/>
							</div>		
							<div class="col-sm-6 form-group">
								<label>Last Working Date</label>
								<a href="#l" onclick="saveUserDetails();return false;">
						          <span class="glyphicon glyphicon-pencil"></span>
						        </a>
								<form:input type="text" path="lastWorkingDate" name="lastWorkingDate" id="lastWorkingDateId" class="form-control" onkeydown="return false;"/>
							</div>
							<%-- <form:input type="hidden" path="lastWorkingDate" name="lastWorkingDate" id="lastWorkingDate" /> --%>	
						</div>
        				<div class="row">
							<div class="col-sm-6 form-group">
								<label>Base Location</label>
								<a href="#l" onclick="populateUserInfoDiv('${employeeBean.baseLocation}','BASE_LOCATION','Base Location',this.id);" id="btnBaseLocId">
						          <span class="glyphicon glyphicon-pencil"></span>
						        </a>
						        <form:input type="text" path="baseLocation" name="baseLocation" id="baseLocation" class="form-control input-sm" readOnly="true"/>
								<%-- <form:hidden path="baseLocation" name="baseLocation" id="baseLocation" value="${employeeBean.baseLocation}"/> --%>
								<%-- <form:select  path="baseLocation" id="baseLocation" name="baseLocation" for="baseLocation" placeholder="Base Location" maxlength="20" class="form-control">
								<form:option value='${employeeBean.baseLocation}'>${employeeBean.baseLocation}</form:option>
								</form:select> --%>
							</div>		
							<div class="col-sm-6 form-group">
								<label>Department</label>
								<a href="#l" onclick="populateUserInfoDiv('${employeeBean.department}','DEPARTMENT','Department',this.id);" id="btnDeptId">
						          <span class="glyphicon glyphicon-pencil"></span>
						        </a>
						        <form:input type="text" path="department" name="department" id="department" class="form-control input-sm" readOnly="true"/>
								<%-- <form:hidden path="department" name="department" id="department" value="${employeeBean.department}"/> --%>
								<%-- <form:select path="department" id="department" name="department" for="department" placeholder="Department" maxlength="20" class="form-control">
								<form:option value='${employeeBean.department}'>${employeeBean.department}</form:option>
								</form:select> --%>
							</div>	
						</div>
        				<div class="row">
							<div class="col-sm-6 form-group">
								<label>Designation</label>
								<a href="#l" onclick="populateUserInfoDiv('${employeeBean.designation}','DESIGNATION','Designation',this.id);" id="btnDesignationId">
						          <span class="glyphicon glyphicon-pencil"></span>
						        </a>
						        <form:input type="text" path="designation" name="designation" id="designation" class="form-control input-sm" readOnly="true"/>
								<%-- <form:hidden path="designation" name="designation" id="designation" value="${employeeBean.designation}"/> --%>
								<%-- <form:select path="designation" id="designation" name="designation" for="designation" placeholder="Designation" maxlength="20" class="form-control">
								<form:option value='${employeeBean.designation}'>${employeeBean.designation}</form:option>
								</form:select> --%>
							</div>		
							<div class="col-sm-6 form-group">
								<label>Role</label>
								<a href="#l" onclick="populateUserInfoDiv('${employeeBean.role}','ROLE','Role',this.id);" id="editRoleId">
						          <span class="glyphicon glyphicon-pencil"></span>
						        </a>
						        <form:input type="text" path="role" name="role" id="role" class="form-control input-sm" readOnly="true"/>
								<%-- <form:hidden path="role" name="role" id="role" value="${employeeBean.role}"/> --%>
								<%-- <form:select path="role" id="role" name="role" for="role" placeholder="User Role" maxlength="20" class="form-control">
								<form:option value='${employeeBean.role}'>${employeeBean.role}</form:option>
								</form:select> --%>
							</div>	
						</div>
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Reporting Person</label>
								<a href="#l" onclick="populateUserInfoDiv('${employeeBean.reportingPerson}','REPORTING_PERSON','REPORTING_PERSON',this.id);" id="btnRMPersonId">
						          <span class="glyphicon glyphicon-pencil"></span>
						        </a>
								<form:input type="text" path="reportingPersonFullName" name="reportingPersonFullName" id="reportingPersonFullName" class="form-control input-sm" readOnly="true"/>
								<form:hidden path="reportingPerson" name="reportingPerson" id="reportingPerson" value="${employeeBean.reportingPerson}"/>
								<%-- <form:select path="reportingPerson" id="reportingPerson" name="reportingPerson" for="reportingPerson" placeholder="Reportee" class="form-control" onchange="validateRM(this.value,'${employeeBean.userName}');">
									<form:option value='${employeeBean.reportingPerson}'>${employeeBean.reportingPerson}</form:option>
								</form:select> --%>
							</div>		
							<div class="col-sm-6 form-group">
								<label>HR Manager</label>
								<a href="#l" onclick="populateUserInfoDiv('${employeeBean.hrManager}','HR_MANAGER','HR_MANAGER',this.id);" id="btnHRPersonId">
						          <span class="glyphicon glyphicon-pencil"></span>
						        </a>
								<form:input type="text" path="hrManagerFullName" name="hrManagerFullName" id="hrManagerFullName" class="form-control input-sm" readOnly="true"/>
								<form:hidden path="hrManager" name="hrManager" id="hrManager" value="${employeeBean.hrManager}"/>
								<%-- <form:select path="hrManager" id="hrManager" name="hrManager" for="hrManager" placeholder="HR Manager" class="form-control" onchange="validateHR(this.value,'${employeeBean.userName}');">
									<form:option value='${employeeBean.hrManager}'>${employeeBean.hrManager}</form:option>
								</form:select> --%>
							</div>	
						</div>
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Account Unit</label>
								<a href="#l" onclick="populateUserInfoDiv('${employeeBean.accountUnit}','ACCOUNT_UNIT','Account Unit',this.id);" id="btnAccUnitId">
						          <span class="glyphicon glyphicon-pencil"></span>
						        </a>
						        <form:input type="text" path="accountUnit" name="accountUnit" id="accountUnit" class="form-control input-sm" readOnly="true"/>
								<%-- <form:hidden path="accountUnit" name="accountUnit" id="accountUnit" value="${employeeBean.accountUnit}"/> --%>
								<%-- <form:select path="accountUnit" name="accountUnit" id="accountUnit" placeholder="Account Unit" maxlength="10" class="form-control">
								<form:option value='${employeeBean.accountUnit}'>${employeeBean.accountUnit}</form:option>
								</form:select> --%>
							</div>
							<div class="col-sm-6 form-group">
								<label>Policy Group</label>
								<a href="#l" onclick="populateUserInfoDiv('${employeeBean.policyGroupName}','POLICY_GROUP','HR Policy Group',this.id);" id="btnPolicyGrpId">
						          <span class="glyphicon glyphicon-pencil"></span>
						        </a>
						        <form:input type="text" path="policyGroupName" name="policyGroupName" id="policyGroupName" class="form-control input-sm" readOnly="true"/>
								<%-- <form:hidden path="policyGroupName" name="policyGroupName" id="policyGroupName" value="${employeeBean.policyGroupName}"/> --%>
								<%-- <form:select path="policyGroupName" name="policyGroupName" id="policyGroupName" class="form-control">
								<form:option value='${employeeBean.policyGroupName}'>${employeeBean.policyGroupName}</form:option>
								</form:select> --%>
							</div>
						</div>
        </div>
        <!-- <button type="button" id="submitEmpForm" class="btn btn-md btn-info pull-right" onclick="saveUserDetails();return false;">Save</button> -->
        </div>
      </div>
                
                <div class="bhoechie-tab-content">
                    <div class="panel-body">
        <div class="col-sm-12">
        				<div class="row">
        					<div class="col-sm-4 form-group">
								<form:label path="userName" for="userName">Employee Id*</form:label>
								<form:input type="text" path="userCode" name="userCode" id="userCode" class="form-control" />
								<form:hidden path="userName" name="userName" id="userName" class="form-control" />
							</div>
							<div class="col-sm-4 form-group">
								<form:label path="attendanceId" for="attendanceId">Biomatric Id*</form:label>
								<form:input type="text" path="attendanceId" name="attendanceId" id="attendanceId" class="form-control" />
							</div>
							<div class="col-sm-4 form-group">
								<label>Salutation*</label>
								<form:input type="text" path="salutation" name="salutation" id="salutation" class="form-control"/>
								<%-- <form:select path="salutation" name="salutation" class="form-control">
									<form:option value="Mr">Mr.</form:option>
									<form:option value="Mrs">Mrs.</form:option>
									<form:option value="Miss">Miss</form:option>
								</form:select> --%>
							</div>
        				</div>
						<div class="row">
							<div class="col-sm-4 form-group">
								<form:label path="firstName" for="firstName">First Name*</form:label>
								<form:input type="text" id="firstName" path="firstName" name="firstName" for="firstName" placeholder="First Name" class="form-control" />
							</div>
							<%-- <c:if test="${not empty employeeBean.middleName}"> --%>
							<div class="col-sm-4 form-group">
								<label>Middle Name</label>
								<form:input type="text" id="middleName" path="middleName" name="middleName" for="middleName" placeholder="Middle Name(if applicable)" class="form-control"/>
							</div>
							<%-- </c:if> --%>
							<div class="col-sm-4 form-group">
								<form:label path="lastName" for="lastName">Last Name*</form:label>
								<form:input type="text" id="lastName" path="lastName" name="lastName" for="lastName" placeholder="Last Name" class="form-control" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4 form-group">
								<label>Mother's Name*</label>
								<form:input type="text" id="motherName" path="motherName" name="motherName" for="motherName" placeholder="Mother's Name" class="form-control"/>
							</div>
							<div class="col-sm-4 form-group">
								<label>Father's Name</label>
								<form:input type="text" id="fatherName" path="fatherName" name="fatherName" for="fatherName" placeholder="Father's Name" class="form-control"/>
							</div>
							<div class="col-sm-4 form-group">
								<label>Mother Tongue*</label>
								<form:input type="text" id="motherTongue" path="motherTongue" name="motherTongue" for="motherTongue" placeholder="Mother's tongue" class="form-control"/>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4 form-group">
								<label>Gender*</label>
								<form:select path="gender" name="gender" class="form-control">
									<form:option value="Male">Male</form:option>
									<form:option value="Female">Female</form:option>
									<form:option value="Other">Other</form:option>
								</form:select>
							</div>
							<div class="col-sm-4 form-group">
								<label>DOB</label>
								<form:input type="text" path="dob" name="dob" for="dob" placeholder="YYYY-MM-DD" class="form-control" onfocusout="calculateAge(this.value)" id="userDobId" onkeydown="return false;"/>
							</div>
							<div class="col-sm-4 form-group">
								<label>Age</label>
								<form:input type="text" path="age" name="age" for="age" placeholder="User Age" class="form-control" maxlength="2" id="userAgeId" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4 form-group">
								<label>Aadhar Card Number</label>
								<form:input type="text" path="aadharNo" name="aadharNo" id="aadharNo" for="aadharNo" placeholder="Aadhar Card No" maxlength="12" class="form-control" />
							</div>
							<div class="col-sm-4 form-group">
								<label>PAN Card Number</label>
								<form:input type="text" path="pancardNo" name="pancardNo" id="pancardNo" for="pancardNo" placeholder="PAN Number" maxlength="12" class="form-control" />
							</div>
							<div class="col-sm-4 form-group">
								<label>Passport Number</label>
								<form:input type="text" path="passportNo" name="passportNo" id="passportNo" for="passportNo" placeholder="Passport Number" maxlength="20" class="form-control" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4 form-group">
								<label>Blood Group*</label>
								<form:select path="bloodGroup" id="bloodGroup" name="bloodGroup" class="form-control">
									<form:option value="">Select</form:option>
									<form:option value="O+">O+</form:option>
									<form:option value="O-">O-</form:option>
									<form:option value="AB+">AB+</form:option>
									<form:option value="AB-">AB-</form:option>
									<form:option value="A+">A+</form:option>
									<form:option value="A-">A-</form:option>
									<form:option value="B+">B+</form:option>
									<form:option value="B-">B-</form:option>
								</form:select>
							</div>
							<div class="col-sm-4 form-group">
								<label>Marital Status</label>
								<form:select path="maritalStatus" id="maritalStatus" name="maritalStatus" class="form-control" onchange="updateSpouseNameField(this.value);return false;">
									<form:option value="">Select</form:option>
									<form:option value="Unmarried">Unmarried</form:option>
									<form:option value="Married">Married</form:option>
								</form:select>
							</div>
								<div class="col-sm-4 form-group">
									<label>Spouse Name</label>
										<form:input type="text" path="spouseName" name="spouseName" id="spouseNameId" for="spouseName" placeholder="Spouse Name" maxlength="50" class="form-control" />
									</div>
						</div>
					 <button type="button" id="submitEmpForm" class="btn btn-md btn-info pull-right">Save</button>					
					</div>
        </div>
                </div>
                <!-- train section -->
                <div class="bhoechie-tab-content">
                    <div class="panel-body">
        <div class="col-sm-12">
        				<div class="row">
							<div class="col-sm-4 form-group">
								<label>Primary Phone Number*</label>
								<form:input type="text" path="primaryPhoneNum" name="primaryPhoneNum" id="primaryPhoneNum" for="primaryPhoneNum" placeholder="Primary Contact No" maxlength="10" class="form-control"/>
							</div>
							<div class="col-sm-4 form-group">
								<label>Secondary Phone Number</label>
								<form:input type="text" path="secondaryPhoneNum" name="secondaryPhoneNum" id="secondaryPhoneNum" for="secondaryPhoneNum" placeholder="Secondary Contact No" maxlength="10" class="form-control" />
							</div>
							<div class="col-sm-4 form-group">
								<label>Emergency Contact Number*</label>
								<form:input type="text" path="emergencyPhoneNum" name="emergencyPhoneNum" id="emergencyPhoneNum" for="emergencyPhoneNum" placeholder="Emergency Phone No" maxlength="10" class="form-control" />
							</div>	
						</div>
        				<div class="row">
							<div class="col-sm-6 form-group">
								<label>Primary Email Id*</label>
								<form:input type="text" path="primaryEmailId" name="primaryEmailId" id="primaryEmailId" for="primaryEmailId" placeholder="Email Id" maxlength="30" class="form-control" />
							</div>
							<div class="col-sm-6 form-group">
								<label>Secondary Email Id</label>
								<form:input type="text" path="secondaryEmailId" name="secondaryEmailId" id="secondaryEmailId" for="secondaryEmailId" placeholder="Optional email id" maxlength="30" class="form-control" />
							</div>
							<div class="col-sm-12 form-group">
								<label>Website/Linked In Profile</label>
								<form:input type="text" path="webOrLinkedInLink" name="webOrLinkedInLink" id="webOrLinkedInLink" for="webOrLinkedInLink" placeholder="LinkedIn URL" maxlength="50" class="form-control" />
							</div>
						</div>
        </div>
        <button type="button" id="submitEmpForm" class="btn btn-md btn-info pull-right" onclick="saveUserDetails();return false;">Save</button>
        </div>
                </div>
    
      
                <div class="bhoechie-tab-content">
                    <div class="panel-body">
        	<div class="col-sm-12">
        		<div class=""row>
        			<table class="table table-hover">
        			<thead style="background-color:#30a5ff;color:white;">
        				<tr>
        				<th>Qualification</th>
        				<th>School/College</th>
        				<th>University/Board</th>
        				<th>Stream/Degree/Specilization</th>
        				<th>Year</th>
        				<th>Score(%)/CGPA</th>
        				<th>Action</th>
        				</tr>
        			</thead>
        			<tbody>
        				<c:forEach items="${employeeBean.empEducationList}" var="eduvar" varStatus="status">
						<tr id='query${status.index}'>
							<td id="empEducationList[${status.index}].qualification">${eduvar.qualification}</td>
							<td id="empEducationList[${status.index}].schoolCollegeName">${eduvar.schoolCollegeName}</td>
							<td id="empEducationList[${status.index}].universityBoard">${eduvar.universityBoard}</td>
							<td id="empEducationList[${status.index}].stream">${eduvar.stream}</td>
							<td id="empEducationList[${status.index}].year">${eduvar.year}</td>
							<td id="empEducationList[${status.index}].score">${eduvar.score}</td>
							<td>
								<a href="#!" class="btn btn-info btn-sm" onclick="deleteEducationRow('${eduvar.qualificationId}')">
						          <span class="glyphicon glyphicon-trash"></span> 
						        </a>
							</td>
						</tr>	
						</c:forEach>
        			</tbody>
        			</table>
        		</div>
        	</div>
        </div>
        <button class="btn btn-sm btn-primary" style="margin:5px;" onclick="openEduModal();return false;">Add More</button>
                </div>
                <div class="bhoechie-tab-content">
                    <div class="panel-body">
        <div class="col-sm-12" style="border: 1px solid #ddd;margin-bottom:5px;">
        <div class="form-group" style="padding-top:10px;padding-left:10px;">
							<label>Permanent Address</label>
							<form:input type="textarea" path="permanentAddress" name="permanentAddress" for="permanentAddress" placeholder="Permanent Address" maxlength="300" class="form-control" />
						</div>
						<div class="row" style="padding-top:10px;padding-left:10px;">
							<div class="col-sm-3 form-group">
								<label>Country</label>
								<form:input type="text" path="permanentAddressCountry" name="permanentAddressCountry" id="permanentAddressCountry" for="permanentAddressCountry"  maxlength="20" class="form-control" />
							</div>
							<div class="col-sm-3 form-group">
								<label>State</label>
								<form:input type="text" path="permanentAddressState" name="permanentAddressState" id="permanentAddressState" for="permanentAddressState" placeholder="" maxlength="20" class="form-control" />
							</div>	
							<div class="col-sm-3 form-group">
								<label>City</label>
								<form:input type="text" path="permanentAddressCity" name="permanentAddressCity" id="permanentAddressCity" for="permanentAddressCity" placeholder="" maxlength="20" class="form-control" />
							</div>	
							<div class="col-sm-3 form-group">
								<label>Zip Code</label>
								<form:input type="text" path="permanentAddressZipCode" name="permanentAddressZipCode" id="permanentAddressZipCode" for="permanentAddressZipCode" placeholder="" maxlength="10" class="form-control" />
							</div>		
						</div>
		</div>
		<div class="col-sm-12" style="border: 1px solid #ddd;margin-bottom:5px;">				
						<div class="form-group" style="padding-top:10px;padding-left:10px;">
							<label>Communication Address</label>
							<form:input type="textarea" path="communicationAddress" name="communicationAddress" for="communicationAddress" id="communicationAddress" placeholder="Communication Address" maxlength="300" class="form-control" />
						</div>	
						<div class="row" style="padding-top:10px;padding-left:10px;">
							<div class="col-sm-3 form-group">
								<label>Country</label>
								<form:input type="text" path="communicationAddressCountry" name="communicationAddressCountry" for="communicationAddressCountry" id="communicationAddressCountry"  maxlength="20" class="form-control" />
							</div>
							<div class="col-sm-3 form-group">
								<label>State</label>
								<form:input type="text" path="communicationAddressState" name="communicationAddressState" for="communicationAddressState" id="communicationAddressState" placeholder="" maxlength="20" class="form-control" />
							</div>	
							<div class="col-sm-3 form-group">
								<label>City</label>
								<form:input type="text" path="communicationAddressCity" name="communicationAddressCity" for="communicationAddressCity" id="communicationAddressCity" placeholder="" maxlength="20" class="form-control" />
							</div>	
							<div class="col-sm-3 form-group">
								<label>Zip Code</label>
								<form:input type="text" path="communicationAddressZipCode" name="communicationAddressZipCode" for="communicationAddressZipCode" id="communicationAddressZipCode" placeholder="" maxlength="10" class="form-control" />
							</div>		
						</div>
        </div>
        <div class="col-sm-12" style="border:1px solid #ddd;margin-bottom:5px;">				
						<div class="form-group" style="padding-top:10px;padding-left:10px;">
							<label>Office Address</label>
							<form:input type="textarea" path="officeAddress" name="officeAddress" for="officeAddress" id="officeAddress" placeholder="Client Office Address" maxlength="300" class="form-control" />
						</div>	
						<div class="row" style="padding-top:10px;padding-left:10px;">
							<div class="col-sm-3 form-group">
								<label>Country</label>
								<form:input type="text" path="officeAddressCountry" name="officeAddressCountry" for="officeAddressCountry" id="officeAddressCountry"  maxlength="20" class="form-control" />
							</div>
							<div class="col-sm-3 form-group">
								<label>State</label>
								<form:input type="text" path="officeAddressState" name="officeAddressState" for="officeAddressState" id="officeAddressState" placeholder="" maxlength="20" class="form-control" />
							</div>	
							<div class="col-sm-3 form-group">
								<label>City</label>
								<form:input type="text" path="officeAddressCity" name="officeAddressCity" for="officeAddressCity" id="officeAddressCity" placeholder="" maxlength="20" class="form-control" />
							</div>	
							<div class="col-sm-3 form-group">
								<label>Zip Code</label>
								<form:input type="text" path="officeAddressZipCode" name="officeAddressZipCode" for="officeAddressZipCode" id="officeAddressZipCode" placeholder="" maxlength="10" class="form-control" />
							</div>		
						</div>
        </div>
        <div class="col-sm-12" style="border:1px solid #ddd;margin-bottom:5px;">				
						<div class="form-group" style="padding-top:10px;padding-left:10px;">
							<label>Client Office Address</label>
							<form:input type="textarea" path="clientOfficeAddress" name="clientOfficeAddress" for="clientOfficeAddress" id="clientOfficeAddress" placeholder="Client Office Address" maxlength="300" class="form-control" />
						</div>	
						<div class="row" style="padding-top:10px;padding-left:10px;">
							<div class="col-sm-3 form-group">
								<label>Country</label>
								<form:input type="text" path="clientOfficeAddressCountry" name="clientOfficeAddressCountry" for="clientOfficeAddressCountry" id="clientOfficeAddressCountry"  maxlength="20" class="form-control" />
							</div>
							<div class="col-sm-3 form-group">
								<label>State</label>
								<form:input type="text" path="clientOfficeAddressState" name="clientOfficeAddressState" for="clientOfficeAddressState" id="clientOfficeAddressState" placeholder="" maxlength="20" class="form-control" />
							</div>	
							<div class="col-sm-3 form-group">
								<label>City</label>
								<form:input type="text" path="clientOfficeAddressCity" name="clientOfficeAddressCity" for="clientOfficeAddressCity" id="clientOfficeAddressCity" placeholder="" maxlength="20" class="form-control" />
							</div>
							<div class="col-sm-3 form-group">
								<label>Zip Code</label>
								<form:input type="text" path="clientOfficeAddressZipCode" name="clientOfficeAddressZipCode" for="clientOfficeAddressZipCode" id="clientOfficeAddressZipCode" placeholder="" maxlength="10" class="form-control" />
							</div>		
						</div>
        </div>
        </div>
        <button type="button" id="submitEmpForm" class="btn btn-md btn-info pull-right" onclick="saveUserDetails();return false;" style="margin-bottom:5px;">Save</button>
                </div>
     <div class="bhoechie-tab-content">
       <div class="panel-body">
        	<div class="col-sm-12">
        		<div class=""row>
        			<table class="table table-hover">
        			<thead style="background-color:#30a5ff;color:white;">
        				<tr>
        				<th>Name</th>
        				<th>Type</th>
        				<th>Version</th>
        				<th>Experience(In month)</th>
        				<th>Action</th>
        				</tr>
        			</thead>
        			<tbody>
        				<c:forEach items="${employeeBean.empSkilltList}" var="skillvar" varStatus="status">
						<tr id='query${status.index}'>
							<td id="empSkilltList[${status.index}].skillName">${skillvar.skillName}</td>
							<td id="empSkilltList[${status.index}].skillType">${skillvar.skillType}</td>
							<td id="empSkilltList[${status.index}].version">${skillvar.version}</td>
							<td id="empSkilltList[${status.index}].experienceMonth">${skillvar.experienceMonth}</td>
							<td>
								<a href="#!" class="btn btn-info btn-sm" onclick="deleteSkillRow('${skillvar.skillId}')">
						          <span class="glyphicon glyphicon-trash"></span> 
						        </a>
							</td>
						</tr>	
						</c:forEach>
        			</tbody>
        			</table>
        		</div>
        	</div>
        </div>
        <button class="btn btn-sm btn-primary" style="margin:5px;" onclick="openSkillModal();return false;">Add More</button>
                </div>
        <div class="bhoechie-tab-content">
       <div class="panel-body">
        	<div class="col-sm-12">
        		<div class=""row>
        			<table class="table table-hover">
        			<thead style="background-color:#30a5ff;color:white;">
        				<tr>
        				<th>Company Name</th>
        				<th>Start Date</th>
        				<th>End Date</th>
        				<th>Last Designation</th>
        				<th>Duration(In month)</th>
        				<th>Action</th>
        				</tr>
        			</thead>
        			<tbody>
        				<c:forEach items="${employeeBean.empWorkHistoryList}" var="workvar" varStatus="status">
						<tr id='query${status.index}'>
							<td id="empWorkHistoryList[${status.index}].companyName">${workvar.companyName}</td>
							<td id="empWorkHistoryList[${status.index}].startDate">${workvar.startDate}</td>
							<td id="empWorkHistoryList[${status.index}].endDate">${workvar.endDate}</td>
							<td id="empWorkHistoryList[${status.index}].lastDesignation">${workvar.lastDesignation}</td>
							<td id="empWorkHistoryList[${status.index}].durationMonth">${workvar.durationMonth}</td>
							<td>
								<a href="#!" class="btn btn-info btn-sm" onclick="deleteEmployementRow('${workvar.companyId}')">
						          <span class="glyphicon glyphicon-trash"></span> 
						        </a>
							</td>
						</tr>	
						</c:forEach>
        			</tbody>
        			</table>
        		</div>
        	</div>
        <button class="btn btn-sm btn-primary" style="margin:5px;" onclick="openEmployementModal();return false;">Add More</button>	
        </div>
                </div>
                <div class="bhoechie-tab-content">
       <div class="panel-body">
        	<div class="col-sm-12">
        		<div class=""row>
        			<table class="table table-hover">
        			<thead style="background-color:#30a5ff;color:white;">
        				<tr>
        				<th>Bank Name</th>
        				<th>Account Type</th>
        				<th>IFSC Code</th>
        				<th>Account Number</th>
        				<th>Primary Account</th>
        				<th>Account Holder</th>
        				<th>Action</th>
        				</tr>
        			</thead>
        			<tbody>
        				<c:forEach items="${employeeBean.empAccountList}" var="accvar" varStatus="status">
						<tr id='query${status.index}'>
							<td id="empAccountList[${status.index}].bankName">${accvar.bankName}</td>
							<td id="empAccountList[${status.index}].accountType">${accvar.accountType}</td>
							<td id="empAccountList[${status.index}].ifscCode">${accvar.ifscCode}</td>
							<td id="empAccountList[${status.index}].accountNumber">${accvar.accountNumber}</td>
							<td id="empAccountList[${status.index}].primaryAccFlag">${accvar.primaryAccFlag}</td>
							<td id="empAccountList[${status.index}].accountHolderName">${accvar.accountHolderName}</td>
							<td>
								<a href="#!" class="btn btn-info btn-sm" onclick="deleteAccountRow('${accvar.accountNumber}')">
						          <span class="glyphicon glyphicon-trash"></span> 
						        </a>
							</td>
						</tr>	
						</c:forEach>
        			</tbody>
        			</table>
        		</div>
        	</div>
        <button class="btn btn-sm btn-primary" style="margin:5px;" onclick="openBankModal();return false;">Add More</button>
        <br>
        <span style="font-size:13px;color:red;">*You can make only one account primary which will be used for all financial transaction</span>	
        </div>
                </div>        
            </div>
        </div>
  </div>
	
				
  
</form:form>
</div>
<div class="footer" style="text-align:center;margin-top: 5px;">
	<!-- <div class="copyrights">
			<span>Use Google Chrome 37 or higher version for better performance.</span>
		</div> -->
	<div class="copyrights">Copyright © 2017 SupraITS. All rights reserved. | <a style="color:#999;" title="supraits.com/privacy" href="http://www.supraits.com/privacy">Privacy</a> 
</div>
</div>
			<div class="modal fade" id="addBankModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">User Bank Account Information </h4>
				        </div>
				        <div class="modal-body">
				        <div class="form-group">
				          <label>Bank Name:</label>
				          <!-- <input class="input-md  textinput textInput form-control" id="bankName" maxlength="100" minlength="3" name="taskName" placeholder="" style="margin-bottom: 10px" type="text" /> -->
				          <select class="form-control" id="bankName" style="margin-bottom: 10px">
				          	    <option selected="selected" value="">Allahabad Bank</option>
						        <option value="Andhra Bank">Andhra Bank</option>
						        <option value="Axis Bank">Axis Bank</option>
						        <option value="Bank of Bahrain and Kuwait">Bank of Bahrain and Kuwait</option>
						        <option value="Bank of Baroda - Corporate BankingBank of Baroda - Corporate Banking">Bank of Baroda - Corporate Banking</option>
						        <option value="Bank of Baroda - Retail Banking">Bank of Baroda - Retail Banking</option>
						        <option value="Bank of India">Bank of India</option>
						        <option value="Bank of Maharashtra">Bank of Maharashtra</option>
						        <option value="Canara Bank">Canara Bank</option>
						        <option value="Central Bank of India">Central Bank of India</option>
						        <option value="City Union Bank">City Union Bank</option>
						        <option value="Corporation Bank">Corporation Bank</option>
						        <option value="Deutsche Bank">Deutsche Bank</option>
						        <option value="Development Credit Bank">Development Credit Bank</option>
						        <option value="Dhanlaxmi Bank">Dhanlaxmi Bank</option>
						        <option value="Federal Bank">Federal Bank</option>
						        <option value="ICICI Bank">ICICI Bank</option>
						        <option value="IDBI Bank">IDBI Bank</option>
						        <option value="Indian Bank">Indian Bank</option>
						        <option value="Indian Overseas Bank">Indian Overseas Bank</option>
						        <option value="IndusInd Bank">IndusInd Bank</option>
						        <option value="ING Vysya Bank">ING Vysya Bank</option>
						        <option value="Jammu and Kashmir Bank">Jammu and Kashmir Bank</option>
						        <option value="Karnataka Bank Ltd">Karnataka Bank Ltd</option>
						        <option value="Karur Vysya Bank">Karur Vysya Bank</option>
						        <option value="Kotak Bank">Kotak Bank</option>
						        <option value="Laxmi Vilas Bank">Laxmi Vilas Bank</option>
						        <option value="Oriental Bank of Commerce">Oriental Bank of Commerce</option>
						        <option value="Punjab National Bank - Corporate Banking">Punjab National Bank - Corporate Banking</option>
						        <option value="Punjab National Bank - Retail Banking">Punjab National Bank - Retail Banking</option>
						        <option value="Punjab & Sind Bank">Punjab & Sind Bank</option>
						        <option value="Shamrao Vitthal Co-operative Bank">Shamrao Vitthal Co-operative Bank</option>
						        <option value="South Indian Bank">South Indian Bank</option>
						        <option value="State Bank of Bikaner & Jaipur">State Bank of Bikaner & Jaipur</option>
						        <option value="State Bank of Hyderabad">State Bank of Hyderabad</option>
						        <option value="State Bank of India">State Bank of India</option>
						        <option value="State Bank of Mysore">State Bank of Mysore</option>
						        <option value="State Bank of Patiala">State Bank of Patiala</option>
						        <option value="State Bank of Travancore">State Bank of Travancore</option>
						        <option value="Syndicate Bank">Syndicate Bank</option>
						        <option value="Tamilnad Mercantile Bank Ltd.">Tamilnad Mercantile Bank Ltd.</option>
						        <option value="UCO Bank">UCO Bank</option>
						        <option value="Union Bank of India">Union Bank of India</option>
						        <option value="United Bank of India">United Bank of India</option>
						        <option value="Vijaya Bank">Vijaya Bank</option>
						        <option value="Yes Bank Ltd">Yes Bank Ltd</option>
				          </select>
				        </div>
				        <div class="form-group">  
				          <label>Type</label>
				          <select id="accType" class="form-control" name="taskType" style="margin-bottom: 10px">
				          		<option value="">Select Type</option>
				          		<option value="Saving">Saving</option>
				          		<option value="Current">Current</option>
				          </select>
				        </div>
				        <div class="form-group">
				          <label>Account Number:</label>
				          <input class="input-md  textinput textInput form-control" id="accountNumber" maxlength="20" minlength="3" name="taskName" placeholder="" style="margin-bottom: 10px" type="text" />
				        </div>
				        <div class="form-group">
				          <label>IFSC Code:</label>
				          <input class="input-md  textinput textInput form-control" id="ifscCode" maxlength="12" minlength="3" name="taskName" placeholder="" style="margin-bottom: 10px" type="text" />
				        </div>
				        <div class="form-group">  
				          <label>Primary Account Flag</label>
				          <select id="primaryFlag" class="form-control" name="primaryFlag" style="margin-bottom: 10px">
				          		<option value="N">No</option>
				          		<option value="Y">Yes</option>
				          </select>
				        </div>
				        <div class="form-group">
				          <label>Account Holder Name:</label>
				          <input class="input-md  textinput textInput form-control" id="accHolderName" maxlength="100" minlength="3" name="taskName" placeholder="" style="margin-bottom: 10px" type="text" />
				        </div>
				        </div>
				        <div class="modal-footer">
	    			      <button type="button" class="btn btn-success" id="saveNewAccDetail" onclick="insertNewUserAcc('${employeeBean.userName}');">Save</button>
				          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div>
				        </div>
				       </div>
				 </div>
				 	<div class="modal fade" id="addEmployementModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Past Employement History </h4>
				        </div>
				        <div class="modal-body">
				        <div class="form-group">
				          <label>Organization Name:</label>
				          <input class="input-md  textinput textInput form-control" id="companyName" maxlength="100" minlength="6" name="taskName" placeholder="" style="margin-bottom: 10px" type="text" />
				        </div>
				        <div class="form-group">
				          <label>Last Designation:</label>
				          <input class="input-md  textinput textInput form-control" id="lastDesignation" maxlength="100" minlength="3" name="taskName" placeholder="" style="margin-bottom: 10px" type="text" />
				        </div>
				        <div class="form-group">
				          <label>Experience(in month):</label>
				          <input class="input-md  textinput textInput form-control" id="experience" maxlength="3" minlength="1" name="taskName" placeholder="" style="margin-bottom: 10px" type="text" />
				        </div>
				        <div class="form-group">
				          <label>Start Date:</label>
				          <input type="date" class="input-md  textinput textInput form-control" id="startDate" maxlength="100" minlength="6" name="taskName" placeholder="" style="margin-bottom: 10px" type="text" />
				        </div>
				        <div class="form-group">
				          <label>Last Working Day:</label>
				          <input type="date" class="input-md  textinput textInput form-control" id="endDate" maxlength="100" minlength="6" name="taskName" placeholder="" style="margin-bottom: 10px" type="text" />
				        </div>
				        </div>
				        <div class="modal-footer">
	    			      <button type="button" class="btn btn-success" id="saveNewWork" onclick="insertNewEmployement('${employeeBean.userName}');">Save</button>
				          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div>
				        </div>
				       </div>
				 </div>
				 	<div class="modal fade" id="addSkillModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">User Technical Skill & Certification </h4>
				          <span id="id_prjctName1"></span>
				          <input type="hidden" id="pidValue" name="pidValue" />
				        </div>
				        <div class="modal-body">
				        <div class="form-group">  
				          <label>Type</label>
				          <select id="skillType" class="form-control" name="skillType" style="margin-bottom: 10px">
				          		<option value="">Select Type</option>
				          		<option value="Language">Language</option>
				          		<option value="Tool">Tool</option>
				          		<option value="Database">Database</option>
				          		<option value="Frameowrk">Framework</option>
				          		<option value="ERP">ERP</option>
				          		<option value="Other">Other</option>
				          </select>
				        </div>
				        <div class="form-group">
				          <label>Name:</label>
				          <input class="input-md  textinput textInput form-control" id="skillName" maxlength="50" minlength="4" name="taskName" placeholder="" style="margin-bottom: 10px" type="text" />
				        </div>
				        <div class="form-group">
				          <label>Version:</label>
				          <input class="input-md  textinput textInput form-control" id="version" maxlength="50" minlength="2" name="taskName" placeholder="" style="margin-bottom: 10px" type="text" />
				        </div>
				        <div class="form-group">
				          <label>Experience(in month):</label>
				          <input class="input-md  textinput textInput form-control" id="expMonths" maxlength="3" minlength="1" name="taskName" placeholder="" style="margin-bottom: 10px" type="text" />
				        </div>
				        </div>
				        <div class="modal-footer">
	    			      <button type="button" class="btn btn-success" id="saveNewSkill" onclick="insertNewSkill('${employeeBean.userName}');">Save</button>
				          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div>
				        </div>
				       </div>
				 </div>
				 	<div class="modal fade" id="addEduModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">User Academic History </h4>
				          <!-- <span id="id_prjctName1"></span>
				          <input type="hidden" id="pidValue" name="pidValue" /> -->
				        </div>
				        <div class="modal-body">
				        <div class="form-group">  
				          <label>Qualification</label>
				          <select id="qualification" class="form-control" name="taskType" style="margin-bottom: 10px">
				          		<option value="">Select Type</option>
				          		<option value="10th">10th</option>
				          		<option value="12th">12th</option>
				          		<option value="Graduation">Graduation</option>
				          		<option value="Diploma">Diploma</option>
				          		<option value="Post Graduation">Post Graduation</option>
				          </select>
				        </div>
				        <div class="form-group">
				          <label>School/College Name:</label>
				          <input class="input-md  textinput textInput form-control" id="collegeName" maxlength="100" minlength="6" placeholder="" style="margin-bottom: 10px" type="text" />
				        </div>
				        <div class="form-group">
				          <label>Board/University:</label>
				          <input class="input-md  textinput textInput form-control" id="boardName" maxlength="100" minlength="6" placeholder="" style="margin-bottom: 10px" type="text" />
				        </div>
				        <div class="form-group">
				          <label>Stream/Degree:</label>
				          <input class="input-md  textinput textInput form-control" id="streamName" maxlength="100" minlength="6" placeholder="" style="margin-bottom: 10px" type="text" />
				        </div>
				        <div class="form-group">
				          <label>Year:</label>
				          <input class="input-md  textinput textInput form-control" id="yearName" maxlength="4" minlength="4" placeholder="YYYY" style="margin-bottom: 10px" type="text" />
				        </div>
				        <div class="form-group">
				          <label>Score(%)/CGPA:</label>
				          <input class="input-md  textinput textInput form-control" id="scoreCGPA" maxlength="5" minlength="1" placeholder="" style="margin-bottom: 10px" type="text" />
				        </div>
				        </div>
				        <div class="modal-footer">
	    			      <button type="button" class="btn btn-success" id="saveNewEdu" onclick="insertNewEdu('${employeeBean.userName}');">Save</button>
				          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div>
				        </div>
				       </div>
				 </div>
				 <div class="modal fade" id="populateHRsModal" role="dialog">
				    <div class="modal-dialog modal-sm">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Update HR Manager</h4>
				        </div>
				        <div class="modal-body panel panel-inline" id="hrModalDiv">
				        </div>
				      </div>
				     </div>
				  </div>
				  <div class="modal fade" id="populateRMsModal" role="dialog">
				    <div class="modal-dialog modal-sm">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Update Reporting Manager</h4>
				        </div>
				        <div class="modal-body panel panel-inline" id="rmModalDiv">
				        </div>
				      </div>
				     </div>
				  </div>
				  <div class="modal fade" id="populatePGsModal" role="dialog">
				    <div class="modal-dialog modal-sm">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Update Employee's work information:</h4>
				        </div>
				        <div class="modal-body panel panel-inline" id="pgModalDiv">
				        </div>
				      </div>
				     </div>
				  </div>
				  <div id="editUserInfoDiv" style="display:none;width:auto;max-height:300px;z-index:100;overflow-y:auto;padding:5px;border:1px solid #eee;border-radius:5px;"></div>
				  <div id="editUserRMDiv" style="display:none;width:auto;max-height:300px;z-index:100;overflow-y:auto;padding:5px;border:1px solid #eee;border-radius:5px;"></div>
				  <div id="editUserHRDiv" style="display:none;width:auto;max-height:300px;z-index:100;overflow-y:auto;padding:5px;border:1px solid #eee;border-radius:5px;"></div>
<div id="loaderGif1" class="loader modal"></div>
<!-- <div id="loaderGif" class="loader" style="display:none;margin-left: 50%;margine-top:15%;z-index:1000;"></div> -->
<script>
function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}
$(document).ready(function() {
	//populateBaseLocations('baseLocation');
	//populateAccountUnit('accountUnit');
	//populateDepartment('department');
	//populateDesignation('designation');
	//populateRoles('role');
	//populateHRPerson('hrManager');
	//populateRMPerson('reportingPerson');
	//populateHRPolicyGroup('policyGroupName');
	updateSpouseNameField('${employeeBean.maritalStatus}');	
	$("#userDobId").datepicker({ 
		format: 'yyyy-mm-dd',
		orientation: "top"
	});
	$("#joiningDateId").datepicker({ 
		format: 'yyyy-mm-dd',
		orientation: "top"
	});
	$("#lastWorkingDateId").datepicker({ 
		format: 'yyyy-mm-dd',
		orientation: "top"
	});
	
    $("div.bhoechie-tab-menu>div.list-group>a").click(function(e) {
        e.preventDefault();
        $(this).siblings('a.active').removeClass("active");
        $(this).addClass("active");
        var index = $(this).index();
        $("div.bhoechie-tab>div.bhoechie-tab-content").removeClass("active");
        $("div.bhoechie-tab>div.bhoechie-tab-content").eq(index).addClass("active");
    });
    
   $('#submitEmpForm').click(function(e) {
    //data : JSON.stringify(formData),	
    var uid = '${employeeBean.userName}';
    var hrUser = document.getElementById("hrManager").value;
    var rmUser = document.getElementById("reportingPerson").value;
    console.log($('#empForm').serialize());
    if(validateFormFields() && validateHR(hrUser,uid) && validateRM(rmUser,uid)){
    var ret=confirm("Do you want to save details?");
    if (ret == true) {
		e.preventDefault();
		   /* var formData =  $("#empForm").serialize(); */
		    $.ajax({
	            type: 'POST',
	            url: "saveEmployeeData",
	            dataType : "json",
	            data : $('#empForm').serialize(),
	            beforeSend: function() { $('#loaderGif1').show(); },
	            complete: function() { $('#loaderGif1').hide(); },
		        success : function(res) {
						if(res){
							alert("User details updated successfully.");
							window.location.reload(true);
						}else{
							alert("Try after some time.");
							return false;
						}		        	 
		        	 }
		         });
		} else {
			return false;
		}
    }
    });
    
});
</script>
</body>
</html>
