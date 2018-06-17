<div>
 <!-- <div class="col-md-5"></div> -->
<div class="col-md-2"></div>
<div class="col-md-12">
    <!-- <div style="height: 20px"></div> -->
    <table class="table table-striped" border="2">
        <tr>
            <td colspan="2" class="text-center">
                <b>XYZ INDIA TECH PRIVATE LIMITED - PAYSLIP for the Month of ****<br>
				   *****, ****, PHASE -** Gautam Budh Nagar, Noida - 2****5 Uttar Pradesh<br>
				   Phone : 0120-4****0 e-Mail : xxxx@SupraITS.com</b>
            </td>
        </tr>
        <tr>
            <td></td>
            <td class="text-right">Tax Year:<input type="hidden" name="taxyear"><b>2017-18</b></td>
        </tr>
        <tr>
            <td>Employee Code:<input type="hidden" name="ecode"><b class="pull-right">${sessionScope.loggedInUser}</b></td>
            <td>Pay Period:<input type="hidden" name="payperiod"></td>
        </tr>
        <tr>
            <td>Employee Name:<input type="hidden" name="ename"><b class="pull-right">${sessionScope.loggedInUserFullName}</b></td>
            <td>Pay Date:<input type="hidden" name="paydate"></td>
        </tr>
        <tr>
            <td>UAN:<input type="hidden" name="ename"><b>******</b></td>
            <td></td>
        </tr>
        <tr>
            <td>PAN:<input type="hidden" name="ename"><b>******</b></td>
            <td></td>
        </tr>
        <tr>
            <td>
                <table class="table table-hover">
                    <tbody>
                    <tr>
                        <th>Earnings</th>
                        <th>Amount</th>
                    </tr>
                    <tr>
                        <td>Basic pay</td>
                        <td><input type="hidden" name="basicpay"></td>
                    </tr>
                    <tr>
                        <td>HRA</td>
                        <td><input type="hidden" name="hra"></td>
                    </tr>
                    <tr>
                        <td>Conveyance Allowance</td>
                        <td><input type="hidden" name="hra"></td>
                    </tr>
                    <tr>
                        <td>Meal Coupons</td>
                        <td><input type="hidden" name="hra"></td>
                    </tr>
                    <tr>
                        <td>LTA</td>
                        <td><input type="hidden" name="hra"></td>
                    </tr>
                    </tbody>
                    </td></table>
            <td>
                <table class="table table-hover">
                    <thead></thead>
                    <tbody>
                    <tr>
                        <th>Deductions</th>
                        <th>Amount</th>
                    </tr>
                    <tr>
                        <td>Prof. Tax</td>
                        <td><input type="hidden" name="proftax"></td>
                    </tr>
                    <tr>
                        <td>Tax</td>
                        <td><input type="hidden" name="tax"></td>
                    </tr>
                    <tr>
                        <td>Loan / Salary Advance</td>
                        <td><input type="hidden" name="tax"></td>
                    </tr>
                    </tbody>
                </table>

            </td>
        </tr>
        <tr>
            <td>
                <table class="table table-hover">
                    <thead>Amount Paid</thead>
                    <tbody>
                    <tr>
                        <td>Earnings</td>
                        <td><input type="hidden" name="earnings"></td>
                    </tr>
                    <tr>
                        <td>Deductions</td>
                        <td><input type="hidden" name="deductions"></td>
                    </tr>
                    <tr>
                        <td>Net Pay</td>
                        <td><input type="hidden" name="netpay"></td>
                    </tr>
                    </tbody>
                </table>

            </td>
            <td>
                <table class="table table-hover">
                    <thead>ER Contributions</thead>
                    <tbody>
                    <tr>
                        <td>Contribution1</td>
                        <td><input type="hidden" name="contibution1"></td>
                    </tr>
                    </tbody>
                </table>

            </td>
        </tr>
    </table>
</div>

<div class=""row>
    <div class="col-md-6" align="right"><button type="button" class="btn btn-primary active"  >Print</button></div>
    <div class="col-md-6"><button type="button" class="btn btn-primary active" >Download</button></div>
</div>
</div>