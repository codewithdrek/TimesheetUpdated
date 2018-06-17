function newReimbursementRequest(){
	/*var top=0;
    var left=0;
    var height=screen.availHeight;
    var width=screen.availWidth;
    var pid = "";
    window.open('newExpenseRequest?pid='+pid ,'ProjectScreen','scrollbars=yes,resizable=yes,toolbar=no,Addressbar=no,menubar=no,status=yes,location=no,fullscreen=yes,top='+top+',left='+left+',height='+height+',width='+width);*/
    var params = { 'pid' : "" };
    openNewRequest("newExpenseRequest",params);
}
function fetchNewRequestNumber(){
	$.ajax({					
		type: "POST",
		url: "billGetRequestNo",
		dataType: 'json',
		success: function(data) 
		{
			$("#requestNumber").val(data.billId);
			
		},
		error: function(jqXHR, textStatus) 
		{
			alert("ERROR_Request_NO: "+textStatus);
		}
		});
}
function deleteExpenseRow(r){
	var queryCount= $('#tab_logicExp tr').length - 1; 
	  var totalCells = 0;
	  for(var l = 0;l<=queryCount;l++){
		  totalCells = totalCells + (document.getElementById('tab_logicExp').rows[l].cells.length);
	  }	
	  if(totalCells <= 10){
		  alert("Atleast one row mandatory");
		  return false;
	  }
	  $("#query"+r).html('');
	  calculateSumAmount();
}
function openAddDocModal(id){
	$('#addDocModal').modal('show');
	$('#tempFile').val(id);
	//var input = $("#docTemp");
	//input.replaceWith(input.val('').clone(true));
}
function uploadExpenseDocuments(){
	var id = document.getElementById("tempFile").value;
	//alert(id);
	//alert(document.getElementById("docTemp").value);
	document.getElementById(id).value = document.getElementById("docTemp").value;
	document.getElementById("docTemp").value = "";
	$('#addDocModal').modal('hide');
}
function addNewExpenseRow(){
	  var queryCount= $('#tab_logicExp tr').length - 1;
	  $('#query'+queryCount).html("" +
			  "<td><select class='form-control' name='expenseList["+queryCount+"].projectName' id='expenseList["+queryCount+"].projectName'><option value='select'  selected='selected'>Select Project</option></select></td>"+
	  		  "<td><select class='form-control' name='expenseList["+queryCount+"].expenseCode' id='expenseList["+queryCount+"].expenseCode' onchange=setExpenseCodeName('"+queryCount+"')><option value='select'  selected='selected'>Select Type</option></select><input type='hidden' path='expenseList["+queryCount+"].codeName' name='expenseList["+queryCount+"].codeName' id='expenseList["+queryCount+"].codeName' /></td>"+
			  "<td><input type='text' name='expenseList["+queryCount+"].expenseName' id='expenseList["+queryCount+"].expenseName' maxlength='50' placeholder='Expense Name' class='form-control'/></td>"+
			  "<td><input type='text' name='expenseList["+queryCount+"].billNumber' id='expenseList["+queryCount+"].billNumber' maxlength='20' placeholder='Bill Number' class='form-control'/></td>"+
			  "<td><input type='text' name='expenseList["+queryCount+"].billAmount' id='expenseList["+queryCount+"].billAmount' maxlength='8' placeholder='0.0' class='form-control' onkeyup='calculateSumAmount();return false;' onkeypress='validate(event)' onfocusout='fillRequestValue("+queryCount+")' /></td>"+
			  "<td><input type='text' name='expenseList["+queryCount+"].requestAmount' id='expenseList["+queryCount+"].requestAmount' maxlength='8' placeholder='0.0' class='form-control' onkeyup='calculateSumAmount();return false;' onkeypress='validate(event)'/></td>"+
			  "<td><input type='text' name='expenseList["+queryCount+"].billDate' id='expenseList["+queryCount+"].billDate' class='billDate form-control'/></td>"+
			  "<td><input type='file' onchange='valFileUpload(this.value,this.id)' name='file' path='expenseList["+queryCount+"].expenseDocumentList' id='expenseList["+queryCount+"].expenseDocumentList' style='width:200px; position: absolute; clip: rect(0px 0px 0px 0px);' class='filestyle' data-buttontext='PDF' tabindex='-1'><div class='bootstrap-filestyle input-group'><input type='text' id='disableLable"+queryCount+"' class='form-control ' disabled=''> <span class='group-span-filestyle input-group-btn' tabindex='0'><label for='expenseList["+queryCount+"].expenseDocumentList' class='btn btn-default '><span class='glyphicon glyphicon-folder-open'></span>PDF</label></span></div><input type='text' readOnly='true' style='color:red;font-size:10px;overflow:hidden;border:none;' name='expenseList["+queryCount+"].expDocName' id='expenseList["+queryCount+"].expDocName'/></td>"+
			  "<td><input type='text' name='expenseList["+queryCount+"].applicantRemark' id='expenseList["+queryCount+"].applicantRemark' maxlength='250' placeholder='Remark' class='form-control'/></td>"+
			  "<td><a href='#!' class='btn btn-info btn-sm' onclick=deleteExpenseRow('"+queryCount+"')><span class='glyphicon glyphicon-trash'></span></a></td>");
	  $('#tab_logicExp').append('<tr id="query'+(queryCount+1)+'"></tr>');
	  	getAssignedProjects("expenseList["+queryCount+"].projectName");
		getExpenseList("expenseList["+queryCount+"].expenseCode");
		queryCount++;
		$(".billDate").datetimepicker({
		      format: 'YYYY-MM-DD',
		      maxDate: new Date()
		  	});	
}
function addNewExpenseRowInView(){
	  var queryCount= $('#tab_logicExp tr').length;
	  $('#tab_logicExp').append('<tr id="query'+(queryCount)+'"></tr>');
	  $('#query'+queryCount).html("" +
			  "<td><select class='form-control' name='expenseList["+queryCount+"].projectName' id='expenseList["+queryCount+"].projectName'><option value='select'  selected='selected'>Select Project</option></select></td>"+
	  		  "<td><select class='form-control' name='expenseList["+queryCount+"].expenseCode' id='expenseList["+queryCount+"].expenseCode' onchange=setExpenseCodeName('"+queryCount+"')><option value='select'  selected='selected'>Select Type</option></select><input type='hidden' path='expenseList["+queryCount+"].codeName' name='expenseList["+queryCount+"].codeName' id='expenseList["+queryCount+"].codeName' /></td>"+
			  "<td><input type='text' name='expenseList["+queryCount+"].expenseName' id='expenseList["+queryCount+"].expenseName' maxlength='50' placeholder='Expense Name' class='form-control'/></td>"+
			  "<td><input type='text' name='expenseList["+queryCount+"].billNumber' id='expenseList["+queryCount+"].billNumber' maxlength='20' placeholder='Bill Number' class='form-control'/></td>"+
			  "<td><input type='text' name='expenseList["+queryCount+"].billAmount' id='expenseList["+queryCount+"].billAmount' maxlength='8' placeholder='0.0' class='form-control' onkeyup='calculateSumAmount();return false;' onkeypress='validate(event)' onfocusout='fillRequestValue("+queryCount+")'/></td>"+
			  "<td><input type='text' name='expenseList["+queryCount+"].requestAmount' id='expenseList["+queryCount+"].requestAmount' maxlength='8' placeholder='0.0' class='form-control' onkeyup='calculateSumAmount();return false;' onkeypress='validate(event)'/></td>"+
			  "<td><input type='text' name='expenseList["+queryCount+"].billDate' id='expenseList["+queryCount+"].billDate' class='billDate form-control'/></td>"+
			  "<td><input type='text' name='expenseList["+queryCount+"].applicantRemark' id='expenseList["+queryCount+"].applicantRemark' maxlength='250' placeholder='Remark' class='form-control'/></td>"+
			  "<td><input type='file' onchange='valFileUpload(this.value,this.id)' name='file' path='expenseList["+queryCount+"].expenseDocumentList' id='expenseList["+queryCount+"].expenseDocumentList' style='width:200px; position: absolute; clip: rect(0px 0px 0px 0px);' class='filestyle' data-buttontext='PDF' tabindex='-1'><div class='bootstrap-filestyle input-group'><input type='text' id='disableLable"+queryCount+"' class='form-control ' disabled=''> <span class='group-span-filestyle input-group-btn' tabindex='0'><label for='expenseList["+queryCount+"].expenseDocumentList' class='btn btn-default '><span class='glyphicon glyphicon-folder-open'></span> PDF</label></span></div><input type='text' readOnly='true' style='color:red;font-size:10px;overflow:hidden;border:none;' name='expenseList["+queryCount+"].expDocName' id='expenseList["+queryCount+"].expDocName' value=''/></td>"+
			  "<td><a href='#!' onclick=deleteExpenseRow('"+queryCount+"')><span class='glyphicon glyphicon-trash'></span></a></td>");
	  //<a href='#!' target='_blank' id='viewDocHref["+queryCount+"]' title='View Attachment' onclick=viewSavedDoc('','','expenseList["+queryCount+"].expenseDocumentList');><span class='glyphicon glyphicon-file'></span></a>
	  //$('#tab_logicExp').append('<tr id="query'+(queryCount+1)+'"></tr>');
	  	getAssignedProjects("expenseList["+queryCount+"].projectName");
		getExpenseList("expenseList["+queryCount+"].expenseCode");
		queryCount++;
		$(".billDate").datetimepicker({
		      format: 'YYYY-MM-DD',
		      maxDate: new Date()
		  	});	
}
function showFileSize(id) {
    var input, file;
    if (!window.FileReader) {
        bodyAppend("p", "The file API isn't supported on this browser yet.");
        return;
    }
    input = document.getElementById(id);
    if (!input) {
        bodyAppend("p", "Um, couldn't find the fileinput element.");
    }
    else if (!input.files) {
        bodyAppend("p", "This browser doesn't seem to support the `files` property of file inputs.");
    }
    else if (!input.files[0]) {
        bodyAppend("p", "Please select a file before clicking 'Load'");
    }
    else {
        file = input.files[0];
        return file.size;
    }
}
function valFileUpload(fileName,id)
{	
	var billId = "expenseList["+id.substring(12,13)+"].billNumber";
	var billNo = document.getElementById(billId).value;
 	/*if((billNo != "") || (billNo == "undefined") || (billNo != " ")){
		alert("Please upload expense Document.");
		return false;
	}*/ 
    var extensions = new Array("jpg","png","gif","pdf");
    var file_extension = fileName.split('.').pop(); 
        if(file_extension != "pdf")
        {
        	alert("Kindly attach pdf file only");
        	document.getElementById(id).value = '';
        	document.getElementById("expenseList["+id.substring(12,13)+"].expDocName").value = '';
        	return false;
        }
        //alert(showFileSize(id));
        if(showFileSize(id) >= 1024 * 1024 * 3){
        	alert("PDF file size can not exceed 3MB.");
        	document.getElementById(id).value = '';
        	document.getElementById("expenseList["+id.substring(12,13)+"].expDocName").value = '';
        	return false;
        }
    var tempFileName = fileName.replace(/^.*[\\\/]/, '');
	var tempId = "expenseList["+id.substring(12,13)+"].expDocName";
		document.getElementById(tempId).value = tempFileName;
		document.getElementById('disableLable'+id.substring(12,13)).value = tempFileName;
		
	//alert(document.getElementById(tempId).value);
}
function getExpenseList(id){
	$.ajax({
		type: "POST",
		url: "getAllExpenseList",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(id);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Type','select'));
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].expenseName,data[i].expenseCode));
			    }
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});	
}
function getAssignedProjects(id){
	var uid = sessionuser;
	$.ajax({
		type: "POST",
		url: "getUsersAllProjectList",
		dataType: 'json',
		data:{uid:uid},
		success: function(data){
			var select = document.getElementById(id);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Project','select'));
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].projectName,data[i].projectName));
			    }
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});	
}

