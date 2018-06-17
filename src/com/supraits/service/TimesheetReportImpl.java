package com.supraits.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.supraits.dao.impl.GetQueryAPI;


@Service
public class TimesheetReportImpl {
	
	@Value("${TM65}")
	private String TM65;
	@Value("${TM66}")
	private String TM66;
	@Value("${TM67}")
	private String TM67;
	@Value("${TM68}")
	private String TM68;
	@Value("${TM70}")
	private String TM70;
	@Value("${TM75}")
	private String TM75;
	@Value("${TM92}")
	private String TM92;
	@Value("${TM93}")
	private String TM93;
	@Value("${TM94}")
	private String TM94;
	@Value("${TM95}")
	private String TM95;
	@Value("${TM96}")
	private String TM96;
	@Value("${TM97}")
	private String TM97;
	@Value("${TM98}")
	private String TM98;
	@Value("${TM99}")
	private String TM99;
	@Value("${TM103}")
	private String TM103;
	@Value("${TM193}")
	private String TM193;
	
	@Value("${ORGANIZATION_PROJECT}")
	private String ORGANIZATION_PROJECT;
	@Value("${ADMIN}")
	private String ADMIN;
	@Value("${Approver}")
	private String Approver;
	@Value("${VP_GROUP}")
	private String VP_GROUP;
	@Value("${HR}")
	private String HR;
	
	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	Date date = new Date();
	String currentDate = sdf.format(date);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	ServletContext servletContext;
	
