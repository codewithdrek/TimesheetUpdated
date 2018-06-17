function fetchUserCountInSystem(){
	$.ajax({
		type: "POST",
		url: "fetchUserCountInSystem",		
		dataType: 'json',
		success: function(data){
			if(data.length > 0){
				var usrCount = 0;
				for(var i=0;i<data.length;i++){
					if(data[i].modulename == "User"){
						if(data[i].statusname == "Active")
							$("#activeUserCount").html(data[i].count);
						if(data[i].statusname == "Deleted")
							$("#deletedUserCount").html(data[i].count);
						if(data[i].statusname == "Disable")
							$("#disableUserCount").html(data[i].count);
						usrCount = usrCount + Number (data[i].count);
					}
				}
				$("#allUserCount").html(usrCount);
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:Error Occured");
		}
	});

}
function fetchProjectCountInSystem(){
	$.ajax({
		type: "POST",
		url: "fetchProjectCountInSystem",		
		dataType: 'json',
		success: function(data){
			if(data.length > 0){
				var expCount = 0;
				for(var i=0;i<data.length;i++){
					if(data[i].modulename == "Project"){
						if(data[i].statusname == "Completed")
							$("#completedCount").html(data[i].count);
						if(data[i].statusname == "Active")
							$("#activeCount").html(data[i].count);
						if(data[i].statusname == "InActive")
							$("#inactiveCount").html(data[i].count);
						if(data[i].statusname == "Not Started")
							$("#notStartedCount").html(data[i].count);
					}
				}	
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:Error Occured");
		}
	});

}
function fetchUserOwnItemCount(){
	$.ajax({
		type: "POST",
		url: "fetchUserOwnPendigItemCount",		
		dataType: 'json',
		success: function(data){
			if(data.length > 0){
				var expCount = 0;
				for(var i=0;i<data.length;i++){
					if(data[i].modulename == "attendance"){
						if(data[i].statusname == "Pending")
							$("#pendingSelfMOAFCount").html(data[i].count);
						if(data[i].statusname == "Approved")
							$("#approvedSelfMOAFCount").html(data[i].count);
						if(data[i].statusname == "Rejected")
							$("#rejectedSelfMOAFCount").html(data[i].count);
					}
					if(data[i].modulename == "reimbursement"){
						if(data[i].statusname.indexOf("Pending") !== -1 || data[i].statusname.indexOf("pending") !== -1){
							expCount = expCount + Number (data[i].count);
							$("#pendingSelfExpCount").html(expCount);
						}if(data[i].statusname == "Approved")
							$("#approvedSelfExpCount").html(data[i].count);
						if(data[i].statusname == "Rejected")
							$("#rejectedSelfExpCount").html(data[i].count);
					}
					if(data[i].modulename == "timesheet"){
						if(data[i].statusname.indexOf("Pending") !== -1 || data[i].statusname.indexOf("pending") !== -1){
							$("#pendingSelfTSCount").html(data[i].count);
						}if(data[i].statusname == "Approved")
							$("#approvedSelfTSCount").html(data[i].count);
						if(data[i].statusname == "Rejected")
							$("#rejectedSelfTSCount").html(data[i].count);
					}
					if(data[i].modulename == "leave"){
						if(data[i].statusname.indexOf("Pending") !== -1 || data[i].statusname.indexOf("pending") !== -1){
							$("#pendingSelfLeaveCount").html(data[i].count);
						}if(data[i].statusname == "Approved")
							$("#approvedSelfLeaveCount").html(data[i].count);
						if(data[i].statusname == "Rejected")
							$("#rejectedSelfLeaveCount").html(data[i].count);
						if(data[i].statusname == "Deleted")
							$("#deletedSelfLeaveCount").html(data[i].count);
					}
				}	
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:Error Occured");
		}
	});
}


function fetchOtherUseresItemCount(){
	$.ajax({
		type: "POST",
		url: "fetchOthersUsersItemCount",		
		dataType: 'json',
		success: function(data)
		
			{
							if(data.length > 0){
								var expCount = 0;
								for(var i=0;i<data.length;i++){
								
									
							if(data[i].modulename == "attendance")
							{
									if(data[i].statusname == "Pending")
									{
										    $("#pendingOtherMOAFCount").html(data[i].count);
										    $("#moafPendingCount").html(data[i].count);
								    }
									
									if(data[i].statusname == "Approved")
									$("#approvedOtherMOAFCount").html(data[i].count);
									 
									if(data[i].statusname == "Rejected")
									$("#rejectedOtherMOAFCount").html(data[i].count);
							}
									
									
									
							if(data[i].modulename == "reimbursement")
							{
									if(data[i].statusname.indexOf("Pending") !== -1 || data[i].statusname.indexOf("pending") !== -1)
									{
										expCount = expCount + Number (data[i].count);
										$("#pendingOtherExpCount").html(expCount);
										$("#rmsPendingCount").html(expCount);
									}
									
									if(data[i].statusname == "ApprovedByYou")
									{
										$("#approvedOtherExpCount").html(data[i].count);console.log(data[i].count);
									}
									
									if(data[i].statusname == "RejectedByYou")
									{
										$("#rejectedOtherExpCount").html(data[i].count);console.log(data[i].count);
									}
								}
								
							
							if(data[i].modulename == "timesheet")
							   {
									if(data[i].statusname.indexOf("Pending") !== -1 || data[i].statusname.indexOf("pending") !== -1)
									{
										$("#pendingOtherTSCount").html(data[i].count);
										$("#approvalPendingCount").html(data[i].count);
									}
									
									if(data[i].statusname == "Approved")
										$("#approvedOtherTSCount").html(data[i].count);
									
									if(data[i].statusname == "Rejected")
										$("#rejectedOtherTSCount").html(data[i].count);
							}
							
								
								
							if(data[i].modulename == "leave")
								{
									if(data[i].statusname.indexOf("Pending") !== -1 || data[i].statusname.indexOf("pending") !== -1){
										$("#pendingOtherLeaveCount").html(data[i].count);
										$("#leavePendingCount").html(data[i].count);
									}
									
									if(data[i].statusname == "Approved")
										$("#approvedOtherLeaveCount").html(data[i].count);
									
									if(data[i].statusname == "Rejected")
										$("#rejectedOtherLeaveCount").html(data[i].count);
								}
												
							  }	
							}
	         },
		
        error: function(jqXHR, textStatus)
		{
		   alert("ERROR:Error Occured");
		}
	});
}
function fetchUserDisplayDivAndQuickLinks(){
	$.ajax({
		type: "POST",
		url: "fetchUsrQuickLinkAndDisplayDiv",		
		dataType: 'json',
		success: function(data){
			if(data.length > 0){
				for(var i=0;i<data.length;i++){
					document.getElementById(data[i].elemId).style.display='block';
					$("#chk" + data[i].elemId).prop('checked', true);
				}	
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:Error Occured");
		}
	});
}
function updateDisplayAccess(checkBoxId){
	var panelId = checkBoxId.substring(3);
	var status = false;
	if ($('#chk'+panelId).is(":checked")){
	  status = true;
	}
	$.ajax({
		type: "POST",
		url: "updateUsrDisplayDivStatus",		
		dataType: 'json',
		data:{panelId:panelId,status:status},
		success: function(data){
			if(data[0]){
				//alert("User Group access changed successfully");
				if(status)
					document.getElementById(panelId).style.display='block';
				else
					document.getElementById(panelId).style.display='none';
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
function updateQuickLinkStatus(checkBoxId){
	var funcLinkId = checkBoxId.substring(3);
	var status = false;
	if ($('#chk'+funcLinkId).is(":checked")){
	  status = true;
	}
	$.ajax({
		type: "POST",
		url: "updateUsrQuickLinkStatus",		
		dataType: 'json',
		data:{funcLinkId:funcLinkId,status:status},
		success: function(data){
			if(data[0]){
				//alert("User Group access changed successfully");
				if(status)
					document.getElementById(funcLinkId).style.display='block';
				else
					document.getElementById(funcLinkId).style.display='none';
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
function fetchNotifications(){
	$.ajax({
		type: "POST",
		url: "getCompanyAnnouncementList",		
		dataType: 'json',
		success: function(data){
			$('#compNotificationUL').empty();
			if(data.length > 0){
				for(var i=0;i<data.length;i++){
					$('#compNotificationUL').append("<li>"+data[i].title+"<a href='#l' id='Notification"+data[i].notificationrfnum+"' onclick=openNotification('"+data[i].type+"','"+data[i].notificationrfnum+"')><br>View more...</a></li>");
				}
			}else{
				
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:Error Occured");
		}
	});
}
function openNotification(type,id){
	if(type == "PDF_Document"){
		openCompNotofication(id);
	}
	if(type == "Popup"){
		alert("Page under construction.");
	}
}
function editProfilePicOwn(){
	$('#addProfilePicModal').modal('show');
}


//upload profile picture
function uploadProfilePicture(id){
	var fuData = document.getElementById(id);
    var FileUploadPath = fuData.value;
    if (FileUploadPath == ''){
    	alert("Please upload an image");
    	return false;
    }
    
	var form = $('#profilePicUploadForm')[0];
    var data = new FormData(form);
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "uploadUserProfilePicture",
        data: data,
        processData: false,
        contentType: false,
        async:false,
        success: function (data) {
        	alert("Image uploaded successfully.");
        	window.location.reload(true);
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}







function valProfilePicUpload(value,id) {
    var fuData = document.getElementById(id);
    var FileUploadPath = fuData.value;
    if (FileUploadPath == '') {
        alert("Please upload an image");
    } else {
        var Extension = FileUploadPath.substring(FileUploadPath.lastIndexOf('.') + 1).toLowerCase();
        if (Extension == "gif" || Extension == "png" || Extension == "bmp" || Extension == "jpeg" || Extension == "jpg") {
            if (fuData.files && fuData.files[0]) {
                var reader = new FileReader();

                reader.onload = function(e) {
                    $('#blah').attr('src', e.target.result);
                }
                reader.readAsDataURL(fuData.files[0]);
            }
        } 
        else {
            alert("Photo only allows file types of GIF, PNG, JPG, JPEG and BMP. ");

        }
    }
}




