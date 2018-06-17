package com.supraits.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supraits.service.AssetService;
import com.supraits.service.LeaveReportImpl;
import com.supraits.service.LeaveService;
import com.supraits.service.LeaveServiceImpl;
import com.supraits.service.impl.AssetServiceImpl;


@Controller
public class AssetController {
	AssetService guiObj;
	
	@Autowired
	public void setAssetServiceImpl(AssetServiceImpl objAssetServiceGUI) {
		this.guiObj = objAssetServiceGUI;
	}
	
	
	@RequestMapping(value="/getUserListForAssetAllocation", method = RequestMethod.POST)
	public @ResponseBody String getUserListForAssetAllocation(HttpServletRequest request, HttpSession session,@RequestParam(value="policyGroup", required=true)String policyGroup)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getEmployeeListForAssetAllocation(session,policyGroup);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	
	
	@RequestMapping(value="/getAssetCategoryList", method = RequestMethod.POST)
	public @ResponseBody String getAssetCategoryList(HttpServletRequest request, HttpSession session)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getAssetCategoryList(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getAssetTagBasedOnCategory", method = RequestMethod.POST)
	public @ResponseBody String getAssetTagBasedOnCategory(HttpServletRequest request, HttpSession session,@RequestParam(value="category", required=true)String category)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getAssetTagBasedOnCategory(session,category);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getAssetIdsBasedOnTag", method = RequestMethod.POST)
	public @ResponseBody String getAssetIdsBasedOnTag(HttpServletRequest request, HttpSession session,@RequestParam(value="category", required=true)String category,@RequestParam(value="tag", required=true)String tag)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getAssetIdsBasedOnTag(session,category,tag);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getAssetStatusList", method = RequestMethod.POST)
	public @ResponseBody String getAssetStatusList(HttpServletRequest request, HttpSession session)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getAssetStatusList(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/addNewAsset", method = RequestMethod.POST)
	public @ResponseBody String addNewAsset(HttpServletRequest request, HttpSession session,
			@RequestParam(value="category", required=true)String category,
			@RequestParam(value="tag", required=true)String tag,
			@RequestParam(value="id", required=true)String id,
			@RequestParam(value="name", required=true)String name,
			@RequestParam(value="srNumber", required=true)String srNumber,
			@RequestParam(value="status", required=true)String status)
	{
		JSONArray addStatus = new JSONArray();
		try{
		 String	addStatusString = guiObj.addNewAsset(session,category,tag,id,name,srNumber,status);
		 addStatus.put(addStatusString);
		}catch(Exception e){
			e.printStackTrace();
		}
		return addStatus.toString();
	}
	@RequestMapping(value="/getAssetDetailsBasedOnAssetId", method = RequestMethod.POST)
	public @ResponseBody String getAssetDetailsBasedOnAssetId(HttpServletRequest request, HttpSession session,@RequestParam(value="assetId", required=true)String assetId)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getAssetDetailsBasedOnAssetId(session,assetId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/allocateAssetToUser", method = RequestMethod.POST)
	public @ResponseBody String allocateAssetToUser(HttpServletRequest request, HttpSession session,
			@RequestParam(value="id", required=true)String id,
			@RequestParam(value="username", required=true)String username,
			@RequestParam(value="description", required=true)String description,
			@RequestParam(value="allocationdate", required=true)String allocationdate)
	{
		JSONArray addStatus = new JSONArray();
		try{
		 String	addStatusString = guiObj.allocateAssetToUser(session,id,username,description,allocationdate);
		 addStatus.put(addStatusString);
		}catch(Exception e){
			e.printStackTrace();
		}
		return addStatus.toString();
	}
	@RequestMapping(value="/getAssetsList", method = RequestMethod.POST)
	public @ResponseBody String getAssetsList(HttpServletRequest request, HttpSession session)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getAllAssetList(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/deAllocateAsset", method = RequestMethod.POST)
	public @ResponseBody String deAllocateAsset(HttpServletRequest request, HttpSession session,@RequestParam(value="id", required=true)String id
			,@RequestParam(value="username", required=true)String username
			,@RequestParam(value="allocationstartdate", required=true)String allocationstartdate)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.deAllocateAsset(session,id,username,allocationstartdate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
}
