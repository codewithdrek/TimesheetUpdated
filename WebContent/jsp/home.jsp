<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
	<link href="<c:url value="/resources/css/googleapi-fonts.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/calendarView.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/bootstrap-timepicker.css" />" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-table.js"/>"></script>
	<script src="<c:url value="/resources/js/moment.min.js"/>"></script>
	<script src="<c:url value="/resources/js/lumino.glyphs.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js" />"></script>
	<!-- <link href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css">
	<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script> -->
	<%-- <script src="<c:url value="/resources/js/bootstrap-multiselect.js" />"></script> --%>
<script src="<c:url value="/resources/scripts/globalConstant.js"/>"></script>	
<script src="<c:url value="/resources/js/modernizr.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui.min.js" />"></script>
<script src="<c:url value="/resources/js/draggable.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap-timepicker.js" />"></script>
<link href="<c:url value="/resources/css/draggable.min.css" />" rel="stylesheet">
<%-- <script src="<c:url value="/resources/js/infragistics.core.js" />"></script>
<script src="<c:url value="/resources/js/infragistics.lob.js" />"></script>
<link href="<c:url value="/resources/css/infragistics.theme.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/infragistics.css" />" rel="stylesheet"> --%>
<!-- <link href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css" rel="stylesheet">
<script src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script> -->
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
<script type="text/javascript">
	//Declaring global js varibale for loggedin user group
	var globalGroup = '${sessionScope.loggedinusergroup}';
	var globalloggedinuser = '${sessionScope.loggedInUser}';
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
<script src="<c:url value="/resources/scripts/userProfile.js"/>"></script>
<script src="<c:url value="/resources/scripts/generateReport.js"/>"></script>	
<script src="<c:url value="/resources/scripts/reimbursement.js"/>"></script>
<script src="<c:url value="/resources/scripts/leaveOperation.js"/>"></script>
<script src="<c:url value="/resources/scripts/assetAllocation.js"/>"></script>
<script src="<c:url value="/resources/scripts/attendance.js"/>"></script>
<script src="<c:url value="/resources/scripts/hrms.js"/>"></script>
	
<script>
$(document).ready(function(){
	$.getJSON("https://api.ipify.org/?format=json", function(e) {
	    console.log(e.ip);
	    clientIp1 = e.ip;
	});
	/* $('input:radio[name="id_select_type_policy"]').change(function(){
	    if($(this).val() == 'SUPRA-Canada'){
	    	getEmpListBasedOnPolicyGroup('SUPRA-Canada');
	    }else{
	    	getEmpListBasedOnPolicyGroup('SUPRA-Noida');
	    }
	}); */
	  getApprovalPendingCount();	
	  $(function () {
		  var value = formatDate(new Date());
		  var firstDate = moment(value, "YYYY-MM-DD").day(0).format("YYYY-MM-DD");
	      var secondDate = moment(value, "YYYY-MM-DD").day(1).format("YYYY-MM-DD");
	      var thirdDate = moment(value, "YYYY-MM-DD").day(2).format("YYYY-MM-DD");
	      var fourthDate = moment(value, "YYYY-MM-DD").day(3).format("YYYY-MM-DD");
	      var fifthDate = moment(value, "YYYY-MM-DD").day(4).format("YYYY-MM-DD");
	      var sixthDate = moment(value, "YYYY-MM-DD").day(5).format("YYYY-MM-DD");
	      var lastDate =  moment(value, "YYYY-MM-DD").day(6).format("YYYY-MM-DD");
	      $("#weeklyDatePicker").val(firstDate + " - " + lastDate);
	      var str = firstDate + " - " + lastDate;
	      $("#weeklyDatePickerShow").val("Sun, "+str.substring(8, 10)+"/"+str.substring(5, 7)+" - " +str.substring(21, 23)+"/"+str.substring(18, 20)+ " ,Sat");
	      $("#weekDay1").text('Sun ' +firstDate.substring(8, 10));
	      $("#weekDay2").text('Mon ' +secondDate.substring(8, 10));
	      $("#weekDay3").text('Tue ' +thirdDate.substring(8, 10));
	      $("#weekDay4").text('Wed ' +fourthDate.substring(8, 10));
	      $("#weekDay5").text('Thu ' +fifthDate.substring(8, 10));
	      $("#weekDay6").text('Fri ' +sixthDate.substring(8, 10));
	      $("#weekDay7").text('Sat ' +lastDate.substring(8, 10));
	      $("#weeklyDatePicker").val(firstDate + " - " + lastDate);
	      var str = firstDate + " - " + lastDate;
	      $("#weeklyDatePickerShow").val("Sun, "+str.substring(8, 10)+"/"+str.substring(5, 7)+" - " +str.substring(21, 23)+"/"+str.substring(18, 20)+ " ,Sat");
	      $("#nextWeek").attr("disabled","disabled");
	      
	      getDataFromTimeSheet1(firstDate,secondDate,thirdDate,fourthDate,fifthDate,sixthDate,lastDate);  
	  });
	  $("#prevWeek").click(function()
	  {
		  var selectedWeek1 = $("#weeklyDatePicker").val();
		  var value = new Date(selectedWeek1.substring(0, 10));
		  value.setDate(value.getDate() - 4);
		  value = formatDate(value);
		  var firstDate = moment(value, "YYYY-MM-DD").day(0).format("YYYY-MM-DD");
	      var secondDate = moment(value, "YYYY-MM-DD").day(1).format("YYYY-MM-DD");
	      var thirdDate = moment(value, "YYYY-MM-DD").day(2).format("YYYY-MM-DD");
	      var fourthDate = moment(value, "YYYY-MM-DD").day(3).format("YYYY-MM-DD");
	      var fifthDate = moment(value, "YYYY-MM-DD").day(4).format("YYYY-MM-DD");
	      var sixthDate = moment(value, "YYYY-MM-DD").day(5).format("YYYY-MM-DD");
	      var lastDate =  moment(value, "YYYY-MM-DD").day(6).format("YYYY-MM-DD");
	      $("#nextWeek").attr("disabled",false);
	      var temp = new Date();
	      temp.setDate(1);
	      temp.setMonth(temp.getMonth() - 4);
	      if(new Date(lastDate) < temp)
	      {
	    	  $("#prevWeek").attr("disabled",true);
	    	  return false;
	      }
	      $("#weekDay1").text('Sun ' +firstDate.substring(8, 10));
	      $("#weekDay2").text('Mon ' +secondDate.substring(8, 10));
	      $("#weekDay3").text('Tue ' +thirdDate.substring(8, 10));
	      $("#weekDay4").text('Wed ' +fourthDate.substring(8, 10));
	      $("#weekDay5").text('Thu ' +fifthDate.substring(8, 10));
	      $("#weekDay6").text('Fri ' +sixthDate.substring(8, 10));
	      $("#weekDay7").text('Sat ' +lastDate.substring(8, 10));
	      $("#weeklyDatePicker").val(firstDate + " - " + lastDate);
	      var str = firstDate + " - " + lastDate;
	      $("#weeklyDatePickerShow").val("Sun, "+str.substring(8, 10)+"/"+str.substring(5, 7)+" - " +str.substring(21, 23)+"/"+str.substring(18, 20)+ " ,Sat");
	      //$("#weeklyDatePicker").val(firstDate + " - " + lastDate);		  
	     
	      getDataFromTimeSheet1(firstDate,secondDate,thirdDate,fourthDate,fifthDate,sixthDate,lastDate);
	  });
	  $("#nextWeek").click(function()
			  {
				  var selectedWeek = $("#weeklyDatePicker").val();
				  var value = new Date(selectedWeek.substring(0, 10));
				  value.setDate(value.getDate() + 8);
				  value = formatDate(value);
				  var firstDate = moment(value, "YYYY-MM-DD").day(0).format("YYYY-MM-DD");
			      var secondDate = moment(value, "YYYY-MM-DD").day(1).format("YYYY-MM-DD");
			      var thirdDate = moment(value, "YYYY-MM-DD").day(2).format("YYYY-MM-DD");
			      var fourthDate = moment(value, "YYYY-MM-DD").day(3).format("YYYY-MM-DD");
			      var fifthDate = moment(value, "YYYY-MM-DD").day(4).format("YYYY-MM-DD");
			      var sixthDate = moment(value, "YYYY-MM-DD").day(5).format("YYYY-MM-DD");
			      var lastDate =  moment(value, "YYYY-MM-DD").day(6).format("YYYY-MM-DD");
			      $("#weeklyDatePicker").val(firstDate + " - " + lastDate);
			      var str = firstDate + " - " + lastDate;
			      $("#weeklyDatePickerShow").val("Sun, "+str.substring(8, 10)+"/"+str.substring(5, 7)+" - " +str.substring(21, 23)+"/"+str.substring(18, 20)+ " ,Sat");
			      $("#weekDay1").text('Sun ' +firstDate.substring(8, 10));
			      $("#weekDay2").text('Mon ' +secondDate.substring(8, 10));
			      $("#weekDay3").text('Tue ' +thirdDate.substring(8, 10));
			      $("#weekDay4").text('Wed ' +fourthDate.substring(8, 10));
			      $("#weekDay5").text('Thu ' +fifthDate.substring(8, 10));
			      $("#weekDay6").text('Fri ' +sixthDate.substring(8, 10));
			      $("#weekDay7").text('Sat ' +lastDate.substring(8, 10));
			      var temp = new Date();
			      if(new Date(lastDate) > temp)
			      {
			    	  $("#nextWeek").attr("disabled",true);
			      }
			      $("#prevWeek").attr("disabled",false);
			      
			      getDataFromTimeSheet1(firstDate,secondDate,thirdDate,fourthDate,fifthDate,sixthDate,lastDate);
	   });
	 	$("#weeklyDatePickerTimesheet").datetimepicker({
	      format: 'YYYY-MM-DD',
	      maxDate: new Date()
	  	});	
	   $('#weeklyDatePickerTimesheet').on('dp.change', function (e) {
	      var value1 = $("#weeklyDatePickerTimesheet").val();
	      var firstDate1 = moment(value1, "YYYY-MM-DD").day(0).format("YYYY-MM-DD");
	      var lastDate1 =  moment(value1, "YYYY-MM-DD").day(6).format("YYYY-MM-DD");
	      $("#weeklyDatePickerTimesheet").val(firstDate1 + " - " + lastDate1);
	      getTimesheetFilteredData("Pending","1");
	  }); 
	   //Get the value of Start and End of Week
	  $("#add_row").click(function(){
		  var queryCount= $('#tab_logic1 tr').length - 1; 
		  $('#query'+queryCount).html("<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><select class='form-control' name='taskMappingList["+queryCount+"].projectId' id='taskMappingList["+queryCount+"].projectId' onchange='getTaskListOnSelect(this.value,this.id)'><option value='--Select--'  selected='selected'>Select Project</option><td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><select class='form-control' name='taskMappingList["+queryCount+"].taskId' id='taskMappingList["+queryCount+"].taskId'><option value='--Select--'  selected='selected'>Select Task</option></select><div class='help-block with-errors'></div></div></td>" +
			  		"<td class='success'><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay1' id='taskMappingList["+queryCount+"].effortInHoursDay1' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day1' id='taskMappingList["+queryCount+"].day1'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='fa fa-comments-o' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day1B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day1Comment' id='taskMappingList["+queryCount+"].day1Comment'/></div></td>" +
						"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay2' id='taskMappingList["+queryCount+"].effortInHoursDay2' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day2' id='taskMappingList["+queryCount+"].day2'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='fa fa-comments-o' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day2B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day2Comment' id='taskMappingList["+queryCount+"].day2Comment'/></div></td>" +
								"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay3' id='taskMappingList["+queryCount+"].effortInHoursDay3' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day3' id='taskMappingList["+queryCount+"].day3'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='fa fa-comments-o' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day3B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day3Comment' id='taskMappingList["+queryCount+"].day3Comment'/></div></td>" +
										"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay4' id='taskMappingList["+queryCount+"].effortInHoursDay4' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day4' id='taskMappingList["+queryCount+"].day4'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='fa fa-comments-o' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day4B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day4Comment' id='taskMappingList["+queryCount+"].day4Comment'/></div></td>" +
												"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay5' id='taskMappingList["+queryCount+"].effortInHoursDay5' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day5' id='taskMappingList["+queryCount+"].day5'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='fa fa-comments-o' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day5B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day5Comment' id='taskMappingList["+queryCount+"].day5Comment'/></div></td>" +
														"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay6' id='taskMappingList["+queryCount+"].effortInHoursDay6' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day6' id='taskMappingList["+queryCount+"].day6'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='fa fa-comments-o' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day6B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day6Comment' id='taskMappingList["+queryCount+"].day6Comment'/></div></td>" +
																"<td class='success'><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay7' id='taskMappingList["+queryCount+"].effortInHoursDay7' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day7' id='taskMappingList["+queryCount+"].day7'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='fa fa-comments-o' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day7B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day7Comment' id='taskMappingList["+queryCount+"].day7Comment'/></div></td>");
		  $('#tab_logic1').append('<tr id="query'+(queryCount+1)+'"></tr>');
			getProjectList("taskMappingList["+queryCount+"].projectId");
			queryCount++;
	  }); 
	  $("#delete_row").click(function()
				{
		  var queryCount= $('#tab_logic1 tr').length - 1; 
					if(queryCount>1)
					{
						var pid = document.getElementById("taskMappingList["+(queryCount-1)+"].projectId").value;
						if(pid == 'select' || pid == ""){
							$("#query"+(queryCount-1)).html('');
							$("#query"+(queryCount)).remove();
							queryCount--;
						}else{
						var ret = confirm("Do you want to delete last row?");	
						if(ret == true){
							deleteLastTask(document.getElementById("taskMappingList["+(queryCount-1)+"].projectId").value,document.getElementById("taskMappingList["+(queryCount-1)+"].taskId").value,document.getElementById("weeklyDatePicker").value);
							$("#query"+(queryCount-1)).html('');
							$("#query"+(queryCount)).remove();
							queryCount--;
						}else{
							return false;
						}	
				     }
					} 
				});
	  /* $('#userSetting').click(function() {
		  openUserProfileDiv();
	  }); */  
	$('#submitTime').click(function() {
							//validateTimesheetForm();
							if($("input[id='weeklyDatePicker']").val() == ""){
								alert("Please select valid week.");
								return false;
							}
							var totalRow = $('#tab_logic1 tr').length - 1;
							var arrPid = [ ];
							var arrTaskId = [ ];
							for (var i = 0;i<totalRow; i++) {
								var tempPId = document.getElementById('taskMappingList['+ i +'].projectId').value;
								if(tempPId == undefined || tempPId == null || tempPId == "select"){
									   alert("Please select valid project");
									   return false;
								   }							   
								 var temptaskId = document.getElementById('taskMappingList['+ i +'].taskId').value;
								   if(temptaskId == undefined || temptaskId == null || temptaskId == "select"){
									   alert("Please select valid task");
									   return false;
								   }
								 arrPid.push(tempPId);
								 arrTaskId.push(temptaskId);
							}
							for(var i=0;i<arrPid.length;i++){
								var j= i+1;
								for(j;j<arrPid.length;j++){
									if(arrPid[i] == arrPid[j] && arrTaskId[i] == arrTaskId[j]){
										alert("No two rows can have same Project and task.");
										return false;
									}
								}
							}
							for (var i = 0;i<totalRow; i++) {
							   var tempDay1Effort = document.getElementById('taskMappingList['+ i +'].effortInHoursDay1').value;
							   if(tempDay1Effort == undefined || tempDay1Effort == null || tempDay1Effort == "" || !(isInteger(tempDay1Effort))){
								   alert("Please enter valid time like 9");
								   return false;
							   }
							   var tempDay2Effort = document.getElementById('taskMappingList['+ i +'].effortInHoursDay2').value;
							   if(tempDay2Effort == undefined || tempDay2Effort == null || tempDay2Effort == "" || !(isInteger(tempDay2Effort))){
								   alert("Please enter valid time like 9");
								   return false;
							   }
							   var tempDay3Effort = document.getElementById('taskMappingList['+ i +'].effortInHoursDay3').value;
							   if(tempDay3Effort == undefined || tempDay3Effort == null || tempDay3Effort == "" || !(isInteger(tempDay3Effort))){
								   alert("Please enter valid time like 9");
								   return false;
							   }
							   var tempDay4Effort = document.getElementById('taskMappingList['+ i +'].effortInHoursDay4').value;
							   if(tempDay4Effort == undefined || tempDay4Effort == null || tempDay4Effort == "" || !(isInteger(tempDay4Effort))){
								   alert("Please enter valid time like 9");
								   return false;
							   }
							   var tempDay5Effort = document.getElementById('taskMappingList['+ i +'].effortInHoursDay5').value;
							   if(tempDay5Effort == undefined || tempDay5Effort == null || tempDay5Effort == "" || !(isInteger(tempDay5Effort))){
								   alert("Please enter valid time like 9");
								   return false;
							   }
							   var tempDay6Effort = document.getElementById('taskMappingList['+ i +'].effortInHoursDay6').value;
							   if(tempDay6Effort == undefined || tempDay6Effort == null || tempDay6Effort == "" || !(isInteger(tempDay6Effort))){
								   alert("Please enter valid time like 9");
								   return false;
							   }
							   var tempDay7Effort = document.getElementById('taskMappingList['+ i +'].effortInHoursDay7').value;
							   if(tempDay7Effort == undefined || tempDay7Effort == null || tempDay7Effort == "" || !(isInteger(tempDay7Effort))){
								   alert("Please enter valid time like 9");
								   return false;
							   }
							}
							 for(var l=1;l<8;l++){
								var tempPerDayEffort = Number("0");
								var rows1 = $('#tab_logic1 tr');
								for (var i = 0;i<rows1.length-1; i++) {
									var tempDEffort = document.getElementById("taskMappingList["+ i +"].effortInHoursDay"+l).value;
									tempPerDayEffort = tempPerDayEffort + Number(tempDEffort);
								}
								if(tempPerDayEffort > 24){
									alert("Per day effort must not exceed 24 Hours");
									return false;
								}
							} 
							var rows = $('#tab_logic1 tr');
							for(var i=1;i<rows.length;i++){
								var dayCounter = 0;
								for(var k=1;k<8;k++){
									var currentWeek = $("#weeklyDatePicker").val();
									var temp = moment(currentWeek, "YYYY-MM-DD").day(dayCounter).format("YYYY-MM-DD");
									$("input[id='taskMappingList["+(i-1)+"].day"+k+"']").val(temp);
									dayCounter = dayCounter+1;
								}
							}
							if(calculateTotalWeekEffort() < 45){
								var inputTotal = calculateTotalWeekEffort(); 
								alert("Total week effort must be atleast 45 hours.Total Input Efort = "+inputTotal);
								return false;
							}
							var ret = confirm("Total effort = "+ calculateTotalWeekEffort() + "? You can not change any data after submission.");
							if (ret == true) {
								var x = document.getElementById("regform");
								x.action = "submitTimeSheetAction";
								x.submit();
							} else {
								return false;
							}
						});
	$('#saveOnlyTime').click(function() {
		//validateTimesheetForm();
		if($("input[id='weeklyDatePicker']").val() == ""){
			alert("Please select valid week.");
			return false;
		}
		var totalRow = $('#tab_logic1 tr').length - 1;
		var arrPid = [ ];
		var arrTaskId = [ ];
		for (var i = 0;i<totalRow; i++) {
			var tempPId = document.getElementById('taskMappingList['+ i +'].projectId').value;
			   if(tempPId == undefined || tempPId == null || tempPId == "select"){
				   alert("Please select valid project");
				   return false;
			   }							   
			 var temptaskId = document.getElementById('taskMappingList['+ i +'].taskId').value;
			   if(temptaskId == undefined || temptaskId == null || temptaskId == "select"){
				   alert("Please select valid task");
				   return false;
			   }
			 arrPid.push(tempPId);
			 arrTaskId.push(temptaskId);
		}
		for(var i=0;i<arrPid.length;i++){
			var j= i+1;
			for(j;j<arrPid.length;j++){
				if(arrPid[i] == arrPid[j] && arrTaskId[i] == arrTaskId[j]){
					alert("No two rows can have same Project and task.");
					return false;
				}
			}
		}
		 for(var l=1;l<8;l++){
				var tempPerDayEffort = Number("0");
				var rows1 = $('#tab_logic1 tr');
				for (var i = 0;i<rows1.length-1; i++) {
					var tempDEffort = document.getElementById("taskMappingList["+ i +"].effortInHoursDay"+l).value;
					tempPerDayEffort = tempPerDayEffort + Number(tempDEffort);
				}
				if(tempPerDayEffort > 24){
					alert("Per day effort must not exceed 24 Hours");
					return false;
				}
			} 
		for (var i = 0;i<totalRow; i++) {
		   var tempDay1Effort = document.getElementById('taskMappingList['+ i +'].effortInHoursDay1').value;
		   if(tempDay1Effort == undefined || tempDay1Effort == null || tempDay1Effort == "" || !(isInteger(tempDay1Effort))){
			   alert("Please enter valid time like 9");
			   return false;
		   }
		   var tempDay2Effort = document.getElementById('taskMappingList['+ i +'].effortInHoursDay2').value;
		   if(tempDay2Effort == undefined || tempDay2Effort == null || tempDay2Effort == "" || !(isInteger(tempDay2Effort))){
			   alert("Please enter valid time like 9");
			   return false;
		   }
		   var tempDay3Effort = document.getElementById('taskMappingList['+ i +'].effortInHoursDay3').value;
		   if(tempDay3Effort == undefined || tempDay3Effort == null || tempDay3Effort == "" || !(isInteger(tempDay3Effort))){
			   alert("Please enter valid time like 9");
			   return false;
		   }
		   var tempDay4Effort = document.getElementById('taskMappingList['+ i +'].effortInHoursDay4').value;
		   if(tempDay4Effort == undefined || tempDay4Effort == null || tempDay4Effort == "" || !(isInteger(tempDay4Effort))){
			   alert("Please enter valid time like 9");
			   return false;
		   }
		   var tempDay5Effort = document.getElementById('taskMappingList['+ i +'].effortInHoursDay5').value;
		   if(tempDay5Effort == undefined || tempDay5Effort == null || tempDay5Effort == "" || !(isInteger(tempDay5Effort))){
			   alert("Please enter valid time like 9");
			   return false;
		   }
		   var tempDay6Effort = document.getElementById('taskMappingList['+ i +'].effortInHoursDay6').value;
		   if(tempDay6Effort == undefined || tempDay6Effort == null || tempDay6Effort == "" || !(isInteger(tempDay6Effort))){
			   alert("Please enter valid time like 9");
			   return false;
		   }
		   var tempDay7Effort = document.getElementById('taskMappingList['+ i +'].effortInHoursDay7').value;
		   if(tempDay7Effort == undefined || tempDay7Effort == null || tempDay7Effort == "" || !(isInteger(tempDay7Effort))){
			   alert("Please enter valid time like 9");
			   return false;
		   }
		}
		var rows = $('#tab_logic1 tr');
		for(var i=1;i<rows.length;i++){
			var dayCounter = 0;
			for(var k=1;k<8;k++){
				var currentWeek = $("#weeklyDatePicker").val();
				var temp = moment(currentWeek, "YYYY-MM-DD").day(dayCounter).format("YYYY-MM-DD");
				$("input[id='taskMappingList["+(i-1)+"].day"+k+"']").val(temp);
				dayCounter = dayCounter+1;
			}
		}
		/* if(calculateTotalWeekEffort() < 45){
			var inputTotal = calculateTotalWeekEffort(); 
			alert("Total week effort must be atleast 45 hours.Total Input Efort = "+inputTotal);
			return false;
		} */
		var ret = confirm("Do you want to save?");
		if (ret == true) {
			var x = document.getElementById("regform");
			x.action = "saveTimeSheetAction";
			x.submit();
		} else {
			return false;
		}
	});
	$("#allocationDateForAsset").datetimepicker({ 
		format: 'YYYY-MM-DD',
		maxDate: new Date(),
		defaultDate: new Date(),
		widgetPositioning:{
            horizontal: 'auto',
            vertical: 'bottom'
        }
    });
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
	$("#from-datepicker-expense").datetimepicker({ 
		format: 'YYYY-MM-DD',
		maxDate: new Date()
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
		format: 'YYYY-MM-DD'
    });
    $("#from-datepicker-leave").on('dp.change', function (e) {
        var fromdate = $(this).val();
    });
    $("#to-datepicker-leave").datetimepicker({ 
		format: 'YYYY-MM-DD'
    });
    $("#to-datepicker-leave").on('dp.change', function (e) {
        var fromdate = $(this).val();
    });
	$("#from-datepicker").datetimepicker({ 
		format: 'YYYY-MM-DD'
    });
    $("#from-datepicker").on('dp.change', function (e) {
        var fromdate = $(this).val();
    });
    $("#to-datepicker").datetimepicker({ 
    	format: 'YYYY-MM-DD',
    	maxDate: new Date()
    });
    $("#to-datepicker").on('dp.change', function (e) {
        var todate = $(this).val();
    });
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
    	format: 'YYYY-MM-DD'
    });
    var datefrst = new Date();y = datefrst.getFullYear();m = datefrst.getMonth();
    var firstDay1 = new Date(y, m, 1);
    $("#timesheetFilterEndDate").datetimepicker({ 
    	format: 'YYYY-MM-DD',
    	maxDate: new Date(),
    	defaultDate: firstDay1
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
  $("#addUserLink").click(function(){
	  userMngtFunc();		  
  });
  $("#approveTimesheet").click(function(){
	  approveTimesheetFunc();
	  var value1 = formatDate(new Date());
      $("#weeklyDatePickerTimesheet").val('Filter By Week');
      getTimesheetFilteredData('Pending',"1");
  });
	  $('#meetingTime').datetimepicker({
	      /* sideBySide: true */
	  });
  $("#saveUserStatus").click(function(){
	  saveUserStatus();		  
  });
 /*  $("#usrRegNotfiFlag").change(function(){
	    if($(this).prop("checked") == true){
	       alert("check");
	    }else{
	    	alert("uncheck");
	    }
	}); */
});
</script>
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
    background-color: #808080;
}
.loader {
    border: 16px solid #f3f3f3; /* Light grey */
    border-top: 16px solid #3498db; /* Blue */
    border-radius: 50%;
    width: 60px;
    height: 60px;
    animation: spin 2s linear infinite;
    margin-top:200px;
}

