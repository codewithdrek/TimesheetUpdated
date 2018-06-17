package com.supraits.controller;


import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supraits.service.PayrollService;
import com.supraits.service.impl.PayrollServiceImpl;
import com.supraits.viewBean.EmployeeBean;
import com.supraits.viewBean.payroll.SesM11Element;
import com.supraits.viewBean.payroll.SesM11ElementGroup;

@Controller
@RequestMapping("/payroll")
public class PayrollController {
	
	PayrollService guiObj;
	@Autowired
	public void setPayrollServiceImpl(PayrollServiceImpl objPayrollServiceImpl) {
		this.guiObj = objPayrollServiceImpl;
	}
	
	@RequestMapping(value="/getPayrollTypeListRest", method = RequestMethod.POST)
	public @ResponseBody String getPayrollTypeListRest(HttpServletRequest request, HttpSession session) throws JsonProcessingException
	{
		String data = "";
		try{
			data = guiObj.getActivePayrollType(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	@RequestMapping(value="/getPayrollPeriodListRest", method = RequestMethod.POST)
	public @ResponseBody String getPayrollPeriodListRest(HttpServletRequest request, HttpSession session) throws JsonProcessingException
	{
		String data = "";
		try{
			data = guiObj.getActivePayrollPeriod(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	@RequestMapping(value="/getPayrollListRest", method = RequestMethod.POST)
	public @ResponseBody String getPayrollListRest(HttpServletRequest request, HttpSession session) throws JsonProcessingException
	{
		String data = "";
		try{
			data = guiObj.getActivePayrollList(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	@RequestMapping(value="/getPayrollElementListRest", method = RequestMethod.POST)
	public @ResponseBody String getPayrollElementListRest(HttpServletRequest request, HttpSession session) throws JsonProcessingException
	{
		String data = "";
		try{
			data = guiObj.getPayrollElementList(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	@RequestMapping(value="/getPayrollElementGroupListRest", method = RequestMethod.POST)
	public @ResponseBody String getPayrollElementGroupListRest(HttpServletRequest request, HttpSession session) throws JsonProcessingException
	{
		String data = "";
		try{
			data = guiObj.getPayrollElementGroupList(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	@RequestMapping(value="/createNewPayrollPeriod", method = RequestMethod.POST)
	public @ResponseBody String createNewPayrollPeriod(HttpServletRequest request, HttpSession session
			,@RequestParam(value="payrollPeriodName", required=true)String payrollPeriodName
			,@RequestParam(value="periodStartDate", required=true)String periodStartDate
			,@RequestParam(value="periodEndDate", required=true)String periodEndDate
			,@RequestParam(value="periodStatus", required=true)String periodStatus) throws JsonProcessingException
	{
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.writeValueAsString(guiObj.createPayrollPeriod(session,payrollPeriodName,periodStartDate,periodEndDate,periodStatus));
	}
	@RequestMapping(value="/createNewPayrollType", method = RequestMethod.POST)
	public @ResponseBody String createNewPayrollType(HttpServletRequest request, HttpSession session
			,@RequestParam(value="payrollTypeName", required=true)String payrollTypeName
			,@RequestParam(value="payrollTypeDesc", required=true)String payrollTypeDesc) throws JsonProcessingException
	{
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.writeValueAsString(guiObj.createPayrollType(session,payrollTypeName,payrollTypeDesc));
	}
	@RequestMapping(value="/createNewPayrollName", method = RequestMethod.POST)
	public @ResponseBody String createNewPayrollName(HttpServletRequest request, HttpSession session
			,@RequestParam(value="payrollName", required=true)String payrollName
			,@RequestParam(value="payrollType", required=true)String payrollType) throws JsonProcessingException
	{
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.writeValueAsString(guiObj.createPayrollName(session,payrollName,payrollType));
	}
	@RequestMapping(value="/createNewPayrollElement",headers="Accept=*/*",  produces="application/json", method = RequestMethod.POST)
	public @ResponseBody String createNewPayrollElement(@ModelAttribute("sesM11Element") SesM11Element sesM11Element,HttpServletRequest request, HttpSession session,BindingResult result){
		String statusString = "Contact Administrator";
		try{
				statusString = guiObj.createPayrollElement(session,sesM11Element);
			}catch(Exception e){
				e.printStackTrace();
			}
		return statusString;
	}
	@RequestMapping(value="/createNewPayrollElementGroupRest",headers="Accept=*/*",  produces="application/json", method = RequestMethod.POST)
	public @ResponseBody String createNewPayrollElement(HttpServletRequest request, HttpSession session
			,@RequestParam(value="elementGroup", required=true)String elementGroup
			,@RequestParam(value="elemArray[]", required=true)Integer[] elemArray) throws JsonProcessingException{
		String statusString = "Contact Administrator";
		try{
				statusString = guiObj.createPayrollElementGroup(session,elementGroup,Arrays.asList(elemArray));
			}catch(Exception e){
				e.printStackTrace();
			}
		return statusString;
	}
	@RequestMapping(value="/getPayrollDataBasedOnCategory", method = RequestMethod.POST)
	public @ResponseBody String getPayrollValuesBasedOnCategory(HttpServletRequest request,HttpSession session,
		   @RequestParam(value="category", required=true)String category)
	{
		String payrollData = "";
		try{
			payrollData = guiObj.getCategoryBasedValues(session,category);
		}catch(Exception e){
			e.printStackTrace();
		}
		return payrollData;
	}
	@RequestMapping(value="/updateUserPayrollInfoByParam", method = RequestMethod.POST)
	public @ResponseBody String updateUserPayInfoByParam(HttpServletRequest request, HttpSession session
			,@RequestParam(value="username", required=true)String username
			,@RequestParam(value="paramValue", required=true)String paramValue
			,@RequestParam(value="param", required=true)String param)
	{
		JSONArray jsonarr = new JSONArray();
		String statusUpdate;
		try{
			statusUpdate = guiObj.updateUserPayrollData(session,username,paramValue,param);
			jsonarr.put(statusUpdate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/createUserNewPayrollAssignment", method = RequestMethod.POST)
	public @ResponseBody String createUserNewPayrollAssignment(HttpServletRequest request, HttpSession session
			,@RequestParam(value="empCode", required=true)String empCode
			,@RequestParam(value="assignCTC", required=true)String assignCTC
			,@RequestParam(value="activeFlag", required=true)String activeFlag
			,@RequestParam(value="startDate", required=true)String startDate
			,@RequestParam(value="endDate", required=true)String endDate)
	{
		JSONArray jsonarr = new JSONArray();
		String statusUpdate;
		try{
			statusUpdate = guiObj.createNewPayrollAssignment(session,empCode,assignCTC,activeFlag,startDate,endDate);
			jsonarr.put(statusUpdate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/fetchUserCurrentPayrollAssignment", method = RequestMethod.POST)
	public @ResponseBody String fetchUserCurrentPayrollAssignment(HttpServletRequest request,HttpSession session,
		   @RequestParam(value="empCode", required=true)String empCode)
	{
		String payrollData = "";
		try{
			payrollData = guiObj.fetchEmpCurrentPayrollAssignment(session,empCode);
		}catch(Exception e){
			e.printStackTrace();
		}
		return payrollData;
	}
	@RequestMapping(value="/fetchEmployeeElementDetails", method = RequestMethod.POST)
	public @ResponseBody String fetchEmployeeElementList(HttpServletRequest request,HttpSession session,
		   @RequestParam(value="empCode", required=true)String empCode)
	{
		String payrollData = "";
		try{
			payrollData = guiObj.fetchEmpCurrentPayrollElementsList(session,empCode);
		}catch(Exception e){
			e.printStackTrace();
		}
		return payrollData;
	}
	@RequestMapping(value="/fetchAvailableElementList", method = RequestMethod.POST)
	public @ResponseBody String fetchAvailableElementList(HttpServletRequest request,HttpSession session,
			   @RequestParam(value="empCode", required=true)String empCode)
	{
		String payrollData = "";
		try{
			payrollData = guiObj.fetchAvailableElementsList(session,empCode);
		}catch(Exception e){
			e.printStackTrace();
		}
		return payrollData;
	}
	@RequestMapping(value="/fetchAvailableElementGroupList", method = RequestMethod.POST)
	public @ResponseBody String fetchAvailableElementGroupList(HttpServletRequest request,HttpSession session,
			   @RequestParam(value="empCode", required=true)String empCode)
	{
		String payrollData = "";
		try{
			payrollData = guiObj.fetchAvailableElementsGroupList(session,empCode);
		}catch(Exception e){
			e.printStackTrace();
		}
		return payrollData;
	}
	@RequestMapping(value="/addElementGroupToUserPayroll", method = RequestMethod.POST)
	public @ResponseBody String addElementToUserPayroll(HttpServletRequest request,HttpSession session,
			   @RequestParam(value="empCode", required=true)String empCode,
			   @RequestParam(value="elementGroupId", required=true)String elementGroupId)
	{
		String payrollData = "";
		try{
			payrollData = guiObj.assignElementGroupToUserPayroll(session,empCode,elementGroupId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return payrollData;
	}
	@RequestMapping(value="/generatePayslipBasedOnPayroll", method = RequestMethod.POST)
	public @ResponseBody String getPayslipBasedOnPayrollNameList(HttpServletRequest request,HttpSession session,
			   @RequestParam(value="payrollNameId", required=true)String payrollNameId
			   ,@RequestParam(value="payrollPeriodId", required=true)String payrollPeriodId)
	{
		String payrollData = "";
		try{
			payrollData = guiObj.fetchPayslipBasedOnPayrollNameList(session,payrollNameId,payrollPeriodId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return payrollData;
	}
	@RequestMapping(value="/updateEmployeeElementData", method = RequestMethod.POST)
	public @ResponseBody String updateEmployeeElementData(HttpServletRequest request,HttpSession session,
			   @RequestParam(value="empElementId", required=true)Integer empElementId,
			   @RequestParam(value="payValue", required=true)Integer payValue)
	{
		String payrollData = "";
		try{
			payrollData = guiObj.updateUsersPayrollElementData(session,empElementId,payValue);
		}catch(Exception e){
			e.printStackTrace();
		}
		return payrollData;
	}
}
