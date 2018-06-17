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
<title>Attendance System SupraITS:User Yearly Attendance View</title>
	<link href='<c:url value="/resources/css/bootstrap.min.css" />' rel="stylesheet">
	<link href="<c:url value="/resources/css/datepicker3.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/bootstrap-year-calendar.min.css" />" rel="stylesheet">
	
	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-year-calendar.min.js" />"></script>
</head>
<body style="padding-top: 10px;">
<div class="sub-content-container">
<div>
	<h4 class="sub-content-title" style="margin-left:10px;">
		Username:<b>${username}</b> &nbsp;&nbsp;
		Emp Name:<b>${fullname}</b> &nbsp;&nbsp;
		RM:<b>${RM}</b> &nbsp;&nbsp;
		Report Year:<b>${reportyear}</b> &nbsp;&nbsp;
	</h4>
</div>
<div>
<p id="toggle">
    <a href="#!" class="btn btn-primary" style="margin-left:10px;"> Calendar View </a>
    <a href="#!" class="btn btn-primary"> Grid View </a>
</p>
</div>
<br />
<div id="calendar" class="calendar" style="border:1px grey;"></div>
<div id="calendars" class="calendars" style="display:none;"></div>
<div class="panel panel-default">
	<table class='table table-bordered'>
		<tr>
			<th style="background-color:#FC766F;">Absent</th>
			<th style="background-color:#04c849;">Present(Extra)</th>
			<th style="background-color:#1efa6b;">Present(Deficient)</th>
			<th style="background-color:#7C6FFC;">Holiday</th>
			<th style="background-color:#FCFA6F;">On Leave::<span id="leaveCount">0</span></th>
			<th style="background-color:#FC6FED">Leave Applied</th>
			<th style="background-color:#AED6F1;">Week Off</th>
		</tr>	
	<tbody>
	</tbody>	
	</table>
