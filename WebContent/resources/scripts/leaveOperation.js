var unpaidLeaveListTypes = [];
var applyDaysBeforeArray = '';
var applyMaxDayArray = '';
var allLeaveTypeData = '';
var dates = {
    convert:function(d) {
        return (
            d.constructor === Date ? d :
            d.constructor === Array ? new Date(d[0],d[1],d[2]) :
            d.constructor === Number ? new Date(d) :
            d.constructor === String ? new Date(d) :
            typeof d === "object" ? new Date(d.year,d.month,d.date) :
            NaN
        );
    },
    compare:function(a,b) {
        return (
            isFinite(a=this.convert(a).valueOf()) &&
            isFinite(b=this.convert(b).valueOf()) ?
            (a>b)-(a<b) :
            NaN
        );
    },
    inRange:function(d,start,end) {
        return (
            isFinite(d=this.convert(d).valueOf()) &&
            isFinite(start=this.convert(start).valueOf()) &&
            isFinite(end=this.convert(end).valueOf()) ?
            start <= d && d <= end :
            NaN
        );
    }
}
function newLeaveRequest(){
	/*var top=0;
    var left=0;
    var height=screen.availHeight;
    var width=screen.availWidth;
    window.open('newLeaveRequest?pid='+pid ,'ProjectScreen','scrollbars=yes,resizable=yes,toolbar=no,Addressbar=no,menubar=no,status=yes,fullscreen=yes,location=no,top='+top+',left='+left+',height='+height+',width='+width);*/
    var pid = "";
    var params = { 'pid' : pid };
    openNewRequest("newLeaveRequest",params);
}
function fetchNewLeaveNumber(uid){
	$.ajax({					
		type: "POST",
		url: "leaveGetRequestNo",
		data:{uid:uid},
		dataType: 'json',
		success: function(data) 
		{
			$("#requestNumber").val(data.leaveId);
			
		},
		error: function(jqXHR, textStatus) 
		{
			alert("ERROR_Request_NO: "+textStatus);
		}
		});
}
function getLeaveTypeList(id){
	$.ajax({
		type: "POST",
		url: "getAllLeaveTypeList",
		dataType: 'json',
		data:{uid:applyLeaveForUser},
		success: function(data){
			var select = document.getElementById(id);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Type','select'));
			for (var i = 0; i < data.length; i++) {
				if(applyLeaveForUser != "" && applyLeaveForUser.length > 0){
					console.log(data[i].leaveFlag);
					if(data[i].leaveFlag == "Y"){
						select.add(new Option(data[i].leaveName,data[i].leaveCode));
					}
				}else{
					select.add(new Option(data[i].leaveName,data[i].leaveCode));
				}
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});	
}
function fetchLeaveParam(uid){
	var leaveTypes = "";
	$.ajax({
		type: "POST",
		url: "getLeaveParamForUser",
		data:{uid:uid},
		dataType: 'json',
		success: function(data){
			allLeaveTypeData = data;
			var countPaidTypeLeave = 0;
			for(var i=0;i<data.length;i++){
				if("NA" != data[i].currLeaveBal)
					countPaidTypeLeave = countPaidTypeLeave + 1;	
			}
			$('#leaveBalTable tr').remove();
			var head = $("<tr />");
			$('#leaveBalTable').append(head);
			for(var i=0;i<data.length;i++){
				head.append("<td id='leaveHead"+data[i].currLeaveId+"' style='text-align:center;'><b>"+data[i].currLeaveName+"</b>("+data[i].cumulativeGroupName+")<br>"+ data[i].currLeaveDesc +"</td>");
				//leaveTypes = leaveTypes + data[i].currLeaveName + "("+ data[i].currLeaveDesc + ") "; 
			}
			var row = $("<tr />");
			$('#leaveBalTable').append(row);
			for(var i=0;i<data.length;i++){
				row.append("<td id='leaveHeadBal"+data[i].currLeaveId+"' style='text-align:center;'><strong style='color:blue;'>"+data[i].currLeaveBal+"</strong></td>");
			}
			$('#currLeaveYear').html(data[0].currLeaveYear);
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});	
}
function addNewLeaveRow(){
	  var queryCount= $('#tab_logicExp tr').length - 1;
	  $('#query'+queryCount).html("" +
			  "<td><select class='form-control' name='leaveList["+queryCount+"].leaveCode' id='leaveList["+queryCount+"].leaveCode' onchange=setLeaveName('"+queryCount+"')><option value='select'  selected='selected'>Select Type</option></select><input type='hidden' name='leaveList["+queryCount+"].leaveName' id='leaveList["+queryCount+"].leaveName' value='' /></td>"+
			  "<td><input type='text' name='leaveList["+queryCount+"].leaveStartDate' id='leaveList["+queryCount+"].leaveStartDate' class='form-control dateClass' placeholder='Start Date'/></td>"+
			  "<td><input type='text' name='leaveList["+queryCount+"].leaveEndDate' id='leaveList["+queryCount+"].leaveEndDate' class='form-control dateClass' placeholder='End Date' /></td>"+
			  "<td><select class='form-control' onchange='calculateLeaveDays1("+queryCount+");return false;' name='leaveList["+queryCount+"].fullDayFlag' id='leaveList["+queryCount+"].fullDayFlag'><option value='select'  selected='selected'>Select</option><option value='Full Day'>Full Day</option><option value='1st Half'>1st Half</option><option value='2nd Half' >2nd Half</option></select></td>"+
			  "<td><input type='text' name='leaveList["+queryCount+"].leaveDays' id='leaveList["+queryCount+"].leaveDays' maxlength='2' class='form-control' placeholder='0.0' readOnly='true' onkeyup='calculateDays();return false;'/></td>"+
			  "<td><input type='text' name='leaveList["+queryCount+"].leavePurpose' id='leaveList["+queryCount+"].leavePurpose' class='form-control' placeholder='Purpose' /></td>"+
			  "<td><a href='#!' onclick=deleteLeaveRow('"+queryCount+"')><span class='glyphicon glyphicon-trash'></span></a></td>");
		$(".dateClass").datepicker({
			format: 'yyyy-mm-dd'
		});
	  	$("input[id='leaveList["+queryCount+"].leaveStartDate']").change(function(){
			calculateLeaveDays1("'"+queryCount+"'");return false;
		});
		$("input[id='leaveList["+queryCount+"].leaveEndDate']").change(function(){
			calculateLeaveDays1("'"+queryCount+"'");return false;
		});
		getLeaveTypeList("leaveList["+queryCount+"].leaveCode");
		$('#tab_logicExp').append('<tr id="query'+(queryCount+1)+'"></tr>');
		queryCount++;
}
function deleteLeaveRow(r){
	var queryCount= $('#tab_logicExp tr').length - 1; 
	  var totalCells = 0;
	  for(var l = 0;l<=queryCount;l++){
		  totalCells = totalCells + (document.getElementById('tab_logicExp').rows[l].cells.length);
	  }	
	  if(totalCells <= 7){
		  alert("Atleast one row mandatory");
		  return false;
	  }
	  $("#query"+r).html('');
}
/*Date.prototype.addDays = function(days) {
    var dat = new Date(this.valueOf());
    dat.setDate(dat.getDate() + days);
    return dat;
};*/
function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}
function getDates(startDate1, stopDate1) {
    var dateArray = new Array();
    var startDate = new Date(startDate1);
    var stopDate = new Date(stopDate1);
    var currentDate = startDate;
    while (currentDate <= stopDate) {
        dateArray.push(formatDate(currentDate));
        var dat = new Date(currentDate);
        dat.setDate(dat.getDate() + 1);
        currentDate = new Date(dat);
    }
    return dateArray;
}
function checkPrevLeaveList(id){
	var status = false;
	var totalRow = $('#'+id+' tr').length - 1;
	var dateArr = new Array();
	for (var i = 0;i<totalRow; i++) {
	if($('#'+id+' tr:nth-child('+(i+1)+') td').length > 0){
		var tempStartDate = document.getElementById('leaveList['+ i +'].leaveStartDate').value;
		var tempEndDate = document.getElementById('leaveList['+ i +'].leaveEndDate').value;
		   if(tempStartDate == undefined || tempStartDate == null || tempStartDate == "" || tempEndDate == "" || tempEndDate == undefined || tempEndDate == null){
			   alert("Please select valid date");
			   return false;
		   }							   
		   var dateArrTemp = getDates(tempStartDate, tempEndDate);
		   dateArr.push.apply(dateArr, dateArrTemp);
		}  
	}
	var dateList = dateArr.filter( onlyUnique );
	$.ajax({
		type: "POST",
        url: "checkPrevAppliedLeaveList",
        dataType: 'json',
        type: "POST",
        async:false,
        data: {dateList:dateList,uid:applyLeaveForUser},
        success: function (data) {
        	var dateString = "";
        	//console.log(data.length);
        	if(data.length > 0){
        		for(var i=0;i<data.length;i++){
        			if(data.length == (i+1))
        				dateString = dateString + data[i];
        			else
        				dateString = dateString + data[i] + ",";
        		}
        		alert("Leave already applied for selcted dates.\n ["+ dateString +"]");
        		status = false;
        	}else{
        		status = true;
        	}
        },
        error: function (e) {
            console.log("ERROR : ", e);
            return false;
        }
    });
	return status;
}
function validateLeaveTable(id){
	var totalRow = $('#'+id+' tr').length - 1;
	var arrLeaveCode = [ ];
	var arrLeaveDays = [ ];
	for (var i = 0;i<totalRow; i++) {
		if($('#'+id+' tr:nth-child('+(i+1)+') td').length > 0){
		var tempExpValue = document.getElementById('leaveList['+ i +'].leaveCode').value;
		var tempDaysValue = document.getElementById('leaveList['+ i +'].leaveDays').value;
		   if(tempExpValue == undefined || tempExpValue == null || tempExpValue == "select"){
			   viewAlertDiv("Please select valid Leave Head");
			   
			   return false;
		   }							   
		   arrLeaveCode.push(tempExpValue);
		   arrLeaveDays.push(tempDaysValue);
		}  
	}
	var dateArr = new Array();
	for (var i = 0;i<totalRow; i++) {
	if($('#'+id+' tr:nth-child('+(i+1)+') td').length > 0){
		var tempStartDate = document.getElementById('leaveList['+ i +'].leaveStartDate').value;
		var tempEndDate = document.getElementById('leaveList['+ i +'].leaveEndDate').value;
		var tempLeaveValue = document.getElementById('leaveList['+ i +'].leaveCode').value;
		   if(tempStartDate == undefined || tempStartDate == null || tempStartDate == "" || tempEndDate == "" || tempEndDate == undefined || tempEndDate == null){
			   viewAlertDiv("Please select valid date");
			   return false;
		   }							   
		   var dateArrTemp = getDates(tempStartDate, tempEndDate);
		   dateArr.push.apply(dateArr, dateArrTemp);
		   var uniqueArr2 = dateArr.filter( onlyUnique );
		   if(uniqueArr2.length < dateArr.length){
			   viewAlertDiv("One or more date selected more than one time");
			   return false;
		   }
		   if(validateApplyMinDayBefore(dateArrTemp,tempLeaveValue)){}else{return false;};
		}  
	}
	var uniqueArr = arrLeaveCode.filter( onlyUnique );
	for(var i=0;i<uniqueArr.length;i++){
		var tempCount = Number ('0.0');
		for(var j=0;j<arrLeaveCode.length;j++){
			if(uniqueArr[i] == arrLeaveCode[j]){
				tempCount = tempCount + Number ( arrLeaveDays[j] ); 
			}
		}
		if(checkLeaveGroup(uniqueArr[i])){
			if(applyLeaveForUser.length > 0){
				
			}else if(tempCount > Number ( $("#leaveHeadBal"+uniqueArr[i]).text() )){
				viewAlertDiv("Not enough balance for one or more leave type.");
				return false;
			}
		}
	}
	for (var i = 0;i<totalRow; i++) {
	if($('#'+id+' tr:nth-child('+(i+1)+') td').length > 0){
		
	   var tempExpRemarkValue = document.getElementById('leaveList['+ i +'].leavePurpose').value;
	   if(tempExpRemarkValue == undefined || tempExpRemarkValue == null || tempExpRemarkValue == ""){
		   viewAlertDiv("Please enter valid purpose for leave");
		   return false;
	   }
	   var valPattern = /^[0-9a-zA-Z ]+$/;
	   if(!(tempExpRemarkValue.match(valPattern))){
		   //viewAlertDiv("Do not use special characters in leave purpose");
		   //return false;
	   }
	   var tempFlagValue = document.getElementById('leaveList['+ i +'].fullDayFlag').value;
	   if(tempFlagValue == "select" || tempFlagValue == null || tempFlagValue == ""){
		   viewAlertDiv("Please select full/half day");
		   return false;
	   }
	   if(calculateLeaveDays1(i)){}else{
		   return false;
	   }
	  }  
	}
	return true;
}
function calculateLeaveDays1(i){
	//alert(i);
	var startDate = new Date(document.getElementById("leaveList["+i+"].leaveStartDate").value);
	var endDate = new Date(document.getElementById("leaveList["+i+"].leaveEndDate").value);
	var fullDayFlag = document.getElementById("leaveList["+i+"].fullDayFlag").value;
	var dateDiff = Math.round((endDate-startDate)/(1000*60*60*24));
	var leaveType = document.getElementById("leaveList["+i+"].leaveCode").value;
	var totalBalance = Number( $("#leaveHeadBal"+leaveType).text() );
	if(applyLeaveForUser.length > 0){
		for(var z=0;z<allLeaveTypeData.length;z++){
			if(allLeaveTypeData[z].currLeaveId == leaveType){
				var tem = allLeaveTypeData[z].currLeaveCumulativeGroup;
				totalBalance = Number( $("#leaveHeadBal"+tem).text() );
			}
			}
	}//alert("8888888888========"+$("#leaveHeadBal"+leaveType).text() );
	if(leaveType == "select"){
		viewAlertDiv("Kindly select leave type");
		return false;
	}
	if(startDate == "" || startDate == undefined || startDate == 'Invalid Date'){
		viewAlertDiv("Select start date");
		document.getElementById("leaveList["+i+"].leaveDays").value = '0.0';
		return false;
	}
	if(endDate == "" || endDate == undefined || endDate == 'Invalid Date'){
		document.getElementById("leaveList["+i+"].leaveDays").value = '0.0';
		return false;
	}
	if(endDate < startDate){
		//alert("End date can not be after start then.");
		viewAlertDiv("End date can not be after start then.");
		document.getElementById("leaveList["+i+"].leaveEndDate").value = "";
		return false;
	}else{
			if(endDate.getTime() === startDate.getTime()){
				
				if(fullDayFlag == 'Full Day'){
				  if(checkLeaveGroup(leaveType)){
					if(totalBalance >= 1.0){
						document.getElementById("leaveList["+i+"].leaveDays").value = '1.0';
					}else{
						viewAlertDiv("Not enough leave balance");
						document.getElementById("leaveList["+i+"].leaveDays").value = '0.0';
						return false;
					}
					}else{
						document.getElementById("leaveList["+i+"].leaveDays").value = '1.0';
					}
				}else if(fullDayFlag == "select"){
					document.getElementById("leaveList["+i+"].leaveDays").value = '0.0';
					return false;
				}else{
					if(checkLeaveGroup(leaveType)){
						if(totalBalance >= 0.5)
							document.getElementById("leaveList["+i+"].leaveDays").value = '0.5';
						else{
							viewAlertDiv("Not enough leave balance");
							document.getElementById("leaveList["+i+"].leaveDays").value = '0.0';
							return false;
						}
					}else{
						document.getElementById("leaveList["+i+"].leaveDays").value = '0.5';
					}
				}	
			}else{
				if(fullDayFlag == 'Full Day'){
					if(checkLeaveGroup(leaveType)){
						if(totalBalance >= (dateDiff+1)){
							document.getElementById("leaveList["+i+"].leaveDays").value = 1.0 * (dateDiff+1);
							valiDateMaxDaysInLeaveType(leaveType,(dateDiff+1));
						}else{
							viewAlertDiv("Not enough leave balance");
							document.getElementById("leaveList["+i+"].leaveDays").value = '0.0';
							return false;
						}
					}else{
						document.getElementById("leaveList["+i+"].leaveDays").value = 1.0 * (dateDiff+1);
						valiDateMaxDaysInLeaveType(leaveType,(dateDiff+1));
					}
				}if(fullDayFlag == '1st Half' || fullDayFlag == '2nd Half'){
					if(checkLeaveGroup(leaveType)){
						if(totalBalance >= (0.5 * (dateDiff+1))){
							document.getElementById("leaveList["+i+"].leaveDays").value = 0.5 * (dateDiff+1);
							valiDateMaxDaysInLeaveType(leaveType,(0.5*(dateDiff+1)));
						}else{
							viewAlertDiv("Not enough leave balance");
							document.getElementById("leaveList["+i+"].leaveDays").value = '0.0';
							return false;
						}
					}else{
						document.getElementById("leaveList["+i+"].leaveDays").value = 0.5 * (dateDiff+1);
						valiDateMaxDaysInLeaveType(leaveType,(0.5*(dateDiff+1)));
					}
				}if(dateDiff == NaN){
					document.getElementById("leaveList["+i+"].leaveDays").value = '0.0';
				}
			}
	}
	return true;
}
function pastLeaveList(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewPastLeavesDiv").style.display='block';
	document.getElementById('viewPastLeavesDiv').setAttribute("style","min-height:500px");
	$("#viewPastLeavesDiv").appendTo("#dataDiv");
	getPastUsersLeaveRequestList('pastUserRequest','All');
}
function getPastUsersLeaveRequestList(filterVar,allFlag){
	$.ajax({
		type: "POST",
		url: "getPendingLeaveRequestList",
		data:{filterVar:filterVar,allFlag:allFlag},
		dataType: 'json',
		beforeSend: function() { $('#loaderGif').show(); },
        complete: function() { $('#loaderGif').hide(); },
		success: function(result){
		if(result.length >0){
			document.getElementById("searchResultNullPass1").style.display='none';	
			document.getElementById("userOwnLeaveItemDiv").style.display='block';
			document.getElementById('userOwnLeaveItemDiv').setAttribute("style","min-height:550px");
			$("#userOwnLeaveItemDiv").appendTo("#viewPastLeavesDiv");
			$('#userOwnLeaveItemTbl tbody').empty();
			for (var i = 0; i < result.length; i++) {	
				var row = "";
							if(result[i].requeststatus == "Withdraw")
								row = $("<tr class='active'  />");
								else{
									if(result[i].requeststatus == "Review Pending")
										row = $("<tr class='info'  />");
								else{
										if(result[i].requeststatus == "Approval Pending")
											row = $("<tr class='success'  />");
										else{
										if(result[i].requeststatus == "Rejected")
											row = $("<tr class='danger'  />");
								else{
											if(result[i].requeststatus == "Review Failed")
												row = $("<tr class='danger'  />");
											else
												row = $("<tr class='' />");
								}
								}
								}
							}
				 	$("#tab_logic_userOwnLeaveItemApp").append(row); 
				 	row.append($("<td class='clickable' data-toggle='collapse' id='rowLeaveUserOwn"+i+"' data-target='.rowLeaveUserOwn"+i+"' nowrap class='EWTableElements'><a href='#!' title='Expand' style='color:red;margin-left:5px;' onclick=showUserLeaveDetails('"+ result[i].requestnumber +"','.rowLeaveUserOwn"+i+"','iconUserOther"+i+"');><span id='iconUserOther"+i+"' class='glyphicon glyphicon-plus-sign' style='color:#2471A3;'></span></a></td>"));
				 	row.append($("<td nowrap class='EWTableElements' title='" + result[i].requestnumber + "'>" + result[i].requestnumber + "</td>"));
					row.append($("<td nowrap class='EWTableElements'>"+result[i].creationdate+"</td>"));
					row.append($("<td  nowrap class='EWTableElements'>"+result[i].totalLeaveDays+"</td>"));
					row.append($("<td id='requestLeaveStatusUser"+i+"' nowrap class='EWTableElements'>"+result[i].requeststatus+"</td>"));
					row.append($("<td  nowrap class='EWTableElements' title="+result[i].approverremark+">"+result[i].approverremark+"<input type='hidden' id='remarkUser"+i+"' value=''/></td>"));
					row.append($("<td  nowrap class='EWTableElements'>"+result[i].lastmodifiedby+"</td>"));
					var tempTodayDate = new Date();
					var tempLeaveStartDate = new Date(result[i].leavestartdate);
					var temp30daysBeforeCurrDate = moment(tempTodayDate, "YYYY-MM-DD").day(leaveReversalDayParam).format("YYYY-MM-DD");
					//var temp30daysBeforeCurrDate = tempTodayDate.moment().add(-30, 'days');
					//console.log(tempTodayDate);console.log(tempLeaveStartDate);console.log(temp30daysBeforeCurrDate);
					if(dates.compare(tempLeaveStartDate,tempTodayDate) == 1){
						row.append($("<td nowrap class='EWTableElements text-center'><a href='#!' title='Delete Leave' style='color:blue;margin-left:5px;' onclick=approveRejectLeaveReq('"+ result[i].requestnumber +"','Deleted','requestLeaveStatusUser"+i+"','remarkUser"+i+"');><span class='glyphicon glyphicon-trash' style='color:black;'></span></a></td>"));
					}else{
						if(dates.inRange (tempLeaveStartDate,temp30daysBeforeCurrDate,tempTodayDate)){
							if(result[i].requeststatus == "Approved"){
								row.append($("<td nowrap class='EWTableElements text-center'><a href='#!' title='Leave Reversal' style='color:blue;margin-left:5px;' onclick=approveRejectLeaveReq('"+ result[i].requestnumber +"','Reversal','requestLeaveStatusUser"+i+"','remarkUser"+i+"');><span class='glyphicon glyphicon-edit' style='color:red;'></span></a></td>"));
							}else{
								row.append($("<td nowrap class='EWTableElements'></td>"));	
							}	
						}else{
							row.append($("<td nowrap class='EWTableElements'></td>"));
						}
					}
					/*if(result[i].requeststatus == "Approved"){
						row.append($("<td nowrap class='EWTableElements'><a href='#!' title='Leave Reversal' style='color:blue;margin-left:5px;' onclick=approveRejectLeaveReq('"+ result[i].requestnumber +"','Reversal','requestLeaveStatusUser"+i+"','remarkUser"+i+"');><span class='glyphicon glyphicon-edit' style='color:red;'></span></a></td>"));
					}else{
						row.append($("<td nowrap class='EWTableElements'></td>"));	
					}*/
					var row1 = $("<tr class='rowLeaveUserOwn"+i+"' />");
					$("#tab_logic_userOwnLeaveItemApp").append(row1);
			    }
			filterAnyTableByInput("Approval Pending",'userOwnLeaveItemTbl','4');
			$("#leaveFilter").val("Approval Pending").change();
			}else{
				document.getElementById("userOwnLeaveItemDiv").style.display='none';
				//document.getElementById("searchResultNullPass1").style.display='block';	
			}
		
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});

}
function showUserLeaveDetails(reqNumber,rowid,expandId){
	//var expandId = "iconUser" + rowid.substring(rowid.length-1,rowid.length);
	 $("#"+expandId).toggleClass('glyphicon-minus-sign').toggleClass('glyphicon-plus-sign');
	 if($("#"+expandId).css('color')=="rgb(36, 113, 163)"){$("#"+expandId).css('color', '#E74C3C');}else{$("#"+expandId).css('color', '#2471A3');}
	$.ajax({
		type: "POST",
		url: "getLeaveDetailByReqNumber",
		data:{reqNumber:reqNumber},
		dataType: 'json',
		success: function(result){
			$(rowid).children('td').remove();
			$(rowid).append($("<td colspan='10'>" +
					"<table class='table table-stripped' style='background-color: #EBF5FB;' scrolling='auto' id='reqNumLeaveUserData"+rowid.substring(rowid.length-1,rowid.length)+"'><thead style='background-color: #D4E6F1;'><tr><th>Leave Head</th><th>Start Date</th><th>End Date</th><th>Days</th><th>Full/Half Day</th><th>Purpose</th></tr></thead><tbody id='reqNumLeaveUserDataBody"+rowid.substring(rowid.length-1,rowid.length)+"'></tbody></table></td>"));
			for (var i = 0; i < result.length; i++) {
				var row = $("<tr />");
				$("#reqNumLeaveUserDataBody"+rowid.substring(rowid.length-1,rowid.length)).append(row);
				row.append($("<td class='EWTableElements'>" + result[i].leavehead + "</td>"));
				row.append($("<td class='EWTableElements'>"+result[i].startdate +"</td>"));
				row.append($("<td nowrap class='EWTableElements'>"+result[i].enddate+"</td>"));
				row.append($("<td nowrap class='EWTableElements'>"+result[i].days+"</td>"));
				row.append($("<td nowrap class='EWTableElements'>"+result[i].fulldayflag+"</td>"));
				/*if(result[i].fulldayflag == "N")
					row.append($("<td nowrap class='EWTableElements'>Half Day</td>"));
				else
					row.append($("<td nowrap class='EWTableElements'>Full Day</td>"));*/
				row.append($("<td nowrap class='EWTableElements'>"+result[i].purpose+"</td>"));
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});	
	
}
function pendingLeaveList(leaveStatus){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewPendingLeavesDiv").style.display='block';
	document.getElementById('viewPendingLeavesDiv').setAttribute("style","min-height:500px");
	$("#viewPendingLeavesDiv").appendTo("#dataDiv");
	getPendingLeaveRequestListJS('pendingUserRequest','All',leaveStatus);
}
function viewModalOtherLeaves(empName,requestNumber,username,leaveDays){
	$('#viewClashedLeaveModal').modal('show');
	var empName1 = empName.replace("$"," ");
	$('#requestLeaveId').html("Emp Name::<b>"+empName1+"</b>Request No::<b>"+requestNumber+"</b> Total Days:: <b>"+leaveDays+"</b>");
	$.ajax({
		type: "POST",
		url: "getEmpProjectList",
		dataType: 'json',
		data:{username:username},
		beforeSend: function() { $('#loaderGif').show(); },
        complete: function() { $('#loaderGif').hide(); },
        
		success: function(result){
			$('#projectDivForLeave').empty();
			for (var i = 0; i < result.length; i++) {	
				$('#projectDivForLeave').append("<input type='radio' id='"+result[i].projectId+"' name='projectForLeave' onchange=viewOtherLeaves('"+requestNumber+"','"+result[i].projectId+"'); />"+result[i].projectName+"<br>");
			}
			$('#projectDivForLeave').append("<input type='radio' id='projectResetId' name='projectForLeave' checked onchange=viewOtherLeaves('"+requestNumber+"',''); />Reset Filter<br>");
			viewOtherLeaves(requestNumber,"");
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function viewOtherLeaves(requestNumber,projectId){
	
	$.ajax({
		type: "POST",
		url: "getEmpListOnLeaveofReqNumber",
		dataType: 'json',
		data:{requestNumber:requestNumber,projectId:projectId},
		beforeSend: function() { $('#loaderGif').show(); },
        complete: function() { $('#loaderGif').hide(); },
        
		success: function(result){
			$('#empLeaveOnSameDayTable tbody').empty();
			for (var i = 0; i < result.length; i++) {	
				var row = $("<tr class='info'  />");
				 	$("#tab_logic_empLeaveOnSameDay").append(row); 
				 	row.append($("<td class='EWTableElements'>"+(i+1)+"</td>"));
				 	row.append($("<td class='EWTableElements' title='"+result[i].date+"'>" + result[i].date + "</td>"));
				 	row.append($("<td class='EWTableElements'>"+result[i].leavehead+"</td>"));
				 	row.append($("<td class='EWTableElements'>"+result[i].fulldayflag+"</td>"));
				 	var temp = result[i].userslist;
				 	temp = temp.replace(/--/g, '<b>(');
				 	temp = temp.replace(/,/g, '</b><br>');
				 	temp = temp.replace(/_/g, ')-');
					row.append($("<td class='EWTableElements'>"+temp.substring(1,temp.length - 1)+"</td>"));
			    }
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function getPendingLeaveRequestListJS(filterVar,allFlag,leaveStatus){
	$.ajax({
		type: "POST",
		url: "getPendingLeaveRequestList",
		data:{filterVar:filterVar,allFlag:allFlag},
		dataType: 'json',
		beforeSend: function() { $('#loaderGif').show(); },
        complete: function() { $('#loaderGif').hide(); },
		success: function(result){
		if(result.length >0){
			document.getElementById("searchResultNullPass1").style.display='none';	
			document.getElementById("pendingLeaveItemDiv").style.display='block';
			document.getElementById('pendingLeaveItemDiv').setAttribute("style","min-height:550px");
			$("#pendingLeaveItemDiv").appendTo("#viewPendingLeavesDiv");
			$('#pendingLeaveItemTbl tbody').empty();
			for (var i = 0; i < result.length; i++) {	
				var row = "";
							if(result[i].requeststatus == "Withdraw")
								row = $("<tr class='active'  />");
								else{
									if(result[i].requeststatus == "Review Pending")
										row = $("<tr class='info'  />");
								else{
										if(result[i].requeststatus == "Approval Pending")
											row = $("<tr class='success'  />");
										else{
										if(result[i].requeststatus == "Rejected")
											row = $("<tr class='danger'  />");
								else{
											if(result[i].requeststatus == "Review Failed")
												row = $("<tr class='danger'  />");
											else
												row = $("<tr class='' />");
								}
								}
								}
							}
				 	$("#tab_logic_pendingLeaveItemApp").append(row);
				 	row.append($("<td class='clickable' data-toggle='collapse' id='rowLeaveUser"+i+"' data-target='.rowLeavesUser"+i+"' nowrap class='EWTableElements'><a href='#!' title='Expand' style='color:red;margin-left:5px;' onclick=showUserLeaveDetails('"+ result[i].requestnumber +"','.rowLeavesUser"+i+"','iconUserOwn"+i+"');><span id='iconUserOwn"+i+"' class='glyphicon glyphicon-plus-sign' style='color:#2471A3;'></span></a></td>"));
				 	row.append($("<td nowrap class='EWTableElements' title='"+result[i].requestnumber+"'>" + result[i].requestnumber + "</td>"));
				 	row.append($("<td nowrap class='EWTableElements' id='reqLeaveFullName"+i+"'>"+result[i].userfullname+"</td>"));
					row.append($("<td nowrap class='EWTableElements'>"+result[i].creationdate+"</td>"));
					row.append($("<td  nowrap class='EWTableElements'>"+result[i].totalLeaveDays+"</td>"));
					row.append($("<td id='requestLeaveStatusUser"+i+"' nowrap class='EWTableElements'>"+result[i].requeststatus+"</td>"));
					row.append($("<td style='align:center;'>"+result[i].lastmodifiedon+"<input type='hidden' id='leaveremark"+i+"' /></td>"));
					if(result[i].requeststatus == "Approved"){
						row.append($("<td colspan='2' style='text-align:center'>NA</td>"));
					}else{
						row.append($("<td colspan='2' style='text-align:center'>" +
					"<button class='btn btn-info btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='View Users on same days leave' onclick=viewModalOtherLeaves('"+(result[i].userfullname).replace(" ","$")+"','"+result[i].requestnumber+"','"+result[i].username+"','"+result[i].totalLeaveDays+"');><i class='glyphicon glyphicon-user'></i></button>" +
					"<button class='btn btn-warning btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Request Meeting'  onclick=openMeetingModal('"+ result[i].requestnumber +"','"+(result[i].userfullname).replace(" ","$")+"');><i class='glyphicon glyphicon-calendar'></i></button>" +
					"<button class='btn btn-info btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Add Remark'  onclick=addLeaveRemark('leaveremark"+i+"');><i class='glyphicon glyphicon-tags'></i></button>" +
					"<button class='btn btn-success btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Approve Leave'  onclick=approveRejectLeaveReq('"+ result[i].requestnumber +"','true','requestLeaveStatusUser"+i+"','leaveremark"+i+"');><i class='glyphicon glyphicon-ok'></i></button>" +
					"<button class='btn btn-danger btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Reject Leave'  onclick=approveRejectLeaveReq('"+ result[i].requestnumber +"','false','requestLeaveStatusUser"+i+"','leaveremark"+i+"');><i class='glyphicon glyphicon-remove'></i></button>" +
					"</td>"));
					}
					var row1 = $("<tr class='rowLeavesUser"+i+"' />");
					$("#tab_logic_pendingLeaveItemApp").append(row1);
			    }
			filterAnyTableByInput(leaveStatus,'pendingLeaveItemTbl','5');
			$("#pendingLeaveFilter").val(leaveStatus).change();
			}else{
				document.getElementById("pendingLeaveItemDiv").style.display='none';
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function approveRejectLeaveReq(reqNumber,approveFlag,reqStatusId,remarkId){
	var remark = document.getElementById(remarkId).value;
	var reqStatus = $("#"+reqStatusId).html();
	if(remark == undefined || remark == ""){
		remark = "Not Available";
	}
	//alert(reqNumber+approveFlag+reqStatus+remark);
	var ret = confirm("Are you sure?");
	if (ret == false)
		return false;
	$.ajax({
		type: "POST",
		url: "updateLeaveRequestStatus",
		data:{reqNumber:reqNumber,approveFlag:approveFlag,reqStatus:reqStatus,remark:remark},
		dataType: 'json',
		success: function(result){
			if(result == 'true'){
				if(approveFlag == "Reversal"){
					alert("Request has been successfully updated");
					getPastUsersLeaveRequestList('pastUserRequest','All');
				}	
				else{
					if(approveFlag == "Deleted"){
						alert("Request has been deleted");
						getPastUsersLeaveRequestList('pastUserRequest','All');
					}
					else{
						alert("Request has been successfully updated");
						getPendingLeaveRequestListJS('pendingUserRequest','All',$("#pendingLeaveFilter").val());
					}	
				}
			}else{
				if(result == 'false'){
					getPendingLeaveRequestListJS('pendingUserRequest','All',$("#pendingLeaveFilter").val());
					alert("Request has been successfully updated");
				}else{
					alert("Kindly reject user timesheet before leave approval!");
					getPendingLeaveRequestListJS('pendingUserRequest','All',$("#pendingLeaveFilter").val());
				}	
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function addLeaveRemark(id){
	$('#addLeaveRemarkModal').modal('show');	
	$('#leaveremarkValueHiddenId').val(id);
	$('#idLeaveRmrk').val($("#"+id).val());
}
function captureLeaveRemark(){
	var remark = document.getElementById('idLeaveRmrk').value;
	if(remark.length>100 || remark == undefined){
		alert("Invalid or exceeded limit beyond 100");
		return false;
	}
	var hidId = document.getElementById('leaveremarkValueHiddenId').value;
	$("#"+hidId).val(remark);
	$('#addLeaveRemarkModal').modal('hide');
}
function manageLeaveType(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewLeaveCategoryDiv").style.display='block';
	document.getElementById('viewLeaveCategoryDiv').setAttribute("style","min-height:500px");
	$("#viewLeaveCategoryDiv").appendTo("#dataDiv");
	populatePolicyGroup('newLeavePolicyGroup');
	$.ajax({
		type: "POST",
		url: "getAllLeaveList",
		dataType: 'json',
		beforeSend: function() { $('#loaderGif').show(); },
        complete: function() { $('#loaderGif').hide(); },
		success: function(data){
			document.getElementById("leaveList").innerHTML = "";
			var table = $("<table class='table table-stripped' style='width:100%;' scrolling='auto' id='leaveTable'>" +
					"<thead style='background-color: #30a5ff;color:white;'>" +
					"<tr><th>Leave Name</th>" +
					"<th>Leave Desc</th>" +
					"<th>Apply Min<br>Days Before</th>" +
					"<th>Max Days<br>Avail</th>" +
					"<th>Active Status</th>" +
					"<th style='width:110px;'>On Behalf of</th>" +
					"<th>Cumulative Group</th>" +
					"<th>Leave Type</th>" +
					"<th>Policy Group</th>" +
					/*"<th>Created By</th>" +*/
					/*"<th>Created On</th>" +*/
					"<th style='width:8%;'>Action</th></tr>" +
					"</thead>" +
					"<tbody id='leaveTableBody'></tbody></table>");
			$("#leaveList").append(table);
			for (var i = 0; i < data.length; i++) {
				var row = $("<tr />");
				$("#leaveTableBody").append(row);
				row.append($("<td class='EWTableElements'><input class='form-control' maxlength='20' style='width:70px;' id='leaveId"+i+"' type='text' value='"+data[i].leavename+"' readonly='true'/></td>"));
				row.append($("<td class='EWTableElements'><input class='form-control' maxlength='50' id='leaveDesc"+i+"' type='text' value='"+data[i].leavedesc+"' readonly='true'/></td>"));
				row.append($("<td class='EWTableElements'><input class='form-control' maxlength='2' id='leaveApplyMinDaysBefore"+i+"' type='text' value='"+data[i].applymindaysbefore+"' readonly='true' style='width:60px;' /></td>"));
				row.append($("<td class='EWTableElements'><input class='form-control' maxlength='3' id='leaveApplyMaxDays"+i+"' type='text' value='"+data[i].maxdaysperrequest+"' readonly='true' style='width:60px;' /></td>"));
				row.append($("<td class='EWTableElements'><input type='checkbox' id='leaveActiveFlag"+i+"'></td>"));
				if(data[i].activeflag == "Y"){$("#leaveActiveFlag"+i).prop( "checked", true );}
				row.append($("<td class='EWTableElements'>" +
						"<input type='checkbox' id='applyByAdmin"+i+"'>Admin<br>"+
						"<input type='checkbox' id='apply1ByManager"+i+"'>Manager<br>"+
						"<input type='checkbox' id='applyByHR"+i+"'>HR"+
						/*"Admin:<input maxlength='1' id='applyByAdmin"+i+"' type='text' value='"+data[i].applybyadmin+"' readonly='true' style='width:20px;height:20px;' />" +
						"<br>Manager:<input maxlength='1' id='apply1ByManager"+i+"' type='text' value='"+data[i].applybymanager+"' readonly='true' style='width:20px;height:20px;' />" +
						"<br>HR:<input maxlength='1' id='applyByHR"+i+"' type='text' value='"+data[i].applybyhr+"' readonly='true' style='width:20px;height:20px;' />" +*/
								
				"</td>"));
				if(data[i].applybyadmin == "Y"){$("#applyByAdmin"+i).prop( "checked", true );}
				if(data[i].applybymanager == "Y"){$("#apply1ByManager"+i).prop( "checked", true );}
				if(data[i].applybyhr == "Y"){$("#applyByHR"+i).prop( "checked", true );}
				/*row.append($("<td class='EWTableElements'>"+data[i].cumulativegroupname+"</td>"));*/
				row.append($("<td class='EWTableElements'><select class='' id='cumulativegroup"+i+"' style='display:inline;width:60px;'><option value='"+ data[i].cumulativegroup +"'>"+data[i].cumulativegroupname+"</option></select><a href='#!' onclick=fetchCumulativeLeaveGroup('"+data[i].policygroup+"','cumulativegroup"+i+"');><span class='caret caret-down'></span></a></td>"));
				row.append($("<td class='EWTableElements'>"+data[i].leavegroup+"</td>"));
				row.append($("<td class='EWTableElements'>"+data[i].policygroup+"</td>"));
				/*row.append($("<td class='EWTableElements'>"+data[i].leavecreatedby+"</td>"));*/
				/*row.append($("<td class='EWTableElements'>"+data[i].leavecreatedon+"</td>"));*/
				row.append($("<td class='EWTableElements' align='center'>" +
						"<a href='#!' title='Modify Leave' onclick=editLeaveName('"+i+"','"+data[i].leaveid+"');><span class='glyphicon glyphicon-edit pull-left'></span></a>" +
						"<a href='#!' title='Click to Save' onclick=updateLeaveName('"+i+"','"+data[i].leaveid+"');><span class='glyphicon glyphicon-floppy-saved' style='margin-right: 10px;'></span></a>" +
						/*"<a href='#!' title='Remove Leave' onclick=removeLMSLeave('"+data[i].leaveid+"');><span class='glyphicon glyphicon-trash'></span></a>"+*/
						"</td>"));
			}
			
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});	
}
function createLeaveName(){
	var leaveName = document.getElementById("newLeaveName").value;
	var leaveDesc = document.getElementById("newLeaveDesc").value;
	var policyGroup = document.getElementById("newLeavePolicyGroup").value;
	var leaveGroup = document.getElementById("newLeaveGroup").value;
	var applyDaysBefore = document.getElementById("applyDaysBefore").value;
	var cumulativeGroup = document.getElementById("newLeaveCumulativeGroup").value;
	var maxDaysPerRequest = document.getElementById("maxDaysPerRequest").value;
	var applyByAdmin = "N";
	var applyByManager = "N";
	var applyByHR = "N";
	if ($('#applyByAdmin').is(":checked"))
		applyByAdmin = "Y";
	if ($('#applyByManager').is(":checked"))
		applyByManager = "Y";
	if ($('#applyByHR').is(":checked"))
		applyByHR = "Y";
	
	if(leaveName == "" || leaveDesc == ""){
		alert("Please provide valid leave name and description");
		return false;
	}
	if(policyGroup == "" || policyGroup == null){
		alert("Please select HR policy Group");
		return false;
	}
	if(leaveGroup == "" || leaveGroup == null){
		alert("Please select type: Paid/Unpaid");
		return false;
	}
	var ret = confirm("Do you want to create new leave type?");	
	if(ret == false)
		return false;
	$.ajax({
		type: "POST",
		url: "createNewLMSLeave",
		data:{leaveName:leaveName,leaveDesc:leaveDesc,policyGroup:policyGroup,leaveGroup:leaveGroup,applyDaysBefore:applyDaysBefore,cumulativeGroup:cumulativeGroup,applyByAdmin:applyByAdmin,applyByManager:applyByManager,applyByHR:applyByHR,maxDaysPerRequest:maxDaysPerRequest},
		dataType: 'json',
		success: function(result){
			if(result == 'true'){
				document.getElementById("newLeaveName").value="";
				document.getElementById("newLeaveDesc").value="";
				document.getElementById("applyDaysBefore").value="";
				document.getElementById("maxDaysPerRequest").value="";
				$("select#newLeavePolicyGroup").prop('selectedIndex', 0);
				$("select#newLeaveGroup").prop('selectedIndex', 0);
				$("select#newLeaveCumulativeGroup").prop('selectedIndex', 0);
					alert("Leave type has been successfully created");
					manageLeaveType();
			}else{
				alert("Encountered error!");
				return false;
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});	
}
function updateLeaveName(indexId,leaveId){
	var leaveName = document.getElementById("leaveId"+indexId).value;
	var leaveDesc = document.getElementById("leaveDesc"+indexId).value;
	var leaveMinDaysBefore = document.getElementById("leaveApplyMinDaysBefore"+indexId).value;
	var leaveApplyMaxDays = document.getElementById("leaveApplyMaxDays"+indexId).value;
	var leaveActiveFlag = "N";
	if ($("#leaveActiveFlag"+indexId).is(":checked"))
	{
		leaveActiveFlag = "Y";
	}
	var applyByAdmin = "N";
	if ($("#applyByAdmin"+indexId).is(":checked"))
	{
		applyByAdmin = "Y";
	}
	var applyByManager1 = "N";
	if ($("#apply1ByManager"+indexId).is(":checked"))
	{
		applyByManager1 = "Y";
	}
	var applyByHR = "N";
	if ($("#applyByHR"+indexId).is(":checked"))
	{
		applyByHR = "Y";
	}
	/*var applyByAdmin = document.getElementById("applyByAdmin"+indexId).value;
	var applyByManager1 = document.getElementById("apply1ByManager"+indexId).value;
	var applyByHR = document.getElementById("applyByHR"+indexId).value;*/
	var cumulativegroup = document.getElementById("cumulativegroup"+indexId).value;
	if(leaveName == "" || leaveName == undefined || leaveName == null){
		alert("Please provide valid leave name.");
		return false;
	}
	if(leaveDesc == ""){
		alert("Please provide valid leave description");
		return false;
	}
	if(leaveMinDaysBefore == ""){
		alert("Please provide valid minimum apply days before.");
		return false;
	}
	if(leaveApplyMaxDays == ""){
		alert("Please provide valid maximum days for per leave request type.");
		return false;
	}
	if(leaveActiveFlag != "Y" && leaveActiveFlag != "N"){
		alert("Please provide valid active flag for leave (Y or N) only.");
		return false;
	}
	if(applyByAdmin != "Y" && applyByAdmin !="N"){
		alert("Please provide valid proxy apply by Admin for leave (Y or N) only.");
		return false;
	}
	if(applyByManager1 != "Y" && applyByManager1 != "N"){
		alert("Please provide valid proxy apply by HR for leave (Y or N) only.");
		return false;
	}
	if(applyByHR != "Y" && applyByHR != "N"){
		alert("Please provide valid proxy apply by HR for leave (Y or N) only.");
		return false;
	}
	$.ajax({
		type: "POST",
		url: "updateLMSLeave",
		data:{leaveId:leaveId,leaveName:leaveName,leaveDesc:leaveDesc,leaveMinDaysBefore:leaveMinDaysBefore,
			leaveApplyMaxDays:leaveApplyMaxDays,leaveActiveFlag:leaveActiveFlag,applyByAdmin:applyByAdmin,
			applyByManager1:applyByManager1,applyByHR:applyByHR,cumulativegroup:cumulativegroup},
		dataType: 'json',
		success: function(result){
			if(result == 'true'){
					alert("Leave parameteres has been successfully updated");
					manageLeaveType();
			}else{
				alert("Encountered error!");
				return false;
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});	
}
function editLeaveName(indexId,leaveId){
	$("#leaveId"+indexId).prop('readonly', false);
	$("#leaveDesc"+indexId).prop('readonly', false);
	$("#leaveApplyMinDaysBefore"+indexId).prop('readonly', false);
	$("#leaveApplyMaxDays"+indexId).prop('readonly', false);
	//$("#leaveActiveFlag"+indexId).prop('readonly', false);
	//$("#applyByAdmin"+indexId).prop('readonly', false);
	//$("#apply1ByManager"+indexId).prop('readonly', false);
	//$("#applyByHR"+indexId).prop('readonly', false);
}
function removeLMSLeave(leaveId){
	var ret = confirm("Are you sure?");
	if (ret == true) {} else {
		return false;
	}
	$.ajax({
		type: "POST",
		url: "deleteLMSLeave",
		data:{leaveId:leaveId},
		dataType: 'json',
		success: function(result){
			if(result == 'true'){
					alert("Successfully removed");
					manageLeaveType();
			}else{
				alert("One or more entry exist in leave!");
				return false;
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});	
}
function onlyUnique(value, index, self) { 
    return self.indexOf(value) === index;
}
function manageLeaveQuaterBand(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewLeaveBandDiv").style.display='block';
	document.getElementById('viewLeaveBandDiv').setAttribute("style","min-height:500px");
	$("#viewLeaveBandDiv").appendTo("#dataDiv");
	$.ajax({
		type: "POST",
		url: "getQuarterLeaveList",
		dataType: 'json',
		beforeSend: function() { $('#loaderGif').show(); },
        complete: function() { $('#loaderGif').hide(); },
		success: function(data){
			document.getElementById("leaveBandList").innerHTML = "";
			var table = $("<table class='table table-bordered' style='width:100%;' scrolling='auto' id='leaveTable'>" +
					"<thead style='background-color:#30a5ff;color:white;'>" +
					"<tr><th>User's Band</th><th>Leave Type</th><th>Quarterly</th><th>Annual</th><th style='width:8%;'>Action</th></tr>" +
					"</thead>" +
					"<tbody id='leaveBandTableBody'></tbody></table>");
			$("#leaveBandList").append(table);
			var moduleIdarr = [];
			for (var i = 0; i < data.length; i++) {
				moduleIdarr.push(data[i].bandid);
			}
			var uniqueArr = moduleIdarr.filter( onlyUnique );
		for(var k=0;k<moduleIdarr.length;k++){
			for (var i = 0; i < data.length; i++) {
				if(uniqueArr[k] == data[i].bandid){
					  var row = "";
					if(k%2 == 0)  
						row = $("<tr style='background-color:#D4F1F1'/>");
					else
						row = $("<tr style='background-color:#D4E8F1  '/>");
				$("#leaveBandTableBody").append(row);
				row.append($("<td class='EWTableElements'>"+data[i].bandid+"</td>"));
				row.append($("<td class='EWTableElements'>"+data[i].leavename+"</td>"));
				row.append($("<td class='EWTableElements'>"+data[i].quartercount+"</td>"));
				row.append($("<td class='EWTableElements'>"+data[i].annualcount+"</td>"));
				row.append($("<td class='EWTableElements' align='center'><button class='btn-primary btn-sm pull-right' title='Delete Leave Type' onclick=updateLeaveCount('"+data[i].leaveid+"');>Update</button></td>"));
			}
		}
		}	
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});	
}
function getAllLeaveHeadTypeList(){
	var leaveHead = new Array();
	$.ajax({
		type: "POST",
		url: "getAllLeaveTypeList",
		dataType: 'json',
		success: function(data){
			for(var i =0;data.length;i++){
				leaveHead.push(data[i].leaveName);
			}
		}
	});	
	return leaveHead;
}
function getAllLeaveCodesTypeList(){
	var leaveCodes = new Array();
	$.ajax({
		type: "POST",
		url: "getAllLeaveTypeList",
		dataType: 'json',
		success: function(data){
			for(var i=0;data.length;i++){
				leaveCodes.push("leavehead"+data[i].leaveCode);
			}
		}
	});	
	return leaveCodes;
}
function updateLeaveBalance(policyGroup){
	/*var leaveHead = new Array();
	leaveHead = getAllLeaveHeadTypeList();*/
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewLeaveQuarterDiv").style.display='block';
	document.getElementById('viewLeaveQuarterDiv').setAttribute("style","min-height:500px");
	$("#viewLeaveQuarterDiv").appendTo("#dataDiv");
	if(policyGroup == 'SUPRA-Canada'){
		$("#noidaSheetId").css("display", "none");
		$("#canadaSheetId").css("display", "inline");
		$("#policyGroupName").val('SUPRA-Canada');
	}else{
		$("#canadaSheetId").css("display", "none");
		$("#noidaSheetId").css("display", "inline");
		$("#policyGroupName").val('SUPRA-Noida');
	}
	$.ajax({
		type: "POST",
		url: "getQuarterLeaveBalance",
		dataType: 'json',
		data:{policyGroup:policyGroup},
		success: function(data){
			document.getElementById("leaveQuarterList").innerHTML = "";
			var thString = "";
			for(var t=0;t<(data[data.length-1].tempListHead.length);t++){
				thString = thString + "<th>"+data[data.length-1].tempListHead[t]+"</th>";
			}
			thString = thString + "<th>Action</th>";
			var codeString = new Array();
			for(var t=0;t<(data[data.length-1].tempListCode.length);t++){
				codeString.push(data[data.length-1].tempListCode[t]);
			}
			//alert(codeString[0]);
			var table = $("<table class='table table-bordered' style='width:100%;' scrolling='auto' id='leaveQuarterTable'>" +
					"<thead style='background-color: #30a5ff;color:white;'>" +
					"<tr><th>User's Band</th><th>Username</th><th>Full Name</th>"+thString+"</tr>" +
					"</thead>" +
					"<tbody id='leaveQuarterTableBody'></tbody></table>");
			$("#leaveQuarterList").append(table);
			for (var i = 0; i < data.length - 1; i++) {
					var row = "";
					if(i%2 == 0)  
						row = $("<tr class='info'/>");
					else
						row = $("<tr />");
				if(undefined != data[i].bandid)
				row.append($("<td class='EWTableElements'>"+data[i].bandid+"</td>"));
				if(undefined != data[i].username)
				row.append($("<td class='EWTableElements'>"+data[i].usercode+"</td>"));
				if(undefined != data[i].userfullname)
				row.append($("<td class='EWTableElements'>"+data[i].userfullname+"</td>"));
				if(undefined != data[i].leavecode1)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode1+"</td>"));
				if(undefined != data[i].leavecode2)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode2+"</td>"));
				if(undefined != data[i].leavecode3)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode3+"</td>"));
				if(undefined != data[i].leavecode4)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode4+"</td>"));
				if(undefined != data[i].leavecode5)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode5+"</td>"));
				if(undefined != data[i].leavecode6)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode6+"</td>"));
				if(undefined != data[i].leavecode7)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode7+"</td>"));
				if(undefined != data[i].leavecode8)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode8+"</td>"));
				if(undefined != data[i].leavecode9)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode9+"</td>"));
				if(undefined != data[i].leavecode10)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode10+"</td>"));
				if(undefined != data[i].leavecode11)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode11+"</td>"));
				if(undefined != data[i].leavecode12)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode12+"</td>"));
				if(undefined != data[i].leavecode13)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode13+"</td>"));
				if(undefined != data[i].leavecode14)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode14+"</td>"));
				if(undefined != data[i].leavecode15)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode15+"</td>"));
				if(undefined != data[i].leavecode16)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode16+"</td>"));
				if(undefined != data[i].leavecode17)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode17+"</td>"));
				if(undefined != data[i].leavecode18)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode18+"</td>"));
				if(undefined != data[i].leavecode19)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode19+"</td>"));
				if(undefined != data[i].leavecode20)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode20+"</td>"));
				if(undefined != data[i].leavecode21)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode21+"</td>"));
				if(undefined != data[i].leavecode22)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode22+"</td>"));
				if(undefined != data[i].leavecode23)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode23+"</td>"));
				if(undefined != data[i].leavecode24)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode24+"</td>"));
				if(undefined != data[i].leavecode25)
					row.append($("<td class='EWTableElements'>"+data[i].leavecode25+"</td>"));
				var tempName = replaceAll(data[i].userfullname," ","@");
				row.append($("<td class='EWTableElements' style='text-align:center;'>" +
						"<button class='btn btn-danger btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Modify Balance' style='margin:3px;' onclick=openMoadalUserLeave('"+data[i].usercode+"','"+data[i].username+"','"+policyGroup+"');><span class='glyphicon glyphicon-pencil'></span></button>" +
						"<button class='btn btn-info btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='View Balance Update History' style='margin:3px;' onclick=openModalUsrLeaveBalHistory('"+data[i].usercode+"','"+data[i].username+"','"+tempName+"');><span class='glyphicon glyphicon-calendar'></span></button></td>"));
				$("#leaveQuarterTableBody").append(row);
		}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});	
	
}
function valBalSheetUpload(fileName,id)
{
	var pathname = document.getElementById(id).value;
	var fName = pathname.split('\\').pop().split('/').pop();
    var file_extension = fileName.split('.').pop(); 
        if(file_extension != 'xlsx' || file_extension == "")
        {
        	alert("Kindly attach xlsx file only");
        	document.getElementById(id).value = '';
        	return false;
        }else if(fName != "LeaveBalanceExcel.xlsx"){
        	alert("Uploaded file name must be LeaveBalanceExcel.xlsx");
        	document.getElementById(id).value = '';
        	return false;
        }else{
        	return true;
        }
}
function uploadBalanceSheet(id){
	var file = document.getElementById(id).value;
	if(!valBalSheetUpload(file,id))
		return false;
    var form = $('#fileUploadForm')[0];
    var data = new FormData(form);
    var policyGroup = document.getElementById("policyGroupName").value;
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "uploadBalanceSheet",
        data:data,
        processData: false,
        contentType: false,
        beforeSend: function() { $('#loaderGif').show();},
        complete: function() { $('#loaderGif').hide();},
        success: function (data) {
            //alert("Successfully uploaded.");
        	$( "#successFileUploadLeave" ).fadeIn( "slow", function() {});
            document.getElementById("leaveBalDocumentList").value = '';
            updateLeaveBalance(policyGroup);
            setTimeout(
            	    function() {
            	    	$( "#successFileUploadLeave" ).fadeOut( "slow", function() {});
            	    }, 2000);
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}
function setLeaveName(rowIndex){
	var el = document.getElementById("leaveList["+rowIndex+"].leaveCode");
	var temp = el.options[el.selectedIndex].innerHTML;
	//$("#leaveList["+rowIndex+"].leaveName").val(temp);
	document.getElementById("leaveList["+rowIndex+"].leaveName").value = temp;
}
function checkBalanceUpdationFlag(){
	if(applyLeaveForUser.length == 0 && applyLeaveForUser != ""){
		return false;
	}
	$.ajax({
        type: "POST",
        url: "checkBalanceUpdationFlag",
        dataType: 'json',
        data:{uid:applyLeaveForUser},
        success: function (result) {
        	if(result.length > 0){
        		var updationFlag='N';
        		for (var i = 0; i < result.length; i++) {
        			if(result[i].leaveUpdationFlag == "Y"){
        				updationFlag = result[i].leaveUpdationFlag;
        				continue;
        			}	
        		}
        		if(updationFlag == 'Y'){
	        		$('#leaveUserAckModal').modal('show');
	        		$('#tab_logic_lmsAck tbody').empty();
	        		for (var i = 0; i < result.length; i++) {	
	    				    var row = $("<tr  />");														
	    				 	$("#tab_logic_lmsAck").append(row); 
	    				 	row.append($("<td nowrap class='EWTableElements'>"+result[i].leaveName+"</td>"));
	    				 	row.append($("<td nowrap class='EWTableElements'>"+result[i].leaveUpdatedBalance+"</td>"));
	    			    }
        		}
        	}
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}
function updateBalanceUpdationFlag(updateFlag){
	$.ajax({
		type: "POST",
        url: "updateBalanceUpdationFlag",
        dataType: 'json',
        type: "POST",
        data: {updateFlag:updateFlag,uid:applyLeaveForUser},
        success: function (data) {
        	if(updateFlag == "true"){
        		alert("Please proceed and apply leave.");
        		$('#leaveUserAckModal').modal('hide');
        	}else{
        		alert("HR person will get back to you soon.");
        		window.close();
        		return false;
        	}
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}
function getUnpaidLeave(){
	$.ajax({
		type: "POST",
        url: "getAllUnpaidLeaveType",
        dataType: 'json',
        type: "POST",
        async:false,
        success: function (data) {
        	for(var i=0;i<data.length;i++){
        		unpaidLeaveListTypes.push(data[i]);
        	}	
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}
function checkLeaveGroup(leaveType){
	var unpaidType = true;
	for(var i=0;i<unpaidLeaveListTypes.length;i++){
		if(leaveType == unpaidLeaveListTypes[i]){
			unpaidType = false;
		}	
	}
	return unpaidType;
}
function getApplyBeforeLeaveParam(){
	$.ajax({
		type: "POST",
        url: "getApplyBeforeLeaveParam",
        dataType: 'json',
        type: "POST",
        async:false,
        success: function (data) {
        	applyDaysBeforeArray = data;	
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });	
}
function getMaxDayLeaveParam(){
	$.ajax({
		type: "POST",
        url: "getMaxDayForEachRequestLeave",
        dataType: 'json',
        type: "POST",
        async:false,
        success: function (data) {
        	applyMaxDayArray = data;
        	//console.log("applyMaxDayArray=="+applyMaxDayArray);
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });	
}
function validateApplyMinDayBefore(appliedDates,leavecode){
	var currDate = new Date();
	for(var i =0;i< applyDaysBeforeArray.length;i++){
		if(applyDaysBeforeArray[i].leaveCode == leavecode && Number(applyDaysBeforeArray[i].applyDaysBefore) != 0){
			for(var k =0;k< applyDaysBeforeArray.length;k++){
				var tempDate = new Date(appliedDates[k]);
				if(tempDate <= currDate){
					viewAlertDiv("You can not avail "+applyDaysBeforeArray[i].leavename+" for past date. ");
					return false;
				}
				var dateDiff = Math.round((tempDate-currDate)/(1000*60*60*24));
				//alert(dateDiff);
				if(Number(dateDiff) < Number(applyDaysBeforeArray[i].applyDaysBefore)){
					viewAlertDiv(applyDaysBeforeArray[i].leavename+" should be applied "+applyDaysBeforeArray[i].applyDaysBefore+" days before.");
					//alert("You can apply "+applyDaysBeforeArray[i].leavename+" atleast "+applyDaysBeforeArray[i].applyDaysBefore+" days before only.");
					return false;
				}
			}
		}
	}
	return true;
}
function userLeaveReport(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("vieLeaveReportDiv").style.display='block';
	document.getElementById('vieLeaveReportDiv').setAttribute("style","min-height:500px;");
	$("#vieLeaveReportDiv").appendTo("#dataDiv");
	populatePolicyGroup('hrPolicyGroup');
}
function generateLeaveReport(){
	var username = document.getElementById('userListForLeaveReport').value;
	var hrPolicyGroup = document.getElementById('hrPolicyGroup').value;
	var startDate = document.getElementById('from-datepicker-leave').value;
	var endDate = document.getElementById('to-datepicker-leave').value;
	var leaveStatus = document.getElementById('leaveStatusList').value;
	if(hrPolicyGroup == "" || hrPolicyGroup == undefined){
		alert("Please select HR Policy Group.");
		return false;
	}
	if(startDate == "" || startDate == undefined){
		alert("Invalid or blank start date.");
		return false;
	}
	if(endDate == "" || endDate == undefined){
		alert("Invalid or blank end date.");
		return false;
	}
	document.getElementById("loaderGif").style.display='block';
	$.ajax({
		type: "POST",
		url: "viewLeaveReport",
		dataType: 'json',
		data: {username :username,hrPolicyGroup:hrPolicyGroup,startDate:startDate,endDate:endDate,leaveStatus:leaveStatus},
		success: function(data){
			document.getElementById("loaderGif").style.display='none';
			$("#vieLeaveReportDataDiv").empty();
			if(data.length > 0){
				$("#vieLeaveReportDataDiv").append($("<span><a href='currentLeaveReport'><button type='button' class='btn btn-primary pull-left' style='margin:5px;'>Download xls</button></a></span>"));
				$("#vieLeaveReportDataDiv").append($("<table class='table table-stripped' style='background-color: #EBF5FB;' scrolling='auto'><thead style='background-color: #D4E6F1;'><tr><th>Emp Name</th><th>RM Name</th><th>Leave Type</th><th>Applied On</th><th>Start Date</th><th>End Date</th><th>Days</th><th>Status</th><th>Purpose</th></tr></thead><tbody id='leaveReportBody'></tbody></table>"));
			for(var i=0;i<data.length;i++){
				var row = $("<tr  />");														
			 	$("#leaveReportBody").append(row); 
			 	row.append($("<td nowrap class='EWTableElements'>"+data[i].fullname+"</td>"));
			 	row.append($("<td nowrap class='EWTableElements'>"+data[i].rmname+"</td>"));
			 	row.append($("<td nowrap class='EWTableElements'>"+data[i].leavename+"</td>"));
			 	row.append($("<td nowrap class='EWTableElements'>"+data[i].applydate+"</td>"));
			 	row.append($("<td nowrap class='EWTableElements'>"+data[i].startdate+"</td>"));
			 	row.append($("<td nowrap class='EWTableElements'>"+data[i].enddate+"</td>"));
			 	row.append($("<td nowrap class='EWTableElements'>"+data[i].totaldays+"</td>"));
			 	row.append($("<td nowrap class='EWTableElements'>"+data[i].status+"</td>"));
			 	row.append($("<td nowrap class='EWTableElements'>"+data[i].purpose+"</td>"));
			}	
			}else{
				$("#vieLeaveReportDataDiv").append($("<div style='margin-left:20px;'><span style='color:red;'>No data found in database.</span></div>"));
			}
		},
		error: function(jqXHR, textStatus)
		{
		document.getElementById("downloadReportDiv").style.display='none';	
		alert("ERROR:"+textStatus);
		}
	});
}
function populateEmployee(hrPolicyGroup){
	$.ajax({
		type: "POST",
		url: "getUserListBasedOnPolicyGroup",
		dataType: 'json',
		data: {hrPolicyGroup:hrPolicyGroup},
		success: function(data){
			var select = document.getElementById('userListForLeaveReport');
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Employee',''));
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].fullname,data[i].username));
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function openMeetingModal(requestNumber,userfullname){
	$('#leaveMeetingModal').modal('show');
	$("#reqNumForMeeting").html(requestNumber);
	$("#reqNumForMeetingHidden").val(requestNumber);
	document.getElementById("selectedMail").value = "";
	document.getElementById("selectedMailCC").value = "";
	document.getElementById("mailSubject").value = "";
	document.getElementById("mailMessage").value = "";
	document.getElementById("selectedUserForMeeting").value = "";
	document.getElementById("meetingLocation").value = "";
	document.getElementById("meetingSubject").value = "";
	document.getElementById("meetingMessage").value = "";
	$.ajax({
		type: "POST",
		url: "fetchMeetingHistory",
		dataType: 'json',
		data: {reqNum:requestNumber},
		success: function(data){
			$("#mailHistoryData").empty();
			if(data.length > 0){
				var divString = "";
				for(var i =0;i<data.length;i++){
					if(data[i].type == "MAIL")
						divString = divString + "<span>Time & Date::<strong>"+data[i].time+"</strong><br>Sent By::<strong>"+data[i].requestedby+"</strong></span><br><span>Subject::<strong>"+data[i].subject+"</strong></span><hr />";
				}
				$("#mailHistoryData").append(divString);
			}else{
				$("#mailHistoryData").append("<span style='color:red'>No history found.</span>");
			}
			$("#meetingHistoryData").empty();
			if(data.length > 0){
				var divString = "";
				for(var i =0;i<data.length;i++){
					if(data[i].type == "MEETING")
						divString = divString + "<span>Start Time::<strong>"+data[i].meetingstarttime+"</strong><br>End Time::<strong>"+data[i].meetingendtime+"</strong><br>Arranaged By::<strong>"+data[i].requestedby+"</strong></span><br><span>Subject::<strong>"+data[i].subject+"</strong></span><hr />";
				}
				$("#meetingHistoryData").append(divString);
			}else{
				$("#meetingHistoryData").append("<span style='color:red'>No history found.</span>");
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
	
}
function sendMeetingRequestForLeave(type){
	var requestNumber = document.getElementById("reqNumForMeetingHidden").value;
	var to = "";
	var cc = "";
	var subject = "";
	var message = "";
	var member = "";
	var meetingSTime = "";
	var meetingETime = "";
	var location = "";
	if(type == "mail"){
		to = document.getElementById("selectedMail").value;
		if(to.length > 0)
			to = to.replace(/,\s*$/, "");
		cc = document.getElementById("selectedMailCC").value;
		if(cc.length > 0)
			cc = cc.replace(/,\s*$/, "");
		subject = document.getElementById("mailSubject").value;
		message = document.getElementById("mailMessage").value;
		if(to == "" || to == null || to == "undefned"){
			alert("Please provide valid recipients list for meeting.");
			return false;
		}
	}else{
		member = document.getElementById("selectedUserForMeeting").value;
		if(member.length > 0)
			member = member.replace(/,\s*$/, "");
		meetingSTime = document.getElementById("meetingTime1").value;
		meetingETime = document.getElementById("meetingTime2").value;
		location = document.getElementById("meetingLocation").value;
		subject = document.getElementById("meetingSubject").value;
		message = document.getElementById("meetingMessage").value;
		if(member == "" || member == null || member == "undefned"){
			alert("Please provide valid member list for meeting.");
			return false;
		}
		if(meetingSTime == "" || meetingSTime == null || meetingSTime == "undefned"){
			alert("Please provide valid meeting start time");
			return false;
		}
		if(meetingETime == "" || meetingETime == null || meetingETime == "undefned"){
			alert("Please provide valid meeting end time");
			return false;
		}
		if(location == "" || location == null || location == "undefned"){
			alert("Please provide valid meeting location");
			return false;
		}
	}
	if(subject == "" || subject == null || subject == "undefned"){
		alert("Please provide valid mail subject");
		return false;
	}
	if(message == "" || message == null || message == "undefned"){
		alert("Please provide valid message.");
		return false;
	}
	var toUser = to.split(',');
	var ccUser = cc.split(',');
	var toMember = member.split(',');
	var ret = confirm("Do you want to create request?");	
	if(ret == false)
		return false;
	$.ajax({
		type: "POST",
		url: "sendMeetingRequest",
		dataType: 'json',
		data: {type:type,requestNumber:requestNumber,toUser:toUser,ccUser:ccUser,toMember:toMember,meetingSTime:meetingSTime,meetingETime:meetingETime,location:location,subject:subject,message:message},
		success: function(data){
			$('#leaveMeetingModal').modal('hide');
			alert(data);
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function searchStringInArray (str, strArray) {
    for (var j=0; j<strArray.length; j++) {
        if (strArray[j].match(str)) return j;
    }
    return -1;
}
function openModalUsrLeaveBalHistory(usercode,username,usrfullname){
	$.ajax({
		type: "POST",
		url: "getLeaveBalHistoryOfUser",
		dataType: 'json',
		data: {username:username},
		success: function(data){
			if(data.length > 0){
				$('#leaveBalHistoryModal').modal('show');
				var tempname =  replaceAll(usrfullname,"@"," ");
				$("#empBalName").html(" "+tempname+"("+usercode+")");
				$("#leaveBalDiv").empty();
				$("#leaveBalDiv").append("<table class='table table-bordered' id='tmsBalanceHistoryTbl' scrolling='auto'>" +
						"<thead><tr><th>Leave Head</th><th>Balance</th><th>Updated On</th><th>Updated By</th></tr></thead><tbody id='tab_logic_lmsActionHistory'></tbody></table>");
				$('#tmsBalanceHistoryTbl td').remove();
				var leaveTypearr = [];
				for (var i = 0; i < data.length; i++) {
					var row = "";
								if(searchStringInArray(data[i].leaveCode,leaveTypearr) == -1)
									row = $("<tr class='danger' style='font-weight:600;'/>");
								else
									row = $("<tr class='' />");
						leaveTypearr.push(data[i].leaveCode);		
					 	$("#tab_logic_lmsActionHistory").append(row); 
					 	row.append($("<td class='EWTableElements'>"+data[i].leaveCode+"</td>"));
					 	row.append($("<td class='EWTableElements'>"+data[i].balance+"</td>"));
					 	row.append($("<td class='EWTableElements'>"+data[i].updatedOn+"</td>"));
					 	row.append($("<td class='EWTableElements'>"+data[i].updatedBy+"</td>"));
				    }
			}else{
				alert("No history available.");
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function openMoadalUserLeave(usercode,username,policyGroup){
	$('#leaveDetailModal').modal('show');
	$.ajax({
		type: "POST",
		url: "getUserOwnLeaveBalance",
		dataType: 'json',
		data: {username:username},
		success: function(data){
			$("#leaveModalDiv").empty();
			var data1 = "<label>Emp Id :</label><span class='pull-right'>"+usercode+"</span><br /><label>Full Name: </label><span class='pull-right'>"+data[0].fullname+"</span><br />";
			var leaveIds = [];
			for(var i=0;i<data.length;i++){
				data1 = data1 + "<div class='form-group'><label>"+data[i].leavetype +" : </label><span class='pull-right'><input type='text' class='form-control' id='leave"+data[i].leaveid +"' value='"+data[i].balance +"' /></span></div>";
				leaveIds[i] = data[i].leaveid;
			}
			data1 = data1 + "<div class='form-group'><a href='#l' class='btn btn-sm btn-info pull-right' title='Update' onclick=updateUserLmsBalance('"+policyGroup+"','"+username+"',["+leaveIds+"]);>Update</a></div>";
			$("#leaveModalDiv").append(data1);
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function updateUserLmsBalance(policyGroup,username,leaveIds){
	var updatedLeave = [];
	//console.log(leaveIds.length);
	for(var i =0;i<leaveIds.length;i++){
		var obj = {};
		var temp = "leave" + leaveIds[i];
		//console.log(temp);
		obj[leaveIds[i]] = document.getElementById(temp).value;
		updatedLeave.push(obj);
	}
	$.ajax({
		type: "POST",
		url: "updateUserOwnLeaveBalance",
		dataType: 'json',
		data: {username:username,updatedLeave:JSON.stringify(updatedLeave)},
		success: function(data){
			$('#leaveDetailModal').modal('hide');
			alert(data);
			updateLeaveBalance(policyGroup);
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
	
}
function fetchCumulativeLeaveGroup(policyGroup,id){
	$.ajax({
		type: "POST",
		url: "fetchActiveCumulativeGroup",
		dataType: 'json',
		data: {policyGroup:policyGroup},
		success: function(data){
			var select = document.getElementById(id);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Cumulative Group',''));
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].leaveName,data[i].cumulativeGroup));
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function newLeaveRequestToOthers(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewNewLeavesOthersDiv").style.display='block';
	document.getElementById('viewNewLeavesOthersDiv').setAttribute("style","min-height:500px");
	$("#viewNewLeavesOthersDiv").appendTo("#dataDiv");
	populateUserListForApplyingLeave('applyLeaveForOtherUser');
}
function applyLeaveRequestForOthers(id){
	/*var top=0;
    var left=0;
    var height=screen.availHeight;
    var width=screen.availWidth;*/
    var uid = document.getElementById(id).value;
    //window.open('newLeaveRequestToOthers?uid='+uid ,'ProjectScreen','scrollbars=yes,resizable=yes,toolbar=no,Addressbar=no,menubar=no,status=yes,fullscreen=yes,location=no,top='+top+',left='+left+',height='+height+',width='+width);
    var params = { 'uid' : uid };
    openNewRequest("newLeaveRequestToOthers",params);
}
function populateUserListForApplyingLeave(id){
	$.ajax({
		type: "POST",
		url: "getUserListForLeaveApply",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(id);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select User',''));
			for (var i = 0; i < data.length; i++) {				
					select.add(new Option(data[i].fullname,data[i].username));
			    }
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});

}
function valiDateMaxDaysInLeaveType(leaveType,applyDaysCount){
	for(var k =0;k< applyMaxDayArray.length;k++){
		if(leaveType == applyMaxDayArray[k].leaveCode){
			if(parseFloat(applyMaxDayArray[k].maxDaysAllowedPerReq) < parseFloat( applyDaysCount )){
				alert("You can max avail "+applyMaxDayArray[k].maxDaysAllowedPerReq+" days for selected leave.");
				return false;
			}
		}
	}
}
function viewAlertDiv(msg){
	$( "#warningMessageId" ).html("Error:"+msg);
	$( "#warningMessageId" ).fadeIn( "slow", function() {});
	setTimeout(
    	    function() {
    	    	$( "#warningMessageId" ).fadeOut( "slow", function() {});
    	    }, 3000);
}
function getUserListForMeeting(searchParam,paramId,id,listDiv){
	if(searchParam.length > 0){
		document.getElementById(id).style.display='block';
	}else{
		document.getElementById(id).style.display='none';
	}
	$.ajax({
		type: "POST",
		url: "getUserListForLeaveMeeting",
		dataType: 'json',
		data:{searchParam:searchParam},
		success: function(data){
			$('#'+listDiv).empty();
			$('#'+listDiv).append("<ul id='emailListForMeetingUL' style='list-style:none;padding-left:10px;overflow-y:scroll;'></ul>");
			$('#emailListForMeetingUL').empty();
			if(data.length > 0){
				for(var i=0;i<data.length;i++){
					$('#emailListForMeetingUL').append("<li><a href='#l' title='Add "+data[i].fullname+"' onclick=addUserToTextDiv('"+data[i].usermail+"','"+id+"','"+paramId+"');return false;><span class='glyphicon glyphicon-plus-sign' style='color:#2471A3;padding:5px;'></span></a>"+data[i].fullname+" ( "+data[i].usermail+" )</li>");
				}
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function addUserToTextDiv(usermail,id,paramId){
		document.getElementById(paramId).value = "";
		$("#emailListForMeetingUL").remove();
		var selectedUser = document.getElementById(id).value;
		selectedUser = selectedUser + usermail +",\n";
		document.getElementById(id).value = selectedUser;
		document.getElementById(id).style.display='block';
}
