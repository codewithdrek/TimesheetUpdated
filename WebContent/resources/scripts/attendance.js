function uploadAttendanceData(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewUploadAttendanceDiv").style.display='block';
	document.getElementById('viewUploadAttendanceDiv').setAttribute("style","min-height:600px;");
	$("#viewUploadAttendanceDiv").appendTo("#dataDiv");
}
function viewOwnAttendance(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewUserAttendanceDiv").style.display='block';
	document.getElementById('viewUserAttendanceDiv').setAttribute("style","min-height:600px;");
	$("#viewUserAttendanceDiv").appendTo("#dataDiv");
	var date = new Date();
	var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
	var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
	getUserAttendanceData(formatDate(firstDay),formatDate(new Date()));
}
function fillMOAFDiv(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewUserMOAFDiv").style.display='block';
	document.getElementById('viewUserMOAFDiv').setAttribute("style","min-height:600px;");
	$("#viewUserMOAFDiv").appendTo("#dataDiv");
	getUserOwnMOAFData();
}
function approveMOAFDiv(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewApproveUserMOAFDiv").style.display='block';
	document.getElementById('viewApproveUserMOAFDiv').setAttribute("style","min-height:600px;");
	$("#viewApproveUserMOAFDiv").appendTo("#dataDiv");
	approveUserAttendance();
}
function viewAttendanceData(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewAttendanceReportDiv").style.display='block';
	document.getElementById('viewAttendanceReportDiv').setAttribute("style","min-height:600px;");
	$("#viewAttendanceReportDiv").appendTo("#dataDiv");
	getAllProjectListforReport('projectListForAttendence');
	getAllUserForReport(false,'usrListInAttendenceReport','projectListForAttendence');
}
function viewYearlyAttendanceData(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewYearlyAttendanceReportDiv").style.display='block';
	document.getElementById('viewYearlyAttendanceReportDiv').setAttribute("style","min-height:600px;");
	$("#viewYearlyAttendanceReportDiv").appendTo("#dataDiv");
	getAllUserForReport(false,'usrListInYearlyAttendenceReport','projectListForAttendence');
}
function viewExceptionAttendanceData(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewExceptionAttendanceReportDiv").style.display='block';
	document.getElementById('viewExceptionAttendanceReportDiv').setAttribute("style","min-height:600px;");
	$("#viewExceptionAttendanceReportDiv").appendTo("#dataDiv");
	getAllUserForReport(false,'usrListInExceptionAttendenceReport','');
}

