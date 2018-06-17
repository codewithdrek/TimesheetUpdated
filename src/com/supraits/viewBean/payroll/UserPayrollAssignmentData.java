package com.supraits.viewBean.payroll;

import java.io.Serializable;
import java.util.Date;


public class UserPayrollAssignmentData implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private double activeCTC;
	private Date startDate;
	private Date endDate;
	private String empAllotedPayroll;
	private Integer empAllotedPayrollId;
	public double getActiveCTC() {
		return activeCTC;
	}
	public void setActiveCTC(double activeCTC) {
		this.activeCTC = activeCTC;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getEmpAllotedPayroll() {
		return empAllotedPayroll;
	}
	public void setEmpAllotedPayroll(String empAllotedPayroll) {
		this.empAllotedPayroll = empAllotedPayroll;
	}
	public Integer getEmpAllotedPayrollId() {
		return empAllotedPayrollId;
	}
	public void setEmpAllotedPayrollId(Integer empAllotedPayrollId) {
		this.empAllotedPayrollId = empAllotedPayrollId;
	}
}
