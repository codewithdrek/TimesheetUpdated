<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='<c:url value="/resources/images/favicon.ico" />' rel="shortcut icon">
<title>Timesheet Management System SupraITS</title>
<link href='<c:url value="/resources/css/bootstrap.min.css" />' rel="stylesheet">
	<link href="<c:url value="/resources/css/datepicker3.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
	<%-- <link href="<c:url value="/resources/css/googleapi-fonts.css" />" rel="stylesheet"> --%>
	<%-- <link href="<c:url value="/resources/css/calendarView.css" />" rel="stylesheet"> --%>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-table.js"/>"></script>
	<script src="<c:url value="/resources/js/moment.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js" />"></script>
	<script src="<c:url value="/resources/js/lumino.glyphs.js" />"></script>
	
	<script src="<c:url value="/resources/js/modernizr.min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-ui.min.js" />"></script>
	<%-- <script src="<c:url value="/resources/js/infragistics.core.js" />"></script>
	<script src="<c:url value="/resources/js/infragistics.lob.js" />"></script>
	<link href="<c:url value="/resources/css/infragistics.theme.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/infragistics.css" />" rel="stylesheet"> --%>
	<script src="<c:url value="/resources/scripts/generateReport.js"/>"></script>
	<script src="<c:url value="/resources/scripts/projectAction.js"/>"></script>
	<script src="<c:url value="/resources/js/draggable.min.js" />"></script>
	<link href="<c:url value="/resources/css/draggable.min.css" />" rel="stylesheet">

<script type="text/javascript">
var sessionuser = '${sessionScope.loggedInUser}';
if(sessionuser == "" ||  sessionuser == null ||sessionuser == undefined){
	alert("Session Expired.");
	window.close();
}
</script>
<script>
		$(document).ready(function(){
		
		$("#endDate").datetimepicker({ 
			format: 'YYYY-MM-DD'
		});
		$("#startDate").datetimepicker({ 
			format: 'YYYY-MM-DD'
		});
		$(".taskStartDate").datetimepicker({ 
			format: 'YYYY-MM-DD'
		});
		$(".taskEndDate").datetimepicker({ 
			format: 'YYYY-MM-DD'
		});
		$("#updatePrjData").click(function()
		{
			updateViewPrjDetails();
		});
		});
</script>
<style>
*,
*:after,
*:before {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

.clearfix:before,
.clearfix:after {
	content: " ";
	display: table;
}

.clearfix:after {
	clear: both;
}
body {
	font-family: sans-serif;
	background: #f6f9fa;
	padding:10px;
}

h1 {
	color: #00000;
	text-align: left;
}

a {
  color: #ccc;
  text-decoration: none;
  outline: none;
}

/*Fun begins*/
.tab_container {
	width: 90%;
	margin: 0 auto;
	padding-top: 20px;
	position: relative;
}

input, section {
  clear: both;
  padding-top: 10px;
  display: none;
}

label {
  font-weight: 700;
  font-size: 18px;
  display: block;
  float: left;
  width: 20%;
  padding: 1.5em;
  color: #757575;
  cursor: pointer;
  text-decoration: none;
  text-align: center;
  background: #f0f0f0;
}

#tab1:checked ~ #content1,
#tab2:checked ~ #content2,
#tab3:checked ~ #content3,
#tab4:checked ~ #content4,
#tab5:checked ~ #content5 {
  display: block;
  padding: 20px;
  background: #fff;
  color: #999;
  border-bottom: 2px solid #f0f0f0;
}

.tab_container .tab-content p,
.tab_container .tab-content h3 {
  -webkit-animation: fadeInScale 0.7s ease-in-out;
  -moz-animation: fadeInScale 0.7s ease-in-out;
  animation: fadeInScale 0.7s ease-in-out;
}
.tab_container .tab-content h3  {
  text-align: left;
}

.tab_container [id^="tab"]:checked + label {
  background: #fff;
  box-shadow: inset 0 3px #0CE;
}

.tab_container [id^="tab"]:checked + label .fa {
  color: #0CE;
}

label .fa {
  font-size: 1.3em;
  margin: 0 0.4em 0 0;
}

