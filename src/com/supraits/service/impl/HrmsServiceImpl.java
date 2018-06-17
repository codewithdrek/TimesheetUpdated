package com.supraits.service.impl;

import java.beans.Encoder;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.velocity.app.VelocityEngine;
import org.docx4j.convert.in.xhtml.FormattingOption;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.model.styles.StyleTree;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.NumberingDefinitionsPart;
import org.docx4j.wml.RFonts;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.supraits.dao.impl.GetQueryAPI;
import com.supraits.service.HrmsService;
import com.supraits.service.TimesheetReportImpl;
import com.supraits.viewBean.ExpenseBean;
import com.supraits.viewBean.NotificationDocBean;


@Service
public class HrmsServiceImpl implements HrmsService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private VelocityEngine velocityEngine;
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Value("${OFFER_LETTER}")
	private String OFFER_LETTER;
	@Value("${APPOINTMENT_LETTER}")
	private String APPOINTMENT_LETTER;
	@Value("${RELIEVING_LETTER}")
	private String RELIEVING_LETTER;
	@Value("${SALARY_SLIP}")
	private String SALARY_SLIP;
	
	@Value("${ATN22}")
	private String ATN22;
	@Value("${ATN23}")
	private String ATN23;
	
	@Value("${HRM1}")
	private String HRM1;
	@Value("${HRM2}")
	private String HRM2;
	@Value("${HRM3}")
	private String HRM3;
	@Value("${HRM4}")
	private String HRM4;
	@Value("${HRM5}")
	private String HRM5;
	@Value("${HRM6}")
	private String HRM6;
	@Value("${HRM7}")
	private String HRM7;
	@Value("${HRM8}")
	private String HRM8;
	@Value("${HRM9}")
	private String HRM9;
	@Value("${HRM11}")
	private String HRM11;
	@Value("${HRM12}")
	private String HRM12;
	@Value("${HRM13}")
	private String HRM13;
	@Value("${HRM14}")
	private String HRM14;
	@Value("${HRM15}")
	private String HRM15;
	@Value("${HRM16}")
	private String HRM16;
	@Value("${HRM17}")
	private String HRM17;
	@Value("${HRM18}")
	private String HRM18;
	@Value("${HRM19}")
	private String HRM19;
	@Value("${HRM20}")
	private String HRM20;
	@Value("${HRM21}")
	private String HRM21;
	@Value("${HRM22}")
	private String HRM22;
	@Value("${HRM23}")
	private String HRM23;
	@Value("${HRM24}")
	private String HRM24;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	String currentDate = sdf2.format(new Date());
	
	@Override
	public JSONArray getDocParams(HttpSession session, String docRfNum) {
		JSONArray jsonData = new JSONArray();
		String query = "";
		try{
			query = GetQueryAPI.getQuery(HRM1,docRfNum);
			List<String> docParams = (List<String>) jdbcTemplate.queryForList(query,String.class);
			jsonData.put(docParams);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonData;
		
}

	@Override
	public String createEmployeeDoc(HttpSession session, String dataString,
			String docType) {
		String tempString =  new SimpleDateFormat("yyyyMMdd'.pdf'").format(new Date());
			   tempString = "Confidential" + tempString;
		try {
			String FILE = System.getProperty("user.dir")+ File.separator + tempString;
	        Document document = new Document();
	        //System.out.println("doc"+FILE);
	        PdfWriter.getInstance(document, new FileOutputStream(FILE));
	        document.open();
	        addMetaData(session,document,dataString,docType);
	        addContent(session,document,dataString,docType);
	        document.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return tempString;
	}
	private void addContent(HttpSession session, Document document, String dataString,String docType) throws DocumentException, IOException {
		  try {
			  JSONArray docParam = getDocParams(session, docType);
			  Properties properties = new Properties();
			  Map<String, Object> data = new LinkedHashMap<String,Object>();
			  String templateName = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(HRM2, docType),String.class);
			  properties.load(HrmsServiceImpl.class.getResourceAsStream(templateName));	
				for (String key : properties.stringPropertyNames()) {
				    String value = properties.getProperty(key);
				    data.put(key, String.valueOf(value));
				}  
			  List<String> items = Arrays.asList(dataString.split("\\s*,\\s*"));
			  for(int i=0;i<docParam.length();i++){
				  JSONObject temp = docParam.getJSONObject(i);
				  data.put(temp.getString("label"),items.get(i+1));
			  }
			  if(OFFER_LETTER.equalsIgnoreCase(docType))
				  generateOfferLetter(session,document,data);
			  if(APPOINTMENT_LETTER.equalsIgnoreCase(docType))
				  generateAppointmentLetter(session,document,data);
			  if(RELIEVING_LETTER.equalsIgnoreCase(docType))
				  generateRelievingLetter(session,document,data);
			  if(SALARY_SLIP.equalsIgnoreCase(docType))
				  generateSalarySlip(session,document,data);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		}
	private void generateOfferLetter(HttpSession session, Document document,
			Map<String, Object> data) {
		try{
			  Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
			        Font.BOLD);
			  Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,
			        Font.NORMAL);
			  Font subFontBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
				        Font.BOLD);
			  Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			        Font.BOLD);
			  	Paragraph titleHead = new Paragraph();
			  	titleHead.setAlignment(Element.ALIGN_CENTER);
			  	addEmptyLine(titleHead, 1);
			  	titleHead.add(new Paragraph("Offer Letter", catFont));
			  	document.add(titleHead);
			  	Paragraph title = new Paragraph();
			    addEmptyLine(title, 1);
			    title.add(new Paragraph("Generated Date:" + new Date(),smallBold));
			    addEmptyLine(title, 3);
			    title.add(new Paragraph(String.valueOf(data.get("TAG_EMPLOYEE_NAME")),subFont));
			    title.add(new Paragraph(String.valueOf(data.get("TAG_ADDRESS")),subFont));
			    title.add(new Paragraph(String.valueOf(data.get("TAG_CITY")),subFont));
			    title.add(new Paragraph(String.valueOf(data.get("TAG_PROVINCE")),subFont));
			    document.add(title);
			    Paragraph title1 = new Paragraph("LETTER OF OFFER OF EMPLOYMENT", catFont);
			    title1.setAlignment(Element.ALIGN_CENTER);
			    addEmptyLine(title1, 2);
			    document.add(title1);
			    Paragraph title1Line = new Paragraph();
			    document.add(new Paragraph("Dear "+String.valueOf(data.get("TAG_EMPLOYEE_NAME")),subFontBold));
			    document.add(new Paragraph(String.valueOf(data.get("TAG_PARA_1")),subFont));
			    document.add(new Paragraph(String.valueOf(data.get("TAG_JOB_TITLE")),subFontBold));
			    document.add(new Paragraph(String.valueOf(data.get("TAG_PARA_2")),subFont));
			    addEmptyLine(title1Line, 1);
			    document.add(new Paragraph(String.valueOf(data.get("TAG_PARA_3")),subFont));
			    addEmptyLine(title1Line, 1);
			    document.add(new Paragraph(String.valueOf(data.get("TAG_PARA_4")),subFont));
			    addEmptyLine(title1Line, 1);
			    addEmptyLine(title1Line, 1);
			    document.add(new Paragraph("Title:"+String.valueOf(data.get("TAG_JOB_TITLE")),subFontBold));
			    document.add(new Paragraph("Job Description:"+String.valueOf(data.get("TAG_JOB_DESCRIPTION")),subFontBold));
			    document.add(new Paragraph("Joining Date:"+String.valueOf(data.get("TAG_JOINING_DATE")),subFontBold));
			    document.add(new Paragraph("CTC:"+String.valueOf(data.get("TAG_CTC")),subFontBold));
			    document.add(new Paragraph("Probation."+String.valueOf(data.get("TAG_PROBATION")),subFontBold));
			    addEmptyLine(title1Line, 1);
			    document.add(new Paragraph(String.valueOf(data.get("TAG_PARA_5")),subFont));
			    addEmptyLine(title1Line, 1);
			    document.add(new Paragraph(String.valueOf(data.get("TAG_PARA_6")),subFont));
			    addEmptyLine(title1Line, 1);
			    document.add(new Paragraph("Sincerely:.",subFontBold));
			    document.add(new Paragraph("Name:"+String.valueOf(data.get("TAG_FROM_NAME")),subFontBold));
			    document.add(new Paragraph("Title:"+String.valueOf(data.get("TAG_FROM_TITLE")),subFontBold));
			    document.add(new Paragraph("Company."+String.valueOf(data.get("TAG_ORGANIZATION_NAME")),subFontBold));
			    document.add(new Paragraph("Your Email Signature ",subFont));
			    addEmptyLine(title1Line, 1);
			    addEmptyLine(title1Line, 1);
			    document.add(new Paragraph(String.valueOf(data.get("TAG_PARA_7")),subFont));
			    document.add(new Paragraph(String.valueOf(data.get("TAG_PARA_8")),subFont));
			    document.add(title1Line);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private void generateAppointmentLetter(HttpSession session, Document document,
			Map<String, Object> data) {
		try{
			  Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
			        Font.BOLD);
			  Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,
			        Font.NORMAL);
			  Font subFontBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
				        Font.BOLD);
			  Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			        Font.BOLD);
			  	Paragraph titleHead = new Paragraph();
			  	titleHead.setAlignment(Element.ALIGN_CENTER);
			  	addEmptyLine(titleHead, 1);
			  	titleHead.add(new Paragraph("Appointment Letter Letter", catFont));
			  	document.add(titleHead);
			  	Paragraph title = new Paragraph();
			    addEmptyLine(title, 1);
			    title.add(new Paragraph("Generated Date:" + new Date(),smallBold));
			    addEmptyLine(title, 2);
			    title.add(new Paragraph(String.valueOf(data.get("TAG_PARA_1")),subFont));
			    addEmptyLine(title, 1);
			    title.add(new Paragraph(String.valueOf(data.get("TAG_PARA_2")),subFont));
			    title.add(new Paragraph(String.valueOf("Dear "+data.get("TAG_EMPLOYEE_NAME")),subFontBold));
			    addEmptyLine(title, 1);
			    title.add(new Paragraph(String.valueOf("Welcome to "+data.get("TAG_COMPANY_NAME")),subFont));
			    title.add(new Paragraph(String.valueOf(data.get("TAG_PARA_3"))+ " "+String.valueOf(data.get("TAG_DESIGNATION")),subFont));
			    title.add(new Paragraph(String.valueOf(data.get("TAG_PARA_4"))+ " "+String.valueOf(data.get("TAG_DESIGNATION"))+" will commense on "+String.valueOf(data.get("TAG_DATE")),subFont));
			    title.add(new Paragraph(String.valueOf(data.get("TAG_PARA_5")),subFont));
			    title.add(new Paragraph(String.valueOf(data.get("TAG_PARA_6")),subFont));
			    title.add(new Paragraph(String.valueOf(data.get("TAG_PARA_7")),subFont));
			    addEmptyLine(title, 1);
			    title.add(new Paragraph(String.valueOf(data.get("TAG_PARA_8")),subFont));
			    addEmptyLine(title, 1);
			    addEmptyLine(title, 1);
			    document.add(title);
			    Paragraph title1Line = new Paragraph();
			    addEmptyLine(title1Line, 1);
			    document.add(new Paragraph("Sincerely:.",subFontBold));
			    addEmptyLine(title1Line, 1);
			    document.add(new Paragraph("Name:"+String.valueOf(data.get("TAG_FROM_NAME")),subFontBold));
			    document.add(new Paragraph("Title:"+String.valueOf(data.get("TAG_FROM_DESIGNATION")),subFontBold));
			    document.add(new Paragraph("Company."+String.valueOf(data.get("TAG_ORGANIZATION_NAME")),subFontBold));
			    document.add(new Paragraph("Your Email Signature ",subFont));
			    document.add(title1Line);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private void generateRelievingLetter(HttpSession session, Document document,
			Map<String, Object> data) {
		try{
			  Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
			        Font.BOLD);
			  Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,
			        Font.NORMAL);
			  Font subFontBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
				        Font.BOLD);
			  Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			        Font.BOLD);
			  	Paragraph titleHead = new Paragraph();
			  	titleHead.setAlignment(Element.ALIGN_CENTER);
			  	addEmptyLine(titleHead, 1);
			  	titleHead.add(new Paragraph("Relieving Letter", catFont));
			  	titleHead.add(new Paragraph("To Whom So Ever It May Concern", subFontBold));
			  	document.add(titleHead);
			  	Paragraph title = new Paragraph();
			    addEmptyLine(title, 1);
			    title.add(new Paragraph("Generated Date:" + new Date(),smallBold));
			    addEmptyLine(title, 3);
			    title.add(new Paragraph(String.valueOf(data.get("TAG_EMPLOYEE_NAME"))+" "+String.valueOf(data.get("TAG_PARA_1"))+" "+String.valueOf(data.get("TAG_DESIGNATION"))+" "+String.valueOf(data.get("TAG_PARA_2"))+" "+String.valueOf(data.get("TAG_COMPANY_NAME"))+".",subFont));
			    title.add(new Paragraph(String.valueOf(data.get("TAG_EMPLOYEE_NAME"))+" "+String.valueOf(data.get("TAG_PARA_3")),subFont));
			    title.add(new Paragraph(String.valueOf(data.get("TAG_PARA_4")),subFont));
			    title.add(new Paragraph(String.valueOf(data.get("TAG_COMPANY_NAME"))+" "+String.valueOf(data.get("TAG_PARA_5")),subFont));
			    document.add(title);
			    Paragraph title1 = new Paragraph();
			    addEmptyLine(title1, 2);
			    document.add(new Paragraph("Sincerely:.",subFontBold));
			    document.add(new Paragraph("Name:"+String.valueOf(data.get("TAG_FROM_NAME")),subFontBold));
			    document.add(new Paragraph("Title:"+String.valueOf(data.get("TAG_FROM_DESIGNATION")),subFontBold));
			    document.add(new Paragraph("Company."+String.valueOf(data.get("TAG_FROM_COMAPANY_NAME")),subFontBold));
			    document.add(title1);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private void generateSalarySlip(HttpSession session, Document document,
			Map<String, Object> data) {
		try{
			  Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
			        Font.BOLD);
			  Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,
			        Font.NORMAL);
			  Font subFontBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
				        Font.BOLD);
			  Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			        Font.BOLD);
			  	Paragraph titleHead = new Paragraph();
			  	titleHead.setAlignment(Element.ALIGN_CENTER);
			  	addEmptyLine(titleHead, 1);
			  	titleHead.add(new Paragraph("Payslip:"+String.valueOf(data.get("TAG_COMPANY_NAME")), catFont));
			  	titleHead.add(new Paragraph(String.valueOf(data.get("TAG_COMPANY_ADDRESS_1")),subFont));
			    titleHead.add(new Paragraph(String.valueOf(data.get("TAG_COMPANY_ADDRESS_2")),subFont));
			    titleHead.add(new Paragraph(String.valueOf(data.get("TAG_MONTH"))+","+String.valueOf(data.get("TAG_YEAR")),subFont));
			    addEmptyLine(titleHead, 2);
			  	document.add(titleHead);
			  	
			  	PdfPTable empDetailTable = new PdfPTable(4);
			  	empDetailTable.addCell("Employee Name:");
		        empDetailTable.addCell(String.valueOf(data.get("TAG_EMPLOYEE_NAME")));
		        empDetailTable.addCell("Employee Code:");
		        empDetailTable.addCell(String.valueOf(data.get("TAG_EMPLOYEE_CODE")));
		        
		        empDetailTable.addCell("Employee PAN:");
		        empDetailTable.addCell(String.valueOf(data.get("TAG_EMPLOYEE_PAN")));
		        empDetailTable.addCell("Employee UAN:");
		        empDetailTable.addCell(String.valueOf(data.get("TAG_EMPLOYEE_UAN")));
		        
		        empDetailTable.addCell("Department:");
		        empDetailTable.addCell(String.valueOf(data.get("TAG_EMPLOYEE_DEPT")));
		        empDetailTable.addCell("Policy Group:");
		        empDetailTable.addCell(String.valueOf(data.get("TAG_EMPLOYEE_POLICY_GROUP")));
		        
		        empDetailTable.addCell("No of days paid for:");
		        empDetailTable.addCell(String.valueOf(data.get("TAG_EMPLOYEE_NO_OF_DAYS")));
		        empDetailTable.addCell("Payment Mode:");
		        empDetailTable.addCell(String.valueOf(data.get("TAG_EMPLOYEE_PAY_MODE")));
			  	
		        document.add(empDetailTable);
		        
		        Paragraph titleHead2 = new Paragraph();
			  	titleHead2.setAlignment(Element.ALIGN_CENTER);
			  	addEmptyLine(titleHead2, 1);
			  	titleHead.add(new Paragraph("Salary Breakdown", catFont));
			  	document.add(titleHead2);
			  	
			  	PdfPTable salaryTable = new PdfPTable(2);
			  	salaryTable.addCell("Basic:");
			  	salaryTable.addCell(String.valueOf(data.get("TAG_EMPLOYEE_BASIC")));
			  	salaryTable.addCell("HRA:");
			  	salaryTable.addCell(String.valueOf(data.get("TAG_EMPLOYEE_HRA")));
			  	salaryTable.addCell("LTA:");
			  	salaryTable.addCell(String.valueOf(data.get("TAG_EMPLOYEE_LTA")));
			  	salaryTable.addCell("Medical Reimbursement:");
			  	salaryTable.addCell(String.valueOf(data.get("TAG_EMPLOYEE_MR")));
			  	salaryTable.addCell("Variable Pay:");
			  	salaryTable.addCell(String.valueOf(data.get("TAG_EMPLOYEE_VP")));
			  	salaryTable.addCell("Special Allowance:");
			  	salaryTable.addCell(String.valueOf(data.get("TAG_EMPLOYEE_SA")));
			  	document.add(salaryTable);
			  	
			  	Paragraph titleHead3 = new Paragraph();
			  	titleHead3.setAlignment(Element.ALIGN_CENTER);
			  	addEmptyLine(titleHead3, 1);
			  	titleHead.add(new Paragraph("Salary Deduction", catFont));
			  	document.add(titleHead3);
			  	
			  	PdfPTable deductTable = new PdfPTable(2);
			  	deductTable.addCell("PF:");
			  	deductTable.addCell(String.valueOf(data.get("TAG_EMPLOYEE_PF")));
			  	deductTable.addCell("TAX:");
			  	deductTable.addCell(String.valueOf(data.get("TAG_EMPLOYEE_TAX")));
			  	deductTable.addCell("GF:");
			  	deductTable.addCell(String.valueOf(data.get("TAG_EMPLOYEE_GF")));
			  	deductTable.addCell("Other Deduction:");
			  	deductTable.addCell(String.valueOf(data.get("TAG_EMPLOYEE_OD")));
			  	document.add(deductTable);
			  	
			  	PdfPTable finalTable = new PdfPTable(2);
			  	finalTable.addCell("Net Pay in Rs:");
			  	finalTable.addCell(String.valueOf(data.get("TAG_NET_PAY")));
			  	document.add(finalTable);
		        
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void addMetaData(HttpSession session, Document document,String dataString,String docType) {
		// TODO Auto-generated method stub
	}
	private void addEmptyLine(Paragraph paragraph, int number) {
	    for (int i = 0; i < number; i++) {
	        paragraph.add(new Paragraph(" "));
	    }
	}
	@Override
	public String punchedTimeInDBByAPI(HttpSession session,String countryCode,String city,String region,String loc,String clientIp){
		try {
					int existEntryCount = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(HRM5, session.getAttribute("loggedInUser").toString()),Integer.class);
					if(existEntryCount == 0)
						jdbcTemplate.update(GetQueryAPI.getQuery(HRM3,session.getAttribute("loggedInUser").toString(),countryCode,countryCode,city == null?"unknown":city ,region,"-","-",loc.substring(0,loc.indexOf(",")),loc.substring(loc.indexOf(",") + 1),session.getAttribute("loggedInUser").toString(),clientIp));
					else
						jdbcTemplate.update(GetQueryAPI.getQuery(HRM4,session.getAttribute("loggedInUser").toString(),countryCode,countryCode,city == null?"unknown":city,region,"-","-",loc.substring(0,loc.indexOf(",")),loc.substring(loc.indexOf(",") + 1),session.getAttribute("loggedInUser").toString(),clientIp));
					return "Thank you! punch time/location has been captured.";
			 
		   } catch (Exception e) {
			  System.err.println(e.getMessage());
			  return "Please Try later";
		  }
	}
	@Override
	public String punchedTimeInDB(HttpSession session, String clientIp) throws URISyntaxException {
		try {
			  URL url = HrmsServiceImpl.class.getResource("GeoLiteCity.dat");
			  //String rootPath = System.getProperty("user.dir");
			  File geoData = new File(url.toURI());
			  //if (url == null){
			  if (geoData == null || geoData.getAbsolutePath().length() == 0){ 
				System.err.println("location database is not found - ");
				return "Please Try later";
			  }else {
					//LookupService lookup = new LookupService(rootPath+"/GeoLiteCity.dat",LookupService.GEOIP_MEMORY_CACHE);
				    LookupService lookup = new LookupService(geoData,LookupService.GEOIP_MEMORY_CACHE);
					Location locationServices = lookup.getLocation(clientIp);
					System.out.println(locationServices.countryCode);
					System.out.println(locationServices.countryName);
					System.out.println(locationServices.city);
					System.out.println(locationServices.region);
					System.out.println(locationServices.postalCode);
					System.out.println(locationServices.area_code);
					System.out.println(locationServices.latitude);
					System.out.println(locationServices.longitude);
					int existEntryCount = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(HRM5, session.getAttribute("loggedInUser").toString()),Integer.class);
					if(existEntryCount == 0)
						jdbcTemplate.update(GetQueryAPI.getQuery(HRM3,session.getAttribute("loggedInUser").toString(), locationServices.countryCode,locationServices.countryName,locationServices.city == null?"NA":locationServices.city ,locationServices.region,locationServices.postalCode == null?"NA":locationServices.postalCode,String.valueOf(locationServices.area_code),String.valueOf(locationServices.latitude),String.valueOf(locationServices.longitude),session.getAttribute("loggedInUser").toString(),clientIp));
					else
						jdbcTemplate.update(GetQueryAPI.getQuery(HRM4,session.getAttribute("loggedInUser").toString(), locationServices.countryCode,locationServices.countryName,locationServices.city == null?"NA":locationServices.city,locationServices.region,locationServices.postalCode == null?"NA":locationServices.postalCode,String.valueOf(locationServices.area_code),String.valueOf(locationServices.latitude),String.valueOf(locationServices.longitude),session.getAttribute("loggedInUser").toString(),clientIp));
					return "Thank you! punch time/location has been captured.";
			 }
		   } catch (IOException e) {
			  System.err.println(e.getMessage());
			  return "Please Try later";
		  }
	}
	@Override
	public JSONArray checkUserExistingPunchData(HttpSession session) {
		JSONArray userPunchedData = new JSONArray();
		try {
			int count  = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(HRM5, session.getAttribute("loggedInUser").toString()),Integer.class);
			JSONObject temp = new JSONObject();
			if(count == 2){
				temp.put("signin","true");
				temp.put("signout","true");
			}else if(count == 1){
				temp.put("signin","true");
				temp.put("signout","false");
			}else{
				temp.put("signin","false");
				temp.put("signout","false");
			}
			userPunchedData.put(temp);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return userPunchedData;
	}

	@Override
	public boolean insertNewNotificationToTable(HttpSession session,String notificationrfnum,
			String type, String description, String policyGroup, String title,
			String attachment) {
		boolean status = false;
		String query = "";
		try{
			if(notificationrfnum != null && !(notificationrfnum.equalsIgnoreCase("")))
				query = GetQueryAPI.getQuery(HRM13,title,description,session.getAttribute("loggedInUser").toString(),notificationrfnum);
			else
				query = GetQueryAPI.getQuery(HRM6,type,title,description,session.getAttribute("loggedInUser").toString(),policyGroup);
			jdbcTemplate.update(query);
			if(attachment.length() > 0 && notificationrfnum.equalsIgnoreCase("")){
				int id = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(HRM7),Integer.class);
				updateNotificationDoc(session,id,attachment);
			}else if(!notificationrfnum.equalsIgnoreCase("")){
				updateNotificationDoc(session,Integer.parseInt(notificationrfnum),attachment);
			}
			status = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}

	private void updateNotificationDoc(HttpSession session, int id,
			String attachment) {
		try {
			String rootPath = System.getProperty("user.dir");
			File serverFile = new File((rootPath + File.separator +attachment));
			byte[] bytes = Files.readAllBytes(new File(rootPath + File.separator +attachment).toPath());
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("notificationrfnum", id);
			parameters.addValue("attachmentname", attachment);
			parameters.addValue("docextension", attachment.substring(attachment.indexOf(".") + 1));
			parameters.addValue("attachment", new SqlLobValue(new ByteArrayInputStream(bytes), bytes.length, new DefaultLobHandler()),Types.BLOB);
			namedParameterJdbcTemplate.update("update supra_emp_company_notification set attachment=:attachment,attachmentname=:attachmentname,docextension=:docextension where notificationrfnum=:notificationrfnum", parameters);
			stream.close();
			serverFile = null;
			stream = null;
		}catch(Exception e){
			System.out.println("Error in upload notofication doc");
		}
	}

	@Override
	public JSONArray getAllCompNotification(HttpSession session) {
		JSONArray userGroupList = new JSONArray();
		try {
			userGroupList = jdbcTemplate.query(GetQueryAPI.getQuery(HRM8,session.getAttribute("loggedInUserPolicyGroup").toString()), new ResultSetExtractor<JSONArray>(){
			@Override
			public JSONArray extractData(ResultSet rsUserSet)
					throws SQLException, DataAccessException {
				JSONArray temp = new JSONArray();
				while(rsUserSet.next()){
					JSONObject tempObj = new JSONObject();
					try {
						tempObj.put("notificationrfnum", rsUserSet.getString(1));
						tempObj.put("type", rsUserSet.getString(2));
						tempObj.put("title", rsUserSet.getString(3));
						tempObj.put("isActive", rsUserSet.getString(4));
						tempObj.put("descrition", rsUserSet.getString(5));
						tempObj.put("lastmodifiedby", rsUserSet.getString(6));
						tempObj.put("policyGroup", rsUserSet.getString(7));
						tempObj.put("lastmodifiedon", rsUserSet.getString(8));
						tempObj.put("attachmentname", rsUserSet.getString(9));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					temp.put(tempObj);
				}
				return temp;
			}
		});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return userGroupList;
	}

	@Override
	public NotificationDocBean getAttachmentDetails(HttpSession session,
			String notificationid) {
		String query = "";
		NotificationDocBean documentBean = new NotificationDocBean();
		try{
			query = GetQueryAPI.getQuery(HRM9,notificationid);
			documentBean = jdbcTemplate.query(query, new ResultSetExtractor<NotificationDocBean>(){
			    @Override
				public NotificationDocBean extractData(java.sql.ResultSet rsEffort)
						throws SQLException, DataAccessException {
			    	NotificationDocBean temp= new NotificationDocBean();
						while(rsEffort.next()){
							temp.setDocName(rsEffort.getString(1));
							temp.setDocId(rsEffort.getString(2));
							temp.setDocName(rsEffort.getString(3));
							temp.setUploadedby(rsEffort.getString(4));
							temp.setUploadedon(rsEffort.getString(5));
							temp.setDocFlag(rsEffort.getString(6));
							temp.setDoctype( rsEffort.getString(7));
							temp.setData( rsEffort.getBytes(8));
						}
						return temp;
						}
			});
		}catch(Exception e){
			e.printStackTrace();
		}	
		return documentBean;
	}

	@Override
	public JSONArray fetchUserPunchedData(HttpSession session,
			String firstDay, String lastDay, String username) {
		firstDay = firstDay + " 00:00:00";
		lastDay = lastDay + " 23:59:59";
		JSONArray jsonData = new JSONArray();
		try{
			List<Date> allDaysBetween = getDaysBetweenDates(sdf2.parse(firstDay),sdf2.parse(lastDay));
			System.out.println(allDaysBetween);
			Map<String,JSONObject> blnkMap = new LinkedHashMap<String,JSONObject>();
			Map<String,String> holidayMap = getHolidayMap(firstDay,lastDay);
			Map<String,String> leaveMap = getUserLeaveMap(firstDay,lastDay,username);
			for(Date d:allDaysBetween){
				JSONObject obj = new JSONObject();
				String date = sdf2.format(d);
				obj.put("username", username);
				obj.put("fullname", username);
				obj.put("city", "-");
				obj.put("region", "-");
				obj.put("postalcode", "-");
				obj.put("date", date);
				obj.put("signintime", "-");
				obj.put("signouttime","-");
				String dayName = new SimpleDateFormat("EEEE").format(d);
				if(dayName.equalsIgnoreCase("Sunday") || dayName.equalsIgnoreCase("Saturday"))
					obj.put("status", "Week Off");	
				else if(holidayMap.containsKey(date))
					obj.put("status", "Holiday");
				else if(leaveMap.containsKey(date)){
					if(leaveMap.get(date).equalsIgnoreCase("Approval Pending"))
						obj.put("status", "Leave Applied");
					else
						obj.put("status", "On Leave");
				}else 
					obj.put("status", "Absent");
				blnkMap.put(date, obj);
			}
			Map<String,JSONObject> blnkMapFinal = new LinkedHashMap<String,JSONObject>();
			blnkMapFinal = jdbcTemplate.query(GetQueryAPI.getQuery(HRM11, firstDay,lastDay,username), new ResultSetExtractor<Map<String,JSONObject>>(){
				@Override
				public Map<String,JSONObject> extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					Map<String,JSONObject> temp = new LinkedHashMap<String,JSONObject>();
					while(rs.next()){
						JSONObject obj = new JSONObject();
						try{
							obj.put("username", rs.getString(1));
							obj.put("fullname", rs.getString(2));
							obj.put("city", rs.getString(3));
							obj.put("region", rs.getString(4));
							obj.put("postalcode", rs.getString(5));
							obj.put("date", rs.getString(6));
							obj.put("signintime", rs.getString(7));
							obj.put("signouttime",rs.getString(7).equalsIgnoreCase(rs.getString(8))?"NA":rs.getString(8));
							obj.put("status", "Present");
							temp.put(rs.getString(6), obj);
						}catch(Exception e){
							e.printStackTrace();
						}
					}
					return temp;
				}				
			});
			Iterator<String> itr = blnkMapFinal.keySet().iterator(); 
			while(itr.hasNext()){
				String tempD = itr.next();
				blnkMap.put(tempD,blnkMapFinal.get(tempD));
			}	
			Iterator<String> itr2 = blnkMap.keySet().iterator();
			while(itr2.hasNext())
				jsonData.put(blnkMap.get(itr2.next()));
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonData;
	}
	private Map<String, String> getUserLeaveMap(String startDate,
			String endDate, String username) {
		Map<String,String> leaveMap = new LinkedHashMap<String,String>();
		try{
			leaveMap = jdbcTemplate.query(GetQueryAPI.getQuery(ATN23,startDate,endDate,username),new ResultSetExtractor<Map<String,String>>(){
				@Override
				public Map<String,String> extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					Map<String,String> temp = new LinkedHashMap<String,String>();
					try{
					while(rs.next()){
						List<Date> allDays = getDaysBetweenDates(sdf2.parse(rs.getString(1)), sdf2.parse(rs.getString(2)));
						System.out.println(allDays.toString());
						for(Date d:allDays){
							temp.put(sdf2.format(d),rs.getString(3));
						}
					}
					}catch(Exception e){
						System.out.println("Exception occured while fetching users leave");
					}
					return temp;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}	
	return leaveMap;		
}

	private Map<String, String> getHolidayMap(String startDate, String endDate) {
		Map<String,String> holiMap = new LinkedHashMap<String,String>();
		try{
			holiMap = jdbcTemplate.query(GetQueryAPI.getQuery(ATN22,startDate,endDate),new ResultSetExtractor<Map<String,String>>(){

				@Override
				public Map<String,String> extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					Map<String,String> temp = new LinkedHashMap<String,String>();
					while(rs.next())
						temp.put(rs.getString(1), rs.getString(2));
					return temp;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}	
	return holiMap;		
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
	    Calendar callast = new GregorianCalendar();
	    callast.setTime(enddate);
	    dates.add(callast.getTime());
	    return dates;
	}

	@Override
	public boolean removeCompanyNotificationLeave(HttpSession session,
			String notificationid) {
			 return jdbcTemplate.update(GetQueryAPI.getQuery(HRM12, notificationid)) == 0?false:true;
		}

	@Override
	public boolean updateNotification(HttpSession session,
			String notificationid, boolean status) {
		return jdbcTemplate.update(GetQueryAPI.getQuery(HRM14,status?"Y":"N", notificationid)) > 0?true:false;
	}

	@Override
	public JSONArray getRTEDocTemplate(HttpSession session, String templaterfnum) {

		JSONArray jsonData = new JSONArray();
		String query = "";
		try{
			query = GetQueryAPI.getQuery(HRM15,templaterfnum);
			String templateData = jdbcTemplate.queryForObject(query,String.class);
			JSONObject temp = new JSONObject();
			temp.put("templateData", templateData);
			jsonData.put(temp);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonData;
	}

	@Override
	public String createNewTemplate(HttpSession session,final String policyGroup,
			final String userGroup,final String docType,final String docDesc,
			final String templateName, final String templateData) {
		String statusString = "Please contact administrator.";
		try{
			final String logInUser = session.getAttribute("loggedInUser").toString();
			jdbcTemplate.update(GetQueryAPI.getQuery(HRM16,templateName,templateData,logInUser,docType,docDesc,userGroup,policyGroup));
			//jdbcTemplate.update("call SUPRA_HRMS_PROC_INSERT_DOC_TEMPLATE ?,?,?,?,?,?,?",templateName,"dd",logInUser,docType,docDesc,userGroup,policyGroup);
			//Object search[]={templateName,templateData,logInUser,docType,docDesc,userGroup,policyGroup};
			/*KeyHolder keyHolder = new GeneratedKeyHolder();
			PreparedStatementCreator psc = new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					return con.prepareStatement(GetQueryAPI.getQuery(HRM16,templateName,templateData,logInUser));
				}
			};
			jdbcTemplate.update(psc,keyHolder);
			final long templateRfNum = keyHolder.getKey().intValue();
			if(templateRfNum != 0){
				PreparedStatementCreator psc2 = new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						return con.prepareStatement(GetQueryAPI.getQuery(HRM17,docType,docDesc,logInUser));
					}
				};
				jdbcTemplate.update(psc2,keyHolder);
				final long docRfNum = keyHolder.getKey().intValue();
				if(docRfNum != 0){
					PreparedStatementCreator psc3 = new PreparedStatementCreator() {
						@Override
						public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
							return con.prepareStatement(GetQueryAPI.getQuery(HRM18,String.valueOf(docRfNum),String.valueOf(templateRfNum)));
						}
					};
					jdbcTemplate.update(psc3,keyHolder);
					long pdtrfRfNum = keyHolder.getKey().intValue();
					if(pdtrfRfNum != 0){
						int updateCount = jdbcTemplate.update(GetQueryAPI.getQuery(HRM19,userGroup, String.valueOf(pdtrfRfNum), policyGroup));
						if(updateCount != 0)
							statusString = "Template created successfully.";
						else{
							jdbcTemplate.update(GetQueryAPI.getQuery(HRM22,String.valueOf(pdtrfRfNum)));
							jdbcTemplate.update(GetQueryAPI.getQuery(HRM20,String.valueOf(docRfNum)));
							jdbcTemplate.update(GetQueryAPI.getQuery(HRM21,String.valueOf(templateRfNum)));
						}
					}else{
						jdbcTemplate.update(GetQueryAPI.getQuery(HRM22,String.valueOf(pdtrfRfNum)));
						jdbcTemplate.update(GetQueryAPI.getQuery(HRM20,String.valueOf(docRfNum)));
						jdbcTemplate.update(GetQueryAPI.getQuery(HRM21,String.valueOf(templateRfNum)));
					}
				}else{
					jdbcTemplate.update(GetQueryAPI.getQuery(HRM21,String.valueOf(templateRfNum)));
				}
			}*/
		}catch(Exception e){
			e.printStackTrace();
			statusString = "Invalid template data.";
		}
		return statusString;
	}

	@Override
	public String fetchGroupListBasedOnPolicy(HttpSession session, String policyGroup) {
		JSONArray jsonarr = new JSONArray();
		try{
			List<Map<String, Object>> results = jdbcTemplate.queryForList(GetQueryAPI.getQuery(HRM17, policyGroup));
			System.out.println(results.toString());
			for (Map m : results){
				   JSONObject obj = new JSONObject();
				   obj.put("key", m.get("groupname"));
				   obj.put("value", m.get("groupname"));
				   jsonarr.put(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}

	@Override
	public String fetchDocListBasedOnUserGroup(HttpSession session, String isActive) {
		JSONArray jsonarr = new JSONArray();
		try{
			List<Map<String, Object>> results = jdbcTemplate.queryForList(GetQueryAPI.getQuery(HRM18,session.getAttribute("loggedInUserPolicyGroup").toString(),isActive));
			System.out.println(results.toString());
			for (Map m : results){
				   JSONObject obj = new JSONObject();
				   obj.put("key", m.get("docrfnum"));
				   obj.put("value", m.get("docname"));
				   jsonarr.put(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}

	@Override
	public String fetchTemplateListBasedOnDocName(HttpSession session,
			String docType) {
		JSONArray jsonarr = new JSONArray();
		try{
			System.out.println(GetQueryAPI.getQuery(HRM19, docType,session.getAttribute("loggedInUserPolicyGroup").toString()));
			List<Map<String, Object>> results = jdbcTemplate.queryForList(GetQueryAPI.getQuery(HRM19, docType,session.getAttribute("loggedInUserPolicyGroup").toString()));
			System.out.println(results.toString());
			for (Map m : results){
				   JSONObject obj = new JSONObject();
				   obj.put("key", m.get("templaterfnum"));
				   obj.put("value", m.get("templatename"));
				   jsonarr.put(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}

	@Override
	public String generateNewDocument(HttpSession session, String username,
			String fullname,String docName,String email, String templateData) {
		String statusString = "Please contact administrator.";
		try{
			final String logInUser = session.getAttribute("loggedInUser").toString();
			jdbcTemplate.update(GetQueryAPI.getQuery(HRM20,username,fullname,logInUser,docName,templateData,email));
		}catch(Exception e){
			e.printStackTrace();
			statusString = "Invalid template data.";
		}
		return statusString;
	}

	@Override
	public String fetchAllGeneretdDocList(HttpSession session, String activeFlag) {
		JSONArray jsonArray = new JSONArray();
		try{
			jsonArray = jdbcTemplate.query(GetQueryAPI.getQuery(HRM21, activeFlag), new ResultSetExtractor<JSONArray>(){
				@Override
				public JSONArray extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					JSONArray tempArray = new JSONArray();
					while(rs.next()){
						JSONObject temp = new JSONObject();
						try {
							temp.put("rfnum", rs.getString(1));
							temp.put("username", rs.getString(2));
							temp.put("fullname", rs.getString(3));
							temp.put("email", rs.getString(4));
							temp.put("docname", rs.getString(5));
							temp.put("createdon", rs.getString(6));
							temp.put("createdby", rs.getString(7));
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						tempArray.put(temp);
					}
					return tempArray;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonArray.toString();
	}

	@Override
	public boolean generatePDFFile(HttpSession session, String rfnum,
			String tempString) {
		boolean status = false;
		try {
			String docData = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(HRM22, rfnum),String.class);
			String FILE = System.getProperty("user.dir")+ File.separator + tempString;
	        Document document = new Document();
	        PdfWriter.getInstance(document, new FileOutputStream(FILE));
	        document.open();
	        HTMLWorker htmlWorker = new HTMLWorker(document);
	        htmlWorker.parse(new StringReader("<html><body>"+docData+"</body></html>"));
	        document.close();
	        status = true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return status;
}
	@Override
	public boolean generateDOCFile(HttpSession session, String rfnum,
			String tempString) {
		boolean status = false;
		try {
			String docData = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(HRM22, rfnum),String.class);
			String FILE = System.getProperty("user.dir")+ File.separator + tempString;
	        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
			wordMLPackage = WordprocessingMLPackage.load(new File(System.getProperty("user.dir") + "/styled.docx"));
			NumberingDefinitionsPart ndp = new NumberingDefinitionsPart();
			wordMLPackage.getMainDocumentPart().addTargetPart(ndp);
			ndp.unmarshalDefaultNumbering();
			XHTMLImporterImpl XHTMLImporter = new XHTMLImporterImpl(wordMLPackage);
			XHTMLImporter.setHyperlinkStyle("Hyperlink");
			wordMLPackage.getMainDocumentPart().getContent()
			.addAll(XHTMLImporter.convert("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><body>"+docData+"</body></html>",FILE));
			wordMLPackage.save(new java.io.File(System.getProperty("user.dir") + "/"+tempString) );
	        status = true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return status;
}
	@Override
	public boolean removeGeneratedDoc(HttpSession session, String docrfnum) {
		try{
			return jdbcTemplate.update(GetQueryAPI.getQuery(HRM23, docrfnum)) > 0?true:false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Object getEmpDocumentData(HttpSession session, String docrfnum) {
		try {
			String docData = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(HRM22, docrfnum),String.class);
			return docData;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}	
	}

	@Override
	public boolean updateGeneratedTemplateDoc(HttpSession session,
			String docrfnum, String templateData) {
		try{
			return jdbcTemplate.update(GetQueryAPI.getQuery(HRM24,templateData,docrfnum)) > 0?true:false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}






