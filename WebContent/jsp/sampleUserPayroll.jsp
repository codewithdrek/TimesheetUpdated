<div>
<div class="panel-group" id="accordion" style="margin-right:10px;">
    <div class="panel panel-default">
      <div class="panel-heading" style="background:#30a5ff;">
        <h4 data-toggle="collapse" data-parent="#accordion" href="#collapseEmpInfo" class="panel-title expand">
           <div class="right-arrow pull-right">+</div>
          <a href="#">Employee Information</a>
        </h4>
      </div>
      <div id="collapseEmpInfo" class="panel-collapse collapse">
        <div class="panel-body">
        	<div class="col-md-12">
	        	<div class="col-md-6 form-group">
	        		<label>Employee Code:</label>
	        		<span class="pull-right">${sessionScope.loggedInUserCode}</span>
	        		<input type="hidden" id="selectedUserForAssignmentHidden" value=""/>
	        	</div>
	        	<div class="col-md-6 form-group">	
	        		<label>Employee Full Name:</label>
	        		<span class="pull-right">${sessionScope.loggedInUserFullName}</span>
	        	</div>
	        	<div class="col-md-6 form-group">	
	        		<label>UAN:</label>
	        		<span class="pull-right">8976756******456</span>
	        	</div>
	        	<div class="col-md-6 form-group">	
	        		<label>PAN:</label>
	        		<span class="pull-right">JKYUH****T</span>
	        	</div>	
        	</div>
        </div>
      </div>
     </div>
  <div class="panel panel-default">
      <div class="panel-heading" style="background:#30a5ff;">
        <h4 data-toggle="collapse" data-parent="#accordion" href="#collapseEmpAssignment" class="panel-title expand">
           <div class="right-arrow pull-right">+</div>
          <a href="#">Employee Payroll Assignment</a>
        </h4>
      </div>
      <div id="collapseEmpAssignment" class="panel-collapse collapse">
        <div class="panel-body">
        	<div class="row">
				<div class="col-sm-3 form-group">
					<label>Payroll Start Date</label><br>
					<input type="text" name="empAllotedPayrollStartDate" id="empAllotedPayrollStartDate" class="form-control input-sm" onkeypress="return false;"/>
					<a href="#l" onclick="populateUserPayrollInfoDiv('empAllotedPayrollId','USER_PAYROLL','USER_PAYROLL',this.id);" id="btnUserPayrollStartDateId">
			          <span class="glyphicon glyphicon-floppy-disk"></span>
			        </a>
				</div>
				<div class="col-sm-3 form-group">
					<label>Payroll End Date</label><br>
					<input type="text" name="empAllotedPayrollEndDate" id="empAllotedPayrollEndDate" class="form-control input-sm" onkeypress="return false;"/>
					<a href="#l" onclick="populateUserPayrollInfoDiv('empAllotedPayrollId','USER_PAYROLL','USER_PAYROLL',this.id);" id="btnUserPayrollEndDateId" >
			          <span class="glyphicon glyphicon-floppy-disk"></span>
			        </a>
				</div>
				<div class="col-sm-3 form-group">
					<label>Assigned Payroll</label><br>
					<input type="text" name="empAllotedPayroll" id="empAllotedPayroll" class="form-control" readOnly="true"/>
					<input type="hidden" name="empAllotedPayrollId" id="empAllotedPayrollId" />
					<a href="#l" onclick="populateUserPayrollInfoDiv('empAllotedPayrollId','USER_PAYROLL','USER_PAYROLL',this.id);" id="btnUserPayrollId">
			          <span class="glyphicon glyphicon-pencil"></span>
			        </a>
				</div>
				<div class="col-sm-3 form-group">
					<label>Employee Assignment</label><br>
					<input type="text" name="empAllotedAssignment" id="empAllotedAssignment" class="form-control" readOnly="true"/>
					<a href="#l" onclick="populateUserPayrollInfoDiv('USER_PAYROLL_ASSIGNMENT','USER_PAYROLL_ASSIGNMENT',this.id);" id="btnUserPayrollAssignId">
			          <span class="glyphicon glyphicon-pencil"></span>
			        </a>
			        <a href="#l" onclick="addNewUserPayrollAssignmentDiv();" id="btnUserAddPayrollAssignId">
			          <span class="glyphicon glyphicon-plus" style="color:green;"></span>
			        </a>
				</div>
			</div>				
        </div>
      </div>
     </div>
     <div class="panel panel-default">
      <div class="panel-heading" style="background:#30a5ff;">
        <h4 data-toggle="collapse" data-parent="#accordion" href="#collapseEmpElement" class="panel-title expand">
           <div class="right-arrow pull-right">+</div>
          <a href="#">Update/Add Payroll Element</a>
        </h4>
      </div>
      <div id="collapseEmpElement" class="panel-collapse collapse">
        <div class="panel-body">
        	<div class="row" id="empCurrAssignedElement">
        		<table class="table table-border" id="payrollAllotedElemTbl"><tbody id="tab_logic_payrollAllotedElementItemApp"></tbody></table>
        	</div>
        	<div class="row" id="empAssignElement">
        		<label>Assign Element Group to User</label>
        		<select class="form-control" id="addFromAvailableElementGroup"><option value="">Select Group</option></select>
        		<button class="btn btn-sm btn-info" onclick="assignAvailableElementGroup();">Add</button>
        	</div>				
        </div>
      </div>
     </div>
     <div class="panel panel-default">
      <div class="panel-heading" style="background:#30a5ff;">
        <h4 data-toggle="collapse" data-parent="#accordion" href="#collapseEmpAssignmentHistory" class="panel-title expand">
           <div class="right-arrow pull-right">+</div>
          <a href="#">Payroll Assignment History</a>
        </h4>
      </div>
      <div id="collapseEmpAssignmentHistory" class="panel-collapse collapse">
        <div class="panel-body">
        	<div class="row">
				<table>
				
				</table>
			</div>				
        </div>
      </div>
     </div>