	@SuppressWarnings("unchecked")
	public String createMonthlyReportProj(HttpSession session,String projectId, String month,
			String year, String username,TimesheetServiceImpl guiObj) throws ParseException {
		
		if(!((List<String>)session.getAttribute("loggedInUserGroups")).contains(ADMIN) && !((List<String>)session.getAttribute("loggedInUserGroups")).contains(Approver) && !((List<String>)session.getAttribute("loggedInUserGroups")).contains(VP_GROUP) && !((List<String>)session.getAttribute("loggedInUserGroups")).contains(HR)){
			int reporteeCount = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM193,username,session.getAttribute("loggedInUser").toString()),Integer.class);
			if(reporteeCount == 0)	
				username = session.getAttribute("loggedInUser").toString();
		}
		Properties properties = new Properties();
		Map<String, Object> map = new HashMap<String,Object>();
		String 	tempString = "";
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat simpleDateformat1 = new SimpleDateFormat("EEEE");
		String fDate = year+"-"+month+"-"+"01"; 
        Date parsed = format.parse(fDate);
        Date startDate = new Date(parsed.getTime());
	        Calendar calendar = Calendar.getInstance();  
	        calendar.setTime(startDate);  
	        calendar.set(Calendar.DAY_OF_MONTH, 1);  
	        calendar.add(Calendar.DATE, -1);  
	        
        Date parsed1 = format.parse(fDate);
        Date startDate11 = new Date(parsed1.getTime());
	        Calendar calendar11 = Calendar.getInstance();  
	        calendar11.setTime(startDate11);  
	        calendar11.add(Calendar.MONTH, 1);  
	        calendar11.set(Calendar.DAY_OF_MONTH, 1);  
	        calendar11.add(Calendar.DATE, -1);  
        Date lastDayOfMonth11 = calendar11.getTime();    
		try {
		//properties.load(TimesheetReportImpl.class.getResourceAsStream("classpath:/resource/template.properties"));
		properties.load(TimesheetReportImpl.class.getResourceAsStream("template.properties"));	
		for (String key : properties.stringPropertyNames()) {
		    String value = properties.getProperty(key);
		    map.put(key, String.valueOf(value));
		}
		map.put("TAG_MONTH", new DateFormatSymbols().getMonths()[Integer.parseInt(month)-1]+","+year);
		map.put("TAG_PROJECT_DESC",guiObj.getProjectName(projectId));
		map.put("TAG_END_CLIENT",getProjectDesc(projectId));
		map.put("TAG_EMPLOYEE_NAME",guiObj.getEmpName(username) + "("+username+")");
		//map.put("TAG_JOIN_CLIENT_DATE",getJoinDate(projectId,username));
		map.put("TAG_JOIN_CLIENT_DATE","");
		map.put("TAG_PM_NAME",getProjectOwner(projectId));
		map.put("TAG_PM_NAME",getTSApproverName(projectId));
		map.put("TAG_APPROVER_NAME",getTSApproverName(projectId));
		int mapTempWeekendCount = 0;
		int mapTempAbsentCount = 0;
		for(int sheetEntry=1;sheetEntry<32;sheetEntry++){
			 calendar.add(Calendar.DATE, 1);  
			//System.out.println(calendar.getTime() +"***"+lastDayOfMonth11);
			if(calendar.getTime().after(lastDayOfMonth11)){
				map.put("Date_TAG_"+sheetEntry,"");
				map.put("Day_TAG_"+sheetEntry,"");
				map.put("TAG_ATTENDANCE_"+sheetEntry,"");
				map.put("TAG_TASK_"+sheetEntry,"");
				map.put("TAG_EFFORT_"+sheetEntry,"");
				map.put("TAG_REMARK_"+sheetEntry,"");
            }else{
				map.put("Date_TAG_"+sheetEntry,format.format(calendar.getTime()));
				map.put("Day_TAG_"+sheetEntry,simpleDateformat1.format(calendar.getTime()));
				if(simpleDateformat1.format(calendar.getTime()).equalsIgnoreCase("Saturday") || simpleDateformat1.format(calendar.getTime()).equalsIgnoreCase("Sunday")){
					map.put("TAG_ATTENDANCE_"+sheetEntry,"Weekend");
					mapTempWeekendCount++;
				}
				else{
					map.put("TAG_ATTENDANCE_"+sheetEntry,"Absent");
					mapTempAbsentCount++;
				}
				map.put("TAG_TASK_"+sheetEntry,"");
				map.put("TAG_EFFORT_"+sheetEntry,"0");
				map.put("TAG_REMARK_"+sheetEntry,"NA");
            }
		}
			map.putAll(getEmpMonthData(projectId,username,year,month,mapTempWeekendCount,mapTempAbsentCount));
		}
		catch (Exception e) { 
			e.printStackTrace();
			return "";
		}
	    try{
	    	File file = new File( servletContext.getRealPath("/WEB-INF/resource/ExcelSheet.xls") );
	    	FileInputStream templateFile= new FileInputStream(file);
	    	HSSFWorkbook wb = new HSSFWorkbook(templateFile);
	    	HSSFSheet worksheet =  wb.getSheetAt(0); 
	    Iterator it = map.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        String tempKey = (String) pair.getKey();
	        String tempValue = (String) pair.getValue();
	        for (Row row : worksheet) {
	        	boolean fillStatus = false;
	            for (Cell cellTemp : row) {
	            	cellTemp.setCellType(Cell.CELL_TYPE_STRING);
	                if(cellTemp.getStringCellValue().equalsIgnoreCase(tempKey)){
	                	cellTemp.setCellValue(tempValue);
	                	fillStatus = true;
	                	continue;
	                }
	            }
	            if(fillStatus){continue;}
	        }
	    }
	    templateFile.close();
	        	tempString =  new SimpleDateFormat("yyyyMMdd HH:mm'.xls'").format(new Date());
	    		tempString = tempString.replaceAll(":", "");
	    		tempString = "TS_"+ guiObj.getEmpName(username).replaceAll("\\s","") +"_"+ projectId +"_"+new DateFormatSymbols().getMonths()[Integer.parseInt(month)-1] + year+"_" + tempString;
	    		tempString = tempString.replaceAll("\\s","");
	    		//System.out.println(tempString);	
		 try (FileOutputStream outputStream = new FileOutputStream(tempString)) {
			 wb.write(outputStream);
			 outputStream.close();
	        }
		//FileOutputStream output_file =new FileOutputStream(new File(System.getProperty("user.dir") + File.separator + "ExcelSheet11.xls"));  
		//wb.write(output_file);
	    }catch(Exception e){
	    	e.printStackTrace();
	    	return "";
	    }
		return tempString;
	}

	private String getProjectDesc(String projectId) {
		String query = GetQueryAPI.getQuery(TM92,projectId);
		return jdbcTemplate.queryForObject(query, String.class);
	}

	private Map<String,String> getEmpMonthData(
			String projectId, String username, String year, String month, final int mapTempWeekendCount, final int mapTempAbsentCount) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");		
		String fDate = year+"-"+month+"-"+"01"; 
        Date parsed = format.parse(fDate);
        Date startDate = new Date(parsed.getTime());
	        Calendar calendar = Calendar.getInstance();  
	        calendar.setTime(startDate);  
	        calendar.add(Calendar.MONTH, 1);  
	        calendar.set(Calendar.DAY_OF_MONTH, 1);  
	        calendar.add(Calendar.DATE, -1);  
        Date lastDayOfMonth = calendar.getTime();  
        String endDate = format.format(lastDayOfMonth);	
		    Map<String,String> empMap1 = new HashMap<String,String>();
	    try{
	    	String query = GetQueryAPI.getQuery(TM65,username,projectId,fDate,endDate);
	    	
	    	if(projectId.equalsIgnoreCase(ORGANIZATION_PROJECT)){
	    		query = GetQueryAPI.getQuery(TM75,username,projectId,fDate,endDate);
	    	}else{
	    		query = GetQueryAPI.getQuery(TM65,username,projectId,fDate,endDate);
	    	}
	    	//System.out.println("month report query "+query);
	    	empMap1 = jdbcTemplate.query(query, new ResultSetExtractor<Map<String,String>>(){
			    @Override
			    public Map<String,String> extractData(ResultSet rsEffort) throws SQLException,DataAccessException{
			    	Map<String,String> empMap = new HashMap<String,String>();
						int totalWorkingDays = 0;
						int absentDays = 0;
						int daysWorked = 0;
						int totalWeekendHolidays = 0;
						double countTotalEffort = 0;
						while(rsEffort.next()){
							int tempDay=Integer.parseInt(rsEffort.getString(6));
								empMap.put("Date_TAG_"+tempDay, rsEffort.getString(1));
								empMap.put("Day_TAG_"+tempDay, rsEffort.getString(2));
								empMap.put("TAG_EFFORT_"+tempDay, rsEffort.getString(4));
								empMap.put("TAG_REMARK_"+tempDay, rsEffort.getString(5));
								
								if("0".equalsIgnoreCase(rsEffort.getString(4)))
									empMap.put("TAG_TASK_"+tempDay, "");
								else
									empMap.put("TAG_TASK_"+tempDay, rsEffort.getString(3));
								
								if(!(rsEffort.getString(3).toLowerCase().contains("2914Holi".toLowerCase())) || !(rsEffort.getString(3).toLowerCase().contains("7991Leav".toLowerCase())))
									countTotalEffort = countTotalEffort + Double.parseDouble(rsEffort.getString(4));
								
								if(rsEffort.getString(2).equalsIgnoreCase("sunday") || rsEffort.getString(2).equalsIgnoreCase("saturday")){
								  empMap.put("TAG_ATTENDANCE_"+tempDay,"Weekend");	
								}else{
								  if(rsEffort.getString(3).toLowerCase().contains("2914Holi".toLowerCase())){
									  empMap.put("TAG_ATTENDANCE_"+tempDay,"Holiday");
									  totalWeekendHolidays++;
								  }else{	
									  if(rsEffort.getString(3).toLowerCase().contains("7991Leav".toLowerCase())){
										  empMap.put("TAG_ATTENDANCE_"+tempDay,"Absent");
										  absentDays++;
										  totalWorkingDays++;
									  }else{
										  empMap.put("TAG_ATTENDANCE_"+tempDay,"Present");
										  daysWorked++;
										  totalWorkingDays++;
									  }
								  }
								}
						}
						empMap.put("TAG_WORKING_DAYS",String.valueOf(totalWorkingDays));
						empMap.put("TAG_DAYS_ABSENT",String.valueOf(totalWorkingDays - daysWorked));
						empMap.put("TAG_DAYS_WORKED",String.valueOf(daysWorked));
						empMap.put("TAG_TOTAL_WORKING_DAY",String.valueOf(totalWorkingDays));
						empMap.put("TAG_DAYS_WORKED",String.valueOf(daysWorked));
						empMap.put("TAG_ABSENT_DAYS",String.valueOf(totalWorkingDays - daysWorked));
						empMap.put("TAG_WEEKEND_HOLIDAYS",String.valueOf(mapTempWeekendCount + totalWeekendHolidays));
						empMap.put("TAG_TOTAL_EFFORT",String.valueOf(countTotalEffort));
					return empMap;
			    }

				private boolean checkDateHoliday(String date) {
					String query = GetQueryAPI.getQuery(TM66,date);
					if( jdbcTemplate.queryForObject(query,Integer.class)  > 0)
						return true;
					else
						return false;
				} 			
			});
	    }catch(Exception e){
	    	e.printStackTrace();
	    	empMap1.clear();
	    }
	    //empMap1.put("TAG_MONTH",month+","+year);
		return empMap1;
	}

	private Object getTSApproverName(String projectId) {
		String query = GetQueryAPI.getQuery(TM93,projectId);
		return jdbcTemplate.queryForObject(query, String.class);
	}

	private Object getProjectOwner(String projectId) {
		String query = GetQueryAPI.getQuery(TM68,projectId);
		return jdbcTemplate.queryForObject(query, String.class);
	}
	private Object getJoinDate(String projectId, String username) {
		String query = GetQueryAPI.getQuery(TM70,projectId,username);
		return jdbcTemplate.queryForObject(query, String.class);
	}
	private boolean checkJoiningDate(String usermail,String dateSpan) throws ParseException {
		boolean joinStatus = true;
		String tSStartDateString = dateSpan.substring(0, 10);
		//System.out.println("tSStartDateString=="+tSStartDateString);
		String tEndDateString = dateSpan.substring(11,dateSpan.length());
		//System.out.println("tEndDateString=="+tEndDateString);
		String query = GetQueryAPI.getQuery(TM98,tEndDateString,tSStartDateString,usermail);
		Integer joinStartTempdate =  jdbcTemplate.queryForObject(query, Integer.class);
		// String query2 = GetQueryAPI.getQuery(TM103,tSStartDateString,usermail);
		//Integer joinEndTempdate =  jdbcTemplate.queryForObject(query2, Integer.class);
        if(joinStartTempdate > 0)
        	joinStatus =false;
        //if(joinEndTempdate > 0)
       // 	joinStatus =true;
		return joinStatus;
	}

	public boolean createReportProjWeekly(String status, String startDate1,
			String endDate1, String username) throws ParseException, FileNotFoundException, IOException {
				//Header Row creation for xls file
				@SuppressWarnings("rawtypes")
				List<List> recordToAdd = new ArrayList<List>();
				List<String> headerRow = generateHeadersForStatusReport(startDate1,endDate1);
				//System.out.println(generateHeadersForStatusReport(startDate1,endDate1));
				recordToAdd.add(headerRow);
				//System.out.println("*******"+tempDateSet);
				List<List<String>> dataRow = generateDataForStatusReport(username,startDate1,endDate1,status);
				if(dataRow.size()>0){
					Set<String> userL1 = new LinkedHashSet<String>();
					for(int i=0;i<dataRow.size();i++){
						userL1.add(dataRow.get(i).get(0));
					}
					Set<String> userL1Mail = new LinkedHashSet<String>();
					for(int i=0;i<dataRow.size();i++){
						userL1Mail.add(dataRow.get(i).get(1));
					}
			List<String> userLMail = new ArrayList<String>(userL1Mail);		
			List<String> userL = new ArrayList<String>(userL1);
				for(int k =0;k<userL.size();k++){
					Map<String,String> tempDateSet = getTempDateHashMap(startDate1,endDate1,userLMail.get(k));
					List<String> dataRowTemp = new ArrayList<String>();
					dataRowTemp.add(String.valueOf(k+1));
					for(int i=0;i<dataRow.size();i++){
						if(userL.get(k).equalsIgnoreCase(dataRow.get(i).get(0))){
							tempDateSet.put("username", dataRow.get(i).get(0));
							tempDateSet.put("usernamemail", dataRow.get(i).get(1));
							tempDateSet.put("managername", dataRow.get(i).get(2));
							tempDateSet.put("managernamemail", dataRow.get(i).get(3));
							tempDateSet.put(dataRow.get(i).get(4), dataRow.get(i).get(5));		
						}
					}
					Iterator<String> itr = tempDateSet.keySet().iterator();
					while(itr.hasNext()){
						String tempS = itr.next(); 
						dataRowTemp.add(tempDateSet.get(tempS));
					}
					recordToAdd.add(dataRowTemp);
			}
				}else{
					return false;
				}
		return createXLSWeeklyReport(recordToAdd);
	}

	private Map<String, String> getTempDateHashMap(String startDate1,
			String endDate1,String userMail) throws ParseException {
		Map<String,String> tempHashMap = new LinkedHashMap<String,String>();
		tempHashMap.put("username", "");
		tempHashMap.put("usernamemail", userMail);
		tempHashMap.put("managername", "");
		tempHashMap.put("managernamemail", "");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date endDate = formatter.parse(endDate1);
		Date startDate = formatter.parse(startDate1);
		
		List<String> temp = new ArrayList<String>();
		String query = GetQueryAPI.getQuery(TM99, startDate1,endDate1);
		//System.out.println(query);
		temp = jdbcTemplate.query(query, new ResultSetExtractor<List<String>>(){
		    @Override
		    public List<String> extractData(ResultSet rsEffort) throws SQLException,DataAccessException{
		    	List<String> tempList1= new LinkedList<String>();					
					while(rsEffort.next()){
						String tempS = rsEffort.getString(1);
						tempList1.add(tempS);
					}							
				return tempList1;
		    } 			
		});
		Iterator<String> itr = temp.iterator();
		while(itr.hasNext()){
			String tempWeek = itr.next();
			if(checkJoiningDate(userMail, tempWeek))
				tempHashMap.put(tempWeek, "Not Applicable");
			else
				tempHashMap.put(tempWeek, "Missing");
		}
		//System.out.println("temphashmap"+tempHashMap);
		return tempHashMap;
	}

	private boolean createXLSWeeklyReport(List<List> recordToAdd) throws FileNotFoundException, IOException {
		
		boolean createStatus = false;
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet firstSheet = workbook.createSheet("Weekly_Status_Sheet");
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor(HSSFColor.RED.index);
        style.setFont(font);
        CellStyle style1 = workbook.createCellStyle();
        Font font1 = workbook.createFont();
        font1.setColor(HSSFColor.GREEN.index);
        style1.setFont(font1);
        int columnNumber = 5;
		try {
			int rownum = 0;
			for (int j = 0; j < recordToAdd.size(); j++) {
				Row row = firstSheet.createRow(rownum);
				@SuppressWarnings("unchecked")
				List<String> rowData= recordToAdd.get(j);
				columnNumber = rowData.size();
				for(int k=0; k<rowData.size(); k++)
				{
					Cell cell = row.createCell(k);
					if(rowData.get(k).equalsIgnoreCase("Missing"))
						cell.setCellStyle(style);
					if(rowData.get(k).equalsIgnoreCase("Approved"))
						cell.setCellStyle(style1);
					cell.setCellValue(rowData.get(k));
					
				}
				rownum++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		 String tempString =  new SimpleDateFormat("yyyyMMdd'.xlsx'").format(new Date());
		 tempString = "Status_Report_" + tempString;
		 for (int i=0; i<columnNumber; i++){
	        	firstSheet.autoSizeColumn(i);
	     }
		 try (FileOutputStream outputStream = new FileOutputStream(tempString)) {
	            workbook.write(outputStream);
	            createStatus = true;
	        }
		 return createStatus;
	}

	private List<List<String>> generateDataForStatusReport(String username,
			final String startDate1, String endDate1, String status) {
		List<List<String>> userDataList1 = new LinkedList<List<String>>();
		String query = "";
		try{
			if(username.equalsIgnoreCase("select") && status.equalsIgnoreCase("select")){
				query = GetQueryAPI.getQuery(TM95,startDate1,endDate1);
			}
			if(!(username.equalsIgnoreCase("select")) && !(status.equalsIgnoreCase("select"))){
				 query = GetQueryAPI.getQuery(TM94,startDate1,endDate1,username,status);	
			}
			if(!(username.equalsIgnoreCase("select")) && (status.equalsIgnoreCase("select"))){
				query = GetQueryAPI.getQuery(TM96,startDate1,endDate1,username);		
				//System.out.println(query);
			}
			if((username.equalsIgnoreCase("select")) && !(status.equalsIgnoreCase("select"))){
					 query = GetQueryAPI.getQuery(TM97,startDate1,endDate1,status);	
			}		 
			 //System.out.println("username query = "+query);
				userDataList1 = jdbcTemplate.query(query, new ResultSetExtractor<List<List<String>>>(){
				    @Override
				    public List<List<String>> extractData(ResultSet rsEffort) throws SQLException,DataAccessException{
				    	List<List<String>> tempList1= new LinkedList<List<String>>();					
							while(rsEffort.next()){
								List<String> tempList= new ArrayList<String>();
								tempList.add(rsEffort.getString(1));
								tempList.add(rsEffort.getString(2));
								tempList.add(rsEffort.getString(3));
								tempList.add(rsEffort.getString(4));
								if(rsEffort.wasNull())
									tempList.add("");
								else	
									tempList.add(rsEffort.getString(5));
								if(rsEffort.wasNull())
										tempList.add("");
								else
									tempList.add(rsEffort.getString(6));
								tempList1.add(tempList);
							}							
						return tempList1;
				    } 			
				});	
		}catch(Exception e){
			System.out.println("Error encountered while fetching user list");
			e.printStackTrace();
		}
		return userDataList1;
	}

	private List<String> generateHeadersForStatusReport(String startDate1,
			String endDate1) throws ParseException {

		List<String> headerRow = new ArrayList<String>();
		headerRow.add("SR#");
		headerRow.add("Name");
		headerRow.add("Email Id");
		headerRow.add("Manager Name");
		headerRow.add("Manager Email");
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date endDate = formatter.parse(endDate1);
		Date startDate = formatter.parse(startDate1);
		List<String> temp = new ArrayList<String>();
		String query = GetQueryAPI.getQuery(TM99, startDate1,endDate1);
		temp = jdbcTemplate.query(query, new ResultSetExtractor<List<String>>(){
		    @Override
		    public List<String> extractData(ResultSet rsEffort) throws SQLException,DataAccessException{
		    	List<String> tempList1= new LinkedList<String>();					
					while(rsEffort.next()){
						String tempS = rsEffort.getString(1);
						tempList1.add(tempS.substring(8,10)+"/"+tempS.substring(5,7)+" to "+tempS.substring(19,21)+"/"+tempS.substring(16,18));
					}							
				return tempList1;
		    } 			
		});	
		headerRow.addAll(temp);
		return headerRow;
	}
	public boolean generateDailyTSReport(HttpSession session,JSONArray data) throws FileNotFoundException, IOException {
		List<List<String>> recordToAdd = new ArrayList<List<String>>();
		List<String> headerRow = new LinkedList<String>();
		headerRow.add("S No");
		headerRow.add("Name");
		headerRow.add("Project");
		headerRow.add("Task");
		headerRow.add("Effort");
		headerRow.add("Comment");
		headerRow.add("Submitted On");
		headerRow.add("Last Modified On");
		headerRow.add("LastModified By");
		headerRow.add("Reporting Manager");
		headerRow.add("Status");
		headerRow.add("Leave Type");
		headerRow.add("Leave Flag");
		headerRow.add("Leave Status");
		headerRow.add("Applied On");
		headerRow.add("Last Modified On");
		headerRow.add("Last Modified By");
		headerRow.add("Purpose");
		recordToAdd.add(headerRow);
		List<List<String>> dataRow = generateDataForDailyTSReport(data);
		recordToAdd.addAll(dataRow);
		return createXLTSDailyReport(recordToAdd);
	}

	private List<List<String>> generateDataForDailyTSReport(JSONArray data) {
		List<List<String>> userData = new LinkedList<List<String>>();
		try {
			for(int i =0;i<data.length();i++){
				JSONObject temp = data.getJSONObject(i);
				List<String> row  =new LinkedList<String>();
				row.add(String.valueOf(i+1));
				row.add(temp.getString("fullname"));
				row.add(temp.getString("projectname"));
				row.add(temp.getString("taskname"));
				row.add(temp.getString("effort"));
				row.add(temp.getString("comment"));
				row.add(temp.getString("submittedon"));
				row.add(temp.getString("lastmodifiedon"));
				row.add(temp.getString("lasmodifiedby"));
				row.add(temp.getString("rmname"));
				row.add(temp.getString("status"));
				
				row.add(temp.getString("leaveType"));
				row.add(temp.getString("leaveFlag"));
				row.add(temp.getString("leavestatus"));
				row.add(temp.getString("appliedon"));
				row.add(temp.getString("approvedon"));
				row.add(temp.getString("approvedby"));
				row.add(temp.getString("purpose"));
				userData.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userData;
	}

	private boolean createXLTSDailyReport(List<List<String>> recordToAdd) throws IOException {
		String tempString = "";
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet firstSheet = workbook.createSheet("User_Task_Sheet");
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor(HSSFColor.RED.index);
        style.setFont(font);
        CellStyle style1 = workbook.createCellStyle();
        Font font1 = workbook.createFont();
        font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style1.setFont(font1);
        int columnNumber = 5;
		try {
			int rownum = 0;
			for (int j = 0; j < recordToAdd.size(); j++) {
				Row row = firstSheet.createRow(rownum);
				List<String> rowData= recordToAdd.get(j);
				columnNumber = rowData.size();
				for(int k=0; k<rowData.size(); k++)
				{
					Cell cell = row.createCell(k);
					if(rownum == 0)
						cell.setCellStyle(style1);
					cell.setCellValue(rowData.get(k));
					
				}
				rownum++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		 tempString =  new SimpleDateFormat("yyyyMMdd'.xlsx'").format(new Date());
		 tempString = "TSDailyReport"+tempString;
		 for (int i=0; i<columnNumber; i++){
	        	firstSheet.autoSizeColumn(i);
	     }
		 try (FileOutputStream outputStream = new FileOutputStream(tempString)) {
	            workbook.write(outputStream);
	        }
		return true;
	}
}
