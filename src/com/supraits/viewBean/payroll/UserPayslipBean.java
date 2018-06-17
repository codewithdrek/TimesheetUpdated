package com.supraits.viewBean.payroll;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.supraits.viewBean.payroll.SesM11EmpElement;

@Component
public class UserPayslipBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer empCode;
	private String username;
	private String userfullname;
	private Date startDate;
	private Date endDate;
	private double grossPay;
	private double totalDeduction;
	private double netPay;
	private List<SesM11EmpElement> empELementList;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserfullname() {
		return userfullname;
	}
	public void setUserfullname(String userfullname) {
		this.userfullname = userfullname;
	}
	public Integer getEmpCode() {
		return empCode;
	}
	public void setEmpCode(Integer empCode) {
		this.empCode = empCode;
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
	public double getGrossPay() {
		return grossPay;
	}
	public void setGrossPay(double grossPay) {
		this.grossPay = grossPay;
	}
	public double getTotalDeduction() {
		return totalDeduction;
	}
	public void setTotalDeduction(double totalDeduction) {
		this.totalDeduction = totalDeduction;
	}
	public double getNetPay() {
		return netPay;
	}
	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}
	public List<SesM11EmpElement> getEmpELementList() {
		return empELementList;
	}
	public void setEmpELementList(List<SesM11EmpElement> empELementList) {
		this.empELementList = empELementList;
	}
	
	
	
	
}
