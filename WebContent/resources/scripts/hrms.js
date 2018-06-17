var globalAnnonanceData = "";
function populateDocParameterDocGen(docType){
	$.ajax({
		type: "POST",
		url: "fetchDocRequiredParamList",
		dataType: 'json',
		data:{docType:docType},
		success: function(data){
			if(data.length > 0){
				var divString = "<span><strong>Add Parameter:</strong></span>";
				data = data[0];
				for(var i=0;i<data.length;i++){
					var temp = data[i] + "_TAG" + i;
					divString = divString + "<input type='button' class='btn btn-sm' value='"+temp+"' title='Add "+temp+" to template' style='margin:5px;border-radius:5px;background-color:yellow;' id='paramBtnId"+i+"' onclick=populateIntoEditor2('${"+temp+"}','"+i+"'); /><br>";
				}
				$("#populateFieldId").empty();
				$("#populateFieldId").append(divString);
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function populateIntoEditor2(paramName,index){
	var templateData = CKEDITOR.instances['createTemplateEditorId'].getData();
	var paramBtnId = "paramBtnId"+index;
	var btnTitle = $("#"+paramBtnId).attr("title");
	templateData = templateData + "<p><b>"+paramName+"</b></p>";
	CKEDITOR.instances['createTemplateEditorId'].setData(templateData);
}
function generateDoc(dataLength,docType){
	var dataString = dataLength;
	for(var i =0;i<dataLength;i++){
		dataString = dataString + "," + document.getElementById("dataId"+i).value;
	}
	$.ajax({
		type: "POST",
		url: "createUserDocumentById",
		dataType: 'json',
		data:{dataString:dataString,docType:docType},
		success: function(data){
			if(data.length > 0){
				window.open("downloadEmpDoc?filename="+data[0].fileName,"eManagerDoc");
			}else{
				alert("Please try after some time");
				return false;
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}	
function generateEmployeeDocuments(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewGenerateDocDiv").style.display='block';
	document.getElementById('viewGenerateDocDiv').setAttribute("style","min-height:545px");
	$("#viewGenerateDocDiv").appendTo("#dataDiv");
}
function createEmployeeDocRTE(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewCreateDocRTEDiv").style.display='block';
	document.getElementById('viewCreateDocRTEDiv').setAttribute("style","min-height:545px");
	$("#viewCreateDocRTEDiv").appendTo("#dataDiv");
	populateDocListforDocGen('Y','templateDocType');
	CKEDITOR.replace( 'editor2',{
		height:"280px",
		width:"800px",
    });
}
function generateEmployeeDocumentsRTE(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewGenerateDocRTEDiv").style.display='block';
	document.getElementById('viewGenerateDocRTEDiv').setAttribute("style","min-height:545px");
	$("#viewGenerateDocRTEDiv").appendTo("#dataDiv");
}
function populateUserGroupListforDocGen(value,id){
	populateDropdownElement("getUserGroupListBasedOnPolicy",id,"Select Group",value);
}
function populateDocListforDocGen(value,id){
	populateDropdownElement("getDocListBasedOnPolicyGroup",id,"Select Document",value);
}
function populateUserTemplateListforDocGen(value,id){
	populateDropdownElement("getTemplateListBasedOnDocName",id,"Select Template",value);
}
function punchedTimeFromClientLoc1(countryCode,city,region,loc,clientIp){
	$.ajax({
		type: "POST",
		url: "punchedTimeFromClientSide",
		dataType: 'json',
		data:{countryCode:countryCode,city:city,region:region,loc:loc,clientIp:clientIp},
		success: function(data){
			alert(data);
			checkCurrentDayPunchStatus();
			return false;
		},
		error: function(jqXHR, textStatus)
		{
			document.getElementById("loaderGif").style.display='none';
			alert("ERROR:"+textStatus);
		}
	});
}
function punchedTimeFromClientLoc(){
	var clientIp = clientIp1;
	var ret = confirm("Are you sure? You can SignIn/SignOut once in 24 hours.");
	if(ret == false)
		return false;
	$.ajax({
		type: "POST",
		url: "http://ipinfo.io",
		dataType: 'json',
		data:{clientIp:clientIp},
		success: function(data){
			punchedTimeFromClientLoc1(data.country,data.city,data.region,data.loc,clientIp);
		},
		error: function(jqXHR, textStatus)
		{
			document.getElementById("loaderGif").style.display='none';
			alert("ERROR:"+textStatus);
		}
	});
}
function checkCurrentDayPunchStatus(){
	$.ajax({
		type: "POST",
		url: "checkUsersCurrentPunchedRecord",
		dataType: 'json',
		success: function(data){
			if(data[0].signin == "true" && data[0].signout == "true"){
				document.getElementById("punchedInOutListId").style.display='none';
			}else if(data[0].signin == "true" && data[0].signout == "false"){
				$("#punchedInOutId").html("Sign Out");
			}else{
				$("#punchedInOutId").html("Sign In");
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function viewCompanyNotificationDiv(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewCompNotificationDiv").style.display='block';
	document.getElementById('viewCompNotificationDiv').setAttribute("style","min-height:545px");
	$("#viewCompNotificationDiv").appendTo("#dataDiv");
	fetchManageCompanyNotification();
}
function fetchManageCompanyNotification(){
	 $.ajax({
			type: "POST",
			url: "fetchManageCompanyNotifications",
			dataType: 'json',
		success: function(result){
			if(result.length > 0){
				globalAnnonanceData = result;
				    $('#compNotificationTable td').remove();
					for (var i = 0; i < result.length; i++) {
						var row = "";
									if(i%2 == 0)
										row = $("<tr class='info' />");
									else
										row = $("<tr class='info' />");														
						 	$("#tab_logic_comp_notification").append(row); 
						 	row.append($("<td class='EWTableElements'>"+(i+1)+"</td>"));
						 	row.append($("<td class='EWTableElements'>"+result[i].policyGroup+"</td>"));
						 	row.append($("<td class='EWTableElements'>"+result[i].type+"</td>"));
						 	row.append($("<td class='EWTableElements' title='"+result[i].descrition+"'>"+result[i].title+"</td>"));
						 	if(result[i].isActive == "Y" || result[i].isActive == "y")
						 		row.append($("<td class='EWTableElements'><label class='switch' style='margin:3px;'><input type='checkbox' id='status"+result[i].notificationrfnum+"' checked onchange='updateNotificationStatus(this.id);'><span class='slider round'></span></label></td>"));
						 	else
						 		row.append($("<td class='EWTableElements'><label class='switch' style='margin:3px;'><input type='checkbox' id='status"+result[i].notificationrfnum+"' onchange='updateNotificationStatus(this.id);'><span class='slider round'></span></label></td>"));
						 	row.append($("<td class='EWTableElements'>"+result[i].lastmodifiedon+"</td>"));
						 	if(result[i].attachmentname == "NA" || result[i].attachmentname == "" || result[i].attachmentname == undefined || result[i].attachmentname == null)
						 		row.append($("<td class='EWTableElements'>NA</td>"));
						 	else
						 		row.append($("<td class='EWTableElements'><a href='#l' onclick=openCompNotofication('"+result[i].notificationrfnum+"')>"+result[i].attachmentname+"</td>"));
						 	row.append($("<td class='EWTableElements'><a href='#l' onclick=deleteNotfication('"+result[i].notificationrfnum+"');return false;><span class='glyphicon glyphicon-trash' style='color:black;'></span></a>" +
						 			"<a href='#l' onclick=editNotfication('"+i+"');return false;><span class='glyphicon glyphicon-edit' style='color:red;margin-left:3px;'></span></a></td>"));
					    }
					}
				},
				error: function(jqXHR, textStatus)
				{
					alert("ERROR:"+textStatus);
				}
			});
}

function addNewAnnouncement(){
	$('#addNewNotificationModal').modal('show');
	populatePolicyGroup('notificationPolicyGroup');
	$("#id_notificationName").val('');
}
function insertNewCompNotification(){
	var notificationrfnum = "";
	var type = document.getElementById('id_NotificationType').value;
	var policyGroup = document.getElementById('notificationPolicyGroup').value;
	var title = document.getElementById('id_notificationName').value;
	var description = document.getElementById('id_NotificationDesc').value;
	var attachment = document.getElementById('id_NotificationAttach').value;
	$.ajax({
		type: "POST",
		url: "addModifyNewCompAnnouncement",
		dataType: 'json',
		data:{notificationrfnum:notificationrfnum,type:type,description:description,policyGroup:policyGroup,title:title,attachment:attachment},
		success: function(data){
			if(data)
				alert("New notification added successfully");
			$('#addNewNotificationModal').modal('hide');
			fetchManageCompanyNotification();
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function valNotificationUpload(fileName,id,filenameId)
{
	var pathname = document.getElementById(id).value;
	var fName = pathname.split('\\').pop().split('/').pop();
    var file_extension = fileName.split('.').pop(); 
    if(file_extension != "pdf"){
        	alert("Kindly attach PDF file only");
        	document.getElementById(id).value = '';
        	document.getElementById(filenameId).value = '';
        	return false;
        }else{
        	document.getElementById(filenameId).value = fName;
        }
}
function uploadNotificationDoc1(){
    var form = $('#notificationDocUploadForm')[0];
    var data = new FormData(form);
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "uploadNotificationAttachment",
        data: data,
        processData: false,
        contentType: false,
        async:false,
        success: function (data) {
        	document.getElementById("uploadNotificationDoc").value = '';
        	insertNewCompNotification();
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}
function openCompNotofication(notificationid){
	var top=0;
    var left=0;
    var height='500px';
    var width='600px';
    if(notificationid == "")
    	return false;
    else{
    	window.open('viewCompanyNotificationDoc?notificationid='+notificationid,'DocScreen','scrollbars=yes,resizable=yes,toolbar=no,Addressbar=no,menubar=no,status=yes,location=no,top='+top+',left='+left+',height='+height+',width='+width);
    }	
    return false;
}
function viewPunchedRecordFromClientLoc(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewPunchedFromOnsiteDiv").style.display='block';
	document.getElementById('viewPunchedFromOnsiteDiv').setAttribute("style","min-height:545px");
	$("#viewPunchedFromOnsiteDiv").appendTo("#dataDiv");
	getAllUserForReport(false,'username-punchLoc','');
}
function fetchPunchedRecordFromClientLoc(){
	var startDate = $("#from-datepicker-punchLoc").val();
	var endDate = $("#to-datepicker-punchLoc").val();
	var username = $("#username-punchLoc").val();
	if(startDate == "" || endDate == "" || username == ""){
		alert("Please select valid input.");
		return false;
	}
	 $.ajax({
			type: "POST",
			url: "fetchPunchedRecordFromRemoteLoc",
			dataType: 'json',
			data:{startDate:startDate,endDate:endDate,username:username},
		success: function(result){
			if(result.length > 0){
				    $('#punchRecordFromOnsiteTable td').remove();
					for (var i = 0; i < result.length; i++) {
						var row = "";
									if(i%2 == 0)
										row = $("<tr class='info' />");
									else
										row = $("<tr class='info' />");														
						 	$("#tab_logic_punchRecordFromOnsite").append(row); 
						 	row.append($("<td class='EWTableElements'>"+(i+1)+"</td>"));
						 	row.append($("<td class='EWTableElements'>"+result[i].username+"</td>"));
						 	/*row.append($("<td class='EWTableElements'>"+result[i].fullname+"</td>"));*/
						 	row.append($("<td class='EWTableElements'>"+result[i].date+"</td>"));
						 	row.append($("<td class='EWTableElements'>"+result[i].city+"</td>"));
						 	row.append($("<td class='EWTableElements'>"+result[i].region+"</td>"));
						 	row.append($("<td class='EWTableElements'>"+result[i].postalcode+"</td>"));
						 	row.append($("<td class='EWTableElements'>"+result[i].signintime+"</td>"));
						 	row.append($("<td class='EWTableElements'>"+result[i].signouttime+"</td>"));
						 	row.append($("<td class='EWTableElements'>"+result[i].status+"</td>"));
					    }
					}
				},
				error: function(jqXHR, textStatus)
				{
					alert("ERROR:"+textStatus);
				}
			});
}
function deleteNotfication(notificationid){

	var ret = confirm("Are you sure?");
	if (ret == true) {} else {
		return false;
	}
	$.ajax({
		type: "POST",
		url: "deleteHRMSNotfication",
		data:{notificationid:notificationid},
		dataType: 'json',
		success: function(result){
			if(result == 'true'){
					alert("Successfully removed");
					fetchManageCompanyNotification();
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});	
}
function editNotfication(i){
	$('#modifyNotificationModal').modal('show');
	$("#modify_NotificationRfNum").val(globalAnnonanceData[i].notificationrfnum);
	$("#modify_NotificationType").html(globalAnnonanceData[i].type);
	$("#modify_notificationPolicyGroup").html(globalAnnonanceData[i].policyGroup);
	$("#modify_notificationName").val(globalAnnonanceData[i].title);
	$("#modify_NotificationDesc").val(globalAnnonanceData[i].descrition);
}
function updateCompNotification(){
	var notificationrfnum = document.getElementById('modify_NotificationRfNum').value;
	var type = document.getElementById('modify_NotificationType').innerHTML;
	var policyGroup = document.getElementById('modify_notificationPolicyGroup').innerHTML;
	var title = document.getElementById('modify_notificationName').value;
	var description = document.getElementById('modify_NotificationDesc').value;
	var attachment = document.getElementById('modify_NotificationAttach').value;
	$.ajax({
		type: "POST",
		url: "addModifyNewCompAnnouncement",
		dataType: 'json',
		data:{notificationrfnum:notificationrfnum,type:type,description:description,policyGroup:policyGroup,title:title,attachment:attachment},
		success: function(data){
			if(data)
				alert("Announancement updated successfully");
			$('#modifyNotificationModal').modal('hide');
			fetchManageCompanyNotification();
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function uploadNotificationDoc2(){
    var form1 = $("#modifyNotificationDocUploadForm")[0];
    var data = new FormData(form1);
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "uploadNotificationAttachment",
        data: data,
        processData: false,
        contentType: false,
        async:false,
        success: function (result) {
        	document.getElementById("uploadNotificationDoc2").value = '';
        	updateCompNotification();
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}
function updateNotificationStatus(id){
	var status = false;
	if ($('#'+id).is(":checked")){
	  status = true;
	}else{
	  status = false;	
	}
	var notificationid = id.substring(6);
$.ajax({
	type: "POST",
	url: "updateNotificationStatus",		
	dataType: 'json',
	data:{notificationid:notificationid,status:status},
	success: function(data){
		if(data[0]){
			fetchManageCompanyNotification();
		}else{
			alert("Try after some time.");
		}
	},
	error: function(jqXHR, textStatus)
	{
	alert("ERROR:Error Occured");
	}
});
}
function fetchGenDocTemplate(elemId){
	var templateId = document.getElementById(elemId).value;
	$.ajax({
		type: "POST",
		url: "fetchRTEDocRequiredParamList",
		dataType: 'json',
		data:{docType:templateId},
		success: function(data){
			if(data.length > 0){
				console.log(data[0].templateData);
				document.getElementById("populateRTEFieldId").style.display='block';
				$("#templateEditorId").val(data[0].templateData);
				CKEDITOR.replace( 'editor1' );
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function openRTERequiredParamModal(rteTextAreaId){
	var templateData = CKEDITOR.instances['createTemplateEditorId'].getData();
	if("templateData" == ""){
		alert("Please write data before creating template format.");
		return false;
	}
	$('#rteParamDetailModal').modal('show');
	populatePolicyGroup('rteTempPolicyGroup');
	groupListForUserModification('rteTempUserGroup');
	$("#rteTemplateId").val(templateData);
}
function createNewDocTemplate(){
	var policyGroup = document.getElementById("rteTempPolicyGroup").value;
	var userGroup = document.getElementById("rteTempUserGroup").value;
	var docType = document.getElementById("rteDocType").value;
	var docDesc = document.getElementById("rteDocDesc").value;
	var templateName = document.getElementById("rteTemplateName").value;
	var templateData = document.getElementById("rteTemplateId").value;
	
	if(policyGroup == ""){
		alert("Please provide valid Policy Group.");
		return false;
	}
	if(userGroup == ""){
		alert("Please provide valid User Group.");
		return false;
	}
	if(docType == ""){
		alert("Please provide valid Document Type such as Offer Letter,Confirmation Letter etc.");
		return false;
	}
	if(docDesc == ""){
		alert("Please provide valid document description.");
		return false;
	}
	if(templateName == ""){
		alert("Please provide valid Template Name.");
		return false;
	}
	if(templateData == ""){
		alert("Please write data before creating template format.");
		return false;
	}
	$.ajax({
		type: "POST",
		url: "createNewDocTemplate",
		dataType: 'json',
		data:{policyGroup:policyGroup,userGroup:userGroup,docType:docType,docDesc:docDesc,templateName:templateName,templateData:templateData},
		success: function(data){
			if(data.length > 0){
				$('#rteParamDetailModal').modal('hide');
				alert("New "+templateName+" has been created successfylly for group "+userGroup+".");
				$("#templateEditorId").val("");
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function openGenerateNewEmpDocModal(){
	$('#rteDocUserDetailModal').modal('show');
	var templateData = CKEDITOR.instances['templateEditorId'].getData();
	$("#rteUserDocId").val(templateData);
}
function generateNewEmpDoc(){
	var username = document.getElementById("rteGenDocUsername").value;
	var fullname = document.getElementById("rteGenDocFullname").value;
	var email = document.getElementById("rteGenDocEmail").value;
	var templateData = document.getElementById("rteUserDocId").value;
	var d = new Date();	
	if(fullname == ""){
		alert("Please provide user full name.");
		return false;
	}
	if(username == ""){
		username = "SUP"+(d.getMonth()+1)+d.getFullYear()+ d.getHours() + d.getMinutes();
	}
	if(templateData == ""){
		alert("Please edit template before generating document.");
		return false;
	}
	var datestring = d.getDate() + (d.getMonth()+1) + d.getFullYear() + d.getHours() + d.getMinutes();
	var docName = username + datestring;
	$.ajax({
		type: "POST",
		url: "generateEmpDocThroughTemplate",
		dataType: 'json',
		data:{username:username,fullname:fullname,docName:docName,email:email,templateData:templateData},
		success: function(data){
			if(data.length > 0){
				alert("New document "+docName+" has been generated successfylly for user "+fullname+".");
				document.getElementById("rteGenDocUsername").value="";
				document.getElementById("rteGenDocFullname").value="";
				document.getElementById("rteGenDocEmail").value="";
				document.getElementById("rteUserDocId").value="";
				$('#rteDocUserDetailModal').modal('hide');
				resetTemplateForm();
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function trackGeneratedEmpocumentsRTE(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewGeneratedDocDiv").style.display='block';
	document.getElementById('viewGeneratedDocDiv').setAttribute("style","min-height:500px;");
	$("#viewGeneratedDocDiv").appendTo("#dataDiv");
	var activeFlag = "Y";
	document.getElementById("loaderGif").style.display='block';
	$.ajax({
		type: "POST",
		url: "getAllGeneratedDocuments",
		data:{activeFlag:activeFlag},
		dataType: 'json',
		success: function(result){
			document.getElementById("loaderGif").style.display='none';
			if(result.length >0){
			$('#viewEmpGenDocTbl td').remove();
			for (var i = 0; i < result.length; i++) {
				var row = "";
							if(i%2 == 0)
								row = $("<tr class='info' />");
							else
								row = $("<tr class='info' />");														
				 	$("#viewEmpDirTblBody").append(row); 
				 	row.append($("<td class='EWTableElements'>"+(i+1)+"</td>"));
				 	row.append($("<td class='EWTableElements'>"+result[i].username+"</td>"));
				 	row.append($("<td class='EWTableElements'>"+result[i].fullname+"</td>"));
				 	row.append($("<td class='EWTableElements'>"+result[i].email+"</td>"));
				 	row.append($("<td class='EWTableElements'>"+result[i].docname+"</td>"));
				 	row.append($("<td class='EWTableElements'>" +
				 			"<a href='downloadEmpDocPdf?rfnum="+result[i].rfnum+"' target='_blank' title='Downlaod PDF' style='color:blue;margin-left:5px;'><img src='/"+contextName+"/resources/images/icon-pdf.png'/></a>" +
				 			"<a href='downloadEmpDocxFile?rfnum="+result[i].rfnum+"' target='_blank' title='Downlaod Doc' style='color:blue;margin-left:5px;'><img src='/"+contextName+"/resources/images/icon-doc.png'/></a></td>"));
				 	row.append($("<td class='EWTableElements'>" +
				 			"<a href='#!' title='View' style='color:black;margin-left:5px;' onclick=rteDocAction('"+result[i].rfnum+"','View');><span class='glyphicon glyphicon-eye-open' style='color:black;'></span></a>" +
				 			"<a href='#!' title='Edit' style='color:black;margin-left:5px;' onclick=rteDocAction('"+result[i].rfnum+"','Edit');><span class='glyphicon glyphicon-edit' style='color:black;'></span></a>" +
				 			"<a href='#!' title='Delete' style='color:black;margin-left:5px;' onclick=rteDocAction('"+result[i].rfnum+"','Delete');><span class='glyphicon glyphicon-trash' style='color:black;'></span></a></td>"));
			    }
			}else{
				alert("No result found in database");
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function resetTemplateForm(){
	$('select').val('');
}
function rteDocAction(docrfnum,action){
	var top=0;
    var left=0;
    var height='500px';
    var width='600px';
	if(action.toUpperCase() == "VIEW"){
		window.open('viewGeneratedTempDoc?docrfnum='+docrfnum,'DocScreen','scrollbars=yes,resizable=yes,toolbar=no,Addressbar=no,menubar=no,status=yes,location=no,top='+top+',left='+left+',height='+height+',width='+width);
	}
	if(action.toUpperCase() == "EDIT"){
		window.open('editGeneratedTempDoc?docrfnum='+docrfnum,'DocScreen','scrollbars=yes,resizable=yes,toolbar=no,Addressbar=no,menubar=no,status=yes,location=no,top='+top+',left='+left+',height='+height+',width='+width);
	}
	if(action.toUpperCase() == "DELETE"){
		var ret = confirm("Are you sure?");
		if(!ret)
			return false;
		$.ajax({
			type: "POST",
			url: "deleteGeneratedTempDoc",
			dataType: 'json',
			data:{docrfnum:docrfnum},
			success: function(data){
				if(data.length > 0){
					alert("Document has been deleted successfully.");
					trackGeneratedEmpocumentsRTE();
				}
			},
			error: function(jqXHR, textStatus)
			{
				alert("ERROR:"+textStatus);
			}
		});
	}
}
function updateEmployeeDocumentRTE(docrfnum){
	var templateData = CKEDITOR.instances['editEmpDocEditorId'].getData();
	if(templateData == ""){
		alert("You can not updated blank document");
		return false;
	}
	$.ajax({
		type: "POST",
		url: "updateEmpDocTemplate",
		dataType: 'json',
		data:{docrfnum:docrfnum,templateData:templateData},
		success: function(data){
			console.log(data[0]);
			if(data[0] == true){
				alert("Document updated successfully.");
				//window.close();
			}else{
				alert("Contact Administrator.");
				//window.close();
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function setPopoverData(funcKey,popId){
	var liString = "";
	for (var key in tooltipMap){
		  if(key == funcKey){
			  var arr = tooltipMap[funcKey];
			  for(var i=0;i<arr.length;i++){
				  liString = liString + "<li class='nowrap' style='font-weight:400;font-size:14px;color:#5f6468;font-family:'Open Sans',sans-serif;'>"+arr[i]+"</li>";
			  	}
			  }
	}
	var finalString = "<ul id='pop"+popId+"' style='margin-left:-20px;'>"+liString+"</ul>";
	$("[data-toggle="+popId+"]").popover({
		container: "body",
		html: true,
		delay: {show: 800, hide: 100}
    });
	$("[data-toggle="+popId+"]").attr('data-content',finalString).popover('show');
	//.css('min-width',"600px")
}
