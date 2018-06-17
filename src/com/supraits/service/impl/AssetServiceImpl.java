package com.supraits.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

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
import com.supraits.service.AssetService;

@Service
public class AssetServiceImpl implements AssetService {
	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	String currentDate = sdf.format(date);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Value("${AST1}")
	private String AST1;
	@Value("${AST2}")
	private String AST2;
	@Value("${AST3}")
	private String AST3;
	@Value("${AST4}")
	private String AST4;
	@Value("${AST5}")
	private String AST5;
	@Value("${AST6}")
	private String AST6;
	@Value("${AST7}")
	private String AST7;
	@Value("${AST8}")
	private String AST8;
	@Value("${AST9}")
	private String AST9;
	@Value("${AST10}")
	private String AST10;
	@Value("${AST11}")
	private String AST11;
	@Value("${AST12}")
	private String AST12;
	@Value("${AST13}")
	private String AST13;
	@Value("${AST14}")
	private String AST14;
	@Value("${AST15}")
	private String AST15;
	
	@Override
	public JSONArray getEmployeeListForAssetAllocation(HttpSession session,
			String policyGroup) {
		JSONArray userList = new JSONArray();
		try{
			userList = jdbcTemplate.query(GetQueryAPI.getQuery(AST1, policyGroup), new ResultSetExtractor<JSONArray>(){
				@Override
				public JSONArray extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					JSONArray userListTemp = new JSONArray();
					while(rs.next()){
						JSONObject user = new JSONObject();
						try {
							user.put("username", rs.getString(1));
							user.put("fullname", rs.getString(2));
						} catch (JSONException e) {
							System.out.println("JSON Exception occured");
						}
						userListTemp.put(user);
					}
					return userListTemp;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public JSONArray getAssetCategoryList(HttpSession session) {
		JSONArray categoryList = new JSONArray();
		try{
			List<String> categoryListForAsset = (List<String>) jdbcTemplate.queryForList(GetQueryAPI.getQuery(AST2), String.class);
			for(String temp : categoryListForAsset)
				categoryList.put(temp);
		}catch(Exception e){
			e.printStackTrace();
		}
		return categoryList;
	}

	@Override
	public JSONArray getAssetStatusList(HttpSession session) {
		JSONArray statusList = new JSONArray();
		try{
			List<String> statusListForAsset = (List<String>) jdbcTemplate.queryForList(GetQueryAPI.getQuery(AST3), String.class);
			for(String temp : statusListForAsset)
				statusList.put(temp);
		}catch(Exception e){
			e.printStackTrace();
		}
		return statusList;
	}

	@Override
	public String addNewAsset(HttpSession session, String category, String tag,
			String id, String name, String srNumber, String status) {
		String addStatus = "";
		try{
			if(category == null && tag == null && id == null && name==null && srNumber == null && status == null &&
					category.equals("") && tag.equals("") && id.equals("") && name.equals("") && srNumber.equals("") && status.equals("")){
				return "One or parameter is null or invalid.";
			}else{
				int count = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(AST15, id), Integer.class);
				if(count != 0)
					return "assetIdExist";
			}
			int updatecount = 0;
			do{
				Random rnd = new Random();
				int pKey = 10000000 + rnd.nextInt(90000000);
				String query = GetQueryAPI.getQuery(AST4,String.valueOf(pKey),id,category,tag,name,srNumber,status,session.getAttribute("loggedInUser").toString(),session.getAttribute("loggedInUser").toString(),currentDate);
				updatecount = jdbcTemplate.update(query);
				if(updatecount != 0){
					addStatus = "New asset added successfully";
					break;
				}
			}while(true);
		}catch(Exception e){
			e.printStackTrace();
		}
		return addStatus;
	}

	@Override
	public JSONArray getAssetTagBasedOnCategory(HttpSession session,
			String category) {
		JSONArray statusList = new JSONArray();
		try{
			List<String> statusListForAsset = (List<String>) jdbcTemplate.queryForList(GetQueryAPI.getQuery(AST7,category), String.class);
			for(String temp : statusListForAsset)
				statusList.put(temp);
		}catch(Exception e){
			e.printStackTrace();
		}
		return statusList;
	}

	@Override
	public JSONArray getAssetIdsBasedOnTag(HttpSession session,
			String category, String tag) {
		JSONArray statusList = new JSONArray();
		try{
			List<String> statusListForAsset = (List<String>) jdbcTemplate.queryForList(GetQueryAPI.getQuery(AST8,category,tag), String.class);
			for(String temp : statusListForAsset)
				statusList.put(temp);
		}catch(Exception e){
			e.printStackTrace();
		}
		return statusList;
	}

	@Override
	public JSONArray getAssetDetailsBasedOnAssetId(HttpSession session,
			String assetId) {
		JSONArray assetDetail = new JSONArray();
		try{
			assetDetail = jdbcTemplate.query(GetQueryAPI.getQuery(AST9, assetId), new ResultSetExtractor<JSONArray>(){
				@Override
				public JSONArray extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					JSONArray tempArray = new JSONArray();
					while(rs.next()){
						JSONObject temp = new JSONObject();
							try{
								temp.put("name", rs.getString(1));
								temp.put("srnum", rs.getString(2));
								temp.put("status", rs.getString(3));
							}catch(Exception e){}
						tempArray.put(temp);
					}
					return tempArray;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return assetDetail;
	}

	@Override
	public String allocateAssetToUser(HttpSession session, String id,
			String username, String description, String allocationdate) {

		String allocationStatus = "";
		if(id == null && username == null && description == null && allocationdate==null &&
				id.equals("") && username.equals("") && description.equals("") && allocationdate.equals("")){
			return "One or parameter is null or invalid.";
		}
		try{
			int updatecount = 0;
			do{
				Random rnd = new Random();
				int pKey = 10000000 + rnd.nextInt(90000000);
				String query = GetQueryAPI.getQuery(AST5,String.valueOf(pKey),id,username,allocationdate,"2099-12-12",session.getAttribute("loggedInUser").toString(),currentDate,session.getAttribute("loggedInUser").toString());
				updatecount = jdbcTemplate.update(query);
				if(updatecount != 0){
					break;
				}
				allocationStatus = "Asset has been allocated to "+ username +" successfully";
				insertIntoAllocationHistoryTable(session,id,username,description,allocationdate,"2099-12-12","Allocated");
				jdbcTemplate.update(GetQueryAPI.getQuery(AST11,session.getAttribute("loggedInUser").toString(),id));
			}while(true);
		}catch(Exception e){
			e.printStackTrace();
		}
		return allocationStatus;
	
	}

	private void insertIntoAllocationHistoryTable(HttpSession session,
			String id, String username, String description,
			String allocationstartdate,String allocationenddate,String status) {
		try{
			String query = GetQueryAPI.getQuery(AST6,id,allocationstartdate,allocationenddate,username,session.getAttribute("loggedInUser").toString(),status);
			jdbcTemplate.update(query);
		}catch(Exception e){
			System.out.println("Error in History table insert query.");
		}
	}
	@Override
	public JSONArray getAllAssetList(HttpSession session) {
		JSONArray assetDetail = new JSONArray();
		try{
			assetDetail = jdbcTemplate.query(GetQueryAPI.getQuery(AST10), new ResultSetExtractor<JSONArray>(){
				@Override
				public JSONArray extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					JSONArray tempArray = new JSONArray();
					while(rs.next()){
						JSONObject temp = new JSONObject();
							try{
								temp.put("assetid", rs.getString(1));
								temp.put("assetname", rs.getString(2));
								temp.put("status", rs.getString(3));
								temp.put("startdate", rs.getString(4));
								if(rs.getString(5) == null || rs.getString(5).equals(""))
									temp.put("enddate", "NA");
								else
									temp.put("enddate", rs.getString(5));
								temp.put("allocatedto", rs.getString(6));
								temp.put("allocatedby", rs.getString(7));
								temp.put("allocatedon", rs.getString(8));
								temp.put("assetrfnum", rs.getString(9));
								temp.put("username", rs.getString(10));
							}catch(Exception e){}
						tempArray.put(temp);
					}
					return tempArray;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return assetDetail;
	}

	@Override
	public JSONArray deAllocateAsset(HttpSession session, String assetrfnum,String username,String allocationstartdate) {
		JSONArray deleteStatus = new JSONArray();
		try{
			int deleteCount = jdbcTemplate.update(GetQueryAPI.getQuery(AST12, assetrfnum));
			String assetId = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(AST13, assetrfnum), String.class);
			if(deleteCount != 0){
				insertIntoAllocationHistoryTable(session,assetId,username,"",allocationstartdate,currentDate,"Deallocated");
				jdbcTemplate.update(GetQueryAPI.getQuery(AST14,session.getAttribute("loggedInUser").toString(),assetId));
				deleteStatus.put("Asset has been deallocated successfully");
			}else
				deleteStatus.put("Asset deallocation encountered some problem.");
		}catch(Exception e){
			e.printStackTrace();
		}
		return deleteStatus;
	}
}