</div>
</div>
<script type="text/javascript">
	var dataSource1 = '${datasource}';
	var getUrlParameter = function getUrlParameter(sParam) {
	    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
	        sURLVariables = sPageURL.split('&'),
	        sParameterName,
	        i;

	    for (i = 0; i < sURLVariables.length; i++) {
	        sParameterName = sURLVariables[i].split('=');

	        if (sParameterName[0] === sParam) {
	            return sParameterName[1] === undefined ? true : sParameterName[1];
	        }
	    }
	};
	var weeks = "";
	for(var i=0;i<6;i++){
		weeks = weeks + "<th style='background-color:#3498DB;'>Su</th><th>Mo</th><th>Tu</th><th>We</th><th>Th</th><th>Fr</th><th style='background-color:#3498DB;'>Sa</th>";
	}
	var table = $("<table class='table table-bordered' style='background-color: #EBF5FB;' scrolling='auto'><thead style='background-color: #63B9F3;'><tr><th>Month</th>"+weeks+"</tr></thead><tbody id='reqNumApproveAttendenca'></tbody></table>");
	for(var k=0;k<12;k++){
		var row = '';
	    	row = $("<tr />");
		$("#reqNumApproveAttendenca").append(row);
		if(k==0)
			row.append($("<td class='EWTableElements' style='background-color:#768DF9;'><b>Jan</b></td>"));
		if(k==1)
			row.append($("<td class='EWTableElements' style='background-color:#768DF9;'><b>Feb</b></td>"));
		if(k==2)
			row.append($("<td class='EWTableElements' style='background-color:#768DF9;'><b>Mar</b></td>"));
		if(k==3)
			row.append($("<td class='EWTableElements' style='background-color:#9CABEF;'><b>Apr</b></td>"));
		if(k==4)
			row.append($("<td class='EWTableElements' style='background-color:#9CABEF;'><b>May</b></td>"));
		if(k==5)
			row.append($("<td class='EWTableElements' style='background-color:#9CABEF;'><b>Jun</b></td>"));
		if(k==6)
			row.append($("<td class='EWTableElements' style='background-color:#B1BAE1;'><b>Jul</b></td>"));
		if(k==7)
			row.append($("<td class='EWTableElements' style='background-color:#B1BAE1;'><b>Aug</b></td>"));
		if(k==8)
			row.append($("<td class='EWTableElements' style='background-color:#B1BAE1;'><b>Sep</b></td>"));
		if(k==9)
			row.append($("<td class='EWTableElements' style='background-color:#C4C8DA;'><b>Oct</b></td>"));
		if(k==10)
			row.append($("<td class='EWTableElements' style='background-color:#C4C8DA;'><b>Nov</b></td>"));
		if(k==11)
			row.append($("<td class='EWTableElements' style='background-color:#C4C8DA;'><b>Dec</b></td>"));
		var years = getUrlParameter('year');
		var days = new Date(years, (k+1), 0).getDate();
		var firstDays = new Date(years,(k), 1);
		var firstDay = firstDays.getDay();
		for(var z=0;z<firstDay;z++){
			row.append($("<td class='EWTableElements'></td>"));
		}
		for(var l=0;l<days;l++){
			var dateId = years+"-"+('0' + (k+1)).slice(-2)+"-"+('0' + (l+1)).slice(-2);
			row.append($("<td class='EWTableElements' id='Date"+dateId+"'><a href='#!' class='popovert' id='D"+dateId+"' title='"+dateId+"' data-toggle='popover' data-trigger='focus' style='color:black;' onclick=getContent(this.id)>"+(l+1)+"</a></td>"));
		}
		table.append(row);
	}
	$("#calendars").empty();
	$('#calendars').append(table);
	var ds = JSON.parse(dataSource1);
	var leaveCount = 0;
	 for(var u=0;u<ds.length;u++){
		 var dated = ds[u].date;
		 //console.log(dated);
		 var elem = "Date"+dated;
		if(ds[u].status == "Absent"){
			document.getElementById(elem).setAttribute("style","background-color:#FC766F;");
		}else if(ds[u].status == "Week Off"){
			document.getElementById(elem).setAttribute("style","background-color:#AED6F1;");					
		}else if(ds[u].status == "Present"){
			if(ds[u].deficienthours == undefined)
				document.getElementById(elem).setAttribute("style","background-color:#04c849;");
			else
				document.getElementById(elem).setAttribute("style","background-color:#1efa6b;");
		}else if(ds[u].status == "Holiday"){
			document.getElementById(elem).setAttribute("style","background-color:#7C6FFC");
		}else if(ds[u].status == "Leave Applied"){
			document.getElementById(elem).setAttribute("style","background-color:#FC6FED");
		}else if(ds[u].status == "On Leave"){
			document.getElementById(elem).setAttribute("style","background-color:#FCFA6F");
			leaveCount = leaveCount + 1;
		}
	}
	 $("#leaveCount").html(leaveCount);
	 $(document).ready(function(){
		    $('[data-toggle="popover"]').popover();
		    /* $(".popovert").popover({
				  content: getContent(this.id),
				  placement: "bottom"
			 }); */
		    $('#toggle > a').click(function() {
		        var ix = $(this).index();
		        
		        $('#calendar').toggle( ix === 0 );
		        $('#calendars').toggle( ix === 1 );
		    });
	 });
	 function getContent(id){
		 var ds2 = JSON.parse(dataSource1);
		 var data = "";
		 //console.log("date="+id);
		 for(var u=0;u<ds2.length;u++){
			 var dateId = "D" + ds2[u].date;
			 //console.log("dataid="+dateId);
			 if(id == dateId){
				 data = "In Time: " + ds2[u].intime +"\n"+
				 		"Out Time: " + ds2[u].outtime;	
			 }
		 }
		 //console.log("data="+data); 
		 //var popover = $("#"+id).data('bs.popover');
		 //popover.options.content = data;
		 //console.log("data="+data);
		 $("#"+id).attr('data-html',true);
		 $("#"+id).attr('data-content', data);
		 $("#"+id).popover("show");
		 //return data;
	 }