</div>    
</div>  
<%-- <div class="col-md-2"></div>
<div class="col-md-12">
    <!-- <div style="height: 20px"></div> -->
    <table class="table table-striped" border="2">
        <tr>
            <td colspan="2">
                <b>CTC:</b>
                <input type="text" class="form-inline form-control input-sm" placeholde="Employee Current CTC" />
                <button class="btn btn-sm btn-success">Go</button>
                <select class="form-control pull-right">
                	<option>CTC History</option>
                	<option>9LPA</option>
                	<option>8LPA</option>
                </select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td class="text-right">Tax Year:<input type="hidden" name="taxyear"><b>2017-18</b></td>
        </tr>
        <tr>
            <td>Employee Code:<input type="hidden" name="ecode"><b class="pull-right">${sessionScope.loggedInUser}</b></td>
            <td>Start Date:<input type="text" class="form-control input-sm pull-right" name="payperiod" value="********"></td>
        </tr>
        <tr>
            <td>Employee Name:<input type="hidden" name="ename"><b class="pull-right">${sessionScope.loggedInUserFullName}</b></td>
            <td>End Date:<input type="text" class="form-control input-sm pull-right" name="paydate" value="********"></td>
        </tr>
        <tr>
            <td>UAN:<input type="text" class="form-control input-sm pull-right" name="paydate" value="********"></td>
            <td></td>
        </tr>
        <tr>
            <td>PAN:<input type="text" class="form-control input-sm pull-right" name="paydate" value="********"></td>
            <td></td>
        </tr>
        <tr>
            <td colspan="2">
                <table class="table table-hover">
                <caption style="text-align:left;"><h4><strong>Element Group 1</strong></h4></caption>
                    <theader>
                    <tr>
                        <th>Element Name</th>
                        <th>Formula</th>
                        <th style="text-align:right;">Calculated Amount</th>
                    </tr>
                    </theader>
                    <tbody>
                    <tr>
                        <td>Basic pay</td>
                        <td>
                        <span class="form-control" style="border:1px solid gray;">@{CTC} % 5 </span>
                        <button class="glyphicon glyphicon-edit" title="Edit Formula" style="background-color:#fff;margin-top:5px;border:none;"></button>
                        </td>
                        <td><input type="text" class="form-control input-sm pull-right" name="basicpay" value="0.0"/></td>
                    </tr>
                    <tr>
                        <td>HRA</td>
                        <td>
                        <span class="form-control" style="border:1px solid gray;">@{BASIC} % 40 </span>
                        <button class="glyphicon glyphicon-edit" title="Edit Formula" style="background-color:#fff;margin-top:5px;border:none;"></button>
                        </td>
                        <td><input type="text" class="form-control input-sm pull-right" name="basicpay" value="0.0"/></td>
                    </tr>
                    <tr>
                        <td>Conveyance Allowance</td>
                        <td>
                        <span class="form-control" style="border:1px solid gray;">@{BASIC} % 40 </span>
                        <button class="glyphicon glyphicon-edit" title="Edit Formula" style="background-color:#fff;margin-top:5px;border:none;"></button>
                        </td>
                        <td><input type="text" class="form-control input-sm pull-right" name="basicpay" value="0.0"/></td>
                    </tr>
                    <tr>
                        <td>Meal Coupons</td>
                        <td>
                        <span class="form-control" style="border:1px solid gray;">@{BASIC} % 40 </span>
                        <button class="glyphicon glyphicon-edit" title="Edit Formula" style="background-color:#fff;margin-top:5px;border:none;"></button>
                        </td>
                        <td><input type="text" class="form-control input-sm pull-right" name="basicpay" value="0.0"/></td>
                    </tr>
                    <tr>
                        <td>LTA</td>
                        <td>
                        <span class="form-control" style="border:1px solid gray;">@{BASIC} % 40 </span>
                        <button class="glyphicon glyphicon-edit" title="Edit Formula" style="background-color:#fff;margin-top:5px;border:none;"></button>
                        </td>
                        <td><input type="text" class="form-control input-sm pull-right" name="basicpay" value="0.0"/></td>
                    </tr>
                    </tbody>
                    </td>
               </table>
              </td> 
        </tr>
        <tr>
            <td colspan="2">
                <table class="table table-hover">
                <caption style="text-align:left;"><h4><strong>Element Group 2</strong></h4></caption>
                    <theader>
                    <tr>
                        <th>Element Name</th>
                        <th>Formula</th>
                        <th style="text-align:right;">Calculated Amount</th>
                    </tr>
                    </theader>
                    <tbody>
                    <tr>
                        <td>Travel Allowance</td>
                        <td>
                        <span class="form-control" style="border:1px solid gray;">@{BASIC} % 1 </span>
                        <button class="glyphicon glyphicon-edit" title="Edit Formula" style="background-color:#fff;margin-top:5px;border:none;"></button>
                        </td>
                        <td><input type="text" class="form-control input-sm pull-right" name="basicpay" value="0.0"/></td>
                    </tr>
                    <tr>
                        <td>Uniform</td>
                        <td>
                        <span class="form-control" style="border:1px solid gray;">@{BASIC} % 3 </span>
                        <button class="glyphicon glyphicon-edit" title="Edit Formula" style="background-color:#fff;margin-top:5px;border:none;"></button>
                        </td>
                        <td><input type="text" class="form-control input-sm pull-right" name="basicpay" value="0.0"/></td>
                    </tr>
                    <tr>
                        <td>Maternity Allowance</td>
                        <td>
                        <span class="form-control" style="border:1px solid gray;">@{BASIC} % 2 </span>
                        <button class="glyphicon glyphicon-edit" title="Edit Formula" style="background-color:#fff;margin-top:5px;border:none;"></button>
                        </td>
                        <td><input type="text" class="form-control input-sm pull-right" name="basicpay" value="0.0"/></td>
                    </tr>
                    <tr>
                        <td>Meal Coupons</td>
                        <td>
                        <span class="form-control" style="border:1px solid gray;">@{BASIC} % 4 </span>
                        <button class="glyphicon glyphicon-edit" title="Edit Formula" style="background-color:#fff;margin-top:5px;border:none;"></button>
                        </td>
                        <td><input type="text" class="form-control input-sm pull-right" name="basicpay" value="0.0"/></td>
                    </tr>
                    <tr>
                        <td>Advance Salary</td>
                        <td>
                        <span class="form-control" style="border:1px solid gray;">Fixed </span>
                        <button class="glyphicon glyphicon-edit" title="Edit Formula" style="background-color:#fff;margin-top:5px;border:none;"></button>
                        </td>
                        <td><input type="text" class="form-control input-sm pull-right" name="basicpay" value="0.0"/></td>
                    </tr>
                    </tbody>
                    </td>
               </table>

            </td>
        </tr>
    </table>
