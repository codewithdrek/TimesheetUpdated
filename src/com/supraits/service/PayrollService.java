package com.supraits.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import com.supraits.viewBean.payroll.SesM11Element;
import com.supraits.viewBean.payroll.SesM11ElementGroup;


public interface PayrollService {

	Object createPayrollType(HttpSession session, String payrollTypeName,String payrollTypeDesc);

	Object createPayrollPeriod(HttpSession session, String payrollPeriodName,String periodStartDate, String periodEndDate, String periodStatus);
	
	Object createPayrollName(HttpSession session, String payrollName,String payrollType);

	String getActivePayrollType(HttpSession session);
	
	String getActivePayrollPeriod(HttpSession session);

	String getActivePayrollList(HttpSession session);

	String createPayrollElement(HttpSession session, SesM11Element sesM11Element);

	String getPayrollElementList(HttpSession session);

	String createPayrollElementGroup(HttpSession session, String elementGroup,List<Integer> list);

	String getPayrollElementGroupList(HttpSession session);

	String getCategoryBasedValues(HttpSession session, String category);

	String updateUserPayrollData(HttpSession session, String username,String paramValue, String param);

	String fetchEmpCurrentPayrollAssignment(HttpSession session, String empCode);

	String createNewPayrollAssignment(HttpSession session, String empCode,String assignCTC, String activeFlag, String startDate,String endDate);

	String fetchEmpCurrentPayrollElementsList(HttpSession session,String empCode);

	String fetchAvailableElementsList(HttpSession session, String empCode);

	String assignElementGroupToUserPayroll(HttpSession session, String empCode,String elementGroupId);

	String fetchAvailableElementsGroupList(HttpSession session, String empCode);

	String fetchPayslipBasedOnPayrollNameList(HttpSession session,String payrollNameId, String payrollPeriodId);

	String updateUsersPayrollElementData(HttpSession session,Integer empElementId, Integer payValue);

}