function viewWeeklyAttendanceData(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewWeeklyAttendanceReportDiv").style.display='block';
	document.getElementById('viewWeeklyAttendanceReportDiv').setAttribute("style","min-height:600px;");
	$("#viewWeeklyAttendanceReportDiv").appendTo("#dataDiv");
	getAllUserForReport(false,'usrListInWeeklyAttendenceReport','');
	var d = new Date();
	var startDay = moment(d, "YYYY-MM-DD").day(0).format("YYYY-MM-DD");
    var endDay =  moment(d, "YYYY-MM-DD").day(6).format("YYYY-MM-DD");
    $("#from-date-User-Atten-Week").val(startDay);
    $("#to-date-User-Atten-Week").val(endDay);
    getUsersWeeklyAttendenceReport();
}
function showUserAttendanceHistory(){
	var firstDay = document.getElementById("from-datepicker-attendance").value;
	var lastDay  = document.getElementById("to-datepicker-attendance").value;
	if(firstDay == "" || firstDay == undefined || firstDay == null || lastDay == "" || lastDay == undefined || lastDay == null){
		alert("Please select valid date.");
		return false;
	}
	getUserAttendanceData(firstDay,lastDay);
}
function getUserAttendanceData(firstDay,lastDay){
	//alert(firstDay +"**"+lastDay);
	$.ajax({
		type: "POST",
		url: "getUserOwnCurrentMonthAttendance",
		data:{firstDay:firstDay,lastDay:lastDay},
		dataType: 'json',
		success: function(result){
		if(result.length >0){
			document.getElementById("searchResultNullPass1").style.display='none';	
			$('#userAttenItemTbl tbody').empty();
			var totalHH = "00:00:00";
			var totalDefHH = "00:00:00";
			var totalExtraHH = "00:00:00";
			for (var i = 0; i < result.length; i++) {	
				var row = "";
							if(result[i].status == "Absent"){
								row = $("<tr class='danger'  />");
							}else if(result[i].status == 'Week Off'){
									row = $("<tr class='info' />");					
								}else if((result[i].status == 'Leave Applied') || (result[i].status == 'On Leave')){
									row = $("<tr class='warning' />");
								}else	
									row = $("<tr class='success' />");
				 	$("#tab_logic_userAttenItemApp").append(row); 
					row.append($("<td  class='EWTableElements'>" + result[i].date + "</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].shifttime+"</td>"));
					row.append($("<td  class='EWTableElements'>"+result[i].intime+"</td>"));
					row.append($("<td   class='EWTableElements'>"+result[i].outtime+"</td>"));
					row.append($("<td   class='EWTableElements'>"+result[i].punchedcount+"</td>"));
					if(result[i].punchedcount != "0")
						totalHH = changeToHHMMSS(changeToSeconds(totalHH) + changeToSeconds(result[i].punchedcount));
					if(result[i].deficienthours == undefined){
						row.append($("<td  class='EWTableElements' style='font-weight:400;color:green;'>"+result[i].extrahours+"</td>"));
						if(result[i].extrahours != "0")
							totalExtraHH = changeToHHMMSS(changeToSeconds(totalExtraHH) + changeToSeconds(result[i].extrahours));
					}else{
						row.append($("<td  class='EWTableElements' style='font-weight:400;color:red;'>"+result[i].deficienthours+"</td>"));
						if(result[i].deficienthours != "0")
							totalDefHH = changeToHHMMSS(changeToSeconds(totalDefHH) + changeToSeconds(result[i].deficienthours));
					}	
					row.append($("<td id='requestStatus"+i+"'  class='EWTableElements'>"+result[i].status+"</td>"));
			    }
				var row1 = $("<tr class='info' />");
				row1.append($("<td  class='EWTableElements' colSpan='4' style='text-align:right;'><strong>Total Punched HH</strong></td>"));
				row1.append($("<td  class='EWTableElements'>"+totalHH+"</td>"));
				if(changeToSeconds(totalExtraHH) >= changeToSeconds(totalDefHH))
					row1.append($("<td  class='EWTableElements' style='color:green;'>"+changeToHHMMSS(changeToSeconds(totalExtraHH) - changeToSeconds(totalDefHH))+"</td>"));
				else
					row1.append($("<td  class='EWTableElements' style='color:red;'>"+changeToHHMMSS(changeToSeconds(totalDefHH) - changeToSeconds(totalExtraHH))+"</td>"));
				row1.append($("<td  class='EWTableElements'><strong>Def/Extra HH</strong></td>"));
				$("#tab_logic_userAttenItemApp").append(row1);
			}else{
				document.getElementById("userExpenseItemDiv").style.display='none';
				//document.getElementById("searchResultNullPass1").style.display='block';
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
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
function getUsersAttendenceReport(){
	var startDay = document.getElementById("from-date-User-Atten").value;
	var endDay  = document.getElementById("to-date-User-Atten").value;
	var projectId = document.getElementById("projectListForAttendence").value;
	var username = document.getElementById("usrListInAttendenceReport").value;
	$.ajax({
		type: "POST",
		url: "getUserAttendanceBasedOnParam",
		data:{startDay:startDay,endDay:endDay,projectId:projectId,username:username},
		dataType: 'json',
		beforeSend: function() { $('#loaderGif').show(); },
        complete: function() { $('#loaderGif').hide(); },
		success: function(result){
		if(result.length >0){
			document.getElementById("searchResultNullPass1").style.display='none';
			document.getElementById("userAttnDataDiv").style.display='block';
			$('#otherUserAttenItemTbl tbody').empty();
			for (var i = 0; i < result.length; i++) {	
				var row = "";
							if(result[i].status == "Absent"){
								row = $("<tr class='danger'  />");
							}else if(result[i].status == "WeekOff"){
								row = $("<tr class='Warning'  />");					
							}else{
								row = $("<tr class='' />");
							}	
				 	$("#tab_logic_otherUserAttenItemApp").append(row); 
					row.append($("<td  class='EWTableElements'>" + result[i].date + "</td>"));
					row.append($("<td  class='EWTableElements'>" + result[i].username + "</td>"));
					row.append($("<td  class='EWTableElements'>" + result[i].fullname + "</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].shifttime+"</td>"));
					row.append($("<td  class='EWTableElements'>"+result[i].intime+"</td>"));
					row.append($("<td   class='EWTableElements'>"+result[i].outtime+"</td>"));
					row.append($("<td   class='EWTableElements'>"+result[i].punchedcount+"</td>"));
					if(result[i].deficienthours == undefined)
						row.append($("<td  class='EWTableElements' style='font-weight:400;color:green;'>"+result[i].extrahours+"</td>"));
					else
						row.append($("<td  class='EWTableElements' style='font-weight:400;color:red;'>"+result[i].deficienthours+"</td>"));
					row.append($("<td id='requestStatus"+i+"'  class='EWTableElements'>"+result[i].status+"</td>"));
			    }
			}else{
				document.getElementById("userExpenseItemDiv").style.display='none';
				//document.getElementById("searchResultNullPass1").style.display='block';
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function getUsersWeeklyAttendenceReport(){
	var startDate1 = document.getElementById("from-date-User-Atten-Week").value;
	if(startDate1 == undefined || startDate1 == null || startDate1 == ""){
		   alert("Please select start date");
		   return false;
	}
	var endDate1  = document.getElementById("to-date-User-Atten-Week").value;
	if(endDate1 == undefined || endDate1 == null || endDate1 == ""){
		   alert("Please select end date");
		   return false;
	}
	var username = document.getElementById("usrListInWeeklyAttendenceReport").value;
	var startDay = moment(startDate1, "YYYY-MM-DD").day(0).format("YYYY-MM-DD");
    var endDay =  moment(endDate1, "YYYY-MM-DD").day(6).format("YYYY-MM-DD");
	$.ajax({
		type: "POST",
		url: "getUserWeeklyAttendanceRprt",
		data:{startDay:startDay,endDay:endDay,username:username},
		dataType: 'json',
		beforeSend: function() { $('#loaderGif').show(); },
        complete: function() { $('#loaderGif').hide(); },
		success: function(result){
		if(result.length >0){
			document.getElementById("searchResultNullPass1").style.display='none';
			document.getElementById("userWeeklyAttnDataDiv").style.display='block';
			$('#otherUserWeeklyAttenItemTbl tbody').empty();
			for (var i = 0; i < result.length; i++) {	
				var row = "";
								if(i%2 == 0){
									row = $("<tr class='info' />");					
								}else{
									row = $("<tr class='' />");
								}	
				 	$("#tab_logic_otherUserWeeklyAttenItemApp").append(row); 
				 	row.append($("<td  class='EWTableElements'>" + (i+1) + "</td>"));
					row.append($("<td  class='EWTableElements'>" + result[i].weekinterval + "</td>"));
					row.append($("<td  class='EWTableElements'>" + result[i].username + "</td>"));
					row.append($("<td  class='EWTableElements'>" + result[i].fullname + "</td>"));
					row.append($("<td  class='EWTableElements'>"+result[i].workinghours+"</td>"));
					row.append($("<td   class='EWTableElements'>"+result[i].punchedhours+"</td>"));
					row.append($("<td   class='EWTableElements'>"+result[i].totaldiff+"</td>"));
			    }
			}else{
				//document.getElementById("viewWeeklyAttendanceReportDiv").style.display='none';
				//document.getElementById("searchResultNullPass1").style.display='block';
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function inOutSelect(inOutFlag){
	if(inOutFlag == "In"){
		$("#timepicker3").prop('disabled', true);
		$("#timepicker2").prop('disabled', false);
		$("#timepicker3").val('');
	}
	if(inOutFlag == "Out"){
		$("#timepicker2").prop('disabled', true);
		$("#timepicker2").val('');
		$("#timepicker3").prop('disabled', false);
	}
	if(inOutFlag == "Both"){
		$("#timepicker2").prop('disabled', false);
		$("#timepicker3").prop('disabled', false);
	}
}
function submitMOAF(){
	var moafCategory = document.getElementById("moafCategory").value;
	var date = document.getElementById("moafDateId").value;
	if(date == undefined || date == null || date == ""){
		   alert("Please select valid date");
		   return false;
	}
	var direction = document.getElementById("attendanceDirection").value;
	var inTime = document.getElementById("timepicker2").value;
    var outTime =  document.getElementById("timepicker3").value;
    if(direction == "Both"){
    	if(inTime == undefined || inTime == null || inTime == ""){
 		   alert("Please select valid in time");
 		   return false;
    	}
    	if(outTime == undefined || outTime == null || outTime == ""){
 		   alert("Please select valid out time");
 		   return false;
    	}
    }else if(direction == "In"){
    	if(inTime == undefined || inTime == null || inTime == ""){
  		   alert("Please select valid in time");
  		   return false;
     	}
    }else{
    	if(outTime == undefined || outTime == null || outTime == ""){
  		   alert("Please select valid out time");
  		   return false;
     	}
    }
    var reason =  document.getElementById("moafPurpose").value;
    if(reason == undefined || reason == null || reason == ""){
		   alert("Please select valid reason for missed punch record");
		   return false;
  	}
    $.ajax({
		type: "POST",
		url: "submitMOAFData",
		data:{date:date,direction:direction,inTime:inTime,outTime:outTime,reason:reason,moafCategory:moafCategory},
		dataType: 'json',
		beforeSend: function() { $('#loaderGif').show(); },
        complete: function() { $('#loaderGif').hide(); },
		success: function(result){
		    	alert(result);
		    	fillMOAFDiv();
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function getUserOwnMOAFData(){
	$.ajax({
		type: "POST",
		url: "getUsersPendingMOAFList",
		dataType: 'json',
		success: function(result){
			if(result.length > 0){
				var table = $("<table class='table table-stripped' style='background-color: #EBF5FB;' scrolling='auto'><thead style='background-color: #D4E6F1;'><tr><th>SrNo</th><th>Category</th><th>Date</th><th>Direction</th><th>In Time</th><th>Out Time</th><th>Submitted On</th><th>Reason</th><th>Status</th></tr></thead><tbody id='reqNumAttendenceBody'></tbody></table>");
				for(var i=0; i<result.length; i++){
				    var row = '';
				    if(i%2 == 0)
				    	row = $("<tr />");
				    else
				    	row = $("<tr class='info'/>");
				    $("#reqNumAttendenceBody").append(row);
				    row.append($("<td class='EWTableElements'>" + (i+1) + "</td>"));
				    row.append($("<td class='EWTableElements'>" + result[i].category + "</td>"));
					row.append($("<td class='EWTableElements'>" + result[i].date + "</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].direction +"</td>"));
					row.append($("<td  class='EWTableElements'>"+(result[i].intime).substring(11,16)+"</td>"));
					row.append($("<td  class='EWTableElements'>"+(result[i].outtime).substring(11,16)+"</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].reason+"</td>"));
					row.append($("<td  class='EWTableElements'>"+result[i].createdate+"</td>"));
					if(result[i].status == "Rejected")
						row.append($("<td  class='EWTableElements'><b style='color:red'>"+result[i].status+"</b></td>"));
					else if(result[i].status == "Approved")
						row.append($("<td  class='EWTableElements'><b style='color:green'>"+result[i].status+"</b></td>"));
					else
						row.append($("<td  class='EWTableElements'>"+result[i].status+"</td>"));
				    table.append(row);
				}
				$('#userOwnMOAFData').empty();
				$('#userOwnMOAFData').append(table);
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function approveUserAttendance(){
	$.ajax({
		type: "POST",
		url: "getUsersPendingMOAFRequest",
		dataType: 'json',
		success: function(result){
			if(result.length > 0){
				var table = $("<table class='table table-stripped' style='background-color: #EBF5FB;' scrolling='auto'><thead style='background-color: #D4E6F1;'><tr><th>SrNo</th><th>Username</th><th>Date</th><th>Category</th><th>Direction</th><th>In Time</th><th>Out Time</th><th>Submitted On</th><th>Reason</th><th>Status</th><th>Action</th></tr></thead><tbody id='reqNumApproveAttendenceBody'></tbody></table>");
				for(var i=0; i<result.length; i++){
				    var row = '';
				    if(i%2 == 0)
				    	row = $("<tr />");
				    else
				    	row = $("<tr class='info'/>");
				    $("#reqNumApproveAttendenceBody").append(row);
				    row.append($("<td class='EWTableElements'>" + (i+1) + "</td>"));
				    row.append($("<td class='EWTableElements'>" + result[i].fullname+" ("+result[i].username + ") </td>"));
					row.append($("<td class='EWTableElements'>" + result[i].date + "</td>"));
					row.append($("<td class='EWTableElements'>" + result[i].category + "</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].direction +"</td>"));
					row.append($("<td  class='EWTableElements'>"+(result[i].intime).substring(11,16)+"</td>"));
					row.append($("<td  class='EWTableElements'>"+(result[i].outtime).substring(11,16)+"</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].reason+"</td>"));
					row.append($("<td  class='EWTableElements'>"+result[i].createdate+"</td>"));
					if(result[i].status == "Rejected")
						row.append($("<td  class='EWTableElements'><b style='color:red'>"+result[i].status+"</b></td>"));
					else if(result[i].status == "Approved")
						row.append($("<td  class='EWTableElements'><b style='color:green'>"+result[i].status+"</b></td>"));
					else
						row.append($("<td  class='EWTableElements'>"+result[i].status+"</td>"));
					row.append($("<td  style='item-align:center;'>" +
							"<a href='#!' class='btn btn-success btn-sm' title='Approve MOAF' style='margin:2px;padding:2px;border-radius:6px;' onclick=approveRejectMOAF('"+result[i].moafid+"','true');return false;>Approve</a>" +
							"<a href='#!' class='btn btn-danger btn-sm' title='Reject' style='margin:2px;padding:2px;border-radius:6px;' onclick=approveRejectMOAF('"+result[i].moafid+"','false');return false;>Reject</a></td>"));
				    table.append(row);
				}
				$('#userPendingMOAF').empty();
				$('#userPendingMOAF').append(table);
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function approveRejectMOAF(moafid,approveFlag){
		var ret=confirm("Are you sure?");
		if(ret == false)
			return false;
	$.ajax({
		type: "POST",
		url: "approveMOAFData",
		data:{moafid:moafid,approveFlag:approveFlag},
		dataType: 'json',
		beforeSend: function() { $('#loaderGif').show(); },
        complete: function() { $('#loaderGif').hide(); },
		success: function(result){
		    	alert(result);
		    	approveUserAttendance();
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function viewUserYearAttnRequest(){
	var uid =  document.getElementById("usrListInYearlyAttendenceReport").value;
	var year =  document.getElementById("yearListInYearAttendenceReport").value;
	if(uid == "select" || uid=="" || uid==undefined){
		alert("Please select Username.");
		return false;
	}
	/*var top=0;
    var left=0;
    var height=screen.availHeight;
    var width=screen.availWidth;
    window.open('yearlyUserReport?uid='+uid+"&year="+year ,'AttendanceScreen','scrollbars=yes,resizable=yes,toolbar=no,Addressbar=no,menubar=no,status=yes,fullscreen=yes,location=no,top='+top+',left='+left+',height='+height+',width='+width);*/
    var params = { 'uid' : uid , 'year':year };
    openNewRequest("yearlyUserReport",params);
}
function getUsersExceptionAttendenceReport(){
	var startDate1 = document.getElementById("from-date-User-Atten-Exception").value;
	if(startDate1 == undefined || startDate1 == null || startDate1 == ""){
		   alert("Please select start date");
		   return false;
	}
	var endDate1  = document.getElementById("to-date-User-Atten-Exception").value;
	if(endDate1 == undefined || endDate1 == null || endDate1 == ""){
		   alert("Please select end date");
		   return false;
	}
	var username = document.getElementById("usrListInExceptionAttendenceReport").value;
	$.ajax({
		type: "POST",
		url: "getUserExceptionAttendanceRprt",
		data:{startDay:startDate1,endDay:endDate1,username:username},
		dataType: 'json',
		beforeSend: function() { $('#loaderGif').show(); },
        complete: function() { $('#loaderGif').hide(); },
		success: function(result){
		if(result.length >0){
			document.getElementById("searchResultNullPass1").style.display='none';
			document.getElementById("userExceptionAttnDataDiv").style.display='block';
			$('#otherUserExceptionAttenItemTbl tbody').empty();
			for (var i = 0; i < result.length; i++) {	
				var row = "";
								if(i%2 == 0){
									row = $("<tr class='info' />");					
								}else{
									row = $("<tr class='' />");
								}	
				 	$("#tab_logic_otherUserExceptionAttenItemApp").append(row); 
				 	row.append($("<td class='EWTableElements'>" + (i+1) + "</td>"));
					row.append($("<td class='EWTableElements'>" + result[i].username + "</td>"));
					row.append($("<td class='EWTableElements'>" + result[i].fullname + "</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].totalWorkingHH+"</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].desiredWorkingHH+"</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].punchedhours+"</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].totaldiff+"</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].onLeave+"</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].holiday+"</td>"));
			    }
			/*$('#otherUserExceptionAttenItemTbl').dataTable({
				paging:false
			});*/
			}else{
				//document.getElementById("viewWeeklyAttendanceReportDiv").style.display='none';
				//document.getElementById("searchResultNullPass1").style.display='block';
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function changeToHHMMSS(sec) {
    var sec_num = parseInt(sec, 10); // don't forget the second parm
    var hours   = Math.floor(sec_num / 3600);
    var minutes = Math.floor((sec_num - (hours * 3600)) / 60);
    var seconds = sec_num - (hours * 3600) - (minutes * 60);

    if (hours   < 10) {hours   = "0"+hours;}
    if (minutes < 10) {minutes = "0"+minutes;}
    if (seconds < 10) {seconds = "0"+seconds;}
    var time    = hours+':'+minutes+':'+seconds;
    return time;
}
function changeToSeconds( time ) {
    var parts = time.split(':');
    return (+parts[0]) * 60 * 60 + (+parts[1]) * 60 + (+parts[2]); 
}