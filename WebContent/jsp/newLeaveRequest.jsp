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
<title>Leave Management System SupraITS</title>
	<link href='<c:url value="/resources/css/bootstrap.min2.css" />' rel="stylesheet">
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
	<script src="<c:url value="/resources/js/moment.js"/>"></script>
	<script src="<c:url value="/resources/js/moment.min.js"/>"></script>
	<script src="<c:url value="/resources/js/lumino.glyphs.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap-filestyle.min.js" />"></script>
	<script src="<c:url value="/resources/scripts/leaveOperation.js"/>"></script>
<script type="text/javascript">
var sessionuser = '${sessionScope.loggedInUser}';
var applyLeaveForUser = '${applyLeaveForUser}';
if(sessionuser == "" ||  sessionuser == null ||sessionuser == undefined){
	alert("Session Expired.");
	window.close();
}
</script>
</head>
<body style="padding-top: 20px;">
<div class="container" style="width:100%;">
<form:form cssClass="form-horizontal" role="form"
				id="newLeaveRequestForm" data-toggle="validator" method="post"
				modelAttribute="leaveRequestBean" enctype="multipart/form-data">
<div class="row">
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
	<c:if test="${not empty applyLeaveForUser}">
	   <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" style="display:inline-block;">
						<div class="panel-group" id="accordion">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										New Leave Request 
										<span class="pull-right clickable">
											<i class="glyphicon glyphicon-chevron-up" data-toggle="collapse" data-target="#collapseUser"></i>
										</span>
									</h4>
								</div>
								<div id="collapseUser" class="panel-collapse collapse in" style="min-height: 300px;">
									<div class="panel-body">
									<table class="table" style="border:none;border-top:0px;">
										<tbody>
										<tr>
											<td style="border-top:0px;text-align:left;width:10%;">
											<form:label path="requestNumber" for="requestNumber"
													class="col-xs-6 col-sm-2 col-md-2 col-lg-3 form-control" style="border:none;">Request Number:</form:label>
											</td>
											<td style="border-top:0px;text-align:left">
											<form:input path="requestNumber" cssClass="form-control" style="width: 100%;background-color: #ff6666;"
														id="requestNumber" readonly="true" />
											</td>
											<td style="border-top:0px;text-align:left;width:10%;">
											<form:label path="requestNumber" for="requestNumber"
													class="col-xs-6 col-sm-2 col-md-2 col-lg-3 form-control" style="border:none;">HR Policy Group:</form:label>
											</td>
											<td style="border-top:0px;text-align:left">
											<form:input path="" cssClass="form-control" style="width: 100%;"
														id="" readonly="true" value='${userData.policyGroup}'/>
											</td>
										</tr>
										<tr>
											<td style="border-top:0px;text-align:left;width:10%;">
											<form:label path="" for=""
													class="col-xs-6 col-sm-2 col-md-2 col-lg-3 form-control" style="border:none;">Base Location:</form:label>
											</td>
											<td style="border-top:0px;text-align:left">
											<form:input path="" cssClass="form-control" style="width: 100%;"
														id="" readonly="true" value='${userData.baseLocation}'/>
											</td>
											<td style="border-top:0px;text-align:left;width:10%;">
											<form:label path="" for=""
													class="col-xs-6 col-sm-2 col-md-2 col-lg-3 form-control" style="border:none;">Emp Department:</form:label>
											</td>
											<td>
											<form:input path="" cssClass="form-control" style="width: 100%;" id="" readonly="true" value='${userData.department}'/>
											</td>
										</tr>
										<tr>
											<td style="border-top:0px;text-align:left;width:10%;">
											<form:label path="" for=""
													class="col-xs-6 col-sm-2 col-md-2 col-lg-3 form-control" style="border:none;">Employee Id:</form:label>
											</td>
											<td style="border-top:0px;text-align:left">
											<form:input path="" cssClass="form-control" style="width: 100%;" value='${userData.usercode}'
														id="" readonly="true" />
											<form:hidden path="username" cssClass="form-control" style="width: 100%;" value='${applyLeaveForUser}'
														id="" readonly="true" />
											</td>
											<td style="border-top:0px;text-align:left;width:10%;">
											<form:label path="" for=""
													class="col-xs-6 col-sm-2 col-md-2 col-lg-3 form-control" style="border:none;">Employee Name:</form:label>
											</td>
											<td style="border-top:0px;text-align:left">
											<form:input path="" cssClass="form-control" style="width: 100%;"
														id="" readonly="true" value='${userData.fullname}'/>
											</td>
										</tr>
										<tr>
											<td style="border-top:0px;text-align:left;width:10%;">
											<form:label path="" for=""
													class="col-xs-6 col-sm-2 col-md-2 col-lg-3 form-control" style="border:none;">Designation:</form:label>
											</td>
											<td style="border-top:0px;text-align:left">
											<form:input path="" cssClass="form-control" style="width: 100%;"
														id="" readonly="true" value='${userData.designation}'/>
											</td>
											<td style="border-top:0px;text-align:left;width:10%;">
											<form:label path="" for=""
													class="col-xs-6 col-sm-2 col-md-2 col-lg-3 form-control" style="border:none;">Approver Name:</form:label>
											</td>
											<td style="border-top:0px;text-align:left">
											<form:input path="" cssClass="form-control" style="width: 100%;" id="" readonly="true" value='${userData.rmFullname}'/>
											<form:hidden path="" cssClass="form-control" style="width: 100%;" id="" readonly="true" value='${userData.reportingManager}'/>
											</td>
										</tr>
										<tr>
											<td style="border-top:0px;text-align:left;width:10%;">
											<form:label path="requestedOn" for="requestedOn"
													class="col-xs-6 col-sm-2 col-md-2 col-lg-3 form-control" style="border:none;">Generation Date:</form:label>
											</td>
											<td style="border-top:0px;text-align:left">
													<form:input path="requestedOn" cssClass="form-control" style="width: 100%;"
														id="generationDate" readonly="true" />											
											</td>
											<td style="border-top:0px;text-align:left;width:10%;">
											<form:label path="requestedBy" for="requestedBy"
													class="col-xs-6 col-sm-2 col-md-2 col-lg-3 form-control" style="border:none;">Requested By:</form:label>
											</td>
											<td style="border-top:0px;text-align:left">
												<c:choose>
												    <c:when test="${sessionScope.loggedInUserProxy != ''}">
												    <form:input path="" cssClass="form-control" style="width: 100%;"
														id="" readonly="true" value='${sessionScope.loggedInUserFullNameProxy}'/>
												    <form:hidden path="requestedBy" value='${sessionScope.loggedInUserProxy}'/> 
												    </c:when>    
												    <c:otherwise>
													<form:input path="requestedBy" cssClass="form-control" style="width: 100%;"
														id="" readonly="true" value='${sessionScope.loggedInUserCode}'/>
													<form:hidden path="requestedBy" value='${sessionScope.loggedInUser}'/>	
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
										</tbody>
									</table>
									</div>
								</div>
							</div>
						</div>
				</div>
	</c:if>
	<c:if test="${empty applyLeaveForUser}">
   <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" style="display:inline-block;">
						<div class="panel-group" id="accordion">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										New Leave Request 
										<span class="pull-right clickable">
											<i class="glyphicon glyphicon-chevron-up" data-toggle="collapse" data-target="#collapseUser"></i>
										</span>
									</h4>
								</div>
								<div id="collapseUser" class="panel-collapse collapse in" style="min-height: 300px;">
									<div class="panel-body">
									<table class="table" style="border:none;border-top:0px;">
										<tbody>
										<tr>
											<td style="border-top:0px;text-align:left;width:10%;">
											<form:label path="requestNumber" for="requestNumber"
													class="col-xs-6 col-sm-2 col-md-2 col-lg-3 form-control" style="border:none;">Request Number:</form:label>
											</td>
											<td style="border-top:0px;text-align:left">
											<form:input path="requestNumber" cssClass="form-control" style="width: 100%;background-color: #ff6666;"
														id="requestNumber" readonly="true" />
											</td>
											<td style="border-top:0px;text-align:left;width:10%;">
											<form:label path="requestNumber" for="requestNumber"
													class="col-xs-6 col-sm-2 col-md-2 col-lg-3 form-control" style="border:none;">HR Policy Group:</form:label>
											</td>
											<td style="border-top:0px;text-align:left">
											<form:input path="" cssClass="form-control" style="width: 100%;"
														id="" readonly="true" value='${sessionScope.loggedInUserPolicyGroup}'/>
											</td>
										</tr>
										<tr>
											<td style="border-top:0px;text-align:left;width:10%;">
											<form:label path="" for=""
													class="col-xs-6 col-sm-2 col-md-2 col-lg-3 form-control" style="border:none;">Base Location:</form:label>
											</td>
											<td style="border-top:0px;text-align:left">
											<form:input path="" cssClass="form-control" style="width: 100%;"
														id="" readonly="true" value='${sessionScope.loggedInUserLocation}'/>
											</td>
											<td style="border-top:0px;text-align:left;width:10%;">
											<form:label path="" for=""
													class="col-xs-6 col-sm-2 col-md-2 col-lg-3 form-control" style="border:none;">Emp Department:</form:label>
											</td>
											<td>
											<form:input path="" cssClass="form-control" style="width: 100%;" id="" readonly="true" value='${sessionScope.loggedInUserDepartment}'/>
											</td>
										</tr>
										<tr>
											<td style="border-top:0px;text-align:left;width:10%;">
											<form:label path="" for=""
													class="col-xs-6 col-sm-2 col-md-2 col-lg-3 form-control" style="border:none;">Employee Id:</form:label>
											</td>
											<td style="border-top:0px;text-align:left">
											<form:input path="" cssClass="form-control" style="width: 100%;" value='${sessionScope.loggedInUserCode}'
														id="" readonly="true" />
											<form:hidden path="username" cssClass="form-control" style="width: 100%;" value='${sessionScope.loggedInUser}'
														 />
											</td>
											<td style="border-top:0px;text-align:left;width:10%;">
											<form:label path="" for=""
													class="col-xs-6 col-sm-2 col-md-2 col-lg-3 form-control" style="border:none;">Employee Name:</form:label>
											</td>
											<td style="border-top:0px;text-align:left">
											<form:input path="" cssClass="form-control" style="width: 100%;"
														id="" readonly="true" value='${sessionScope.loggedInUserFullName}' />
											</td>
										</tr>
										<tr>
											<td style="border-top:0px;text-align:left;width:10%;">
											<form:label path="" for=""
													class="col-xs-6 col-sm-2 col-md-2 col-lg-3 form-control" style="border:none;">Designation:</form:label>
											</td>
											<td style="border-top:0px;text-align:left">
											<form:input path="" cssClass="form-control" style="width: 100%;"
														id="" readonly="true" value='${sessionScope.loggedInUserDesignation}'/>
											</td>
											<td style="border-top:0px;text-align:left;width:10%;">
											<form:label path="" for=""
													class="col-xs-6 col-sm-2 col-md-2 col-lg-3 form-control" style="border:none;">Approver Name:</form:label>
											</td>
											<td style="border-top:0px;text-align:left">
												<form:input path="" cssClass="form-control" style="width: 100%;" id="" readonly="true" value='${sessionScope.reportingManager}' />
											</td>
										</tr>
										<tr>
											<td style="border-top:0px;text-align:left;width:10%;">
											<form:label path="requestedOn" for="requestedOn"
													class="col-xs-6 col-sm-2 col-md-2 col-lg-3 form-control" style="border:none;">Generation Date:</form:label>
											</td>
											<td style="border-top:0px;text-align:left">
													<form:input path="requestedOn" cssClass="form-control" style="width: 100%;"
														id="generationDate" readonly="true" />											
											</td>
											<td style="border-top:0px;text-align:left;width:10%;">
											<form:label path="requestedBy" for="requestedBy"
													class="col-xs-6 col-sm-2 col-md-2 col-lg-3 form-control" style="border:none;">Requested By:</form:label>
											</td>
											<td style="border-top:0px;text-align:left">
												<c:choose>
												    <c:when test="${sessionScope.loggedInUserProxy != ''}">
												    <form:input path="" cssClass="form-control" style="width: 100%;"
														id="" readonly="true" value='${sessionScope.loggedInUserFullNameProxy}'/>
												    <form:hidden path="requestedBy" cssClass="form-control" style="width: 100%;"
														id="" readonly="true" value='${sessionScope.loggedInUserProxy}'/> 
												    </c:when>    
												    <c:otherwise>
													<form:input path="" cssClass="form-control" style="width: 100%;"
														id="" readonly="true" value='${sessionScope.loggedInUserCode}'/>
													<form:hidden path="requestedBy" value='${sessionScope.loggedInUser}'/>	
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
										</tbody>
									</table>
									</div>
								</div>
							</div>
						</div>
				</div>
				</c:if>
				<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" style="display:inline-block;">
						<div class="panel-group" id="accordion">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										Leave Summary <span class="pull-right clickable"><i
											class="glyphicon glyphicon-chevron-up" data-toggle="collapse" data-target="#collapseGuideline"></i></span>
									</h4>
								</div>
								<div id="collapseGuideline" class="panel-collapse collapse in" style="min-height: 300px;">
									<div class="panel-body">
										<p class="egov-p-form"><b>Guideline for applying leave:</b></p>
										<p class="egov-p-form">Select leave interval, leave type and Full/Half day.You can add as many rows for different dates.</p>
										<p class="egov-p-form">No two rows can contain same dates in leave interval.</p>
										<p class="egov-p-form">Leave Year:<span style="color:red;" id="currLeaveYear">2017</span></p>
										<!-- <p class="egov-p-form">Leave Type:<span style="color:red;" id="carryLeaveTypes"></span></p> -->
										<!-- <p class="egov-p-form">Leave Balance:</p> -->
										<table class="table table-bordered" style="width:auto;"><tbody id="leaveBalTable"></tbody></table>
										<p class="egov-p-form">Note :: Same cumulative group shown in bracket ( ) share Leave Balance.</p>
									</div>
								</div>
							</div>
						</div>
					</div>
			</div>
			<span class="inline alert alert-danger" id="warningMessageId" style="display:none;position:fixed;"></span>
	<div class="row" style="margin-left:0px;margin-right:0px;">
		 <div class="panel-group" id="accordion">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										Leave Details<span class="pull-right clickable"></span>
									</h4>
								</div>
								<div id="collapseUser" class="panel-collapse collapse in">
									<div class="panel-body">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 column">
				<table id="piotable_exp" class="table table-bordered table-hover" id="tab_logic_exp" style="margin-bottom:0px;">
					<thead>
						<tr>
						<th class="text-center" style="width:12%;">
								Leave Type*
							</th>
							<th class="text-center" style="width:10%;">
								Start Date*
							</th>
							<th class="text-center" style="width:10%;">
								End date*
							</th>
							<th class="text-center" style="width:10%;">
								Full/Half Day*
							</th>
							<th class="text-center" style="width:10%;">
								Days
							</th>
							<th class="text-center" style="width:45%;">
								Purpose
							</th>
							<th>
								#
							</th>
						</tr>
					</thead>
					<tbody id="tab_logicExp">
						<tr id='query0'>
						<td>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
							<form:select class="form-control" path="leaveList[0].leaveCode" id="leaveList[0].leaveCode" onchange="setLeaveName('0')">
								<form:option value="select"  selected="selected">Select Type</form:option>
							</form:select>
							<div class="help-block with-errors"></div>
							<form:hidden path="leaveList[0].leaveName" id="leaveList[0].leaveName" />
		                 </div>
						</td>
						 <td>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
								<form:input type="text" path="leaveList[0].leaveStartDate" id="leaveList[0].leaveStartDate"  placeholder="Start Date" class="form-control dateClass" />
							</div>
						</td>
						<td>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
								<form:input type="text" path="leaveList[0].leaveEndDate" id="leaveList[0].leaveEndDate"  placeholder="End Date" class="form-control dateClass"/>
							</div>
						</td>
						<td>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
							<form:select class="form-control" path="leaveList[0].fullDayFlag" id="leaveList[0].fullDayFlag" onchange="calculateLeaveDays1('0');return false;">
								<form:option value="Full Day" selected="selected">Full Day</form:option>
								<form:option value="1st Half" >1st Half</form:option>
								<form:option value="2nd Half" >2nd Half</form:option>
							</form:select>
							<div class="help-block with-errors"></div>
		                 </div>
						</td>
						<td>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
								<form:input type="text" path="leaveList[0].leaveDays" id="leaveList[0].leaveDays" maxlength="2" value="0.0" class="form-control" readOnly="true"/>
							</div>
						</td>
						<td>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
								<form:input type="text" path="leaveList[0].leavePurpose" id="leaveList[0].leavePurpose" maxlength="100" placeholder="Purpose" class="form-control"/>
							</div>
						</td>
						<!-- <td>
								<a href="#!" class="btn btn-info btn-sm" onclick="openAddDocModal('leaveList[0].expenseDocumentList');">
						          <span class="glyphicon glyphicon-file"></span> File 
						        </a>
						        <input type="file" name="file" onchange="valFileUpload(this.value,id);" path="leaveList[0].expenseDocumentList" id="leaveList[0].expenseDocumentList" style="width:200px;" class="filestyle" data-buttonText="Doc"/>
						       <input type='text' readOnly='true' style='color:red;font-size:10px;overflow:hidden;border:none;' name="leaveList[0].expDocName" id="leaveList[0].expDocName"/> 
						</td> -->
						<td>
								<a href="#!" onclick="deleteLeaveRow('0')">
						          <span class="glyphicon glyphicon-trash"></span> 
						        </a>
						</td>
						</tr>
						<tr id='query1'></tr>
					</tbody>
					</table>
					<!-- <table class="table table-bordered ">
									<tr style="background-color:#eee;">
									<td colspan="3" style="text-align:right;width:36.88%;color:red;">Total</td>
									<td style="width:8%;color:red;" id="totalBillAmt">0</td>
									<td style="width:8%;color:red;" id="totalClaimedAmt">0</td>	
									<td></td>								
									</tr>
					</table> -->
					</div>
					<div class="row">
					<a id="add_leave_row" class="btn btn-success pull-left" style="margin-top: 20px;margin-left:30px;" onclick="addNewLeaveRow();">Add New Row</a>
					<a id="submitForApproval" class="btn btn-success pull-right" style="margin-top: 20px;margin-right:30px;">Submit</a>
					<!-- <a id="saveAsDraft" class="btn btn-danger pull-right" style="margin-top: 20px;margin-right:10px;">Save</a> -->
			</div>					
		    </div>
			</div>
		</div>
	</div>		 
 </div>	