</script>
<script type="text/javascript">
var dataSourceN = '${datasource}';
var ds4 = JSON.parse(dataSourceN);
var currentYear = getUrlParameter('year');
var ds3 = [ ];
    for(var i=0;i<ds4.length;i++){
    	var color = "";
    	if(ds4[i].status == "Absent"){
    		color = "#FC766F";
		}else if(ds4[i].status == "Week Off"){
			color = "#AED6F1";
		}else if(ds4[i].status == "Present"){
			if(ds4[i].deficienthours == undefined)
				color = "#04c849";
			else{
				color = "#1efa6b";
			}	
		}else if(ds4[i].status == "Holiday"){
			color = "#7C6FFC";
		}else if(ds4[i].status == "Leave Applied"){
			color = "#FC6FED";
		}else if(ds4[i].status == "On Leave"){
			color = "#FCFA6F";
		}
    	ds3.push({
    		id: i,
            name: ds4[i].date,
            location: "Intime:"+ds4[i].intime +" Outtime:"+ds4[i].outtime,
            startDate: new Date("'"+ds4[i].date+"'"),
            endDate: new Date("'"+ds4[i].date+"'"),
            color:color
    	});	
    }   
function editEvent(event) {
    $('#event-modal input[name="event-index"]').val(event ? event.id : '');
    $('#event-modal input[name="event-name"]').val(event ? event.name : '');
    $('#event-modal input[name="event-location"]').val(event ? event.location : '');
    $('#event-modal input[name="event-start-date"]').datepicker('update', event ? event.startDate : '');
    $('#event-modal input[name="event-end-date"]').datepicker('update', event ? event.endDate : '');
    $('#event-modal').modal();
}

function deleteEvent(event) {
    var dataSource = $('#calendar').data('calendar').getDataSource();

    for(var i in dataSource) {
        if(dataSource[i].id == event.id) {
            dataSource.splice(i, 1);
            break;
        }
    }
    
    $('#calendar').data('calendar').setDataSource(dataSource);
}

function saveEvent() {
    var event = {
        id: $('#event-modal input[name="event-index"]').val(),
        name: $('#event-modal input[name="event-name"]').val(),
        location: $('#event-modal input[name="event-location"]').val(),
        startDate: $('#event-modal input[name="event-start-date"]').datepicker('getDate'),
        endDate: $('#event-modal input[name="event-end-date"]').datepicker('getDate')
    }
    
    var dataSource = $('#calendar').data('calendar').getDataSource();

    if(event.id) {
        for(var i in dataSource) {
            if(dataSource[i].id == event.id) {
                dataSource[i].name = event.name;
                dataSource[i].location = event.location;
                dataSource[i].startDate = event.startDate;
                dataSource[i].endDate = event.endDate;
            }
        }
    }
    else
    {
        var newId = 0;
        for(var i in dataSource) {
            if(dataSource[i].id > newId) {
                newId = dataSource[i].id;
            }
        }
        
        newId++;
        event.id = newId;
    
        dataSource.push(event);
    }
    
    $('#calendar').data('calendar').setDataSource(dataSource);
    //$('#calendar').data('calendar').setYear(currentYear);
    $('#event-modal').modal('hide');
}

$(function() {
    //var currentYear = getUrlParameter('year');

    $('#calendar').calendar({ 
        enableContextMenu: true,
        enableRangeSelection: true,
        contextMenuItems:[
            {
                text: 'Update',
                click: editEvent
            },
            {
                text: 'Delete',
                click: deleteEvent
            }
        ],
        selectRange: function(e) {
            editEvent({ startDate: e.startDate, endDate: e.endDate });
        },
        mouseOnDay: function(e) {
            if(e.events.length > 0) {
                var content = '';
                
                for(var i in e.events) {
                    content += '<div class="event-tooltip-content">'
                                    + '<div class="event-name" style="color:' + e.events[i].color + '">' + e.events[i].name + '</div>'
                                    + '<div class="event-location">' + e.events[i].location + '</div>'
                                + '</div>';
                }
            
                $(e.element).popover({ 
                    trigger: 'manual',
                    container: 'body',
                    html:true,
                    content: content
                });
                
                $(e.element).popover('show');
            }
        },
        mouseOutDay: function(e) {
            if(e.events.length > 0) {
                $(e.element).popover('hide');
            }
        },
        dayContextMenu: function(e) {
            $(e.element).popover('hide');
        },
        /* setStyle: function(style, preventRendering) {
            this.options.style = style == 'background' || style == 'border' || style == 'custom' ? style : 'custom';

            if (!preventRendering) {
              this.render();
            }
        }, */
        dataSource: ds3
    });
    $('#calendar').data('calendar').setYear(currentYear);
    $('#save-event').click(function() {
        saveEvent();
    });
});
</script>
<style>
.calendar-header{
	display:none;
}
</style>
</body>
</html>
