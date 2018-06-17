var globalElementMap = new Object();
var globalElementGroupMap = new Object();
function closeTDSParamDiv(){
	$('#addTDSRemarkDiv').css({
    	display: 'none'
    });
}
function setPayrollElementMap(){
	$.ajax({
		type: "POST",
		url: "payroll/getPayrollElementListRest",
		dataType: 'json',
		success: function(data){
			for (var i = 0; i < data.length; i++) {
				globalElementMap[data[i].elementId] = data[i].elementName;
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function setPayrollElementGroupMap(){
	$.ajax({
		type: "POST",
		url: "payroll/getPayrollElementGroupListRest",
		dataType: 'json',
		success: function(data){
			for (var i = 0; i < data.length; i++) {
				globalElementGroupMap[data[i].elementGroupId] = data[i].elementGroupName;
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function addRemarkTDS(btnId){
	var btn = $("#"+btnId);
    $('#addTDSRemarkDiv').css({
    	background: '#f0f0f0',
        position: 'absolute',
        top: btn.offset().top + btn.outerHeight() + 5,
        left: btn.offset().left + btn.outerWidth() - 300,
    }).show();
}
function viewUserPayslip(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewPayslipDiv").style.display='block';
	document.getElementById('viewPayslipDiv').setAttribute("style","min-height:545px");
	$("#viewPayslipDiv").appendTo("#dataDiv");
	document.getElementById("viewUserPayslipdiv").style.display='none';
}
function userTDSDeclaration(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewTDSDeclarationDiv").style.display='block';
	document.getElementById('viewTDSDeclarationDiv').setAttribute("style","min-height:545px");
	$("#viewTDSDeclarationDiv").appendTo("#dataDiv");
}
function mapEmployeeToPayrollTemplateDiv(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewMapEmpPayrollDiv").style.display='block';
	document.getElementById('viewMapEmpPayrollDiv').setAttribute("style","min-height:545px");
	$("#viewMapEmpPayrollDiv").appendTo("#dataDiv");
}
function viewEmployeePayrollDiv(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewEmpPayrollDiv").style.display='block';
	document.getElementById('viewEmpPayrollDiv').setAttribute("style","min-height:545px");
	$("#viewEmpPayrollDiv").appendTo("#dataDiv");
	document.getElementById("viewUserPayroll").style.display='none';
	populatePolicyGroup('policyGroupPayroll');
	setPayrollElementMap();
	setPayrollElementGroupMap();
}
function getUserListOnSelectPolicy(hrPolicyGroup){
	$.ajax({
		type: "POST",
		url: "getUserListBasedOnPolicyGroup",
		dataType: 'json',
		data: {hrPolicyGroup:hrPolicyGroup},
		success: function(data){
			var select = document.getElementById('empNamePayroll');
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
function viewEmployeePendingTDSDiv(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewEmpPendingTDSDiv").style.display='block';
	document.getElementById('viewEmpPendingTDSDiv').setAttribute("style","min-height:545px");
	$("#viewEmpPendingTDSDiv").appendTo("#dataDiv");
}
function generateEmpsPayslipDiv(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewGeneratePayslipDiv").style.display='block';
	document.getElementById('viewGeneratePayslipDiv').setAttribute("style","min-height:545px");
	$("#viewGeneratePayslipDiv").appendTo("#dataDiv");
	getPayrollForPayslipGeneration('genPayslipPayroll');
	getPayrollPeriodForPayslipGeneration('payrollGenPeriod');
}
function getPayrollForPayslipGeneration(id){
	$.ajax({
		type: "POST",
		url: "payroll/getPayrollListRest",
		dataType: 'json',
		success: function(data){
			for (var i = 0; i < data.length; i++) {		
				if(data[i].status == "Y"){
					var select = document.getElementById(id);
					for(var i=select.options.length-1;i>=0;i--)
		                select.remove(i);
					select.add(new Option("Select Payroll",""));
					for (var i = 0; i < data.length; i++)				
						select.add(new Option(data[i].payrollName,data[i].payrollId));
				}
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function getPayrollPeriodForPayslipGeneration(id){
	$.ajax({
		type: "POST",
		url: "payroll/getPayrollPeriodListRest",
		dataType: 'json',
		success: function(data){
			for (var i = 0; i < data.length; i++) {		
				if(data[i].status == "Open"){
					var select = document.getElementById(id);
					for(var i=select.options.length-1;i>=0;i--)
		                select.remove(i);
					select.add(new Option("Select Period",""));
					for (var i = 0; i < data.length; i++)				
						select.add(new Option(data[i].periodName,data[i].payrollPeriodId));
				}
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function viewUserPayrollSetupDiv(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewPayrollSetupDiv").style.display='block';
	document.getElementById('viewPayrollSetupDiv').setAttribute("style","min-height:545px");
	$("#viewPayrollSetupDiv").appendTo("#dataDiv");
	getPayrollTypeListDetails();
	getPayrollTypeListToDropDown('payrollCreateType');
	getPayrollTypePeriodDetails();
	getPayrollListDetails();
	getElementGroupListDetails();
	getPayrollElementListDetails();
	fetchAvaibalePayElement();
}
function getPayrollTypeListToDropDown(id){
	$.ajax({
		type: "POST",
		url: "payroll/getPayrollTypeListRest",
		dataType: 'json',
		success: function(result){
			var data = result;
			/*alert(data[0]);alert(gh[0].typeName);*/
			var select = document.getElementById(id);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Type',''));
			for (var i = 0; i < data.length; i++) {		
				if(data[i].status == "Y")	
					select.add(new Option(data[i].typeName,data[i].payrollTypeId));
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function getPayrollTypePeriodDetails(){
	$.ajax({
		type: "POST",
		url: "payroll/getPayrollPeriodListRest",
		dataType: 'json',
		success: function(result){
			var data = result; 
			$('#tab_logic_payrollPeriodItemApp').empty();
			for (var i = 0; i < data.length; i++) {				
				var row = $("<tr />");
				$("#tab_logic_payrollPeriodItemApp").append(row);
				row.append($("<td class='EWTableElements'>" + (i+1) + "</td>"));
				row.append($("<td class='EWTableElements'>" + data[i].periodName + "</td>"));
				row.append($("<td class='EWTableElements'>"+data[i].startDate +"</td>"));
				row.append($("<td class='EWTableElements'>"+data[i].endDate +"</td>"));
				row.append($("<td  class='EWTableElements'>"+data[i].status+"</td>"));
				row.append($("<td  class='EWTableElements'>"+data[i].lastUpdateDate+"</td>"));
				row.append($("<td  class='EWTableElements'>"+data[i].lastUpdatedByFullname+"</td>"));
				row.append($("<td  class='EWTableElements text-center'>" +
						"<button class='btn btn-info btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Edit Period'  onclick=actionOnElement();><i class='glyphicon glyphicon-edit'></i></button>" +
						"<button class='btn btn-success btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Update'  onclick=actionOnElement('');><i class='glyphicon glyphicon-floppy-disk'></i></button>" +
						"<button class='btn btn-danger btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Delete'  onclick=actionOnElement('');><i class='glyphicon glyphicon-trash'></i></button>" +
						"</td>"));
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function getPayrollListDetails(){
	$.ajax({
		type: "POST",
		url: "payroll/getPayrollListRest",
		dataType: 'json',
		success: function(result){
			var data = result; 
			$('#tab_logic_payrollNameItemApp').empty();
			for (var i = 0; i < data.length; i++) {				
				var row = $("<tr />");
				$("#tab_logic_payrollNameItemApp").append(row);
				row.append($("<td class='EWTableElements'>" + (i+1) + "</td>"));
				row.append($("<td class='EWTableElements'>" + data[i].payrollName + "</td>"));
				row.append($("<td class='EWTableElements'>"+data[i].payrollTypeName +"</td>"));
				row.append($("<td  class='EWTableElements'>"+data[i].status+"</td>"));
				row.append($("<td  class='EWTableElements'>"+data[i].lastUpdateDate+"</td>"));
				row.append($("<td  class='EWTableElements'>"+data[i].lastUpdatedByFullname+"</td>"));
				row.append($("<td  class='EWTableElements text-center'>" +
						"<button class='btn btn-info btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Edit Payroll'  onclick=actionOnElement();><i class='glyphicon glyphicon-edit'></i></button>" +
						"<button class='btn btn-success btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Update'  onclick=actionOnElement('');><i class='glyphicon glyphicon-floppy-disk'></i></button>" +
						"<button class='btn btn-danger btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Delete'  onclick=actionOnElement('');><i class='glyphicon glyphicon-trash'></i></button>" +
						"</td>"));
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function getPayrollTypeListDetails(){
	$.ajax({
		type: "POST",
		url: "payroll/getPayrollTypeListRest",
		dataType: 'json',
		success: function(result){
			var data = result; 
				//JSON.parse(result);
			$('#tab_logic_payrollTypeItemApp').empty();
			for (var i = 0; i < data.length; i++) {				
				var row = $("<tr />");
				$("#tab_logic_payrollTypeItemApp").append(row);
				row.append($("<td class='EWTableElements'>" + (i+1) + "</td>"));
				row.append($("<td class='EWTableElements'>" + data[i].typeName + "</td>"));
				row.append($("<td class='EWTableElements'>"+data[i].description +"</td>"));
				row.append($("<td  class='EWTableElements'>"+data[i].status+"</td>"));
				row.append($("<td  class='EWTableElements'>"+data[i].lastUpdateDate+"</td>"));
				row.append($("<td  class='EWTableElements'>"+data[i].lastUpdatedByFullname+"</td>"));
				row.append($("<td  class='EWTableElements text-center'>" +
						"<button class='btn btn-info btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Edit Payroll Type'  onclick=actionOnElement();><i class='glyphicon glyphicon-edit'></i></button>" +
						"<button class='btn btn-success btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Update'  onclick=actionOnElement('');><i class='glyphicon glyphicon-floppy-disk'></i></button>" +
						"<button class='btn btn-danger btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Delete'  onclick=actionOnElement('');><i class='glyphicon glyphicon-trash'></i></button>" +
						"</td>"));
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function viewPayrollReportDiv(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewPayrollReportDiv").style.display='block';
	document.getElementById('viewPayrollReportDiv').setAttribute("style","min-height:545px");
	$("#viewPayrollReportDiv").appendTo("#dataDiv");
}
function createEmpLoanReqDiv(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewCreateLoanRequestDiv").style.display='block';
	document.getElementById('viewCreateLoanRequestDiv').setAttribute("style","min-height:545px");
	$("#viewCreateLoanRequestDiv").appendTo("#dataDiv");
}
function getEmpPayslip(){
	var year = document.getElementById("yearPaySlip").value;
	var month = document.getElementById("monthPaySlip").value;
	if(year == "" || month == ""){
		alert("Please select valid Year and Month.");
		return false;
	}
	document.getElementById("viewUserPayslipdiv").style.display='block';
}
function getEmpPayRoll(){
	var policyGroup = document.getElementById("policyGroupPayroll").value;
	var username = document.getElementById("empNamePayroll").value;
	if(policyGroup == "" || username == ""){
		alert("Please select valid Policy Group and User.");
		return false;
	}
	document.getElementById("viewUserPayroll").style.display='block';
	document.getElementById("selectedUserForAssignmentHidden").value = username;
	fetchEmployeeCurrentAssignment(username);
	fetchEmpAllotedElementList(username);
	fetchAvailableElementGroups();
}
function createNewPayrollType(){
	var payrollTypeName = document.getElementById("createPRollType").value;
	var payrollTypeDesc = document.getElementById("createPRollTypeDesc").value;
	if(payrollTypeName == ""){
		alert("Please provide payroll type.");
		return false;
	}
	if(payrollTypeDesc == ""){
		alert("Please provide payroll type description.");
		return false;
	}
	document.getElementById("loaderGif").style.display='block';
	$.ajax({
		type: "POST",
		url: "payroll/createNewPayrollType",
		dataType: 'json',
		data:{payrollTypeName:payrollTypeName,payrollTypeDesc:payrollTypeDesc},
		success: function(data){
			document.getElementById("loaderGif").style.display='none';
			alert(data);
		},
		error: function(jqXHR, textStatus)
		{
			document.getElementById("loaderGif").style.display='none';
			alert("ERROR:"+textStatus);
		}
	});
}
function createNewPayrollPeriod(){
	var payrollPeriodName = document.getElementById("createPRollPeriodName").value;
	var periodStartDate = document.getElementById("createPRollPeriodSDate").value;
	var periodEndDate = document.getElementById("createPRollPeriodEDate").value;
	var periodStatus = document.getElementById("createPRollPeriodStatus").value;
	if(payrollPeriodName == ""){
		alert("Please provide payroll period name.");
		return false;
	}
	if(periodStartDate == ""){
		alert("Please provide period start date.");
		return false;
	}
	if(periodEndDate == ""){
		alert("Please provide period end date.");
		return false;
	}
	document.getElementById("loaderGif").style.display='block';
	$.ajax({
		type: "POST",
		url: "payroll/createNewPayrollPeriod",
		dataType: 'json',
		data:{payrollPeriodName:payrollPeriodName,periodStartDate:periodStartDate,periodEndDate:periodEndDate,periodStatus:periodStatus},
		success: function(data){
			document.getElementById("loaderGif").style.display='none';
			getPayrollTypePeriodDetails();
			showAlert(data,"SUCCESS");
		},
		error: function(jqXHR, textStatus)
		{
			document.getElementById("loaderGif").style.display='none';
			alert("ERROR:"+textStatus);
		}
	});
}
function createNewPayrollName(){
	var payrollName = document.getElementById("createPayrollName").value;
	var payrollType = document.getElementById("payrollCreateType").value;
	if(payrollName == ""){
		alert("Please provide payroll  name.");
		return false;
	}
	if(payrollType == ""){
		alert("Please provide payroll type.");
		return false;
	}
	document.getElementById("loaderGif").style.display='block';
	$.ajax({
		type: "POST",
		url: "payroll/createNewPayrollName",
		dataType: 'json',
		data:{payrollName:payrollName,payrollType:payrollType},
		success: function(data){
			document.getElementById("loaderGif").style.display='none';
			alert(data);
		},
		error: function(jqXHR, textStatus)
		{
			document.getElementById("loaderGif").style.display='none';
			alert("ERROR:"+textStatus);
		}
	});
}
function enableDisableFormulaFields(formulaFlag){
	if(formulaFlag == "M"){
		  document.getElementById("payElemFormulaType").setAttribute("disabled", "disabled");
		  document.getElementById("payrollElemFormulaText").setAttribute("disabled", "disabled");
	}else{
		document.getElementById("payElemFormulaType").removeAttribute("disabled");
		document.getElementById("payrollElemFormulaText").removeAttribute("disabled");
	}
}
function createPayrollElement(formId){
    $.ajax({
        type: 'POST',
        url: "payroll/createNewPayrollElement",
        dataType : "json",
        data : $("#"+formId).serialize(),
        beforeSend: function() { $('#loaderGif1').show(); },
        complete: function() { $('#loaderGif1').hide(); },
        success : function(res) {
					alert(res);
        	 }
         });
}
function fetchAvaibalePayElement(){
	$.ajax({
        type: 'POST',
        url: "payroll/getPayrollElementListRest",
        dataType : "json",
        success : function(data) {
        	$("#availablePayElementId").empty();
			if(data.length > 0){
				var divCompnent = "";
				for(var i=0;i<data.length;i++){
					if(data[i].elementGroupId == null || data[i].elementGroupId == "" || data[i].elementGroupId == undefined)
						divCompnent = divCompnent + "<input type='checkbox' id='checkbox"+i+"' value='"+data[i].elementId+"' name='associateElementToGroup' /><span style='margin:3px;' title='"+data[i].description+"'>"+data[i].elementName+"</span>";
					else
						divCompnent = divCompnent + "<input type='checkbox' id='checkbox"+i+"' value='"+data[i].elementId+"' disabled/><span style='margin:3px;' title='"+data[i].description+"'>"+data[i].elementName+"</span>";
				}
				$("#availablePayElementId").html(divCompnent);
			}else{
				$("#availablePayElementId").html("<span>"+data+"</span>");
			}		
        }
    });
}
function getPayrollElementListDetails(){
	$.ajax({
		type: "POST",
		url: "payroll/getPayrollElementListRest",
		dataType: 'json',
		success: function(result){
			var data = result; 
			$('#tab_logic_payrollElementItemApp').empty();
			for (var i = 0; i < data.length; i++) {				
				var row = $("<tr />");
				$("#tab_logic_payrollElementItemApp").append(row);
				row.append($("<td class='EWTableElements'>" + (i+1) + "</td>"));
				row.append($("<td class='EWTableElements'>" + data[i].elementName + "</td>"));
				row.append($("<td class='EWTableElements'>"+data[i].description +"</td>"));
				row.append($("<td class='EWTableElements'>"+data[i].elementGroupId +"</td>"));
				row.append($("<td class='EWTableElements'>" + data[i].elementType + "</td>"));
				row.append($("<td class='EWTableElements'>" + data[i].formulaText + "</td>"));
				row.append($("<td  class='EWTableElements'>"+data[i].activeFlag+"</td>"));
				row.append($("<td  class='EWTableElements'>"+data[i].lastUpdateDate+"</td>"));
				row.append($("<td  class='EWTableElements'>"+data[i].lastUpdatedByFullname+"</td>"));
				row.append($("<td  class='EWTableElements text-center' style='white-space: nowrap;'>" +
						"<button class='btn btn-info btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Edit Element'  onclick=actionOnElement();><i class='glyphicon glyphicon-edit'></i></button>" +
						"<button class='btn btn-success btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Update'  onclick=actionOnElement('"+ data[i].elementId +"');><i class='glyphicon glyphicon-floppy-disk'></i></button>" +
						"<button class='btn btn-danger btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Delete Element'  onclick=actionOnElement('"+ data[i].elementId +"');><i class='glyphicon glyphicon-trash'></i></button>" +
						"</td>"));
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function createElementGroup(){
	var elementGroup = document.getElementById("createPayElementGroup").value;
	if(elementGroup == ""){
		alert("Please provide element group name.");
		return false;
	}
	var elemArray = [];
	$("input[name=associateElementToGroup]:checked").each( function () {
		elemArray.push($(this).val());
	});
	if(elemArray.length == 0){
		alert("Please select atleast one available payroll element.");
		return false;
	}
	document.getElementById("loaderGif").style.display='block';
	$.ajax({
		type: "POST",
		url: "payroll/createNewPayrollElementGroupRest",
		dataType: 'json',
		data:{elementGroup:elementGroup,elemArray:elemArray},
		success: function(data){
			document.getElementById("loaderGif").style.display='none';
			alert(data);
		},
		error: function(jqXHR, textStatus)
		{
			document.getElementById("loaderGif").style.display='none';
			alert("ERROR:"+textStatus);
		}
	});
}
function getElementGroupListDetails(){
	$.ajax({
		type: "POST",
		url: "payroll/getPayrollElementGroupListRest",
		dataType: 'json',
		success: function(result){
			var data = result; 
			$('#tab_logic_payrollGroupItemApp').empty();
			for (var i = 0; i < data.length; i++) {				
				var row = $("<tr />");
				$("#tab_logic_payrollGroupItemApp").append(row);
				row.append($("<td class='EWTableElements'>" + (i+1) + "</td>"));
				row.append($("<td class='EWTableElements'>" + data[i].elementGroupName + "</td>"));
				row.append($("<td  class='EWTableElements'>"+data[i].status+"</td>"));
				row.append($("<td  class='EWTableElements'>"+data[i].lastUpdateDate+"</td>"));
				row.append($("<td  class='EWTableElements'>"+data[i].lastUpdatedByFullname+"</td>"));
				row.append($("<td  class='EWTableElements text-center'>" +
						"<button class='btn btn-info btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='View Element'  onclick=actionOnElement();><i class='glyphicon glyphicon-eye-open'></i></button>" +
						"<button class='btn btn-info btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Edit Element Group'  onclick=actionOnElement();><i class='glyphicon glyphicon-edit'></i></button>" +
						"<button class='btn btn-success btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Update Group'  onclick=actionOnElement('"+ data[i].elementGroupId +"');><i class='glyphicon glyphicon-floppy-disk'></i></button>" +
						"<button class='btn btn-danger btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Delete Group'  onclick=actionOnElement('"+ data[i].elementGroupId +"');><i class='glyphicon glyphicon-trash'></i></button>" +
						"</td>"));
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function populateUserPayrollInfoDiv(currentValueId,param,category,btnId){
	var currentValue = document.getElementById(currentValueId).value;
	$.ajax({
		type: "POST",
		url: "payroll/getPayrollDataBasedOnCategory",
		dataType: 'json',
		data: {category:category},
		success: function(data){
			var elem = "<div><button class='btn pull-right' style='background:#fff;' onclick=closePayrollDivElem('editUserPayrollInfoDiv');><span class='glyphicon glyphicon-remove'></span></button></div><div style='margin-top:25px;'>";
			for (var i = 0; i < data.length; i++) {
				var temp = "";
				if((currentValue == data[i].value)){
					temp = "<input type='radio' name='crrRadioButton' value='"+data[i].value+"' checked='true'><label>"+data[i].paramlabel+"</label><br>";
			    }else{
			    	temp = "<input type='radio' name='crrRadioButton' value='"+data[i].value+"'><label style='font-weight:400;'>"+data[i].paramlabel+"</label><br>";
			    }
			    elem = elem + temp;
			}
			elem = elem + "</div><button class='btn btn-sm btn-danger pull-right' style='margin-left:5px;' onclick=closePayrollDivElem('editUserPayrollInfoDiv');>Close</button>" +
						  "<button class='btn btn-sm btn-success pull-right' onclick=modifyUserPayrollParam('"+category+"','crrRadioButton','editUserPayrollInfoDiv');>Save</button>";
			$("#editUserPayrollInfoDiv").empty();
			$("#editUserPayrollInfoDiv").html(elem);
			var btn = $("#"+btnId);
		    $('#editUserPayrollInfoDiv').css({
		    	background: '#fff',
		        position: 'absolute',
		        top: btn.offset().top + btn.outerHeight() - 60,
		        left: btn.offset().left - 230
		    }).show();
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function modifyUserPayrollParam(param,paramValueId,divId){
	var username = document.getElementById("selectedUserForAssignmentHidden").value;
	var paramValue = $("input[name="+paramValueId+"]:checked").val();
	$.ajax({
		type: "POST",
		url: "payroll/updateUserPayrollInfoByParam",
		dataType: 'json',
		data:{username:username,paramValue:paramValue,param:param},
		success: function(data){
			$("#"+divId).modal('hide');
			showAlert(data[0],"INFO");
			closePayrollDivElem('editUserPayrollInfoDiv');
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function addNewUserPayrollAssignmentDiv(){
	$('#addUserPayrollAssignmentModal').modal('show');
}
function saveNewUserPayrollAssignment(){
	var empCode = document.getElementById("selectedUserForAssignmentHidden").value;
	var assignCTC = document.getElementById("id_newCTC").value;
	var activeFlag = document.getElementById("id_assignmentStatus").value;
	var startDate = document.getElementById("id_newCTCStartDate").value;
	var endDate = document.getElementById("id_newCTCEndDate").value;
	$.ajax({
		type: "POST",
		url: "payroll/createUserNewPayrollAssignment",
		dataType: 'json',
		data:{empCode:empCode,assignCTC:assignCTC,activeFlag:activeFlag,startDate:startDate,endDate:endDate},
		success: function(data){
			$('#addUserPayrollAssignmentModal').modal('hide');
			showAlert(data,"INFO");
		},
		error: function(jqXHR, textStatus)
		{
			$('#addUserPayrollAssignmentModal').modal('hide');
			alert("ERROR:"+textStatus);
		}
	});
}
function fetchEmployeeCurrentAssignment(empCode){
	$.ajax({
		type: "POST",
		url: "payroll/fetchUserCurrentPayrollAssignment",
		dataType: 'json',
		async: false,
		data:{empCode:empCode},
		success: function(data){
			$('#empAllotedAssignment').val(data.activeCTC);
			$('#empAllotedPayrollStartDate').val(formatDate(data.startDate));
			$('#empAllotedPayrollEndDate').val(formatDate(data.endDate));
			$('#empAllotedPayroll').val(data.empAllotedPayroll);
			$('#empAllotedPayrollId').val(data.empAllotedPayrollId);
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function closePayrollDivElem(id){
	$("#"+id).empty();
	$("#"+id).hide();
}
function fetchEmpAllotedElementList(empCode){
	var $CTC$ = document.getElementById("empAllotedAssignment").value;
	if($CTC$ == null || $CTC$ == ""){
		showAlert("Please assign CTC to employee.","ERROR");
		return false;
	}
	$.ajax({
		type: "POST",
		url: "payroll/fetchEmployeeElementDetails",
		dataType: 'json',
		data:{empCode:empCode},
		success: function(data){
			$("#tab_logic_payrollAllotedElementItemApp").empty();
			var row1 = $("<tr class='info' />");
			$("#tab_logic_payrollAllotedElementItemApp").append(row1);
			row1.append($("<td><b>Element</b></td>"));
			row1.append($("<td><b>Verified Flag</b></td>"));
			row1.append($("<td><b>Pay Freq.</b></td>"));
			row1.append($("<td><b>Bill Verification</b></td>"));
			row1.append($("<td><b>Formula Text</b></td>"));
			/*row1.append($("<td><b>Max Limit</b></td>"));*/
			row1.append($("<td><b>Pay Value</b></td>"));
			row1.append($("<td><b>Action</b></td>"));
			for(var i = 0;i<data.length;i++){
				var row = $("<tr />");
				$("#tab_logic_payrollAllotedElementItemApp").append(row);
				var tempElementId = data[i].elementId;
				row.append($("<td class='EWTableElements'>" + globalElementMap[tempElementId] + "(<b>"+data[i].formulaAlias+"</b>)</td>"));
				row.append($("<td class='EWTableElements'>" + data[i].verifiedFlag + "</td>"));
				row.append($("<td class='EWTableElements'>" + data[i].payFrequency + "</td>"));
				row.append($("<td class='EWTableElements'>" + data[i].billVarification + "</td>"));
				if(data[i].formulaText == null)
					row.append($("<td class='EWTableElements'>Mannual</td>"));
				else
					row.append($("<td class='EWTableElements'>" + data[i].formulaText + "</td>"));
				/*row.append($("<td class='EWTableElements'>" + data[i].maxLimit + "</td>"));*/
				if(data[i].formulaText == null)
					row.append($("<td class='EWTableElements'><input type='text' class='form-control' id='payAmountId"+i+"' value='0' onkeyup='getTotalCalculatedAmount();return false;' /></td>"));
				else{
					var payValue = caluclateElementValue($CTC$,data[i].formulaText);
					row.append($("<td class='EWTableElements'><input type='text' class='form-control' id='payAmountId"+i+"' value='" + payValue + "' onkeyup='getTotalCalculatedAmount();return false;' /></td>"));
				}
				row.append($("<td class='EWTableElements'>" +
								"<button class='btn btn-info btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Edit Element'  onclick=actionOnElement();><i class='glyphicon glyphicon-edit'></i></button>" +
								"<button class='btn btn-success btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Save'  onclick=actionOnEmpElement('Update','"+data[i].empElementId+"','"+i+"');><i class='glyphicon glyphicon-floppy-disk'></i></button>" +
							 "</td>"));
			}
			var row2 = $("<tr class='success' />");
			$("#tab_logic_payrollAllotedElementItemApp").append(row2);
			row2.append($("<td colspan='3'></td>"));
			row2.append($("<td><b>Assign CTC</b></td>"));
			row2.append($("<td><b>"+$CTC$+"</b></td>"));
			row2.append($("<td><b><span id='totalCalculateAmountId'>0</span></b></td>"));
			row2.append($("<td><b>Total</b></td>"));
			getTotalCalculatedAmount();
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function getTotalCalculatedAmount(){
	var totalPayValue =  Number("0");
			var rows1 = $('#payrollAllotedElemTbl tr');
			var id = 'payrollAllotedElemTbl';
			for (var i = 0;i<rows1.length-2; i++) {
				if($('#'+id+' tr:nth-child('+(i+1)+') td').length > 0){	
					var tempAmt = document.getElementById("payAmountId"+i).value;
					totalPayValue = totalPayValue + Number(tempAmt);
				}
			}
	$("#totalCalculateAmountId").html(totalPayValue);		
}
function caluclateElementValue($CTC$,formulaText){
	var calculatedValue = 0;
	if(formulaText.indexOf("$") == -1){
		return 0;
	}else{
		formulaText = replaceAll(formulaText,"_","");
		formulaText = replaceAll(formulaText,"$CTC$",$CTC$);
		calculatedValue =  eval(formulaText);
	}
	return calculatedValue;
}
function fetchAvailableElementGroups(){
	var empCode = document.getElementById("empNamePayroll").value;
	$.ajax({
		type: "POST",
		url: "payroll/fetchAvailableElementGroupList",
		dataType: 'json',
		data:{empCode:empCode},
		success: function(data){
			var select = document.getElementById("addFromAvailableElementGroup");
			for(var j=select.options.length-1;j>=0;j--)
                select.remove(j);
			select.add(new Option("Select Element",""));
			for (var i = 0, keys = Object.keys(data), ii = keys.length; i < ii; i++) {
				select.add(new Option(data[keys[i]],keys[i]));
				console.log('key : ' + keys[i] + ' val : ' + data[keys[i]]);
			}				
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function assignAvailableElementGroup(){
	var empCode = document.getElementById("empNamePayroll").value;
	var elementGroupId = document.getElementById("addFromAvailableElementGroup").value;
	$.ajax({
		type: "POST",
		url: "payroll/addElementGroupToUserPayroll",
		dataType: 'json',
		data:{empCode:empCode,elementGroupId:elementGroupId},
		success: function(data){
			showAlert(data,"INFO");
			fetchEmpAllotedElementList(empCode);
			fetchAvailableElementGroups();
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function viewEmpsPayslip(){
	var payrollNameId = document.getElementById("genPayslipPayroll").value;
	var payrollPeriodId = document.getElementById("payrollGenPeriod").value;
	$.ajax({
		type: "POST",
		url: "payroll/generatePayslipBasedOnPayroll",
		dataType: 'json',
		data:{payrollNameId:payrollNameId,payrollPeriodId:payrollPeriodId},
		success: function(data){
			$("#tab_logic_empPayrollMapItemApp").empty();
			for(var i=0;i<data.length;i++){
				var row = $("<tr />");
				$("#tab_logic_empPayrollMapItemApp").append(row);
				row.append($("<td class='EWTableElements'>" + (i+1) + "</td>"));
				row.append($("<td class'text-center'><input type='checkbox' /></td>"));
				row.append($("<td class='EWTableElements'>" + data[i].username + "</td>"));
				row.append($("<td class='EWTableElements'>" + data[i].userfullname + "</td>"));
				row.append($("<td class='EWTableElements'>" + formatDate(data[i].startDate) + "</td>"));
				row.append($("<td class='EWTableElements'>" + formatDate(data[i].endDate) + "</td>"));
				row.append($("<td class='EWTableElements info'>" + data[i].grossPay + "</td>"));
				row.append($("<td class='EWTableElements danger'>" + data[i].totalDeduction + "</td>"));
				row.append($("<td class='EWTableElements success'>" + data[i].netPay + "</td>"));
				row.append($("<td class='EWTableElements'><a href='#!'>Generate</a></td>"));
			}	
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function actionOnEmpElement(operation,empElementId,index){
	var empCode = document.getElementById("empNamePayroll").value;
	var payValue = document.getElementById("payAmountId"+index).value;
	if(operation == "Update"){
		$.ajax({
			type: "POST",
			url: "payroll/updateEmployeeElementData",
			dataType: 'json',
			data:{empElementId:empElementId,payValue:payValue},
			success: function(data){
				showAlert(data,"SUCCESS");
				return false;
			},
			error: function(jqXHR, textStatus)
			{
				alert("ERROR:"+textStatus);
			}
		});
	}
}