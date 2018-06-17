var alphanumers = /^[a-zA-Z0-9]+$/;
function populateDropdownElement(url,id,elementName,param){
	$.ajax({
		type: "POST",
		url: url,
		dataType: 'json',
		data:{param:param},
		success: function(data){
			var select = document.getElementById(id);
			for(var i=select.options.length-1;i>=0;i--)
                select.remove(i);
			select.add(new Option(elementName,""));
			for (var i = 0; i < data.length; i++)				
				select.add(new Option(data[i].value,data[i].key));
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function prevTSData(){
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
    getDataFromTimeSheet1(value);
}
function nextTSData(){
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
    getDataFromTimeSheet1(value);
}
function deleteFTRow(r){
	var queryCount= $('#tab_logic1 tr').length - 1; 
	  var totalCells = 0;
	  for(var l = 0;l<=queryCount;l++){
		  totalCells = totalCells + (document.getElementById('tab_logic1').rows[l].cells.length);
	  }	
	  if(totalCells == "10"){
		  alert("Atleast one row mandatory");
		  return false;
	  }
	  $("#query"+r).html('');
	  calculateSumEachDay();
}
function addRowTS(){
	var queryCount= $('#tab_logic1 tr').length - 1; 
	  $('#query'+queryCount).html("<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><select class='form-control' name='taskMappingList["+queryCount+"].projectId' id='taskMappingList["+queryCount+"].projectId' onchange='getTaskListOnSelect(this.value,this.id)'><option value='--Select--'  selected='selected'>Select Project</option><td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><select class='form-control' name='taskMappingList["+queryCount+"].taskId' id='taskMappingList["+queryCount+"].taskId'><option value='--Select--'  selected='selected'>Select Task</option></select><div class='help-block with-errors'></div></div></td>" +
		  		"<td class='success'><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay1' id='taskMappingList["+queryCount+"].effortInHoursDay1' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day1' id='taskMappingList["+queryCount+"].day1'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day1B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day1Comment' id='taskMappingList["+queryCount+"].day1Comment'/></div></td>" +
					"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay2' id='taskMappingList["+queryCount+"].effortInHoursDay2' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day2' id='taskMappingList["+queryCount+"].day2'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day2B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day2Comment' id='taskMappingList["+queryCount+"].day2Comment'/></div></td>" +
							"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay3' id='taskMappingList["+queryCount+"].effortInHoursDay3' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day3' id='taskMappingList["+queryCount+"].day3'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day3B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day3Comment' id='taskMappingList["+queryCount+"].day3Comment'/></div></td>" +
									"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay4' id='taskMappingList["+queryCount+"].effortInHoursDay4' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day4' id='taskMappingList["+queryCount+"].day4'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day4B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day4Comment' id='taskMappingList["+queryCount+"].day4Comment'/></div></td>" +
											"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay5' id='taskMappingList["+queryCount+"].effortInHoursDay5' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day5' id='taskMappingList["+queryCount+"].day5'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day5B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day5Comment' id='taskMappingList["+queryCount+"].day5Comment'/></div></td>" +
													"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay6' id='taskMappingList["+queryCount+"].effortInHoursDay6' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day6' id='taskMappingList["+queryCount+"].day6'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day6B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day6Comment' id='taskMappingList["+queryCount+"].day6Comment'/></div></td>" +
													"<td class='success'><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay7' id='taskMappingList["+queryCount+"].effortInHoursDay7' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day7' id='taskMappingList["+queryCount+"].day7'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day7B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day7Comment' id='taskMappingList["+queryCount+"].day7Comment'/></div></td>"+		
														"<td><a href='#!' onclick=deleteFTRow('"+queryCount+"');><span class='glyphicon glyphicon-trash' style='color:#000;margin-top:7px;'></span></a></td>");
	  $('#tab_logic1').append('<tr id="query'+(queryCount+1)+'"></tr>');
		getProjectList("taskMappingList["+queryCount+"].projectId");
		queryCount++;
}
/*function deleteRowTS(){
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
}*/
function saveTS(){
	if($("input[id='weeklyDatePicker']").val() == ""){
		alert("Please select valid week.");
		return false;
	}
	var totalRow = $('#tab_logic1 tr').length - 1;
	var arrPid = [ ];
	var arrTaskId = [ ];
	for (var i = 0;i<totalRow; i++) {
		if($('#tab_logic1 tr:nth-child('+(i+1)+') td').length > 0){
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
				if($('#tab_logic1 tr:nth-child('+(i+1)+') td').length > 0){
					var tempDEffort = document.getElementById("taskMappingList["+ i +"].effortInHoursDay"+l).value;
					tempPerDayEffort = tempPerDayEffort + Number(tempDEffort);
				}	
			}
			if(tempPerDayEffort > 24){
				alert("Per day effort must not exceed 24 Hours");
				return false;
			}
		} 
	for (var i = 0;i<totalRow; i++) {
	 if($('#tab_logic1 tr:nth-child('+(i+1)+') td').length > 0){	
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
	var ret = confirm("Do you want to save?");
	if (ret == true) {
		    $.ajax({
	            type: 'POST',
	            url: "saveTimeSheetAction",
	            dataType : "json",
	            data : $('#regform').serialize(),
	            beforeSend: function() { $('#loaderGif').show(); },
	            complete: function() { $('#loaderGif').hide(); },
		        success : function(res) {
		        	var selectedWeek1 = $("#weeklyDatePicker").val();
					var value = new Date(selectedWeek1.substring(0, 10));
					value.setDate(value.getDate());
					value = formatDate(value);
				    getDataFromTimeSheet1(value);
				    getRecentTimesheetData();
				    alert(res);
		        }
		        });
	}
}
function submitTS(){
	if($("input[id='weeklyDatePicker']").val() == ""){
		alert("Please select valid week.");
		return false;
	}
	var totalRow = $('#tab_logic1 tr').length - 1;
	var arrPid = [ ];
	var arrTaskId = [ ];
	for (var i = 0;i<totalRow; i++) {
	 if($('#tab_logic1 tr:nth-child('+(i+1)+') td').length > 0){	
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
	if($('#tab_logic1 tr:nth-child('+(i+1)+') td').length > 0){	
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
	}
	 for(var l=1;l<8;l++){
		var tempPerDayEffort = Number("0");
		var rows1 = $('#tab_logic1 tr');
		for (var i = 0;i<rows1.length-1; i++) {
			if($('#tab_logic1 tr:nth-child('+(i+1)+') td').length > 0){
				var tempDEffort = document.getElementById("taskMappingList["+ i +"].effortInHoursDay"+l).value;
				tempPerDayEffort = tempPerDayEffort + Number(tempDEffort);
			}
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
		$.ajax({
            type: 'POST',
            url: "submitTimeSheetAction",
            dataType : "json",
            data : $('#regform').serialize(),
            beforeSend: function() { $('#loaderGif').show(); },
            complete: function() { $('#loaderGif').hide(); },
	        success : function(res) {
						var selectedWeek1 = $("#weeklyDatePicker").val();
						var value = new Date(selectedWeek1.substring(0, 10));
						value.setDate(value.getDate());
						value = formatDate(value);
					    getDataFromTimeSheet1(value);
					    getRecentTimesheetData();
					    alert(res);
	        	 }
	         });
	}
}
function viewFillTimesheetDiv(username){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("vieTimeShtDiv").style.display='block';
	document.getElementById('vieTimeShtDiv').setAttribute("style","min-height:550px");
	$("#vieTimeShtDiv").appendTo("#dataDiv");
	getRecentTimesheetData();
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
    
    getDataFromTimeSheet1(value);
	/*$.ajax({
		type: "POST",
		url: "home",
		dataType: 'json',
		success: function(data){
			//document.getElementById("dataDiv").innerHTML = "";
			var nodes = document.getElementById('dataDiv').childNodes;
			for(var i=0; i<nodes.length; i++) {
			    if (nodes[i].nodeName.toLowerCase() == 'div') {
			         nodes[i].style.display='none';
			     }
			}
			document.getElementById("vieTimeShtDiv").style.display='block';
			document.getElementById('vieTimeShtDiv').setAttribute("style","min-height:550px");
			$("#vieTimeShtDiv").appendTo("#dataDiv");
			
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});*/	
}
function getTaskListOnSelect(projectId,id){
	$.ajax({
		type: "POST",
		url: "getTaskList",
		dataType: 'json',
		data:{projectId:projectId},
		success: function(data){
			//document.getElementById(id.substring(0,19) + 'taskId').disabled = false;
			var select = document.getElementById(id.substring(0,19) + 'taskId');
			//alert(select);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Task','select'));
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].taskName,data[i].taskId));
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function getAllHRManager(id){
	$.ajax({
		type: "POST",
		url: "getAllHRManager",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(id);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select HR Manager','select'));
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
function getTaskList(projectId,id,taskId){
	//alert(id);
	$.ajax({
		type: "POST",
		url: "getTaskList",
		dataType: 'json',
		data:{projectId:projectId},
		success: function(data){
			var select = document.getElementById(id);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			//select.add(new Option('Select Task','select'));
			for (var i = 0; i < data.length; i++) {
				if(taskId == data[i].taskId ){
					select.add(new Option(data[i].taskName,data[i].taskId));
				}
			 }
			for (var i = 0; i < data.length; i++) {
		        if (data[i].taskId != taskId) {
		        	select.add(new Option(data[i].taskName,data[i].taskId));
		        }
		    }
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function getProjectList(id){
	$.ajax({
		type: "POST",
		url: "getProjectList",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(id);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Project','select'));
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].projectName,data[i].projectId));
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function getAllProjectListforReport(id){
	$.ajax({
		type: "POST",
		url: "getAllProjectList",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(id);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Project','select'));
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].projectName,data[i].projectId));
			    }
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function getAllProjectList(id){
	$.ajax({
		type: "POST",
		url: "getAllProjectList",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(id);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Project','select'));
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].projectName,data[i].projectId));
				if (select.options[i].value == data[i]) {
		        	select.options[i].selected = true;
		        }
			    }
			
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function getProjectList(elementId,projectId){
	$.ajax({
		type: "POST",
		url: "getProjectList",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(elementId);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
            if(projectId == undefined){
            	select.add(new Option('Select Project','select'));
            }
			for (var i = 0; i < data.length; i++) {	
					if(projectId == data[i].projectId ){
						select.add(new Option(data[i].projectName,data[i].projectId));
					}
			}	
			for (var i = 0; i < data.length; i++) {
		        if (projectId != data[i].projectId) {
		        		select.add(new Option(data[i].projectName,data[i].projectId));
		        }
		    }
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function getDataFromTimeSheet1(value){
	var firstDate = moment(value, "YYYY-MM-DD").day(0).format("YYYY-MM-DD");
    var secondDate = moment(value, "YYYY-MM-DD").day(1).format("YYYY-MM-DD");
    var thirdDate = moment(value, "YYYY-MM-DD").day(2).format("YYYY-MM-DD");
    var fourthDate = moment(value, "YYYY-MM-DD").day(3).format("YYYY-MM-DD");
    var fifthDate = moment(value, "YYYY-MM-DD").day(4).format("YYYY-MM-DD");
    var sixthDate = moment(value, "YYYY-MM-DD").day(5).format("YYYY-MM-DD");
    var lastDate =  moment(value, "YYYY-MM-DD").day(6).format("YYYY-MM-DD");
	$.ajax({
		type: "POST",
		url: "fetchCurrentWeekData",
		dataType: 'json',
		data: {firstDate :firstDate,secondDate:secondDate,thirdDate:thirdDate,fourthDate:fourthDate,fifthDate:fifthDate,sixthDate:sixthDate,lastDate:lastDate},
		success: function(data){
			if(data.length >0){
				$('#tab_logic1').children( 'tr:not(:first)' ).remove();
				var queryCount=0;
				for(var j=0;j<data.length;j++){
					
					if("true" == data[j].submitStatus){
						/*$('#query'+queryCount).html("<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><select class='form-control' name='taskMappingList["+queryCount+"].projectId' id='taskMappingList["+queryCount+"].projectId' onchange='getTaskListOnSelect(this.value,this.id)'><option value='--Select--'  selected='selected'>Select Project</option></td><td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><select class='form-control' name='taskMappingList["+queryCount+"].taskId' id='taskMappingList["+queryCount+"].taskId'><option value='--Select--'  selected='selected'>Select Task</option></select><div class='help-block with-errors'></div></div></td>" +
						  		"<td class='success'><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay1' id='taskMappingList["+queryCount+"].effortInHoursDay1' value='0' class='form-control'/><input type='hidden' name='taskMappingList["+queryCount+"].day1' id='taskMappingList["+queryCount+"].day1'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day1B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day1Comment' id='taskMappingList["+queryCount+"].day1Comment'/></div></td>" +
									"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay2' id='taskMappingList["+queryCount+"].effortInHoursDay2' value='0' class='form-control'/><input type='hidden' name='taskMappingList["+queryCount+"].day2' id='taskMappingList["+queryCount+"].day2'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day2B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day2Comment' id='taskMappingList["+queryCount+"].day2Comment'/></div></td>" +
											"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay3' id='taskMappingList["+queryCount+"].effortInHoursDay3' value='0' class='form-control'/><input type='hidden' name='taskMappingList["+queryCount+"].day3' id='taskMappingList["+queryCount+"].day3'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day3B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day3Comment' id='taskMappingList["+queryCount+"].day3Comment'/></div></td>" +
													"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay4' id='taskMappingList["+queryCount+"].effortInHoursDay4' value='0' class='form-control'/><input type='hidden' name='taskMappingList["+queryCount+"].day4' id='taskMappingList["+queryCount+"].day4'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day4B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day4Comment' id='taskMappingList["+queryCount+"].day4Comment'/></div></td>" +
															"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay5' id='taskMappingList["+queryCount+"].effortInHoursDay5' value='0' class='form-control'/><input type='hidden' name='taskMappingList["+queryCount+"].day5' id='taskMappingList["+queryCount+"].day5'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day5B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day5Comment' id='taskMappingList["+queryCount+"].day5Comment'/></div></td>" +
																	"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay6' id='taskMappingList["+queryCount+"].effortInHoursDay6' value='0' class='form-control'/><input type='hidden' name='taskMappingList["+queryCount+"].day6' id='taskMappingList["+queryCount+"].day6'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day6B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day6Comment' id='taskMappingList["+queryCount+"].day6Comment'/></div></td>" +
																			"<td class='success'><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay7' id='taskMappingList["+queryCount+"].effortInHoursDay7' value='0' class='form-control'/><input type='hidden' name='taskMappingList["+queryCount+"].day7' id='taskMappingList["+queryCount+"].day7'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day7B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day7Comment' id='taskMappingList["+queryCount+"].day7Comment'/></div></td>");*/
$('#query'+queryCount).html("<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' class='form-control' name='taskMappingList["+queryCount+"].projectId' id='taskMappingList["+queryCount+"].projectId'/ ></td>" +
		"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' class='form-control' name='taskMappingList["+queryCount+"].taskId' id='taskMappingList["+queryCount+"].taskId' /></div></td>" +
  		"<td class='success'><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay1' id='taskMappingList["+queryCount+"].effortInHoursDay1' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day1' id='taskMappingList["+queryCount+"].day1'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day1B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day1Comment' id='taskMappingList["+queryCount+"].day1Comment'/></div></td>" +
			"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay2' id='taskMappingList["+queryCount+"].effortInHoursDay2' value='0' class='form-control'/><input type='hidden' name='taskMappingList["+queryCount+"].day2' id='taskMappingList["+queryCount+"].day2'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day2B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day2Comment' id='taskMappingList["+queryCount+"].day2Comment'/></div></td>" +
					"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay3' id='taskMappingList["+queryCount+"].effortInHoursDay3' value='0' class='form-control'/><input type='hidden' name='taskMappingList["+queryCount+"].day3' id='taskMappingList["+queryCount+"].day3'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day3B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day3Comment' id='taskMappingList["+queryCount+"].day3Comment'/></div></td>" +
							"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay4' id='taskMappingList["+queryCount+"].effortInHoursDay4' value='0' class='form-control'/><input type='hidden' name='taskMappingList["+queryCount+"].day4' id='taskMappingList["+queryCount+"].day4'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day4B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day4Comment' id='taskMappingList["+queryCount+"].day4Comment'/></div></td>" +
									"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay5' id='taskMappingList["+queryCount+"].effortInHoursDay5' value='0' class='form-control'/><input type='hidden' name='taskMappingList["+queryCount+"].day5' id='taskMappingList["+queryCount+"].day5'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day5B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day5Comment' id='taskMappingList["+queryCount+"].day5Comment'/></div></td>" +
											"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay6' id='taskMappingList["+queryCount+"].effortInHoursDay6' value='0' class='form-control'/><input type='hidden' name='taskMappingList["+queryCount+"].day6' id='taskMappingList["+queryCount+"].day6'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day6B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day6Comment' id='taskMappingList["+queryCount+"].day6Comment'/></div></td>" +
													"<td class='success'><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay7' id='taskMappingList["+queryCount+"].effortInHoursDay7' value='0' class='form-control'/><input type='hidden' name='taskMappingList["+queryCount+"].day7' id='taskMappingList["+queryCount+"].day7'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day7B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day7Comment' id='taskMappingList["+queryCount+"].day7Comment'/></div></td>" +
															"<td class='text-center'><a href='#!' onclick=deleteFTRow('"+queryCount+"');><span class='glyphicon glyphicon-trash' style='color:#000;margin-top:7px;'></span></a></td>");
							if(data[j].day1 != undefined || data[j].day1 != null){
						  		$("input[id='taskMappingList["+ queryCount+"].effortInHoursDay1']").val(data[j].day1);
						  		$("input[id='taskMappingList["+ queryCount+"].day1Comment']").val(data[j].day1Comment);
						  	}
						  	$("input[id='taskMappingList["+ queryCount+"].effortInHoursDay1']").attr('disabled','disabled');
						  	document.getElementById("taskMappingList["+ queryCount+"].day1B").disabled = true;
							if(data[j].day2 != undefined || data[j].day2 != null){
								$("input[id='taskMappingList["+ queryCount+"].effortInHoursDay2']").val(data[j].day2);
								$("input[id='taskMappingList["+ queryCount+"].day2Comment']").val(data[j].day2Comment);
							}
							$("input[id='taskMappingList["+ queryCount+"].effortInHoursDay2']").attr('disabled','disabled');
							document.getElementById("taskMappingList["+ queryCount+"].day2B").disabled = true;
							if(data[j].day3 != undefined || data[j].day3 != null){
								$("input[id='taskMappingList["+ queryCount+"].effortInHoursDay3']").val(data[j].day3);
								$("input[id='taskMappingList["+ queryCount+"].day3Comment']").val(data[j].day3Comment);
							}
							$("input[id='taskMappingList["+ queryCount+"].effortInHoursDay3']").attr('disabled','disabled');
							document.getElementById("taskMappingList["+ queryCount+"].day3B").disabled = true;
							if(data[j].day4 != undefined || data[j].day4 != null){
								$("input[id='taskMappingList["+ queryCount+"].effortInHoursDay4']").val(data[j].day4);
								$("input[id='taskMappingList["+ queryCount+"].day4Comment']").val(data[j].day4Comment);
							}
							$("input[id='taskMappingList["+ queryCount+"].effortInHoursDay4']").attr('disabled','disabled');
							document.getElementById("taskMappingList["+ queryCount+"].day4B").disabled = true;
							if(data[j].day5 != undefined || data[j].day5 != null){
								$("input[id='taskMappingList["+ queryCount+"].effortInHoursDay5']").val(data[j].day5);
								$("input[id='taskMappingList["+ queryCount+"].day5Comment']").val(data[j].day5Comment);
							}
							$("input[id='taskMappingList["+ queryCount+"].effortInHoursDay5']").attr('disabled','disabled');
							document.getElementById("taskMappingList["+ queryCount+"].day5B").disabled = true;
							if(data[j].day6 != undefined || data[j].day6 != null){
								$("input[id='taskMappingList["+ queryCount+"].effortInHoursDay6']").val(data[j].day6);
								$("input[id='taskMappingList["+ queryCount+"].day6Comment']").val(data[j].day6Comment);
							}
							$("input[id='taskMappingList["+ queryCount+"].effortInHoursDay6']").attr('disabled','disabled');
							document.getElementById("taskMappingList["+ queryCount+"].day6B").disabled = true;
							
							if(data[j].day7 != undefined || data[j].day7 != null){
								$("input[id='taskMappingList["+ queryCount+"].effortInHoursDay7']").val(data[j].day7);
								$("input[id='taskMappingList["+ queryCount+"].day7Comment']").val(data[j].day7Comment);
							}
							$("input[id='taskMappingList["+ queryCount+"].effortInHoursDay7']").attr('disabled','disabled');
							document.getElementById("taskMappingList["+ queryCount+"].day7B").disabled = true;
							
						//	getProjectList('taskMappingList['+ queryCount +'].projectId',data[queryCount].projectId);
						//	getTaskList(data[queryCount].projectId,'taskMappingList['+ queryCount +'].taskId',data[queryCount].taskId);
							
							$(document.getElementById('taskMappingList['+ queryCount +'].projectId')).val(data[j].projectName);
							$(document.getElementById('taskMappingList['+ queryCount +'].taskId')).val(data[j].taskName);
							
							$(document.getElementById('taskMappingList['+ queryCount +'].projectId')).attr("disabled",true);
							$(document.getElementById('taskMappingList['+ queryCount +'].taskId')).attr("disabled",true);
							
							$('#tab_logic1').append('<tr id="query'+(queryCount+1)+'"></tr>');
							queryCount++;
							document.getElementById("timeButtonDiv").style.display='none';
					}else{
						$('#query'+queryCount).html("<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><select class='form-control' name='taskMappingList["+queryCount+"].projectId' id='taskMappingList["+queryCount+"].projectId' onchange='getTaskListOnSelect(this.value,this.id)'><option value='--Select--'  selected='selected'>Select Project</option></td><td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><select class='form-control' name='taskMappingList["+queryCount+"].taskId' id='taskMappingList["+queryCount+"].taskId'><option value='--Select--'  selected='selected'>Select Task</option></select><div class='help-block with-errors'></div></div></td>" +
						  		"<td class='success'><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay1' id='taskMappingList["+queryCount+"].effortInHoursDay1' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day1' id='taskMappingList["+queryCount+"].day1'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day1B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day1Comment' id='taskMappingList["+queryCount+"].day1Comment'/></div></td>" +
									"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay2' id='taskMappingList["+queryCount+"].effortInHoursDay2' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day2' id='taskMappingList["+queryCount+"].day2'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day2B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day2Comment' id='taskMappingList["+queryCount+"].day2Comment'/></div></td>" +
											"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay3' id='taskMappingList["+queryCount+"].effortInHoursDay3' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day3' id='taskMappingList["+queryCount+"].day3'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day3B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day3Comment' id='taskMappingList["+queryCount+"].day3Comment'/></div></td>" +
													"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay4' id='taskMappingList["+queryCount+"].effortInHoursDay4' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day4' id='taskMappingList["+queryCount+"].day4'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day4B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day4Comment' id='taskMappingList["+queryCount+"].day4Comment'/></div></td>" +
															"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay5' id='taskMappingList["+queryCount+"].effortInHoursDay5' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day5' id='taskMappingList["+queryCount+"].day5'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day5B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day5Comment' id='taskMappingList["+queryCount+"].day5Comment'/></div></td>" +
																	"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay6' id='taskMappingList["+queryCount+"].effortInHoursDay6' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day6' id='taskMappingList["+queryCount+"].day6'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day6B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day6Comment' id='taskMappingList["+queryCount+"].day6Comment'/></div></td>" +
																			"<td class='success'><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay7' id='taskMappingList["+queryCount+"].effortInHoursDay7' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day7' id='taskMappingList["+queryCount+"].day7'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day7B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day7Comment' id='taskMappingList["+queryCount+"].day7Comment'/></div></td>" +
																			     "<td><a href='#!' onclick=deleteFTRow('"+queryCount+"');><span class='glyphicon glyphicon-trash' style='color:#000;margin-top:7px;'></span></a></td>");
						  	if(data[j].day1 != undefined || data[j].day1 != null){
						  		$("input[id='taskMappingList["+ queryCount+"].effortInHoursDay1']").val(data[j].day1);
						  		$("input[id='taskMappingList["+ queryCount+"].day1Comment']").val(data[j].day1Comment);
						  	}
							if(data[j].day2 != undefined || data[j].day2 != null){
								$("input[id='taskMappingList["+ queryCount+"].effortInHoursDay2']").val(data[j].day2);
								$("input[id='taskMappingList["+ queryCount+"].day2Comment']").val(data[j].day2Comment);
							}	
							if(data[j].day3 != undefined || data[j].day3 != null){
								$("input[id='taskMappingList["+ queryCount+"].effortInHoursDay3']").val(data[j].day3);
								$("input[id='taskMappingList["+ queryCount+"].day3Comment']").val(data[j].day3Comment);							
					        }
							if(data[j].day4 != undefined || data[j].day4 != null){
								$("input[id='taskMappingList["+ queryCount+"].effortInHoursDay4']").val(data[j].day4);
								$("input[id='taskMappingList["+ queryCount+"].day4Comment']").val(data[j].day4Comment);	
							}	
							if(data[j].day5 != undefined || data[j].day5 != null){
								$("input[id='taskMappingList["+ queryCount+"].effortInHoursDay5']").val(data[j].day5);
								$("input[id='taskMappingList["+ queryCount+"].day5Comment']").val(data[j].day5Comment);	
							}	
							if(data[j].day6 != undefined || data[j].day6 != null){
								$("input[id='taskMappingList["+ queryCount+"].effortInHoursDay6']").val(data[j].day6);
								$("input[id='taskMappingList["+ queryCount+"].day6Comment']").val(data[j].day6Comment);	
							}	
							if(data[j].day7 != undefined || data[j].day7 != null){
								$("input[id='taskMappingList["+ queryCount+"].effortInHoursDay7']").val(data[j].day7);
								$("input[id='taskMappingList["+ queryCount+"].day7Comment']").val(data[j].day7Comment);	
							}	
							
							getProjectList('taskMappingList['+ queryCount +'].projectId',data[queryCount].projectId);
							
							getTaskList(data[queryCount].projectId,'taskMappingList['+ queryCount +'].taskId',data[queryCount].taskId);
							
							$('#tab_logic1').append('<tr id="query'+(queryCount+1)+'"></tr>');
							queryCount++;
							document.getElementById("timeButtonDiv").style.display='inline';
					}
					$('#adminRemark').val(data[0].adminRemark);
				}
		 }else{
			 $('#tab_logic1').children( 'tr:not(:first)' ).remove();
			 var queryCount= 0;
  $('#query'+queryCount).html("<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><select class='form-control' name='taskMappingList["+queryCount+"].projectId' id='taskMappingList["+queryCount+"].projectId' onchange='getTaskListOnSelect(this.value,this.id)'><option value='--Select--'  selected='selected'>Select Project</option><td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><select class='form-control' name='taskMappingList["+queryCount+"].taskId' id='taskMappingList["+queryCount+"].taskId'><option value='--Select--'  selected='selected'>Select Task</option></select><div class='help-block with-errors'></div></div></td>" +
  		"<td class='success'><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay1' id='taskMappingList["+queryCount+"].effortInHoursDay1' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day1' id='taskMappingList["+queryCount+"].day1'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day1B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day1Comment' id='taskMappingList["+queryCount+"].day1Comment'/></div></td>" +
			"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay2' id='taskMappingList["+queryCount+"].effortInHoursDay2' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day2' id='taskMappingList["+queryCount+"].day2'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day2B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day2Comment' id='taskMappingList["+queryCount+"].day2Comment'/></div></td>" +
					"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay3' id='taskMappingList["+queryCount+"].effortInHoursDay3' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day3' id='taskMappingList["+queryCount+"].day3'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day3B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day3Comment' id='taskMappingList["+queryCount+"].day3Comment'/></div></td>" +
							"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay4' id='taskMappingList["+queryCount+"].effortInHoursDay4' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day4' id='taskMappingList["+queryCount+"].day4'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day4B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day4Comment' id='taskMappingList["+queryCount+"].day4Comment'/></div></td>" +
									"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay5' id='taskMappingList["+queryCount+"].effortInHoursDay5' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day5' id='taskMappingList["+queryCount+"].day5'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day5B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day5Comment' id='taskMappingList["+queryCount+"].day5Comment'/></div></td>" +
											"<td><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay6' id='taskMappingList["+queryCount+"].effortInHoursDay6' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day6' id='taskMappingList["+queryCount+"].day6'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day6B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day6Comment' id='taskMappingList["+queryCount+"].day6Comment'/></div></td>" +
													"<td class='success'><div class='col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group'><input type='text' name='taskMappingList["+queryCount+"].effortInHoursDay7' id='taskMappingList["+queryCount+"].effortInHoursDay7' value='0' class='form-control' onkeyup='calculateSumEachDay();return false;'/><input type='hidden' name='taskMappingList["+queryCount+"].day7' id='taskMappingList["+queryCount+"].day7'/><span class='input-group-addon' style='padding: 0px;background:white;'><button type='button' class='glyphicon glyphicon-comment' style='border: none;background:white;' id='taskMappingList["+queryCount+"].day7B' onclick='addCmt(this.id)'></button></span><input type='hidden' name='taskMappingList["+queryCount+"].day7Comment' id='taskMappingList["+queryCount+"].day7Comment'/></div></td>" +
															"<td><a href='#!' onclick=deleteFTRow('"+queryCount+"');><span class='glyphicon glyphicon-trash' style='color:#000;margin-top:7px;'></span></a></td>");
  				getProjectList("taskMappingList["+queryCount+"].projectId");
			  	
  				$('#tab_logic1').append('<tr id="query'+(queryCount+1)+'"></tr>');
				queryCount++;
				document.getElementById("timeButtonDiv").style.display='inline';
				$('#adminRemark').val("Approver Remark");
		 }
			calculateSumEachDay();	
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function getReportParameter(){
	$.ajax({
		type: "POST",
		url: "renderreportsparam",
		dataType: 'json',
		success: function(data){
			//document.getElementById("dataDiv").innerHTML = "";
			var nodes = document.getElementById('dataDiv').childNodes;
			for(var i=0; i<nodes.length; i++) {
			    if (nodes[i].nodeName.toLowerCase() == 'div') {
			         nodes[i].style.display='none';
			     }
			}
			document.getElementById("vieReportDiv").style.display='block';
			document.getElementById('vieReportDiv').setAttribute("style","min-height:550px");
			$("#vieReportDiv").appendTo("#dataDiv");
			getAllProjectListforReport('projectList');
			getAllUserForReport(false,"usrListInPrjct",'projectList');
			getAllUserForReport(false,"rmListInReport",'');
			
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});	
}
function populateReportParamForDailyReport(flag,childElemId,id){
	getAllUserForReport(flag,childElemId,id);
	var projectId = document.getElementById(id).value;
	$.ajax({
		type: "POST",
		url: "getTaskList",
		dataType: 'json',
		data:{projectId:projectId},
		success: function(data){
			var select = document.getElementById('taskListForReport');
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Task','select'));
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].taskName,data[i].taskId));
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function getReport(){
	var startDate = document.getElementById('from-datepicker').value;
	var endDate = document.getElementById('to-datepicker').value;
	var projectId = document.getElementById('projectList').value;
	var taskId = document.getElementById('taskListForReport').value;
	var username = document.getElementById('usrListInPrjct').value;
	var rmname = document.getElementById('rmListInReport').value;
	var status = document.getElementById('statusListforReport').value;
	if(startDate == undefined || startDate == null || startDate == ""){
		   alert("Please select start date");
		   return false;
	}	
	if(endDate == undefined || endDate == null || endDate == ""){
		   alert("Please select end date");
		   return false;
	}	
	document.getElementById("loaderGif").style.display='block';
	$.ajax({
		type: "POST",
		url: "generateReport",
		dataType: 'json',
		data: {projectId :projectId,startDate:startDate,endDate:endDate,username:username,taskId:taskId,rmname:rmname,status:status},
		success: function(result){
			document.getElementById("loaderGif").style.display='none';
			if(result.length > 0){
				document.getElementById("tsDailyRprtId").style.display='block';
				$('#dailyUserTSItemTbl td').remove();
				for (var i = 0; i < result.length; i++) {
					var row = $("<tr class='info' />");
					 	$("#tab_logic_dailyUserItemApp").append(row); 
					 	row.append($("<td class='EWTableElements'>"+(i+1)+"</td>"));
					 	row.append($("<td class='EWTableElements'>"+result[i].date+"</td>"));
					 	row.append($("<td class='EWTableElements'>"+result[i].fullname+"</td>"));
					 	row.append($("<td class='EWTableElements'>"+result[i].projectname+"</td>"));
					 	row.append($("<td class='EWTableElements'>"+result[i].taskname+"</td>"));
					 	row.append($("<td class='EWTableElements'>"+result[i].effort+"</td>"));
					 	row.append($("<td class='EWTableElements'>"+result[i].comment+"</td>"));
					 	row.append($("<td class='EWTableElements nowrap'>"+(result[i].submittedon).substring(0,10)+"</td>"));
					 	row.append($("<td class='EWTableElements nowrap'>"+(result[i].lastmodifiedon).substring(0,10)+"</td>"));
					 	row.append($("<td class='EWTableElements'>"+result[i].lasmodifiedby+"</td>"));
					 	row.append($("<td class='EWTableElements'>"+result[i].rmname+"</td>"));
					 	row.append($("<td class='EWTableElements'>"+result[i].status+"</td>"));
					 	
					 	row.append($("<td class='EWTableElements'>"+result[i].leaveType+"</td>"));
					 	row.append($("<td class='EWTableElements'>"+result[i].leaveFlag+"</td>"));
					 	row.append($("<td class='EWTableElements'>"+result[i].leavestatus+"</td>"));
					 	row.append($("<td class='EWTableElements nowrap'>"+result[i].appliedon+"</td>"));
					 	row.append($("<td class='EWTableElements nowrap'>"+result[i].approvedon+"</td>"));
					 	row.append($("<td class='EWTableElements'>"+result[i].approvedby+"</td>"));
					 	row.append($("<td class='EWTableElements'>"+result[i].purpose+"</td>"));
				    }
			}
			/*document.getElementById("downloadReportDiv").style.display='block';
			$('#fileNameReportTime').html(new Date($.now()));
			$('#fileNameReport').html(data[0].FileName);*/
		},
		error: function(jqXHR, textStatus)
		{
			document.getElementById("downloadReportDiv").style.display='none';	
			alert("ERROR:"+textStatus);
		}
	});
}
function isInteger(input){
	 var RE = /^-{0,1}\d*\.{0,1}\d+$/;
	 var flag1 = RE.test(input);
	 var flag2 = input <= 24;
	 var flag3 = input >= 0;
	 var flag4 = input.length<5;
	 if(flag1 && flag2 && flag3 && flag4 && (input != '0.0') && (input != '-0.0') && (input != '-000') && (input != '000')){}else{return false;}
	 if( input.includes(".")){
		 var lengthAfter = (input.substr(input.indexOf(".") + 1)).length;
		 if(lengthAfter != 1){
			 return false;
		 }else{
			 if(input.substr(input.indexOf(".") + 1) != '5')
				 return false;
		 }
	 }else{
		 if (isNaN(input) || (input === 0)){
			 return false;
		 }
	 }
	 return true;
}
function userMngtFunc(userStatus){
	//document.getElementById("dataDiv").innerHTML = "";
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("addUserDiv").style.display='block';
	document.getElementById('addUserDiv').setAttribute("style","min-height:550px");
	$("#addUserDiv").appendTo("#dataDiv");
	addUserFilterFunc('Active','');
}
function filterAnyTableByInput(input,id,tdNum) {
	  // Declare variables 
	  var filter, table, tr, td, i;
	  filter = input.toUpperCase();
	  var filterArr = filter.split(',');
	  table = document.getElementById(id);
	  tr = table.getElementsByTagName("tr");
	  //alert(table +"  "+tr);
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[tdNum];
	    if (td) {
	    //if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
	    var displayCount = 0;	
		  for(var j=0;j<filterArr.length;j++){
		      if (td.innerHTML.toUpperCase().indexOf(filterArr[j]) > -1) {	  
		        tr[i].style.display = "";
		        displayCount = displayCount + 1;
		      } else {
		       if(displayCount == 0)	  
		    	   tr[i].style.display = "none";
		      }
	       }
	    } 
	  }
}
function addUserFilterFunc(filterVar,filterName){
	document.getElementById("loaderGif").style.display='block';
	$.ajax({
		type: "POST",
		url: "getPendingUserList",
		data:{filterVar:filterVar,filterName:filterName},
		dataType: 'json',
		success: function(result){
			document.getElementById("loaderGif").style.display='none';
			if(result.length >0){
			document.getElementById("searchResultNullPass1").style.display='none';	
			document.getElementById("pendingUserItemDiv").style.display='block';
			document.getElementById('pendingUserItemDiv').setAttribute("style","min-height:550px");
			$("#pendingUserItemDiv").appendTo("#addUserDiv");
			$('#pendingUserItemTbl td').remove();
			for (var i = 0; i < result.length; i++) {
				var row = "";
							if(i%2 == 0)
								row = $("<tr class='info' />");
							else
								row = $("<tr class='info' />");														
				 	$("#tab_logic_pendingUserItemApp").append(row); 
				 	row.append($("<td class='EWTableElements'>"+(i+1)+"</td>"));
				 	row.append($("<td class='EWTableElements'><a href='#!' style='color:#000;' title='Edit User Info' onclick=editUserProfile('"+result[i].username+"')><u>"+result[i].firstname+ " " +result[i].lastname+"</a></u></td>"));
				 	row.append($("<td class='EWTableElements'>"+result[i].primaryemail+"</td>"));
				 	row.append($("<td nowrap class='EWTableElements'><select class='form-control' id='updateRole"+i+"' style='display:inline;width:90%;height:20px;padding:0px;'><option value='"+ result[i].role +"'>"+result[i].role+"</option></select><a href='#!' onclick=getRoleType('updateRole"+i+"');return false;><span class='glyphicon glyphicon-pencil'></span></a></td>"));
				 	row.append($("<td nowrap class='EWTableElements'><select class='form-control' id='updateGrp"+i+"' style='display:inline;width:90%;height:20px;padding:0px;'><option value='"+ result[i].usergroup +"'>"+result[i].usergroup+"</option></select><a href='#!' onclick=groupListForUserModification('updateGrp"+i+"');><span class='glyphicon glyphicon-pencil'></span></a></td>"));
				 	row.append($("<td nowrap class='EWTableElements'><select class='form-control' id='updateSts"+i+"' style='display:inline;width:90%;height:20px;padding:0px;'><option value='"+ result[i].userstatus +"'>"+result[i].userstatus+"</option></select><a href='#!' onclick=getStsType('updateSts"+i+"');><span class='glyphicon glyphicon-pencil'></span></a></td>"));
				 	/*row.append($("<td nowrap class='EWTableElements'><select class='form-control' id='updateRpt"+i+"' style='display:inline;width:90%;height:20px;padding:0px;'><option value='"+ result[i].reporteeId +"'>"+result[i].reporteeName+"</option></select><a href='#!' onclick=getAllReportee('updateRpt"+i+"');><span class='caret caret-down'></span></a></td>"));
				 	row.append($("<td nowrap class='EWTableElements'><select class='form-control' id='updateHRRpt"+i+"' style='display:inline;width:90%;height:20px;padding:0px;'><option value='"+ result[i].reporteeHRId +"'>"+result[i].reporteeHRName+"</option></select><a href='#!' onclick=getAllHRManager('updateHRRpt"+i+"');><span class='caret caret-down'></span></a></td>"));*/
				 	/*row.append($("<td class='EWTableElements'>"+result[i].role+"</td>"));
				 	row.append($("<td class='EWTableElements'>"+result[i].usergroup+"</td>"));
				 	row.append($("<td class='EWTableElements'>"+result[i].userstatus+"</td>"));*/
				 	row.append($("<td class='EWTableElements'>"+result[i].reporteeName+"<input type='hidden' id='updateRpt"+i+"' value='"+result[i].reporteeId+"'/></td>"));
				 	row.append($("<td class='EWTableElements'>"+result[i].reporteeHRName+"<input type='hidden' id='updateHRRpt"+i+"' value='"+result[i].reporteeHRId+"'/></td>"));
				 	if(globalProxyLoginAccess){
					 	row.append($("<td nowrap class='EWTableElements text-center'>" +
					 			"<button class='btn btn-success btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Save Detail' onclick=approveRejectUser('"+result[i].username+"','updateRole"+i+"','updateGrp"+i+"','updateSts"+i+"','updateRpt"+i+"','updateHRRpt"+i+"');><i class='glyphicon glyphicon-circle-arrow-up'></i></button>" +
					 			"<button class='btn btn-info btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Edit Access Group' onclick=viewAccessGroup('"+result[i].username+"');><i class='glyphicon glyphicon-lock'></i></button>" +
					 			"<button class='btn btn-danger btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Login as "+ result[i].firstname+ " " +result[i].lastname +"' onclick=loginAsUser('"+result[i].username+"','"+result[i].primaryemail+"');><i class='glyphicon glyphicon-user'></i></button>" +
					 			"</td>"));
					}else{
						row.append($("<td nowrap class='EWTableElements text-center'>" +
								"<button class='btn btn-success btn-circle' style='margin:2px;border-radius:35px;height:35px;padding-top:0px;' title='Save Detail' onclick=approveRejectUser('"+result[i].username+"','updateRole"+i+"','updateGrp"+i+"','updateSts"+i+"','updateRpt"+i+"','updateHRRpt"+i+"');><i class='glyphicon glyphicon-circle-arrow-up'></i></button>" +
								"</td>"));
					}
			    }
			}else{
				alert("No result found in database");
				document.getElementById("searchResultNullPass1").style.display='block';	
				document.getElementById("pendingUserItemDiv").style.display='none';
			}
			$("#userFilter option:contains(" + filterVar + ")").attr('selected', 'selected');
			//$("#userFilter").val(filterVar).change();
		},
		error: function(jqXHR, textStatus)
		{
			document.getElementById("loaderGif").style.display='none';
			alert("ERROR:"+textStatus);
		}
	});
}
function approveTimesheetFunc(filterStatus){
	//document.getElementById("dataDiv").innerHTML = "";
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewApproveTimeDiv").style.display='block';
	document.getElementById('viewApproveTimeDiv').setAttribute("style","min-height:550px");
	$("#viewApproveTimeDiv").appendTo("#dataDiv");
	fetchByParam(filterStatus,'1');
}
function getUserListUnderUser(loggedInUser){
	$.ajax({
		type: "POST",
		url: "getUserListUnderUser",
		dataType: 'json',
		data:{loggedInUser:loggedInUser},
		success: function(data){
			var select = document.getElementById('userListUnder');
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select User','select'));
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
/*function getAllUserList(){
	$.ajax({
		type: "POST",
		url: "getUserList",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById('userList');
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select User','select'));
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].fullname,data[i].username));
			    }
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}*/
function goToPage(elementId){
	var filterVar = document.getElementById("timesheetFilter").value;
	if(elementId == "firstButton"){
		document.getElementById('nextButton').title = '2';
	}
	if(elementId == "prevButton"){
		document.getElementById('nextButton').title = Number( $("#nextButton").attr("title") ) - 1;
	}
	if(elementId == "nextButton"){
		document.getElementById('prevButton').title = Number( $("#prevButton").attr("title") ) + 1;
	}
	if(elementId == "lastButton"){
		document.getElementById('nextButton').title = Number( $("#lastButton").attr("title") ) - 1;
	}
	fetchByParam(filterVar,$("#"+elementId).attr("title"));
}
function fetchByParam(filterVar,pageno){
	if(filterVar != "")
		$("#timesheetFilter").val(filterVar).change();
	filterVar = document.getElementById("timesheetFilter").value;
	var userId = document.getElementById("tmshtFilterByName").value;
	var startDate = document.getElementById("timesheetFilterStartDate").value;
	var endDate = document.getElementById("timesheetFilterEndDate").value;
	var batchSize = document.getElementById("timesheetFilterPerPageRecord").value;
	getTimesheetFilteredData(filterVar,pageno,userId,startDate,endDate,batchSize);
}
function getTimesheetFilteredData(filterVar,pageno,userId,startDate,endDate,batchSize){
	var allReporteeFlag =  globalloggedinuser;
	if(globalGroup == 'Admin'){
		var myRadio = $('input[name=allUserPendingTS]');
		var checkedRadio = myRadio.filter(':checked').val();
		//alert(globalloggedinuser);
		if(checkedRadio == "All")
			allReporteeFlag = "";
		else
			allReporteeFlag = globalloggedinuser;
	}
		startDate = moment(startDate, "YYYY-MM-DD").day(0).format("YYYY-MM-DD"); 
		endDate = moment(endDate, "YYYY-MM-DD").day(6).format("YYYY-MM-DD");
	document.getElementById("loaderGif").style.display='block';
	$.ajax({
		type: "POST",
		url: "getTimesheetData",
		dataType: 'json',
		data: {userId:userId,startDate:startDate,endDate:endDate,filterVar:filterVar,allReporteeFlag:allReporteeFlag,batchSize:batchSize,pageno:pageno},
		success: function(result){
			document.getElementById("loaderGif").style.display='none';
			if(result.length >0){
			$("#noDataAvailableId").html("");	
			/*document.getElementById("timesheetStatusFilter").style.display='block';	*/
			document.getElementById("searchResultNullPass1").style.display='none';	
			document.getElementById("userDataItemDiv").style.display='block';
			document.getElementById('userDataItemDiv').setAttribute("style","min-height:550px");
			document.getElementById('firstButton').title = '1';
			document.getElementById('lastButton').title = result[0].totalcount;
			if(result[0].totalcount == pageno){
				$('#lastButton').prop('disabled', true);
				$('#nextButton').prop('disabled', true);
				$('#lastButton').css('background-color','grey');
				$('#nextButton').css('background-color','grey');
				$('#prevButton').css('background-color','#5bc0de');
				$('#firstButton').css('background-color','#5bc0de');
				$('#prevButton').prop('disabled', false);
				$('#firstButton').prop('disabled', false);
				document.getElementById('prevButton').title = result[0].totalcount - 1;
				document.getElementById('nextButton').title = "";
			}else{
				if(pageno == 1 && result[0].totalcount > 1){
					$('#lastButton').prop('disabled', false);
					$('#nextButton').prop('disabled', false);
					$('#prevButton').css('background-color','grey');
					$('#firstButton').css('background-color','grey');
					$('#lastButton').css('background-color','#5bc0de');
					$('#nextButton').css('background-color','#5bc0de');
					document.getElementById('nextButton').title = "2";
					$('#prevButton').prop('disabled', true);
					$('#firstButton').prop('disabled', true);
					document.getElementById('prevButton').title = "";
					document.getElementById('firstButton').title = "";
				}else{
					$('#prevButton').prop('disabled', false);
					$('#firstButton').prop('disabled', false);
					$('#lastButton').prop('disabled', false);
					$('#nextButton').prop('disabled', false);
					$('#lastButton').css('background-color','#5bc0de');
					$('#nextButton').css('background-color','#5bc0de');
					$('#prevButton').css('background-color','#5bc0de');
					$('#firstButton').css('background-color','#5bc0de');
					document.getElementById('nextButton').title = Number(pageno) + 1;
					document.getElementById('prevButton').title = Number(pageno) - 1;
				}
			}	
			$("#userDataItemDiv").appendTo("#viewApproveTimeDiv");
			$('#userItemTbl td').remove();
			for (var i = 0; i < result.length; i++) {
				var row = "";
							if(i%2 == 0)
								row = $("<tr class='info' />");
							else
								row = $("<tr class='' />");														
							$("#tab_logic_userItemApp").append(row); 
							/*row.append($("<td class='EWTableElements'>" + (i+1) + "</td>"));*/
							row.append($("<td class='EWTableElements'><input type='checkbox' id='timeCheckbox"+i+"' value='"+ result[i].weekDates +"$"+ result[i].username +"'></td>"));
							row.append($("<td class='EWTableElements'>"+result[i].usercode+"</td>"));
							row.append($("<td class='EWTableElements'>"+result[i].userfName+"</td>"));
							row.append($("<td class='EWTableElements'>"+result[i].rmManagerName+"</td>"));
							row.append($("<td class='EWTableElements'>"+result[i].usereffort+"</td>"));
							row.append($("<td class='EWTableElements'>Sun, "+result[i].weekDates.substring(8, 10)+"/"+result[i].weekDates.substring(5, 7)+" - " +result[i].weekDates.substring(19, 21)+"/"+result[i].weekDates.substring(16, 18) +", Sat</td>"));
							if(result[i].status == 'Approved')
								row.append($("<td class='EWTableElements' style='color:green;'>"+result[i].status+"</td>"));
							if(result[i].status == 'Rejected')
								row.append($("<td class='EWTableElements' style='color:red;'>"+result[i].status+"</td>"));
							if(result[i].status == 'Pending')
								row.append($("<td class='EWTableElements' style='color:blue;'>"+result[i].status+"</td>"));
							if(result[i].status == 'Saved')
								row.append($("<td class='EWTableElements' style='color:#800000;'>"+result[i].status+"</td>"));
							
							if(result[i].status == 'Pending' || globalGroup == 'SuperAdmin' || globalGroup == 'Admin'){
								row.append($("<td class='EWTableElements text-center'>" +
										"<button type='button' class='btn btn-info btn-sm' onclick=viewTimeData('"+result[i].username+"','"+result[i].weekDates+"');>View</button>" +
										"<button type='button' class='btn btn-success btn-sm' id='btnAprv' style='margin-left:5px;' onclick=approveRejectTime('"+result[i].username+"','"+result[i].weekDates+"',true);>Approve</button>" +
												"<button type='button' class='btn btn-danger btn-sm' id='btnRjt' style='margin-left:5px;' onclick=approveRejectTime('"+result[i].username+"','"+result[i].weekDates+"',false);>Reject</button></td>"));
							}else{
								row.append($("<td class='EWTableElements text-center'><button type='button' class='btn btn-info btn-sm' onclick=viewTimeData('"+result[i].username+"','"+result[i].weekDates+"');>View</button>" +
										"<button type='button' class='btn btn-success btn-sm' style='margin-left:5px;' disabled onclick=approveRejectTime('"+result[i].username+"','"+result[i].weekDates+"',true);>Approve</button>" +
												"<button type='button' class='btn btn-danger btn-sm' style='margin-left:5px;' disabled onclick=approveRejectTime('"+result[i].username+"','"+result[i].weekDates+"',false);>Reject</button></td>"));
							}
							row.append($("<td class='EWTableElements' style='display:none;'>"+result[i].rmManagerName+"</td>"));
					   }
			}else{
				$("#noDataAvailableId").html("No data found in database!!");
			   document.getElementById("userDataItemDiv").style.display='none';
			   //document.getElementById("searchResultNullPass1").style.display='block';
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function viewTimeData(username,value){
	
	 var strtDate = moment(value, "YYYY-MM-DD").day(0).format("YYYY-MM-DD"); 
	 var endDate = moment(value, "YYYY-MM-DD").day(6).format("YYYY-MM-DD");
	
	 var firstDate = moment(value, "YYYY-MM-DD").day(0).format("YYYY-MM-DD");
     var secondDate = moment(value, "YYYY-MM-DD").day(1).format("YYYY-MM-DD");
     var thirdDate = moment(value, "YYYY-MM-DD").day(2).format("YYYY-MM-DD");
     var fourthDate = moment(value, "YYYY-MM-DD").day(3).format("YYYY-MM-DD");
     var fifthDate = moment(value, "YYYY-MM-DD").day(4).format("YYYY-MM-DD");
     var sixthDate = moment(value, "YYYY-MM-DD").day(5).format("YYYY-MM-DD");
     var lastDate =  moment(value, "YYYY-MM-DD").day(6).format("YYYY-MM-DD");
     var currWeek = strtDate.substring(8,strtDate.length)+"/"+strtDate.substring(5,7)+ " to "+ endDate.substring(8,endDate.length) +"/"+endDate.substring(5,7); 
	$('#viewDModal').modal('show');
	$.ajax({
		type: "POST",
		url: "fetchTimeDataBasedPrjctUser",
		dataType: 'json',
		data: {strtDate:strtDate,endDate:endDate,username:username,secondDate:secondDate,thirdDate:thirdDate,fourthDate:fourthDate,fifthDate:fifthDate,sixthDate:sixthDate},
		success: function(result){
			  $("#weekDay11").text('Sun ' +firstDate.substring(8, 10));
		      $("#weekDay22").text('Mon ' +secondDate.substring(8, 10));
		      $("#weekDay33").text('Tue ' +thirdDate.substring(8, 10));
		      $("#weekDay44").text('Wed ' +fourthDate.substring(8, 10));
		      $("#weekDay55").text('Thu ' +fifthDate.substring(8, 10));
		      $("#weekDay66").text('Fri ' +sixthDate.substring(8, 10));
		      $("#weekDay77").text('Sat ' +lastDate.substring(8, 10));
			/*$('#prjctName').html(result[0].prjctName);*/
			$('#userName11').html(result[0].userfName + "("+ result[0].userName +")");
			$('#statusWeek').html(currWeek+" ( "+result[0].statusWeek+" ) ");
			//var rUser = (result[0].remarkUser).replace(";", "<br />");
			//var rUser = replaceAll(result[0].remarkUser,";", "");
			//rUser = (result[0].remarkUser).replace(/abc/g, '');
			//$('#usrRemark').html(rUser);
			$('#usrRemark').html(result[0].remarkUser);
			$('#approverRemark11').val(result[0].approverRemark);
			$('#frstDayDate11').val(firstDate);
			$('#lastDayDate11').val(lastDate);
			$('#userName12').val(result[0].userName);
			$('#userPrjctEffortTbl td').remove();
			for (var i = 0; i < result.length; i++) {
					var row = "";
						row = $("<tr class='' />");														

				 	$("#tab_logic_userPrjctEffortApp").append(row); 
				 	row.append($("<td class='EWTableElements'>"+result[i].prjctName+"</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].taskname+"</td>"));
					
					if(result[i].day1 != undefined || result[i].day1 != null)
						row.append($("<td class='EWTableElements success'>"+result[i].day1+"</td>"));
					else
						row.append($("<td class='EWTableElements success'>0</td>"));
					if(result[i].day2 != undefined || result[i].day2 != null)
						row.append($("<td class='EWTableElements'>"+result[i].day2+"</td>"));
					else
						row.append($("<td class='EWTableElements'>0</td>"));
					if(result[i].day3 != undefined || result[i].day3 != null)
						row.append($("<td class='EWTableElements'>"+result[i].day3+"</td>"));
					else
						row.append($("<td class='EWTableElements'>0</td>"));
					if(result[i].day4 != undefined || result[i].day4 != null)
						row.append($("<td class='EWTableElements'>"+result[i].day4+"</td>"));
					else
						row.append($("<td class='EWTableElements'>0</td>"));
					if(result[i].day5 != undefined || result[i].day5 != null)
						row.append($("<td class='EWTableElements'>"+result[i].day5+"</td>"));
					else
						row.append($("<td class='EWTableElements'>0</td>"));
					if(result[i].day6 != undefined || result[i].day6 != null)
						row.append($("<td class='EWTableElements'>"+result[i].day6+"</td>"));
					else
						row.append($("<td class='EWTableElements'>0</td>"));
					if(result[i].day7 != undefined || result[i].day7 != null)
						row.append($("<td class='EWTableElements success'>"+result[i].day7+"</td>"));
					else
						row.append($("<td class='EWTableElements success'>0</td>"));
			    }
			
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function approveRejectTime(username,weekDates,result){
	document.getElementById("btnAprv").disabled = true;
	document.getElementById("btnRjt").disabled = true;
	var appRmrk = document.getElementById('approverRemark11').value;
	var startDate = moment(weekDates, "YYYY-MM-DD").day(0).format("YYYY-MM-DD");
	var endDate = 	moment(weekDates, "YYYY-MM-DD").day(6).format("YYYY-MM-DD");
	var status = document.getElementById("timesheetFilter").value;
	document.getElementById("loaderGif").style.display='block';
	$.ajax({
		type: "POST",
		url: "updateTimesheetStatus",
		dataType: 'json',
		data:{username:username,startDate:startDate,endDate:endDate,result:result,appRmrk:appRmrk},
		success: function(data){
			document.getElementById("loaderGif").style.display='none';
			fetchByParam(status,'1');
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function approveRejectUser(uid,userRole,userGroup,userStatus,userReportingManager,userHRManager){
	//var uid = $('#uid').text();
	//document.getElementById('uid').value;
	//alert(uid+userRole+userGroup+userStatus+userReportingManager);
	document.getElementById("loaderGif").style.display='block';
	var usrGroup = document.getElementById(userGroup).value;
	//var usrGroup = "";
	var usrRole = document.getElementById(userRole).value;
	var usrStatus = document.getElementById(userStatus).value;
	var usrRemark = "";
	var usrReportingManager = document.getElementById(userReportingManager).value;
	var usrHRManager = document.getElementById(userHRManager).value;
	//$('#myModal').modal('hide');
	$.ajax({
		type: "POST",
		url: "updateUserData",
		dataType: 'json',
		data:{uid:uid,usrGroup:usrGroup,usrRole:usrRole,usrStatus:usrStatus,usrRemark:usrRemark,usrReportingManager:usrReportingManager,usrHRManager:usrHRManager},
		success: function(data){
			document.getElementById("loaderGif").style.display='none';
			if(data[0]){
				alert("User "+ uid +" has been updated successfully.");
			}else{
				alert("Try after some time.");
			}
			userMngtFunc('All');
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function addNewProject(){
		//document.getElementById("dataDiv").innerHTML = "";
			var nodes = document.getElementById('dataDiv').childNodes;
			for(var i=0; i<nodes.length; i++) {
			    if (nodes[i].nodeName.toLowerCase() == 'div') {
			         nodes[i].style.display='none';
			     }
			}
		document.getElementById("viewProjectDiv").style.display='block';
		document.getElementById('viewProjectDiv').setAttribute("style","min-height:550px");
		$('#id_prjctName').val('');
		$('#id_projectDesc').val('');
		$("#viewProjectDiv").appendTo("#dataDiv");
}
function submitDetailPrjct(){
	//var prjctType = document.getElementById('id_select_type').value;
	var prjctType = $("input[name='id_select_type']:checked"). val();
	var prjctName = document.getElementById('id_prjctName').value;
	var prjctDesc = document.getElementById('id_projectDesc').value;
	var prjctStartDate = document.getElementById('from-datepickerProject').value;
	var prjctEndDate = document.getElementById('to-datepickerProject').value;
	
	if(prjctName == "" || prjctName.length<5 || prjctName.length>50){
		alert("Select valid project name.\n Min Length:5\n Max Length:50");
		return false;
	}
	if(prjctDesc == "" || prjctDesc.length<8 || prjctDesc.length>250){
		alert("Fill valid project description.\n Min Length:8\n Max Length:250");
		return false;
	}
	if(prjctStartDate == ""){
		alert("Select valid start date.");
		return false;
	}
	if(prjctEndDate == ""){
		alert("Select valid end date.");
		return false;
	}
	document.getElementById("submit-project").disabled = true;
	$.ajax({
		type: "POST",
		url: "createNewProject",
		dataType: 'json',
		data:{prjctType:prjctType,prjctName:prjctName,prjctDesc:prjctDesc,prjctStartDate:prjctStartDate,prjctEndDate:prjctEndDate},
		success: function(data){
			document.getElementById("submit-project").disabled = false;
			if(data[0].length > 0){
				alert("New Project created successfully");
				var conf = confirm("Do you want to add Task and  Users to "+ prjctName +" Project?");
				if(conf == true){
					editExistingProjectDetail(data[0]);
				}else{
					$('#id_prjctName').val('');
					$('#id_projectDesc').val('');
					return false;
				}
			}else{
				alert("Try after some time.");
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function editProject(projectStatus){
	//document.getElementById("dataDiv").innerHTML = "";
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("editProjectDiv").style.display='block';
	document.getElementById('editProjectDiv').setAttribute("style","min-height:550px");
	$("#editProjectDiv").appendTo("#dataDiv");
	getProjectDetailsForEdit(projectStatus);
}
function assignProject(){
	//document.getElementById("dataDiv").innerHTML = "";
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("assignProjectDiv").style.display='block';
	document.getElementById('assignProjectDiv').setAttribute("style","min-height:550px");
	$("#assignProjectDiv").appendTo("#dataDiv");
	getProjectDetailsForAssign('Active');
}
function getProjectDetailsForAssign(filterVar){
	$.ajax({
		type: "POST",
		url: "getAllProjectDetailList",
		data:{filterVar:filterVar},
		dataType: 'json',
		success: function(result){
		if(result.length >0){
			document.getElementById("searchResultNullPass1").style.display='none';
			document.getElementById("assignPrjctItemDiv").style.display='block';
			document.getElementById('assignPrjctItemDiv').setAttribute("style","min-height:550px");
			$("#assignPrjctItemDiv").appendTo("#assignProjectDiv");
			$('#assignPrjctItemTbl td').remove();
			for (var i = 0; i < result.length; i++) {
				var row = "";
							if(i%2 == 0)
								row = $("<tr class='info' />");
							else
								row = $("<tr class='' />");														
				 	$("#tab_logic_assignPrjctItemApp").append(row); 
					row.append($("<td class='EWTableElements'>" + result[i].prjctId + "</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].projectName+"</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].projectOwner+"</td>"));
					if(result[i].startDate != undefined || result[i].startDate != null)
						row.append($("<td class='EWTableElements'>"+result[i].startDate+"</td>"));
					else
						row.append($("<td class='EWTableElements'>NA</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].status+"</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].type+"</td>"));
					row.append($("<td class='EWTableElements'>"+(result[i].creationDate).substring(0,10)+"</td>"));
					row.append($("<td class='EWTableElements'><a href='#' onclick=addUserToPrjct('"+result[i].prjctId+"');>Select User</a></td>"));
					/*row.append($("<td class='EWTableElements'><a href='#' style='color:green;' onclick=editExistingProjectDetail('"+result[i].prjctId+"');>Edit</a></td>"));*/
			    }
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});

	
}
function getProjectDetailsForEdit(filterVar){
	$.ajax({
		type: "POST",
		url: "getAllProjectDetailList",
		data:{filterVar:filterVar},
		dataType: 'json',
		success: function(result){
		if(result.length >0){
			document.getElementById("searchResultNullPass1").style.display='none';	
			document.getElementById("editPrjctItemDiv").style.display='block';
			document.getElementById('editPrjctItemDiv').setAttribute("style","min-height:550px");
			$("#editPrjctItemDiv").appendTo("#editProjectDiv");
			$('#editPrjctItemTbl td').remove();
			for (var i = 0; i < result.length; i++) {
				var row = "";
							if(i%2 == 0)
								row = $("<tr class='info' />");
							else
								row = $("<tr class='info' />");														
				 	$("#tab_logic_editPrjctItemApp").append(row); 
					row.append($("<td class='EWTableElements'>" + result[i].prjctId + "</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].projectName+"</td>"));
					var pName = replaceAll(result[i].projectName, " ", "%");
					row.append($("<td class='EWTableElements'>"+result[i].projectOwner+"</td>"));
					if(result[i].startDate != undefined || result[i].startDate != null)
						row.append($("<td class='EWTableElements'>"+result[i].startDate+"</td>"));
					else
						row.append($("<td class='EWTableElements'>NA</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].status+"</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].type+"</td>"));
					row.append($("<td class='EWTableElements'>"+(result[i].creationDate).substring(0,10)+"</td>"));
					row.append($("<td class='EWTableElements'><button type='button' class='btn btn-info btn-sm' onclick=addTask('"+result[i].prjctId+"','"+ pName +"');>Add Task</button></td>"));
					row.append($("<td class='EWTableElements'><button type='button' class='btn btn-success btn-sm' onclick=editExistingProjectDetail('"+result[i].prjctId+"');>Edit</button></td>"));
			    }
		}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function filterProjectByName(){
	var prjName = document.getElementById('projectName1').value;
	$.ajax({
		type: "POST",
		url: "getAllProjectDetailList",
		data:{filterVar:prjName},
		dataType: 'json',
		success: function(result){
		if(result.length >0){
			document.getElementById("searchResultNullPass1").style.display='none';	
			document.getElementById("editPrjctItemDiv").style.display='block';
			document.getElementById('editPrjctItemDiv').setAttribute("style","min-height:550px");
			$("#editPrjctItemDiv").appendTo("#editProjectDiv");
			$('#editPrjctItemTbl td').remove();
			for (var i = 0; i < result.length; i++) {
				var row = "";
							if(i%2 == 0)
								row = $("<tr class='info' />");
							else
								row = $("<tr class='' />");														
				 	$("#tab_logic_editPrjctItemApp").append(row); 
					row.append($("<td class='EWTableElements'>" + result[i].prjctId + "</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].projectName+"</td>"));
					var pName = replaceAll(result[i].projectName, " ", "%");
					row.append($("<td class='EWTableElements'>"+result[i].projectOwner+"</td>"));
					if(result[i].startDate != undefined || result[i].startDate != null)
						row.append($("<td class='EWTableElements'>"+result[i].startDate+"</td>"));
					else
						row.append($("<td class='EWTableElements'>NA</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].status+"</td>"));
					row.append($("<td class='EWTableElements'>"+result[i].type+"</td>"));
					row.append($("<td class='EWTableElements'>"+(result[i].creationDate).substring(0,10)+"</td>"));
					row.append($("<td class='EWTableElements'><button type='button' class='btn btn-info btn-sm' onclick=addTask('"+result[i].prjctId+"','"+ pName +"');>Add Task</button></td>"));
					row.append($("<td class='EWTableElements'><button type='button' class='btn btn-success btn-sm' onclick=editExistingProjectDetail('"+result[i].prjctId+"');>Edit</button></td>"));
			    }
			}else{
				document.getElementById("editPrjctItemDiv").style.display='none';
				//document.getElementById("searchResultNullPass1").style.display='block';	
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function editExistingProjectDetail(pid){
	var top=0;
    var left=0;
    var height=window.screen.availHeight;
    var width=window.screen.availWidth;
    //window.open('openProjecttdetailPage?pid='+pid ,'ProjectScreen','scrollbars=yes,resizable=yes,toolbar=no,Addressbar=no,menubar=no,status=yes,location=no,top='+top+',left='+left+',height='+height+',width='+width);
    var params = { 'pid' : pid };
    openNewRequest("openProjecttdetailPage",params);
}
function addTask(pid,pName){
	$('#addTaskModal').modal('show');
	var PrjName = replaceAll(pName, "%", " ");
	$('#id_prjctName1').html(PrjName);
	$('#pidValue').val(pid);
	$('#id_taskName').val('');
	getAllTaskType("id_taskType");
	$('#id_taskDesc').val('');
}
function addTask1(pid,pName){
	$('#addTaskModal1').modal('show');
	var PrjName = replaceAll(pName, "%", " ");
	$('#id_prjctName11').html(PrjName);
	$('#pidValue1').val(pid);
	$('#id_taskName1').val('');
	getAllTaskType("id_taskType1");
	$('#id_taskDesc1').val('');
}
function getAllTaskType(id){
	$.ajax({
		type: "POST",
		url: "getTaskTypeList",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(id);
			//alert(select);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Type','select'));
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].taskType,data[i].taskType));
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});	
}
function insertNewTask(){
	var taskName = document.getElementById('id_taskName').value;
	var taskType = document.getElementById('id_taskType').value;
	var taskDesc = document.getElementById('id_taskDesc').value;
	var pid = document.getElementById('pidValue').value;
	$.ajax({
		type: "POST",
		url: "addNewTaskToPrjct",
		dataType: 'json',
		data:{taskName:taskName,taskType:taskType,taskDesc:taskDesc,pid:pid},
		success: function(data){
			//$('#addTaskModal').modal('hide'); 
			if(data[0]){
				alert("Task inserted successfully.");
			}else{
				alert("Try after some time.");
			}
			$('#addTaskModal').modal('hide');
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function insertNewTask1(){
	var taskName = document.getElementById('id_taskName1').value;
	var taskType = document.getElementById('id_taskType1').value;
	var taskDesc = document.getElementById('id_taskDesc1').value;
	var pid = document.getElementById('pidValue1').value;
	$.ajax({
		type: "POST",
		url: "addNewTaskToPrjct",
		dataType: 'json',
		data:{taskName:taskName,taskType:taskType,taskDesc:taskDesc,pid:pid},
		success: function(data){
			//$('#addTaskModal').modal('hide'); 
			if(data[0]){
				alert("Task inserted successfully.");
				location.reload(true);
			}else{
				alert("Try after some time.");
			}
			$('#addTaskModal1').modal('hide');
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function resetUpdateProjectUserModal(){
	var projectId = document.getElementById('id_projectId1').value;
	addUserToPrjct(projectId);
}
function addUserToPrjct(pid){
	var allUserFlag = true;
	$.ajax({
		type: "POST",
		url: "getUserListForProjectAllocation",
		dataType: 'json',
		async:false,
		data:{pid:pid,allUserFlag:allUserFlag},
		success: function(data){
		if(data.length > 0){
			$('#assignUserModal1').modal('show');
			$('#id_projectName1').html("Project Id :: "+pid);
			$('#id_projectId1').val(pid);
			var select = document.getElementById("duallistbox_demo55");
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].userfullname+"("+data[i].username+")",data[i].username));
				select.options[i].title = data[i].usermail;
				if(data[i].existingInGroupFlag == 'Y'){
					select.options[i].selected = true;
				}	
			 }
			$('[name=duallistbox_demo5]').bootstrapDualListbox('refresh');
			}else
			{
				alert("No data available");
				return false;
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function addRemoveUsersFromProject1(){
	var addRemoveFlag = "";
	var selectedUsers = [];
	selectedUsers = $('[name=duallistbox_demo5]').val(); 
	var projectId = document.getElementById('id_projectId1').value;
	if( null == selectedUsers){
		selectedUsers = [''];
	}
	//alert(selectedUsers.length);
	$.ajax({
		type: "POST",
		url: "addRemoveUsersFromProject",
		dataType: 'json',
		data:{projectId:projectId,selectedUsers:selectedUsers,addRemoveFlag:addRemoveFlag},
		success: function(data){
			if(data[0]){
				if(selectedUsers[0] == "")
					alert("All Users from project "+ $('#id_projectId1').val() +" deallocated successfully.");
				else
					alert("Users in project "+ $('#id_projectId1').val() +" updated successfully.");
			}else{
				alert("Try after some time.");
			}
			$('#assignUserModal1').modal('hide');
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
	
}
function addCmt(id){
	var cmtHiddenId = id.substring(0, id.length - 1) + 'Comment'; 
	var tempEffortId = id.substring(0, id.length - 5) + "effortInHoursDay" + id.substring(id.length - 2,id.length - 1);
	var tempEffort = document.getElementById(tempEffortId).value;
	if(tempEffort == "0" || tempEffort == ""){
		alert("Please fill hours first.");
		return false;
	}
	$('#addCmtModal').modal('show');	
	$('#cmtValueHidden').val(cmtHiddenId);
	$('#idComment').val(document.getElementById(cmtHiddenId).value);
}
function captureCmt(){
	var cmtId = document.getElementById('cmtValueHidden').value;
	var comment = document.getElementById('idComment').value;
	if(comment.length>100 || comment == ""){
		alert("Invalid or exceeded limit beyond 100");
		return false;
	}
	document.getElementById(cmtId).value = comment;
	$('#addCmtModal').modal('hide');
}
function calculateTotalWeekEffort(){
	var totalRow = $('#tab_logic1 tr').length - 1;
	var total = 0;
	for (var i = 0;i<totalRow; i++) {
		if($('#tab_logic1 tr:nth-child('+(i+1)+') td').length > 0){
			var tempDay1Effort = document.getElementById('taskMappingList['+ i +'].effortInHoursDay1').value;
			var tempDay2Effort = document.getElementById('taskMappingList['+ i +'].effortInHoursDay2').value;
			var tempDay3Effort = document.getElementById('taskMappingList['+ i +'].effortInHoursDay3').value;
			var tempDay4Effort = document.getElementById('taskMappingList['+ i +'].effortInHoursDay4').value;
			var tempDay5Effort = document.getElementById('taskMappingList['+ i +'].effortInHoursDay5').value;
			var tempDay6Effort = document.getElementById('taskMappingList['+ i +'].effortInHoursDay6').value;
			var tempDay7Effort = document.getElementById('taskMappingList['+ i +'].effortInHoursDay7').value;
			total = +total + +tempDay1Effort + +tempDay2Effort + +tempDay3Effort + +tempDay4Effort + +tempDay5Effort + +tempDay6Effort + +tempDay7Effort;
		}
	}
	return total;
}
function saveApproverRmrk(){
	var username = document.getElementById('userName12').value;
	var appRemark = document.getElementById('approverRemark11').value;
	var startDate = document.getElementById('frstDayDate11').value;
	var endDate = document.getElementById('lastDayDate11').value;
	var alphanumers = /^[a-zA-Z0-9]+$/;
	//alert(username+appRemark+startDate+endDate);
	if(appRemark == "" || (appRemark.length > 100)){
		alert("Please enter valid remarks.");
		return false;
	}
	$.ajax({
		type: "POST",
		url: "updateApproverRemark",		
		dataType: 'json',
		data:{username:username,appRemark:appRemark,startDate:startDate,endDate:endDate},
		success: function(data){
			if(data[0]){
				alert("Updated successfully.");
			}else{
				alert("Try after some time.");
			}
			//$('#assignUserModal').modal('hide');
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function deleteLastTask(pid,taskId,weekValue){
	//alert(pid+taskId+weekValue);
	var startDate = moment(weekValue, "YYYY-MM-DD").day(0).format("YYYY-MM-DD");
    var endDate =  moment(weekValue, "YYYY-MM-DD").day(6).format("YYYY-MM-DD");
	if(pid == 'select' || pid == ""){
		return true;
	}else{
		var ret = confirm("Do you want to delete last row?");	
		if(ret == true){
			$.ajax({
				type: "POST",
				url: "deleteTaskForGivenWeek",		
				dataType: 'json',
				data:{pid:pid,taskId:taskId,startDate:startDate,endDate:endDate},
				success: function(data){
					if(data[0]){
						//alert("successfully.");
					}else{
						alert("Try after some time.");
					}
				},
				error: function(jqXHR, textStatus)
				{
				alert("ERROR:"+textStatus);
				}
			});
		}else{
			return false;
		}
	}
}
function disAllocateUser(id){
	var pid = document.getElementById('projectId').value;
	var username = document.getElementById(id.substring(1,id.length)).value;
	//alert(pid+username);
	var ret = confirm("Do you want to remove "+ username +" from project "+pid);	
	if(ret == true){
		$.ajax({
			type: "POST",
			url: "removeUserFrmProject",		
			dataType: 'json',
			data:{pid:pid,username:username},
			success: function(data){
				if(data[0]){
					alert("User removed successfully");
					location.reload(true);
					$("tab-content").addClass("#tab3:checked ~ #content3");
					//$('#tab3:checked ~ #content3').setAttribute("style","display:block");
				}else{
					alert("Try after some time.");
				}
			},
			error: function(jqXHR, textStatus)
			{
			alert("ERROR:Error Occured");
			}
		});
	}else{
		return false;
	}
}
function removeTaskFrmPrjct(id){
	//alert(id.substring(4,id.length));return false;
	var pid = document.getElementById('projectId').value;
	//var taskId = document.getElementById(id.substring(4,id.length)).value;
	//alert(pid+taskId);
	var taskId = id.substring(4,id.length);
	var ret = confirm("Do you want to remove "+ taskId +" from project "+pid);	
	if(ret == true){
		$.ajax({
			type: "POST",
			url: "removeTaskFrmProject",		
			dataType: 'json',
			data:{pid:pid,taskId:taskId},
			success: function(data){
				if(data[0]){
					alert("Task has been removed successfully");
					location.reload(true);
					$("tab-content").addClass("#tab2:checked ~ #content2");
					//$('#tab3:checked ~ #content3').setAttribute("style","display:block");
				}else{
					alert("Try after some time.");
				}
			},
			error: function(jqXHR, textStatus)
			{
			alert("ERROR:Error Occured");
			}
		});
	}else{
		return false;
	}
}
function openUserProfileDiv(){
	//document.getElementById("dataDiv").innerHTML = "";
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
document.getElementById("viewUserProfileDiv").style.display='block';
document.getElementById('viewUserProfileDiv').setAttribute("style","min-height:550px");
			$('#id_prjctName').val('');
			$('#id_projectDesc').val('');
			$("#viewUserProfileDiv").appendTo("#dataDiv");

	
}
function changePwdModal(){
	$('#chngPwdModal').modal('show');	
	$('#idOldPwd').val('');
	$('#idNewPwd').val('');
	$('#idNewConfPwd').val('');
}
function updateNewPwd(){
	var oldPwd = document.getElementById('idOldPwd').value;
	var newPwd = document.getElementById('idNewPwd').value;
	if(oldPwd == ""){
		alert("Please input old password");
		return false;
	}
	if(matchPwd()){
		$.ajax({
			type: "POST",
			url: "updateUserPassword",		
			dataType: 'json',
			data:{oldPwd:oldPwd,newPwd:newPwd},
			success: function(data){
				if(data[0]){
					alert("Password has been changed successfully.");
					window.location.href = "logoutUser";
				}else{
					alert("Old password did not match.Kindly use forgot password");
					return false;
				}
			},
			error: function(jqXHR, textStatus)
			{
			alert("ERROR:Error Occured");
			}
		});
	}else{
		alert("Please provide valid input.");
		return false;
	}
	
}
function matchPwd(){
	 var ucase = new RegExp("[A-Z]+");
		var lcase = new RegExp("[a-z]+");
		var num = new RegExp("[0-9]+");
		
		if($("#idNewPwd").val().length >= 8){
			$("#8char").removeClass("glyphicon-remove");
			$("#8char").addClass("glyphicon-ok");
			$("#8char").css("color","#00A41E");
		}else{
			$("#8char").removeClass("glyphicon-ok");
			$("#8char").addClass("glyphicon-remove");
			$("#8char").css("color","#FF0004");
		}
		
		if(ucase.test($("#idNewPwd").val())){
			$("#ucase").removeClass("glyphicon-remove");
			$("#ucase").addClass("glyphicon-ok");
			$("#ucase").css("color","#00A41E");
		}else{
			$("#ucase").removeClass("glyphicon-ok");
			$("#ucase").addClass("glyphicon-remove");
			$("#ucase").css("color","#FF0004");
			return false;
		}
		
		if(lcase.test($("#idNewPwd").val())){
			$("#lcase").removeClass("glyphicon-remove");
			$("#lcase").addClass("glyphicon-ok");
			$("#lcase").css("color","#00A41E");
		}else{
			$("#lcase").removeClass("glyphicon-ok");
			$("#lcase").addClass("glyphicon-remove");
			$("#lcase").css("color","#FF0004");
			return false;
		}
		
		if(num.test($("#idNewPwd").val())){
			$("#num").removeClass("glyphicon-remove");
			$("#num").addClass("glyphicon-ok");
			$("#num").css("color","#00A41E");
		}else{
			$("#num").removeClass("glyphicon-ok");
			$("#num").addClass("glyphicon-remove");
			$("#num").css("color","#FF0004");
			return false;
		}
		
		if($("#idNewPwd").val() == $("#idNewConfPwd").val()){
			$("#pwmatch").removeClass("glyphicon-remove");
			$("#pwmatch").addClass("glyphicon-ok");
			$("#pwmatch").css("color","#00A41E");
			return true;
		}else{
			$("#pwmatch").removeClass("glyphicon-ok");
			$("#pwmatch").addClass("glyphicon-remove");
			$("#pwmatch").css("color","#FF0004");
			return false;
		}
}
function replaceAll(str, find, replace) {
    return str.replace(new RegExp(find, 'g'), replace);
}
function getAllUserForReport(allUFlag,id,pidElem){
	var pid = "";
	if(pidElem.length > 0)
		pid = document.getElementById(pidElem).value;
	$.ajax({
		type: "POST",
		url: "getUserList",
		dataType: 'json',
		data:{pid:pid,allUFlag:allUFlag},
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
function getUsersInPrjct(allUserFlag){
	var pid = document.getElementById("projectList").value;
	$.ajax({
		type: "POST",
		url: "getUsersList",
		dataType: 'json',
		data:{pid:pid,allUserFlag:true},
		success: function(data){
			var select = document.getElementById('usrListInPrjct');
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select User','select'));
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
function addNewUser(){
	//document.getElementById("dataDiv").innerHTML = "";
		var nodes = document.getElementById('dataDiv').childNodes;
		for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].nodeName.toLowerCase() == 'div') {
		         nodes[i].style.display='none';
		     }
		}
		document.getElementById("createUserDiv").style.display='block';
		document.getElementById('createUserDiv').setAttribute("style","min-height:550px");
		$('#userCreateId').val('');
		$('#fCreateName').val('');
		$('#lCreateName').val('');
		$('#userCreateMail').val('');
		$("#createUserDiv").appendTo("#dataDiv");
		getAllReportee('userCreateReportee');
		getAllHRManager('userCreateHRReportee');
		groupListForUserModification('userCreateGrp');
		getGroupType('userAccessGrp');
		populateRoles('usrCreateRole');
		populatePolicyGroup('userCreatePolicyGroup');
}
function checkUserNameAvailability1(){
	var status = false;
	var flag = false;
	var userids = document.getElementById('userCreateId').value;
	$.ajax({
		type: "POST",
		url: "getExistingUserList",
		dataType: 'json',
		async: false,
		success: function(data){
			for(var i=0;i<data.length;i++){	
                if(data[i].userid.toUpperCase() == userids.toUpperCase()){
                	flag = true;
                	status = false;
                	$('#user-availability-status').html('&#x274c'+ "Not available");
                	break;
                }
            }
			if(flag == true){
				$('#user-availability-status').html('&#x274c'+ "Not available");
            	status = false;
			}else{
				$('#user-availability-status').html('&#x2705' + "Perfect");
				status = true;
				return true;
			}	
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
			status = false;
		}
	});
	return status;
}
function checkfNameAvailability(){
	var fName = document.getElementById('fCreateName').value;
	var regx = /^[a-zA-Z ]+$/;
	if(fName == "" || !regx.test(fName)){
		$('#user-fName-status').html('&#x274c'+ "Not Applicable");
    	return false;
    }else{
    	$('#user-fName-status').html('&#x2705' + "Perfect");
    }
	return true;
}
function checklNameAvailability(){
	var lName = document.getElementById('lCreateName').value;	
	if(lName == "" || !alphanumers.test(lName)){
		$('#user-lName-status').html('&#x274c'+ "Not Applicable");
    	return false;
    }else{
    	$('#user-lName-status').html('&#x2705' + "Perfect");
    }
	return true;
}
function checkUserMailAvailability(){
	var email1 = document.getElementById('userCreateMail').value;	
	if(email1 == "" || !(isEmail(email1))){
		$('#user-mail-status').html('&#x274c'+ "Not Applicable");
    	return false;
	}else{
	$.ajax({
		type: "POST",
		url: "getExistingUserMailList",
		data:{email:email1},
		dataType: 'json',
		success: function(data){
                if(data[0].status){
                	$('#user-mail-status').html('&#x274c'+ "Not Applicable");
                	return false;
                }else{
                	$('#user-mail-status').html('&#x2705' + "Perfect");
                	return true;
                }
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
	}
	return true;
}
function isEmail(email) {
	return /^[a-z0-9]+([-._][a-z0-9]+)*@([a-z0-9]+(-[a-z0-9]+)*\.)+[a-z]{2,4}$/.test(email)
    && /^(?=.{1,64}@.{4,64}$)(?=.{6,100}$).*/.test(email);
}
function getAllReportee(id){
	$.ajax({
		type: "POST",
		url: "getAllReportingManager",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(id);
			//alert(select);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Reporting Manager','select'));
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
function getStsType(id){
	$.ajax({
		type: "POST",
		url: "getAllStatusType",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(id);
			//alert(select);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Status','select'));
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
function groupListForUserModification(id){
	$.ajax({
		type: "POST",
		url: "groupListForUserModification",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(id);
			//alert(select);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			if(id != 'userGroupList')
				select.add(new Option('User Group','select'));
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].usergroup,data[i].usergroup));
			    }
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function getGroupType(id){
	$.ajax({
		type: "POST",
		url: "getAllGroupType",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(id);
			//alert(select);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			if(id != 'userGroupList')
				select.add(new Option('Access Group','select'));
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].grpType,data[i].groupid));
			    }
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function addUserByAdmin(){
	var username = document.getElementById('userCreateId').value;
	var fName = document.getElementById('fCreateName').value;
	var lName = document.getElementById('lCreateName').value;
	var email = document.getElementById('userCreateMail').value;
	var group = document.getElementById('userCreateGrp').value;
	var accessgroup = document.getElementById('userAccessGrp').value;
	var role = document.getElementById('usrCreateRole').value;
	var status = document.getElementById('userCreateStatus').value;
	var reportee = document.getElementById('userCreateReportee').value;
	var reporteeHR = document.getElementById('userCreateHRReportee').value;
	var policyGroup = document.getElementById('userCreatePolicyGroup').value;
	//var remark = document.getElementById('approverCreateComment').value;
	var remark = "";
	
	if(username == "" || !alphanumers.test(username)){
		alert("Enter valid Username.");		
		return false;
	}
	if(group == "" || role == "" || status == "" || reportee == "" || reporteeHR == "" || accessgroup == "" || policyGroup == ""){
		alert("Please select Group/Access Group/Role/Status/Reporting Manager/HR Manager/Policy Group");
		return false;
	}
	if(checkUserNameAvailability1() && checkfNameAvailability() && checklNameAvailability() && checkUserMailAvailability()){
		var ret = confirm("Do you want to add new User?");
		if (ret == true) {
			document.getElementById("loaderGif").style.display='block';
			$.ajax({
				type: "POST",
				url: "createNewUserInDB",		
				dataType: 'json',
				data:{username:username,fName:fName,lName:lName,email:email,group:group,role:role,status:status,reportee:reportee,remark:remark,reporteeHR:reporteeHR,accessgroup:accessgroup,policyGroup:policyGroup},
				success: function(data){
					if(data[0]){
						document.getElementById("loaderGif").style.display='none';
						alert("New User has been added successfully.");
					}else{
						document.getElementById("loaderGif").style.display='none';
						alert("Try after some time.");
					}
				},
				error: function(jqXHR, textStatus)
				{
					document.getElementById("loaderGif").style.display='none';	
				alert("ERROR:Error Occured");
				}
			});
		} else {
			return false;
		}
}else{
	alert("One or more invalid entry.");
	return false;
}
}
function getRoleType(id){
	//alert(id);
	$.ajax({
		type: "POST",
		url: "getRoleTypeList",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(id);
			//alert(select);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Role','select'));
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].roleType,data[i].roleType));
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});	
}
function getPrjStatusType(id){
	//alert(id);
	$.ajax({
		type: "POST",
		url: "getPrjStatusTypeList",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(id);
			//alert(select);
			/*for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }*/
			//select.add(new Option('Select Type','select'));
			for (var i = 0; i < data.length; i++) {
				if(!(document.getElementById(id).value == data[i].prjStatusType))
					select.add(new Option(data[i].prjStatusType,data[i].prjStatusType));
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});	
}
function updateViewPrjDetails(){
	var pid = document.getElementById('projectId').value;
	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;
	var status = document.getElementById("projectStatus").value;
	var owner = document.getElementById("projectOwner").value;
	var timesheetApprover = document.getElementById("timesheetApprover").value;
	//alert(pid+startDate+endDate+status);
	if(startDate == "" || endDate == ""){
		alert("Please enter strat/end date");
		return false;
	}
	$.ajax({
		type: "POST",
		url: "updateProjectStatus",
		dataType: 'json',
		data:{pid:pid,startDate:startDate,endDate:endDate,status:status,timesheetApprover:timesheetApprover,owner:owner},
		success: function(data){
			alert("Data successfully saved.");
			window.location.reload(true);
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function updateTaskFrmPrjct(id,index){
	//alert(id+"***"+index);return false;
	var taskId = id.substring(4,id.length);
	var taskName = document.getElementById("taskName["+index+"]").value;
	var taskDesc = document.getElementById("taskDesc["+index+"]").value;
	var taskCriticality = document.getElementById("criticality["+index+"].criticality").value;
	var taskStartdate = document.getElementById("actualStartDate["+index+"]").value;
	var taskEnddate = document.getElementById("actualEndDate["+index+"]").value;
	var taskStatus = document.getElementById("taskStatus["+index+"].taskStatus").value;
	var assignedTo = document.getElementById("assignedTo["+index+"].assignedTo").value;
	if(taskName == "" || taskDesc == ""){
		alert("Invalid/Blank task name/description");
		return false;
	}
	if(taskStartdate == "" || taskEnddate == ""){
		alert("Invalid/Blank start/end date");
		return false;
	}
	var ret = confirm("Do you want to update "+ taskName);
	if(ret == true){
		$.ajax({
			type: "POST",
			url: "editTasksOfPrject",		
			dataType: 'json',
			data:{taskId:taskId,taskName:taskName,taskDesc:taskDesc,taskCriticality:taskCriticality,taskStartdate:taskStartdate,taskEnddate:taskEnddate,taskStatus:taskStatus,assignedTo:assignedTo},
			success: function(data){
				if(data[0]){
					alert("Task has been updated successfully");
					location.reload(true);
					$("tab-content").addClass("#tab2:checked ~ #content2");
				}else{
					alert("Try after some time.");
				}
			},
			error: function(jqXHR, textStatus)
			{
			alert("ERROR:Error Occured");
			}
		});
	}else{
		return false;
	}
}
function getAllRepoManagerForCreate(){
	
}
function getUserReportParameter(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("vieMonthlyReportDiv").style.display='block';
	document.getElementById('vieMonthlyReportDiv').setAttribute("style","min-height:500px;");
	$("#vieMonthlyReportDiv").appendTo("#dataDiv");
	document.getElementById("downloadMonthlyReportDiv").style.display='none';
	$("select#projectListOfUser").prop('selectedIndex', 0);
	getAllUserForMonhlyReport(false);
}
function getUserReportParameter1(){
	var projectId = document.getElementById('projectListOfUser').value;
	var year = document.getElementById('yearsForReport').value;
	var month = document.getElementById('monthForReport').value;
	var username = document.getElementById('userListForMonthlyReport').value;
	document.getElementById("loaderGif").style.display='block';
	$.ajax({
		type: "POST",
		url: "generateMonthlyReport",
		dataType: 'json',
		data: {projectId :projectId,startDate:month,endDate:year,username:username},
		success: function(data){
			document.getElementById("loaderGif").style.display='none';
			document.getElementById("downloadMonthlyReportDiv").style.display='block';
			$('#fileNameMonthlyReportTime').html(new Date($.now()));
			$('#fileNameMonthlyReport').html(data[0].FileName);
			$('#fileNameHidden').val(data[0].FileName);
			if(data[0].FileName == "Please try after some time.")
				document.getElementById("downloadMonthButton").style.display='none';
			else
				document.getElementById("downloadMonthButton").style.display='block';
			
		},
		error: function(jqXHR, textStatus)
		{
		document.getElementById("downloadReportDiv").style.display='none';	
		alert("ERROR:"+textStatus);
		}
	});
}
function getAllUserForMonhlyReport(allUFlag){
	//alert(allUFlag);
	var pid = "select";
	$.ajax({
		type: "POST",
		url: "getUserList",
		dataType: 'json',
		data:{pid:pid,allUFlag:allUFlag},
		success: function(data){
			var select = document.getElementById('userListForMonthlyReport');
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select User','select'));
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
function getUsersProjectList(){
	var uid = document.getElementById('userListForMonthlyReport').value;
	if(uid == 'select'){
		alert("Please select Employee");
		return false;
	}
	$.ajax({
		type: "POST",
		url: "getUsersAllProjectList",
		dataType: 'json',
		data: {uid:uid},
		success: function(data){
			var select = document.getElementById('projectListOfUser');
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Project','select'));
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].projectName,data[i].projectId));
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function showActionHistory(){
	var selectedWeek1 = $("#weeklyDatePicker").val();
	var value = new Date(selectedWeek1.substring(0, 10));
	var firstDate = moment(value, "YYYY-MM-DD").day(0).format("YYYY-MM-DD");
    var lastDate =  moment(value, "YYYY-MM-DD").day(6).format("YYYY-MM-DD");
    var username = "";
    $.ajax({
		type: "POST",
		url: "getUsersActionHistory",
		dataType: 'json',
		data: {firstDate:firstDate,lastDate:lastDate,username:username},
success: function(result){
	if(result.length > 0){
		$('#actionHistoryModal').modal('show');
		    $('#tmsActionHistoryTbl td').remove();
			for (var i = 0; i < result.length; i++) {
				var row = "";
							if(i%2 == 0)
								row = $("<tr class='info' />");
							else
								row = $("<tr class='' />");														
				 	$("#tab_logic_actionHistory").append(row); 
				 	row.append($("<td class='EWTableElements'>"+result[i].action+"</td>"));
				 	row.append($("<td class='EWTableElements'>"+result[i].actionDate+"</td>"));
				 	row.append($("<td class='EWTableElements'>"+result[i].actionBy+"</td>"));
				 	row.append($("<td class='EWTableElements'>"+result[i].actionRemark+"</td>"));
			    }
			}else{
		alert("No action has been taken on shown week");
		return false;
	}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function showActionHistoryView(){
var value = $("#frstDayDate11").val();
var firstDate = moment(value, "YYYY-MM-DD").day(0).format("YYYY-MM-DD");
var lastDate =  moment(value, "YYYY-MM-DD").day(6).format("YYYY-MM-DD");
var username = $("#userName12").val();
//alert(firstDate + lastDate);
$.ajax({
	type: "POST",
	url: "getUsersActionHistory",
	dataType: 'json',
	data: {firstDate:firstDate,lastDate:lastDate,username:username},
success: function(result){
if(result.length > 0){
		$('#actionHistoryModal').modal('show');
	    $('#tmsActionHistoryTbl td').remove();
		for (var i = 0; i < result.length; i++) {
			var row = "";
						if(i%2 == 0)
							row = $("<tr class='info' />");
						else
							row = $("<tr class='' />");														
			 	$("#tab_logic_actionHistory").append(row); 
			 	row.append($("<td class='EWTableElements'>"+result[i].action+"</td>"));
			 	row.append($("<td class='EWTableElements'>"+result[i].actionDate+"</td>"));
			 	row.append($("<td class='EWTableElements'>"+result[i].actionBy+"</td>"));
			 	row.append($("<td class='EWTableElements'>"+result[i].actionRemark+"</td>"));
		    }
		}else{
	alert("No action has been taken on shown week");
	return false;
}
	},
	error: function(jqXHR, textStatus)
	{
		alert("ERROR:"+textStatus);
	}
});
}
function emailNotificationDiv(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("vieNotificationDiv").style.display='block';
	document.getElementById('vieNotificationDiv').setAttribute("style","min-height:500px;");
	$("#vieNotificationDiv").appendTo("#dataDiv");
	$.ajax({
		type: "POST",
		url: "getEmailNotificationFlagStatus",
		dataType: 'json',
		success: function(result){
			//$('#tmsNotificationTbl td').remove();
			$('#emailNotificationDiv').empty();
			var moduleIdarr = [];
			for (var l = 0; l < result.length; l++) {
				moduleIdarr.push(result[l].moduleName);
			}
			var uniqueArr = moduleIdarr.filter( onlyUnique );
		for(var k=0;k<uniqueArr.length;k++){
			$('#emailNotificationDiv').append($("<div class='col-lg-4 panel panel-default template'><div class='panel-heading panel-grey'>"+uniqueArr[k]+"</div><div class='panel-body' style='min-height:182px;max-height:182px;overflow:auto;padding:0px;background:#f1f1f1;border-radius:5px;'><table class='table table-striped' style='background-color:#EBF5FB;width:100%;border:none;' scrolling='auto'><thead style='background-color: #D4E6F1;'><tr><th>Evenet Name</th><th>Enable Flag</th></tr></thead><tbody id='module"+k+"'></tbody></table></div></div>"));
			for (var i = 0; i < result.length; i++) {
				if(uniqueArr[k] == result[i].moduleName){
					var row = $("<tr class='' />");
				 	$("#module"+k).append(row); 
				 	row.append($("<td class='EWTableElements'>"+result[i].eventName+"</td>"));
				 	if(result[i].status == "true")
				 		row.append($("<td class='EWTableElements'><label class='switch'><input type='checkbox' id='"+result[i].eventId+"' checked onchange='updateEmailNotification(this.id);'><span class='slider round'></span></label></td>"));
				 	else
				 		row.append($("<td class='EWTableElements'><label class='switch'><input type='checkbox' id='"+result[i].eventId+"' onchange='updateEmailNotification(this.id);'><span class='slider round'></span></label></td>"));
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
function updateEmailNotification(id){
	var status = false;
	if ($('#'+id).is(":checked")){
	  status = true;
	}else{
	  status = false;	
	}
$.ajax({
	type: "POST",
	url: "updateEmailNotificationStatus",		
	dataType: 'json',
	data:{id:id,status:status},
	success: function(data){
		if(data[0]){
			emailNotificationDiv();
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
function getDataFromTimeSheetRecent(weekDate){
	var value = new Date(weekDate.substring(0, 10));
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
    $("#nextWeek").attr("disabled",false);
    var temp = new Date();
    if(new Date(lastDate) >= temp)
    {
  	  $("#nextWeek").attr("disabled",true);
    }
    getDataFromTimeSheet1(value);
}
function getRecentTimesheetData(){
	var value = formatDate(new Date());
	var firstDate = moment(value, "YYYY-MM-DD").day(0).format("YYYY-MM-DD");
	var lastDate =  moment(value, "YYYY-MM-DD").day(6).format("YYYY-MM-DD");
	var temp = firstDate + "-" + lastDate;
	var weekArray = [temp];
	for(var j=1;j<5;j++){
		  value = new Date(temp.substring(0, 10));
		  value.setDate(value.getDate() - 4);
		  value = formatDate(value);
		  var firstDate1 = moment(value, "YYYY-MM-DD").day(0).format("YYYY-MM-DD");
		  var lastDate1 =  moment(value, "YYYY-MM-DD").day(6).format("YYYY-MM-DD");
		  temp = firstDate1 + "-" + lastDate1;
		  weekArray.push(temp);
	}
	$.ajax({
		type: "POST",
		url: "getRecentTimesheetData",
		dataType: 'json',
		success: function(result){
			$('#userLasttenWeekTbl td').remove();
			for (var i = 0; i < 5; i++) {
				var row = "";
							if(i%2 == 0)
								row = $("<tr class='info' />");
							else
								row = $("<tr class='' />");		
				 	$("#tab_logic_userLasttenWeekApp").append(row); 
				 	row.append($("<td class='EWTableElements'>"+(i+1)+"</td>"));
				 	//alert(typeof result[i].weekDate != 'undefined');
				 	//if(typeof result[i].weekDate == 'undefined' || (weekArray[i] != result[i].weekDate))
				 	var temp = "";
				 	for (var j = 0; j < result.length; j++) {
				 		if(weekArray[i] == result[j].weekDate){
				 			temp = result[j].weekDate;
				 			row.append($("<td class='EWTableElements' style='padding:10px;'><a href='#!' onclick=getDataFromTimeSheetRecent('"+ result[j].weekDate +"');return false; >Sun, "+result[j].weekDate.substring(8, 10)+"/"+result[j].weekDate.substring(5, 7)+" to " +result[j].weekDate.substring(19,21)+"/"+result[j].weekDate.substring(16,18)+ " ,Sat</a></td>"));
						 	row.append($("<td class='EWTableElements'>"+result[j].totaleffort+"</td>"));
						 	row.append($("<td class='EWTableElements'>"+result[j].status+"</td>"));
						 	row.append($("<td class='EWTableElements'>"+result[j].lastmodifiedon+"</td>"));
				 		}
				 	}
				 	if(temp.length == 0){
				 		row.append($("<td class='EWTableElements' style='padding:10px;'><a href='#!' onclick=getDataFromTimeSheetRecent('"+ weekArray[i] +"');return false; >Sun, "+weekArray[i].substring(8, 10)+"/"+weekArray[i].substring(5, 7)+" to " +weekArray[i].substring(19,21)+"/"+weekArray[i].substring(16,18)+ " ,Sat</a></td>"));
				 		row.append($("<td class='EWTableElements'>0</td>"));
				 		row.append($("<td class='EWTableElements' style='color:red;'>Not Filled</td>"));
				 		row.append($("<td class='EWTableElements'>NA</td>"));
				 	}
				 		/*row.append($("<td class='EWTableElements' style='padding:10px;'><a href='#!' onclick=getDataFromTimeSheetRecent('"+ result[i].weekDate +"');return false; >Sun,"+(result[i].weekDate).substring(5,10)+" to "+ (result[i].weekDate).substring(16,(result[i].weekDate).length)+",Sat</a></td>"));
					 	row.append($("<td class='EWTableElements'>"+result[i].totaleffort+"</td>"));
					 	row.append($("<td class='EWTableElements'>"+result[i].status+"</td>"));*/
			  }
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function getReportParameterWeekly(){
	$.ajax({
		type: "POST",
		url: "renderreportsparam",
		dataType: 'json',
		success: function(data){
			//document.getElementById("dataDiv").innerHTML = "";
			var nodes = document.getElementById('dataDiv').childNodes;
			for(var i=0; i<nodes.length; i++) {
			    if (nodes[i].nodeName.toLowerCase() == 'div') {
			         nodes[i].style.display='none';
			     }
			}
			document.getElementById("downloadWeeklyReportDiv").style.display='none';
			document.getElementById("vieReportWeeklyDiv").style.display='block';
			document.getElementById('vieReportWeeklyDiv').setAttribute("style","min-height:550px");
			$("#vieReportWeeklyDiv").appendTo("#dataDiv");
			getAllUserForReport(false,'usrListInWeeklyReport','');
			
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});	
}
function getReportWeekly(){
	var status = document.getElementById('weeklyStatus').value;
	var username = document.getElementById('usrListInWeeklyReport').value;
	/*if(projectId == undefined || projectId == null || projectId == "select"){
		   alert("Please select valid project");
		   return false;
	   }*/	
	var startDate1 = document.getElementById('from-datepickerWeekly').value;
	if(startDate1 == undefined || startDate1 == null || startDate1 == ""){
		   alert("Please select start date");
		   return false;
	   }	
	var endDate1 = document.getElementById('to-datepickerWeekly').value;
	if(endDate1 == undefined || endDate1 == null || endDate1 == ""){
		   alert("Please select end date");
		   return false;
	   }
	var startDate = moment(startDate1, "YYYY-MM-DD").day(0).format("YYYY-MM-DD");
    var endDate =  moment(endDate1, "YYYY-MM-DD").day(6).format("YYYY-MM-DD");
   // alert(startDate+"*"+endDate);
	document.getElementById("loaderGif").style.display='block';
	$.ajax({
		type: "POST",
		url: "generateReportWeekly",
		dataType: 'json',
		data: {status :status,startDate:startDate,endDate:endDate,username:username},
		success: function(data){
			document.getElementById("loaderGif").style.display='none';
			document.getElementById("downloadWeeklyReportDiv").style.display='block';
			$('#fileNameWeeklyReportTime').html(new Date($.now()));
			$('#fileNameWeeklyReport').html(data[0].FileName);
		},
		error: function(jqXHR, textStatus)
		{
		document.getElementById("downloadWeeklyReportDiv").style.display='none';	
		alert("ERROR:"+textStatus);
		}
	});
}
function loginAsUser(username,usermail){
	$.ajax({
		type: "POST",
		url: "loginAsProxy",
		dataType: 'json',
		data: {username :username,usermail:usermail},
		complete: function() {
	        window.location.replace("homenew2");
	     },
		error: function(jqXHR, textStatus)
		{
			//window.location.replace("homenew2");
			//alert("ERROR:"+textStatus);
		}
	});
}
function calculateSumEachDay(){
	var totalWeeklyEffort = Number("0");
	 for(var l=1;l<8;l++){
			var tempPerDayEffort = Number("0");
			var rows1 = $('#tab_logic1 tr');
			for (var i = 0;i<rows1.length-1; i++) {
				var tdId = document.getElementById("taskMappingList["+ i +"].effortInHoursDay"+l);
				if(tdId != null){
					var tempDEffort = document.getElementById("taskMappingList["+ i +"].effortInHoursDay"+l).value;
					tempPerDayEffort = tempPerDayEffort + Number(tempDEffort);
					totalWeeklyEffort =totalWeeklyEffort + Number(tempDEffort);
				}
			}
			$('#totalDay'+l).html(tempPerDayEffort);
			$('#totalAllDayEffort').html(totalWeeklyEffort);
		} 
}
function openFeedBackModal(){
	$('#addFeedbackModal').modal('show');
	$("select#feedbackType").prop('selectedIndex', 0);
	$('#feedBackDesc').val('');
}


function insertNewFeedback(){
	var feedbackType = document.getElementById('feedbackType').value;
	var moduleType = document.getElementById('moduleType').value;
	var feedBackDesc = document.getElementById('feedBackDesc').value;
	var attachment = document.getElementById('feedbackAttachmentId').value;
	if(feedbackType == "select"){
		alert("Please select valid category");
		return false;
	}
	if(moduleType == "select"){
		alert("Please select valid module");
		return false;
	}
	if(feedBackDesc == "" || feedBackDesc == null || feedBackDesc == undefined || feedBackDesc.length > 500){
		alert("Please input valid description");
		return false;
	}
	$.ajax({
		type: "POST",
		url: "addNewFeedback",
		dataType: 'json',
		data:{feedbackType:feedbackType,feedBackDesc:feedBackDesc,moduleType:moduleType,attachment:attachment},
		success: function(data){
			if(data[0]){
				alert("Successfully sent. We will update you shortly!.");
				$('#addFeedbackModal').modal('hide');
			}else{
				alert("Encountered error, try after some time.");
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function userAccessManagementDiv(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewManageUserAccessDiv").style.display='block';
	document.getElementById('viewManageUserAccessDiv').setAttribute("style","min-height:550px");
	$("#viewManageUserAccessDiv").appendTo("#dataDiv");
	getGroupType('userGroupList');
	getAccessFunctionList('1');
}
function onlyUnique(value, index, self) { 
    return self.indexOf(value) === index;
}
function getAccessFunctionList(groupId){
	$.ajax({
		type: "POST",
		url: "getAllFunctionAccess",
		dataType: 'json',
		data:{groupId:groupId},
		success: function(data){
			var select = document.getElementById('userGroupList');
			for (var t=select.options.length-1;t>=0;t--) {
				if(select.options[t].value == groupId)
					select.options[t].selected = true;
			}
			document.getElementById("accessList").innerHTML = "";
			var moduleIdarr = [];
			for (var i = 0; i < data.length; i++) {
				moduleIdarr.push(data[i].modulename);
			}
			var uniqueArr = moduleIdarr.filter( onlyUnique );
			for(var k=0;k<uniqueArr.length;k++){
				$('#accessList').append($("<div class='col-lg-4 panel panel-default template'><div class='panel-heading panel-grey'>"+uniqueArr[k]+"</div><div class='panel-body' style='min-height:182px;max-height:182px;overflow:auto;padding:0px;background:#f1f1f1;border-radius:5px;'><table class='table table-striped' style='background-color:#EBF5FB;width:100%;border:none;' scrolling='auto'><thead style='background-color: #D4E6F1;'><tr><th>System Function</th><th>Access Flag</th></tr></thead><tbody id='moduleAccess"+k+"'></tbody></table></div></div>"));
				for (var i = 0; i < data.length; i++) {
					if(uniqueArr[k] == data[i].modulename){
						var row = $("<tr class='' />");
					 	$("#moduleAccess"+k).append(row); 
					 	row.append($("<td class='EWTableElements'>"+data[i].functionname+"</td>"));
					 	if(data[i].status == "true")
					 		row.append($("<td class='EWTableElements'><label class='switch'><input type='checkbox' id='chkFunc"+data[i].functionid+"' checked onchange=updateFunctionAccess('"+data[i].functionid+"');><span class='slider round'></span></label></td>"));
					 	else
					 		row.append($("<td class='EWTableElements'><label class='switch'><input type='checkbox' id='chkFunc"+data[i].functionid+"' onchange=updateFunctionAccess('"+data[i].functionid+"');><span class='slider round'></span></label></td>"));
				  }
			}
			}
		/*	var table = $("<table class='table table-bordered' style='width:100%;' scrolling='auto' id='reqFuncAccessData'>" +
					"<thead style='background-color: #30a5ff;color:white;'>" +
					"<tr><th>Function Name</th><th>Module Name</th><th>Access</th></tr>" +
					"</thead>" +
					"<tbody id='dataBody'></tbody></table>");
			$("#accessList").append(table);
			var moduleIdarr = [];
			for (var i = 0; i < data.length; i++) {
				moduleIdarr.push(data[i].modulename);
			}
			var uniqueArr = moduleIdarr.filter( onlyUnique );
		for(var k=0;k<moduleIdarr.length;k++){
				for (var i = 0; i < data.length; i++) {
				  if(uniqueArr[k] == data[i].modulename){
					  var row = "";
					if(k%2 == 0)  
						row = $("<tr style='background-color:#D4F1F1'/>");
					else
						row = $("<tr style='background-color:#D4E8F1  '/>");
					$("#dataBody").append(row);
					row.append($("<td class='EWTableElements'>"+data[i].functionname+"</td>"));
					row.append($("<td class='EWTableElements'>"+data[i].modulename+"</td>"));
				 	if(data[i].status == "true")
				 		row.append($("<td class='EWTableElements'><label class='switch'><input type='checkbox' id='chkFunc"+data[i].functionid+"' checked onchange=updateFunctionAccess('"+data[i].functionid+"');><span class='slider round'></span></label></td>"));
				 	else
				 		row.append($("<td class='EWTableElements'><label class='switch'><input type='checkbox' id='chkFunc"+data[i].functionid+"' onchange=updateFunctionAccess('"+data[i].functionid+"');><span class='slider round'></span></label></td>"));
			}
			}	
		}*/
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});	
}
function updateFunctionAccess(functionId){
	var status = false;
	if ($('#chkFunc'+functionId).is(":checked")){
	  status = true;
	}else{
	  status = false;	
	}
	var groupId = document.getElementById('userGroupList').value;
$.ajax({
	type: "POST",
	url: "updatefunctionAccessStatus",		
	dataType: 'json',
	data:{groupId:groupId,functionId:functionId,status:status},
	success: function(data){
		if(data[0]){
			alert("Group access changed successfully");
			//getAccessFunctionList('1');
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
function userGroupManagementDiv(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewGroupUserAccessDiv").style.display='block';
	document.getElementById('viewGroupUserAccessDiv').setAttribute("style","min-height:550px");
	$("#viewGroupUserAccessDiv").appendTo("#dataDiv");
	$.ajax({
		type: "POST",
		url: "getAllGroupType",
		dataType: 'json',
		success: function(data){
			document.getElementById("accessGroupList").innerHTML = "";
			var table = $("<table class='table table-stripped' style='width:100%;' scrolling='auto' id='reqNumData'>" +
					"<thead style='background-color: #D4E6F1;'>" +
					"<tr><th>Group Name</th><th>Created By</th><th>Created On</th><th>Last Modified On</th><th style='width:20%;'>Action</th></tr>" +
					"</thead>" +
					"<tbody id='dataBodyGroup'></tbody></table>");
			$("#accessGroupList").append(table);
			for (var i = 0; i < data.length; i++) {
				var row = $("<tr />");
				$("#dataBodyGroup").append(row);
				row.append($("<td class='EWTableElements'><input class='form-control' maxlength='50' id='groupId"+data[i].groupid+"' type='text' value='"+data[i].groupname+"' readonly='true' style='width:70%;'/>" +
						"<a href='#!' title='Click to Delete' onclick=removeAccessGroup('"+data[i].groupid+"');><span class='glyphicon glyphicon-trash pull-right' style='margin-left: 5px;'></span></a><a href='#!' title='Modify Bucket' onclick=editGroupName('groupId"+i+"','"+data[i].groupid+"');><span class='glyphicon glyphicon-edit pull-right'></span></a><a href='#!' title='Click to Save' onclick=updateGroupName('groupId"+i+"','"+data[i].groupid+"');><span class='glyphicon glyphicon-floppy-saved pull-right' style='margin-right: 10px;'></span></a></td>"));
				row.append($("<td class='EWTableElements'>"+data[i].createdbyfullname+"("+data[i].createdbyusercode+")</td>"));
				row.append($("<td class='EWTableElements'>"+data[i].createdon+"</td>"));
				row.append($("<td class='EWTableElements'>"+data[i].lastmodifiedon+"</td>"));
				row.append($("<td class='EWTableElements text-center'><button class='btn-primary btn-sm' onclick=updateGroupUsers('"+data[i].groupid+"');> Modify Group Users</button></td>"));
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});	
}
function createGroupName(){
	var groupName = document.getElementById("newGroupName").value;
	if(groupName == ""){
		alert("Please provide valid group name");
		return false;
	}
	var ret = confirm("Do you want to create new access group?");	
	if(ret == false)
		return false;
	$.ajax({
		type: "POST",
		url: "createNewAccessGroup",
		data:{groupName:groupName},
		dataType: 'json',
		success: function(result){
			if(result == 'true'){
					alert("Access Group has been successfully created");
					userGroupManagementDiv();
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
function updateGroupName(nameId,groupId){
	var groupName = document.getElementById(nameId).value;
	if(groupName == ""){
		alert("Please provide valid group name");
		return false;
	}
	$.ajax({
		type: "POST",
		url: "updateAccessGroupName",
		data:{groupName:groupName,groupId:groupId},
		dataType: 'json',
		success: function(result){
			if(result == 'true'){
					alert("Group Name has been successfully updated");
					userGroupManagementDiv();
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
function editGroupName(nameId,groupId){
	$("#"+nameId).prop('readonly', false);	
}
function removeAccessGroup(groupId){
	$.ajax({
		type: "POST",
		url: "deleteGroupFromApp",
		data:{groupId:groupId},
		dataType: 'json',
		success: function(result){
			if(result == 'true'){
					alert("Successfully removed");
					userGroupManagementDiv()
			}else{
				alert("One or more user exist in group!");
				return false;
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});	
}
function addRemoveUsersFromGroup1(){
	var addRemoveFlag = document.getElementById('id_addRemoveFlag').value;
	var selectedUsers = $('.ui-igcombo-hidden-field').val();
	var groupId = document.getElementById('id_groupId').value;
	$.ajax({
		type: "POST",
		url: "updateUsersInGroup",
		dataType: 'json',
		data:{groupId:groupId,selectedUsers:selectedUsers,addRemoveFlag:addRemoveFlag},
		success: function(data){
			if(data[0]){
				alert("Users updated successfully.");
			}else{
				alert("Try after some time.");
			}
			$('#addUsersToGroupModal').modal('hide');
			
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
	
}
function addRemoveUsersFromGroup(){
	var addRemoveFlag = "";
	var selectedUsers = [];
	selectedUsers = $('[name=duallistbox_demo1]').val(); 
		//document.getElementById("bootstrap-duallistbox-selected-list_duallistbox_demo1").value;
	var groupId = document.getElementById('id_groupId1').value;
	if( null == selectedUsers){
		selectedUsers = [''];
	}
	//alert(selectedUsers.length);
	$.ajax({
		type: "POST",
		url: "addRemoveUsersInGroup",
		dataType: 'json',
		data:{groupId:groupId,selectedUsers:selectedUsers,addRemoveFlag:addRemoveFlag},
		success: function(data){
			if(data[0]){
				if(selectedUsers[0] == "")
					alert("All Users in group "+ $('#groupId'+groupId).val() +" removed successfully.");
				else
					alert("Users in group "+ $('#groupId'+groupId).val() +" updated successfully.");
			}else{
				alert("Try after some time.");
			}
			$('#dragDropUserModal').modal('hide');
			
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
	
}
function resetUpdateGroupUserModal(){
	//$('[name=duallistbox_demo1]').bootstrapDualListbox('refresh');
	var groupId = document.getElementById('id_groupId1').value;
	updateGroupUsers(groupId);
}
function updateGroupUsers(groupId){
	var addRemoveFlag = "";
	$.ajax({
		type: "POST",
		url: "getUnassignedAssignedUserListBasedOnFlag",
		dataType: 'json',
		async:false,
		data:{groupId:groupId,addRemoveFlag:addRemoveFlag},
		success: function(data){
		if(data.length > 0){
			$('#dragDropUserModal').modal('show');
			$('#id_groupName1').html("Group Name :: "+$('#groupId'+groupId).val());
			$('#id_groupId1').val(groupId);
			var select = document.getElementById("duallistbox_demo11");
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].userfullname+"("+data[i].username+")",data[i].username));
				select.options[i].title = data[i].usermail;
				if(data[i].existingInGroupFlag == 'Y'){
					select.options[i].selected = true;
				}	
			 }
			$('[name=duallistbox_demo1]').bootstrapDualListbox('refresh');
			}else
			{
				alert("No data available");
				return false;
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function getApprovalPendingCount(){
	$.ajax({
		type: "POST",
		url: "getCountParameter",
		dataType: 'json',
		success: function(data){
		//if(data.length > 0){
				/*$("#approvalPendingCount").html(data[0].approvalPendingCount);
				$("#leavePendingCount").html(data[0].leaveApprovalCount);
				$("#rmsPendingCount").html(data[0].expensePendingCount);*/
			//}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});

	
}
function approveSelectedTimesheet(){
	var status = document.getElementById("timesheetFilter").value;
	var reqArray = [];
	var i = 0;
	var t = document.getElementById('tab_logic_userItemApp');
		$('#tab_logic_userItemApp tr').each(function () {
	        $(this).find('td input:checked').each(function () {
	        	//var uname = $(t.rows[i].cells[1]).text();
	        	//var weekDates = $(this).val();
	        	reqArray.push($(this).val());
	        });
	        i++;
	    });
		if(reqArray.length == 0){
			alert("Please select atleast one row");
			return false;
		}
		//alert(reqArray);
		document.getElementById("loaderGif").style.display='block';
		$.ajax({
			type: "POST",
			url: "approveMultipleTimesheet",
			dataType: 'json',
			data:{reqArray:reqArray},
			success: function(data){
				document.getElementById("loaderGif").style.display='none';	
			if(data == "true"){
					alert("Data updated successfully");
					getApprovalPendingCount();
					fetchByParam(status,'1');
				}else{
					alert("Encountered Error");
					fetchByParam(status,'1');
				}
			},
			error: function(jqXHR, textStatus)
			{
				document.getElementById("loaderGif").style.display='none';
				alert("ERROR:"+textStatus);
			}
		});
}
function uploadFeedbackAttachment(){
    var form = $('#feedbackUploadForm')[0];
    var data = new FormData(form);
    //console.log(data);
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "uploadFeedbackAttachment",
        data: data,
        processData: false,
        contentType: false,
        success: function (data) {
        	document.getElementById("feedbackDocumentList").value = '';
        	insertNewFeedback();
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}
function valFeedbackUpload(fileName,id)
{
	var pathname = document.getElementById(id).value;
	var fName = pathname.split('\\').pop().split('/').pop();
    var file_extension = fileName.split('.').pop(); 
    //alert(file_extension);
    /*if(file_extension != "pdf" || file_extension != "jpg" || file_extension != "jpeg" || file_extension != "gif" || file_extension != "png"){
        	alert("Kindly attach PDF,JPG,GIF or PNG file only");
        	document.getElementById(id).value = '';
        	return false;
        }else{*/
        	document.getElementById("feedbackAttachmentId").value = fName;
        //}
}
function viewUserAssignedTask(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("userAssignedTaskDiv").style.display='block';
	document.getElementById('userAssignedTaskDiv').setAttribute("style","min-height:550px");
	$("#userAssignedTaskDiv").appendTo("#dataDiv");
	getUserPendingTaskList();
}
function getUserPendingTaskList(){
	document.getElementById("loaderGif").style.display='block';
	$.ajax({
		type: "POST",
		url: "fetchAssignedTaskList",
		dataType: 'json',
		success: function(data){
			document.getElementById("loaderGif").style.display='none';
			$('#assignedTaskList').empty();
		if(data.length > 0){
			var divString = "";
			$("#userTotalTaskCount").html(data[data.length - 1].totalCount);
			for(var i = 0;i<data.length;i++){
				 divString = divString + "<div class='panel panel-default'>"+
								    "<div class='panel-heading' style='background-color:#f5f5f5;'>"+
								      "<h4 class='panel-title' style='color:#000;'>"+
								        "<a class='accordion-toggle' data-toggle='collapse' data-parent='#accordion' href='#collapse"+i+"'>Task "+(i+1)+":: "+
								          data[i].taskName
								        +"</a>"+
								      "</h4>"+
								    "</div>"+
								    "<div id='collapse"+i+"' class='panel-collapse collapse'>"+
								      "<div class='panel-body'>"+
								        "<label>Task Description:</label>"+data[i].taskDesc+
								        "<br /><label>Project Name:</label>"+data[i].projectName+
								        "<br /><label>Current Status:</label>"+data[i].status+
								        "<br /><label>Work Progress(in %):</label><input type='text' id='workProgress"+i+"' value='"+data[i].workProgress+"' />"+
								        "<br /><a href='#!' class='btn btn-primary' title='Update Progress' onclick=updateTaskProgress('"+data[i].taskId+"','workProgress"+i+"');>Update</a></div></div></div>";
			}
			$('#assignedTaskList').append(divString);
			/*for(var i = 0;i<data.length;i++){
				$("#progressSlider"+i).slider({
					  min: 0,
					  max: 100,
					  step: data[i].workProgress
				});
			}*/
		}else{
			$('#assignedTaskList').append("<div><span style='color:red;margin-left:10px;'>No Task has been assigned.</span></div>");
		}
		},
		error: function(jqXHR, textStatus)
		{
			document.getElementById("loaderGif").style.display='none';
			alert("ERROR:"+textStatus);
		}
	});
}
function updateTaskProgress(taskId,id){
	document.getElementById("loaderGif").style.display='block';
	var workProgress = $("#"+id).val();
	if(isNaN(workProgress)){
		alert("Invalid input for work progress.");
		return false;
	}
	$.ajax({
		type: "POST",
		url: "updateAssignedTaskProgress",
		dataType: 'json',
		data:{taskId:taskId,workProgress:workProgress},
		success: function(data){
			document.getElementById("loaderGif").style.display='none';
			alert(data);
			getUserPendingTaskList();
		},
		error: function(jqXHR, textStatus)
		{
			document.getElementById("loaderGif").style.display='none';
			alert("ERROR:"+textStatus);
		}
	});
}
function sortTable(id,n) {
	  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	  table = document.getElementById(id);
	  switching = true;
	  //Set the sorting direction to ascending:
	  dir = "asc"; 
	  /*Make a loop that will continue until
	  no switching has been done:*/
	  while (switching) {
	    //start by saying: no switching is done:
	    switching = false;
	    rows = table.getElementsByTagName("tr");
	    /*Loop through all table rows (except the
	    first, which contains table headers):*/
	    for (i = 2; i < (rows.length - 1); i++) {
	      //start by saying there should be no switching:
	      shouldSwitch = false;
	      /*Get the two elements you want to compare,
	      one from current row and one from the next:*/
	      x = rows[i].getElementsByTagName("td")[n];
	      y = rows[i + 1].getElementsByTagName("td")[n];
	      /*check if the two rows should switch place,
	      based on the direction, asc or desc:*/
	      if (dir == "asc") {
	        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
	          //if so, mark as a switch and break the loop:
	          shouldSwitch= true;
	          break;
	        }
	      } else if (dir == "desc") {
	        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
	          //if so, mark as a switch and break the loop:
	          shouldSwitch= true;
	          break;
	        }
	      }
	    }
	    if (shouldSwitch) {
	      /*If a switch has been marked, make the switch
	      and mark that a switch has been done:*/
	      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
	      switching = true;
	      //Each time a switch is done, increase this count by 1:
	      switchcount ++;      
	    } else {
	      /*If no switching has been done AND the direction is "asc",
	      set the direction to "desc" and run the while loop again.*/
	      if (switchcount == 0 && dir == "asc") {
	        dir = "desc";
	        switching = true;
	      }
	    }
	  }
	}

function viewAccessGroup(username){
	$.ajax({
		type: "POST",
		url: "fetchUserCurrentAccessGroupList",
		dataType: 'json',
		async:false,
		data:{username:username},
		success: function(data){
		if(data.length > 0){
			$('#viewAccessGroupModal').modal('show');
			$('#id_userName1').html(username);
			$('#userName1Value').val(username);
			$('#accessGroupDiv').empty();
			var table = $("<table class='table table-bordered' style='width:100%;' scrolling='auto' id='reqUsrAccessData'>" +
					"<thead style='background-color: #30a5ff;color:white;'>" +
					"<tr><th>Group Name</th><th>Access</th></tr>" +
					"</thead>" +
					"<tbody id='dataBody12'></tbody></table>");
			$("#accessGroupDiv").append(table);
				for (var i = 0; i < data.length; i++) {
					  var row = "";
					if(i%2 == 0)  
						row = $("<tr class=''/>");
					else
						row = $("<tr class='info'/>");
					$("#dataBody12").append(row);
					row.append($("<td class='EWTableElements'>"+data[i].groupName+"</td>"));
				 	if(data[i].assignedFlag == "Y")
				 		row.append($("<td class='EWTableElements' style='line-height:14px;'><label class='switch' style='margin-bottom:0px;'><input type='checkbox' id='group"+data[i].groupId+"' checked onchange=updateGrpAccess('"+data[i].groupId+"','"+username+"');><span class='slider round'></span></label></td>"));
				 	else
				 		row.append($("<td class='EWTableElements' style='line-height:14px;'><label class='switch' style='margin-bottom:0px;'><input type='checkbox' id='group"+data[i].groupId+"' onchange=updateGrpAccess('"+data[i].groupId+"','"+username+"');><span class='slider round'></span></label></td>"));
				}
			}else{
					alert("No data available");
					return false;
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function updateGrpAccess(groupId,username){
	var status = false;
	if ($('#group'+groupId).is(":checked")){
	  status = true;
	}
	$.ajax({
		type: "POST",
		url: "updateUsrGroupAccessStatus",		
		dataType: 'json',
		data:{groupId:groupId,username:username,status:status},
		success: function(data){
			if(data[0]){
				alert("User Group access changed successfully");
				viewAccessGroup(username);
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
function populatePolicyGroup(id){
	$.ajax({
		type: "POST",
		url: "getValuesBasedOnCategory",
		dataType: 'json',
		data:{category:'HR Policy Group'},
		success: function(data){
			var select = document.getElementById(id);
			//alert(select);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Policy Group',''));
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].values,data[i].values));
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function openNewRequest(url,params){
	var winName='eManagerPage';
	var top=0;
    var left=0;
    var height=screen.availHeight;
    var width=screen.availWidth;	
	  var windowoption="resizable=yes,height="+height+",width="+width+",location=0,menubar=0,scrollbars=1,top="+top+",left="+left;
	  var form = document.createElement("form");
	  form.setAttribute("method", "POST");
	  form.setAttribute("action", url);
	  form.setAttribute("target",'eManagerPage');  
	  for (var i in params) {
	    if (params.hasOwnProperty(i)) {
	      var input = document.createElement('input');
	      input.type = 'hidden';
	      input.name = i;
	      input.value = params[i];
	      form.appendChild(input);
	    }
	  }              
	  document.body.appendChild(form);                       
	  window.open('', winName,windowoption);
	  form.target = winName;
	  form.submit();                 
	  document.body.removeChild(form);
}
function showAlert(message,alertType){
	var divElem = $("#success-alert");
	if(alertType == "SUCCESS"){
		divElem.addClass("alert-success");
	}
	if(alertType == "INFO"){
	    divElem.addClass("alert-info");	
	}
	if(alertType == "ERROR"){
	    divElem.addClass("alert-error");
	}
	if(alertType == "WARNING"){
		divElem.addClass("alert-warning");
	}
	$("#success-alert").fadeTo(5000, 500).slideUp(500, function(){
        $("#success-alert").slideUp(5000);
	});
	$("#alertMessageheadId").html(alertType+"!");
	$("#alertMessageId").html(message);
}