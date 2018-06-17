package com.supraits.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.supraits.dao.impl.GetQueryAPI;

@Service
public class RMSReportImpl {
	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	Date date = new Date();
	String currentDate = sdf.format(date);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	ServletContext servletContext;
	
	@Value("${RMS26}")
	private String RMS26;
	@Value("${RMS40}")
	private String RMS40;
	@Value("${RMS41}")
	private String RMS41;
	@Value("${RMS42}")
	private String RMS42;
	
	public String createMonthlyRMSReport(HttpSession session,
			String currentBucket) throws IOException {
		Map<String,String> getReportDataMap = new HashMap<String,String>(); 
		String tempString = "";
		File file;
		try{
			if(currentBucket.equalsIgnoreCase("9")){
				file = new File( servletContext.getRealPath("/WEB-INF/resource/Bank_Reference_Sheet.xls") );
				getReportDataMap = getRMSDataForBankRefUpdation(session,currentBucket);
			}else{
				file = new File( servletContext.getRealPath("/WEB-INF/resource/Wire_Transfer_Reimb_HSBC.xls") );
				getReportDataMap = getRMSReportData(session,currentBucket);
			}
			FileInputStream templateFile= new FileInputStream(file);
	    	HSSFWorkbook wb = new HSSFWorkbook(templateFile);
	    	HSSFSheet worksheet =  wb.getSheetAt(0); 
	    Iterator<Entry<String, String>> it = getReportDataMap.entrySet().iterator();
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
	    for (Row row : worksheet) {
            for (Cell cellTemp : row) {
            	cellTemp.setCellType(Cell.CELL_TYPE_STRING);
                if(cellTemp.getStringCellValue().contains("TAG")){
                	cellTemp.setCellValue("");
                }
            }
        }
	    templateFile.close();
	    tempString =  new SimpleDateFormat("yyyyMMdd HH:mm'.xls'").format(new Date());
	    tempString = "RMSReport" +tempString;
	    System.out.println(tempString);	
		 try (FileOutputStream outputStream = new FileOutputStream(tempString)) {
			 wb.write(outputStream);
			 outputStream.close();
	        }
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		return tempString;
	}
	private Map<String, String> getRMSDataForBankRefUpdation(
			HttpSession session, String currentBucket) {
		Map<String, String> reportData = new HashMap<String,String>();
		String query = "";
		try{
			query = GetQueryAPI.getQuery(RMS40, currentBucket);
			System.out.println(query);
			reportData = jdbcTemplate.query(query, new ResultSetExtractor<Map<String, String>>(){
				@Override
				public Map<String, String> extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					int i = 1;
					Map<String, String> temp = new HashMap<String,String>();
					/*Calendar cal = Calendar.getInstance();
					String currMonth = new SimpleDateFormat("MMM").format(cal.getTime());
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.MONTH, 1);  
					calendar.set(Calendar.DAY_OF_MONTH, 1);  
					calendar.add(Calendar.DATE, -1);  
					Date date = calendar.getTime();
					String endDateOfMonth = new SimpleDateFormat("dd/MM/yyyy").format(date);*/
					DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = new Date();
					String currentDate = sdf.format(date);
					while(rs.next()){
						temp.put("RMS_REQUESTNO_TAG_"+i,rs.getString(1));
						temp.put("USERNAME_TAG_"+i,rs.getString(2));
						temp.put("USERFULLNAME_TAG_"+i,rs.getString(3));
						temp.put("AMOUNT_TAG_"+i, String.valueOf(rs.getDouble(4)));
						temp.put("PROCESSINGDATE_TAG_"+i, currentDate);
						temp.put("BANKREFNO_TAG_"+i, "Update Transaction-Id here");
						i = i+1;
					}
					return temp;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return reportData;
	}
	private Map<String, String> getRMSReportData(HttpSession session,
			String currentBucket) throws IOException {
		Properties properties = new Properties();
		final Map<String, Object> map = new HashMap<String,Object>();
		Map<String, String> reportData = new HashMap<String,String>();
		String query = "";
		properties.load(RMSReportImpl.class.getResourceAsStream("rmstemplate.properties"));	
		for (String key : properties.stringPropertyNames()) {
		    String value = properties.getProperty(key);
		    map.put(key, String.valueOf(value));
		}
		try{
			query = GetQueryAPI.getQuery(RMS26, currentBucket);
			System.out.println(query);
			reportData = jdbcTemplate.query(query, new ResultSetExtractor<Map<String, String>>(){
				@Override
				public Map<String, String> extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					int i = 1;
					Map<String, String> temp = new HashMap<String,String>();
					Calendar cal = Calendar.getInstance();
					String currMonth = new SimpleDateFormat("MMM").format(cal.getTime());
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.MONTH, 1);  
					calendar.set(Calendar.DAY_OF_MONTH, 1);  
					calendar.add(Calendar.DATE, -1);  
					Date date = calendar.getTime();
					String endDateOfMonth = new SimpleDateFormat("dd/MM/yyyy").format(date);
					while(rs.next()){
						temp.put("TRANSACTION_TYPE_TAG_"+i, (String) map.get("BENE_ACC_TRANSACTION_TYPE"));
						if(null == rs.getString(1))
							temp.put("REFERENCE_NUMBER_TAG_"+i, "Supra-"+currMonth+"-Reimb-USER_NAME");
						else
							temp.put("REFERENCE_NUMBER_TAG_"+i, "Supra-"+currMonth+"-Reimb-"+rs.getString(1));
						temp.put("DR_ACCOUNT_NUMBER_TAG_"+i, (String) map.get("SUPRA_ACC_NO"));
						if(null == rs.getString(1))
							temp.put("BENEFITIARY_NAME_TAG_"+i, "USER_NAME");
						else
							temp.put("BENEFITIARY_NAME_TAG_"+i, rs.getString(1));
						temp.put("VALUE_DATE_TAG_"+i, endDateOfMonth);
						temp.put("AMOUNT_TAG_"+i, String.valueOf(rs.getDouble(2)));
						temp.put("FREE_TEXT_TAG_"+i, rs.getString(3));
						if(null == rs.getString(4))
							temp.put("BENE_BANK_ACC_NO_TAG_"+i, "00000");
						else
							temp.put("BENE_BANK_ACC_NO_TAG_"+i, rs.getString(4));
						if(null == rs.getString(5))
							temp.put("BENE_BANK_IFSC_TAG_"+i, "");
						else
							temp.put("BENE_BANK_IFSC_TAG_"+i, rs.getString(5));
						i = i+1;
					}
					return temp;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return reportData;
	}
	public boolean uploadRMSBankRefData() {
		final String FILE_NAME = System.getProperty("user.dir") + File.separator +"BankReferencesExcel.xlsx";
		boolean status = false;
		try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            List<List<String>> tempRowList = new LinkedList<List<String>>();
            Iterator<Row> iterator = datatypeSheet.iterator();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                List<String> tempList = new LinkedList<String>();
                Iterator<Cell> cellIterator = currentRow.iterator();
                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    tempList.add(currentCell.getStringCellValue());
                    //System.out.print(currentCell.getStringCellValue());
                }
                tempRowList.add(tempList);
            }
            Iterator<List<String>> itrRowList = tempRowList.iterator();
            while(itrRowList.hasNext()){
            	List<String> temp = itrRowList.next();
            	String currBuckId = "";
            	try{
            	 	currBuckId = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS42,temp.get(0)),String.class);
            		}catch(Exception e){}		
            	 if(null != currBuckId && currBuckId.equalsIgnoreCase("9"))
            		jdbcTemplate.update(GetQueryAPI.getQuery(RMS41,temp.get(4),temp.get(5),"5",temp.get(0)));
            }
            status = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return status;
	}
	
}
