package com.supraits.service.impl;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.supraits.dao.impl.GetQueryAPI;
import com.supraits.service.PayrollService;
import com.supraits.viewBean.payroll.SesM11Element;
import com.supraits.viewBean.payroll.SesM11ElementGroup;
import com.supraits.viewBean.payroll.SesM11EmpElement;
import com.supraits.viewBean.payroll.SesM11Payroll;
import com.supraits.viewBean.payroll.SesM11PayrollPeriods;
import com.supraits.viewBean.payroll.SesM11PayrollTypes;
import com.supraits.viewBean.payroll.UserPayrollAssignmentData;
import com.supraits.viewBean.payroll.UserPayslipBean;

@Service
public class PayrollServiceImpl implements PayrollService{
	@Value("${REST_SERVICE_URI}")
	private String REST_SERVICE_URI;
	@Value("${plainCredentials}")
	private String plainCredentials;
	
	@Value("${PAY1}")
	private String PAY1;
	@Value("${PAY2}")
	private String PAY2;

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	
	private HttpHeaders getHeaders(HttpSession session){
    	String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Authorization", "Basic " + base64Credentials);
    	headers.add("username",String.valueOf(session.getAttribute("loggedInUser")));
    	headers.add("clientId",String.valueOf(session.getAttribute("loggedInUserClientId")));
    	headers.setAccept(Arrays.asList(MediaType.ALL));
    	return headers;
    }
	@Override
	public String getActivePayrollList(HttpSession session) {
		try {
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = getHeaders(session);
				HttpEntity<String> entity = new HttpEntity<String>(headers);
				ResponseEntity<String> responsePayrollType = restTemplate.exchange(REST_SERVICE_URI+"/getPayrollTypeList/", HttpMethod.GET, entity, String.class);
				ObjectMapper objectMapper2 = new ObjectMapper();
				Map<Integer,String> payrollTypeMap = new HashMap<Integer,String>();
				if(responsePayrollType != null && responsePayrollType.getBody().length() != 0){
					SesM11PayrollTypes[] payrollTypes = objectMapper2.readValue(responsePayrollType.getBody(), SesM11PayrollTypes[].class);
					for(SesM11PayrollTypes temp : payrollTypes){
						payrollTypeMap.put(temp.getPayrollTypeId(), temp.getTypeName());
					}
				}
				
				ResponseEntity<String> response = restTemplate.exchange(REST_SERVICE_URI+"/getPayrollNameList/", HttpMethod.GET, entity, String.class);
				ObjectMapper objectMapper = new ObjectMapper();
				if(response != null && response.getBody().length() != 0){
					SesM11Payroll[] payrolls = objectMapper.readValue(response.getBody(), SesM11Payroll[].class);
					List<String> updatedByusernameList = new LinkedList<String>();
					for(SesM11Payroll s : payrolls)
						updatedByusernameList.add(String.valueOf(s.getLastUpdatedBy()));
					MapSqlParameterSource parameters = new MapSqlParameterSource();
				    parameters.addValue("empcodelist", updatedByusernameList);
					List<String> updatedByuserfullnameList = (List<String>) namedParameterJdbcTemplate.queryForList(PAY1,parameters,String.class);
					JSONArray data = new JSONArray();
					int i = 0;
					for(SesM11Payroll temp : payrolls){
						JSONObject t = new JSONObject();
						t.put("payrollId",temp.getPayrollId());
						t.put("payrollName",temp.getPayrollName());
						t.put("payrollTypeId",temp.getPayrollTypeId());
						if(payrollTypeMap.size() > 0 && payrollTypeMap != null)
							t.put("payrollTypeName",payrollTypeMap.get(temp.getPayrollTypeId()));
						t.put("status",temp.getStatus());
						t.put("lastUpdatedBy",temp.getLastUpdatedBy());
						t.put("lastUpdateDate",sdf2.format(temp.getLastUpdateDate()));
						t.put("createdBy",temp.getCreatedBy());
						t.put("creationDate",sdf2.format(temp.getCreationDate()));
						if(i < updatedByuserfullnameList.size())
							t.put("lastUpdatedByFullname",updatedByuserfullnameList.get(i));
						else
							t.put("lastUpdatedByFullname","NA");
						i = i+1;
						data.put(t);
					}
					return data.toString();
				}
		}catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
		    if(HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())) {
		    	//System.out.println("Server down! Please try later");
		    	return "Server down! Please try later";  
		    }
		}catch (Exception e) {
			e.printStackTrace();
			return "Please contact administrator.";
		}
		return "No payroll type available.";
	}
	@Override
	public String getActivePayrollType(HttpSession session) {
		try {
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = getHeaders(session);
				HttpEntity<String> entity = new HttpEntity<String>(headers);
				ResponseEntity<String> response = restTemplate.exchange(REST_SERVICE_URI+"/getPayrollTypeList/", HttpMethod.GET, entity, String.class);
				ObjectMapper objectMapper = new ObjectMapper();
				//System.out.println(new JSONArray(objectMapper.readValue(response.getBody(), SesM11PayrollTypes[].class)));
				if(response != null && response.getBody().length() != 0){
					SesM11PayrollTypes[] payrollTypes = objectMapper.readValue(response.getBody(), SesM11PayrollTypes[].class);
					//System.out.println(objectMapper.writeValueAsString(payrollTypes));
					List<String> updatedByusernameList = new LinkedList<String>();
					for(SesM11PayrollTypes s : payrollTypes)
						updatedByusernameList.add(String.valueOf(s.getLastUpdatedBy()));
					MapSqlParameterSource parameters = new MapSqlParameterSource();
				    parameters.addValue("empcodelist", updatedByusernameList);
					List<String> updatedByuserfullnameList = (List<String>) namedParameterJdbcTemplate.queryForList(PAY1,parameters,String.class);
					JSONArray data = new JSONArray();
					int i = 0;
					for(SesM11PayrollTypes temp : payrollTypes){
						JSONObject t = new JSONObject();
						t.put("payrollTypeId",temp.getPayrollTypeId());
						t.put("typeName",temp.getTypeName());
						t.put("description",temp.getDescription());
						t.put("status",temp.getStatus());
						t.put("lastUpdatedBy",temp.getLastUpdatedBy());
						t.put("lastUpdateDate",temp.getLastUpdateDate());
						t.put("createdBy",temp.getCreatedBy());
						t.put("creationDate",temp.getCreationDate());
						if(i < updatedByuserfullnameList.size())
							t.put("lastUpdatedByFullname",updatedByuserfullnameList.get(i));
						else
							t.put("lastUpdatedByFullname","NA");
						i = i+1;
						data.put(t);
					}
					return data.toString();
				}
		}catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
		    if(HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())) {
		    	//System.out.println("Server down! Please try later");
		    	return "Server down! Please try later";  
		    }
		}catch (Exception e) {
			e.printStackTrace();
			return "Please contact administrator.";
		}
		return "No payroll type available.";
	}
	@Override
	public String getActivePayrollPeriod(HttpSession session) {
		try {
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = getHeaders(session);
				HttpEntity<String> entity = new HttpEntity<String>(headers);
				ResponseEntity<String> response = restTemplate.exchange(REST_SERVICE_URI+"/getPayrollPeriodList/", HttpMethod.GET, entity, String.class);
				ObjectMapper objectMapper = new ObjectMapper();
				if(response != null && response.getBody().length() != 0){
					SesM11PayrollPeriods[] payrollPeriods = objectMapper.readValue(response.getBody(), SesM11PayrollPeriods[].class);
					List<String> updatedByusernameList = new LinkedList<String>();
					for(SesM11PayrollPeriods o : payrollPeriods)
						updatedByusernameList.add(String.valueOf(o.getLastUpdatedBy()));
					MapSqlParameterSource parameters = new MapSqlParameterSource();
				    parameters.addValue("empcodelist", updatedByusernameList);
					List<String> updatedByuserfullnameList = (List<String>) namedParameterJdbcTemplate.queryForList(PAY1,parameters,String.class);
					JSONArray data = new JSONArray();
					int i = 0;
					for(SesM11PayrollPeriods temp : payrollPeriods){
						JSONObject t = new JSONObject();
						t.put("payrollPeriodId",temp.getPayrollPeriodId());
						t.put("periodName",temp.getPeriodName());
						t.put("startDate",sdf2.format(temp.getStartDate()));
						t.put("endDate",sdf2.format(temp.getEndDate()));
						t.put("status",temp.getStatus());
						t.put("lastUpdatedBy",temp.getLastUpdatedBy());
						t.put("lastUpdateDate",sdf2.format(temp.getLastUpdateDate()));
						t.put("createdBy",temp.getCreatedBy());
						t.put("creationDate",sdf2.format(temp.getCreationDate()));
						if(i < updatedByuserfullnameList.size())
							t.put("lastUpdatedByFullname",updatedByuserfullnameList.get(i));
						else
							t.put("lastUpdatedByFullname","NA");
						i = i+1;
						data.put(t);
					}
					return data.toString();
				}
		}catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
		    if(HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())) {
		    	return "Server down! Please try later";  
		    }
		}catch (Exception e) {
			e.printStackTrace();
			return "Please contact administrator.";
		}
		return "No payroll period available.";
	}
	@Override
	public Object createPayrollType(HttpSession session,String payrollTypeName, String payrollTypeDesc) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = getHeaders(session);
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			
			ResponseEntity<String> response = restTemplate.exchange(REST_SERVICE_URI+"/createNewPayrollType/payrollTypeName/"+payrollTypeName+"/payrollTypeDesc/"+payrollTypeDesc, HttpMethod.POST, entity, String.class);
			ObjectMapper objectMapper = new ObjectMapper();
			if(response != null && String.valueOf( response.getBody() ).length() != 0){
				String createStatus = objectMapper.readValue((String) response.getBody(), String.class);
				return objectMapper.writeValueAsString(createStatus);
			}
		}catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
	    if(HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())) {
	    	//System.out.println("Server down! Please try later");
	    	return "Server down! Please try later";  
	    }
	}catch (Exception e) {
		e.printStackTrace();
	}
	return "Please contact administrator.";
}
	@Override
	public Object createPayrollPeriod(HttpSession session,
			String payrollPeriodName, String periodStartDate,
			String periodEndDate, String periodStatus) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = getHeaders(session);
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			
			ResponseEntity<String> response = restTemplate.exchange(REST_SERVICE_URI+"/createNewPayrollPeriod/payrollPeriodName/"+payrollPeriodName+"/periodStartDate/"+periodStartDate+"/periodEndDate/"+periodEndDate+"/periodStatus/"+periodStatus, HttpMethod.POST, entity, String.class);
			ObjectMapper objectMapper = new ObjectMapper();
			if(response != null && String.valueOf( response.getBody() ).length() != 0){
				String createStatus = objectMapper.readValue((String) response.getBody(), String.class);
				return objectMapper.writeValueAsString(createStatus);
			}
		}catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
	    if(HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())) {
	    	return "Server down! Please try later";  
	    }
	}catch (Exception e) {
		e.printStackTrace();
	}
	return "Please contact administrator.";
}
	@Override
	public Object createPayrollName(HttpSession session, String payrollName,
			String payrollType) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = getHeaders(session);
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			
			ResponseEntity<String> response = restTemplate.exchange(REST_SERVICE_URI+"/createNewPayrollName/payrollName/"+payrollName+"/payrollType/"+payrollType, HttpMethod.POST, entity, String.class);
			ObjectMapper objectMapper = new ObjectMapper();
			if(response != null && String.valueOf( response.getBody() ).length() != 0){
				String createStatus = objectMapper.readValue((String) response.getBody(), String.class);
				return objectMapper.writeValueAsString(createStatus);
			}
		}catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
	    if(HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())) {
	    	return "Server down! Please try later";  
	    }
	}catch (Exception e) {
		e.printStackTrace();
	}
	return "Please contact administrator.";
}
	@Override
	public String createPayrollElement(HttpSession session,
			SesM11Element sesM11Element) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = getHeaders(session);
			if(StringUtils.isEmpty(sesM11Element.getElementName()) || sesM11Element.getElementName() == null){
				return "Element name is blank or invalid.";
			}
			if(StringUtils.isEmpty(sesM11Element.getElementType()) || sesM11Element.getElementType() == null){
				return "Element type is blank or invalid.";
			}
			if(StringUtils.isEmpty(sesM11Element.getDescription()) || sesM11Element.getDescription() == null){
				return "Element description is blank or invalid.";
			}
			if(StringUtils.isEmpty(sesM11Element.getPayFrequency()) || sesM11Element.getPayFrequency()  == null){
				return "Element pay frequency is blank or invalid.";
			}
			if(StringUtils.isEmpty(sesM11Element.getTaxExemted()) || sesM11Element.getTaxExemted()  == null){
				return "Tax exemption is blank or invalid.";
			}
			if(StringUtils.isEmpty(sesM11Element.getBillVerification()) || sesM11Element.getBillVerification()  == null){
				return "Bill verification is blank or invalid.";
			}
			if(StringUtils.isWhitespace(sesM11Element.getFormulaAlias()) || StringUtils.isEmpty(sesM11Element.getFormulaAlias()) || sesM11Element.getFormulaAlias()  == null){
				return "Element alias is blank or invalid or having white space.";
			}
			if(StringUtils.isEmpty(sesM11Element.getFormulaFlag()) || sesM11Element.getFormulaFlag()  == null){
				return "Element formula flag is blank or invalid.";
			}
			if("F".equalsIgnoreCase(sesM11Element.getFormulaFlag())){
				if(StringUtils.isEmpty(sesM11Element.getFormulaType()) || sesM11Element.getFormulaType()  == null){
					return "Formula type is blank or invalid.";
				}
				if(StringUtils.isEmpty(sesM11Element.getFormulaText()) || sesM11Element.getFormulaText()  == null){
					return "Formula text is blank or invalid.";
				}
			}
			if(sesM11Element.getMaxLimit() <= 0 || sesM11Element.getMaxLimit()  == null){
				return "Max limit is blank or invalid.";
			}
			HttpEntity entity = new HttpEntity(sesM11Element,headers);
			
			ResponseEntity<String> response = restTemplate.exchange(REST_SERVICE_URI+"/createNewPayrollElement/", HttpMethod.POST, entity, String.class);
			ObjectMapper objectMapper = new ObjectMapper();
			if(response != null && String.valueOf( response.getBody() ).length() != 0){
				String createStatus = objectMapper.readValue((String) response.getBody(), String.class);
				return objectMapper.writeValueAsString(createStatus);
			}
		}catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
	    if(HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())) {
	    	return "Server down! Please try later";  
	    }
	}catch (Exception e) {
		e.printStackTrace();
	}
	return "Please contact administrator.";
}
	@Override
	public String createPayrollElementGroup(HttpSession session, String elementGroup,List<Integer> sesM11ElementIdList) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = getHeaders(session);
			System.out.println(StringUtils.isEmpty(elementGroup));
			System.out.println(elementGroup == null);
			if(StringUtils.isEmpty(elementGroup) || elementGroup == null)
				return "Element group name can not be blank or null.";
			if(sesM11ElementIdList.size() == 0 || sesM11ElementIdList == null)
				return "Please select at least one element in group.";
			HttpEntity entity = new HttpEntity(sesM11ElementIdList,headers);
			ResponseEntity<String> response = restTemplate.exchange(REST_SERVICE_URI+"/createNewPayrollElementGroup/elementGroup/"+elementGroup, HttpMethod.POST, entity, String.class);
			ObjectMapper objectMapper = new ObjectMapper();
			if(response != null && String.valueOf( response.getBody() ).length() != 0){
				String createStatus = objectMapper.readValue((String) response.getBody(), String.class);
				return objectMapper.writeValueAsString(createStatus);
			}
		}catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
	    if(HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())) {
	    	return "Server down! Please try later";  
	    }
	}catch (Exception e) {
		e.printStackTrace();
	}
	return "Please contact administrator.";
}
	@Override
	public String getPayrollElementList(HttpSession session){
		try {
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = getHeaders(session);
				HttpEntity<String> entity = new HttpEntity<String>(headers);
				ResponseEntity<String> response = restTemplate.exchange(REST_SERVICE_URI+"/getPayrollElementList/", HttpMethod.GET, entity, String.class);
				ObjectMapper objectMapper = new ObjectMapper();
				if(response != null && response.getBody().length() != 0){
					SesM11Element[] payrollElements = objectMapper.readValue(response.getBody(), SesM11Element[].class);
					List<String> updatedByusernameList = new LinkedList<String>();
					for(SesM11Element o : payrollElements)
						updatedByusernameList.add(String.valueOf(o.getLastUpdatedBy()));
					MapSqlParameterSource parameters = new MapSqlParameterSource();
				    parameters.addValue("empcodelist", updatedByusernameList);
					List<String> updatedByuserfullnameList = (List<String>) namedParameterJdbcTemplate.queryForList(PAY1,parameters,String.class);
					System.out.println(updatedByuserfullnameList.toString());
					JSONArray data = new JSONArray();
					System.out.println(payrollElements.length);
					int i = 0;
					for(SesM11Element temp : payrollElements){
						JSONObject t = new JSONObject();
						t.put("elementId",temp.getElementId());
						t.put("elementName",temp.getElementName());
						t.put("description", temp.getDescription());
						t.put("elementGroupId", temp.getElementGroupId());
						t.put("formulaAlias", temp.getFormulaAlias());
						t.put("elementType", temp.getElementType());
						t.put("payFrequency", temp.getPayFrequency());
						t.put("billVerification", temp.getBillVerification());
						t.put("taxExemted", temp.getTaxExemted());
						t.put("formulaFlag", temp.getFormulaFlag());
						t.put("formulaText", temp.getFormulaText());
						t.put("formulaType", temp.getFormulaType());
						t.put("maxLimit", temp.getMaxLimit());
						t.put("activeFlag",temp.getActiveFlag());
						t.put("lastUpdatedBy",temp.getLastUpdatedBy());
						t.put("lastUpdateDate",sdf2.format(temp.getLastUpdateDate()));
						t.put("createdBy",temp.getCreatedBy());
						t.put("creationDate",sdf2.format(temp.getCreationDate()));
						if(i < updatedByuserfullnameList.size())
							t.put("lastUpdatedByFullname",updatedByuserfullnameList.get(i));
						else
							t.put("lastUpdatedByFullname","NA");
						i = i+1;
						data.put(t);
					}
					return data.toString();
				}
		}catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
		    if(HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())) {
		    	return "Server down! Please try later";  
		    }
		}catch (Exception e) {
			e.printStackTrace();
			return "Please contact administrator.";
		}
		return "No payroll element found.";
	}
	@Override
	public String getPayrollElementGroupList(HttpSession session) {
		try {
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = getHeaders(session);
				HttpEntity<String> entity = new HttpEntity<String>(headers);
				ResponseEntity<String> response = restTemplate.exchange(REST_SERVICE_URI+"/getAllPayrollElementGroup/", HttpMethod.GET, entity, String.class);
				ObjectMapper objectMapper = new ObjectMapper();
				if(response != null && response.getBody().length() != 0){
					SesM11ElementGroup[] payrollElementsGroup = objectMapper.readValue(response.getBody(), SesM11ElementGroup[].class);
					List<String> updatedByusernameList = new LinkedList<String>();
					for(SesM11ElementGroup o : payrollElementsGroup)
						updatedByusernameList.add(String.valueOf(o.getLastUpdatedBy()));
					MapSqlParameterSource parameters = new MapSqlParameterSource();
					parameters.addValue("empcodelist", updatedByusernameList);
					List<String> updatedByuserfullnameList = (List<String>) namedParameterJdbcTemplate.queryForList(PAY1,parameters,String.class);
					System.out.println(updatedByuserfullnameList.toString());
					JSONArray data = new JSONArray();
					int i = 0;
					for(SesM11ElementGroup temp : payrollElementsGroup){
						JSONObject t = new JSONObject();
						t.put("elementGroupId",temp.getElementGroupId());
						t.put("elementGroupName",temp.getElementGroupName());
						t.put("status",temp.getStatus());
						t.put("lastUpdatedBy",temp.getLastUpdatedBy());
						t.put("lastUpdateDate",sdf2.format(temp.getLastUpdateDate()));
						t.put("createdBy",temp.getCreatedBy());
						t.put("creationDate",sdf2.format(temp.getCreationDate()));
						if(i < updatedByuserfullnameList.size())
							t.put("lastUpdatedByFullname",updatedByuserfullnameList.get(i));
						else
							t.put("lastUpdatedByFullname","NA");
						i = i+1;
						data.put(t);
					}
					return data.toString();
				}
		}catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
		    if(HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())) {
		    	return "Server down! Please try later";  
		    }
		}catch (Exception e) {
			e.printStackTrace();
			return "Please contact administrator.";
		}
		return "No element group found.";
	}
	@Override
	public String getCategoryBasedValues(HttpSession session, String category) {
		String data = "";
		JSONArray finalData = new JSONArray();
		if(category.equalsIgnoreCase("USER_PAYROLL")){
			try{
				JSONArray payrollData = new JSONArray(getActivePayrollList(session));
				for(int i=0;i< payrollData.length();i++){
					JSONObject temp = payrollData.getJSONObject(i);
					System.out.println(temp);
					//if("Y".equalsIgnoreCase(String.valueOf(temp.get("status")))){
						JSONObject o = new JSONObject();
						o.put("value",temp.get("payrollId"));
						o.put("paramlabel",temp.get("payrollName"));
						finalData.put(o);
					//}
				}
				return finalData.toString();
			}catch(Exception e){
				System.out.println(e.getMessage());
				return data;
			}
		}
		if(category.equalsIgnoreCase("USER_PAYROLL_ASSIGNMENT")){
			try{
				JSONArray payrollData = new JSONArray(getActivePayrollList(session));
				for(int i=0;i< payrollData.length();i++){
					JSONObject temp = payrollData.getJSONObject(i);
					System.out.println(temp);
						JSONObject o = new JSONObject();
						o.put("value",temp.get("assignmentId"));
						o.put("paramlabel",temp.get("assignmentName"));
						finalData.put(o);
				}
				return finalData.toString();
			}catch(Exception e){
				System.out.println(e.getMessage());
				return data;
			}
		}
		return data;
	}
	@Override
	public String updateUserPayrollData(HttpSession session, String empCode,
			String paramValue, String param) {
		try{
			if(param.equalsIgnoreCase("USER_PAYROLL")){
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = getHeaders(session);
				if(StringUtils.isEmpty(empCode) || empCode == null)
					return "Employee code can not be blank or null.";
				if(StringUtils.isEmpty(paramValue) || paramValue == null)
					return "Please select valid payroll.";
				HttpEntity entity = new HttpEntity(headers);
				ResponseEntity<String> response = restTemplate.exchange(REST_SERVICE_URI+"/updateEmpPayrollName/empCode/"+empCode+"/payrollId/"+paramValue, HttpMethod.POST, entity, String.class);
				ObjectMapper objectMapper = new ObjectMapper();
				if(response != null && String.valueOf( response.getBody() ).length() != 0){
					String saveOrUpdateStatus = objectMapper.readValue((String) response.getBody(), String.class);
					return objectMapper.writeValueAsString(saveOrUpdateStatus);
				}
			}
			
		}catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
		    if(HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())) {
		    	return "Server down! Please try later";  
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public String fetchEmpCurrentPayrollAssignment(HttpSession session,
			String empCode) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = getHeaders(session);
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			ResponseEntity<String> response = restTemplate.exchange(REST_SERVICE_URI+"/getEmpCurrentPayrollAssignment/empCode/"+empCode, HttpMethod.GET, entity, String.class);
			ObjectMapper objectMapper = new ObjectMapper();
			if(response != null && response.getBody().length() != 0){
				UserPayrollAssignmentData userAssignmentData = objectMapper.readValue(response.getBody(), UserPayrollAssignmentData.class);
				return objectMapper.writeValueAsString(userAssignmentData);
			}
	}catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
	    if(HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())) {
	    	return "Server down! Please try later";  
	    }
	}catch (Exception e) {
		e.printStackTrace();
		return "Please contact administrator.";
	}
	return "No element group found.";
}
	@Override
	public String createNewPayrollAssignment(HttpSession session,
			String empCode, String assignCTC, String activeFlag,
			String startDate, String endDate) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = getHeaders(session);
			if(StringUtils.isEmpty(empCode) || empCode == null)
				return "Emp code can not be blank or null.";
			if(Double.parseDouble(assignCTC) == 0 || assignCTC == null)
				return "Please provide valid CTC.";
			if(StringUtils.isEmpty(activeFlag) || activeFlag == null)
				return "Active Flag can not be blank or null.";
			if(StringUtils.isEmpty(startDate) || startDate == null)
				return "Start date can not be blank or null.";
			if(StringUtils.isEmpty(endDate) || endDate == null)
				return "End date can not be blank or null.";
			HttpEntity entity = new HttpEntity(headers);
			ResponseEntity<String> response = restTemplate.exchange(REST_SERVICE_URI+"/createNewPayrollAssignmentForUser/empCode/"+empCode+"/assignCTC/"+assignCTC+"/activeFlag/"+activeFlag+"/startDate/"+startDate+"/endDate/"+endDate, HttpMethod.POST, entity, String.class);
			ObjectMapper objectMapper = new ObjectMapper();
			if(response != null && String.valueOf( response.getBody() ).length() != 0){
				String createStatus = objectMapper.readValue((String) response.getBody(), String.class);
				return objectMapper.writeValueAsString(createStatus);
			}
		}catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
	    if(HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())) {
	    	return "Server down! Please try later";  
	    }
	}catch (Exception e) {
		e.printStackTrace();
	}
	return "Please contact administrator.";
}
	@Override
	public String fetchEmpCurrentPayrollElementsList(HttpSession session,
			String empCode) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = getHeaders(session);
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			ObjectMapper objectMapper = new ObjectMapper();
			ResponseEntity<String> response = restTemplate.exchange(REST_SERVICE_URI+"/getEmpCurrentElementDetails/empCode/"+empCode, HttpMethod.GET, entity, String.class);
			System.out.println(response);
			if(response.getBody().length() != 0 && response != null){
				SesM11EmpElement[] userElementData = objectMapper.readValue(response.getBody(), SesM11EmpElement[].class);
				System.out.println(userElementData);
				return objectMapper.writeValueAsString(userElementData);
			}
	}catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
	    if(HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())) {
	    	return "Server down! Please try later";  
	    }
	}catch (Exception e) {
		e.printStackTrace();
		return "Please contact administrator.";
	}
	return "No element available.";
}
	@Override
	public String fetchAvailableElementsList(HttpSession session,String empCode) {
		try {
			//JSONArray allotedElements = new JSONArray(fetchEmpCurrentPayrollElementsList(session,empCode));
			ObjectMapper objectMapper = new ObjectMapper();
			SesM11EmpElement[] allotedElements = objectMapper.readValue(fetchEmpCurrentPayrollElementsList(session,empCode), SesM11EmpElement[].class);
			JSONArray allElements = new JSONArray(getPayrollElementList(session));
			Map<Integer,Object> availableElemList = new HashMap<Integer,Object>();
			if(allotedElements.length > 0){
				for(int i=0;i<allotedElements.length;i++){
					SesM11EmpElement tempEmpElem = allotedElements[i];
					int presentCount = 0;
					JSONObject tempAllElem = new JSONObject();
					for(int j=0;j<allElements.length();j++){
						tempAllElem =  allElements.getJSONObject(j);
						if(tempAllElem.getInt("elementId") == tempEmpElem.getElementId()){
							presentCount++; 
						}
					}
					if(presentCount == 0)
						availableElemList.put(tempEmpElem.getElementId(),tempAllElem);
				}
				ObjectMapper objectMapper2 = new ObjectMapper();
				Iterator itr = availableElemList.keySet().iterator();
				int i =0;
				Object[] availableList =  new SesM11EmpElement[]{};
				while(itr.hasNext()){
					availableList[i] = availableElemList.get(itr.next());
				}
				return objectMapper2.writeValueAsString(availableList);
			}else{
				return allElements.toString();
			}
		}catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
	    if(HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())) {
	    	return "Server down! Please try later";  
	    }
	}catch (Exception e) {
		e.printStackTrace();
		return "Please contact administrator.";
	}
	return "No element available.";
}
	@Override
	public String assignElementGroupToUserPayroll(HttpSession session,
			String empCode, String elementGroupId) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = getHeaders(session);
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			ResponseEntity<String> response = restTemplate.exchange(REST_SERVICE_URI+"/assignElementGroupToUserPayroll/empCode/"+empCode+"/elementGroupId/"+elementGroupId, HttpMethod.POST, entity, String.class);
			ObjectMapper objectMapper = new ObjectMapper();
			if(response != null && String.valueOf( response.getBody() ).length() != 0){
				String createStatus = objectMapper.readValue((String) response.getBody(), String.class);
				return objectMapper.writeValueAsString(createStatus);
			}
		}catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
	    if(HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())) {
	    	return "Server down! Please try later";  
	    }
	}catch (Exception e) {
		e.printStackTrace();
	}
	return "Please contact administrator.";
}
	@Override
	public String fetchAvailableElementsGroupList(HttpSession session,
			String empCode) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = getHeaders(session);
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			ObjectMapper objectMapper = new ObjectMapper();
			ResponseEntity<String> response = restTemplate.exchange(REST_SERVICE_URI+"/getAvailableElementsGroupList/empCode/"+empCode, HttpMethod.GET, entity, String.class);
			System.out.println(response);
			if(response.getBody().length() != 0 && response != null){
				Map userElementData = objectMapper.readValue(response.getBody(), Map.class);
				System.out.println(userElementData);
				return objectMapper.writeValueAsString(userElementData);
			}
	}catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
	    if(HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())) {
	    	return "Server down! Please try later";  
	    }
	}catch (Exception e) {
		e.printStackTrace();
		return "Please contact administrator.";
	}
	return "No element available.";
}
	@Override
	public String fetchPayslipBasedOnPayrollNameList(HttpSession session,
			String payrollNameId, String payrollPeriodId) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = getHeaders(session);
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			ObjectMapper objectMapper = new ObjectMapper();
			ResponseEntity<String> response = restTemplate.exchange(REST_SERVICE_URI+"/getUserPayDataBasedOnPayrollAndPeriod/payrollNameId/"+payrollNameId+"/payrollPeriodId/"+payrollPeriodId, HttpMethod.GET, entity, String.class);
			System.out.println(response);
			if(response.getBody().length() != 0 && response != null){
				UserPayslipBean[] userPayslipData = objectMapper.readValue(response.getBody(), UserPayslipBean[].class);
				List<String> empCodeList = new LinkedList<String>();
				for(UserPayslipBean u : userPayslipData)
					empCodeList.add(String.valueOf(u.getEmpCode()));
				MapSqlParameterSource parameters = new MapSqlParameterSource();
			    parameters.addValue("empcodelist", empCodeList);
				List<String> userfullnameList = (List<String>) namedParameterJdbcTemplate.queryForList(PAY1,parameters,String.class);
				List<String> usernameList = (List<String>) namedParameterJdbcTemplate.queryForList(PAY2,parameters,String.class);
				for(int i = 0; i < userPayslipData.length;i++){
					userPayslipData[i].setUsername(usernameList.get(i));
					userPayslipData[i].setUserfullname(userfullnameList.get(i));
				}
				System.out.println(userPayslipData);
				return objectMapper.writeValueAsString(userPayslipData);
			}
	}catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
	    if(HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())) {
	    	return "Server down! Please try later";  
	    }
	}catch (Exception e) {
		e.printStackTrace();
		return "Please contact administrator.";
	}
	return "No data available.";
}
	@Override
	public String updateUsersPayrollElementData(HttpSession session,Integer empElementId, Integer payValue) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = getHeaders(session);
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			ResponseEntity<String> response = restTemplate.exchange(REST_SERVICE_URI+"/editUserPayrollElementData/empElementId/"+empElementId+"/payValue/"+payValue, HttpMethod.POST, entity, String.class);
			ObjectMapper objectMapper = new ObjectMapper();
			if(response != null && String.valueOf( response.getBody() ).length() != 0){
				String createStatus = objectMapper.readValue((String) response.getBody(), String.class);
				return objectMapper.writeValueAsString(createStatus);
			}
		}catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
	    if(HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())) {
	    	return "Server down! Please try later";  
	    }
	}catch (Exception e) {
		e.printStackTrace();
	}
	return "Please contact administrator.";
}
}
