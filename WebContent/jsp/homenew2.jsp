<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='<c:url value="/resources/images/favicon.ico" />' rel="shortcut icon">
<title>eManager SupraITS</title>
	<link href='<c:url value="/resources/css/bootstrap.min.css" />' rel="stylesheet">
	<link href="<c:url value="/resources/css/datepicker3.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">
    <!-- <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet"> -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">
	<link href="<c:url value="/resources/css/googleapi-fonts.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/calendarView.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/bootstrap-timepicker.css" />" rel="stylesheet">
	<%-- <link href="<c:url value="/resources/css/bootstrap-multiselect.css" />" rel="stylesheet"> --%>
	<%-- <link href="<c:url value="/resources/css/jquery.dropdown.min.css" />" rel="stylesheet"> --%>
	<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> -->
	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-table.js"/>"></script>
	<script src="<c:url value="/resources/js/moment.min.js"/>"></script>
	<script src="<c:url value="/resources/js/lumino.glyphs.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js" />"></script>
	<script src="<c:url value="/resources/js/modernizr.min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-ui.min.js" />"></script>
	<script src="<c:url value="/resources/js/draggable.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap-timepicker.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
	<link href="<c:url value="/resources/css/draggable.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/bootstrap-datetimepicker.min.css" />" rel="stylesheet">
	<script src="https://cdn.ckeditor.com/4.9.0/full-all/ckeditor.js"></script>
	<script src="<c:url value="/resources/js/chart.min.js"/>"></script>
	<script src="<c:url value="/resources/scripts/globalConstant.js"/>"></script>
	<script src="<c:url value="/resources/scripts/tooltipMessage.js"/>"></script>
	
	
	
		<spring:eval var="FILL_TIMESHEET" expression="@propertyConfigurer.getProperty('FILL_TIMESHEET')" />
		<spring:eval var="TIMESHEET_APPROVAL" expression="@propertyConfigurer.getProperty('TIMESHEET_APPROVAL')" />
		<spring:eval var="PROJECT_REPORT" expression="@propertyConfigurer.getProperty('PROJECT_REPORT')" />
		<spring:eval var="WEEKLY_REPORT" expression="@propertyConfigurer.getProperty('WEEKLY_REPORT')" />
		<spring:eval var="MONTHLY_REPORT" expression="@propertyConfigurer.getProperty('MONTHLY_REPORT')" />
		<spring:eval var="NEW_REQUEST" expression="@propertyConfigurer.getProperty('NEW_REQUEST')" />
		<spring:eval var="VIEW_REQUEST" expression="@propertyConfigurer.getProperty('VIEW_REQUEST')" />
		<spring:eval var="APPROVE_REQUEST" expression="@propertyConfigurer.getProperty('APPROVE_REQUEST')" />
		<spring:eval var="VIEW_BUCKET" expression="@propertyConfigurer.getProperty('VIEW_BUCKET')" />
		<spring:eval var="ADD_USER" expression="@propertyConfigurer.getProperty('ADD_USER')" />
		<spring:eval var="MODIFY_USER" expression="@propertyConfigurer.getProperty('MODIFY_USER')" />
		<spring:eval var="ADD_PROJECT" expression="@propertyConfigurer.getProperty('ADD_PROJECT')" />
		<spring:eval var="MODIFY_PROJECT" expression="@propertyConfigurer.getProperty('MODIFY_PROJECT')" />
		<spring:eval var="ASSIGN_PROJECT" expression="@propertyConfigurer.getProperty('ASSIGN_PROJECT')" />
		<spring:eval var="EMAIL_NOTIFICATION" expression="@propertyConfigurer.getProperty('EMAIL_NOTIFICATION')" />
		<spring:eval var="ACCESS_MANAGEMENT" expression="@propertyConfigurer.getProperty('ACCESS_MANAGEMENT')" />
		<spring:eval var="FEEDBACK" expression="@propertyConfigurer.getProperty('FEEDBACK')" />
		<spring:eval var="MANAGE_GROUP" expression="@propertyConfigurer.getProperty('MANAGE_GROUP')" />
		<spring:eval var="REIMBURSEMENT_REPORT" expression="@propertyConfigurer.getProperty('REIMBURSEMENT_REPORT')" />
		<spring:eval var="MANAGE_BUCKET" expression="@propertyConfigurer.getProperty('MANAGE_BUCKET')" />
		<spring:eval var="PROXY_LOGIN_ACCESS" expression="@propertyConfigurer.getProperty('PROXY_LOGIN_ACCESS')" />
		<spring:eval var="LEAVE_APPROVAL" expression="@propertyConfigurer.getProperty('LEAVE_APPROVAL')" />
		<spring:eval var="LEAVE_CONFIGURATION" expression="@propertyConfigurer.getProperty('LEAVE_CONFIGURATION')" />
		<spring:eval var="LEAVE_BALANCE_UPDATION" expression="@propertyConfigurer.getProperty('LEAVE_BALANCE_UPDATION')" />
		<spring:eval var="APPLY_LEAVE" expression="@propertyConfigurer.getProperty('APPLY_LEAVE')" />
		<spring:eval var="VIEW_OWN_LEAVE" expression="@propertyConfigurer.getProperty('VIEW_OWN_LEAVE')" />
		<spring:eval var="LEAVE_REPORT" expression="@propertyConfigurer.getProperty('LEAVE_REPORT')" />
		<spring:eval var="ADD_ASSET" expression="@propertyConfigurer.getProperty('ADD_ASSET')" />
		<spring:eval var="ALLOCATE_ASSET" expression="@propertyConfigurer.getProperty('ALLOCATE_ASSET')" />
		<spring:eval var="TRACK_ASSET" expression="@propertyConfigurer.getProperty('TRACK_ASSET')" />
		<spring:eval var="MODIFY_ASSET" expression="@propertyConfigurer.getProperty('MODIFY_ASSET')" />
		<spring:eval var="VIEW_ATTENDENCE" expression="@propertyConfigurer.getProperty('VIEW_ATTENDENCE')" />
		<spring:eval var="FILL_MOAF" expression="@propertyConfigurer.getProperty('FILL_MOAF')" />
		<spring:eval var="APPROVE_ATTENDENCE" expression="@propertyConfigurer.getProperty('APPROVE_ATTENDENCE')" />
		<spring:eval var="VIEW_REPORT" expression="@propertyConfigurer.getProperty('VIEW_REPORT')" />
		<spring:eval var="UPLOAD_ATTENDENCE" expression="@propertyConfigurer.getProperty('UPLOAD_ATTENDENCE')" />
		<spring:eval var="USER_ASSIGNED_TASK" expression="@propertyConfigurer.getProperty('USER_ASSIGNED_TASK')" />
		<spring:eval var="LEAVE_APPLY_FOR_OTHERS" expression="@propertyConfigurer.getProperty('LEAVE_APPLY_FOR_OTHERS')" />
		<spring:eval var="GENERATE_EMPLOYEE_DOC" expression="@propertyConfigurer.getProperty('GENERATE_EMPLOYEE_DOC')" />
		<spring:eval var="MANAGE_NOTIFICATION" expression="@propertyConfigurer.getProperty('MANAGE_NOTIFICATION')" />
		<spring:eval var="EMP_SIGNIN_SIGNOUT_REMOTELY" expression="@propertyConfigurer.getProperty('EMP_SIGNIN_SIGNOUT_REMOTELY')" />
		<spring:eval var="EMP_PUNCH_RECORD" expression="@propertyConfigurer.getProperty('EMP_PUNCH_RECORD')" />
		<spring:eval var="EMP_DIRECTORY" expression="@propertyConfigurer.getProperty('EMP_DIRECTORY')" />
		<script type="text/javascript">
	//Declaring global js varibale for loggedin user group
	var globalGroup = '${sessionScope.loggedinusergroup}';
	var globalloggedinuser = '${sessionScope.loggedInUser}';
	//Declare global count for proxy user.
	var globalProxyCount = '${sessionScope.globalProxyCount}';
	var clientIp1 = "0.0.0.0";
	function validateSession(){
		if(globalloggedinuser == ""){
			var url = "/TimesheetManagement/login";
			$(location).attr('href',url);
		}
	}
	var globalProxyLoginAccess = false;
</script>

<c:if test="${sessionScope.loggedInUserAccessMap[PROXY_LOGIN_ACCESS] eq true}">
	<script type="text/javascript">
		 globalProxyLoginAccess = true;
	</script>
</c:if>
<script src="<c:url value="/resources/scripts/dashboard.js"/>"></script>
<script src="<c:url value="/resources/scripts/userProfile.js"/>"></script>
<script src="<c:url value="/resources/scripts/generateReport.js"/>"></script>	
<script src="<c:url value="/resources/scripts/reimbursement.js"/>"></script>
<script src="<c:url value="/resources/scripts/leaveOperation.js"/>"></script>
<script src="<c:url value="/resources/scripts/assetAllocation.js"/>"></script>
<script src="<c:url value="/resources/scripts/attendance.js"/>"></script>
<script src="<c:url value="/resources/scripts/hrms.js"/>"></script>
<script src="<c:url value="/resources/scripts/payroll.js"/>"></script>
<style>
sup {
    font-size: .90em;
    line-height: 0.5em;
    vertical-align: baseline;
    position: relative;
    top: -0.4em;
    color:red;
    /* background-color:#30a5ff; */
    /* border-radius:30%; */
    padding:2px;
    font-weight:bold;
}
.bootstrap-datetimepicker-widget tr:hover {
    background-color: #fff;
}
.loader {
    border: 16px solid #f3f3f3; /* Light grey */
    border-top: 16px solid #3498db; /* Blue */
    border-radius: 50%;
    width: 60px;
    height: 60px;
    animation: spin 2s linear infinite;
    top:45%;
    left:50%;
}

@keyframes spin {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
}
.marquee {
    top: 0em;
    position: relative;
    box-sizing: border-box;
    animation: marquee 15s linear infinite;
}
.marquee:hover {
    animation-play-state: paused;
}