/*Media query*/
@media only screen and (max-width: 930px) {
  label span {
    font-size: 14px;
  }
  label .fa {
    font-size: 14px;
  }
}

@media only screen and (max-width: 768px) {
  label span {
    display: none;
  }

  label .fa {
    font-size: 16px;
  }

  .tab_container {
    width: 98%;
  }
}

/*Content Animation*/
@keyframes fadeInScale {
  0% {
  	transform: scale(0.9);
  	opacity: 0;
  }
  
  100% {
  	transform: scale(1);
  	opacity: 1;
  }
}
 input,textarea, select, button {
    text-rendering: auto;
    color: initial;
    letter-spacing: normal;
    word-spacing: normal;
    text-transform: none;
    text-indent: 0px;
    text-shadow: none;
    display: inline-block;
    text-align: start;
    margin: 0em;
    font: 13.3333px Arial;
} 
span.multiselect-native-select {
	position: relative
}
span.multiselect-native-select select {
	border: 0!important;
	clip: rect(0 0 0 0)!important;
	height: 1px!important;
	margin: -1px -1px -1px -3px!important;
	overflow: hidden!important;
	padding: 0!important;
	position: absolute!important;
	width: 1px!important;
	left: 50%;
	top: 30px
}
.multiselect-container {
	position: absolute;
	list-style-type: none;
	margin: 0;
	padding: 0
}
.multiselect-container .input-group {
	margin: 5px
}
.multiselect-container>li {
	padding: 0
}
.multiselect-container>li>a.multiselect-all label {
	font-weight: 700
}
.multiselect-container>li.multiselect-group label {
	margin: 0;
	padding: 3px 20px 3px 20px;
	height: 100%;
	font-weight: 700
}
.multiselect-container>li.multiselect-group-clickable label {
	cursor: pointer
}
.multiselect-container>li>a {
	padding: 0
}
.multiselect-container>li>a>label {
	margin: 0;
	height: 100%;
	cursor: pointer;
	font-weight: 400;
	padding: 3px 0 3px 30px
}
.multiselect-container>li>a>label.radio, .multiselect-container>li>a>label.checkbox {
	margin: 0
}
.multiselect-container>li>a>label>input[type=checkbox] {
	margin-bottom: 5px
}
.btn-group>.btn-group:nth-child(2)>.multiselect.btn {
	border-top-left-radius: 4px;
	border-bottom-left-radius: 4px
}
.form-inline .multiselect-container label.checkbox, .form-inline .multiselect-container label.radio {
	padding: 3px 20px 3px 40px
}
.form-inline .multiselect-container li a label.checkbox input[type=checkbox], .form-inline .multiselect-container li a label.radio input[type=radio] {
	margin-left: -20px;
	margin-right: 0
}
</style>	
</head>
<body>
	<form:form role="form" id="addPrjct" data-toggle="validator" method="post" modelAttribute="projectBean">
    <h2>Project: ${projectBean.projectName}</h2>
		<div class="tab_container">
			<input id="tab1" type="radio" name="tabs" style="display:none;" checked>
			<label for="tab1"><i class="fa fa-table"></i><span>Project Details</span></label>

			<input id="tab2" type="radio" name="tabs" style="display:none;">
			<label for="tab2"><i class="fa fa-pencil-square-o"></i><span>Task Details</span></label>

			<input id="tab3" type="radio" name="tabs" style="display:none;">
			<label for="tab3"><i class="fa fa-bar-chart-o"></i><span>Users</span></label>

			<input id="tab4" type="radio" name="tabs" style="display:none;">
			<label for="tab4"><i class="fa fa-folder-open-o"></i><span>Project Doc</span></label>

			<input id="tab5" type="radio" name="tabs" style="display:none;">
			<label for="tab5"><i class="fa fa-envelope-o"></i><span>Others</span></label>

			<section id="content1" class="tab-content">
			<div class="row">
			<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-4">
				<span class="">Project Id:</span>
					<form:input class="form-control" path="projectId" name="projectId" for="projectId" id="projectId" readOnly="true" />
			</div>
			<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-4">
				<span class="">Project Name:</span>
					<form:input class="form-control" path="projectName" name="projectName" for="projectName" id="projectName" readOnly="true"/>
			</div>
			<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-4">
				<span class="">Project Status:</span>
				<%-- <form:input class="form-control" path="projectStatus" name="projectStatus" for="projectStatus" id="projectStatus"/> --%>
					 <form:select class="form-control" path="projectStatus" name="projectStatus" for="projectStatus" id="projectStatus">
						<form:option value='${projectBean.projectStatus}'>${projectBean.projectStatus}</form:option>
					</form:select>
			</div>
			</div>
			<div class="row">
			<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-12">
				<span class="">Project Description:</span>
					<form:input type="textarea" class="form-control" path="projectDesc" name="projectDesc" for="projectDesc" id="projectDesc" readOnly="true"/>
			</div></div>
			<div class="row">
			<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-4">
				<span class="">Project Owner:</span>
					<%-- <form:input class="form-control" path="projectOwner" name="projectOwner" for="projectOwner" id="projectOwner" readOnly="true"/> --%>
					<form:select class="form-control" path="projectOwner" name="projectOwner" for="projectOwner" id="projectOwner">
						<form:option value='${projectBean.projectOwner}'>${projectBean.projectOwner}</form:option>
					</form:select>
			</div>
			<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-4">
				<span class="">Project Type:</span>
					<form:input class="form-control" path="projectType" name="projectType" for="projectType" id="projectType" readOnly="true"/>
			</div>
			<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-4">
				<span class="">Creation Date:</span>
					<form:input class="form-control" path="creationDate" name="creationDate" for="creationDate" id="creationDate" readOnly="true"/>
			</div>
			</div>
			<div class="row">
			<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-4">
				<span class="">Start Date:</span>
				<form:input class="form-control" path="startDate" name="startDate" for="startDate" id="startDate"/>
			</div>
			<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-4">
				<span class="">End Date(Actual/Expected):</span>
				<form:input class="form-control" path="endDate" name="endDate" for="endDate" id="endDate"/>
			</div>
			<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-4">
				<span class="">Timesheet Approver:</span>
				<form:input class="form-control" path="timesheetApprover" name="timesheetApprover" for="timesheetApprover" id="timesheetApprover"/>
			</div>
			<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-12">
				<a href="#" class="btn btn-info btn-sm" id="updatePrjData"> Update</a>			
			</div>	
			</div>
			</section>

			<section id="content2" class="tab-content">
			<div class="row pull-right" style="margin-right:5px;margin-bottom:15px;">
					<button class="btn btn-info btn-sm" onclick='return false;'><a href="#" style="color:white;" onclick="addTask1('${projectBean.projectId}','${projectBean.projectId}');">Add New Task</a></button>
			</div>
				<div class="row">
					<table id="piotable" class="table table-bordered table-hover" id="tab_logic">
						<thead>
							<tr >
								<!-- <th class="text-center">
									Task Id
								</th> -->
								<th class="text-center" style="width:15%;">
									Task Name
								</th>
								<th class="text-center">
									Task Description
								</th>
								<th class="text-center" style="width:10%;">
									Priority
								</th>
								<th class="text-center" style="width:10%;">
									Start Date
								</th>
								<th class="text-center" style="width:10%;">
									End Date
								</th>
								<th class="text-center" style="width:11%;">
									Status
								</th>
								<th class="text-center">
									Assigned To
								</th>
								<th class="text-center">
									Action
								</th>
							</tr>
						</thead>
						<tbody id='taskList'>
						<c:forEach items="${projectBean.taskList}" var="query" varStatus="qrystatus">
							<tr>
							<%-- <td><form:input class="form-control" path="taskList[${qrystatus.index}].taskId" id="row${query.taskId}" /></td> --%>
							<%-- <form:input class="form-control" path="taskList[${qrystatus.index}].taskId" id="row${query.taskId}" /> --%>
							<td><form:input class="form-control" path="taskList[${qrystatus.index}].taskName" id="taskName[${qrystatus.index}]" /></td>
							<td><form:input class="form-control" path="taskList[${qrystatus.index}].taskDesc" id="taskDesc[${qrystatus.index}]" /></td>
							<td><form:select class="form-control" path="taskList[${qrystatus.index}].criticality"  id="criticality[${qrystatus.index}].criticality"><form:option value="${query.criticality}" /></form:select></td>
							<td><form:input class="taskStartDate form-control" path="taskList[${qrystatus.index}].actualStartDate" id="actualStartDate[${qrystatus.index}]" /></td>
							<td><form:input class="taskEndDate form-control" path="taskList[${qrystatus.index}].actualEndDate" id="actualEndDate[${qrystatus.index}]" /></td>
							<td><form:select class="form-control" path="taskList[${qrystatus.index}].taskStatus" id="taskStatus[${qrystatus.index}].taskStatus"><form:option value="${query.taskStatus}" /></form:select></td>
							<td><form:select class="form-control" path="taskList[${qrystatus.index}].assignedTo" id="assignedTo[${qrystatus.index}].assignedTo"><form:option value="${query.assignedTo}" /></form:select></td>
							<td nowrap class="EWTableElements text-center"><button class="btn btn-danger btn-sm" id="xrow${query.taskId}" onclick="removeTaskFrmPrjct(this.id);return false;"> Remove </button>
								<button class="btn btn-success btn-sm" id="yrow${query.taskId}" onclick="updateTaskFrmPrjct(this.id,'${qrystatus.index}');return false;"> Update </button>
							</td>
							</tr>
						</c:forEach>											
					</table>
				</div>
			</section>
			<section id="content3" class="tab-content">
				 <div class="row pull-right" style="margin-right:5px;margin-bottom:15px;">
					<button class="btn btn-info btn-sm"><a href="#!" style="color:white;" onclick="updateProjectUsers('${projectBean.projectId}');return false;">Assign User</a></button>
				</div>
				<div class="row">
					<table id="piotable" class="table table-bordered table-hover" id="tab_logic">
						<thead>
							<tr >
								<th class="text-center">
									Username
								</th>
								<th class="text-center">
									First Name
								</th>
								<th class="text-center">
									Last Name
								</th>
								<th class="text-center" style="width:20%;">
									Primary Email
								</th>
								<th class="text-center">
									Role
								</th>
								<th class="text-center">
									Group
								</th>
								<th class="text-center">
									Creation Date
								</th>
								<th class="text-center">
									Current Status
								</th>
								<th class="text-center">
									Action
								</th>
							</tr>
						</thead>
						<tbody>
		<c:forEach items="${projectBean.userList}" var="query" varStatus="qrystatus">
			<tr>
			<td><form:input class="form-control" path="userList[${qrystatus.index}].username" id="row${query.username}" readonly="true"/></td>
			<td><form:input class="form-control" path="userList[${qrystatus.index}].firstName" id="row${query.firstName}" readonly="true"/></td>
			<td><form:input class="form-control" path="userList[${qrystatus.index}].lastName" id="row${query.lastName}" readonly="true"/></td>
			<td><form:input style="width:100%;" class="form-control" path="userList[${qrystatus.index}].primaryMail" id="row${query.primaryMail}" readonly="true"/></td>
			<td><form:input class="form-control" path="userList[${qrystatus.index}].role" id="row${query.role}" readonly="true"/></td>
			<td><form:input class="form-control" path="userList[${qrystatus.index}].userGroup" id="row${query.userGroup}" readonly="true"/></td>
			<td><form:input class="form-control" path="userList[${qrystatus.index}].creationDate" id="row${query.creationDate}" readonly="true"/></td>
			<td><form:input class="form-control" path="userList[${qrystatus.index}].userStatus" id="row${query.userStatus}" readonly="true"/></td>
			<td><button class="btn btn-danger btn-sm" id="xrow${query.username}" onclick="disAllocateUser(this.id);return false;"> Deallocate </button></td>
			</tr>
		</c:forEach>											
	</table>
															
				</div>
			</section>

			<section id="content4" class="tab-content">
				<h3>Coming soon!</h3>
		      	<p>All project related documents.</p>
			</section>

			<section id="content5" class="tab-content">
				<h3>Coming Soon</h3>
		      	<p>All project related issue list.</p>
			</section>
		</div>
		</form:form>
		<div class="modal fade" id="addTaskModal1" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Add Task to </h4>
				          <span id="id_prjctName11"></span>
				          <input type="hidden" id="pidValue1" name="pidValue" />
				        </div>
				        <div class="modal-body">
				        <div class="form-group">
				          <span><b>Title:</b></span>
				          <input class="input-md  textinput textInput form-control" id="id_taskName1" maxlength="50" minlength="6" name="taskName" placeholder="Task Name" style="margin-bottom: 10px" type="text" />
				        </div>
				        <div class="form-group">  
				          <span><b>Type</b></span>
				          <select id="id_taskType1" class="form-control" name="taskType" style="margin-bottom: 10px">
				          		<option value="">Select Type</option>
				          </select>
				        </div>
				        <div class="form-group">  
				          <span><b>Description</b></span>
				          <input class="input-md  textinput textInput form-control" id="id_taskDesc1" maxlength="200" name="taskDesc" placeholder="TaskDescription" style="margin-bottom: 10px" type="text" />
				        </div>
				        </div>
				        <div class="modal-footer">
	    			      <button type="button" class="btn btn-success" id="saveNewTask" onclick="insertNewTask1();">Save</button>
				          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div>
				        </div>
				       </div>
				 </div>
				 <!-- <div class="modal fade" id="assignUserModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Assign Users to <span id="id_prjctIdAssign"></span><input type="hidden" id="pidAssignValue" name="pidAssignValue" /></h4>
				        </div>
				       <div class="modal-body">
				        <div class="form-group">
				          <h4 class="combo-label">Multiple Selection of Active Users</h4>
				          	<span style="color:red;font-size:14px;">Users who already assigned to this project are not listed.</span>
    						<div id="checkboxSelectCombo"></div>
    						<input type ="hidden" id ="Hidden1"/>
				        </div>
				        </div>
				        <div class="modal-footer">
	    			      <button type="button" class="btn btn-success" id="saveNewTask" onclick="assignToProject();">Save</button>
				          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div>
				        </div>
				       </div>
				 </div> -->
				 <div class="modal fade" id="assignUserModal" role="dialog">
				    <div class="modal-dialog modal-lg">
				      <div class="modal-content">
				        <div class="modal-header"">
				          <div class="modal-header" style="background-color:#30a5ff;">
				          <!-- <button type="button" class="btn" data-dismiss="modal">&times;</button> -->
				          <a href='#!' title='Reset User List' style='color:#fff;' onclick='resetUpdateProjectUserModal();return false;'><span class='glyphicon glyphicon-refresh pull-right' style='margin-right: 10px;'></span></a>
				          	<span style="color:#fff;font-weight:600;font-size:medium;">Add/Remove Users from Project</span>
				          	</div>
				          	<input type="hidden" id="id_projectId1" value="" />
				        </div>
				        <div class="modal-body">
				        <span id="id_projectName1" style="color:black;font-weight:600;font-size:medium;"></span><br>
				        <span style="color:red;font-size:12px;">*Right panel displays already assigned users in project.</span><br>
				        <span style="color:red;font-size:12px;">*Left panel displays active users in system who are not assigned to this project.</span><br>
				        <select multiple="multiple" name="duallistbox_demo2" id="duallistbox_demo2">
							<option value="option3" selected="selected">Option 3</option>
							<option value="option4">Option 4</option>
						</select>
				        </div>
				        <div style="margin-bottom:7px;">
				        	<button type="button" class="btn btn-sm btn-success" style="margin-left:44%;" onclick="addRemoveUsersFromProject();return false;">Save</button>
				        	<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Close</button>
				        </div>
				      </div>
				     </div>
				  </div> 
				 <script>
$(document).ready(function(){
	//populateTableParam('taskList','${projectBean.projectId}');
	getUsersInProject('taskList','${projectBean.projectId}');
	getAllTaskStatus('taskList');
	getAllMangerAdmin('taskList','projectOwner');
	getAllCriticalLevel('taskList');
	getPrjStatusType('taskList','projectStatus');
});
var demo1 = $('[name=duallistbox_demo2]').bootstrapDualListbox();
</script>
</body>
</html>