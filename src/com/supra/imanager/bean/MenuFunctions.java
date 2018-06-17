package com.supra.imanager.bean;

public class MenuFunctions {
	
	int functionId;
	String functionName;
	String functionUrl;
	
	public int getFunctionId() {
		return functionId;
	}
	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	public String getFunctionUrl() {
		return functionUrl;
	}
	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}
	@Override
	public String toString() {
		return "{ functionId:" + functionId + ", functionName:" + functionName + ", functionUrl:"
				+ functionUrl + "}";
	}
	
	
	
	

}