/* Make it move! */
@keyframes marquee {
    0%   { top:   8em }
    100% { top: -11em }
}
fixed-plugin {
    position: fixed;
    top: 120px;
    right: 0;
    width: 64px;
    background: rgba(0, 0, 0, .3);
    z-index: 1031;
    border-radius: 8px 0 0 8px;
    text-align: center;
}
 #sidebar-wrapper {
    margin-right: -250px;
    right: 0;
    width: 250px;
    background: #2c3e50;
    position: fixed;
    height: 100%;
    overflow-y: auto;
    overflow-x: hidden;
    z-index: 1000;
    transition: all 0.5s ease-in 0s;
    -webkit-transition: all 0.5s ease-in 0s;
    -moz-transition: all 0.5s ease-in 0s;
    -ms-transition: all 0.5s ease-in 0s;
    -o-transition: all 0.5s ease-in 0s;
  }

  .sidebar-nav {
    position: absolute;
    top: 0;
    width: 250px;
    list-style: none;
    margin: 0;
    padding: 0;
  }

  .sidebar-nav li {
    line-height: 30px;
    text-indent: 15px;
  }

  .sidebar-nav li a {
    color: #999999;
    display: block;
    text-decoration: none;
  }

  .sidebar-nav li a:hover {
    color: #fff;
    background: rgba(255,255,255,0.2);
    text-decoration: none;
  }

  .sidebar-nav li a:active, .sidebar-nav li a:focus {
    text-decoration: none;
  }

  .sidebar-nav > .sidebar-brand {
    height: 55px;
    line-height: 55px;
    font-size: 18px;
  }

  .sidebar-nav > .sidebar-brand a {
    color: #999999;
  }

  .sidebar-nav > .sidebar-brand a:hover {
    color: #fff;
    background: none;
  }
  #sidebar-wrapper.active {
    right: 250px;
    width: 250px;
    transition: all 0.5s ease-out 0s;
    -webkit-transition: all 0.5s ease-out 0s;
    -moz-transition: all 0.5s ease-out 0s;
    -ms-transition: all 0.5s ease-out 0s;
    -o-transition: all 0.5s ease-out 0s;
  }

  .toggle {
    margin: 5px 5px 0 0;
  }
.switch {
  position: relative;
  display: inline-block;
  width: 45px;
  height: 20px;
}

.switch input {display:none;}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: .4s;
  transition: .4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 15px;
  width: 13px;
  left: 3px;
  bottom: 3px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}

input:checked + .slider {
  background-color: #2196F3;
}

input:focus + .slider {
  box-shadow: 0 0 1px #2196F3;
}

input:checked + .slider:before {
  -webkit-transform: translateX(26px);
  -ms-transform: translateX(26px);
  transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
  border-radius: 24px;
}

.slider.round:before {
  border-radius: 50%;
}
#successFileUploadLeave {
    position:fixed;
    top: 50%;
    left: 50%;
    width:30em;
    margin-top: -9em; /*set to a negative number 1/2 of your height*/
    margin-left: -15em; /*set to a negative number 1/2 of your width*/
}
.popover{
    min-width:300px;
    max-width:600px;
}
.popover ul li{
    padding-bottom:3px;
}
</style>

