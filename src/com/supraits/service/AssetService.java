package com.supraits.service;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;

public interface AssetService {

	JSONArray getEmployeeListForAssetAllocation(HttpSession session,
			String policyGroup);

	JSONArray getAssetCategoryList(HttpSession session);

	JSONArray getAssetStatusList(HttpSession session);

	String addNewAsset(HttpSession session, String category, String tag,
			String id, String name, String srNumber, String status);

	JSONArray getAssetTagBasedOnCategory(HttpSession session, String category);

	JSONArray getAssetIdsBasedOnTag(HttpSession session, String category,
			String tag);

	JSONArray getAssetDetailsBasedOnAssetId(HttpSession session, String assetId);

	String allocateAssetToUser(HttpSession session, String id, String username,
			String description, String allocationdate);

	JSONArray getAllAssetList(HttpSession session);

	JSONArray deAllocateAsset(HttpSession session, String id, String username,
			String allocationstartdate);
	
}