</form:form>
</div>
				<div class="modal fade" id="leaveUserAckModal" role="dialog" data-keyboard="false" data-backdrop="static" style="margin-top:7%;">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header" style="background-color:#30a5ff;">
				          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
				          	<span style="color:#fff;font-weight:600;font-size:medium;">Employee Name: ${sessionScope.loggedInUserFullName}</span>
				        </div>
				        <div class="modal-body" id="leaveAckModalBody">
				        <span style="color:red;font-size:13px;">Please find below updated leave balance.</span>
				        <br>
				        <span style="color:red;font-size:13px;">Kindly accept and continue to apply leave.</span>
				        <div style="width:100%;margin-top:5px;">
				     		<table id="lmsUserAckTbl" class="table table-bordered" style="width:50%;margin-left:23%;">
		          			<thead>
		          				<tr>
				          			<th class="text-center">Leave Name</th>
									<th class="text-center">Updated Balance</th>
								</tr>
		          			</thead>
		          		<tbody id="tab_logic_lmsAck"></tbody>
		        		</table>
		        		<span style="color:red;font-size:13px;">*In case of any descrepnecy in leave ledger contact HR  ${sessionScope.hrManager}.</span>
		        		<br>
		        		<span style="color:red;font-size:13px;">*Disagreement will notify HR for further action.</span>
		        		</div>
		        		<a class="pull-center btn btn-primary btn-sm" style="margin-top:5px;margin-left:30%;" onclick="updateBalanceUpdationFlag('true');">Accept & Confirm</a>
		        		<a class="pull-center btn btn-primary btn-sm" style="margin-top:5px;" onclick="updateBalanceUpdationFlag('false');">Disagree</a>
		        		
				        </div>
				      </div>
				     </div>
				  </div>
