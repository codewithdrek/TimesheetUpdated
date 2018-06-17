package com.supraits.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
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
public class AttendenceReportImpl {
	
	@Value("${ATN3}")
	private String ATN3;
	
	@Value("${ATN4}")
	private String ATN4;
	
	@Value("${ATN5}")
	private String ATN5;
	@Value("${ATN6}")
	private String ATN6;
	@Value("${ATN7}")
	private String ATN7;
	@Value("${ATN8}")
	private String ATN8;
	
	@Value("${TM21}")
	private String TM21;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date = new Date();
	String currentDate = sdf.format(date);
	DecimalFormat decimalFormat = new DecimalFormat("####0.00");
	
	public boolean createReportProjWeekly(String startDate1,
			String endDate1, String username, String projectId) throws ParseException, FileNotFoundException, IOException {
				//Header Row creation for xls file
				@SuppressWarnings("rawtypes")
				List<List> recordToAdd = new ArrayList<List>();
				List<String> headerRow = generateHeadersForStatusReport(startDate1,endDate1);
				//System.out.println(generateHeadersForStatusReport(startDate1,endDate1));
				recordToAdd.add(headerRow);
				List<List<String>> dataRow = generateDataForStatusReport(username,startDate1,endDate1,projectId);
				if(dataRow.size()>0){
					for(List<String> l:dataRow)
						recordToAdd.add(l);
				}else{
					return false;
				}
		return createXLSWeeklyReport(recordToAdd,startDate1,endDate1);
	}
	private List<String> generateHeadersForStatusReport(String startDate1,
			String endDate1) throws ParseException {

		List<String> headerRow = new ArrayList<String>();
		headerRow.add("SR#");
		headerRow.add("Username");
		headerRow.add("Full Name");
		List<Date> temp = getDaysBetweenDates(sdf.parse(startDate1+" 00:00:01"), sdf.parse(endDate1+" 23:59:59"));
		for(Date d:temp){
			headerRow.add("In Time");
			headerRow.add("Out Time");
			headerRow.add("Punched HH");
			headerRow.add("Def/Extra HH");
		}
		return headerRow;
	}
	@SuppressWarnings("deprecation")
	private boolean createXLSWeeklyReport(List<List> recordToAdd,String startDate1,
			String endDate1) throws FileNotFoundException, IOException {
		boolean createStatus = false;
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet firstSheet = workbook.createSheet("Sheet0");
        
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor(HSSFColor.RED.index);
        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        
        CellStyle stylered = workbook.createCellStyle();
        Font fontred = workbook.createFont();
        fontred.setColor(HSSFColor.GREEN.index);
        stylered.setFont(fontred);
        stylered.setAlignment(CellStyle.ALIGN_RIGHT);
        
        CellStyle style1 = workbook.createCellStyle();
        Font font1 = workbook.createFont();
        font1.setColor(HSSFColor.GREEN.index);
        style1.setFont(font1);
        XSSFFont headF= workbook.createFont();
        headF.setColor(IndexedColors.BLACK.getIndex());
        headF.setBold(true);
        CellStyle cStyle = workbook.createCellStyle();
        cStyle.setFillBackgroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        cStyle.setFont(headF);
        int columnNumber = 5;
		try {
			int rownum = 0;
			Row row = firstSheet.createRow(rownum);
			Cell cell1 = row.createCell(0);
			cell1.setCellValue("Attendence Report");
			cell1.setCellStyle(cStyle);
			firstSheet.addMergedRegion(new CellRangeAddress(
			        0, //first row (0-based)
			        0, //last row  (0-based)
			        0, //first column (0-based)
			        2  //last column  (0-based)
			));
			/*Cell cell2 = row.createCell(1);
			cell2.setCellValue("");
			cell2.setCellStyle(cStyle);
			Cell cell3 = row.createCell(2);
			cell3.setCellValue("");
			cell3.setCellStyle(cStyle);*/
			List<Date> dateL = getDaysBetweenDates(sdf.parse(startDate1+" 00:00:01"), sdf.parse(endDate1+" 23:59:59"));
			int h =3;
			for(Date d: dateL){
				Cell cell4 = row.createCell(h);
				cell4.setCellValue(sdf.format(d));
				cell4.setCellStyle(cStyle);
				firstSheet.addMergedRegion(new CellRangeAddress(
				        0, //first row (0-based)
				        0, //last row  (0-based)
				        h, //first column (0-based)
				        h+3  //last column  (0-based)
				));
				h=h+4;
			}
			rownum =1;
			for (int j = 0; j < recordToAdd.size(); j++) {
			    row = firstSheet.createRow(rownum);
				@SuppressWarnings("unchecked")
				List<String> rowData= recordToAdd.get(j);
				columnNumber = rowData.size();
				for(int k=0; k<rowData.size(); k++)
				{
					if(rownum == 1){
						Cell cell = row.createCell(k);
						cell.setCellStyle(cStyle);
						cell.setCellValue(rowData.get(k));
					}else{
						Cell cell = row.createCell(k);
						if("0".equalsIgnoreCase(rowData.get(k))){
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(Integer.parseInt(rowData.get(k)));
						}else{
							cell.setCellValue(rowData.get(k));
						}
						if(rowData.get(k).contains("-")){
							cell.setCellStyle(style);
						}
						if(rowData.get(k).contains("+")){
							cell.setCellStyle(stylered);
						}
					}	
				}
				rownum++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		 String tempString =  new SimpleDateFormat("yyyyMMdd'.xlsx'").format(new Date());
		 tempString = "Attendence_Report_" + tempString;
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
			final String startDate1, String endDate1, String projectId) {
		List<List<String>> userDataListMain = new LinkedList<List<String>>();
		String query = "";
		String stDate = startDate1+" 00:00:01"; 
		String endDate = endDate1+" 23:59:59";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date endD = formatter.parse(endDate1);
			Date startD = formatter.parse(startDate1);
			if(username.equalsIgnoreCase("select")){
				String userListQuery = "";
				if(projectId.equalsIgnoreCase("select"))
					userListQuery = GetQueryAPI.getQuery(ATN6);
				else
					userListQuery = GetQueryAPI.getQuery(ATN8,projectId);
				List<List<String>>	userData = jdbcTemplate.query(userListQuery, new ResultSetExtractor<List<List<String>>>(){
				    @Override
				    public List<List<String>> extractData(ResultSet rsEffort) throws SQLException,DataAccessException{
				    	List<List<String>> tempList1= new LinkedList<List<String>>();					
							while(rsEffort.next()){
								List<String> tempList= new ArrayList<String>();
								tempList.add(rsEffort.getString(1));//username
								tempList.add(rsEffort.getString(2));//empid
								tempList.add(rsEffort.getString(3));//fullname
								tempList1.add(tempList);
							}							
						return tempList1;
				    } 			
				});
				//System.out.println(userData);
				for(int i=0;i<userData.size();i++){
					String empid = userData.get(i).get(1);
					query = GetQueryAPI.getQuery(ATN7,stDate,endDate,empid);
					 //System.out.println("username query = "+query);
					 List<List<String>> userLogData = jdbcTemplate.query(query, new ResultSetExtractor<List<List<String>>>(){
						    @Override
						    public List<List<String>> extractData(ResultSet rsEffort) throws SQLException,DataAccessException{
						    	List<List<String>> tempList1= new LinkedList<List<String>>();					
									while(rsEffort.next()){
										List<String> tempList= new ArrayList<String>();
										tempList.add(rsEffort.getString(1));//Date
										tempList.add(rsEffort.getString(2));//intime
										tempList.add(rsEffort.getString(3));//outime
										tempList1.add(tempList);
									}							
								return tempList1;
						    } 			
					 });
					 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					 List<Date> allDates = getDaysBetweenDates(sdf.parse(startDate1+" 00:00:01"), sdf.parse(endDate1+" 23:59:59"));
					 List<String> rowToAdd = new LinkedList<String>();
					 rowToAdd.add(String.valueOf(i+1));
					 rowToAdd.add(userData.get(i).get(1));
					 rowToAdd.add(userData.get(i).get(2));
					 //System.out.println(allDates);
					  for(int l=0;l<allDates.size();l++){
						 Date d = allDates.get(l);
						 String tempDate = sdf1.format(d);
						 int z = 0;
					     for(int m=0;m<userLogData.size();m++){	 
							 if(tempDate.equalsIgnoreCase(userLogData.get(m).get(0))){
								 String intime = userLogData.get(m).get(1).substring(11, 19);
								 String outtime = userLogData.get(m).get(2).substring(11, 19);
								 rowToAdd.add(intime);
								 rowToAdd.add(outtime);
								 long diff = 0;
								 Date d1 = sdf.parse(userLogData.get(m).get(1));
							     Date d2 = sdf.parse(userLogData.get(m).get(2));
							     diff = d2.getTime() - d1.getTime();
							     rowToAdd.add(String.format("%02d",(diff / (60 * 60 * 1000) % 24))+":"+ String.format("%02d",(diff / (60 * 1000) % 60)) +":"+ String.format("%02d",(diff / 1000 % 60)));
							     if((d2.after(d1) || d2.equals(d1)) && diff >= 0 ){
										float extraOrDiffHours = diff - (9*60*60*1000);
										if(extraOrDiffHours >= 0){
											rowToAdd.add("+"+String.valueOf(decimalFormat.format(extraOrDiffHours/3600000)));
											//rowToAdd.add(String.format("%02d",(extraOrDiffHours / (60 * 60 * 1000) % 24))+":"+ String.format("%02d",(extraOrDiffHours / (60 * 1000) % 60)) +":"+ String.format("%02d",(extraOrDiffHours / 1000 % 60)));
										}else{
											extraOrDiffHours = (9*60*60*1000) - diff;
											rowToAdd.add("-"+String.valueOf(decimalFormat.format(extraOrDiffHours/3600000)));
											//rowToAdd.add("-"+String.format("%02d",(extraOrDiffHours / (60 * 60 * 1000) % 24))+":"+ String.format("%02d",(extraOrDiffHours / (60 * 1000) % 60)) +":"+ String.format("%02d",(extraOrDiffHours / 1000 % 60)));
									    }
									}else{
										String dayName = new SimpleDateFormat("EEEE").format(sdf1.parse(userLogData.get(m).get(0)));
										if(dayName.equalsIgnoreCase("Sunday") || dayName.equalsIgnoreCase("Saturday"))
											rowToAdd.add("Week Off");	
										else
											rowToAdd.add("Absent");
									}
							     z = 1;
							 }
						 }
					  if(z == 0){
						  String dayName = new SimpleDateFormat("EEEE").format(d);
							if(dayName.equalsIgnoreCase("Sunday") || dayName.equalsIgnoreCase("Saturday")){
								rowToAdd.add("Week Off");rowToAdd.add("");rowToAdd.add("");rowToAdd.add(""); 
					  		}else{
								rowToAdd.add("0");rowToAdd.add("0");rowToAdd.add("0");rowToAdd.add("0");
							}	
					  }
					 }
					  //System.out.println("rowToadd"+rowToAdd);
					 userDataListMain.add(rowToAdd);
				}
			}else{
				List<String> user = new LinkedList<String>();
				user.add("1");
				user.add(username);
				user.add(username);
				List<List<String>>	userData = new LinkedList<List<String>>();
				userData.add(user);
				for(int i=0;i<userData.size();i++){
					String empid = userData.get(i).get(1);
					query = GetQueryAPI.getQuery(ATN7,stDate,endDate,empid);
					 //System.out.println("username query = "+query);
					 List<List<String>> userLogData = jdbcTemplate.query(query, new ResultSetExtractor<List<List<String>>>(){
						    @Override
						    public List<List<String>> extractData(ResultSet rsEffort) throws SQLException,DataAccessException{
						    	List<List<String>> tempList1= new LinkedList<List<String>>();					
									while(rsEffort.next()){
										List<String> tempList= new ArrayList<String>();
										tempList.add(rsEffort.getString(1));//Date
										tempList.add(rsEffort.getString(2));//intime
										tempList.add(rsEffort.getString(3));//outime
										tempList1.add(tempList);
									}							
								return tempList1;
						    } 			
					 });
					 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					 List<Date> allDates = getDaysBetweenDates(sdf.parse(startDate1+" 00:00:01"), sdf.parse(endDate1+" 23:59:59"));
					 List<String> rowToAdd = new LinkedList<String>();
					 rowToAdd.add(String.valueOf(i+1));
					 rowToAdd.add(userData.get(i).get(1));
					 rowToAdd.add(userData.get(i).get(2));
					 //System.out.println(allDates);
					  for(int l=0;l<allDates.size();l++){
						 Date d = allDates.get(l);
						 String tempDate = sdf1.format(d);
						 int z = 0;
					     for(int m=0;m<userLogData.size();m++){	 
							 if(tempDate.equalsIgnoreCase(userLogData.get(m).get(0))){
								 String intime = userLogData.get(m).get(1).substring(11, 19);
								 String outtime = userLogData.get(m).get(2).substring(11, 19);
								 rowToAdd.add(intime);
								 rowToAdd.add(outtime);
								 long diff = 0;
								 Date d11 = sdf.parse(userLogData.get(m).get(1));
							     Date d22 = sdf.parse(userLogData.get(m).get(2));
							     diff = d22.getTime() - d11.getTime();
							     //System.out.println(d11.getTime()+"d1111==="+userLogData.get(m).get(1));
							     rowToAdd.add(String.format("%02d",(diff / (60 * 60 * 1000) % 24))+":"+ String.format("%02d",(diff / (60 * 1000) % 60)) +":"+ String.format("%02d",(diff / 1000 % 60)));
									if(d22.after(d11) && diff > 0){
										float extraOrDiffHours = diff - (9*60*60*1000);
										if(extraOrDiffHours >= 0){
											rowToAdd.add("+"+String.valueOf(decimalFormat.format(extraOrDiffHours/3600000)));
										}else{
											extraOrDiffHours = (9*60*60*1000) - diff;
											rowToAdd.add("-"+String.valueOf(decimalFormat.format(extraOrDiffHours/3600000)));
									    }
									}else{
										String dayName = new SimpleDateFormat("EEEE").format(sdf1.parse(userLogData.get(m).get(0)));
										if(dayName.equalsIgnoreCase("Sunday") || dayName.equalsIgnoreCase("Saturday"))
											rowToAdd.add("Week Off");	
										else
											rowToAdd.add("Absent");
									}
							     z = 1;
							 }
						 }
					  if(z == 0){
						  String dayName = new SimpleDateFormat("EEEE").format(d);
							if(dayName.equalsIgnoreCase("Sunday") || dayName.equalsIgnoreCase("Saturday")){
								rowToAdd.add("Week Off");rowToAdd.add("");rowToAdd.add("");rowToAdd.add(""); 
					  		}else{
								rowToAdd.add("0");rowToAdd.add("0");rowToAdd.add("0");rowToAdd.add("0");
							}	
					  }
					 }
					  //System.out.println("rowToadd"+rowToAdd);
					 userDataListMain.add(rowToAdd);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//System.out.println(userDataListMain.size());
		return userDataListMain;
	}
	public List<Date> getDaysBetweenDates(Date startdate, Date enddate)
	{
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startdate);

	    while (calendar.getTime().before(enddate))
	    {
	        Date result = calendar.getTime();
	        dates.add(result);
	        calendar.add(Calendar.DATE, 1);
	    }
	    /*Calendar callast = new GregorianCalendar();
	    callast.setTime(enddate);
	    dates.add(callast.getTime());*/
	    return dates;
	}
	public void createWeeklyReportTotalAttendance(JSONArray data) {
		List<List> recordToAdd = new ArrayList<List>();
		List<String> headerRow = generateHeadersForWeeklyAttnReport();
		recordToAdd.add(headerRow);
		List<List<String>> dataRow = generateDataForWeeklyAttnReport(data);
		for(List list:dataRow)
			recordToAdd.add(list);
		createXLSAttendenceReport(recordToAdd);
	}
	private void createXLSAttendenceReport(List<List> recordToAdd) {
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet firstSheet = workbook.createSheet("Sheet0");
        
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor(HSSFColor.RED.index);
        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        
        CellStyle stylered = workbook.createCellStyle();
        Font fontred = workbook.createFont();
        fontred.setColor(HSSFColor.GREEN.index);
        stylered.setFont(fontred);
        stylered.setAlignment(CellStyle.ALIGN_RIGHT);
        
        CellStyle style1 = workbook.createCellStyle();
        Font font1 = workbook.createFont();
        font1.setColor(HSSFColor.GREEN.index);
        style1.setFont(font1);
        
        XSSFFont headF= workbook.createFont();
        headF.setColor(IndexedColors.BLACK.getIndex());
        headF.setBold(true);
        CellStyle cStyle = workbook.createCellStyle();
        cStyle.setFillBackgroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        cStyle.setFont(headF);
        int columnNumber = 5;
		try {
			int rownum = 0;
			rownum =1;
			Row row = firstSheet.createRow(rownum);
			for (int j = 0; j < recordToAdd.size(); j++) {
			    row = firstSheet.createRow(rownum);
				@SuppressWarnings("unchecked")
				List<String> rowData= recordToAdd.get(j);
				columnNumber = rowData.size();
				for(int k=0; k<rowData.size(); k++)
				{
					if(rownum == 0){
						Cell cell = row.createCell(k);
						cell.setCellStyle(cStyle);
						cell.setCellValue(rowData.get(k));
					}else{
						Cell cell = row.createCell(k);
						if("0".equalsIgnoreCase(rowData.get(k))){
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(Integer.parseInt(rowData.get(k)));
						}else{
							cell.setCellValue(rowData.get(k));
						}
						if(rowData.get(k).contains("-")){
							cell.setCellStyle(style);
						}
						if(rowData.get(k).contains("+")){
							cell.setCellStyle(stylered);
						}
					}	
				}
				rownum++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 String tempString =  new SimpleDateFormat("yyyyMMdd'.xlsx'").format(new Date());
		 tempString = "Attendence_Weekly_Report_" + tempString;
		 for (int i=0; i<columnNumber; i++){
	        	firstSheet.autoSizeColumn(i);
	     }
		 try (FileOutputStream outputStream = new FileOutputStream(tempString)) {
	            workbook.write(outputStream);
	     }catch(Exception e){
	    	 e.printStackTrace();
	     }
	}
	private List<List<String>> generateDataForWeeklyAttnReport(JSONArray data) {
		List<List<String>> dataList = new LinkedList<List<String>>();
		try{
			for(int i=0;i<data.length();i++){
				List<String> temp = new LinkedList<String>();
				temp.add(String.valueOf(i+1));
				temp.add(data.getJSONObject(i).getString("weekinterval"));
				temp.add(data.getJSONObject(i).getString("username"));
				temp.add(data.getJSONObject(i).getString("fullname"));
				temp.add(data.getJSONObject(i).getString("workinghours"));
				temp.add(data.getJSONObject(i).getString("punchedhours"));
				temp.add(data.getJSONObject(i).getString("totaldiff"));
				dataList.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return dataList;
	}
	private List<String> generateHeadersForWeeklyAttnReport() {
		List<String> headerRow = new ArrayList<String>();
		headerRow.add("SR#");
		headerRow.add("Week Interval");
		headerRow.add("Username");
		headerRow.add("Full Name");
		headerRow.add("Working Hours");
		headerRow.add("Punched Hour");
		headerRow.add("Deficent/Extra HH");
		return headerRow;
	}
	public void createOneUserMonthlyAttendanceReport(JSONArray jsonarr,String username) {
		try {
			System.out.println(GetQueryAPI.getQuery(TM21, username));
			String empfullname = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM21, username),String.class);
			@SuppressWarnings("rawtypes")
			List<List<String>> recordToAdd = new ArrayList<List<String>>();
			List<String> headerRow = generateHeadersForMonthlyReport();
			recordToAdd.add(headerRow);
			List<List<String>> dataRow = new LinkedList<List<String>>();
			for(int l=0;l<jsonarr.length();l++){
				JSONObject obj = jsonarr.getJSONObject(l);
				List<String> temp = new LinkedList<String>();
				temp.add(String.valueOf(l+1));
				temp.add(username);
				temp.add(empfullname);
				temp.add(obj.getString("date"));
				temp.add(obj.getString("intime"));
				temp.add(obj.getString("outtime"));
				temp.add(obj.getString("punchedcount"));
				if(obj.has("extrahours")){
					if(obj.getString("extrahours").equals("0"))
						temp.add("0");
					else	
						temp.add("+" + obj.getString("extrahours"));
				}else if(obj.has("deficienthours"))
					temp.add(obj.getString("deficienthours"));
				else
					temp.add("");
				temp.add(obj.getString("status"));
				dataRow.add(temp);
			}
			if(dataRow.size()>0){
				for(List<String> s:dataRow)
					recordToAdd.add(s);
			}
			createXLSMonthlyAttnReport(recordToAdd);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void createXLSMonthlyAttnReport(List<List<String>> recordToAdd) {

		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet firstSheet = workbook.createSheet("Sheet0");
        
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor(HSSFColor.RED.index);
        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        
        CellStyle stylered = workbook.createCellStyle();
        Font fontred = workbook.createFont();
        fontred.setColor(HSSFColor.GREEN.index);
        stylered.setFont(fontred);
        stylered.setAlignment(CellStyle.ALIGN_RIGHT);
        
        CellStyle style1 = workbook.createCellStyle();
        Font font1 = workbook.createFont();
        font1.setColor(HSSFColor.GREEN.index);
        style1.setFont(font1);
        
        XSSFFont headF= workbook.createFont();
        headF.setColor(IndexedColors.BLACK.getIndex());
        headF.setBold(true);
        CellStyle cStyle = workbook.createCellStyle();
        cStyle.setFillBackgroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        cStyle.setFont(headF);
        int columnNumber = 5;
		try {
			int rownum = 0;
			rownum =1;
			Row row = firstSheet.createRow(rownum);
			for (int j = 0; j < recordToAdd.size(); j++) {
			    row = firstSheet.createRow(rownum);
				@SuppressWarnings("unchecked")
				List<String> rowData= recordToAdd.get(j);
				columnNumber = rowData.size();
				for(int k=0; k<rowData.size(); k++)
				{
					if(rownum == 1){
						Cell cell = row.createCell(k);
						cell.setCellStyle(cStyle);
						cell.setCellValue(rowData.get(k));
					}else{
						Cell cell = row.createCell(k);
						if(rowData.get(k).contains("-")){
							cell.setCellStyle(style);
						}
						if(rowData.get(k).contains("+")){
							cell.setCellStyle(stylered);
						}
						if("0".equalsIgnoreCase(rowData.get(k))){
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(Integer.parseInt(rowData.get(k)));
						}else{
							cell.setCellValue(rowData.get(k));
						}
					}	
				}
				rownum++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 String tempString =  new SimpleDateFormat("yyyyMMdd'.xlsx'").format(new Date());
		 tempString = "Attendence_Weekly_Report_" + tempString;
		 for (int i=0; i<columnNumber; i++){
	        	firstSheet.autoSizeColumn(i);
	     }
		 try (FileOutputStream outputStream = new FileOutputStream(tempString)) {
	            workbook.write(outputStream);
	     }catch(Exception e){
	    	 e.printStackTrace();
	     }
	
	}
	private List<String> generateHeadersForMonthlyReport() throws ParseException {
		List<String> headerRow = new ArrayList<String>();
		headerRow.add("SR#");
		headerRow.add("Username");
		headerRow.add("Full Name");
		headerRow.add("Date");
		headerRow.add("In Time");
		headerRow.add("Out Time");
		headerRow.add("Punched HH");
		headerRow.add("Def/Extra HH");
		headerRow.add("Status");
		return headerRow;
	}
}
