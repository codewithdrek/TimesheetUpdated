package com.supraits.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.supraits.dao.impl.GetQueryAPI;

@Service
public class LeaveReportImpl {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Value("${LMS27}")
	private String LMS27;
	@Value("${LMS28}")
	private String LMS28;
	@Value("${LMS29}")
	private String LMS29;
	@Value("${LMS30}")
	private String LMS30;
	@Value("${LMS31}")
	private String LMS31;
	
	public String createMonthlyLevaeReport(HttpSession session,String policyGroup) throws FileNotFoundException, IOException {
		List<List<String>> recordToAdd = new ArrayList<List<String>>();
		List<String> headerRow = generateHeadersForLeaveBalanceReport(policyGroup);
		recordToAdd.add(headerRow);
		List<List<String>> dataRow = generateDataForLeaveBalanceReport(policyGroup);
		recordToAdd.addAll(dataRow);
		return createXLSLeaveReport(recordToAdd);
	}

	private String createXLSLeaveReport(List<List<String>> recordToAdd) throws FileNotFoundException, IOException {
		String tempString = "";
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet firstSheet = workbook.createSheet("User_Leave_Balance_Sheet");
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
		 tempString = "LeaveSheet" + tempString;
		 for (int i=0; i<columnNumber; i++){
	        	firstSheet.autoSizeColumn(i);
	     }
		 try (FileOutputStream outputStream = new FileOutputStream(tempString)) {
	            workbook.write(outputStream);
	        }
		return tempString;
	}

	private List<List<String>> generateDataForLeaveBalanceReport(String policyGroup) {
		List<String> leaveType = jdbcTemplate.queryForList(GetQueryAPI.getQuery(LMS27,policyGroup),String.class);
		StringBuffer queryString = new StringBuffer();
		for (String list: leaveType){
			queryString.append(GetQueryAPI.getQuery(LMS28,list,list));	
		}
		List<List<String>> userData = new LinkedList<List<String>>();
		final List<String> tempList1 = leaveType;
		try{
			userData = jdbcTemplate.query(GetQueryAPI.getQuery(LMS29,queryString.toString(),policyGroup), new ResultSetExtractor<List<List<String>>>(){
				@Override
				public List<List<String>> extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					List<List<String>> temp = new LinkedList<List<String>>();
					while(rs.next()){
						List<String> tempList = new LinkedList<String>();
						tempList.add(rs.getString(tempList1.size()+2));
						tempList.add(rs.getString(tempList1.size()+3));
						tempList.add(rs.getString(tempList1.size()+1));
						int k = 1;
						for (@SuppressWarnings("unused") String list: tempList1){
							if(null == rs.getString(k))
								tempList.add("0");
							else
								tempList.add(rs.getString(k));
							k++;
						}
						temp.add(tempList);
					}
					return temp;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return userData;
	}

	private List<String> generateHeadersForLeaveBalanceReport(String policyGroup) {
		List<String> headerList = new LinkedList<String>();
		headerList.add("Username");
		headerList.add("Employee Name");
		headerList.add("Employee Leave Band");
		headerList.addAll(jdbcTemplate.queryForList(GetQueryAPI.getQuery(LMS30,policyGroup),String.class));
		return headerList;
	}
	@SuppressWarnings("static-access")
	public void uploadLeaveBalanceData(String policyGroup) {
		
		final String FILE_NAME = System.getProperty("user.dir") + File.separator +"LeaveBalanceExcel.xlsx";
		List<String> leaveType = jdbcTemplate.queryForList(GetQueryAPI.getQuery(LMS27,policyGroup),String.class);
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            List<List<String>> tempRowList = new LinkedList<List<String>>();
            Iterator<Row> iterator = datatypeSheet.iterator();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                //System.out.println(currentRow.getRowNum());
                //Skipping first row since containing headers
                if(currentRow.getRowNum() > 0){
	                List<String> tempList = new LinkedList<String>();
	                Iterator<Cell> cellIterator = currentRow.iterator();
	                while (cellIterator.hasNext()) {
	                    Cell currentCell = cellIterator.next();
	                    if(currentCell.getCellType() == currentCell.CELL_TYPE_NUMERIC)
	                    	tempList.add(String.valueOf(currentCell.getNumericCellValue()));
	                    else
	                    	tempList.add(currentCell.getStringCellValue());
	                }
	                tempRowList.add(tempList);
            	}
            }
            Iterator<List<String>> itrRowList = tempRowList.iterator();
            while(itrRowList.hasNext()){
            	List<String> temp = itrRowList.next();
            	int t =3;
            	for(String leave: leaveType){
            		String username = temp.get(0);
            		String leavebandid = temp.get(2);
            		String leavecode = leave;
            		String leavebalance = temp.get(t);
            		if(null == leavebandid || leavebandid.length() == 0)
            			leavebandid = "1.0";
            		System.out.println(GetQueryAPI.getQuery(LMS31,temp.get(0),leavebandid,leave,temp.get(t),String.valueOf(year),temp.get(t),leavebandid));
            		jdbcTemplate.update(GetQueryAPI.getQuery(LMS31,temp.get(0),leavebandid,leave,temp.get(t),String.valueOf(year),temp.get(t),leavebandid));
            		t++;
            	}	
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