</head>
<body oncontextmenu="return false;" onload="validateSession();return false;">
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<ul class="user-menu">
						<li><a href="#" id="menu-toggle" title="Display Setting"><svg class="glyph stroked gear" style="margin:0px;"><use xlink:href="#stroked-gear"></use></svg></a></li> 
				</ul>
				<ul class="user-menu">
						<li class="dropdown pull-right">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						${sessionScope.loggedInUserFullName}<span class="caret"></span> </a>
						<!-- <svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg> -->
						<ul class="dropdown-menu" role="menu">
						 		<li><a href="#!" onclick="editUserProfileOwn('${sessionScope.loggedInUser}');"><svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg> Edit Profile</a></li>
								<li><a href="#" onclick="changePwdModal();"><svg class="glyph stroked gear"><use xlink:href="#stroked-gear"></use></svg> Change Password</a></li>
								<!-- <li><a href="#!" onclick="editProfilePicOwn();"><svg class="glyph stroked female-user"><use xlink:href="#stroked-female-user"></use></svg> Upload Profile Image</a></li> --> 
								<li><a href="logoutUser"><svg class="glyph stroked cancel"><use xlink:href="#stroked-cancel"></use></svg> Logout</a></li>
						</ul>
						</li>					
				</ul>
				<a href="#l" onclick="editProfilePicOwn();" title="Update Image">
					<img src="<c:url value="${sessionScope.profilePicUrl}" />" width="50" height="50" class="img-responsive" alt="" style="border-radius:9999px !important;display:inline-block;float:right;max-height:55px;max-width:55px;">
				</a>	
				<ul class="pull-left">
				  <a href="homenew2">
					<img src='<c:url value="/resources/images/e-ims-supra.jpg" />' width="200px" height="40px" style="padding-top:3px;margin-left: -50px;margin-right: 5px;" />
				  </a>	
				</ul>
				<ul class="user-menu pull-left">
					<li class="dropdown">
					<a href="#!" class="btn btn-success dropdown-toggle" data-toggle="dropdown">Self Service<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu" style="margin-top:0px;">
								<c:if test="${sessionScope.loggedInUserAccessMap[FILL_TIMESHEET] eq true}">
									<li>
										<a href="#!" onclick="viewFillTimesheetDiv('${sessionScope.loggedInUser}');"><svg class="glyph stroked calendar"><use xlink:href="#stroked-calendar"></use></svg>
										Fill Timesheet</a>
									</li>
								</c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[FILL_MOAF] eq true}">
									<li>
										<a href="#l" onclick="fillMOAFDiv();"><svg class="glyph stroked calendar"><use xlink:href="#stroked-calendar"></use></svg>
										Fill MOAF</a>
									</li>
								 </c:if>
								 <c:if test="${sessionScope.loggedInUserAccessMap[NEW_REQUEST] eq true}">
									<li>
										<a href="#l" onclick="newReimbursementRequest();"><svg class="glyph stroked pen tip"><use xlink:href="#stroked-pen-tip"/></svg>
										New Reimbursement Req.</a>
									</li>
								</c:if>
						 		<c:if test="${sessionScope.loggedInUserAccessMap[APPLY_LEAVE] eq true}">
									<li>
										<a href="#l" onclick="newLeaveRequest();"><svg class="glyph stroked calendar"><use xlink:href="#stroked-calendar"/></svg>
											Apply Leave</a>
									</li>
								 </c:if>
								 <c:if test="${sessionScope.loggedInUserAccessMap[ALLOCATE_ASSET] eq true}">
									<li>
										<a href="#l" onclick="allocateAssetDiv();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
										Allocate Asset</a>
									</li>
				 				</c:if>
						</ul>
					</li>		
				</ul>
				<ul class="user-menu pull-left">
					<!-- <a href="homenew2"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg>Home</a> -->
					<c:if test="${sessionScope.reportingManager != ''}">
	    				<span style="color:white;font-size:15px;margin-right:10px;"><b style="color:#30a5ff;">RM</b>::${sessionScope.reportingManager}</span>
					</c:if>
					<c:if test="${sessionScope.hrManager != ''}">
	    				<span style="color:white;font-size:15px;margin-right:10px;"><b style="color:#30a5ff;">HR</b>::${sessionScope.hrManager}</span>
					</c:if>
					<c:if test="${sessionScope.loggedInUserProxy != ''}">
	    				<%-- <span style="color:white;font-size:15px;margin-right:10px;">(Login by::${sessionScope.loggedInUserFullNameProxy})</span> --%>
					</c:if>
				</ul>
				<c:if test="${sessionScope.loggedInUserProxy != ''}">
					<ul class="user-menu pull-right">
	    				<a href='backToMainLogin' class="btn btn-success backToMainLogin">Return to ${sessionScope.loggedInUserFullNameProxy}</a> 
					</ul>
				</c:if>		
			</div>
		</div>
	</nav>
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar" style="overflow-y: auto;">
		<ul class="nav menu">
		<li class="parent active">
				<a href="homenew2">
					<span><svg class="glyph stroked home active"><use xlink:href="#stroked-home"></use></svg>
					Home</span>
				</a>
		</li>		
		<c:if test="${sessionScope.loggedInUserAccessMap[FILL_TIMESHEET] eq true || sessionScope.loggedInUserAccessMap[TIMESHEET_APPROVAL] eq true || sessionScope.loggedInUserAccessMap[MONTHLY_REPORT] eq true || sessionScope.loggedInUserAccessMap[WEEKLY_REPORT] eq true}">
		<li class="parent">
				<a href="#">
					<span data-toggle="collapse" href="#sub-item-11"><svg class="glyph stroked chevron-down active"><use xlink:href="#stroked-chevron-down"></use></svg>
					Timesheet</span>
				</a>
				 <ul class="children collapse" id="sub-item-11">
				 <c:if test="${sessionScope.loggedInUserAccessMap[FILL_TIMESHEET] eq true}">
					<li>
						<a href="#!" onclick="viewFillTimesheetDiv('${sessionScope.loggedInUser}');"><svg class="glyph stroked calendar"><use xlink:href="#stroked-calendar"></use></svg>
						Fill Timesheet</a>
					</li>
				</c:if>	
					<%-- ${sessionScope.loggedInUserAccessMap[TIMESHEET_APPROVAL]} --%>
					<c:if test="${sessionScope.loggedInUserAccessMap[TIMESHEET_APPROVAL] eq true}">
						<li>
					   		<a href="#l" id="approveTimesheet" onclick="approveTimesheetFunc('Pending');return false;"><svg class="glyph stroked clock"><use xlink:href="#stroked-clock"></use></svg>
					   		TS Approval
					   	   <sup id="approvalPendingCount" ><strong>0</strong></sup>
					   		</a>
						</li>
					</c:if>
					
					
						<li>
					   		<a href="#">
								<span data-toggle="collapse" href="#sub-item-20"><svg class="glyph stroked chevron-down active">
								<use xlink:href="#stroked-chevron-down"></use></svg>
								Report</span>
							</a>
							 <ul class="children collapse" id="sub-item-20">
							 <c:if test="${sessionScope.loggedInUserAccessMap[MONTHLY_REPORT] eq true}">
								<li>
										<a href="#l" id="" onclick="getUserReportParameter();"><svg class="glyph stroked pencil"><use xlink:href="#stroked-pencil"></use></svg>Monthly Report</a>
								</li>
							</c:if>
							<c:if test="${sessionScope.loggedInUserAccessMap[WEEKLY_REPORT] eq true}">	
								<li>	
									<a href="#l" id="" onclick="getReportParameterWeekly();"><svg class="glyph stroked table"><use xlink:href="#stroked-table"></use></svg>Weekly Report</a>
								</li>
							</c:if>
							 </ul>
						</li>
				</ul>
		</li>
		</c:if>
		<c:if test="${sessionScope.loggedInUserAccessMap[ADD_USER] eq true || sessionScope.loggedInUserAccessMap[MODIFY_USER] eq true}">
		<li class="parent">
				<a href="#">
					<span data-toggle="collapse" href="#sub-item-2"><svg class="glyph stroked chevron-down"><use xlink:href="#stroked-chevron-down"></use></svg>
					User Management</span>
				</a>
				 <ul class="children collapse" id="sub-item-2">
				 <c:if test="${sessionScope.loggedInUserAccessMap[MODIFY_USER] eq true}">
					<li>
						<a href="#l" onclick="userMngtFunc('All');return false;"><svg class="glyph stroked male user"><use xlink:href="#stroked-female-user"></use></svg>
						Modify User</a>
					</li>
				</c:if>
				<c:if test="${sessionScope.loggedInUserAccessMap[ADD_USER] eq true}">	
					<li>
					   <a href="#l" onclick="addNewUser();"><svg class="glyph stroked female user"><use xlink:href="#stroked-male-user"></use></svg>
					   Add User</a>
					</li>
				</c:if>	
				</ul>
		</li>
		</c:if>
		<c:if test="${sessionScope.loggedInUserAccessMap[ADD_PROJECT] eq true || sessionScope.loggedInUserAccessMap[MODIFY_PROJECT] eq true || sessionScope.loggedInUserAccessMap[ASSIGN_PROJECT] eq true || sessionScope.loggedInUserAccessMap[USER_ASSIGNED_TASK] eq true}">
		<li class="parent ">
				<a href="#">
					<span data-toggle="collapse" href="#sub-item-4"><svg class="glyph stroked chevron-down active"><use xlink:href="#stroked-chevron-down"></use></svg>
					Project Management</span> 
				</a>
				 <ul class="children collapse" id="sub-item-4">
				 <c:if test="${sessionScope.loggedInUserAccessMap[ADD_PROJECT] eq true}">
					<li>
						<a href="#l" onclick="addNewProject();"><svg class="glyph stroked chain"><use xlink:href="#stroked-chain"></use></svg>
						Add Project</a>
					</li>
				</c:if>	
					<c:if test="${sessionScope.loggedInUserAccessMap[MODIFY_PROJECT] eq true}">
					<li>
							<a href="#l" onclick="editProject('Active');"><svg class="glyph stroked table"><use xlink:href="#stroked-table"></use></svg>
							Modify Project</a>
					</li>
					</c:if>
					<c:if test="${sessionScope.loggedInUserAccessMap[ASSIGN_PROJECT] eq true}">
					<li>
							<a href="#l" onclick="assignProject();"><svg class="glyph stroked pencil"><use xlink:href="#stroked-pencil"></use></svg>
							Assign Project</a>
					</li>
					</c:if>
					<c:if test="${sessionScope.loggedInUserAccessMap[USER_ASSIGNED_TASK] eq true}">
					<li>
							<a href="#l" onclick="viewUserAssignedTask();"><svg class="glyph stroked chain"><use xlink:href="#stroked-chain"></use></svg>
							Assigned Task</a>
					</li>
					</c:if>
					<c:if test="${sessionScope.loggedInUserAccessMap[PROJECT_REPORT] eq true}">
					<li>
					   		<a href="#">
								<span data-toggle="collapse" href="#sub-item-22"><svg class="glyph stroked chevron-down active">
								<use xlink:href="#stroked-chevron-down"></use></svg>
								Report</span>
							</a>
							 <ul class="children collapse" id="sub-item-22">
								<li>
									<a href="#l" id="" onclick="getReportParameter();"><svg class="glyph stroked table"><use xlink:href="#stroked-table"></use></svg>Project Report</a>
								</li>
							 </ul>
					</li>
					</c:if>
				</ul>
		</li>
		</c:if>
		<c:if test="${sessionScope.loggedInUserAccessMap[NEW_REQUEST] eq true || sessionScope.loggedInUserAccessMap[VIEW_REQUEST] eq true || sessionScope.loggedInUserAccessMap[APPROVE_REQUEST] eq true || sessionScope.loggedInUserAccessMap[VIEW_BUCKET] eq true || sessionScope.loggedInUserAccessMap[MANAGE_BUCKET] eq true || sessionScope.loggedInUserAccessMap[REIMBURSEMENT_REPORT] eq true}">
		<li class="parent ">
				<a href="#">
					<span data-toggle="collapse" href="#sub-item-6"><svg class="glyph stroked chevron-down active"><use xlink:href="#stroked-chevron-down"></use></svg>
					Reimbursement</span>
				</a>
				 <ul class="children collapse" id="sub-item-6">
				 <c:if test="${sessionScope.loggedInUserAccessMap[NEW_REQUEST] eq true}">
					<li>
						<a href="#l" onclick="newReimbursementRequest();"><svg class="glyph stroked pen tip"><use xlink:href="#stroked-pen-tip"/></svg>
						New Request</a>
					</li>
				</c:if>
				<c:if test="${sessionScope.loggedInUserAccessMap[VIEW_REQUEST] eq true}">	
					<li>
						<a href="#l" onclick="pastReimbursementList();"><svg class="glyph stroked app-window"><use xlink:href="#stroked-app-window"></use></svg>
						View Requests</a>
					</li>
				</c:if>	
					<c:if test="${sessionScope.loggedInUserAccessMap[APPROVE_REQUEST] eq true}">
					<li>
						<a href="#l" onclick="showPendingExpenseReq('Pending');"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-pencil"></use></svg>
						Pending Request
						<sup id="rmsPendingCount" ><strong>0</strong></sup> 
						</a>
					</li>
					</c:if>					
					<c:if test="${sessionScope.loggedInUserAccessMap[VIEW_BUCKET] eq true}">
				     <li>
						<a href="#l" onclick="checkReimburseBuckets();"><svg class="glyph stroked gear"><use xlink:href="#stroked-gear"></use></svg>
						View Buckets</a>
					</li>
					</c:if>
					<c:if test="${sessionScope.loggedInUserAccessMap[MANAGE_BUCKET] eq true}">
					 <li>
						<a href="#l" onclick="manageReimburseBuckets();"><svg class="glyph stroked eye"><use xlink:href="#stroked-eye"></use></svg>
						Manage Buckets</a>
					</li>
					</c:if>
					<c:if test="${sessionScope.loggedInUserAccessMap[REIMBURSEMENT_REPORT] eq true}">
						<li>
					   		<a href="#">
								<span data-toggle="collapse" href="#sub-item-21"><svg class="glyph stroked chevron-down active">
								<use xlink:href="#stroked-chevron-down"></use></svg>
								Report</span>
							</a>
							 <ul class="children collapse" id="sub-item-21">
								<li>	
									<a href="#l" id="" onclick="getReimbursementReoprtParam();"><svg class="glyph stroked table"><use xlink:href="#stroked-table"></use></svg>Reimbursement Report</a>
								</li>
							 </ul>
						</li>
					</c:if>	
				</ul>
		</li>
		</c:if>
		<c:if test="${sessionScope.loggedInUserAccessMap[APPLY_LEAVE] eq true || sessionScope.loggedInUserAccessMap[VIEW_OWN_LEAVE] eq true || sessionScope.loggedInUserAccessMap[LEAVE_APPROVAL] eq true || sessionScope.loggedInUserAccessMap[LEAVE_BALANCE_UPDATION] eq true || sessionScope.loggedInUserAccessMap[LEAVE_CONFIGURATION] eq true || sessionScope.loggedInUserAccessMap[LEAVE_REPORT] eq true}">
		 <li class="parent ">
				<a href="#">
					<span data-toggle="collapse" href="#sub-item-7"><svg class="glyph stroked chevron-down active"><use xlink:href="#stroked-chevron-down"></use></svg>
					Leave Management</span>
				</a>
				 <ul class="children collapse" id="sub-item-7">
				 <c:if test="${sessionScope.loggedInUserAccessMap[APPLY_LEAVE] eq true}">
					<li>
						<a href="#l" onclick="newLeaveRequest();"><svg class="glyph stroked pen tip"><use xlink:href="#stroked-pen-tip"/></svg>
							Apply Leave</a>
					</li>
				 </c:if>
				 <c:if test="${sessionScope.loggedInUserAccessMap[LEAVE_APPLY_FOR_OTHERS] eq true}">
					<li>
						<a href="#l" onclick="newLeaveRequestToOthers();"><svg class="glyph stroked pen tip"><use xlink:href="#stroked-pen-tip"/></svg>
							Apply for Team</a>
					</li>
				 </c:if>
				 <c:if test="${sessionScope.loggedInUserAccessMap[VIEW_OWN_LEAVE] eq true}">	
					<li>
						<a href="#l" onclick="pastLeaveList();"><svg class="glyph stroked app-window"><use xlink:href="#stroked-app-window"></use></svg>
						Track Leaves</a>
					</li>
				</c:if>	
					<c:if test="${sessionScope.loggedInUserAccessMap[LEAVE_APPROVAL] eq true}">
					<li>
						<a href="#l" onclick="pendingLeaveList('Approval Pending');"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-pencil"></use></svg>
						Pending Request
						<sup id="leavePendingCount" ><strong>0</strong></sup>
						</a>
					</li>
					</c:if>					
					<c:if test="${sessionScope.loggedInUserAccessMap[LEAVE_CONFIGURATION] eq true}">
					 <li>
						<a href="#l" onclick="manageLeaveType();"><svg class="glyph stroked basket "><use xlink:href="#stroked-basket"/></svg>
						Leave Category</a>
					</li>
					</c:if>
				<c:if test="${sessionScope.loggedInUserAccessMap[LEAVE_BALANCE_UPDATION] eq true}">
					 <li>
						<a href="#l" onclick="updateLeaveBalance('${sessionScope.loggedInUserPolicyGroup}');"><svg class="glyph stroked basket "><use xlink:href="#stroked-basket"/></svg>
						Quarterly Update</a>
					</li>
			<!-- <li class="parent">
				<a href="#">
					<span data-toggle="collapse" href="#sub-item-55"><svg class="glyph stroked chevron-down active"><use xlink:href="#stroked-chevron-down"></use></svg>
					Quarterly Update</span>
				</a>
				 <ul class="children collapse" id="sub-item-55">
					<li>
						<a href="#l" onclick="updateLeaveBalance('SUPRA-Noida');"><svg class="glyph stroked pen tip"><use xlink:href="#stroked-pen-tip"/></svg>
							SUPRA-Noida</a>
					</li>
					<li>
						<a href="#l" onclick="updateLeaveBalance('SUPRA-Canada');"><svg class="glyph stroked app-window"><use xlink:href="#stroked-app-window"></use></svg>
						SUPRA-Canada</a>
					</li>
				</ul>
			</li> -->
		</c:if>
					<c:if test="${sessionScope.loggedInUserAccessMap[LEAVE_REPORT] eq true}">
						 <li>
							<a href="#l" onclick="userLeaveReport();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-table"></use></svg>
							Leave Report</a>
						</li>
					</c:if>
				</ul>
		</li>
		</c:if>
		<c:if test="${sessionScope.loggedInUserAccessMap[ADD_ASSET] eq true || sessionScope.loggedInUserAccessMap[ALLOCATE_ASSET] eq true || sessionScope.loggedInUserAccessMap[TRACK_ASSET] eq true || sessionScope.loggedInUserAccessMap[MODIFY_ASSET] eq true}">
			<li class="parent">
				<a href="#">
					<span data-toggle="collapse" href="#sub-item-8"><svg class="glyph stroked chevron-down active"><use xlink:href="#stroked-chevron-down"></use></svg>
					Asset Managment</span>
				</a>
				 <ul class="children collapse" id="sub-item-8">
				 <c:if test="${sessionScope.loggedInUserAccessMap[ADD_ASSET] eq true}">
					<li>
						<a href="#l" onclick="newITRequest();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						New IT Request</a>
					</li>
				 </c:if>
				  <c:if test="${sessionScope.loggedInUserAccessMap[ADD_ASSET] eq true}">
					<li>
						<a href="#l" onclick="pendingITRequest();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						Pending IT Request</a>
					</li>
				 </c:if>
				 <c:if test="${sessionScope.loggedInUserAccessMap[ADD_ASSET] eq true}">
					<li>
						<a href="#l" onclick="addNewAsset();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						Add New Asset</a>
					</li>
				 </c:if>
				 <c:if test="${sessionScope.loggedInUserAccessMap[ADD_ASSET] eq true}">
					<li>
						<a href="#l" onclick="uploadBulkAsset();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						Bulk Upload</a>
					</li>
				 </c:if>
				 <c:if test="${sessionScope.loggedInUserAccessMap[ALLOCATE_ASSET] eq true}">
					<li>
						<a href="#l" onclick="allocateAssetDiv();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						Allocate Asset</a>
					</li>
				 </c:if>
				 <c:if test="${sessionScope.loggedInUserAccessMap[MODIFY_ASSET] eq true}">	
					<li>
						<a href="#l" onclick="modifyViewAsset();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-table"></use></svg>
						Manage Asset</a>
					</li>
				 </c:if>
				 <c:if test="${sessionScope.loggedInUserAccessMap[TRACK_ASSET] eq true}">	
					<li>
						<a href="#l" onclick="trackAsset();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-table"></use></svg>
						Track Asset Allocation</a>
					</li>
				 </c:if>
				 <c:if test="${sessionScope.loggedInUserAccessMap[TRACK_ASSET] eq true}">	
					<li>
						<a href="#l" onclick="viewAssetReport();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-table"></use></svg>
						View Report</a>
					</li>
				 </c:if>
				</ul>
		</li>
		</c:if>
		<c:if test="${sessionScope.loggedInUserAccessMap[VIEW_ATTENDENCE] eq true || sessionScope.loggedInUserAccessMap[FILL_MOAF] eq true || sessionScope.loggedInUserAccessMap[APPROVE_ATTENDENCE] eq true || sessionScope.loggedInUserAccessMap[VIEW_REPORT] eq true || sessionScope.loggedInUserAccessMap[UPLOAD_ATTENDENCE] eq true}">
			<li class="parent">
				<a href="#">
					<span data-toggle="collapse" href="#sub-item-9"><svg class="glyph stroked chevron-down active"><use xlink:href="#stroked-chevron-down"></use></svg>
					Attendance System</span>
				</a>
				 <ul class="children collapse" id="sub-item-9">
				 <c:if test="${sessionScope.loggedInUserAccessMap[VIEW_ATTENDENCE] eq true}">
					<li>
						<a href="#l" onclick="viewOwnAttendance();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						View Attendance</a>
					</li>
				 </c:if>
				 <c:if test="${sessionScope.loggedInUserAccessMap[FILL_MOAF] eq true}">
					<li>
						<a href="#l" onclick="fillMOAFDiv();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						Fill MOAF</a>
					</li>
				 </c:if>
				 <c:if test="${sessionScope.loggedInUserAccessMap[APPROVE_ATTENDENCE] eq true}">	
					<li>
						<a href="#l" onclick="approveMOAFDiv();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-table"></use></svg>
						Approve Attendance
						<sup id="moafPendingCount" ><strong>0</strong></sup>
						</a>
					</li>
				 </c:if>
				 <c:if test="${sessionScope.loggedInUserAccessMap[VIEW_REPORT] eq true}">	
					<li>
						<a href="#l" onclick="viewAttendanceData();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-table"></use></svg>
						Monthly Report</a>
					</li>
					<li>
						<a href="#l" onclick="viewWeeklyAttendanceData();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-table"></use></svg>
						Weekly Report</a>
					</li>
					<li>
						<a href="#l" onclick="viewYearlyAttendanceData();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-table"></use></svg>
						Yearly Report</a>
					</li>
				 </c:if>
				 <c:if test="${sessionScope.loggedInUserAccessMap[UPLOAD_ATTENDENCE] eq true}">	
					<li>
					 <a href="#l" onclick="uploadAttendanceData();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-table"></use></svg>
						Upload Attendance</a>
					</li>
				 </c:if>
				</ul>
		</li>
		</c:if>
		<c:if test="${sessionScope.loggedInUserAccessMap[GENERATE_EMPLOYEE_DOC] eq true || sessionScope.loggedInUserAccessMap[MANAGE_NOTIFICATION] eq true || sessionScope.loggedInUserAccessMap[EMP_SIGNIN_SIGNOUT_REMOTELY] eq true || sessionScope.loggedInUserAccessMap[EMP_PUNCH_RECORD] eq true}">
		<li class="parent ">
				<a href="#">
					<span data-toggle="collapse" href="#sub-item-10"><svg class="glyph stroked chevron-down active"><use xlink:href="#stroked-chevron-down"></use></svg>
					HRMS</span>
				</a>
				 <ul class="children collapse" id="sub-item-10">
				 <c:if test="${sessionScope.loggedInUserAccessMap[GENERATE_EMPLOYEE_DOC] eq true}">
					<!-- <li>
						<a href="#l" onclick="generateEmployeeDocuments();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						Generate Emp Doc</a>
					</li> -->
					<li>
						<a href="#l" onclick="createEmployeeDocRTE();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						Create Template</a>
					</li>
					<li>
						<a href="#l" onclick="generateEmployeeDocumentsRTE();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						Generate Document</a>
					</li>
					<li>
						<a href="#l" onclick="trackGeneratedEmpocumentsRTE();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						Track Document</a>
					</li>
				</c:if>
				<c:if test="${sessionScope.loggedInUserAccessMap[MANAGE_NOTIFICATION] eq true}">
					<li>
						<a href="#l" onclick="viewCompanyNotificationDiv();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						Announcement</a>
					</li>
				</c:if>
				<c:if test="${sessionScope.loggedInUserAccessMap[EMP_SIGNIN_SIGNOUT_REMOTELY] eq true}">	
					<li id="punchedInOutListId">
						<a href="#l" onclick="punchedTimeFromClientLoc();">
							<svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
							<span id="punchedInOutId">Sign In</span>
						</a>
					</li>
				 </c:if>
				 <c:if test="${sessionScope.loggedInUserAccessMap[EMP_PUNCH_RECORD] eq true}">	
					<li>
						<a href="#l" onclick="viewPunchedRecordFromClientLoc();">
							<svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
							<span>Punched Emp Record</span>
						</a>
					</li>
				 </c:if>
				</ul>
		</li>
		</c:if>
		<c:if test="${sessionScope.loggedInUserAccessMap[MANAGE_NOTIFICATION] eq true}">
		<li class="parent ">
				<a href="#">
					<span data-toggle="collapse" href="#sub-item-12"><svg class="glyph stroked chevron-down active"><use xlink:href="#stroked-chevron-down"></use></svg>
					e-Payroll</span>
				</a>
				 <ul class="children collapse" id="sub-item-12">
				 <c:if test="${sessionScope.loggedInUserAccessMap[MANAGE_NOTIFICATION] eq true}">
					<li>
						<a href="#l" onclick="viewUserPayslip();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						View Payslip</a>
					</li>
					<li>
						<a href="#l" onclick="userTDSDeclaration();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						TDS Declaration</a>
					</li>
					<li>
						<a href="#l" onclick="mapEmployeeToPayrollTemplateDiv();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						Emp-Payroll Mapping</a>
					</li>
					<li>
						<a href="#l" onclick="viewEmployeePayrollDiv();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						View Emp Payroll</a>
					</li>
					<li>
						<a href="#l" onclick="viewEmployeePendingTDSDiv();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						Pending TDS</a>
					</li>
				</c:if>
				<c:if test="${sessionScope.loggedInUserAccessMap[MANAGE_NOTIFICATION] eq true}">
					<li>
						<a href="#l" onclick="generateEmpsPayslipDiv();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						Generate Payslip</a>
					</li>
				</c:if>
				<c:if test="${sessionScope.loggedInUserAccessMap[MANAGE_NOTIFICATION] eq true}">	
					<li>
						<a href="#l" onclick="verifyEmpsPayslipDiv();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						Verify Payslip</a>
					</li>
				 </c:if>
				 <c:if test="${sessionScope.loggedInUserAccessMap[MANAGE_NOTIFICATION] eq true}">	
					<li>
						<a href="#l" onclick="viewUserPayrollSetupDiv();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						Payroll Setup</a>
					</li>
				 </c:if>
				 <c:if test="${sessionScope.loggedInUserAccessMap[MANAGE_NOTIFICATION] eq true}">	
					<li>
						<a href="#l" onclick="viewPayrollReportDiv();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						Report</a>
					</li>
				 </c:if>
				 <c:if test="${sessionScope.loggedInUserAccessMap[MANAGE_NOTIFICATION] eq true}">	
					<li>
						<a href="#l" onclick="createEmpLoanReqDiv();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						Create Loan Request</a>
					</li>
				 </c:if>
				</ul>
		</li>
		</c:if>
		<c:if test="${sessionScope.loggedInUserAccessMap[EMAIL_NOTIFICATION] eq true || sessionScope.loggedInUserAccessMap[ACCESS_MANAGEMENT] eq true || sessionScope.loggedInUserAccessMap[MANAGE_GROUP] eq true}">
		<li class="parent ">
				<a href="#">
					<span data-toggle="collapse" href="#sub-item-5"><svg class="glyph stroked chevron-down active"><use xlink:href="#stroked-chevron-down"></use></svg>
					Configuration</span>
				</a>
				 <ul class="children collapse" id="sub-item-5">
				 <c:if test="${sessionScope.loggedInUserAccessMap[EMAIL_NOTIFICATION] eq true}">
					<li>
						<a href="#l" onclick="emailNotificationDiv();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						Email Notification</a>
					</li>
				 </c:if>
				 <c:if test="${sessionScope.loggedInUserAccessMap[ACCESS_MANAGEMENT] eq true}">	
					<li>
						<a href="#l" onclick="userAccessManagementDiv();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-table"></use></svg>
						User Access</a>
					</li>
				 </c:if>
				 <c:if test="${sessionScope.loggedInUserAccessMap[MANAGE_GROUP] eq true}">	 
					<li>	
						<a href="#l" onclick="userGroupManagementDiv();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-female-user"></use></svg>
						Manage Group</a>
					</li>
				 </c:if>	
				</ul>
		</li>
		</c:if>
		<c:if test="${sessionScope.loggedInUserAccessMap[EMP_DIRECTORY] eq true}">
		<li class="parent">
			<a href="#l" onclick="viewEmpDirectory();"><svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg>
			Employee Directory</a>
		</li>
		</c:if>
		<c:if test="${sessionScope.loggedInUserAccessMap[FEEDBACK] eq true}">
		<li class="parent">
			<a href="#l" onclick="openFeedBackModal();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-pencil"></use></svg>
			FeedBack</a>
		</li>
		</c:if>
		</ul>
	<div class="attribution">Provided by<br/><a href="http://www.supraits.com" style="color: #fff;">Supra ITS, Noida</a></div>
	</div>
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main" id="dataDiv" style="overflow-x:hidden;">
		<div class="row" style="background:#ecf0f5;">
		<div class="col-lg-4 pull-right">			  
						  <div class="panel-group" id="accordion1">
						  <div class="panel panel-default template">
						    <div class="panel-heading panel-blue">
						      <h4 class="panel-title">
						        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
						          Announcement
						        </a>
						        <button style="border:none;background:#3e5871" class='glyphicon glyphicon-question-sign pull-right' onclick="setPopoverData('announancementTT','popover3');" tabindex="0" data-toggle="popover3" data-trigger="focus" data-placement="left" data-html="true"></button>
						      </h4>
						    </div>
						    <div id="collapseOne" class="panel-collapse collapse in">
						      <div class="panel-body" style="min-height:180px;max-height:180px;overflow:hidden;">
						        <ul class="marquee" id="compNotificationUL">
					        			<li>Change in leave policy for Supra-Noida<a href="#!" >&nbsp;Read More...</a></li>
										<li>Kindly submit timesheet of every week<a href="#!" >&nbsp;Read More...</a></li>
										<li>All employees depolyed on client location must have to submit mothly timesheet to HR before 25th of every month.<a href="#!" >&nbsp;Read More...</a></li>
										<li>Please find upcoming birthday list<a href="#!" >&nbsp;Read More...</a></li>
						        </ul>
						      </div>
						    </div>
						  </div>
						</div>
			</div>
		<div class="col-lg-4 pull-right">
						  <div class="panel panel-default template">
						    <div class="panel-heading panel-grey">
						      <h4 class="panel-title">
						        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
						          Quick Links
						        </a>
						        <button style="border:none;background:#3e5871" class='glyphicon glyphicon-question-sign pull-right' onclick="setPopoverData('quickLinksTT','popover2');" tabindex="0" data-toggle="popover2" data-trigger="focus" data-placement="bottom" data-html="true"></button>
						      </h4>
						    </div>
						    <div id="collapseTwo" class="panel-collapse collapse in">
						      <div class="panel-body" style="min-height:180px;max-height:180px;overflow: auto;">
						        <ul class="nav">
					            <c:if test="${sessionScope.loggedInUserAccessMap[FILL_TIMESHEET] eq true}"><li style="display:none;" id="FuncId11"><a href="#l" onclick="viewFillTimesheetDiv('${sessionScope.loggedInUser}');">Fill Timesheet</a></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[TIMESHEET_APPROVAL] eq true}"><li style="display:none;" id="FuncId12"><a href="#l" onclick="approveTimesheetFunc('Pending');return false;">Approve Timesheet</a></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[WEEKLY_REPORT] eq true}"><li style="display:none;" id="FuncId13"><a href="#l" onclick="getReportParameterWeekly();">Weekly TS Report</a></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[MONTHLY_REPORT] eq true}"><li style="display:none;" id="FuncId14"><a href="#l" onclick="getUserReportParameter();">Monthly TS Report</a></li></c:if>
							    <%-- <c:if test="${sessionScope.loggedInUserAccessMap[FILL_TIMESHEET] eq true}"><li style="display:none;" id="FuncId15"><a href="#l" onclick="">Apply Leave</a></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[TIMESHEET_APPROVAL] eq true}"><li style="display:none;" id="FuncId16"><a href="#l" onclick="">Approve Leave</a></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[WEEKLY_REPORT] eq true}"><li style="display:none;" id="FuncId17"><a href="#l" onclick="getReportParameterWeekly();">Weekly TS Report</a></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[MONTHLY_REPORT] eq true}"><li style="display:none;" id="FuncId18"><a href="#l" onclick="">Monthly TS Report</a></li></c:if> --%>
							    
							    <c:if test="${sessionScope.loggedInUserAccessMap[MODIFY_USER] eq true}"><li style="display:none;" id="FuncId19"><a href="#l" onclick="userMngtFunc('All');">Modify User</a></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[ADD_USER] eq true}"><li style="display:none;" id="FuncId20"><a href="#l" onclick="addNewUser();">Add New User</a></li></c:if>
								
								<c:if test="${sessionScope.loggedInUserAccessMap[ADD_PROJECT] eq true}"><li style="display:none;" id="FuncId21"><a href="#l" onclick="addNewProject();">Add New Project</a></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[MODIFY_PROJECT] eq true}"><li style="display:none;" id="FuncId22"><a href="#l" onclick="editProject('Active');">Modify Project</a></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[ASSIGN_PROJECT] eq true}"><li style="display:none;" id="FuncId23"><a href="#l" onclick="assignProject();">Assign Project</a></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[USER_ASSIGNED_TASK] eq true}"><li style="display:none;" id="FuncId24"><a href="#l" onclick="viewUserAssignedTask();">Assigned User's Task</a></li></c:if>
								    
							    <c:if test="${sessionScope.loggedInUserAccessMap[EMAIL_NOTIFICATION] eq true}"><li style="display:none;" id="FuncId25"><a href="#l" onclick="emailNotificationDiv();">Email Setting</a></li></c:if>    
								<c:if test="${sessionScope.loggedInUserAccessMap[ACCESS_MANAGEMENT] eq true}"><li style="display:none;" id="FuncId26"><a href="#l" onclick="userAccessManagementDiv();">User Access</a></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[MANAGE_GROUP] eq true}"><li style="display:none;" id="FuncId27"><a href="#l" onclick="userGroupManagementDiv();">Manage Group</a></li></c:if>
								
								<c:if test="${sessionScope.loggedInUserAccessMap[REIMBURSEMENT_REPORT] eq true}"><li style="display:none;" id="FuncId28"><a href="#l" onclick="getReimbursementReoprtParam();">Expense Report</a></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[MANAGE_BUCKET] eq true}"><li style="display:none;" id="FuncId29"><a href="#l" onclick="manageReimburseBuckets();">Expense Bucket</a></li></c:if>
								
								<c:if test="${sessionScope.loggedInUserAccessMap[APPLY_LEAVE] eq true}"><li style="display:none;" id="FuncId30"><a href="#l" onclick="newLeaveRequest();">Apply Leave</a></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[LEAVE_APPROVAL] eq true}"><li style="display:none;" id="FuncId31"><a href="#l" onclick="pendingLeaveList('Approval Pending');">Leave Approval</a></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[LEAVE_CONFIGURATION] eq true}"><li style="display:none;" id="FuncId32"><a href="#l" onclick="manageLeaveType();">Configure Leaves</a></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[LEAVE_BALANCE_UPDATION] eq true}">
									<li style="display:none;" id="FuncId33"><a href="#l" onclick="updateLeaveBalance('SUPRA-Canada');">Balance(Supra-Canada)</a></li>
									<li style="display:none;" id="FuncId34"><a href="#l" onclick="updateLeaveBalance('SUPRA-Noida');">Balance(Supra-Noida)</a></li>
								</c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[VIEW_OWN_LEAVE] eq true}"><li style="display:none;" id="FuncId35"><a href="#l" onclick="pastLeaveList();">Track Leave</a></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[LEAVE_REPORT] eq true}"><li style="display:none;" id="FuncId36"><a href="#l" onclick="">Leave Report</a></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[LEAVE_APPLY_FOR_OTHERS] eq true}"><li style="display:none;" id="FuncId37"><a href="#l" onclick="newLeaveRequestToOthers();">Leave Apply For Team</a></li></c:if>
								
								<c:if test="${sessionScope.loggedInUserAccessMap[ADD_ASSET] eq true}"><li style="display:none;" id="FuncId38"><a href="#l" onclick="addNewAsset();">Add Asset</a></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[ALLOCATE_ASSET] eq true}"><li style="display:none;" id="FuncId39"><a href="#l" onclick="allocateAssetDiv();">Allocate Asset</a></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[TRACK_ASSET] eq true}"><li style="display:none;" id="FuncId40"><a href="#l" onclick="trackAsset();">Track Asset</a></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[MODIFY_ASSET] eq true}"><li style="display:none;" id="FuncId41"><a href="#l" onclick="modifyViewAsset();">Modify Asset</a></li></c:if>
								
								<c:if test="${sessionScope.loggedInUserAccessMap[VIEW_ATTENDENCE] eq true}"><li style="display:none;" id="FuncId42"><a href="#l" onclick="viewOwnAttendance();">View Attendance</a></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[FILL_MOAF] eq true}"><li style="display:none;" id="FuncId43"><a href="#l" onclick="fillMOAFDiv();">Fill MOAF</a></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[APPROVE_ATTENDENCE] eq true}"><li style="display:none;" id="FuncId44"><a href="#l" onclick="approveMOAFDiv();">Approve Attendance</a></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[VIEW_REPORT] eq true}">
									<li style="display:none;" id="FuncId45"><a href="#l" onclick="viewWeeklyAttendanceData();">Weekly Attendance Report</a></li>
									<li style="display:none;" id="FuncId46"><a href="#l" onclick="viewAttendanceData();">Monthly Attendance Report</a></li>
									<li style="display:none;" id="FuncId47"><a href="#l" onclick="viewYearlyAttendanceData();">Yearly Attendance Report</a></li>
								</c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[UPLOAD_ATTENDENCE] eq true}"><li style="display:none;" id="FuncId48"><a href="#l" onclick="uploadAttendanceData();">Upload Attendance</a></li></c:if>
								
								<c:if test="${sessionScope.loggedInUserAccessMap[GENERATE_EMPLOYEE_DOC] eq true}"><li style="display:none;" id="FuncId49"><a href="#l" onclick="generateEmployeeDocuments();">Generate Emp Doc</a></li></c:if>
					          </ul>
						      </div>
						    </div>
						  </div>
			</div>			  
			<c:if test="${sessionScope.loggedInUserAccessMap[MODIFY_USER] eq true}">
			<div class="col-lg-4" id="PanelId1" style="display:none;">
			<div class="panel panel-default template">
						    <div class="panel-heading panel-grey">
						      <h4 class="panel-title">
						        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
						          Employee Management::${sessionScope.loggedInUserPolicyGroup}
						        </a>
						      </h4>
						    </div>
						    <div id="collapseThree" class="panel-collapse collapse in">
						      <div class="panel-body">
						        <ul class="nav nav-stacked">
					                <li><a href="#l" onclick="userMngtFunc('Active');return false;">Active Users<span class="pull-right badge bg-blue" id="activeUserCount" style="background-color:red;">0</span></a></li>
					                <li><a href="#l" onclick="userMngtFunc('Disable');return false;">Disable<span class="pull-right badge bg-aqua" id="disableUserCount">0</span></a></li>
					                <li><a href="#l" onclick="userMngtFunc('Deleted');return false;">Deleted<span class="pull-right badge bg-green" id="deletedUserCount">0</span></a></li>
					                <li><a href="#l" onclick="userMngtFunc('All');return false;">Total<span class="pull-right badge bg-red" id="allUserCount">0</span></a></li>
					              </ul>
						      </div>
						    </div>
			</div>
			</div>
			</c:if>
			<c:if test="${sessionScope.loggedInUserAccessMap[ADD_PROJECT] eq true || sessionScope.loggedInUserAccessMap[MODIFY_PROJECT] eq true || sessionScope.loggedInUserAccessMap[ASSIGN_PROJECT] eq true}">
			<div class="col-lg-4" id="PanelId2" style="display:none;">
			<div class="panel panel-default template">
						    <div class="panel-heading panel-grey">
						      <h4 class="panel-title">
						        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
						          Project Management::${sessionScope.loggedInUserPolicyGroup}
						        </a>
						      </h4>
						    </div>
						    <div id="collapseFour" class="panel-collapse collapse in">
						      <div class="panel-body">
						        <ul class="nav nav-stacked">
					                <li><a href="#l" onclick="editProject('Active');">Active Project<span class="pull-right badge bg-blue" id="activeCount" style="background-color:red;">0</span></a></li>
					                <li><a href="#l" onclick="editProject('Completed');">Completed<span class="pull-right badge bg-aqua" id="completedCount">0</span></a></li>
					                <li><a href="#l" onclick="editProject('Inactive');">Inactive<span class="pull-right badge bg-green" id="inactiveCount">0</span></a></li>
					                <li><a href="#l" onclick="editProject('Not Started');">Yet to start<span class="pull-right badge bg-red" id="notStartedCount">0</span></a></li>
					              </ul>
						      </div>
						    </div>
			</div>
			</div>
			</c:if>
			
			<c:if test="${sessionScope.loggedInUserAccessMap[APPLY_LEAVE] eq true || sessionScope.loggedInUserAccessMap[VIEW_OWN_LEAVE] eq true || sessionScope.loggedInUserAccessMap[LEAVE_APPROVAL] eq true || sessionScope.loggedInUserAccessMap[LEAVE_BALANCE_UPDATION] eq true || sessionScope.loggedInUserAccessMap[LEAVE_CONFIGURATION] eq true || sessionScope.loggedInUserAccessMap[LEAVE_REPORT] eq true}">
			<div class="col-lg-4" id="PanelId3" style="display:none;">
			<div class="panel panel-default template">
						    <div class="panel-heading panel-grey">
						      <h4 class="panel-title">
						        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseFive">
						          Leave Management(Self)
						        </a>
						      </h4>
						    </div>
						    <div id="collapseFive" class="panel-collapse collapse in">
						      <div class="panel-body">
						        <ul class="nav nav-stacked">
					                <li><a href="#l" onclick="pastLeaveList();return false;">Pending<span class="pull-right badge bg-blue" id="pendingSelfLeaveCount" style="background-color:red;">0</span></a></li>
					                <li><a href="#l" onclick="pastLeaveList();return false;">Approved<span class="pull-right badge bg-aqua" id="approvedSelfLeaveCount">0</span></a></li>
					                <li><a href="#l" onclick="pastLeaveList();return false;">Rejected<span class="pull-right badge bg-green" id="rejectedSelfLeaveCount">0</span></a></li>
					                <li><a href="#l" onclick="pastLeaveList();return false;">Cancelled/Reversal<span class="pull-right badge bg-red" id="deletedSelfLeaveCount">0</span></a></li>
					              </ul>
						      </div>
						    </div>
			</div>
			</div>
			</c:if>
			
			<c:if test="${sessionScope.loggedInUserAccessMap[APPLY_LEAVE] eq true || sessionScope.loggedInUserAccessMap[VIEW_OWN_LEAVE] eq true || sessionScope.loggedInUserAccessMap[LEAVE_APPROVAL] eq true || sessionScope.loggedInUserAccessMap[LEAVE_BALANCE_UPDATION] eq true || sessionScope.loggedInUserAccessMap[LEAVE_CONFIGURATION] eq true || sessionScope.loggedInUserAccessMap[LEAVE_REPORT] eq true}">
			<div class="col-lg-4" id="PanelId4" style="display:none;">
			<div class="panel panel-default template">
						    <div class="panel-heading panel-grey">
						      <h4 class="panel-title">
						        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseSix">
						          Leave Management(Others)
						        </a>
						      </h4>
						    </div>
						    <div id="collapseSix" class="panel-collapse collapse in">
						      <div class="panel-body">
						        <ul class="nav nav-stacked">
					                <li><a href="#l" onclick="pendingLeaveList('Approval Pending');return false;">Pending<span class="pull-right badge bg-blue" id="pendingOtherLeaveCount" style="background-color:red;">0</span></a></li>
					                <li><a href="#l" onclick="pendingLeaveList('Approved');return false;">Approved<span class="pull-right badge bg-aqua" id="approvedOtherLeaveCount">0</span></a></li>
					                <li><a href="#l" onclick="pendingLeaveList('Rejected');return false;">Rejected<span class="pull-right badge bg-green" id="rejectedOtherLeaveCount">0</span></a></li>
					                <li><a href="#l" onclick="pendingLeaveList('Approval Pending');return false;">Click to view pending leaves...</a></li>
					              </ul>
						      </div>
						    </div>
			</div>
			</div>
			</c:if>
			<c:if test="${sessionScope.loggedInUserAccessMap[FILL_TIMESHEET] eq true}">
			<div class="col-lg-4" id="PanelId5" style="display:none;">
			<div class="panel panel-default template">
						    <div class="panel-heading panel-grey">
						      <h4 class="panel-title">
						        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseSeven">
						          Timesheet Management(Self)
						        </a>
						      </h4>
						    </div>
						    <div id="collapseSeven" class="panel-collapse collapse in">
						      <div class="panel-body">
						        <ul class="nav nav-stacked">
					                <li><a href="#l">Approval Pending<span class="pull-right badge bg-red" id="pendingSelfTSCount" style="background-color:red;">0</span></a></li>
					                <li><a href="#l">Approved<span class="pull-right badge bg-aqua" id="approvedSelfTSCount">0</span></a></li>
					                <li><a href="#l">Rejected<span class="pull-right badge bg-green" id="rejectedSelfTSCount">0</span></a></li>
					                <li><a href="#l" onclick="showUserTimesheetHistory('${sessionScope.loggedInUser}');">Timesheet History...</a></li>
					              </ul>
						      </div>
						    </div>
			</div>
			</div>
			</c:if>
			<c:if test="${sessionScope.loggedInUserAccessMap[TIMESHEET_APPROVAL] eq true || sessionScope.loggedInUserAccessMap[MONTHLY_REPORT] eq true || sessionScope.loggedInUserAccessMap[WEEKLY_REPORT] eq true}">
			<div class="col-lg-4" id="PanelId6" style="display:none;">
			<div class="panel panel-default template">
						    <div class="panel-heading panel-grey">
						      <h4 class="panel-title">
						        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseEight">
						          Timesheet Management(Others)
						        </a>
						      </h4>
						    </div>
						    <div id="collapseEight" class="panel-collapse collapse in">
						      <div class="panel-body">
						        <ul class="nav nav-stacked">
					                <li><a href="#l" onclick="approveTimesheetFunc('Pending');return false;">Your Approval Pending<span class="pull-right badge bg-blue" id="pendingOtherTSCount" style="background-color:red;">0</span></a></li>
					                <li><a href="#l" onclick="approveTimesheetFunc('Approved');return false;">Approved<span class="pull-right badge bg-aqua" id="approvedOtherTSCount">0</span></a></li>
					                <li><a href="#l" onclick="approveTimesheetFunc('Rejected');return false;">Rejected<span class="pull-right badge bg-green" id="rejectedOtherTSCount">0</span></a></li>
					                <li><a href="#l" onclick="approveTimesheetFunc('Pending');return false;">Click to view pending pending TS...</a></li>
					              </ul>
						      </div>
						    </div>
			</div>
			</div>
			</c:if>
			<c:if test="${sessionScope.loggedInUserAccessMap[NEW_REQUEST] eq true}">
			<div class="col-lg-4" id="PanelId7" style="display:none;">
			<div class="panel panel-default template">
						    <div class="panel-heading panel-grey">
						      <h4 class="panel-title">
						        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseNine">
						          Reimbursement(Self)
						        </a>
						      </h4>
						    </div>
						    <div id="collapseNine" class="panel-collapse collapse in">
						      <div class="panel-body">
						        <ul class="nav nav-stacked">
					                <li><a href="#l" onclick="pastReimbursementList();return false;">In process<span class="pull-right badge bg-blue" id="pendingSelfExpCount" style="background-color:red;">0</span></a></li>
					                <li><a href="#l" onclick="pastReimbursementList();return false;">Approved<span class="pull-right badge bg-aqua" id="approvedSelfExpCount">0</span></a></li>
					                <li><a href="#l" onclick="pastReimbursementList();return false;">Rejected<span class="pull-right badge bg-green" id="rejectedSelfExpCount">0</span></a></li>
					                <li><a href="#l" onclick="newReimbursementRequest();">Create New Request...</a></li>
					              </ul>
						      </div>
						    </div>
			</div>
			</div>
			</c:if>
			<c:if test="${sessionScope.loggedInUserAccessMap[APPROVE_REQUEST] eq true || sessionScope.loggedInUserAccessMap[VIEW_BUCKET] eq true || sessionScope.loggedInUserAccessMap[MANAGE_BUCKET] eq true || sessionScope.loggedInUserAccessMap[REIMBURSEMENT_REPORT] eq true}">
			<div class="col-lg-4" id="PanelId8" style="display:none;">
			<div class="panel panel-default template">
						    <div class="panel-heading panel-grey">
						      <h4 class="panel-title">
						        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseTen">
						          Expense Management(Others)
						        </a>
						      </h4>
						    </div>
						    <div id="collapseTen" class="panel-collapse collapse in">
						      <div class="panel-body">
						        <ul class="nav nav-stacked">
					                <li><a href="#l" onclick="showPendingExpenseReq('Pending');return false;">Approval Pending<span class="pull-right badge bg-blue" id="pendingOtherExpCount" style="background-color:red;">0</span></a></li>
					                <li><a href="#" onclick="showPendingExpenseReq('Approved');return false;">Approved By You<span class="pull-right badge bg-aqua" id="approvedOtherExpCount">0</span></a></li>
					                <li><a href="#" onclick="showPendingExpenseReq('Rejected');return false;">Rejected By You<span class="pull-right badge bg-green" id="rejectedOtherExpCount">0</span></a></li>
					                <li><a href="#" onclick="showPendingExpenseReq('Pending');return false;">View All Pending Request</a></li>
					              </ul>
						      </div>
						    </div>
			</div>
			</div>
			</c:if>
			<c:if test="${sessionScope.loggedInUserAccessMap[VIEW_ATTENDENCE] eq true || sessionScope.loggedInUserAccessMap[FILL_MOAF] eq true}">
			<div class="col-lg-4" id="PanelId9" style="display:none;">
			<div class="panel panel-default template">
						    <div class="panel-heading panel-grey">
						      <h4 class="panel-title">
						        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseEleven">
						          Attendance System(Self)
						        </a>
						      </h4>
						    </div>
						    <div id="collapseEleven" class="panel-collapse collapse in">
						      <div class="panel-body">
						        <ul class="nav nav-stacked">
					                <li><a href="#l" onclick="fillMOAFDiv();">Pending MOAF<span class="pull-right badge bg-blue" id="pendingSelfMOAFCount" style="background-color:red;">0</span></a></li>
					                <li><a href="#l" onclick="fillMOAFDiv();">Approved<span class="pull-right badge bg-aqua" id="approvedSelfMOAFCount">0</span></a></li>
					                <li><a href="#l" onclick="fillMOAFDiv();">Rejected<span class="pull-right badge bg-green" id="rejectedSelfMOAFCount">0</span></a></li>
					                <li><a href="#l" onclick="fillMOAFDiv();">Create New MOAF Request...</a></li>
					              </ul>
						      </div>
						    </div>
			</div>
			</div>
			</c:if>
			<c:if test="${sessionScope.loggedInUserAccessMap[APPROVE_ATTENDENCE] eq true || sessionScope.loggedInUserAccessMap[VIEW_REPORT] eq true || sessionScope.loggedInUserAccessMap[UPLOAD_ATTENDENCE] eq true}">
			<div class="col-lg-4" id="PanelId10" style="display:none;">
			<div class="panel panel-default template">
						    <div class="panel-heading panel-grey">
						      <h4 class="panel-title">
						        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseTwelve">
						          Attendance System(Others)
						        </a>
						      </h4>
						    </div>
						    <div id="collapseTwelve" class="panel-collapse collapse in">
						      <div class="panel-body">
						        <ul class="nav nav-stacked">
					                <li><a href="#" onclick="approveMOAFDiv();return false;">Pending<span class="pull-right badge bg-blue" id="pendingOtherMOAFCount" style="background-color:red;">0</span></a></li>
					                <li><a href="#" onclick="approveMOAFDiv();return false;">Approved<span class="pull-right badge bg-aqua" id="approvedOtherMOAFCount">0</span></a></li>
					                <li><a href="#" onclick="approveMOAFDiv();return false;">Rejected<span class="pull-right badge bg-green" id="rejectedOtherMOAFCount">0</span></a></li>
					                <li><a href="#" onclick="approveMOAFDiv();return false;">View Pending MOAF<span class="pull-right badge bg-green">0</span></a></li>
					              </ul>
						      </div>
						    </div>
			</div>
			</div>
			</c:if>
		</div>
		
	</div>
	<div id="sidebar-wrapper">
						    <ul class="sidebar-nav" style="padding-bottom:70px;">
							    <a id="menu-close" href="#!" title="Hide Display Settings..." class="btn btn-default btn-sm pull-right" style="margin-top:15px;margin-right:20px;border-radius:30px;"><i class="glyphicon glyphicon-remove"></i></a>
							    <li class="sidebar-brand"><span style="color:#fff;">Display Settings</span></li>
							    <c:if test="${sessionScope.loggedInUserAccessMap[MODIFY_USER] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkPanelId1" onchange="updateDisplayAccess(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Employee Management</span></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[MODIFY_PROJECT] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkPanelId2" onchange="updateDisplayAccess(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Project Management</span></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[APPLY_LEAVE] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkPanelId3" onchange="updateDisplayAccess(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Leave Management(Self)</span></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[LEAVE_APPROVAL] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkPanelId4" onchange="updateDisplayAccess(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Leave Management(Others)</span></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[FILL_TIMESHEET] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkPanelId5" onchange="updateDisplayAccess(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Timesheet (Self)</span></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[TIMESHEET_APPROVAL] eq true || sessionScope.loggedInUserAccessMap[WEEKLY_REPORT] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkPanelId6" onchange="updateDisplayAccess(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Timesheet(Others)</span></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[NEW_REQUEST] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkPanelId7" onchange="updateDisplayAccess(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Reimbursement (Self)</span></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[APPROVE_REQUEST] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkPanelId8" onchange="updateDisplayAccess(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Reimbursement (Others)</span></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[FILL_MOAF] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkPanelId9" onchange="updateDisplayAccess(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Attendance (Self)</span></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[APPROVE_ATTENDENCE] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkPanelId10" onchange="updateDisplayAccess(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Attendance ()Others)</span></li></c:if>
							    
							    <li class="sidebar-brand"><span style="color:#fff;">Quick Link Setting</span></li>
							    <c:if test="${sessionScope.loggedInUserAccessMap[FILL_TIMESHEET] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId11" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Fill Timesheet</span></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[TIMESHEET_APPROVAL] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId12" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Approve Timesheet</span></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[WEEKLY_REPORT] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId13" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Weekly TS Report</span></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[MONTHLY_REPORT] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId14" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Monthly TS Report</span></li></c:if>
							    <%-- <c:if test="${sessionScope.loggedInUserAccessMap[FILL_TIMESHEET] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId15" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Apply Leave</span></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[TIMESHEET_APPROVAL] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId16" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Approve Leave</span></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[WEEKLY_REPORT] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId17" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Weekly TS Report</span></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[MONTHLY_REPORT] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId18" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Monthly TS Report</span></li></c:if> --%>
							    
							    <c:if test="${sessionScope.loggedInUserAccessMap[MODIFY_USER] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId19" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Modify User</span></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[ADD_USER] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId20" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Add New User</span></li></c:if>
								
								<c:if test="${sessionScope.loggedInUserAccessMap[ADD_PROJECT] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId21" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Add New Project</span></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[MODIFY_PROJECT] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId22" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Modify Project</span></li></c:if>
							    <c:if test="${sessionScope.loggedInUserAccessMap[ASSIGN_PROJECT] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId23" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Assign Project</span></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[USER_ASSIGNED_TASK] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId24" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Assigned User's Task</span></li></c:if>
								    
							    <c:if test="${sessionScope.loggedInUserAccessMap[EMAIL_NOTIFICATION] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId25" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Email Setting</span></li></c:if>    
								<c:if test="${sessionScope.loggedInUserAccessMap[ACCESS_MANAGEMENT] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId26" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">User Access</span></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[MANAGE_GROUP] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId27" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Manage Group</span></li></c:if>
								
								<c:if test="${sessionScope.loggedInUserAccessMap[REIMBURSEMENT_REPORT] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId28" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Expense Report</span></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[MANAGE_BUCKET] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId29" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Expense Bucket</span></li></c:if>
								
								<c:if test="${sessionScope.loggedInUserAccessMap[APPLY_LEAVE] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId30" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Apply Leave</span></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[LEAVE_APPROVAL] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId31" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Leave Approval</span></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[LEAVE_CONFIGURATION] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId32" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Configure Leaves</span></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[LEAVE_BALANCE_UPDATION] eq true}">
									<li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId33" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Balance(Supra-Canada)</span></li>
									<li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId34" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Balance(Supra-Noida)</span></li>
								</c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[VIEW_OWN_LEAVE] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId35" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Track Leave</span></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[LEAVE_REPORT] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId36" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Leave Report</span></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[LEAVE_APPLY_FOR_OTHERS] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId37" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Leave Apply For Team</span></li></c:if>
								
								<c:if test="${sessionScope.loggedInUserAccessMap[ADD_ASSET] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId38" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Add Asset</span></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[ALLOCATE_ASSET] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId39" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Allocate Asset</span></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[TRACK_ASSET] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId40" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Track Asset</span></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[MODIFY_ASSET] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId41" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Modify Asset</span></li></c:if>
								
								<c:if test="${sessionScope.loggedInUserAccessMap[VIEW_ATTENDENCE] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId42" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">View Attendance</span></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[FILL_MOAF] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId43" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Fill MOAF</span></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[APPROVE_ATTENDENCE] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId44" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Approve Attendance</span></li></c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[VIEW_REPORT] eq true}">
									<li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId45" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Weekly Attendance Report</span></li>
									<li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId46" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Monthly Attendance Report</span></li>
									<li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId47" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Yearly Attendance Report</span></li>
								</c:if>
								<c:if test="${sessionScope.loggedInUserAccessMap[UPLOAD_ATTENDENCE] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId48" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Upload Attendance</span></li></c:if>
								
								<c:if test="${sessionScope.loggedInUserAccessMap[GENERATE_EMPLOYEE_DOC] eq true}"><li><label class='switch' style="margin-bottom:-5px;margin-right:3px;"><input type="checkbox" id="chkFuncId49" onchange="updateQuickLinkStatus(this.id);"><span class="slider round"></span></label><span style="color:#fff;">Generate Emp Doc</span></li></c:if>    
						    </ul>
						</div>
						<div class="alert alert-success" id="success-alert" style="position:absolute;z-index:10000;top:30%;left:45%;">
						    <button type="button" class="close" data-dismiss="alert">x</button>
						    <strong id="alertMessageheadId"></strong>
						    <span id="alertMessageId"></span>
						</div>
						
						