</div>

<div class="row">
    <button type="button" class="btn btn-success" style="margin:5px;margin-left:30px;">Save</button>
    <button type="button" class="btn btn-danger" style="margin:5px;">Reset</button>
    <button type="button" class="btn btn-primary" style="margin:5px;">Print</button>
    <button type="button" class="btn btn-primary" style="margin:5px;">Download(PDF/Excel)</button>
</div>
</div> --%>
<div id="editUserPayrollInfoDiv" style="display:none;width:auto;max-height:300px;z-index:100;overflow-y:auto;padding:5px;border:1px solid #000;border-radius:5px;"></div>
<div class="modal fade" id="addUserPayrollAssignmentModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Create New Assignment for User</h4>
				        </div>
				        <div class="modal-body">
				        <div class="form-group">
				          <label>Gross Pay*:</label>
				          <input class="input-md  textinput textInput form-control" id="id_newCTC" maxlength="10" minlength="3" placeholder="Enter gross CTC here..." style="margin-bottom: 10px" type="text" />
				        </div>
				        <div class="form-group">  
				          <label>Active Flag*</label>
				          <select id="id_assignmentStatus" class="form-control" style="margin-bottom: 10px">
				          		<option value="Y">Yes</option>
				          		<option value="N">No</option>
				          </select>
				        </div>
				        <div class="form-group">
				          <label>Start Date*:</label>
				          <input class="input-md  textinput textInput form-control" id="id_newCTCStartDate" maxlength="10" minlength="3" placeholder="Start Date" style="margin-bottom: 10px" type="text" />
				        </div>
				        <div class="form-group">
				          <label>End Date*:</label>
				          <input class="input-md  textinput textInput form-control" id="id_newCTCEndDate" maxlength="10" minlength="3" placeholder="End Date" style="margin-bottom: 10px" type="text" />
				        </div>
				        </div>
				        <div class="modal-footer">
	    			      <button type="button" class="btn btn-success" id="createNewAssignment" onclick="saveNewUserPayrollAssignment();">Save</button>
				          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div>
				        </div>
				       </div>
				 </div>
<script type="text/javascript">
	$(function() {
	  $(".expand").on( "click", function() {
	    // $(this).next().slideToggle(200);
	    $expand = $(this).find(">:first-child");
	    
	    if($expand.text() == "+") {
	      $expand.text("-");
	    } else {
	      $expand.text("+");
	    }
	  });
	});
</script>