<!-- <div class="modal fade" id="addDocModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				     	     <h4 class="modal-title">Upload Document </h4><span id="id_cmtId"></span>
				          </div>
				          <div class="modal-body">
				        			<div id="docRow_logic">
												<div id="docRow0" style="padding-bottom: 5px;">
													<fieldset class="form-inline">
														<input type="file" name="file" class="filestyle"
															data-buttonName="btn-primary"
															data-buttonText="Upload Document"
															onchange="valFileUpload(this.value,id);"
															id="docTemp">
														<input type="hidden" id="tempFile" value=""/>	
														<progress id="progressBar" value="0" max="100"
															style="width: 100px; display: none;"></progress>
														<span id="docNote0" style="color: red; margin-left: 3px;"></span>
													</fieldset>
												</div>
												<div id="docRow1"></div>
				        </div>
				       						 <div> 
				       						 <br>
												<a id="add_docRow"	class="btn btn-default pull-left">Add New Doc</a> 
												<a id='delete_docRow' class="pull-right btn btn-default">Delete	Last Doc</a>
												<br>
											</div>
				        <div class="modal-footer">
	    			      <button type="button" class="btn btn-success" onclick="uploadExpenseDocuments();">Upload</button>
				          <K type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div>
				       </div>
				     </div>
				</div>
				</div> -->
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
$(document).ready(function(){
	checkBalanceUpdationFlag();
	fetchNewLeaveNumber(applyLeaveForUser);
	fetchLeaveParam(applyLeaveForUser);
	getLeaveTypeList("leaveList[0].leaveCode");
	getUnpaidLeave();
	getApplyBeforeLeaveParam();
	getMaxDayLeaveParam();
	$('.dateClass').datepicker({
		format: 'yyyy-mm-dd',
	});
	$("input[id='leaveList[0].leaveStartDate']").change(function(){
		calculateLeaveDays1('0');return false;
	});
	$("input[id='leaveList[0].leaveEndDate']").change(function(){
		calculateLeaveDays1('0');return false;
	});
	/* $(".dateClass").datetimepicker({
	      format: 'YYYY-MM-DD',
	      onSelect: function(selected,evnt) {calculateLeaveDays1(this.id);}
	}); */
	$("#generationDate").val(formatDate(new Date()));
	$('#submitForApproval').click(function() {
		if(validateLeaveTable('tab_logicExp') && checkPrevLeaveList('tab_logicExp')){
		var ret = confirm("Do you want to send for approval?");
		if (ret == true) {
			var x = document.getElementById("newLeaveRequestForm");
			x.action = "submitNewLeaveRequest";
			x.submit();
		} else {
			return false;
		}
		}else{
			return false;
		}
	});
});
</script>
</body>
</html>