@keyframes spin {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
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
<body oncontextmenu="return false;" onload="validateSession();return false;">
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="home">
				<span>
				Welcome::</span>${sessionScope.loggedInUserFullName}</a>
				<ul class="user-menu">
					<li class="dropdown pull-right">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg> User <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
						 		<li><a href="#!" onclick="editUserProfileOwn('${sessionScope.loggedInUser}');"><svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg> Edit Profile</a></li>
								<li><a href="#" onclick="changePwdModal();"><svg class="glyph stroked gear"><use xlink:href="#stroked-gear"></use></svg> Change Password</a></li>
								<li><a href="#" onclick="punchedTimeFromClientLoc();"><svg class="glyph stroked gear"><use xlink:href="#stroked-gear"></use></svg> Punched-In/Out</a></li> 
								<li><a href="logoutUser"><svg class="glyph stroked cancel"><use xlink:href="#stroked-cancel"></use></svg> Logout</a></li>
						</ul>
					</li>
				</ul>
				<ul class="user-menu pull-left">
				<c:if test="${sessionScope.loggedInUserDesignation != ''}">
    				<span style="color:white;font-size:15px;margin-right:10px;"><b style="color:#30a5ff;">Designation</b>::${sessionScope.loggedInUserDesignation}</span>
				</c:if>
				<c:if test="${sessionScope.loggedInUserProxy != ''}">
    				<span style="color:white;font-size:15px;margin-right:10px;">(Proxy login by::${sessionScope.loggedInUserProxy})</span>
				</c:if>
				</ul>
				<ul class="user-menu pull-right">
				<c:if test="${sessionScope.loggedInUserProxy != ''}">
    				<a href='backToMainLogin' class="backToMainLogin">Back to::${sessionScope.loggedInUserProxy}  </a> 
				</c:if>
				</ul>
			</div>
							
		</div><!-- /.container-fluid -->
	</nav>
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<ul class="nav menu">
		<!-- <li class="parent">
			<a href="home">
					<span data-toggle="collapse"><svg class="glyph stroked home"><use xlink:href="#stroked-home"/></svg></span>
					Home
				</a>
		</li> -->
		<c:if test="${sessionScope.loggedInUserAccessMap[FILL_TIMESHEET] eq true || sessionScope.loggedInUserAccessMap[TIMESHEET_APPROVAL] eq true || sessionScope.loggedInUserAccessMap[MONTHLY_REPORT] eq true || sessionScope.loggedInUserAccessMap[WEEKLY_REPORT] eq true}">
		<li class="parent active">
				<a href="#">
					<span data-toggle="collapse" href="#sub-item-11"><svg class="glyph stroked chevron-down active"><use xlink:href="#stroked-chevron-down"></use></svg>
					Timesheet</span>
				</a>
				 <ul class="children collapse" id="sub-item-11">
				 <c:if test="${sessionScope.loggedInUserAccessMap[FILL_TIMESHEET] eq true}">
					<li>
						<a href="home" id=""><svg class="glyph stroked calendar"><use xlink:href="#stroked-calendar"></use></svg>
						Fill Timesheet</a>
					</li>
				</c:if>	
					<%-- ${sessionScope.loggedInUserAccessMap[TIMESHEET_APPROVAL]} --%>
					<c:if test="${sessionScope.loggedInUserAccessMap[TIMESHEET_APPROVAL] eq true}">
						<li>
					   		<a href="#l" id="approveTimesheet"><svg class="glyph stroked clock"><use xlink:href="#stroked-clock"></use></svg>
					   		Timesheet Approval<sup id="approvalPendingCount" ><strong>0</strong></sup></a>
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
						<a href="#l" id="addUserLink"><svg class="glyph stroked male user"><use xlink:href="#stroked-female-user"></use></svg>
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
		<%-- <c:if test="${sessionScope.loggedInUserAccessMap[PROJECT_REPORT] eq true || sessionScope.loggedInUserAccessMap[WEEKLY_REPORT] eq true || sessionScope.loggedInUserAccessMap[REIMBURSEMENT_REPORT] eq true || sessionScope.loggedInUserAccessMap[MONTHLY_REPORT] eq true}">
		<li class="parent ">
				<a href="#">
					<span data-toggle="collapse" href="#sub-item-3"><svg class="glyph stroked chevron-down active"><use xlink:href="#stroked-chevron-down"></use></svg>
					Reports</span> 
				</a>
				 <ul class="children collapse" id="sub-item-3">
					<c:if test="${sessionScope.loggedInUserAccessMap[PROJECT_REPORT] eq true}">			
					<li>
						<a href="#l" id="" onclick="getReportParameter();"><svg class="glyph stroked table"><use xlink:href="#stroked-table"></use></svg>Project Report</a>
					</li>
					</c:if>
					<c:if test="${sessionScope.loggedInUserAccessMap[WEEKLY_REPORT] eq true}">	
					<li>	
						<a href="#l" id="" onclick="getReportParameterWeekly();"><svg class="glyph stroked table"><use xlink:href="#stroked-table"></use></svg>Weekly Report</a>
					</li>
					</c:if>
					<c:if test="${sessionScope.loggedInUserAccessMap[REIMBURSEMENT_REPORT] eq true}">	
					<li>	
						<a href="#l" id="" onclick="getReimbursementReoprtParam();"><svg class="glyph stroked table"><use xlink:href="#stroked-table"></use></svg>Reimbursement Report</a>
					</li>
					</c:if>
					<c:if test="${sessionScope.loggedInUserAccessMap[MONTHLY_REPORT] eq true}">
					<li>
							<a href="#l" id="" onclick="getUserReportParameter();"><svg class="glyph stroked pencil"><use xlink:href="#stroked-pencil"></use></svg>Monthly Report</a>
					</li>
					</c:if>
				</ul>
		</li>
		</c:if> --%>
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
							<a href="#l" onclick="editProject();"><svg class="glyph stroked table"><use xlink:href="#stroked-table"></use></svg>
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
					<li>
					   		<a href="#">
								<span data-toggle="collapse" href="#sub-item-22"><svg class="glyph stroked chevron-down active">
								<use xlink:href="#stroked-chevron-down"></use></svg>
								Report</span>
							</a>
							 <ul class="children collapse" id="sub-item-22">
							 	<c:if test="${sessionScope.loggedInUserAccessMap[PROJECT_REPORT] eq true}">			
								<li>
									<a href="#l" id="" onclick="getReportParameter();"><svg class="glyph stroked table"><use xlink:href="#stroked-table"></use></svg>Project Report</a>
								</li>
								</c:if>
							 </ul>
						</li>
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
						<a href="#l" onclick="showPendingExpenseReq();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-pencil"></use></svg>
						Pending Request<sup id="rmsPendingCount" ><strong>0</strong></sup></a>
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
					<li>
					   		<a href="#">
								<span data-toggle="collapse" href="#sub-item-21"><svg class="glyph stroked chevron-down active">
								<use xlink:href="#stroked-chevron-down"></use></svg>
								Report</span>
							</a>
							 <ul class="children collapse" id="sub-item-21">
							 <c:if test="${sessionScope.loggedInUserAccessMap[REIMBURSEMENT_REPORT] eq true}">	
								<li>	
									<a href="#l" id="" onclick="getReimbursementReoprtParam();"><svg class="glyph stroked table"><use xlink:href="#stroked-table"></use></svg>Reimbursement Report</a>
								</li>
							</c:if>
							 </ul>
						</li>
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
						<a href="#l" onclick="pendingLeaveList();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-pencil"></use></svg>
						Pending Request<sup id="leavePendingCount" ><strong>0</strong></sup></a>
					</li>
					</c:if>					
					<%-- <c:if test="${sessionScope.loggedInUserAccessMap[MANAGE_BUCKET] eq true}">
				     <li>
						<a href="#l" onclick="manageLeaveQuaterBand();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-pencil"></use></svg>
						Update Leave</a>
					</li>
					</c:if> --%>
					<c:if test="${sessionScope.loggedInUserAccessMap[LEAVE_CONFIGURATION] eq true}">
					 <li>
						<a href="#l" onclick="manageLeaveType();"><svg class="glyph stroked basket "><use xlink:href="#stroked-basket"/></svg>
						Leave Category</a>
					</li>
					</c:if>
		<c:if test="${sessionScope.loggedInUserAccessMap[LEAVE_BALANCE_UPDATION] eq true}">
			<li class="parent">
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
			</li>
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
						<a href="#l" onclick="addNewAsset();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						Add New Asset</a>
					</li>
				 </c:if>
				 <c:if test="${sessionScope.loggedInUserAccessMap[ALLOCATE_ASSET] eq true}">
					<li>
						<a href="#l" onclick="allocateAssetDiv();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						Allocate Asset</a>
					</li>
				 </c:if>
				 <c:if test="${sessionScope.loggedInUserAccessMap[TRACK_ASSET] eq true}">	
					<li>
						<a href="#l" onclick="trackAsset();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-table"></use></svg>
						Track Asset Allocation</a>
					</li>
				 </c:if>
				 <c:if test="${sessionScope.loggedInUserAccessMap[MODIFY_ASSET] eq true}">	
					<li>
						<a href="#l" onclick="modifyViewAsset();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-table"></use></svg>
						Manage Asset</a>
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
						Approve Attendance</a>
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
					<li>
						<a href="#l" onclick="viewExceptionAttendanceData();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-table"></use></svg>
						Exception Report</a>
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
		<c:if test="${sessionScope.loggedInUserAccessMap[GENERATE_EMPLOYEE_DOC] eq true}">
		<li class="parent ">
				<a href="#">
					<span data-toggle="collapse" href="#sub-item-10"><svg class="glyph stroked chevron-down active"><use xlink:href="#stroked-chevron-down"></use></svg>
					HRMS</span>
				</a>
				 <ul class="children collapse" id="sub-item-10">
				 <c:if test="${sessionScope.loggedInUserAccessMap[GENERATE_EMPLOYEE_DOC] eq true}">
					<li>
						<a href="#l" onclick="generateEmployeeDocuments();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-calendar"></use></svg>
						View Emp Doc</a>
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
		<c:if test="${sessionScope.loggedInUserAccessMap[FEEDBACK] eq true}">
		<li>
			<a href="#l" onclick="openFeedBackModal();"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-pencil"></use></svg>
			FeedBack</a>
		</li>
		</c:if>
		</ul>
	<div class="attribution">Provided by<br/><a href="http://www.supraits.com" style="color: #333;">Supra ITS, Noida</a></div>
	</div>
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main" id="dataDiv" style="overflow-x:hidden;">	
	</div>
	<div class="panel panel-primary" id="vieTimeShtDiv" aria-labelledby="vieTimeSht" style="width: 100%;display: none;margin-bottom:5px;" scrolling="auto">
			<div class="panel-heading">
				<strong>Timesheet</strong>
			</div>
		<div class="panel-body" style="padding-top:0px;">	
	        <c:if test="${not empty successMsg}">
			<script type="text/javascript">
				alert('${successMsg}');
			</script>
			</c:if>
			
			<c:if test="${not empty errorMsg}">
			<script type="text/javascript">
				alert('${errorMsg}');
			</script>
			</c:if>			
	<form:form role="form" id="regform" data-toggle="validator" method="post" modelAttribute="loginBean">
        <!-- <div class="container"> -->
			    <div class="row">
			        <div class="col-xs-6 panel panel-primary inline" id="viewLasttenWeekDiv" aria-labelledby="viewLasttenWeek"	style="width:60%;display: block;" scrolling="auto">
				<!-- <strong>Recent Timesheet Data </strong> -->
					<table class="table table-striped" id="userLasttenWeekTbl" scrolling="auto">
						<thead>
							<tr>
								<th>S.No.</th>
								 <th>Week(Sun-Sat)</th>
								 <th>Total Effort</th>
								 <th>Status</th>
							</tr>
						</thead>
						<tbody id="tab_logic_userLasttenWeekApp">
							<tr align="center" id='userApp1'></tr>
						</tbody>
					</table>
			        </div>
			        <div class="col-xs-6" style="margin-top: 5px;width:20%;">
			        <table class="table borderless" scrolling="auto">
						<tbody>
							<tr><td style="border-top:none;"><strong>Username:</strong></td></tr>
							<tr><td style="border-top:none;"><form:input id="username" path="username" style="border:none;" readOnly="true" value='${sessionScope.loggedInUser}'></form:input></td></tr>
							<tr><td style="border-top:none;"><strong>Reporting Manager:</strong></td></tr>
							<tr><td style="border-top:none;"><form:input id="reportingManager" path="reportingManager" style="border:none;" readOnly="true" value='${sessionScope.reportingManager}'></form:input></td></tr>
							<tr><td style="border-top:none;"><strong>HR Manager:</strong></td></tr>
							<tr><td style="border-top:none;"><form:input id="hrManager" path="hrManager" style="border:none;" readOnly="true" value='${sessionScope.hrManager}'></form:input></td></tr>
						</tbody>
					</table>
			        </div>
			    </div>
		<!-- </div> --> 
        <div class="col-sm-12 form-group">
        <%-- <form:label path="">Navigate Week</form:label>  --%>
        <a id='pastActionHistory' class="pull-right btn btn-primary" style="margin-right:10px;" onclick="showUserTimesheetHistory('${sessionScope.loggedInUser}');">Timesheet History</a>
        <a id='actionHistory' class="pull-right btn btn-primary" style="margin-right:10px;" onclick="showActionHistory();">Show Action History</a>
		<div class=" col-sm-4 input-group">
      			<span class="input-group-btn">
        			<button class="btn btn-secondary" type="button" id="prevWeek"><i class="fa fa-chevron-circle-left" style="font-size:22px"></i></button>
      			</span>
      				<input type="text" class="form-control" id='weeklyDatePickerShow' readOnly="true" style="text-align:center;" />
      				<form:hidden path="submittedWeek" id='weeklyDatePicker' />
      			<span class="input-group-btn">
        			<button class="btn btn-secondary" type="button" id="nextWeek"><i class="fa fa-chevron-circle-right" style="font-size:22px"></i></button>
      			</span>
      	</div>
      </div>
      	<div class="row">
      		<!-- <span style="margin:17px;color:red;">*Accepted Values: 7.5, 8.5, 9, 10, 11.5, <= 24.</span><br />
      		<span style="margin:17px;color:red;">*Once submitted no modification allowed unless rejected by Manager.</span><br /> -->
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 column">
				<table id="piotable" class="table table-bordered table-hover" id="tab_logic" style="margin-bottom:0px;">
					<thead>
						<tr>
							<th class="text-center" style="width:20%;">
								Project List
							</th>
							<th class="text-center" style="width:20%;">
								Task List
							</th>
							<th class="text-center success" id="weekDay1">
								Sun
							</th>
							<th class="text-center" id="weekDay2">
								Mon
							</th>
							<th class="text-center" id="weekDay3">
								Tue
							</th>
							<th class="text-center" id="weekDay4">
								Wed
							</th>
							<th class="text-center" id="weekDay5">
								Thu
							</th>
							<th class="text-center" id="weekDay6">
								Fri
							</th>
							<th class="text-center success"  id="weekDay7">
								Sat
							</th>
						</tr>
					</thead>
					<tbody id="tab_logic1">
						<tr id='query0'>
						<td>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
							<select class="form-control" name="taskMappingList[0].projectId" id="taskMappingList[0].projectId" onchange="getTaskListOnSelect(this.value,this.id)" >
								<option value="select"  selected="selected">Select Project</option>
							</select>
							<div class="help-block with-errors"></div>
		                 </div>
						</td>
							<td>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
							<select class="form-control" name="taskMappingList[0].taskId" id="taskMappingList[0].taskId">
							<option value="select"  selected="selected">Select Task</option>	
							</select>
							<div class="help-block with-errors"></div>
		                 </div>
							</td>
						<td class="success">
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
								<i class="fa fa-comments-o" aria-hidden=""></i>
                                  <input type="text" name="taskMappingList[0].effortInHoursDay1" id="taskMappingList[0].effortInHoursDay1" value='0' class="form-control"/>
                                  <input type="hidden" name="taskMappingList[0].day1" id="taskMappingList[0].day1"/>
                                  <span class="input-group-addon" style="padding: 0px;background:white;">
									<button type="button" class="fa fa-comments-o" style="border: none;background:white;" id="taskMappingList[0].day1B" onclick="addCmt(this.id)"></button>
								  </span><input type="hidden" name="taskMappingList[0].day1Comment" id="taskMappingList[0].day1Comment"/>
							</div>
						</td>
						<td>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
                                  <input type="text" name="taskMappingList[0].effortInHoursDay2" id="taskMappingList[0].effortInHoursDay2" value='0' class="form-control"/>
                                  <input type="hidden" name="taskMappingList[0].day2" id="taskMappingList[0].day2"/>
                                  <span class="input-group-addon" style="padding: 0px;background:white;">
									<button type="button" class="fa fa-comments-o" style="border: none;background:white;" id="taskMappingList[0].day2B" onclick="addCmt(this.id)"></button>
								  </span><input type="hidden" name="taskMappingList[0].day2Comment" id="taskMappingList[0].day2Comment"/>
							</div>
						</td>
						<td>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
                                  <input type="text" name="taskMappingList[0].effortInHoursDay3" id="taskMappingList[0].effortInHoursDay3" value='0' class="form-control"/>
                                  <input type="hidden" name="taskMappingList[0].day3" id="taskMappingList[0].day3"/>
                                  <span class="input-group-addon" style="padding: 0px;background:white;">
									<button type="button" class="fa fa-comments-o" style="border: none;background:white;" id="taskMappingList[0].day3B" onclick="addCmt(this.id)"></button>
								  </span><input type="hidden" name="taskMappingList[0].day3Comment" id="taskMappingList[0].day3Comment"/>
							</div>
						</td>
						<td>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
                                  <input type="text" name="taskMappingList[0].effortInHoursDay4" id="taskMappingList[0].effortInHoursDay4" value='0' class="form-control"/>
                                  <input type="hidden" name="taskMappingList[0].day4" id="taskMappingList[0].day4"/>
                                  <span class="input-group-addon" style="padding: 0px;background:white;">
									<button type="button" class="fa fa-comments-o" style="border: none;background:white;" id="taskMappingList[0].day4B" onclick="addCmt(this.id)"></button>
								  </span><input type="hidden" name="taskMappingList[0].day4Comment" id="taskMappingList[0].day4Comment"/>
							</div>
						</td>
						<td>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
                                  <input type="text" name="taskMappingList[0].effortInHoursDay5" id="taskMappingList[0].effortInHoursDay5" value='0' class="form-control"/>
                                  <input type="hidden" name="taskMappingList[0].day5" id="taskMappingList[0].day5"/>
                                  <span class="input-group-addon" style="padding: 0px;background:white;">
									<button type="button" class="fa fa-comments-o" style="border: none;background:white;" id="taskMappingList[0].day5B" onclick="addCmt(this.id)"></button>
								  </span><input type="hidden" name="taskMappingList[0].day5Comment" id="taskMappingList[0].day5Comment"/>
							</div>
						</td>
						<td>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
                                  <input type="text" name="taskMappingList[0].effortInHoursDay6" id="taskMappingList[0].effortInHoursDay6" value='0' class="form-control"/>
                                  <input type="hidden" name="taskMappingList[0].day6" id="taskMappingList[0].day6"/>
                                  <span class="input-group-addon" style="padding: 0px;background:white;">
									<button type="button" class="fa fa-comments-o" style="border: none;background:white;" id="taskMappingList[0].day6B" onclick="addCmt(this.id)"></button>
								  </span><input type="hidden" name="taskMappingList[0].day6Comment" id="taskMappingList[0].day6Comment"/>
							</div>
						</td>
						<td class="success">
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
                                  <input type="text" name="taskMappingList[0].effortInHoursDay7" id="taskMappingList[0].effortInHoursDay7" value='0' class="form-control"/>
                                  <input type="hidden" name="taskMappingList[0].day7" id="taskMappingList[0].day7"/>
                                  <span class="input-group-addon" style="padding: 0px;background:white;">
									<button type="button" class="fa fa-comments-o" style="border: none;background:white;" id="taskMappingList[0].day7B" onclick="addCmt(this.id)"></button>
								  </span><input type="hidden" name="taskMappingList[0].day7Comment" id="taskMappingList[0].day7Comment"/>
							</div>
						</td>
						</tr>
					<tr id='query1'></tr>
					</tbody>
				</table>
				<table class="table table-bordered " id="perDayEffortItemTbl" scrolling="auto" style="margin-bottom:2px;">
					<tr>
					<td class="text-right" style="width:35%;background-color:#A6E4F7;"><strong>Total Effort:</strong></td>
					<td id="totalAllDayEffort" style="width:5%;background-color:#A6E4F7;color:red;font-weight: bolder;">0</td>
					<td id="totalDay1" class="success" style="width: 8.5714%;text-align:center;font-weight: bolder;">0</td>
					<td id="totalDay2" style="width: 8.5714%;background-color:#A6E4F7;text-align:center;font-weight: bolder;">0</td>
					<td id="totalDay3" style="width: 8.5714%;background-color:#A6E4F7;text-align:center;font-weight: bolder;">0</td>
					<td id="totalDay4" style="width: 8.5714%;background-color:#A6E4F7;text-align:center;font-weight: bolder;">0</td>
					<td id="totalDay5" style="width: 8.5714%;background-color:#A6E4F7;text-align:center;font-weight: bolder;">0</td>
					<td id="totalDay6" style="width: 8.5714%;background-color:#A6E4F7;text-align:center;font-weight: bolder;">0</td>
					<td id="totalDay7" class="success" style="width: 8.5714%;text-align:center;font-weight: bolder;">0</td>
					</tr>
				</table>
		</div>
	</div>
		<input id="adminRemark" name="adminRemark" readonly="true" class="form-control" type="text" placeholder="Approver Remark" style="margin:5px;" />
		<div class="inline" id="timeButtonDiv">
		<!-- <input id="remark" name="remark" class="form-control" type="text" placeholder="User comment if any" style="margin:5px;" /> -->
			<a id="add_row" class="btn btn-primary pull-left">Add Task Row</a>
			<a id='delete_row' class="pull-left btn btn-primary" style="margin-left:5px;">Delete Last Task</a>
			<a id='submitTime' class="pull-right btn btn-success" style="margin-right:10px;">Submit</a>
			<a id='saveOnlyTime' class="pull-right btn btn-primary" style="margin-right:10px;">Save</a><br />
		</div>
</form:form>
</div>
		<!-- <div id="actionHistoryDiv">
			<a id='actionHistory' class="pull-left btn btn-primary" style="margin-left:10px;" onclick="showActionHistory();">Show Action History</a><br />
		</div> -->
	</div>
							
		<div class="panel panel-primary" id="vieReportDiv" aria-labelledby="viewReport"	style="width: 100%;display: none;" scrolling="auto">
						<!-- <div class="row">
							<ol class="breadcrumb">
								<li><a href="#"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg></a></li>
								<li class="active"></li>
							</ol>
						</div> -->
						<div class="panel-heading">
							<strong>Timesheet Reports </strong>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:20px;">
										<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Project Name</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
											<!--<select	class="form-control" id="projectList">-->
												 <select class="form-control" id="projectList" onchange="getAllUserForReport('true','usrListInPrjct',this.id);"> 
													<option value="select" label="Select Project" selected />
													    <option value="P1" label="Project1" />
														<option value="P2" label="Project2" />
												</select>
											</span>
										</div>
									 	<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>User Name</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="usrListInPrjct">
													<option value="select" label="Select User" selected />
												</select>
											</span>
										</div>
									<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Start Date</strong></label> <br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<input type="text" id="from-datepicker" class="form-control"/>
											</span>
						</div>
						<div class="form-group" style="margin-top: 10px;margin-left:5px;">					
											<label><strong>End Date</strong></label><br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<input type="text" id="to-datepicker" class="form-control"/>
											</span>
												
						</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
											<label><a href="#"><button type="button"
														class="btn btn-primary" style="margin-top:30px;" onclick="getReport();">Report</button></a></label>
					</div>
					
					</div>
					</div>
					</div>
					  
					<div class="row">
		<div class="col-lg-4">
			<div class="panel panel-primary" id="downloadReportDiv" aria-labelledby="downloadReport"
					style="width: 100%;display: none;" scrolling="auto">
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
					<strong>Generated Time:</strong><br /><span id="fileNameReportTime" style="color:red;"></span>
					</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
					<strong>Timesheet Report:</strong><br /><span id="fileNameReport" style="color:red;"></span>
					</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
						<label><a href="files"><button type="button" class="btn btn-primary" style="margin-top:30px;">Download</button></a></label>
					</div>
			</div>
		</div>
	</div>
	</div>
	
			
<div id="loaderGif" class="loader" style="display:none;margin-left: 50%;margine-top:15%;"></div>
	<div class="panel panel-primary" id="addUserDiv" aria-labelledby="addUserDiv"	style="width: 100%;display: none;" scrolling="auto">
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    		<strong>Approve pending users </strong>
			 	</div>
			 <div class="panel-body">
			 <!-- <div id="userListFilter" class="form-group pull-right"> -->
											<strong>Choose Filter:</strong> 
												<select	class="form-control" style="width:auto;display:inline;" id="userFilter" onchange="addUserFilterFunc(this.value,'')">
														<option value="All" label="All User" selected>All User</option>
													    <option value="Created" label="Pending">Pending</option>
														<option value="Active" label="Active">Active</option>
														<option value="Disable" label="Disable">Disable</option>
														<option value="Deleted" label="Deleted">Deleted</option>
												</select>
											<strong>Filter by Name:</strong> 	
												<input class="input-sm  textinput textInput form-control" style="width:20%;display:inline" id="userFilterByName" placeholder="Employee Name" onkeyup="filterAnyTableByInput(this.value,'pendingUserItemTbl','1');">
											<strong>Filter by Reporting Manager:</strong> 	
												<input class="input-sm  textinput textInput form-control" style="width:20%;display:inline" id="userRMFilterByName" placeholder="Reporting Manager" onkeyup="filterAnyTableByInput(this.value,'pendingUserItemTbl','6');">
					</div>
	    		</div>
			</div>
			 	<div id="pendingUserItemDiv" aria-labelledby="pendingUserItems"
					style="margin-top: 10px; width: 100%;display: none;" scrolling="auto">
					<table class="table table-bordered" style="margin:5px;" id="pendingUserItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th class="text-center">S No</th>
								<th class="text-center">Name</th>
								<th class="text-center">E-Mail</th>
								<th class="text-center">Role</th>
								<th class="text-center">Group</th>
								<th class="text-center">Status</th>
								<th class="text-center">Reporting Manager</th>
								<th class="text-center">Reporting HR</th>
								<th class="text-center">Update</th>
							</tr>
						</thead>
						<tbody id="tab_logic_pendingUserItemApp">
							<tr align="center" id='userApp2'></tr>
						</tbody>
					</table>
				</div>
			    	
			<div class="panel panel-primary" id="viewApproveTimeDiv" aria-labelledby="viewApproveTimeDiv"	style="width: 100%;display: none;" scrolling="auto">
						<!-- <div class="row">
							<ol class="breadcrumb">
								<li><a href="#"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg></a></li>
								<li class="active"></li>
							</ol>
						</div> -->
						<div class="panel-heading">
							<strong>Approve/Reject User Timesheet </strong>
						</div>
							<div class="table-responsive" style="padding-top:15px;">
								<div class="form-inline" style="margin-left:10px;">
								<c:if test = "${sessionScope.loggedinusergroup eq 'Admin'}">
								<div class="form-group" id="timesheetStatusFilterByUserName" style="margin-left:15px;">
									<label><strong>Filter By:</strong></label><br />
									<input type="radio" name="allUserPendingTS" value='All' checked="true">
									<label>All</label>
									<input type="radio" name="allUserPendingTS" value='${sessionScope.loggedInUser}'>
									<label>Direct Reportee</label>
								</div>
							</c:if>
									<div class="form-group" id="timesheetStatusFilter" style="margin-left:15px;">
										<label><strong>Status:</strong></label><br />
										<span>
											<select class="form-control" id="timesheetFilter">
												<option value="All" label="All">All</option>
											    <option value="Pending" label="Pending" selected>Pending</option>
											    <option value="Rejected" label="Rejected">Rejected</option>
												<option value="Approved" label="Approved">Approved</option>
												<option value="Saved" label="Saved">Saved</option>
											</select>
										</span>	
									</div>
						<div class="form-group" id="timesheetStatusFilterByUserName" style="margin-left:15px;">
							<label><strong>Employee Name:</strong></label><br />
							<span>
							<input class="input-md  textinput textInput form-control" id="tmshtFilterByName" placeholder="Employee Name">
							</span>
						</div>
						<div class="form-group" style="margin-left:15px;">
							<label><strong>Start Date:</strong></label><br />
							<span>
							 <input class="input-md  textinput textInput form-control" id="timesheetFilterStartDate" placeholder="Start Date">
							</span>
						</div>
						<div class="form-group" style="margin-left:15px;">
							<label><strong>End Date:</strong></label><br />
							<span>
							 <input class="input-md  textinput textInput form-control" id="timesheetFilterEndDate" placeholder="End Date">
							</span>
						</div>
						<div class="form-group" style="margin-left:15px;">
							<br />
							<span><a href="#!" onclick="fetchByParam('Pending','1');return false;" class="btn btn-primary pull-left" style="margin:5px;">Go</a></span>
						</div>
					</div>
					</div>
			
			</div>
			<div id="userDataItemDiv" aria-labelledby="userTimeItems"
					style="margin-top: 2px; width: 100%;display: none;" scrolling="auto">
					<div>
					<span><a href="#!" onclick="approveSelectedTimesheet();" class="btn btn-primary pull-left" style="margin:5px;">Approve Selected</a></span>
					<span class="pull-right">
					<!-- <span class='glyphicon glyphicon-backward' style='margin-right: 10px;border:1px solid;border-radius:3px;'></span> -->
						<Button title='1' id="firstButton" class="btn-sm" onclick="goToPage(this.id);" style="border:none;background-color:#5bc0de;color:#fff;">First</Button>
						<Button title='0' id="prevButton" class="btn-sm" onclick="goToPage(this.id);" style="border:none;background-color:#5bc0de;color:#fff;">Prev</Button>
						<Button title='2' id="nextButton" class="btn-sm" onclick="goToPage(this.id);" style="border:none;background-color:#5bc0de;color:#fff;">Next</Button>
						<Button title='3' id="lastButton" class="btn-sm" onclick="goToPage(this.id);" style="border:none;background-color:#5bc0de;color:#fff;">Last</Button>
					</span>					
					</div>
					<table class="table table-bordered" id="userItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th>#</th>
								<th class="text-center">User Name</th>
						        <th class="text-center">Full Name<a href="#" onclick="sortTable('userItemTbl',2)"><i class="fa fa-fw fa-sort"></i></a></th>
								<th class="text-center">Total Effort</th>
								 <th class="text-center">Week(Sun-Sat)</th>
								<th class="text-center">Status</th>
								<th class="text-center">Action</th>
							</tr>
						</thead>
						<tbody id="tab_logic_userItemApp">
							<tr align="center" id='userApp1'></tr>
						</tbody>
					</table>
			</div>
				<div class="modal fade" id="myModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Update User's Role,Group and Status</h4>
				        </div>
				        <div class="modal-body">
				        <div class="form-group">
				          <label>User Id:</label><span id="uid" style="color:red;margin-left:10px;">dddddd</span>
				        </div>
				        <div class="form-group">  
				          <label>Group*</label>
				          <select class="form-control" id="userGrp">
								<option value="Admin" label="Admin">Admin</option>
							    <option value="Approver" label="Approver">Approver</option>
								<option value="Owner" label="Project Owner">Project Owner</option>
								<option value="User" label="User" selected>User</option>
						</select>
						</div>												
						<div class="form-group">						
				          <label>Role*</label>
				          <select class="form-control" id="usrRoleFilter">
								<option value="User" label="User" selected>User</option>
							    <option value="Developer" label="Developer">Developer</option>
								<option value="Manager" label="Manager">Manager</option>
								<option value="Designer" label="Designer">Designer</option>
								<option value="Tester" label="Tester">Tester</option>
								<option value="HR" label="HR">HR</option>
								<option value="Accountant" label="Accountant">Accountant</option>
						</select>
						</div>
						<div class="form-group">						
				          <label>User Status*</label>
				          <select	class="form-control" id="userStat">
									<option value="Active" label="Active" selected>Active</option>
									<option value="Disable" label="Disable">Disable</option>
									<option value="Deleted" label="Delete">Delete</option>
							</select>
						</div>
						<div class="form-group">						
				          <label>Reporting Manager*</label>
				          <select class="form-control" id="userReportee">
														<option value="" label="Select" selected/>
												</select>
						</div>
						<div class="form-group">						
				          <label>HR Manager*</label>
				          <select class="form-control" id="userHRReportee">
							 <option value="" label="Select" selected/>
						  </select>
						</div>
						<div class="form-group">						
				          <label>Comment</label>
				          <input type = "text" class="form-control" maxlength="100" value="" id="approverComment" placeholder="Remark"/>
				        </div>
				        </div>
				        <div class="modal-footer">
	    			      <button type="button" class="btn btn-success" id="saveUserStatus">Save</button>
				          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div>
				      </div>
				    </div>
				  </div>
	 <div id="viewDModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="classInfo" aria-hidden="true">
		  <div class="modal-dialog modal-lg">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title" id="classModalLabel">
		              View Only: Weekly Timesheet Data
		            </h4>
		      </div>
		      <div class="modal-body">
		      	<!-- <strong>Project:</strong><span id="prjctName"></span><br /> -->
		      	<strong>User Name:</strong><span id="userName11" style="margin-left:10px;"></span><br />
		      	<strong>Week(Status):</strong><span id="statusWeek" style="margin-left:10px;"></span><br />
		      	<strong>User Remark:</strong><br /><span id="usrRemark" style="color:red;"></span><br />
		      	<strong>Remarks(add remark if any):</strong>
		      	<input type="text" id="approverRemark11" placeholder="Approver remark."/>
		      	<input type="hidden" id="frstDayDate11" />
		      	<input type="hidden" id="lastDayDate11" />
		      	<input type="hidden" id="userName12" />
		      	<button class= "btn btn-primary-sm" onclick="saveApproverRmrk();" style="margin:10px;">Save</button>
		        <table id="userPrjctEffortTbl" class="table table-bordered">
		        
		          <thead>
		          <tr>
		          			<th class="text-center">
								Project Name
							</th>
							<th class="text-center">
								Task Name
							</th>
							<th class="text-center success" id="weekDay11">
								Sun
							</th>
							<th class="text-center" id="weekDay22">
								Mon
							</th>
							<th class="text-center" id="weekDay33">
								Tue
							</th>
							<th class="text-center" id="weekDay44">
								Wed
							</th>
							<th class="text-center" id="weekDay55">
								Thu
							</th>
							<th class="text-center" id="weekDay66">
								Fri
							</th>
							<th class="text-center success"  id="weekDay77">
								Sat
							</th>
						</tr>
		          </thead>
		          <tbody id="tab_logic_userPrjctEffortApp">
		          </tbody>
		        </table>
		      </div>
		      <div class="modal-footer">
		        <button type="button" id='actionHistory' class="pull-left btn btn-info" style="margin-left:10px;" onclick="showActionHistoryView();"> Show Action History</button>
		        <button type="button" class="btn btn-primary" data-dismiss="modal">
		          Ok
		        </button>
		      </div>
		    </div>
		  </div>
</div>
	<div class="panel panel-primary" id="viewProjectDiv" aria-labelledby="viewProjectDiv"	style="width: 100%;display: none;" scrolling="auto">
						<!-- <div class="row">
							<ol class="breadcrumb">
								<li><a href="#"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg></a></li>
								<li class="active">Home</li>
							</ol>
						</div> -->
						<div class="panel-heading">
							<strong>Create New Project </strong>
						</div>
						<div class="panel-body">
							<div id="div_id_select" class="form-group required">
								<span style="color:red;font-size:14px;">*Kindly enable pop up before proceed.</span>
							</div>
							<div id="div_id_select" class="form-group required">
                            <label for="id_select"  class="control-label col-md-4  requiredField"> Project Type<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 "  style="margin-bottom: 10px">
                                <label class="radio-inline"><input type="radio" checked="checked" name="id_select_type" id="id_select_type" value="Internal"  style="margin-bottom: 10px">Internal</label>
                                <label class="radio-inline"> <input type="radio" name="id_select_type" id="id_select_type" value="Client"  style="margin-bottom: 10px">Client </label>
                            </div>
                       		 </div>
                       		 <div id="div_id_prjctName" class="form-group required">
                            <label for="id_prjctName" class="control-label col-md-4  requiredField"> Project Name<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 ">
                                <input class="input-md  textinput textInput form-control" id="id_prjctName" maxlength="100" name="projctName" placeholder="Choose Project Name" style="margin-bottom: 10px" type="text" />
                            </div>
                        	</div>
                        	<div id="div_id_projectDesc" class="form-group required">
                            <label for="id_projectDesc" class="control-label col-md-4  requiredField"> Description<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 ">
                                <input class="input-md  textinput textInput form-control" id="id_projectDesc" maxlength="250" name="projectDesc" placeholder="Project Description" style="margin-bottom: 10px" type="text" />
                            </div>
                            </div>
                            <div id="div_id_projectStart" class="form-group required">
                            <label for="id_projectDesc" class="control-label col-md-4  requiredField"> Actual/Planned Start Date<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 ">
                                <input type="text" id="from-datepickerProject" class="form-control" placeholder="Project start Date"/>
                            </div>
                            </div>
                            <div id="div_id_projectEnd" class="form-group required" >
                            <label class="control-label col-md-4  requiredField" style="margin-top:7px;"> Actual/Planned Completion Date<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8" style="margin-top:7px;">
                                <input type="text" id="to-datepickerProject" class="form-control" placeholder="Project end Date"/>
                            </div>
                            </div>
                            <div class="form-group"> 
                            	<div class="controls col-md-4 ">
                                	<input type="submit" name="submit-project" value="Create" style="margin-top:5px;" class="btn btn-primary btn btn-info pull-right" id="submit-project" onclick="submitDetailPrjct();"/>
                            	</div>
                        	</div> 
                        
						
						</div>
					</div>		
		<div class="panel panel-primary" id="editProjectDiv" aria-labelledby="editProjectDiv"	style="width: 100%;display: none;" scrolling="auto">
						<!-- <div class="row">
							<ol class="breadcrumb">
								<li><a href="#"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg></a></li>
								<li class="active">Home</li>
							</ol>
						</div> -->
						<div class="panel-heading">
							<strong>Edit Project Details </strong>
						</div>
						<div class="panel-body">
						<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:20px;">
										<div class="form-group" style="margin-top: 10px;margin-left:5px;">
						<strong>Filter:</strong>
							<select	class="form-control" id="projectStatusFilter" onchange="getProjectDetailsForEdit(this.value);">
								<option value="Active" label="Active" selected>Active</option>
							    <option value="Inactive" label="Inactive">Inactive</option>
							</select>
							</div>
							<div class="form-group" style="margin-top: 10px;margin-left:5px;">
							<strong>Search Project:</strong>
							<input class="input-sm  textinput textInput form-control" id="projectName1" placeholder="Project Name" onkeyup="filterAnyTableByInput(this.value,'editPrjctItemTbl','1');"/>
						</div>
						</div>
						</div>
						</div>
		</div>
		<div class="panel panel-primary" id="assignProjectDiv" aria-labelledby="assignProjectDiv"	style="width: 100%;display: none;" scrolling="auto">
						<!-- <div class="row">
							<ol class="breadcrumb">
								<li><a href="#"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg></a></li>
								<li class="active">Home</li>
							</ol>
						</div> -->
						<div class="panel-heading">
							<strong>Project Assignment </strong>
						</div>
						<div class="panel-body">
							
						</div>
		</div>
		<div id="editPrjctItemDiv" aria-labelledby="editPrjctItems"
					style="margin-top: 10px; width: 100%;display: none;" scrolling="auto">
					<table class="table table-bordered" id="editPrjctItemTbl" style="margin:5px;" scrolling="auto">
						<thead>
							<tr>
								<th>Project Id</th>
								<th class="text-center">Project Name</th>
								<th class="text-center">Project Owner</th>
								<th class="text-center">Start Date</th>
								<th class="text-center">Status</th>
								<th class="text-center">Project Type</th>
								<th class="text-center">Creation Date</th>
								<th class="text-center">Add Task</th>
								<th class="text-center">Operation</th>
							</tr>
						</thead>
						<tbody id="tab_logic_editPrjctItemApp">
							<tr align="center" id='editPrjctApp1'></tr>
						</tbody>
					</table>
				</div>
		<div id="assignPrjctItemDiv" aria-labelledby="assignPrjctItems"
					style="margin-top: 10px; width: 100%;display: none;" scrolling="auto">
					<table class="table table-bordered " id="assignPrjctItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th>Project Id</th>
								<th class="text-center">Project Name</th>
								<th class="text-center">Project Owner</th>
								<th class="text-center">Start Date</th>
								<th class="text-center">Status</th>
								<th class="text-center">Project Type</th>
								<th class="text-center">Creation Date</th>
								<th class="text-center">Add User</th>
							</tr>
						</thead>
						<tbody id="tab_logic_assignPrjctItemApp">
							<tr align="center" id='assignPrjctApp1'></tr>
						</tbody>
					</table>
				</div>	
				<div class="modal fade" id="addTaskModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Add Task to </h4>
				          <span id="id_prjctName1"></span>
				          <input type="hidden" id="pidValue" name="pidValue" />
				        </div>
				        <div class="modal-body">
				        <div class="form-group">
				          <label>Title:</label>
				          <input class="input-md  textinput textInput form-control" id="id_taskName" maxlength="50" minlength="6" name="taskName" placeholder="Task Name" style="margin-bottom: 10px" type="text" />
				        </div>
				        <div class="form-group">  
				          <label>Type</label>
				          <select id="id_taskType" class="form-control" name="taskType" style="margin-bottom: 10px">
				          		<option value="">Select Type</option>
				          </select>
				        </div>
				        <div class="form-group">  
				          <label>Description</label>
				          <input class="input-md  textinput textInput form-control" id="id_taskDesc" maxlength="200" name="taskDesc" placeholder="TaskDescription" style="margin-bottom: 10px" type="text" />
				        </div>
				        </div>
				        <div class="modal-footer">
	    			      <button type="button" class="btn btn-success" id="saveNewTask" onclick="insertNewTask();">Save</button>
				          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div>
				        </div>
				       </div>
				 </div>
				 <div class="modal fade" id="actionHistoryModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Action History</h4>
				        </div>
				        <div class="modal-body">
				     <table id="tmsActionHistoryTbl" class="table table-bordered">
		          		<thead>
		          		<tr>
		          			<th class="text-center">
								Action
							</th>
							<th class="text-center">
								Date
							</th>
							<th class="text-center">
								Action by
							</th>
							<th class="text-center">
								Remark
							</th>
						</tr>
		          </thead>
		          <tbody id="tab_logic_actionHistory">
		          </tbody>
		        </table>
				        </div>
				      </div>
				     </div>
				  </div>
				 <div class="modal fade" id="addCmtModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Add Comment </h4><span id="id_cmtId"></span>
				          <input type="hidden" id="cmtValueHidden" name="cmtValueHidden" />
				        </div>
				        <div class="modal-body">
				        <div class="form-group">
				          <label>Remark:</label>
				          <input class="input-md  textinput textInput form-control" id="idComment" maxlength="100" name="taskName" placeholder="Write Comment...max 100 character" style="margin-bottom: 10px" type="text" />
				        </div>
				      <div class="modal-footer">
	    			      <button type="button" class="btn btn-success" id="addNewCmt" onclick="captureCmt();">Ok</button>
				          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div>
				        </div>
				       </div>
				 </div>
				</div>  
				<!-- <div class="panel panel-primary" id="viewUserProfileDiv" aria-labelledby="viewUserProfileDiv"	style="width: 100%;display: none;" scrolling="auto">
						<div class="row">
							<ol class="breadcrumb">
								<li><a href="#"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg></a></li>
								<li class="active">Home</li>
							</ol>
						</div>
						<div class="panel-heading">
							<strong>Edit User Profile </strong>
						</div>
						<div class="panel-body">
							<div class="form-inline col-lg-12">
                            <label for="id_select"  class="control-label"> User Type<span class="asteriskField">*</span> </label>
                                <label class="radio-inline"><input type="radio" checked="checked" name="id_user_type" id="id_user_type" value="Internal"  style="margin-bottom: 10px">Internal</label>
                                <label class="radio-inline"> <input type="radio" name="id_user_type" id="id_user_type" value="Third_Party"  style="margin-bottom: 10px">Third_Party </label>
                       		 </div>
                       		  <div class="form-inline col-lg-12">
		                            <label class="control-label"> User Name<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_userName" maxlength="100"  style="margin-bottom: 10px" readOnly="true"/>
		                            <label class="control-label"> Employee Name<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_fullName" maxlength="250" name="projectDesc" style="margin-bottom: 10px" readOnly="true" />
		                             <label class="control-label"> Reporting Manager<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_fullName" maxlength="250" name="projectDesc" style="margin-bottom: 10px" readOnly="true" />   
		                      </div>
		                      <div class="form-inline col-lg-12">
		                            <label class="control-label">Assigned Project<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_userName" maxlength="100"  style="margin-bottom: 10px" readOnly="true"/>
		                      </div>
		                      <div class="form-inline col-lg-12">
		                            <label class="control-label"> Gender<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_userName" maxlength="100"  style="margin-bottom: 10px" readOnly="true"/>
		                            <label class="control-label"> DOB<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_fullName" maxlength="250" name="projectDesc" style="margin-bottom: 10px" readOnly="true" />
		                             <label class="control-label"> Joining Date<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_fullName" maxlength="250" name="projectDesc" style="margin-bottom: 10px" readOnly="true" />   
		                      </div>
                        	<div class="form-inline col-lg-12">
                        			<label class="control-label"> Group<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_userName" maxlength="100"  style="margin-bottom: 10px" readOnly="true"/>
		                            <label class="control-label"> Role<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_fullName" maxlength="250" name="projectDesc" style="margin-bottom: 10px" readOnly="true" />
		                             <label class="control-label"> E-mail<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_fullName" maxlength="250" name="projectDesc" style="margin-bottom: 10px" readOnly="true" />
                            </div>
                            <div class="form-inline col-lg-12">
                        			<label class="control-label"> Update Password<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_userName" maxlength="100"  style="margin-bottom: 10px" readOnly="true"/>
		                            <label class="control-label"> New Password<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_fullName" maxlength="250" name="projectDesc" style="margin-bottom: 10px" readOnly="true" />
		                             <label class="control-label"> Confirm Password<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_fullName" maxlength="250" name="projectDesc" style="margin-bottom: 10px" readOnly="true" />
                            </div>
                            <div class="form-group"> 
                            <div class="controls col-md-8 ">
                                <button class="btn btn-primary btn btn-info" id="updateProfileBtn" onclick="updateProfile();">Update Detail</button>
                            </div>
                        </div> 
					</div></div> -->
					<div class="modal fade" id="chngPwdModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Change Password </h4><span id="id_cmtId"></span>
				          <input type="hidden" id="cmtValueHidden" name="cmtValueHidden" />
				        </div>
				        <div class="modal-body">
				        <div class="form-group">
				          <label>Old Password:</label>
				          <input class="input-md  textinput textInput form-control" id="idOldPwd" maxlength="15"  placeholder="Old Password" style="margin-bottom: 10px" type="password" />
				        </div>
				        <div class="form-group">
				          <label>New Password:</label>
				          <input class="input-md  textinput textInput form-control" id="idNewPwd" maxlength="15" placeholder="Password length must be between 8 and 15 " style="margin-bottom: 10px" type="password" onkeyUp="matchPwd();" autocomplete="off" />
				        </div>
				        <div class="row">
						<div class="col-sm-6">
						<span id="8char" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> Min 8 Characters Long<br>
						<span id="ucase" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> One Uppercase Letter
						</div>
						<div class="col-sm-6">
						<span id="lcase" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> One Lowercase Letter<br>
						<span id="num" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> One Number
						</div>
						</div>
				        <div class="form-group"><br />
				          <label>Confirm Password:</label>
				          <input class="input-md  textinput textInput form-control" id="idNewConfPwd" maxlength="15"  placeholder="Confirm Password" style="margin-bottom: 10px" type="password" onkeyUp="matchPwd();" autocomplete="off"/>
				        </div>
				        <div class="row">
						<div class="col-sm-12">
						<span id="pwmatch" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> Passwords Match
						</div>
						</div>
				      <div class="modal-footer" style="margine-top:3px;">
	    			      <button type="button" class="btn btn-success" id="updateNewPwd" onclick="updateNewPwd();" data-loading-text="Changing Password..." value="Change Password">Update Password</button>
				          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div>
				        </div>
				       </div>
				 </div>
				</div>
				<div class="panel panel-primary" id="createUserDiv" aria-labelledby="createUserDiv"	style="width: 100%;display: none;" scrolling="auto">
						<div class="panel-heading">
							<strong>Create New User </strong>
						</div>
						<div class="panel-body">
							<div class="form-group" style="display:-webkit-box;margin-left: 14px;">
							<input type="text" id="userCreateId" tabindex="1" class="form-control" placeholder="Username...only alphanumeric" value="" onkeyup="checkUserNameAvailability1()" style="width: 80%;"/>
						<span class="mp2 nj211" id="user-availability-status" style="margin:20px;"></span>
					</div>
					<div class="form-group" style="display:-webkit-box;margin-left: 14px;">
						<input id="fCreateName" name="fCreateName" class="form-control" tabindex="2" placeholder="First Name" onkeyup="checkfNameAvailability()" style="width: 80%;" />
						<span class="mp2 nj211" id="user-fName-status" style="margin:20px;"></span>
					</div>
					<div class="form-group" style="display:-webkit-box;margin-left: 14px;">
						<input id="lCreateName" name="lCreateName" class="form-control" tabindex="3" placeholder="Last Name" onkeyup="checklNameAvailability()" style="width: 80%;"/>
						<span class="mp2 nj211" id="user-lName-status" style="margin:20px;"></span>
					</div>
					<div class="form-group" style="display:-webkit-box;margin-left: 14px;">
						<input type="email" id="userCreateMail" tabindex="4" class="form-control" placeholder="Email Address" value="" onkeyup="checkUserMailAvailability()" style="width: 80%;"/>
						<span class="mp2 nj211" id="user-mail-status" style="margin:20px;"></span>
					</div>
					<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-6">
						<select	class="form-control" id="userCreateGrp" tabindex="5">
							<option value="" label="Select User Group" selected />
						</select>
					</div>
					<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-6">
						<select	class="form-control" id="userAccessGrp" tabindex="5">
								<option value="" label="Select Access Group" selected />
						</select>
					</div>
					<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-6">	
				          <!-- <label>Role*</label> -->
				          <select class="form-control" id="usrCreateRole" tabindex="6">
				          								<option value="" label="Select User Role" selected>Select User Role</option>
														<option value="User" label="User">User</option>
													    <option value="Developer" label="Developer">Developer</option>
														<option value="Manager" label="Manager">Manager</option>
														<option value="Designer" label="Designer">Designer</option>
														<option value="Tester" label="Tester">Tester</option>
														<option value="HR" label="HR">HR</option>
														<option value="Accountant" label="Accountant">Accountant</option>
							</select>
						</div>
						<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-6">						
				          <!-- <label>User Status*</label> -->
				          <select	class="form-control" id="userCreateStatus" tabindex="7">
				          								<option value="" label="Select User Status" selected>Select User Status</option>
														<option value="Active" label="Active">Active</option>
														<option value="Disable" label="Disable">Disable</option>
														<option value="Deleted" label="Delete">Delete</option>
												</select>
				          <!-- <label>Reporting Manager*</label> -->
				         </div>
				         <div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-6"> 
				          <select	class="form-control" id="userCreateReportee" tabindex="8">
														<option value="" label="Select Reporting Manager" selected/>
						</select>
						</div>
						<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-6"> 
				          <select	class="form-control" id="userCreateHRReportee" tabindex="8">
														<option value="" label="Select HR Manager" selected/>
						</select>
						</div>
						<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-6">
						<select class="form-control" id="userCreatePolicyGroup">
							<option value="" label="HR Policy Group">HR Policy Group</option>
						</select>
						</div>		
						<!-- <div class="form-group">						
				          <input type = "text" class="form-control" maxlength="100" tabindex="9" id="approverCreateComment" placeholder="Admin remark...if any"/>
				        </div> -->
				        </div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-6 col-sm-offset-3">
								<input type="button" name="create-submit" id="add-user" tabindex="10" class="form-control btn-primary btn-register" onclick="addUserByAdmin();" value="Create User">
							</div>
						</div>
					</div>
				</div>
				<div class="panel panel-primary" id="vieMonthlyReportDiv" aria-labelledby="viewMonthlyReport"	style="width: 100%;display: none;" scrolling="auto">
						<div class="panel-heading">
							<strong>Monthly Timesheet Report </strong>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:20px;">
										<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Select Employee:</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
											<select class="form-control" id="userListForMonthlyReport" onchange="getUsersProjectList();">
													<option value="select" label="Select Employee" selected />
												</select>
											</span>
										</div>
									 	<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Project List</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="projectListOfUser">
													<option value="select" label="Select User" selected />
												</select>
											</span>
										</div>
									<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Year</strong></label> <br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="yearsForReport">
													<option value="2016" label="2016">2016</option>
													<option value="2017" label="2017" selected>2017</option>
													<option value="2018" label="2018">2018</option>
												</select>
											</span>
						</div>
						<div class="form-group" style="margin-top: 10px;margin-left:5px;">					
											<label><strong>Month</strong></label><br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="monthForReport">
													<option value="1" label="Jan">Jan</option>
													<option value="2" label="Feb">Feb</option>
													<option value="3" label="Mar">Mar</option>
													<option value="4" label="Apr">Apr</option>
													<option value="5" label="May">May</option>
													<option value="6" label="Jun">Jun</option>
													<option value="7" label="Jul">Jul</option>
													<option value="8" label="Aug">Aug</option>
													<option value="9" label="Sep">Sep</option>
													<option value="10" label="Oct">Oct</option>
													<option value="11" label="Nov">Nov</option>
													<option value="12" label="Dec">Dec</option>
												</select>
											</span>
												
						</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
											<label><a href="#"><button type="button"
														class="btn btn-primary" style="margin-top:30px;" onclick="getUserReportParameter1();">Report</button></a></label>
					</div>
					<br /><br /><span style="margin:17px;color:red;">*Project list of selected user, Only if timesheet data exist.</span>
					</div>
					</div>
					</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-primary" id="downloadMonthlyReportDiv" aria-labelledby="downloadMonthlyReport"
					style="width: 100%;display: none;" scrolling="auto">
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
					<strong>Generated Time:</strong><br /><span id="fileNameMonthlyReportTime" style="color:blue;"></span>
					</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
					<strong>Timesheet Report:</strong><br /><span id="fileNameMonthlyReport" style="color:blue;"></span>
					</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;"><input type='hidden' id='fileNameHidden' />
						<label><a href="monthlyFiles" class="getMonthlyReporthref" target="_blank" id="downloadMonthButton">
						<button type="button" class="btn btn-primary" style="margin-top:30px;">Download</button>
						</a></label>
						
					</div>
			</div>
		</div>
	</div>
	</div>		
	<div class="panel panel-primary" id="vieNotificationDiv" aria-labelledby="vieNotification"	style="width: 100%;display: none;" scrolling="auto">
						<div class="panel-heading">
							<strong>Email Notification Setting </strong>
						</div>
						<div class="panel-body" id="emailNotificationDiv">
								<!-- <table id="tmsNotificationTbl" class="table table-bordered">
									<thead style="background-color:#30a5ff;color:white;">
										<tr>
											<th>Event Id</th>
											<th>Name</th>
											<th>Module</th>
											<th>Current Flag</th>
										</tr>
									</thead>
									<tbody id="tmsNotificationTblBody">
									</tbody>
								</table> -->
						</div>
	</div>
	<div class="panel panel-primary" id="vieReportWeeklyDiv" aria-labelledby="viewReportWeekly"	style="width: 100%;display: none;" scrolling="auto">
						<!-- <div class="row">
							<ol class="breadcrumb">
								<li><a href="#"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg></a></li>
								<li class="active"></li>
							</ol>
						</div> -->
						<div class="panel-heading">
							<strong>Timesheet Weekly Status Reports </strong>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:20px;">
										<!-- <div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Project Name</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
											<select	class="form-control" id="projectList">
												 <select class="form-control" id="projectList" onchange="getAllUserForReport('true','usrListInPrjct');"> 
													<option value="select" label="Select Project" selected />
													    <option value="P1" label="Project1" />
														<option value="P2" label="Project2" />
												</select>
											</span>
										</div> -->
									 	<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>User Name</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="usrListInWeeklyReport">
													<option value="select" label="Select User" selected />
												</select>
											</span>
										</div>
									<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Start Date</strong></label> <br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<input type="text" id="from-datepickerWeekly" class="form-control"/>
											</span>
						</div>
						<div class="form-group" style="margin-top: 10px;margin-left:5px;">					
											<label><strong>End Date</strong></label><br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<input type="text" id="to-datepickerWeekly" class="form-control"/>
											</span>
												
						</div>
						<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Status</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="weeklyStatus">
													<option value="select" label="Select Status" selected>Select Status</option>
													<option value="Pending" label="Pending">Pending</option>
													<option value="Saved" label="Saved">Saved</option>
													<option value="Rejected" label="Rejected">Rejected</option>
													<option value="Approved" label="Approved">Approved</option>
												</select>
											</span>
						</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
											<label><a href="#"><button type="button"
														class="btn btn-primary" style="margin-top:30px;" onclick="getReportWeekly();">Generate Report</button></a></label>
					</div>
					
					</div>
					</div>
					</div>
					  
	<div class="row">
		<div class="col-lg-6">
			<div class="panel panel-primary" id="downloadWeeklyReportDiv" aria-labelledby="downloadWeeklyReport"
					style="width: 100%;display: none;" scrolling="auto">
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
					<strong>Generated Time:</strong><br /><span id="fileNameWeeklyReportTime" style="color:red;"></span>
					</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
					<strong>Timesheet Status Report:</strong><br /><span id="fileNameWeeklyReport" style="color:red;"></span>
					</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
						<label><a href="filesWeekly"><button type="button" class="btn btn-primary" style="margin-top:30px;">Download</button></a></label>
					</div>
			</div>
		</div>
	</div></div>	
	<div class="modal fade" id="addFeedbackModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Feedback/Suggestion/Issue </h4>
				        </div>
				        <div class="modal-body">
				        <div class="form-group">  
				          <label>Category</label>
				          <select id="feedbackType" class="form-control" style="margin-bottom: 10px">
				          		<option value="select" selected>Select Category</option>
				          		<option value="feedback">Feedback</option>
				          		<option value="suggestion">Suggestion</option>
				          		<option value="issue">Issue</option>
				          </select>
				        </div>
				        <div class="form-group">  
				          <label>Select Module</label>
				          <select id="moduleType" class="form-control" style="margin-bottom: 10px">
				          		<option value="select" selected>Select Module</option>
				          		<option value="timesheet">Timesheet</option>
				          		<option value="expense">Reimbursement</option>
				          		<option value="leave">Leave</option>
				          		<option value="other">Others</option>
				          </select>
				        </div>
				        <div class="form-group">  
				          <label>Description</label>
				          <textarea class="form-control" rows="5" id="feedBackDesc" maxlength="500" style="max-width:100%;margin-bottom: 10px" placeholder="Maximum length 500 character..."></textarea>
				        </div>
				        <input type="hidden" id="feedbackAttachmentId" value="" />
				        <div class="form-group">  
				          <form class="form-inline" method="POST" enctype="multipart/form-data" id="feedbackUploadForm" style="display:inline;">
								<input type="submit" class="btn btn-primary pull-right" title="Click to upload feedback attachment" value="Submit" onclick="uploadFeedbackAttachment();return false;" style="margin-right:5px;"/>
					 			<input type="file" name="file" onchange="valFeedbackUpload(this.value,id);" id="feedbackDocumentList" class="filestyle" style="display:inline;" data-buttonText="Upload Attachment"/>
					 		</form>
				         </div>
				         <br> 
				         <div>
				        	@Contact:<span style="margin:5px;color:red;">timesheet@supraits.com</span>
				        </div>	
				        </div>
				        <!-- <div class="modal-footer">
	    			      <button type="button" class="btn btn-success" onclick="insertNewFeedback();">Send</button>
				          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div> -->
				        </div>
				       </div>
	  </div>
	  <div class="panel panel-primary" id="viewApproveExpenseDiv" aria-labelledby="viewApproveExpenseDiv"	style="width: 100%;display: none;" scrolling="auto">
						<!-- <div class="row">
							<ol class="breadcrumb">
								<li><a href="#"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg></a></li>
								<li class="active"></li>
							</ol>
						</div> -->
						<div class="panel-heading">
							<strong>Approve/Reject User Reimbursement Request </strong>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;">
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Choose Filter:</strong></label><br />
							<span>
								<select class="form-control" id="expenseFilter" onchange="filterAnyTableByInput(this.value,'userExpenseItemTbl','6');">
								    <option value="" label="Select" selected>Select</option>
								    <option value="Review Pending" label="Review Pending">Review Pending</option>
								    <option value="Review Failed" label="Review Failed">Review Failed</option>
									<option value="Approval Pending" label="Approval Pending">Approval Pending</option>
									<option value="Rejected" label="Rejected">Rejected</option>
								</select>
							</span>	
						</div>
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Filter by Employee Name:</strong></label><br />
							<span>
							<input class="input-md  textinput textInput form-control" id="expenseFilterByName" placeholder="Employee Name" onkeyup="filterAnyTableByInput(this.value,'userExpenseItemTbl','2');">
							</span>
						</div>	
					
					</div>
					</div>
					</div>
			</div>
			<div class="panel panel-primary" id="viewBucketExpenseDiv" aria-labelledby="viewBucketExpenseDiv"	style="width: 100%;display: none;" scrolling="auto">
						<div class="panel-heading">
							<strong>Manage Buckets of Approved Reimbursement Request</strong>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
					<!-- <div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;"> -->
					<div class="form-group">
						<input type="hidden" id="expenseFilterBucket" value="0"/>
						<table style="border:none;width:100%;" id="bucketCountTbl">
							<tbody id="bucketCountDiv"></tbody>
						</table>	        
					 </div>
						<div class="form-inline form-group" id="" style="margin-left:15px;">
							<label><strong>Search by Request Number:</strong></label>
							<span>
							<input class="input-md  textinput textInput form-control" id="expenseFilterBucketByRequestNumber" placeholder="Request Number" onkeyup="filterAnyTableByInput(this.value,'userBucketExpenseItemTbl','2');">
							</span>
						</div>		
						<!-- <div class="form-group" style="margin-left:15px;">
							<label><strong>Filter By Bucket:</strong></label><br />
							<span>
								<select class="form-control" id="expenseFilterBucket" onchange="getBucketExpenseRequestList('Approved',this.value);">
								    <option value="0" label="All" selected/>
									<option value="Ready for payment" label="Ready for payment"/>
								    <option value="Document Verification Pending" label="Document Verification Pending" />
								    <option value="Document Verified" label="Document Verified" />
								    <option value="To be review" label="To be review" />
									<option value="Request Discarded" label="Request Discarded" />
									<option value="Request Completed" label="Request Completed" />
								</select>
							</span>	
						</div> -->
					<!-- </div> -->
					</div>
					</div>
			</div>
			<div class="panel panel-primary" id="viewOwnExpenseDiv" aria-labelledby="viewOwnExpenseDiv"	style="width: 100%;display: none;" scrolling="auto">
						<div class="panel-heading">
							<strong>User Past/Pending Reimbursement Request List </strong>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;">
						<div class="form-group" id="timesheetStatusFilter" style="margin-left:15px;">
							<label><strong>Choose Filter:</strong></label><br />
							<span>
								<select class="form-control" id="expenseFilter" onchange="filterAnyTableByInput(this.value,'userOwnExpenseItemTbl','8');">
								    <option value="" label="Select" selected>Select</option>
								    <option value="Review Pending" label="Review Pending">Review Pending</option>
								    <option value="Review Failed" label="Review Failed">Review Failed</option>
									<option value="Saved as Draft" label="Saved">Saved</option>
									<option value="Approval Pending" label="Approval Pending">Approval Pending</option>
									<option value="VP Approval Pending" label="VP Approval Pending">VP Approval Pending</option>
									<option value="VP Approval Failed" label="VP Approval Failed">VP Approval Failed</option>
									<option value="Rejected" label="Rejected">Rejected</option>
									<option value="Approved" label="Approved">Approved</option>
									<option value="Withdraw" label="Withdraw">Withdraw</option>
								</select>
							</span>	
						</div>
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Filter by Request Number:</strong></label><br />
							<span>
							<input class="input-md  textinput textInput form-control" id="expenseFilterByReqNum" placeholder="Request Number" onkeyup="filterAnyTableByInput(this.value,'userOwnExpenseItemTbl','1');">
							</span>
						</div>	
					
					</div>
					</div>
					</div>
			</div>
			<div id="userBucketExpenseItemDiv" aria-labelledby="userBucketExpenseItems"
					style="margin-top: 10px; width: 100%;display: none;" scrolling="auto">
					<div><strong><span id="currentBucketName" style="color:red;margin-left:10px;"></span></strong></div>
					<table class="table table-bordered" id="userBucketExpenseItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th class="text-center">#</th>
								<th class="text-center"><input type='checkbox' id="checkBucketid" onclick = "selectAllBucketCheckBox('tab_logic_userBucketExpenseItemApp')" /></th>
								<th class="text-center">Request Number</th>
								<th class="text-center">User Name</th>
								<th class="text-center">Created On</th>
								<th class="text-center">Claimed<br>(INR)</th>
								<th class="text-center">Approved<br>(INR)</th>
								<th class="text-center">Status</th>
								<th class="text-center">Bucket Name</th>
							</tr>
						</thead>
						<tbody id="tab_logic_userBucketExpenseItemApp">
						</tbody>
					</table>
					<div class="form-group" style="margin-left:15px;">
							<strong>Send To:</strong>
								<select class="form-control" id="expenseBucket" style="width:auto;display:inline;">
								    <option value="" label="Choose Bucket" selected/>
								</select>
							<button class="btn primary btn-sm" id="sendToBucketId" onclick="sendToBucket();return false;">Go</button>
							<button class="btn primary btn-sm pull-right" id="downloadBankSheetId" title="Click to download list" style="margin-right:10px;display:none;" onclick="downloadBucketReport();return false;">Download Sheet for Bank</button>
							<form class="form-inline pull-right" method="POST" enctype="multipart/form-data" id="bankFileUploadForm" style="display:none;">
							<button class="btn primary btn-sm" id="downloadBankRefNoSheetId" title="Click to download template" style="margin-right:10px;" onclick="downloadBankRefUpdateSheet();return false;">Download Bank Ref Sheet</button>
								<input type="submit" class="btn btn-primary pull-right" title="Click to upload Bank Ref Sheet" value="Upload" id="uploadBankSheetId" onclick="uploadBankRefUpdateSheet();return false;" style="margin-right:5px;"/>
					 			<input type="file" name="file" onchange="valBankRefSheetUpload(this.value,id);" id="rmsBankRefDocumentList" class="filestyle pull-right" style="display:inline;" data-buttonText="Upload Bank Ref Sheet"/>
					 		</form>
					</div>
			</div>
	       <div id="userExpenseItemDiv" aria-labelledby="userExpenseItems"
					style="margin-top: 10px; width: 100%;display: none;" scrolling="auto">
					<table class="table table-bordered" id="userExpenseItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th>#</th>
								<th class="text-center">Request Number</th>
								<th class="text-center">User Name</th>
								<th class="text-center">Created On</th>
								<th class="text-center">Claimed<br>(INR)</th>
								<th class="text-center">Approved<br>(INR)</th>
								<th class="text-center">Status</th>
								<th class="text-center">Action</th>
							</tr>
						</thead>
						<tbody id="tab_logic_userExpenseItemApp">
						</tbody>
					</table>
			</div>
			
			<div id="userOwnExpenseItemDiv" aria-labelledby="userOwnExpenseItems"
					style="margin-top: 10px; width: 100%;display: none;" scrolling="auto">
					<table class="table table-bordered" id="userOwnExpenseItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th>#</th>
								<th class="text-center">Request Number</th>
								<th class="text-center">Created On</th>
								<th class="text-center">Claimed<br>(INR)</th>
								<th class="text-center">Approved<br>(INR)</th>
								<th class="text-center">Reviewed By</th>
								<th class="text-center">Remark(Reviewer)</th>
								<th class="text-center">Remark(Approver)</th>
								<th class="text-center">Status</th>
								<th class="text-center">Action</th>
							</tr>
						</thead>
						<tbody id="tab_logic_userOwnExpenseItemApp">
						</tbody>
					</table>
			</div>  	
			<div class="modal fade" id="addExpRemarkModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Add Remark </h4>
				          <input type="hidden" id="remarkValueHiddenId" name="remarkValueHiddenId" value=''/>
				        </div>
				        <div class="modal-body">
				        <div class="form-group">
				          <label>Remark:</label>
				          <textarea class="input-md  textinput textInput form-control" id="idExpRmrk" maxlength="100" placeholder="Remarks...max 100 character" style="margin-bottom: 10px"></textarea>
				        </div>
				      <div class="modal-footer">
	    			      <button type="button" class="btn btn-success" id="addExpRemark" onclick="captureExpRemark();">OK</button>
				          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div>
				        </div>
				       </div>
				   </div>
			</div>		
			<div class="panel panel-primary" id="viewManageUserAccessDiv" aria-labelledby="viewManageUserAccessDiv"	style="width: 100%;display: none;" scrolling="auto">
						<div class="panel-heading">
							<strong>Manage User Access </strong>
						</div>
						<div class="panel-body">
					<div class="table-responsive">
					 <div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;">
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Select Access Group:</strong></label>
							<select class="form-control" id="userGroupList" style="width:auto;display:inline;" onchange="getAccessFunctionList(this.value);">
							</select>
						</div>		
						<br />
						<div class="form-group" id="accessList" style="margin:10px;width:100%">
						</div>
					</div>
					</div>
					</div>
			</div>
			<div class="panel panel-primary" id="viewGroupUserAccessDiv" aria-labelledby="viewGroupUserAccessDiv"	style="width: 100%;display: none;" scrolling="auto">
						<div class="panel-heading">
							<strong>Manage User Group </strong>
						</div>
						<div class="panel-body">
					<div class="table-responsive">
					 <div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;">
						<div class="form-group" id="" style="margin-left:15px;">
							<label>New Group Name:</label>
							<input type="text" class="form-control" id="newGroupName" maxlength="50" />
							<button class="btn-sm btn-primary" onclick="createGroupName();return false;">Create Group</button>
						</div>		
						<br />
						<div class="form-group" id="accessGroupList" style="margin:10px;width:100%">
						</div>
					</div>
					</div>
					</div>
			</div>
			<div class="panel panel-primary" id="viewRMSBucketDiv" aria-labelledby="viewRMSBucketDiv"	style="width: 100%;display: none;" scrolling="auto">
						<div class="panel-heading">
							<strong>Manage Reimbursement Bucket </strong>
						</div>
						<div class="panel-body">
					<div class="table-responsive">
					 <div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;">
						<div class="form-group" id="" style="margin-left:15px;">
							<label>New Bucket Name:</label>
							<input type="text" class="form-control" id="newBucketName" maxlength="50" />
							<button class="btn-sm btn-primary" onclick="createBucketName();return false;">Create Bucket</button>
						</div>		
						<br />
						<div class="form-group" id="bucketList" style="margin:10px;width:100%">
						</div>
					</div>
					</div>
					</div>
			</div>
			<div class="modal fade" id="addUsersToGroupModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Add Users to group:  <span id="id_groupName" style='color:red;'></span></h4>
				          <input type="hidden" id="id_groupId" />
				          <input type="hidden" id="id_addRemoveFlag" />
				        </div>
				        <div class="modal-body">
							<div class="form-group">
									    <label class="col-md-4 control-label" for="rolename">Users Email-Id</label>
									    <div id="checkboxSelectComboAddRemove"></div>
    									<input type ="hidden" id ="Hidden1"/>
							</div>
							
						</div>
						<div class="modal-footer">
						  <button type="button" class="btn btn-success" id="saveGrpUsers" onclick="addRemoveUsersFromGroup();return false;">Save</button>
						  <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
						</div>
						</div>
					   </div>
				</div>
				<div class="panel panel-primary" id="viewPastLeavesDiv" aria-labelledby="viewPastLeavesDiv"	style="width: 100%;display: none;" scrolling="auto">
						<div class="panel-heading">
							<strong>User Past/Pending Leave Request List </strong>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;">
						<div class="form-group" id="timesheetStatusFilter" style="margin-left:15px;">
							<label><strong>Choose Filter:</strong></label><br />
							<span>
								<select class="form-control" id="leaveFilter" onchange="filterAnyTableByInput(this.value,'userOwnLeaveItemTbl','4');">
								    <option value="" label="Select" label="All">All</option>
								    <option value="Approval Pending" selected label="Approval Pending">Approval Pending</option>
								    <option value="Approved" label="Approved">Approved</option>
									<option value="Rejected" label="Rejected">Rejected</option>
									<option value="Cancel" label="Canceled">Canceled</option>
									<option value="Leave Reversed" label="Leave Reversed">Leave Reversed</option>
								</select>
							</span>	
						</div>
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Filter by Request Number:</strong></label><br />
							<span>
							<input class="input-md  textinput textInput form-control" id="expenseFilterByRequestNo" placeholder="Request Number" onkeyup="filterAnyTableByInput(this.value,'userOwnLeaveItemTbl','1');">
							</span>
						</div>
						<div class="form-group" id="" style="margin-left:15px;">	
							<br>
							<a href="#l" onclick="newLeaveRequest();" class="pull-right btn btn-success">Add New Leave</a>
						</div>							
					</div>
					</div>
					</div>
			</div>
			<div id="userOwnLeaveItemDiv" aria-labelledby="userOwnLeaveItems"
					style="margin-top: 10px; width: 100%;display: none;" scrolling="auto">
					<table class="table table-bordered" id="userOwnLeaveItemTbl" scrolling="auto">
						<thead style='background-color: #30a5ff;color:white;'>
							<tr>
								<th>#</th>
								<th class="text-center">Request Number</th>
								<th class="text-center">Created On</th>
								<th class="text-center">Leave Days</th>
								<th class="text-center">Status</th>
								<th class="text-center">Remark(Approver)</th>
								<th class="text-center">Last Modified By</th>
								<th class="text-center">Action</th>
							</tr>
						</thead>
						<tbody id="tab_logic_userOwnLeaveItemApp">
						</tbody>
					</table>
			</div>
			<div class="panel panel-primary" id="viewPendingLeavesDiv" aria-labelledby="viewPendingLeavesDiv"	style="width: 100%;display: none;" scrolling="auto">
						<div class="panel-heading">
							<strong>Pending Leave Request List </strong>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;">
						<div class="form-group" id="timesheetStatusFilter" style="margin-left:15px;">
							<label><strong>Status:</strong></label>
							<span>
								<select class="form-control" id="pendingLeaveFilter" onchange="filterAnyTableByInput(this.value,'pendingLeaveItemTbl','5');">
								    <option value="" label="Select" label="All">All</option>
								    <option value="Approval Pending" selected label="Approval Pending">Approval Pending</option>
								    <option value="Approved" label="Approved">Approved</option>
									<option value="Rejected" label="Rejected">Rejected</option>
									<option value="Cancel" label="Canceled">Canceled</option>
									<option value="Leave Reversed" label="Leave Reversed">Leave Reversed</option>
								</select>
							</span>	
						</div>
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Employee Name:</strong></label>
							<span>
							 <input class="input-md  textinput textInput form-control" id="pendingLeaveFilterByName" placeholder="Employee Name" onkeyup="filterAnyTableByInput(this.value,'pendingLeaveItemTbl','2');">
							</span>
						</div>	
					</div>
					</div>
					</div>
			</div>
			<div id="pendingLeaveItemDiv" aria-labelledby="pendingLeaveItems"
					style="margin-top: 10px; width: 100%;display: none;" scrolling="auto">
					<table class="table table-bordered" id="pendingLeaveItemTbl" scrolling="auto">
						<thead style='background-color: #30a5ff;color:white;'>
							<tr>
								<th>#</th>
								<th class="text-center">Request Number</th>
								<th class="text-center">Employee Name</th>
								<th class="text-center">Created On</th>
								<th class="text-center">Leave Days</th>
								<th class="text-center">Status</th>
								<th class="text-center">LastModifiedOn</th>
								<th class="text-center">Action</th>
							</tr>
						</thead>
						<tbody id="tab_logic_pendingLeaveItemApp">
						</tbody>
					</table>
			</div>
			<div class="panel panel-primary" id="viewLeaveCategoryDiv" aria-labelledby="viewLeaveCategoryDiv"	style="width: 100%;display: none;" scrolling="auto">
						<div class="panel-heading">
							<strong>Manage Leave Type </strong>
						</div>
						<div class="panel-body">
					<div class="table-responsive">
					 <div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;">
						<div class="form-group" id="" style="margin-left:15px;">
							<input type="text" class="form-control" id="newLeaveName" placeholder="New Leave Name(Short)" maxlength="50" />
							<input type="text" class="form-control" id="newLeaveDesc" placeholder="Leave Description" maxlength="100" />
							<!-- <label><strong>Policy Group:</strong></label> -->
							<span>
								<select class="form-control" id="newLeavePolicyGroup" onchange="fetchCumulativeLeaveGroup(this.value,'newLeaveCumulativeGroup');return false;">
									<option value="" label="HR Policy Group">HR Policy Group</option>
								</select>
							</span>
							<!-- <label><strong>Leave Group:</strong></label> -->
							<span>
								<select class="form-control" id="newLeaveGroup">
									 <option value="" label="Leave Group">Leave Group</option>
								    <option value="Unpaid" label="Unpaid">Unpaid</option>
									<option value="Paid" label="Paid">Pai</option>
								</select>
							</span>
							<span>
								<select class="form-control" id="newLeaveCumulativeGroup">
									 <option value="" label="Cumulative Group">Cumulative Group</option>
								</select>
							</span><br>
							<input type="text" class="form-control" id="maxDaysPerRequest" placeholder="Max day per request" maxlength="2" />
							<input type="text" class="form-control" id="applyDaysBefore" placeholder="Apply min days before" maxlength="2" />
							<label class="checkbox-inline"><input type="checkbox" value="" id="applyByAdmin">Admin</label>
							<label class="checkbox-inline"><input type="checkbox" value="" id="applyByManager">Manager</label>
							<label class="checkbox-inline"><input type="checkbox" value="" id="applyByHR">HR</label>
							<button class="btn-sm btn-primary pull-right" onclick="createLeaveName();return false;">Create</button>
						</div>		
						<br />
						<div class="form-group" id="leaveList" style="margin:10px;width:100%">
						</div>
					</div>
					</div>
					</div>
			</div>
			<div class="panel panel-primary" id="viewLeaveQuarterDiv" aria-labelledby="viewLeaveQuarterDiv"	style="width: 100%;display: none;" scrolling="auto">
						<div class="panel-heading">
							<strong>Quarterly Leave Update Sheet </strong>
						</div>
						<div class="panel-body">
					<div class="table-responsive">
					 <div class="form-inline panel panel-primary" style="margin-left:0px;padding-bottom:0px;box-shadow:none;">
					 	<div>
					 	<span style="color:red;font-size:12px;"><strong>Important!</strong></span><br>
					 	<span style="font-size:10px;">Uploaded file must be in format xlsx.</span><br>
					 	<span style="font-size:10px;">Uploaded file name must be <strong>LeaveBalanceExcel.xlsx</strong> only.</span><br>
					 	<span style="font-size:10px;">Update and rename the downloaded file and do not change template format.</span><br>
					 	<span style="font-size:10px;">All cell format type must be text only.</span><br>
					 	<br>
					 	</div>
					 	<div>
					 	<a href="downloadLeaveBalance?policyGroup=SUPRA-Noida" id="noidaSheetId" class="btn btn-primary"style="display:inline;">Download Sheet</a>
					 	<a href="downloadLeaveBalance?policyGroup=SUPRA-Canada" id="canadaSheetId" class="btn btn-primary"style="display:inline;">Download Sheet</a>
					 	<form method="POST" enctype="multipart/form-data" id="fileUploadForm" style="display:inline;">
							<input type="submit" class="btn btn-primary pull-right" value="Upload" id="btnSubmit" onclick="uploadBalanceSheet();return false;"/>
							<input type="hidden" value="SUPRA-Noida" name="policyGroupName" id="policyGroupName"/>
					 		<input type="file" name="file" onchange="valBalSheetUpload(this.value,id);" id="leaveBalDocumentList" class="filestyle pull-right" style="display:inline;" data-buttonText="Upload Sheet"/>
					 	</form>
					 	</div>
						<div class="form-group" id="leaveQuarterList" style="margin-top:10px;width:100%">
						</div>
					</div>
					</div>
					</div>
			</div>
			<div class="modal fade" id="addLeaveRemarkModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Add Remark </h4>
				          <input type="hidden" id="leaveremarkValueHiddenId" name="leaveremarkValueHiddenId" value=''/>
				        </div>
				        <div class="modal-body">
				        <div class="form-group">
				          <label>Remark:</label>
				          <textarea class="input-md  textinput textInput form-control" id="idLeaveRmrk" maxlength="100" placeholder="Remarks...max 100 character" style="margin-bottom: 10px"></textarea>
				        </div>
				      <div class="modal-footer">
	    			      <button type="button" class="btn btn-success" id="addLeaveRemark" onclick="captureLeaveRemark();">OK</button>
				          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div>
				        </div>
				       </div>
				   </div>
			</div>
			<div class="modal fade" id="rmsActionHistoryModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header" style="background-color:#30a5ff;border-radius:4%;">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          	<span id="tempReqNoForModalHeader" style="color:#fff;font-weight:600;font-size:medium;"></span>
				        </div>
				        <div class="modal-body">
				     <table id="rmsActionHistoryTbl" class="table table-bordered">
		          			<thead>
		          				<tr>
				          			<th class="text-center">Status</th>
									<th class="text-center">From</th>
									<th class="text-center">Date</th>
									<th class="text-center">Remark</th>
									<th class="text-center">Send To</th>
								</tr>
		          			</thead>
		          		<tbody id="tab_logic_rmsActionHistory"></tbody>
		        	</table>
				        </div>
				      </div>
				     </div>
				  </div>
				<div class="modal fade" id="dragDropUserModal" role="dialog">
				    <div class="modal-dialog modal-lg">
				      <div class="modal-content">
				        <div class="modal-header"">
				          <div class="modal-header" style="background-color:#30a5ff;">
				          <!-- <button type="button" class="btn" data-dismiss="modal">&times;</button> -->
				          <a href='#!' title='Reset User List' style='color:#fff;' onclick='resetUpdateGroupUserModal();return false;'><span class='glyphicon glyphicon-refresh pull-right' style='margin-right: 10px;'></span></a>
				          	<span style="color:#fff;font-weight:600;font-size:medium;">Add/Remove Users from Access Group</span>
				          	</div>
				          	<input type="hidden" id="id_groupId1" value="" />
				        </div>
				        <div class="modal-body">
				        <span id="id_groupName1" style="color:black;font-weight:600;font-size:medium;"></span><br>
				        <span style="color:red;font-size:12px;">*Right panel displays existing users in group.</span><br>
				        <span style="color:red;font-size:12px;">*Left panel displays active users in system who are not in this group.</span><br>
				        <select multiple="multiple" name="duallistbox_demo1" id="duallistbox_demo11">
							<option value="option3" selected="selected">Option 3</option>
							<option value="option4">Option 4</option>
						</select>
				        </div>
				        <div style="margin-bottom:7px;">
				        	<button type="button" class="btn btn-sm btn-success" style="margin-left:44%;" onclick="addRemoveUsersFromGroup();return false;">Save</button>
				        	<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Close</button>
				        </div>
				      </div>
				     </div>
				  </div>  
			<div id="searchResultNullPass1" style="margin:10px;display:none;"><span style="color:red">No Result found in database.</span></div>
			<div class="modal fade" id="assignUserModal1" role="dialog">
				    <div class="modal-dialog modal-lg">
				      <div class="modal-content">
				        <div class="modal-header"">
				          <div class="modal-header" style="background-color:#30a5ff;">
				          <button type="button" class="btn pull-right" data-dismiss="modal">&times;</button>
				          <a href='#!' class="btn pull-right" title='Reset User List' style='color:#fff;' onclick='resetUpdateProjectUserModal();return false;'><span class='glyphicon glyphicon-refresh pull-right' style='margin-right: 10px;'></span></a>
				          	<span style="color:#fff;font-weight:600;font-size:medium;">Allocate/Deallocate Users from Project</span>
				          	</div>
				          	<input type="hidden" id="id_projectId1" value="" />
				        </div>
				        <div class="modal-body">
				        <span id="id_projectName1" style="color:black;font-weight:600;font-size:medium;"></span><br>
				        <span style="color:red;font-size:12px;">*Right panel displays existing users in project.</span><br>
				        <span style="color:red;font-size:12px;">*Left panel displays active users in system who are not in this project.</span><br>
				        <select multiple="multiple" name="duallistbox_demo5" id="duallistbox_demo55">
							<option value="option3" selected="selected">Option 3</option>
							<option value="option4">Option 4</option>
						</select>
				        </div>
				        <div style="margin-bottom:7px;">
				        	<button type="button" class="btn btn-sm btn-success" style="margin-left:44%;" onclick="addRemoveUsersFromProject1();return false;">Save</button>
				        	<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Close</button>
				        </div>
				      </div>
				     </div>
				  </div>
				<div class="panel panel-primary" id="vieLeaveReportDiv" aria-labelledby="viewLeaveReport" style="width: 100%;display: none;" scrolling="auto">
						<div class="panel-heading">
							<strong>Generate Leave Report </strong>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;">
										<div class="form-group" style="margin-top: 5px;margin-left:5px;">
											<label><strong>HR Policy Group*</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="hrPolicyGroup" onchange="populateEmployee(this.value);return false;">
													<option value="" label="HR Policy Group">HR Policy Group</option>
												</select>
											</span>
										</div>
										<div class="form-group" style="margin-top: 5px;margin-left:5px;">
											<label><strong>Select Employee:</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
											<select class="form-control" id="userListForLeaveReport" onchange="getUsersPolicy();">
													<option value="" label="Select Employee" selected />
												</select>
											</span>
										</div>
									<div class="form-group" style="margin-top: 5px;margin-left:5px;">
											<label><strong>Start Date*</strong></label> <br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<input type="text" id="from-datepicker-leave" class="form-control" style="width:100px;" onkeypress="return false;"/>
											</span>
									</div>
									<div class="form-group" style="margin-top: 5px;margin-left:5px;">
											<label><strong>End Date*</strong></label> <br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<input type="text" id="to-datepicker-leave" class="form-control" style="width:100px;" onkeypress="return false;"/>
											</span>
									</div>
									<div class="form-group" style="margin-top: 5px;margin-left:5px;">
											<label><strong>Leave Status</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="leaveStatusList">
													<option value="" label="Select Status" selected>Select Status</option>
													<option value="Approved" label="Approved">Approved</option>
													<option value="Approval Pending" label="Approval Pending">Approval Pending</option>
													<option value="Rejected" label="Rejected">Rejected</option>
													<option value="Deleted" label="Deleted">Deleted</option>
												</select>
											</span>
									</div>
								<div class="form-group" style="margin-top: 5px;margin-left:15px;">
											<label><a href="#"><button type="button" class="btn btn-primary" style="margin-top:30px;" onclick="generateLeaveReport();">View Report</button></a>
								</label>
								</div>
					<br />
					<br />
					</div>
					</div>
					<div id="vieLeaveReportDataDiv"></div>
					</div>
					
				</div>
				<div class="panel panel-primary" id="vieReimbursementReportDiv" aria-labelledby="viewReimbursementReport"	style="width: 100%;display: none;" scrolling="auto">
						<div class="panel-heading">
							<strong>Reimbursement Report </strong>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;">
										<div class="form-group" style="margin-top:5px;margin-left:5px;">
											<label><strong>Select Employee:</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
											<select class="form-control" id="userListForReimbursementReport">
													<option value="" label="Select Employee" selected />
												</select>
											</span>
										</div>
									<div class="form-group" style="margin-top: 5px;margin-left:5px;">
											<label><strong>Start Date*</strong></label> <br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<input type="text" id="from-datepicker-expense" class="form-control" style="width:100px;" placehoder="Strat Date" onkeypress="return false;"/>
											</span>
									</div>
									<div class="form-group" style="margin-top:5px;margin-left:5px;">
											<label><strong>End Date*</strong></label> <br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<input type="text" id="to-datepicker-expense" class="form-control" style="width:100px;" onkeypress="return false;"/>
											</span>
									</div>
									<div class="form-group" style="margin-top:5px;margin-left:5px;">
											<label><strong>Status:</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="expenseStatusList">
													<option value="select" label="Select Status" selected />
												</select>
											</span>
									</div>
									<div class="form-group" style="margin-top:5px;margin-left:5px;">
											<label><strong>Select Bucket:</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
											<select class="form-control" id="bucketListForReimbursementReport">
													<option value="" label="Select Employee" selected />
												</select>
											</span>
										</div>
									<div class="form-group" style="margin-top:5px;margin-left:15px;">
										<label><a href="#"><button type="button" class="btn btn-primary" style="margin-top:30px;" onclick="generateExpenseReport();">View</button></a></label>
									</div>
									<br />
									<span style="margin-left:10px;color:red;font-size:12px;">*Please note all listed bucket have only "Approved Status" reimbursement request.</span>
					</div>
					</div>
					<div id="vieExpReportDataDiv"></div>
					</div>
				</div>
				<div class="modal fade" id="rmsDetailModal" role="dialog">
				    <div class="modal-dialog modal-lg">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Expense Details</h4>
				        </div>
				        	<div class="modal-body" id="rmsModalDiv">
				        </div>
				      </div>
				     </div>
				  </div>
				  <div class="panel panel-primary" id="viewAddAssetDiv" aria-labelledby="viewAddAsset" style="width: 100%;display: none;" scrolling="auto">
						<div class="panel-heading">
							<strong>Add New Asset</strong>
						</div>
						<div class="panel-body" id="addAssetDiv">
								<div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Asset Category:<span class="asteriskField">*</span> </label>
                       		 	<select id="addCategoryListForAsset" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;" onchange="populateAssetTag(this.value,'addTagForAsset',this.id)">
                       		 		<option value="">Select Category</option>
                       		 	</select>
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Asset Tag<span class="asteriskField">*</span> </label>
                       		 	<select id="addTagForAsset" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;">
                       		 		<option value="">Select Asset Tag</option>
                       		 	</select>
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Asset Id:<span class="asteriskField">*</span> </label>
                       		 	<input type="text" id="addIdForAsset" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;">
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Asset Name:<span class="asteriskField">*</span> </label>
                       		 	<input type="text" id="addNameForAsset" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;">
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Serial Number/Service Tag<span class="asteriskField">*</span>:</label>
                       		 	<input type="text" id="addSrNoForAsset" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;">
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Asset Status:<span class="asteriskField">*</span> </label>
                       		 	<select id="addStatusForAsset" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;">
                       		 		<option value="Select Status">Select Status</option>
                       		 	</select>
                       		 </div>
                       		 <div class="form-group required">
								<a id='addAssetAllocation' class="pull-right btn btn-primary" style="margin-right:10px;" onclick="addNewAssetInSystem();">Add</a>										                       		 	
                       		 </div>
						</div>		
				  </div>		
				  <div class="panel panel-primary" id="viewAllocateAssetDiv" aria-labelledby="viewAllocateAsset" style="width: 100%;display: none;" scrolling="auto">
						<div class="panel-heading">
							<strong>Asset Allocation Form </strong>
						</div>
						<div class="panel-body" id="allocationAssetDiv">
								<div class="form-group required">
		                            <label class="control-label col-md-4 requiredField"> Employee Policy Group:<span class="asteriskField">*</span> </label>
		                            <div class="controls col-md-8 "  style="margin-bottom: 10px">
		                            <select class="form-control" id="id_select_type_policy" onchange="getEmpListBasedOnPolicyGroup(this.value);">
										<option value="" label="HR Policy Group">Select HR Policy Group</option>
									</select>
		                                <!-- <label class="radio-inline">
		                                	<input type="radio" checked="checked" name="id_select_type_policy" id="id_select_type_policy" value="SUPRA-Noida"  style="margin-bottom: 10px" >SUPRA-Noida</label>
		                                <label class="radio-inline"> 
		                                	<input type="radio" name="id_select_type_policy" id="id_select_type_policy" value="SUPRA-Canada" style="margin-bottom: 10px" >SUPRA-Canada </label> -->
		                            </div>
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Select Employee:<span class="asteriskField">*</span> </label>
                       		 	<select id="userListForAsset" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;">
                       		 		<option value="">Select Employee</option>
                       		 	</select>
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Asset Category:<span class="asteriskField">*</span> </label>
                       		 	<select id="categoryListForAsset" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;" onchange="populateAssetTag(this.value,'tagListForAsset',this.id)">
                       		 		<option value="">Select Category</option>
                       		 	</select>
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Asset Tag:<span class="asteriskField">*</span> </label>
                       		 	<select id="tagListForAsset" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;" onchange="populateAssetIds(this.value,'idListForAsset')">
                       		 		<option value="">Select Tag</option>
                       		 	</select>
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Asset Id List:<span class="asteriskField">*</span> </label>
                       		 	<select id="idListForAsset" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;" onchange="populateAssetDetails(this.value)">
                       		 		<option value="">Select Asset Id</option>
                       		 	</select>
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Asset Name:<span class="asteriskField">*</span> </label>
                       		 	<input type="text" id="nameForAsset" readonly="true" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;">
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Serial Number/Service Tag<span class="asteriskField">*</span>:</label>
                       		 	<input type="text" id="srNoForAsset" readonly="true" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;">
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Asset Status:<span class="asteriskField">*</span> </label>
                       		 	<input type="text" id="statusForAsset" readonly="true" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;">
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Description:<span class="asteriskField">*</span> </label>
                       		 	<input type="text" id="descForAsset" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;">
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Allocation Date:<span class="asteriskField">*</span> </label>
                       		 	<input type="text" id="allocationDateForAsset" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;" onkeypress="return false;">
                       		 </div>
                       		 <div class="form-group required">
								<a id='assetAllocation' class="pull-right btn btn-primary" style="margin-right:10px;" onclick="allocateAssetToUser();">Submit</a>										                       		 	
                       		 </div>
						</div>
					</div>
		<div class="panel panel-primary" id="viewTrackAssetDiv" aria-labelledby="viewTrackAssetDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel-heading">
				<strong>Track Asset Allocation</strong>
			</div>
			 <div class="panel-body">
				<div class="table-responsive">
					<div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;">
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Asset Status:</strong></label><br />
							<span>
								<select class="form-control" id="assetStatusFilter" onchange="filterAnyTableByInput(this.value,'userAssetItemTbl','3');">
								    <option value="" label="Select" selected>Select Status</option>
								</select>
							</span>	
						</div>
						<!-- <div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Asset Category:</strong></label><br />
							<span>
								<select class="form-control" id="assetCategoryFilter" onchange="filterAnyTableByInput(this.value,'userAssetItemTbl','6');">
								    <option value="" label="Select" selected>Select Category</option>
								</select>
							</span>	
						</div> -->
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Asset Name:</strong></label><br />
							<span>
							<input class="input-md  textinput textInput form-control" id="assetFilterByAssetName" placeholder="Asset Name" onkeyup="filterAnyTableByInput(this.value,'userAssetItemTbl','2');">
							</span>
						</div>
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Employee Name:</strong></label><br />
							<span>
							<input class="input-md  textinput textInput form-control" id="assetFilterByEmpName" placeholder="Employee Name" onkeyup="filterAnyTableByInput(this.value,'userAssetItemTbl','6');">
							</span>
						</div>	
					</div>
				</div>
				<table class="table table-bordered" id="userAssetItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th>#</th>
								<th class="text-center">Asset Id</th>
								<th class="text-center">Asset Name</th>
								<th class="text-center">Status</th>
								<th class="text-center">Start Date</th>
								<th class="text-center">End Date</th>
								<th class="text-center">Allocated To</th>
								<th class="text-center">Allocated By</th>
								<th class="text-center">Allocated On</th>
								<th class="text-center">Action</th>
							</tr>
						</thead>
						<tbody id="tab_logic_userAssetItemApp">
						</tbody>
				</table>
			  </div>
		</div>
		<div class="panel panel-primary" id="viewUploadAttendanceDiv" aria-labelledby="viewUploadAttendanceDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel-heading">
				<strong>Upload Attendance Data</strong>
			</div>
			 <div class="panel-body">
			 	<div class="table-responsive">
					 <div class="form-inline panel panel-primary" style="margin-left:0px;padding-bottom:0px;box-shadow:none;">
					 	<div>
					 	<span style="color:red;font-size:12px;"><strong>Important!</strong></span><br>
					 	<span style="font-size:10px;">Uploaded file must be in format xlsx.</span><br>
					 	<span style="font-size:10px;">Uploaded file name must be <strong>UploadAttendance.xlsx</strong> only.</span><br>
					 	<span style="font-size:10px;">Update and rename the downloaded file and do not change template format.</span><br>
					 	<span style="font-size:10px;">All cell format type must be text only.</span><br>
					 	<br>
					 	</div>
					 	<div>
					 	<a href="downloadAttendanceTemplate" id="attendanceSheetId" class="btn btn-primary"style="display:inline;">Download Sheet</a>
					 	<form method="POST" enctype="multipart/form-data" id="attenFileUploadForm" style="display:inline;">
							<input type="submit" class="btn btn-primary pull-right" value="Upload" id="btnAttenSubmit" onclick="uploadAttendanceSheet1();return false;"/>
					 		<input type="file" name="file" onchange="valAttendanceSheetUpload(this.value,id);" id="attendanceDocumentList" class="filestyle pull-right" style="display:inline;" data-buttonText="Upload Sheet"/>
					 	</form>
					 	</div>
						<div class="form-group" id="attendanceSheetList" style="margin-top:10px;width:100%">
						</div>
					</div>
					</div>			
			 </div>
		</div>	 
		<div class="panel panel-primary" id="viewUserAttendanceDiv" aria-labelledby="viewUserAttendanceDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel-heading">
				<strong>Attendance Data</strong>
			</div>
			 <div class="panel-body">
			 <div class="table-responsive">
				<div class="form-inline panel panel-primary" style="margin-left:10px;">
				 		<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Start Date</strong></label> <br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<input type="text" id="from-datepicker-attendance" class="form-control" onkeypress="return false;" style="width:100px;"/>
											</span>
						</div>
						<div class="form-group" style="margin-top: 10px;margin-left:5px;">					
											<label><strong>End Date</strong></label><br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<input type="text" id="to-datepicker-attendance" class="form-control" onkeypress="return false;" style="width:100px;"/>
											</span>
												
						</div>
						<div class="form-group" style="margin-top: 35px;margin-left:5px;">					
											<span style="padding-left: 0px; margin-top: 5px;">
												<a id='pastAttendanceHistory' class="pull-right btn btn-primary" style="margin-right:10px;" onclick="showUserAttendanceHistory();">Go</a>
											</span>
												
						</div>
					</div>
					<table class="table table-bordered" id="userAttenItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th class="text-center">Date</th>
								<th class="text-center">Shift Time</th>
								<th class="text-center">In Time</th>
								<th class="text-center">Out Time</th>
								<th class="text-center">Punched HH</th>
								<th class="text-center">Def/Extra HH</th>
								<th class="text-center">Status</th>
							</tr>
						</thead>
						<tbody id="tab_logic_userAttenItemApp">
						</tbody>
				</table>
				</div>		
			 </div>
		</div>	 
		<div class="panel panel-primary" id="viewUserMOAFDiv" aria-labelledby="viewUserMOAFDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel-heading">
				<strong>Fill Missed Out Attendance Form</strong>
			</div>
			 <div class="panel-body">
			 	<div class="table-responsive">
					<div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;">
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Category:</strong></label><br />
							<span>
								<select class="form-control" id="moafCategory">
								    <option value="MOAF" selected>Missed Punch</option>
								    <option value="WFH">Work From Home</option>
								    <option value="Onsite">Onsite</option>
								</select>
							</span>	
						</div>
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Fill Date:</strong></label><br />
							<span>
								<input class="input-md  textinput form-control" id="moafDateId" placeholder="Missed Punch Date" onkeypress="return false;">
							</span>	
						</div>
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Select In/Out:</strong></label><br />
							<span>
								<select class="form-control" id="attendanceDirection" onchange="inOutSelect(this.value);">
								    <option value="Both" selected>Both</option>
								    <option value="In">In Time</option>
								    <option value="Out">Out Time</option>
								</select>
							</span>	
						</div>
						 <div class="input-group bootstrap-timepicker timepicker">
						 	<label><strong>In Time:</strong></label><br />
					            <input id="timepicker2" type="text" class="form-control input-small" style="width:70%;">
					            <span class="input-group-addon" style="border:none;padding-top:70px;padding-left:10px;background:none;display:inline;">
					                <i class="glyphicon glyphicon-time" style="border:none;padding-top:7px;"></i>
					            </span>
					     </div>
					     <div class="input-group bootstrap-timepicker timepicker">
						 	<label><strong>Out Time:</strong></label><br />
					            <input id="timepicker3" type="text" class="form-control input-small" style="width:70%;">
					            <span class="input-group-addon" style="border:none;padding-left:10px;background:none;display:inline;">
					                <i class="glyphicon glyphicon-time" style="border:none;padding-top:7px;"></i>
					            </span>
					     </div>
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Reason:</strong></label><br />
							<input id="moafPurpose" type="text" class="form-control input-small" maxlength="100" >	
						</div>
						<div class="form-group" id="" style="margin-left:15px;margin-top:30px;">
							<span>
								<a href="#!" class="btn btn-primary btn sm" style="margin-right:10px;" onclick="submitMOAF();return false;">Submit</a>
							</span>	
						</div>
					</div>
				</div>
				<div id="userOwnMOAFData">
						<span>User pending/past MOAF data will be shown below.</span>
				</div>		
			 </div>
		</div>
		<div class="panel panel-primary" id="viewApproveUserMOAFDiv" aria-labelledby="viewApproveMOAFDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel-heading">
				<strong>Approve Employees Attendance</strong>
			</div>
			 <div class="panel-body">
			 	<div id="userPendingMOAF">
			 	</div>
			 </div>
		</div>
		<div class="panel panel-primary" id="viewAttendanceReportDiv" aria-labelledby="viewAttendanceReportDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel-heading">
				<strong>Employees Attendance Report</strong>
			</div>
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;">
										<div class="form-group" style="margin-top: 10px;margin-left:5px;">
													<label><strong>Start Date</strong></label> <br />
													<span style="padding-left: 0px; margin-top: 5px;">
														<input type="text" id="from-date-User-Atten" class="form-control" onkeypress="return false;" />
													</span>
											</div>
											<div class="form-group" style="margin-top: 10px;margin-left:5px;">					
																<label><strong>End Date</strong></label><br />
																<span style="padding-left: 0px; margin-top: 5px;">
																	<input type="text" id="to-date-User-Atten" class="form-control" onkeypress="return false;"/>
																</span>
																	
											</div>
										 <div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Project Name</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												 <select class="form-control" id="projectListForAttendence" onchange="getAllUserForReport('true','usrListInAttendenceReport',this.id);"> 
														<option value="select" label="All Project" selected />
												</select>
											</span>
										</div>
									 	<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>User Name</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="usrListInAttendenceReport">
													<option value="select" label="Select User" selected />
												</select>
											</span>
										</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
											<label><a href="#"><button type="button"
														class="btn btn-primary" style="margin-top:30px;" title="Click to View" onclick="getUsersAttendenceReport();">Go</button></a></label>
					</div>
					</div>
					</div>
					<div id="userAttnDataDiv" style="display:none;">
						<a href="downloadUserAttendenceRepo" id="attnRprtId" class="btn btn-primary pull-right" style="display:inline;margin-right:67px;margin-bottom:5px;">Download</a>
					<table class="table table-bordered" id="otherUserAttenItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th class="">Date</th>
								<th class="">Username</th>
								<th class="">Emp Name</th>
								<th class="">Shift Time</th>
								<th class="">In Time</th>
								<th class="">Out Time</th>
								<th class="">Punched HH</th>
								<th class="">Def/Extra HH</th>
								<th class="">Status</th>
							</tr>
						</thead>
						<tbody id="tab_logic_otherUserAttenItemApp">
						</tbody>
					</table>
					</div>
		</div>
		<div class="panel panel-primary" id="viewWeeklyAttendanceReportDiv" aria-labelledby="viewWeeklyAttendanceReportDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel-heading">
				<strong>Employees Weekly Attendance Report</strong>
			</div>
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;">
										<div class="form-group" style="margin-top: 10px;margin-left:5px;">
													<label><strong>Start Date(Sun)</strong></label> <br />
													<span style="padding-left: 0px; margin-top: 5px;">
														<input type="text" id="from-date-User-Atten-Week" class="form-control" onkeypress="return false;" />
													</span>
											</div>
											<div class="form-group" style="margin-top: 10px;margin-left:5px;">					
																<label><strong>End Date(Sat)</strong></label><br />
																<span style="padding-left: 0px; margin-top: 5px;">
																	<input type="text" id="to-date-User-Atten-Week" class="form-control" onkeypress="return false;"/>
																</span>
																	
											</div>
									 	<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>User Name</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="usrListInWeeklyAttendenceReport">
													<option value="select" label="Select User" selected />
												</select>
											</span>
										</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
											<label><a href="#"><button type="button"
														class="btn btn-primary" style="margin-top:30px;" title="Click to View" onclick="getUsersWeeklyAttendenceReport();">Go</button></a></label>
					</div>
					</div>
					</div>
					<div id="userWeeklyAttnDataDiv" style="display:none;">
					<a href="downloadUserWeeklyAttendenceRepo" id="attnWeeklyRprtId" class="btn btn-primary pull-right" style="display:inline;margin-right:67px;margin-bottom:5px;">Download</a>
					<table class="table table-bordered" id="otherUserWeeklyAttenItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th class="">SrNo</th>
								<th class="">Week Interval</th>
								<th class="">Username</th>
								<th class="">Emp Name</th>
								<th class="">Working HH</th>
								<th class="">Punched HH</th>
								<th class="">Def/Extra HH</th>
							</tr>
						</thead>
						<tbody id="tab_logic_otherUserWeeklyAttenItemApp">
						</tbody>
					</table>
					</div>
		</div>
		<div class="panel panel-primary" id="viewYearlyAttendanceReportDiv" aria-labelledby="viewYearlyAttendanceReportDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel-heading">
				<strong>Employees Yearly Attendance Report</strong>
			</div>
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;">
									 	<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>User Name</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="usrListInYearlyAttendenceReport">
													<option value="select" label="Select User" selected />
												</select>
											</span>
										</div>
										<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Year</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="yearListInYearAttendenceReport">
													<option value="2017" label="2017" selected>2017</option>
													<option value="2018" label="2018" >2018</option>
													<option value="2019" label="2019" >2019</option>
												</select>
											</span>
										</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
											<label><a href="#"><button type="button"
														class="btn btn-primary" style="margin-top:30px;" title="Click to View" onclick="viewUserYearAttnRequest();">Go</button></a></label>
					</div>
					</div>
					</div>
				</div>
				<div class="modal fade" id="leaveDetailModal" role="dialog">
				    <div class="modal-dialog modal-sm">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">User Leave Balance</h4>
				        </div>
				        <div class="modal-body" id="leaveModalDiv"></div>
				        <div class="modal-footer"></div>
				      </div>
				     </div>
				  </div>
				  <div class="modal fade" id="leaveMeetingModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Send Meeting Request</h4>
				        </div>
				        <div class="modal-body" id="leaveMeetingModalDiv">
				        	<div class="form-group" style="margin-top: 5px;margin-left:5px;">
								<label><strong>Request No:</strong></label><span id="reqNumForMeeting"></span>
								<input type="hidden" value="" id="reqNumForMeetingHidden" />
							</div>
				        	<div class="form-group" style="margin-top: 5px;margin-left:5px;">
								<label><strong>To</strong></label>
								<span id="userNameMeeting"></span>
								<input type="hidden" value="" id="userNameMeetingHidden" />
							</div>
							<div class="form-group" style="margin-left:5px;">
								<label><strong>Time</strong></label>
								<div class='input-group date' id='meetingTime' style="position:relative;">
				                    <input type='text' class="form-control" id='meetingTime1'/>
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
								<!-- <input id="meetingTime" type="text" class="form-control input-small" value="Meeting Time" /> --> 
							</div>
							<div class="form-group" style="margin-left:5px;">
								<label><strong>Location</strong></label><input type="text" class="form-control" placeholder="Meeting Location...." id="meetingLocation" /> 
							</div>
							<div class="form-group" style="margin-left:5px;">
								<label><strong>Subject</strong></label><textarea class="form-control" id="meetingSubject" rows="2" value="Discussion regarding leave request."></textarea> 
							</div>
							<div class="form-group" style="margin-left:3px;">
											<label><a href="#"><button type="button"
														class="btn btn-primary" style="" title="Send Request" onclick="sendMeetingRequestForLeave();">Send</button></a></label>
							</div>
							<div class="panel panel-default" id="meetingHistory">
								    <div class="panel-heading" style="background-color:#f5f5f5;">
								      <h4 class="panel-title" style="color:#000;">
								        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#meetCollapse">
								          Meeting History
								        </a>
								      </h4>
								    </div>
								    <div id="meetCollapse" class="panel-collapse collapse">
								      <div class="panel-body" id="meetingHistoryData">
								        	<span style="color:red;">No history available.</span>
								      </div>
								    </div>
							</div>
				        </div>
				        <div class="modal-footer"></div>
				      </div>
				     </div>
				  </div>
				  <div class="panel panel-primary" id="userAssignedTaskDiv" aria-labelledby="userAssignedTaskDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel-heading">
				<strong>Assigned Task List to User</strong>
			</div>
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;">
									 	<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Total Assigned Task::</strong></label> 
											<span margin-left: 5px;" id="userTotalTaskCount"><b>0</b></span>
										</div>
								</div>
							</div>
							<div id="assignedTaskList"></div>
						</div>
						<div class="panel panel-primary" id="viewExceptionAttendanceReportDiv" aria-labelledby="viewExceptionAttendanceReportDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel-heading">
				<strong>Attendance Exception Report</strong>
			</div>
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;">
										<div class="form-group" style="margin-top: 10px;margin-left:5px;">
													<label><strong>Start Date(Sun)</strong></label> <br />
													<span style="padding-left: 0px; margin-top: 5px;">
														<input type="text" id="from-date-User-Atten-Exception" class="form-control" onkeypress="return false;" />
													</span>
											</div>
											<div class="form-group" style="margin-top: 10px;margin-left:5px;">					
																<label><strong>End Date(Sat)</strong></label><br />
																<span style="padding-left: 0px; margin-top: 5px;">
																	<input type="text" id="to-date-User-Atten-Exception" class="form-control" onkeypress="return false;"/>
																</span>
																	
											</div>
									 	<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>User Name</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="usrListInExceptionAttendenceReport">
													<option value="select" label="Select User" selected />
												</select>
											</span>
										</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
											<label><a href="#"><button type="button"
														class="btn btn-primary" style="margin-top:30px;" title="Click to View" onclick="getUsersExceptionAttendenceReport();">Go</button></a></label>
					</div>
					</div>
					</div>
					<div id="userExceptionAttnDataDiv" style="display:none;">
					<table class="table table-bordered" id="otherUserExceptionAttenItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th class="">SrNo</th>
								<th class="">Username</th>
								<th class="">Emp Name</th>
								<th class="">Total Working HH</th>
								<th class="">Desired Working HH</th>
								<th class="">Punched HH</th>
								<th class="">Def/Extra HH</th>
								<th class="">On Leave</th>
								<th class="">Holidays</th>
							</tr>
						</thead>
						<tbody id="tab_logic_otherUserExceptionAttenItemApp">
						</tbody>
					</table>
					<a href="downloadUserExcpetionAttendenceRepo" title="Download Excel" id="attnExceptionRprtId" class="btn btn-primary pull-left" style="display:inline;margin-right:67px;margin-bottom:5px;">Download</a>
					</div>
		</div>	 
		<div class="panel panel-primary" id="viewNewLeavesOthersDiv" aria-labelledby="viewNewLeavesOthersDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel-heading">
				<strong>Apply Leave for Other Employees</strong>
			</div>
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;">
									 	<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Select Employee</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="applyLeaveForOtherUser">
													<option value="select" label="Select User" selected />
												</select>
											</span>
										</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
						<label><a href="#"><button type="button" class="btn btn-primary" style="margin-top:30px;" title="Click to View" onclick="applyLeaveRequestForOthers('applyLeaveForOtherUser');">Apply</button></a></label>
					</div>
					</div>
					</div>
				</div>
	<div class="panel panel-primary" id="viewGenerateDocDiv" aria-labelledby="viewGenerateDocDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel-heading">
				<strong>Generate Employee Documents</strong>
			</div>
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;">
									 	<!-- <div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Select Employee</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="empListForDoc">
													<option value="select" label="Select User" selected />
												</select>
											</span>
										</div> -->
										<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Select Doc</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="docListForDoc">
													<option value="select" label="Select Document" selected>Select Doc</option>
													<option value="1" label="Offer Letter">Offer Letter</option>
													<option value="2" label="Confirmation Letter">Confirmation Letter</option>
													<option value="3" label="Relieving Letter">Relieving Letter</option>
													<option value="4" label="Payslip">Payslip</option>
												</select>
											</span>
										</div>
									<div class="form-group" style="margin-top: 10px;margin-left:15px;">
										<label><a href="#"><button type="button" class="btn btn-primary" style="margin-top:30px;" title="Click to View" onclick="fetchRequiredParam('','docListForDoc');">Go</button></a></label>
									</div>
					</div>
					<div class="row col-lg-12" id="populateFieldId"></div>
					<div class="row" id="downloadempdocdiv"></div>
					</div>
				</div>
				<div class="modal fade" id="viewAccessGroupModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Edit User Access Group ::<span id="id_userName1"></span></h4>
				          <input type="hidden" id="userName1Value" />
				        </div>
				        <div class="modal-body" id="accessGroupDiv" style="padding:0px;"></div>
				        <div class="modal-footer">
	    			      <!-- <button type="button" class="btn btn-success" onclick="updateUserAccessGrp();">Save</button> -->
				          <!-- <button type="button" class="btn btn-info" data-dismiss="modal">Close</button> -->
				        </div>
				        </div>
				       </div>
				 </div>
<script type="text/javascript">
	document.getElementById("vieTimeShtDiv").style.display='block';
	document.getElementById('vieTimeShtDiv').setAttribute("style","min-height:490px");
	$("#vieTimeShtDiv").appendTo("#dataDiv");
	/* document.getElementById("viewLasttenWeekDiv").style.display='block';
	$("#viewLasttenWeekDiv").appendTo("#vieTimeShtDiv"); */
	getRecentTimesheetData();
	
	function formatDate(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();

	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;

	    return [year, month, day].join('-');
   }
	
</script>
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
    $("input").click(function(){
    clearTimeout(myTimeOut);
        SessionTime=900000;
     myTimeOut=setTimeout(SessionExpireEvent,SessionTime);
    });

    function SessionExpireEvent()
    { 
    	clearInterval(myInterval);
    	window.location.href = "logoutUser";
    }
    var demo1 = $('[name=duallistbox_demo1]').bootstrapDualListbox();
    var demo2 = $('[name=duallistbox_demo5]').bootstrapDualListbox();
</script>
<c:if test="${sessionScope.loggedInUserAccessMap[FILL_TIMESHEET] eq false}">
	<script type="text/javascript">
		pastLeaveList();
	</script>
</c:if>
</body>
</html>