<c:if test="${sessionScope.loggedInUserAccessMap[MODIFY_USER] eq true}">
	<script>fetchUserCountInSystem();</script>
</c:if>
<c:if test="${sessionScope.loggedInUserAccessMap[MODIFY_PROJECT] eq true}">
	<script>fetchProjectCountInSystem();</script>
</c:if>
<c:if test="${sessionScope.loggedInUserAccessMap[GENERATE_EMPLOYEE_DOC] eq true}">
	<script>checkCurrentDayPunchStatus();</script>
</c:if>
<script>
$(document).ready(function(){
	$("#success-alert").hide();
	fetchNotifications();  
	fetchUserOwnItemCount();
	fetchOtherUseresItemCount();
	fetchUserDisplayDivAndQuickLinks();
	/* getApprovalPendingCount(); */
	$.getJSON("https://api.ipify.org/?format=json", function(e) { clientIp1 = e.ip;console.log(clientIp1); });
	//Right collapsable panel for Display Setting
	  $("#menu-close").click(function(e) {
	    e.preventDefault();
	    $("#sidebar-wrapper").toggleClass("active");
	  });
	  $("#menu-toggle").click(function(e) {
	    e.preventDefault();
	    $("#sidebar-wrapper").toggleClass("active");
	  });
	  $("#allocationStartDateForAsset").datetimepicker({ 
			format: 'YYYY-MM-DD',
			defaultDate: new Date(),
			widgetPositioning:{
	            horizontal: 'auto',
	            vertical: 'bottom'
	        }
	    });
	  $("#allocationEndDateForAsset").datetimepicker({ 
			format: 'YYYY-MM-DD',
			defaultDate: new Date(),
			widgetPositioning:{
	            horizontal: 'auto',
	            vertical: 'bottom'
	        }
	    });
	  	var datefrst = new Date();y = datefrst.getFullYear();m = datefrst.getMonth();
	    var firstDay1 = new Date(y, m, 1);
	    $("#allocationDateForAsset").on('dp.change', function (e) {
	        var fromdate = $(this).val();
	    });
	    var newDate = new Date();
	    var fDate = new Date(newDate.getFullYear(), newDate.getMonth(), 1);
	    $("#from-datepicker-attendance").datetimepicker({ 
			format: 'YYYY-MM-DD',
			maxDate: new Date(),
			defaultDate: fDate 
	    });
	    $("#from-datepicker-attendance").on('dp.change', function (e) {
	        var fromdate = $(this).val();
	    });
	    $("#to-datepicker-attendance").datetimepicker({ 
			format: 'YYYY-MM-DD',
			maxDate: new Date(),
			defaultDate: new Date()
	    });
	    $("#to-datepicker-attendance").on('dp.change', function (e) {
	        var fromdate = $(this).val();
	    });
	    $("#from-datepicker-punchLoc").datetimepicker({ 
			format: 'YYYY-MM-DD',
			maxDate: new Date(),
			defaultDate: fDate 
	    });
	    /* $("#from-datepicker-punchLoc").on('dp.change', function (e) {
	        var fromdate = $(this).val();
	    }); */
	    $("#to-datepicker-punchLoc").datetimepicker({ 
			format: 'YYYY-MM-DD',
			maxDate: new Date(),
			defaultDate: new Date()
	    });
		$("#from-datepicker-expense").datetimepicker({ 
			format: 'YYYY-MM-DD',
			maxDate: new Date(),
			defaultDate: fDate
	    });
	    $("#from-datepicker-expense").on('dp.change', function (e) {
	        var fromdate = $(this).val();
	    });
	    $("#to-datepicker-expense").datetimepicker({ 
			format: 'YYYY-MM-DD',
			maxDate: new Date(),
			defaultDate: new Date()
	    });
	    $("#to-datepicker-expense").on('dp.change', function (e) {
	        var fromdate = $(this).val();
	    });
		$("#from-datepicker-leave").datetimepicker({ 
			format: 'YYYY-MM-DD',
			defaultDate:firstDay1
	    });
	    $("#to-datepicker-leave").datetimepicker({ 
			format: 'YYYY-MM-DD',
			defaultDate:new Date()
	    });
		$("#from-datepicker").datetimepicker({ 
			format: 'YYYY-MM-DD',
			defaultDate:fDate
	    });
	    /* $("#from-datepicker").on('dp.change', function (e) {
	        var fromdate = $(this).val();
	    }); */
	    $("#to-datepicker").datetimepicker({ 
	    	format: 'YYYY-MM-DD',
	    	defaultDate:new Date(),
	    	maxDate: new Date()
	    });
	    /* $("#to-datepicker").on('dp.change', function (e) {
	        var todate = $(this).val();
	    }); */
	    $("#from-datepicker1").datetimepicker({ 
	    	format: 'YYYY-MM-DD'
	    });
	    $("#to-datepicker2").datetimepicker({ 
	    	format: 'YYYY-MM-DD',
	    	maxDate: new Date()
	    });
	    $("#from-datepickerWeekly").datetimepicker({ 
	    	format: 'YYYY-MM-DD'
	    });
	    $("#to-datepickerWeekly").datetimepicker({ 
	    	format: 'YYYY-MM-DD',
	    	maxDate: new Date()
	    });
	    $("#from-datepickerProject").datetimepicker({ 
	    	format: 'YYYY-MM-DD'
	    });
	    $("#to-datepickerProject").datetimepicker({ 
	    	format: 'YYYY-MM-DD'
	    });
	    $("#timesheetFilterStartDate").datetimepicker({ 
	    	format: 'YYYY-MM-DD',
	    	maxDate: new Date(),
	    	defaultDate: fDate.setMonth(fDate.getMonth() - 2, 1)
	    });
	    $("#timesheetFilterEndDate").datetimepicker({ 
	    	format: 'YYYY-MM-DD',
	    	maxDate: new Date(),
	    	defaultDate: new Date()
	    });
	    $("#from-date-User-Atten").datetimepicker({ 
	    	format: 'YYYY-MM-DD',
	    	maxDate: new Date(),
	    	defaultDate: firstDay1
	    });
	    $("#to-date-User-Atten").datetimepicker({ 
	    	format: 'YYYY-MM-DD',
	    	maxDate: new Date(),
	    	defaultDate: new Date()
	    });
	    $("#from-date-User-Atten-Week").datetimepicker({ 
	    	format: 'YYYY-MM-DD',
	    	maxDate: new Date()
	    });
	    $("#to-date-User-Atten-Week").datetimepicker({ 
	    	format: 'YYYY-MM-DD',
	    	maxDate: new Date()
	    });
	    $("#from-date-User-Atten-Exception").datetimepicker({ 
	    	format: 'YYYY-MM-DD',
	    	maxDate: new Date()
	    });
	    $("#to-date-User-Atten-Exception").datetimepicker({ 
	    	format: 'YYYY-MM-DD',
	    	maxDate: new Date()
	    });
	    $("#moafDateId").datetimepicker({ 
	    	format: 'YYYY-MM-DD',
	    	maxDate: new Date()
	    });
	  $('#timepicker2').timepicker({
	      minuteStep: 1,
	      template: 'dropdown',
	      showSeconds: false,
	      showMeridian: false,
	      defaultTime: 'current'
	  });
	  $('#timepicker3').timepicker({
	      minuteStep: 1,
	      template: 'dropdown',
	      showSeconds: false,
	      showMeridian: false,
	      defaultTime: 'current'
	  });
	  $('.meetingTime').datetimepicker({
		   format : 'MM/DD/YYYY HH:mm',
	       sideBySide: true,
	       defaultDate: new Date()
	  });
	  $("#assetPurchaseDate").datetimepicker({ 
	    	format: 'YYYY-MM-DD',
	    	maxDate: new Date(),
	    	defaultDate: new Date()
	    });
	  $("#createPRollPeriodSDate").datetimepicker({ 
	    	format: 'YYYY-MM-DD'
	   });
	  $("#createPRollPeriodEDate").datetimepicker({ 
	    	format: 'YYYY-MM-DD'
	   });
	  $("#empAllotedPayrollStartDate").datetimepicker({ 
	    	format: 'YYYY-MM-DD',
    		widgetPositioning:{
                   horizontal: 'auto',
                   vertical: 'bottom'
               }
	   });
	  $("#empAllotedPayrollEndDate").datetimepicker({ 
	    	format: 'YYYY-MM-DD',
    		widgetPositioning:{
                   horizontal: 'auto',
                   vertical: 'bottom'
               }
	   });
	  $("#id_newCTCStartDate").datetimepicker({ 
	    	format: 'YYYY-MM-DD',
  		widgetPositioning:{
                 horizontal: 'auto',
                 vertical: 'bottom'
             }
	   });
	  $("#id_newCTCEndDate").datetimepicker({ 
	    	format: 'YYYY-MM-DD',
  		widgetPositioning:{
                 horizontal: 'auto',
                 vertical: 'bottom'
             }
	   });
	  if(globalProxyCount != "")
		  userMngtFunc('All');
});	  
</script>
<%@ include file="divContainer.jsp" %>
<script type="text/javascript">
	var filename = "";
	$('.getMonthlyReporthref').click(function(){
		filename=document.getElementById('fileNameHidden').value;
	    var $this = $(this);       
	    var _href = $this.attr("href");
	    $this.attr("href", 'monthlyFiles?filename='+filename);
	});
	var uName = "";
	$('.backToMainLogin').click(function(){
		uName= '${sessionScope.loggedInUserProxy}';
	    var $this = $(this);       
	    $this.attr("href", 'backToMainLogin?uName='+uName);
	});
	var SessionTime=900000;
	var tickDuration=1000;
	var myInterval=setInterval(function(){SessionTime=SessionTime-tickDuration;},1000);
	var myTimeOut=setTimeout(SessionExpireEvent,SessionTime);
	$("body").click(function(){
	clearTimeout(myTimeOut);
	    SessionTime=900000;
	 myTimeOut=setTimeout(SessionExpireEvent,SessionTime);
	});
	
	function SessionExpireEvent()
	{ 
		clearInterval(myInterval);
		window.location.href = "logoutUser";
	}
	function formatDate(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();

	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;

	    return [year, month, day].join('-');
   }
	var demo1 = $('[name=duallistbox_demo1]').bootstrapDualListbox();
	var demo2 = $('[name=duallistbox_demo5]').bootstrapDualListbox();
</script>						
</body>			
</html>