function setExpenseCodeName(idCount){
	var el = document.getElementById("expenseList["+idCount+"].expenseCode");
	var text = el.options[el.selectedIndex].innerHTML;
	//$("#expenseList["+idCount+"].codeName").val(text);
	document.getElementById("expenseList["+idCount+"].codeName").value = text;
	//alert(document.getElementById("expenseList["+idCount+"].codeName").value);
}
function showPendingExpenseReq(status){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewApproveExpenseDiv").style.display='block';
	document.getElementById('viewApproveExpenseDiv').setAttribute("style","min-height:500px");
	$("#viewApproveExpenseDiv").appendTo("#dataDiv");
	if(status == "Rejected" || status == "Approved")
		getExpenseListUnderUser(status);
	else
		getPendingExpenseRequestList('All');
}
function getExpenseListUnderUser(status){
	var expenseBucket = "";
	$.ajax({
		type: "POST",
		url: "getExpensesListUnderUser",
		data:{status:status},
		dataType: 'json',
		success: function(result){
		if(result.length >0){
			document.getElementById("searchResultNullPass1").style.display='none';	
			document.getElementById("userExpenseItemDiv").style.display='block';
			document.getElementById('userExpenseItemDiv').setAttribute("style","min-height:550px");
			$("#userExpenseItemDiv").appendTo("#viewApproveExpenseDiv");
			$("#noDataAvailableIdExpense").html("");
			$('#userExpenseItemTbl tbody').empty();
			for (var i = 0; i < result.length; i++) {	
				var row = "";
							if(i%2 == 0)
								row = $("<tr class='info'  />");
							else
								row = $("<tr  />");														
				 	$("#tab_logic_userExpenseItemApp").append(row); 
				 	row.append($("<td class='clickable' data-toggle='collapse' id='row_"+i+"' data-target='.row_"+i+"'  class='EWTableElements'><a href='#!' title='Expand' style='color:red;margin-left:5px;' onclick=showExpanseDetails('"+ result[i].requestnumber +"','.row_"+i+"');><span id='icon"+i+"' class='glyphicon glyphicon-plus-sign' style='color:#2471A3;'></span></a></td>"));
					row.append($("<td  class='EWTableElements'>" + result[i].requestnumber + "<a href='#!' class='pull-right' title='Open Action History' onclick=openRMSActionHistory('"+result[i].requestnumber+"');><span class='glyphicon glyphicon-time' style='color:blue;'></span></a></td>"));
					row.append($("<td class='EWTableElements'>"+result[i].fullname+"("+ result[i].username +")</td>"));
					row.append($("<td  class='EWTableElements'>"+result[i].creationdate+"</td>"));
					row.append($("<td   class='EWTableElements'>"+result[i].requestedamount+"</td>"));
					row.append($("<td   class='EWTableElements' style='font-weight:600;color:red;'>"+result[i].approvedamount+"</td><input type='hidden' id='remark"+i+"' value=''/>"));
					row.append($("<td id='requestStatus"+i+"'  class='EWTableElements'>"+result[i].requeststatus+"</td>"));
					row.append($("<td  class='EWTableElements'>NA</td>"));
					var row11 = $("<tr class='row_"+i+"' />");
					$("#tab_logic_userExpenseItemApp").append(row11);
			    }
			}else{
				document.getElementById("userExpenseItemDiv").style.display='none';
				$("#noDataAvailableIdExpense").html("No data found in database!!");
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function getPendingExpenseRequestList(filterVar){
	var expenseBucket = "";
	$.ajax({
		type: "POST",
		url: "getPendingExpenseRequestList",
		data:{filterVar:filterVar,expenseBucket:expenseBucket},
		dataType: 'json',
		success: function(result){
		if(result.length >0){
			document.getElementById("searchResultNullPass1").style.display='none';	
			document.getElementById("userExpenseItemDiv").style.display='block';
			document.getElementById('userExpenseItemDiv').setAttribute("style","min-height:550px");
			$("#userExpenseItemDiv").appendTo("#viewApproveExpenseDiv");
			$("#noDataAvailableIdExpense").html("");
			$('#userExpenseItemTbl tbody').empty();
			for (var i = 0; i < result.length; i++) {	
				var row = "";
							if(i%2 == 0)
								row = $("<tr class='info'  />");
							else
								row = $("<tr  />");														
				 	$("#tab_logic_userExpenseItemApp").append(row); 
				 	row.append($("<td class='clickable' data-toggle='collapse' id='row"+i+"' data-target='.row"+i+"'  class='EWTableElements'><a href='#!' title='Expand' style='color:red;margin-left:5px;' onclick=showExpanseDetails('"+ result[i].requestnumber +"','.row"+i+"');><span id='icon"+i+"' class='glyphicon glyphicon-plus-sign' style='color:#2471A3;'></span></a></td>"));
					row.append($("<td  class='EWTableElements'>" + result[i].requestnumber + "<a href='#!' class='pull-right' title='Open Action History' onclick=openRMSActionHistory('"+result[i].requestnumber+"');><span class='glyphicon glyphicon-time' style='color:blue;'></span></a></td>"));
					row.append($("<td class='EWTableElements'>"+result[i].fullname+"("+ result[i].username +")</td>"));
					row.append($("<td  class='EWTableElements'>"+result[i].creationdate+"</td>"));
					row.append($("<td   class='EWTableElements'>"+result[i].requestedamount+"</td>"));
					row.append($("<td   class='EWTableElements' style='font-weight:600;color:red;'>"+result[i].approvedamount+"</td><input type='hidden' id='remark"+i+"' value=''/>"));
					row.append($("<td id='requestStatus"+i+"'  class='EWTableElements'>"+result[i].requeststatus+"</td>"));
					row.append($("<td  class='EWTableElements'>" +
							"<button class='btn btn-info btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Add Remark'  onclick=addExpRemark('remark"+i+"');><i class='glyphicon glyphicon-tags'></i></button>" +
							"<button class='btn btn-success btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Approve'  onclick=approveRejectExpReq('"+ result[i].requestnumber +"','true','requestStatus"+i+"','remark"+i+"');><i class='glyphicon glyphicon-ok'></i></button>" +
							"<button class='btn btn-danger btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Reject'  onclick=approveRejectExpReq('"+ result[i].requestnumber +"','false','requestStatus"+i+"','remark"+i+"');><i class='glyphicon glyphicon-remove'></i></button>" +
							"</td>"));
					var row11 = $("<tr class='row"+i+"' />");
					$("#tab_logic_userExpenseItemApp").append(row11);
			    }
			}else{
				document.getElementById("userExpenseItemDiv").style.display='none';
				$("#noDataAvailableIdExpense").html("No data found in database!!");
				//document.getElementById("searchResultNullPass1").style.display='block';
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function showBucketExpanseDetails(reqNumber,rowid){
	var expandId = "iconBucket" + rowid.substring(rowid.length-1,rowid.length);
	 $("#"+expandId).toggleClass('glyphicon-minus-sign').toggleClass('glyphicon-plus-sign');
	 if($("#"+expandId).css('color')=="rgb(36, 113, 163)"){$("#"+expandId).css('color', '#E74C3C');}else{$("#"+expandId).css('color', '#2471A3');}
	$.ajax({
		type: "POST",
		url: "getExpanseDetailByReqNumber",
		data:{reqNumber:reqNumber},
		dataType: 'json',
		success: function(result){
			$(rowid).children('td').remove();
			$(rowid).append($("<td colspan='10'><table class='table table-stripped' style='background-color: #EBF5FB;' scrolling='auto' id='reqNumDataBucket"+rowid.substring(rowid.length-1,rowid.length)+"'><thead style='background-color: #D4E6F1;'><tr><th>Expense Head</th><th>Project</th><th>Bill No</th><th>Bill Date</th><th>Bill Amt</th><th>Claimed Amt</th><th>Approved Amt</th><th>Remark(Applicant)</th><th>Attachment</th></tr></thead><tbody id='reqNumDataBodyBucket"+rowid.substring(rowid.length-1,rowid.length)+"'></tbody></table></td>"));
			for (var i = 0; i < result.length; i++) {
				var row = $("<tr />");
				$("#reqNumDataBodyBucket"+rowid.substring(rowid.length-1,rowid.length)).append(row);
				row.append($("<td class='EWTableElements'>" + result[i].expensename + "</td>"));
				row.append($("<td class='EWTableElements'>" + result[i].projectname + "</td>"));
				row.append($("<td class='EWTableElements'>"+result[i].billnumber +"</td>"));
				row.append($("<td  class='EWTableElements'>"+result[i].billdate+"</td>"));
				row.append($("<td  class='EWTableElements'>"+result[i].billamount+"</td>"));
				row.append($("<td  class='EWTableElements'>"+result[i].requestedamount+"</td>"));
				row.append($("<td  class='EWTableElements' style='font-weight:600;color:red;'>"+result[i].approvedamount+"</td>"));
				row.append($("<td  class='EWTableElements' title='"+result[i].applicantremark+"'>"+result[i].applicantremark+"</td>"));
				if(result[i].billattachment == "" || result[i].billattachment == "NA")
					row.append($("<td  class='EWTableElements'>NA</td>"));
				else
					row.append($("<td  class='EWTableElements' style='overflow:hidden;'><a href='#!' id='viewDocHref["+i+"]' title='Open "+result[i].billattachment+"' onclick=viewSavedDoc('"+reqNumber+"','"+result[i].expensecode+"','viewDocHref["+i+"]');>"+result[i].billattachment.substr(0, 4) + "..." +result[i].billattachment.substr(result[i].billattachment.length-8, result[i].billattachment.length)+"</a></td>"));
				/*if(result[i].billnumber == "" || result[i].billnumber == undefined)
					row.append($("<td title='No Doc Attached'  class='EWTableElements'>NA</td>"));
				else	
					row.append($("<td  class='EWTableElements'><a href='#!' id='viewDocHref["+i+"]' title='View Attachment' onclick=viewSavedDoc('"+reqNumber+"','"+result[i].expensecode+"','viewDocHref["+i+"]');><span class='glyphicon glyphicon-file'></span></a></td>"));*/
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});	
	
}
function showExpanseDetails(reqNumber,rowid){
	var expandId = "icon" + rowid.substring(rowid.length-1,rowid.length);
	 $("#"+expandId).toggleClass('glyphicon-minus-sign').toggleClass('glyphicon-plus-sign');
	 if($("#"+expandId).css('color')=="rgb(36, 113, 163)"){$("#"+expandId).css('color', '#E74C3C');}else{$("#"+expandId).css('color', '#2471A3');}
	$.ajax({
		type: "POST",
		url: "getExpanseDetailByReqNumber",
		data:{reqNumber:reqNumber},
		dataType: 'json',
		success: function(result){
			$(rowid).children('td').remove();
			$(rowid).append($("<td colspan='10'><table class='table table-stripped' style='background-color: #EBF5FB;' scrolling='auto' id='reqNumData"+rowid.substring(rowid.length-1,rowid.length)+"'><thead style='background-color: #D4E6F1;'><tr><th>Expense Head</th><th>Project</th><th>Bill No</th><th>Bill Date</th><th>Bill Amt</th><th>Claimed Amt</th><th>Approved Amt</th><th>Remark(Applicant)</th><th>Attachment</th></tr></thead><tbody id='reqNumDataBody"+rowid.substring(rowid.length-1,rowid.length)+"'></tbody></table></td>"));
			for (var i = 0; i < result.length; i++) {
				var row = $("<tr />");
				$("#reqNumDataBody"+rowid.substring(rowid.length-1,rowid.length)).append(row);
				row.append($("<td class='EWTableElements'>" + result[i].expensename + "</td>"));
				row.append($("<td class='EWTableElements'>" + result[i].projectname + "</td>"));
				row.append($("<td class='EWTableElements'>"+result[i].billnumber +"</td>"));
				row.append($("<td  class='EWTableElements'>"+result[i].billdate+"</td>"));
				row.append($("<td  class='EWTableElements'>"+result[i].billamount+"</td>"));
				row.append($("<td  class='EWTableElements'>"+result[i].requestedamount+"</td>"));
				row.append($("<td  class='EWTableElements' style='font-weight:600;color:red;'>"+result[i].approvedamount+"</td>"));
				row.append($("<td  class='EWTableElements' title='"+result[i].applicantremark+"'>"+result[i].applicantremark+"</td>"));
				if(result[i].billattachment == "" || result[i].billattachment == "NA")
					row.append($("<td  class='EWTableElements'>NA</td>"));
				else
					row.append($("<td  class='EWTableElements' style='overflow:hidden;'><a href='#!' id='viewDocHref["+i+"]' title='Open "+result[i].billattachment+"' onclick=viewSavedDoc('"+reqNumber+"','"+result[i].expensecode+"','viewDocHref["+i+"]');>"+result[i].billattachment.substr(0, 4) + "..." +result[i].billattachment.substr(result[i].billattachment.length-8, result[i].billattachment.length)+"</a></td>"));
				/*if(result[i].billnumber == "" || result[i].billnumber == undefined)
					row.append($("<td title='No Doc Attached'  class='EWTableElements'>NA</td>"));
				else	
					row.append($("<td  class='EWTableElements'><a href='#!' id='viewDocHref["+i+"]' title='View Attachment' onclick=viewSavedDoc('"+reqNumber+"','"+result[i].expensecode+"','viewDocHref["+i+"]');><span class='glyphicon glyphicon-file'></span></a></td>"));*/
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});	
	
}
function showUserExpanseDetails(reqNumber,rowid){
	var expandId = "iconUser" + rowid.substring(rowid.length-1,rowid.length);
	 $("#"+expandId).toggleClass('glyphicon-minus-sign').toggleClass('glyphicon-plus-sign');
	 if($("#"+expandId).css('color')=="rgb(36, 113, 163)"){$("#"+expandId).css('color', '#E74C3C');}else{$("#"+expandId).css('color', '#2471A3');}
	$.ajax({
		type: "POST",
		url: "getExpanseDetailByReqNumber",
		data:{reqNumber:reqNumber},
		dataType: 'json',
		success: function(result){
			$(rowid).children('td').remove();
			$(rowid).append($("<td colspan='10'><table class='table table-stripped' style='background-color: #EBF5FB;' scrolling='auto' id='reqNumUserData"+rowid.substring(rowid.length-1,rowid.length)+"'><thead style='background-color: #D4E6F1;'><tr><th>Expense Head</th><th>Project</th><th>Bill No</th><th>Bill Date</th><th>Bill Amt</th><th>Claimed Amt</th><th>Approved Amt</th><th>Remark(Applicant)</th><th>Attachment</th></tr></thead><tbody id='reqNumUserDataBody"+rowid.substring(rowid.length-1,rowid.length)+"'></tbody></table></td>"));
			for (var i = 0; i < result.length; i++) {
				var row = $("<tr />");
				$("#reqNumUserDataBody"+rowid.substring(rowid.length-1,rowid.length)).append(row);
				row.append($("<td class='EWTableElements'>" + result[i].expensename + "</td>"));
				row.append($("<td class='EWTableElements'>" + result[i].projectname + "</td>"));
				row.append($("<td class='EWTableElements'>"+result[i].billnumber +"</td>"));
				row.append($("<td  class='EWTableElements'>"+result[i].billdate+"</td>"));
				row.append($("<td  class='EWTableElements'>"+result[i].billamount+"</td>"));
				row.append($("<td  class='EWTableElements'>"+result[i].requestedamount+"</td>"));
				row.append($("<td  class='EWTableElements' style='font-weight:600;color:red;'>"+result[i].approvedamount+"</td>"));
				row.append($("<td  class='EWTableElements'>"+result[i].applicantremark+"</td>"));
				if(result[i].billattachment == "" || result[i].billattachment == "NA")
					row.append($("<td  class='EWTableElements'>NA</td>"));
				else
					row.append($("<td  class='EWTableElements' style='overflow:hidden;'><a href='#!' id='viewDocHref["+i+"]' title='Open "+result[i].billattachment+"' onclick=viewSavedDoc('"+reqNumber+"','"+result[i].expensecode+"','viewDocHref["+i+"]');>"+result[i].billattachment.substr(0, 4) + "..." +result[i].billattachment.substr(result[i].billattachment.length-8, result[i].billattachment.length)+"</a></td>"));
				/*if(result[i].billnumber == "" || result[i].billnumber == undefined)
					row.append($("<td title='No Doc Attached'  class='EWTableElements'>NA</td>"));
				else	
					row.append($("<td  class='EWTableElements'><a href='#!' id='viewDocHref["+i+"]' title='View Attachment' onclick=viewSavedDoc('"+reqNumber+"','"+result[i].expensecode+"','viewDocHref["+i+"]');><span class='glyphicon glyphicon-file'></span></a></td>"));*/
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});	
	
}
function checkReimburseBuckets(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewBucketExpenseDiv").style.display='block';
	document.getElementById('viewBucketExpenseDiv').setAttribute("style","min-height:500px");
	$("#viewBucketExpenseDiv").appendTo("#dataDiv");
	getEveryBucketCount();
	getBucketExpenseRequestList('Approved','0');
	//getBucketList('expenseFilterBucket');
	$("select#expenseFilterBucket").prop('selectedIndex', 1);
}
function getEveryBucketCount(){
	$.ajax({
		type: "POST",
		url: "getEachBucketCount",
		dataType: 'json',
		async:false,
		success: function(result){
			var row = $("<tr class='success'  />");
			$('#bucketCountTbl tbody').empty();
			$("#bucketCountDiv").append(row);
			var count = 0;
			for (var i = 0; i < result.length; i++) {
				count = count + Number( result[i].bucketCount );
			}
			row.append($("<td style='text-align:center;background-color:#30a5ff;color:white;border-radius:5px;padding:5px;'><b>"+count+"</b><br><a href='#!' style='color:white;' onclick=getBucketExpenseRequestList('Approved','0');>All Bucket List</a></td>"));
		 	row.append($("<td style='width:5px;font-weight:600;'>::</td>"));
			for (var i = 0; i < result.length; i++) {
				if(Number( result[i].bucketCount ) == 0)
				 	row.append($("<td style='text-align:center;background-color:#D6EAF8;color:#000;border-radius:5px;padding:5px;'>"+result[i].bucketCount+"<br>"+result[i].bucketName+"</td>"));
				else if(result[i].bucketId == "786"){
					row.append($("<td style='text-align:center;background-color:#fb4a6a;color:#000;border-radius:5px;padding:5px;'><b>"+result[i].bucketCount+"</b><br><a href='#!' style='color:#000;' onclick=getBucketExpenseRequestList('Approved','"+result[i].bucketId+"');>"+result[i].bucketName+"</a></td>"));
				}else{
					row.append($("<td style='text-align:center;background-color:#AED6F1;color:#000;border-radius:5px;padding:5px;'><b>"+result[i].bucketCount+"</b><br><a href='#!' style='color:#000;' onclick=getBucketExpenseRequestList('Approved','"+result[i].bucketId+"');>"+result[i].bucketName+"</a></td>"));
				}
				row.append($("<td style='width:5px;'></td>"));
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});	
}
function getBucketExpenseRequestList(filterVar,expenseBucket){
	$.ajax({
		type: "POST",
		url: "getPendingExpenseRequestList",
		data:{filterVar:filterVar,expenseBucket:expenseBucket},
		dataType: 'json',
		success: function(result){
		if(result.length >0){
			$('#expenseFilterBucket').val(expenseBucket);
			document.getElementById("searchResultNullPass1").style.display='none';	
			document.getElementById("userBucketExpenseItemDiv").style.display='block';
			document.getElementById('userBucketExpenseItemDiv').setAttribute("style","min-height:550px");
			$("#userBucketExpenseItemDiv").appendTo("#viewBucketExpenseDiv");
			//var expenseFilterBucketValue = document.getElementById("expenseFilterBucket").value;
			//$('#currentBucketName').html($('#expenseFilterBucket option:selected').text());
			$('#userBucketExpenseItemTbl tbody').empty();
			if(expenseBucket == "786")
				$('#checkBucketid').attr('disabled','disabled');
			else
				$('#checkBucketid').removeAttr('disabled','disabled');
			for (var i = 0; i < result.length; i++) {	
				var row = "";
							if(i%2 == 0)
								row = $("<tr class='info'  />");
							else
								row = $("<tr  />");														
				 	$("#tab_logic_userBucketExpenseItemApp").append(row); 
				 	row.append($("<td class='clickable text-center' data-toggle='collapse' id='rowBucket"+i+"' data-target='.rowBucket"+i+"'  class='EWTableElements'><a href='#!' title='Expand' style='color:red;margin-left:5px;' onclick=showBucketExpanseDetails('"+ result[i].requestnumber +"','.rowBucket"+i+"');><span id='iconBucket"+i+"' class='glyphicon glyphicon-plus-sign' style='color:#2471A3;'></span></a></td>"));
				 	if(expenseBucket == "786")
				 		row.append($("<td  class='EWTableElements text-center'><input type='checkbox' id='checkbox"+i+"' value='"+result[i].requestnumber+"' disabled /></td>"));
				 	else
				 		row.append($("<td  class='EWTableElements text-center'><input type='checkbox' id='checkbox"+i+"' value='"+result[i].requestnumber+"'/></td>"));
					row.append($("<td  class='EWTableElements text-center'>" + result[i].requestnumber + "<a href='#!' class='pull-right' title='Open Action History' onclick=openRMSActionHistory('"+result[i].requestnumber+"');><span class='glyphicon glyphicon-time' style='color:blue;'></span></a></td>"));
					row.append($("<td class='EWTableElements'>"+result[i].fullname+"("+ result[i].username +")</td>"));
					row.append($("<td  class='EWTableElements'>"+result[i].creationdate+"</td>"));
					row.append($("<td   class='EWTableElements'>"+result[i].requestedamount+"</td>"));
					row.append($("<td   class='EWTableElements' style='font-weight:600;color:red;'>"+result[i].approvedamount+"</td><input type='hidden' id='remark"+i+"' value=''/>"));
					row.append($("<td id='requestStatus"+i+"'  class='EWTableElements'>"+result[i].requeststatus+"</td>"));
					row.append($("<td id='requestStatus"+i+"'  class='EWTableElements'>"+result[i].bucketname+"</td>"));
					var row11 = $("<tr class='rowBucket"+i+"' />");
					$("#tab_logic_userBucketExpenseItemApp").append(row11);
			    }
				  if(globalGroup != "Admin"){
						getBucketList('expenseBucket');
						if(expenseBucket == '4')
							document.getElementById("downloadBankSheetId").style.display='block';
						else
							document.getElementById("downloadBankSheetId").style.display='none';
						if(expenseBucket == '5')
							document.getElementById("sendToBucketId").disabled = true;
						else
							document.getElementById("sendToBucketId").disabled = false;
						if(expenseBucket == '9'){
							document.getElementById("bankFileUploadForm").style.display='block';
						}else{
							document.getElementById("bankFileUploadForm").style.display='none';
						}	
				  }
				}else{
					document.getElementById("userBucketExpenseItemDiv").style.display='none';
					//document.getElementById("searchResultNullPass1").style.display='block';
					return false;
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function sendToBucket(){
	var expenseBucket = document.getElementById("expenseBucket").value;
	if(expenseBucket == ""){
		alert("Select bucket first");
		return false;
	}
	var totalRow = $('#userBucketExpenseItemTbl tr').length - 1;
	var reqArray = [];
	for(var i=0;i<totalRow;i++){
	if($("#checkbox"+i).is(':checked')){	
		var temp = document.getElementById("checkbox"+i).value;
			reqArray.push(temp);
		}
	}
	if(reqArray.length == 0){
		alert("Please select atleast one row");
		return false;
	}
	$.ajax({
		type: "POST",
		url: "updateBucketofRequests",
		data:{expenseBucket:expenseBucket,reqArray:reqArray},
		dataType: 'json',
		success: function(result){
		if(result.length >0){
			alert("Requests successfully sent to bucket "+$('#expenseBucket option:selected').text());
			getBucketExpenseRequestList('Approved','0');
			getEveryBucketCount();
			/*$("select#expenseBucket").prop('selectedIndex', 0);
			$("select#expenseFilterBucket").prop('selectedIndex', 0);
			$("#expenseFilterBucketByRequestNumber").val('');*/
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});	
}
function pastReimbursementList(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewOwnExpenseDiv").style.display='block';
	document.getElementById('viewOwnExpenseDiv').setAttribute("style","min-height:500px");
	$("#viewOwnExpenseDiv").appendTo("#dataDiv");
	getPastUsersExpenseRequestList('pastUserRequest');
}
function getPastUsersExpenseRequestList(filterVar){
	var expenseBucket = "";
	$.ajax({
		type: "POST",
		url: "getPendingExpenseRequestList",
		data:{filterVar:filterVar,expenseBucket:expenseBucket},
		dataType: 'json',
		success: function(result){
		if(result.length >0){
			document.getElementById("searchResultNullPass1").style.display='none';	
			document.getElementById("userOwnExpenseItemDiv").style.display='block';
			document.getElementById('userOwnExpenseItemDiv').setAttribute("style","min-height:550px");
			$("#userOwnExpenseItemDiv").appendTo("#viewOwnExpenseDiv");
			//$('#userOwnExpenseItemTbl td').remove();
			$('#userOwnExpenseItemTbl tbody').empty();
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
				 	$("#tab_logic_userOwnExpenseItemApp").append(row); 
				 	row.append($("<td class='clickable' data-toggle='collapse' id='rowUser"+i+"' data-target='.rowUser"+i+"'  class='EWTableElements'><a href='#!' title='Expand' style='color:red;margin-left:5px;' onclick=showUserExpanseDetails('"+ result[i].requestnumber +"','.rowUser"+i+"');><span id='iconUser"+i+"' class='glyphicon glyphicon-plus-sign' style='color:#2471A3;'></span></a></td>"));
				 	if(result[i].requeststatus == "Withdraw" ||result[i].requeststatus == "Review Failed" || result[i].requeststatus == "Saved as Draft")
				 		row.append($("<td  class='EWTableElements'><a href='#!' onclick=viewPendingRequest('"+result[i].requestnumber+"');>" + result[i].requestnumber + "</a><a href='#!' class='pull-right' title='Open Action History' onclick=openRMSActionHistory('"+result[i].requestnumber+"');><span class='glyphicon glyphicon-time' style='color:blue;'></span></a></td>"));
				 	else
				 		row.append($("<td  class='EWTableElements' title='Withdraw request for editing.'>" + result[i].requestnumber + "<a href='#!' class='pull-right' title='Open Action History' onclick=openRMSActionHistory('"+result[i].requestnumber+"');><span class='glyphicon glyphicon-time' style='color:blue;'></span></a></td>"));
					row.append($("<td  class='EWTableElements'>"+result[i].creationdate+"</td>"));
					row.append($("<td   class='EWTableElements'>"+result[i].requestedamount+"</td>"));
					row.append($("<td   class='EWTableElements' style='font-weight:600;color:red;'>"+result[i].approvedamount+"</td>"));
					row.append($("<td   class='EWTableElements'>"+result[i].reviewedby+"</td>"));
					row.append($("<td   class='EWTableElements' title="+result[i].reviewerremark+">"+result[i].reviewerremark+"</td>"));
					row.append($("<td   class='EWTableElements' title="+result[i].approverremark+">"+result[i].approverremark+"<input type='hidden' id='remarkUser"+i+"' value=''/></td>"));
					row.append($("<td id='requestStatusUser"+i+"'  class='EWTableElements'>"+result[i].requeststatus+"</td>"));
					if(result[i].requeststatus == "Withdraw" ||result[i].requeststatus == "Review Failed" || result[i].requeststatus == "Saved as Draft")
						row.append($("<td class='EWTableElements' style='padding:1px;'>" +
								"<button class='btn btn-info btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Delete' onclick=approveRejectExpReq('"+ result[i].requestnumber +"','Deleted','requestStatusUser"+i+"','remarkUser"+i+"');>" +
								"<span class='glyphicon glyphicon-trash'></span>" +
								"</button></td>"));
					else{
						if(result[i].requeststatus == "Approved")
							row.append($("<td  class='EWTableElements' style='padding:1px;'><a href='downloadRequestForm?requestNumber="+result[i].requestnumber+"' target='_blank' title='Downlaod Approved form' class='btn btn-success btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:9px;' onclick=downloadApprovedForm('"+ result[i].requestnumber +"');><span class='glyphicon glyphicon-download'></span></a></td>"));
						else
							row.append($("<td  class='EWTableElements' style='padding:1px;'>" +
									        "<button class='btn btn-warning btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Delete' onclick=approveRejectExpReq('"+ result[i].requestnumber +"','Deleted','requestStatusUser"+i+"','remarkUser"+i+"');>" +
											"<span class='glyphicon glyphicon-trash'></span>" +
											"</button>" +
											"<button class='btn btn-danger btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Withdraw Request' onclick=approveRejectExpReq('"+ result[i].requestnumber +"','Withdraw','requestStatusUser"+i+"','remarkUser"+i+"');>" +
											"<span class='glyphicon glyphicon-edit'></span>" +
											"</button></td>"));
					}
					var row1 = $("<tr class='rowUser"+i+"' />");
					$("#tab_logic_userOwnExpenseItemApp").append(row1);
			    }
			}else{
				document.getElementById("userOwnExpenseItemDiv").style.display='none';
				//document.getElementById("searchResultNullPass1").style.display='block';	
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function addExpRemark(id){
	$('#addExpRemarkModal').modal('show');	
	$('#remarkValueHiddenId').val(id);
	$('#idExpRmrk').val('');
}
function captureExpRemark(){
	var remark = document.getElementById('idExpRmrk').value;
	if(remark.length>100 || remark == ""){
		alert("Invalid or exceeded limit beyond 100");
		return false;
	}
	var hidId = document.getElementById('remarkValueHiddenId').value;
	$("#"+hidId).val(remark);
	$('#addExpRemarkModal').modal('hide');
}
function approveRejectExpReq(reqNumber,approveFlag,reqStatusId,remarkId){
	var remark = document.getElementById(remarkId).value;
	var reqStatus = $("#"+reqStatusId).html();
	if(remark == undefined || remark == ""){
		remark = "Not Available";
	}
	var ret=confirm("Are you sure?");
	if (ret == true) {}else{return false;}
	//alert(reqNumber+approveFlag+reqStatus+remark);
	$.ajax({
		type: "POST",
		url: "updateRMSRequestStatus",
		data:{reqNumber:reqNumber,approveFlag:approveFlag,reqStatus:reqStatus,remark:remark},
		dataType: 'json',
		success: function(result){
			if(result == 'true'){
				if(approveFlag == "Withdraw"){
					alert("Request has been successfully withdrawn");
					getPastUsersExpenseRequestList('pastUserRequest');
				}	
				else{
					if(approveFlag == "Deleted"){
						alert("Request has been successfully deleted");
						getPastUsersExpenseRequestList('pastUserRequest');
					}	
					else{
					alert("Request has been successfully sent");
					getPendingExpenseRequestList('All');
				}
			}
			}else{
				alert("Encountered error!");
				if(approveFlag == "Withdraw")
					getPastUsersExpenseRequestList('pastUserRequest');
				else
					getPendingExpenseRequestList('All');
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function viewPendingRequest(requestNumber){
	/*var top=0;
    var left=0;
    var height=screen.availHeight;
    var width=screen.availWidth;
    window.open('openExpenseRequestForEdit?requestNumber='+requestNumber ,'BillScreen','scrollbars=yes,resizable=yes,toolbar=no,Addressbar=no,menubar=no,status=yes,fullscreen=yes,location=no,top='+top+',left='+left+',height='+height+',width='+width);*/
    var params = { 'requestNumber' : requestNumber };
    openNewRequest("openExpenseRequestForEdit",params);
}
function viewSavedDoc(requestNumber,expenseCode,hrefId){
	var top=0;
    var left=0;
    var height='500px';
    var width='600px';
    if(requestNumber == ""){
    	return false;
    }
    else{
    	//window.open('viewExpDoc?requestNumber='+requestNumber+'&expenseCode='+expenseCode,'DocScreen','scrollbars=yes,resizable=yes,toolbar=no,Addressbar=no,menubar=no,status=yes,location=no,top='+top+',left='+left+',height='+height+',width='+width);
    	var params = { 'requestNumber' : requestNumber , 'expenseCode':expenseCode };
        openNewRequest("viewExpDoc",params);
    }	
	//document.getElementById(hrefId).href = "viewExpDoc?requestNumber="+requestNumber+"&expenseCode="+expenseCode;
    return false;
}
function validateExpenseTable(id){
	var totalRow = $('#'+id+' tr').length - 1;
	var arrExpCode = [ ];
	for (var i = 0;i<totalRow; i++) {
		//alert($('#'+id+' tr:nth-child('+(i+1)+') td').length);
		if($('#'+id+' tr:nth-child('+(i+1)+') td').length > 0){
		var tempExpValue = document.getElementById('expenseList['+ i +'].expenseCode').value;
		   if(tempExpValue == undefined || tempExpValue == null || tempExpValue == "select"){
			   alert("Please select valid Expense Head");
			   return false;
		   }							   
		   arrExpCode.push(tempExpValue);
		}  
	}
	//alert(arrExpCode);
	for(var i=0;i<arrExpCode.length;i++){
		var j= i+1;
		for(j;j<arrExpCode.length;j++){
			if(arrExpCode[i] == arrExpCode[j] && arrExpCode[i] == arrExpCode[j]){
				alert("No two rows can have same Expense Head.");
				return false;
			}
		}
	}
	for (var i = 0;i<totalRow; i++) {
		//alert($('#'+id+' tr:nth-child('+(i+1)+') td').length);
		if($('#'+id+' tr:nth-child('+(i+1)+') td').length > 0){
			var tempPrjNameValue = document.getElementById('expenseList['+ i +'].projectName').value;
			   if(tempPrjNameValue == undefined || tempPrjNameValue == null || tempPrjNameValue == ""){
				   alert("Please select project");
				   return false;
			   }	
		var tempExpNameValue = document.getElementById('expenseList['+ i +'].expenseName').value;
		   if(tempExpNameValue == undefined || tempExpNameValue == null || tempExpNameValue == ""){
			   alert("Please enter valid Expense Name");
			   return false;
		   }
	   /*var tempExpBillNoValue = document.getElementById('expenseList['+ i +'].billNumber').value;
	   if(tempExpBillNoValue == undefined || tempExpBillNoValue == null || tempExpBillNoValue == ""){
		   alert("Please enter valid Expense Bill Number");
		   return false;
	   }*/
	   var tempExpBillAmtValue = document.getElementById('expenseList['+ i +'].billAmount').value;
	   if(tempExpBillAmtValue == undefined || tempExpBillAmtValue == null || tempExpBillAmtValue == "" || Number(tempExpBillAmtValue) < 0){
		   alert("Please enter valid Expense Bill Amount");
		   return false;
	   }
	   var tempExpBillClaimedAmtValue = document.getElementById('expenseList['+ i +'].requestAmount').value;
	   if(tempExpBillClaimedAmtValue == undefined || tempExpBillClaimedAmtValue == null || tempExpBillClaimedAmtValue == "" || Number(tempExpBillClaimedAmtValue) < 0){
		   alert("Please enter valid Expense Bill Amount");
		   return false;
	   }
	   if(Number(tempExpBillClaimedAmtValue) >Number(tempExpBillAmtValue)){
		   alert("Claimed amount can not greater than bill amount.");
		   return false;
	   }
	   var tempExpBillDateValue = document.getElementById('expenseList['+ i +'].billDate').value;
	   if(tempExpBillDateValue == undefined || tempExpBillDateValue == null || tempExpBillDateValue == ""){
		   alert("Please enter valid Expense Bill Date");
		   return false;
	   }
	   var tempExpRemarkValue = document.getElementById('expenseList['+ i +'].applicantRemark').value;
	   if(tempExpRemarkValue == undefined || tempExpRemarkValue == null || tempExpRemarkValue == ""){
		   alert("Please enter valid remark for expense");
		   return false;
	   }
	  }  
	}
	return true;
}
function deleteExpenseDoc(reqNumber,expenseCode,rowIndex){
	var ret = confirm("Are you sure? you can not undo this operation?");
	if (ret == true) {
	} else {
		return false;
	}
	$.ajax({
		type: "POST",
		url: "deleteRMSAttachment",
		data:{reqNumber:reqNumber,expenseCode:expenseCode},
		dataType: 'json',
		success: function(result){
			if(result == 'true'){
					alert("Attachment has been successfully removed");
					window.location.reload();
					//$('#tab_logicExp tr:eq('+(rowIndex)+') td:eq(7)').html("<input type='file' onchange='valFileUpload(this.value,this.id)' name='file' path='expenseList["+rowIndex+"].expenseDocumentList' id='expenseList["+rowIndex+"].expenseDocumentList' style='width:200px; position: absolute; clip: rect(0px 0px 0px 0px);' class='filestyle' data-buttontext='PDF' tabindex='-1'><div class='bootstrap-filestyle input-group'><input type='text' id='disableLable"+queryCount+"' class='form-control ' disabled=''> <span class='group-span-filestyle input-group-btn' tabindex='0'><label for='expenseList["+rowIndex+"].expenseDocumentList' class='btn btn-default '><span class='glyphicon glyphicon-folder-open'></span>Doc</label></span></div><input type='text' readOnly='true' style='color:red;font-size:10px;overflow:hidden;border:none;' name='expenseList["+rowIndex+"].expDocName' id='expenseList["+rowIndex+"].expDocName'/>");
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
function downloadApprovedForm(reqNumber){
	
}
function manageReimburseBuckets(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewRMSBucketDiv").style.display='block';
	document.getElementById('viewRMSBucketDiv').setAttribute("style","min-height:500px");
	$("#viewRMSBucketDiv").appendTo("#dataDiv");
	$.ajax({
		type: "POST",
		url: "getAllBucketList",
		dataType: 'json',
		success: function(data){
			document.getElementById("bucketList").innerHTML = "";
			var table = $("<table class='table table-stripped' style='width:100%;' scrolling='auto' id='bucketTable'>" +
					"<thead style='background-color: #D4E6F1;'>" +
					"<tr><th>Bucket Name</th><th>Created By</th><th>Created On</th><th style='width:8%;'>Action</th></tr>" +
					"</thead>" +
					"<tbody id='bucketTableBody'></tbody></table>");
			$("#bucketList").append(table);
			for (var i = 0; i < data.length; i++) {
				var row = $("<tr />");
				$("#bucketTableBody").append(row);
				row.append($("<td class='EWTableElements'><input class='form-control' maxlength='50' id='bucketId"+i+"' type='text' value='"+data[i].bucketname+"' readonly='true' style='width:90%;'/><a href='#!' title='Modify Bucket' onclick=editBucketName('bucketId"+i+"','"+data[i].bucketid+"');><span class='glyphicon glyphicon-edit pull-right'></span></a><a href='#!' title='Click to Save' onclick=updateBucketName('bucketId"+i+"','"+data[i].bucketid+"');><span class='glyphicon glyphicon-floppy-saved pull-right' style='margin-right: 10px;'></span></a></td>"));
				row.append($("<td class='EWTableElements'>"+data[i].bucketcreatedby+"</td>"));
				row.append($("<td class='EWTableElements'>"+data[i].bucketcreatedon+"</td>"));
				row.append($("<td class='EWTableElements' align='center'><button class='btn-primary btn-sm pull-right' title='Delete Bucket' onclick=removeRMSBucket('"+data[i].bucketid+"');>Remove</button></td>"));
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});	
}
function valBankRefSheetUpload(fileName,id)
{
	var pathname = document.getElementById(id).value;
	var fName = pathname.split('\\').pop().split('/').pop();
    var file_extension = fileName.split('.').pop(); 
        //if(file_extension != 'xlsx' || file_extension != 'xls')
    if(file_extension != 'xlsx')
        {
        	alert("Kindly attach xlsx file only");
        	document.getElementById(id).value = '';
        	return false;
        }
        if(fName != "BankReferencesExcel.xlsx"){
        	alert("Uploaded file name muse be BankReferencesExcel.xlsx");
        	document.getElementById(id).value = '';
        	return false;
        }
}
function uploadBankRefUpdateSheet(){
    var form = $('#bankFileUploadForm')[0];
    var data = new FormData(form);
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "uploadBankRefSheet",
        data: data,
        processData: false,
        contentType: false,
        success: function (data) {
            alert("Successfully uploaded.");
            document.getElementById("rmsBankRefDocumentList").value = '';
            return false;
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}
function downloadBankRefUpdateSheet(){
	var currentBucket = document.getElementById("expenseFilterBucket").value;
	var top=0;
    var left=0;
    var height='500px';
    var width='600px';
	//window.open('bucketMonthlyReport?currentBucket='+currentBucket,'ReportScreen','scrollbars=yes,resizable=yes,toolbar=no,Addressbar=no,menubar=no,status=yes,location=no,top='+top+',left='+left+',height='+height+',width='+width);
	var params = { 'currentBucket' : currentBucket };
    openNewRequest("bucketMonthlyReport",params);
}
function downloadBucketReport(){
	//var currentBucket =  $("#currentBucketName").text();
	var currentBucket = document.getElementById("expenseFilterBucket").value;
	var top=0;
    var left=0;
    var height='500px';
    var width='600px';
	//window.open('bucketMonthlyReport?currentBucket='+currentBucket,'ReportScreen','scrollbars=yes,resizable=yes,toolbar=no,Addressbar=no,menubar=no,status=yes,location=no,top='+top+',left='+left+',height='+height+',width='+width);
	var params = { 'currentBucket' : currentBucket };
    openNewRequest("bucketMonthlyReport",params);
}
function createBucketName(){
	var bucketName = document.getElementById("newBucketName").value;
	if(bucketName == ""){
		alert("Please provide valid bucket name");
		return false;
	}
	var ret = confirm("Do you want to create new bucket?");	
	if(ret == false)
		return false;
	$.ajax({
		type: "POST",
		url: "createNewRMSBucket",
		data:{bucketName:bucketName},
		dataType: 'json',
		success: function(result){
			if(result == 'true'){
					alert("Bucket has been successfully created");
					manageReimburseBuckets();
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
function updateBucketName(nameId,bucektId){
	var bucketName = document.getElementById(nameId).value;
	if(bucketName == ""){
		alert("Please provide valid bucket name");
		return false;
	}
	$.ajax({
		type: "POST",
		url: "updateRMSBucket",
		data:{bucketName:bucketName,bucektId:bucektId},
		dataType: 'json',
		success: function(result){
			if(result == 'true'){
					alert("Bucket has been successfully updated");
					manageReimburseBuckets();
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
function editBucketName(nameId,bucektId){
	$("#"+nameId).prop('readonly', false);	
}
function removeRMSBucket(bucektId){
	$.ajax({
		type: "POST",
		url: "deleteRMSBucket",
		data:{bucektId:bucektId},
		dataType: 'json',
		success: function(result){
			if(result == 'true'){
					alert("Successfully removed");
					manageReimburseBuckets();
			}else{
				alert("One or more entry exist in bucket!");
				return false;
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});	
}
function getBucketList(id){
	$.ajax({
		type: "POST",
		url: "getAllBucketList",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(id);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			if(id == 'expenseFilterBucket')
				select.add(new Option('All','0'));
			else
				select.add(new Option('Select','select'));
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].bucketname,data[i].bucketid));
			    }
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function calculateSumAmount(){
	var totalBillAmt = Number("0");
	var totalClaimedAmt = Number("0");
			var rows1 = $('#piotable_exp tr');
			var id = 'piotable_exp';
			for (var i = 0;i<rows1.length; i++) {
			if($('#'+id+' tr:nth-child('+(i+1)+') td').length > 0){	
				var tempBill = document.getElementById("expenseList["+ i +"].billAmount").value;
				var tempClaimed = document.getElementById("expenseList["+ i +"].requestAmount").value;
				totalBillAmt = totalBillAmt + Number(tempBill);
				totalClaimedAmt =totalClaimedAmt + Number(tempClaimed);
			}
			$('#totalBillAmt').html(totalBillAmt);
			$('#totalClaimedAmt').html(totalClaimedAmt);
			}
}
function fillRequestValue(i){
	var tempBill = document.getElementById("expenseList["+i+"].billAmount").value;
	  document.getElementById("expenseList["+i+"].requestAmount").value = tempBill;	
}
function validate(evt) {
	  var theEvent = evt || window.event;
	  var key = theEvent.keyCode || theEvent.which;
	  key = String.fromCharCode( key );
	  var regex = /[0-9]|\./;
	  if( !regex.test(key) ) {
	    theEvent.returnValue = false;
	    if(theEvent.preventDefault) theEvent.preventDefault();
	  }
	}
function selectAllBucketCheckBox(tbodyId){
	if ($('#checkBucketid').is(":checked"))
	{
		$("#"+tbodyId).find('input[type="checkbox"]').prop('checked', true);
	}else{
		$("#"+tbodyId).find('input[type="checkbox"]').prop('checked', false);
	}
}	
function openRMSActionHistory(requestNumber){
    $.ajax({
		type: "POST",
		url: "getRMSActionHistory",
		dataType: 'json',
		data: {requestNumber:requestNumber},
	success: function(result){
		if(result.length > 0){
			$('#rmsActionHistoryModal').modal('show');
			$('#tempReqNoForModalHeader').html("Action History: "+requestNumber);
			    $('#rmsActionHistoryTbl td').remove();
				for (var i = 0; i < result.length; i++) {
					var row = "";
								if(i%2 == 0)
									row = $("<tr class='info' />");
								else
									row = $("<tr class='' />");														
					 	$("#tab_logic_rmsActionHistory").append(row); 
					 	row.append($("<td class='EWTableElements'>"+result[i].action+"</td>"));
					 	row.append($("<td class='EWTableElements'>"+result[i].actionBy+"</td>"));
					 	row.append($("<td class='EWTableElements'>"+result[i].actionDate+"</td>"));
					 	row.append($("<td class='EWTableElements'>"+result[i].actionRemark+"</td>"));
					 	var temp = result[i].assignedToTitle.replace("[", " ");
					 	var temp2 = temp.replace("]", "");
					 	row.append($("<td class='EWTableElements' title='"+temp2.replace(/,/g , "\n")+"'>"+result[i].assignedTo+"</td>"));
				    }
				}else{
						alert("No action has been taken yet");
						return false;
		}
			},
			error: function(jqXHR, textStatus)
			{
				alert("ERROR:"+textStatus);
			}
		});
}
function getReimbursementReoprtParam(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("vieReimbursementReportDiv").style.display='block';
	document.getElementById('vieReimbursementReportDiv').setAttribute("style","min-height:500px;");
	$("#vieReimbursementReportDiv").appendTo("#dataDiv");
	populateEmployeeList('userListForReimbursementReport');
	populateStatusList('expenseStatusList');
	populateBucketList('bucketListForReimbursementReport');
	getProjectListForExpense('prjctListForReimbursementReport');
}
function getProjectListForExpense(id){
	$.ajax({
		type: "POST",
		url: "getExpenseProjectList",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(id);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Project',''));
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].projectName,data[i].projectName));
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function populateEmployeeList(elementId){
	$.ajax({
		type: "POST",
		url: "getUserListForExpenseReport",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(elementId);
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
function populateStatusList(elementId){

	$.ajax({
		type: "POST",
		url: "getExpenseStatusList",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(elementId);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Status',''));
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].status,data[i].status));
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});

}
function populateBucketList(elementId){
	$.ajax({
		type: "POST",
		url: "getAllBucketList",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(elementId);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Bucket',''));
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].bucketname,data[i].bucketid));
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function generateExpenseReport(){
	var projectId = document.getElementById("prjctListForReimbursementReport").value;
	var username = document.getElementById("userListForReimbursementReport").value;
	var startDate = document.getElementById("from-datepicker-expense").value;
	var endDate = document.getElementById("to-datepicker-expense").value;
	var status = document.getElementById("expenseStatusList").value;
	var bucketId = document.getElementById("bucketListForReimbursementReport").value;
	if(startDate == "" || startDate == undefined || startDate == null){
		alert("Please select valid start date.");
		return false;
	}
	if(endDate == "" || endDate == undefined || endDate == null){
		alert("Please select valid end date.");
		return false;
	}
	document.getElementById("loaderGif").style.display='block';
	$.ajax({
		type: "POST",
		url: "viewReimbursementReport",
		dataType: 'json',
		data: {projectId:projectId,username:username,bucketId:bucketId,startDate:startDate,endDate:endDate,status:status},
		success: function(data){
			document.getElementById("loaderGif").style.display='none';
			$("#vieExpReportDataDiv").empty();
			if(data.length > 0){
				$("#vieExpReportDataDiv").append($("<span><a href='currentExpenseReport'><button type='button' class='btn btn-primary pull-left' style='margin:5px;'>Download xls</button></a></span>"));
				$("#vieExpReportDataDiv").append($("<table class='table table-stripped' style='background-color: #EBF5FB;' scrolling='auto'><thead style='background-color: #D4E6F1;'><tr><th>Request No</th><th>Emp Name</th><th>RM Name</th><th>Claimed Amt</th><th>Approved Amt</th><th>Status</th><th>Bucket Name</th><th>Last Modified On</th></tr></thead><tbody id='expReportBody'></tbody></table>"));
			for(var i=0;i<data.length;i++){
				var row = $("<tr  />");														
			 	$("#expReportBody").append(row);
			 	row.append($("<td  class='EWTableElements'><a href='#!' onclick=viewRMSDetail('"+data[i].requestnumber+"')>"+data[i].requestnumber+"</a></td>"));
			 	row.append($("<td  class='EWTableElements'>"+data[i].fullname+"</td>"));
			 	row.append($("<td  class='EWTableElements'>"+data[i].rmname+"</td>"));
			 	row.append($("<td  class='EWTableElements'>"+data[i].requestedamount+"</td>"));
			 	row.append($("<td  class='EWTableElements'>"+data[i].approvedamount+"</td>"));
			 	row.append($("<td  class='EWTableElements'>"+data[i].status+"</td>"));
			 	/*row.append($("<td  class='EWTableElements'>"+data[i].createdon+"</td>"));*/
			 	row.append($("<td  class='EWTableElements'>"+data[i].bucketname+"</td>"));
			 	/*row.append($("<td  class='EWTableElements'>"+data[i].lastmodifiedby+"</td>"));*/
			 	row.append($("<td  class='EWTableElements'>"+data[i].lastmodifiedon+"</td>"));
			}	
			}else{
				$("#vieExpReportDataDiv").append($("<div style='margin-left:20px;'><span style='color:red;'>No data found in database.</span></div>"));
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function viewRMSDetail(reqNumber){
	$.ajax({
		type: "POST",
		url: "getExpanseDetailByReqNumber",
		data:{reqNumber:reqNumber},
		dataType: 'json',
		success: function(result){
			$("#rmsDetailModal").modal('show');
			$("#rmsModalDiv").empty();
			$("#rmsModalDiv").append($("<table class='table table-stripped' style='background-color: #EBF5FB;' scrolling='auto'><thead style='background-color: #D4E6F1;'><tr><th>Expense Head</th><th>Project</th><th>Bill No</th><th>Bill Date</th><th>Bill Amt</th><th>Claimed Amt</th><th>Approved Amt</th><th>Remark(Applicant)</th><th>Attachment</th></tr></thead><tbody id='reqNumUserDataBody'></tbody></table>"));
			for (var i = 0; i < result.length; i++) {
				var row = $("<tr />");
				$("#reqNumUserDataBody").append(row);
				row.append($("<td class='EWTableElements'>" + result[i].expensename + "</td>"));
				row.append($("<td class='EWTableElements'>" + result[i].projectname + "</td>"));
				row.append($("<td class='EWTableElements'>"+result[i].billnumber +"</td>"));
				row.append($("<td  class='EWTableElements'>"+result[i].billdate+"</td>"));
				row.append($("<td  class='EWTableElements'>"+result[i].billamount+"</td>"));
				row.append($("<td  class='EWTableElements'>"+result[i].requestedamount+"</td>"));
				row.append($("<td  class='EWTableElements' style='font-weight:600;color:red;'>"+result[i].approvedamount+"</td>"));
				row.append($("<td  class='EWTableElements'>"+result[i].applicantremark+"</td>"));
				if(result[i].billattachment == "" || result[i].billattachment == "NA")
					row.append($("<td  class='EWTableElements'>NA</td>"));
				else
					row.append($("<td  class='EWTableElements' style='overflow:hidden;'><a href='#!' id='viewDocHref["+i+"]' title='Open "+result[i].billattachment+"' onclick=viewSavedDoc('"+reqNumber+"','"+result[i].expensecode+"','viewDocHref["+i+"]');>"+result[i].billattachment.substr(0, 4) + "..." +result[i].billattachment.substr(result[i].billattachment.length-8, result[i].billattachment.length)+"</a></td>"));
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}