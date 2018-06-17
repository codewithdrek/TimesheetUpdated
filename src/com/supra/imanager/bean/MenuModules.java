package com.supra.imanager.bean;

import java.util.List;

public class MenuModules {

	int moduleId;
	String moduleName;
	String moduleUrl;
	List<MenuFunctions> functionBeans;	
	
	public String getModuleUrl() {
		return moduleUrl;
	}
	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public List<MenuFunctions> getFunctionBeans() {
		return functionBeans;
	}
	public void setFunctionBeans(List<MenuFunctions> functionBeans) {
		this.functionBeans = functionBeans;
	}
	@Override
	public String toString() {
		return "{ moduleId:" + moduleId + ", moduleName:" + moduleName + ", functionBeans:" + functionBeans
				+ ", moduleUrl=" + moduleUrl + "}";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + moduleId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuModules other = (MenuModules) obj;
		if (moduleId != other.moduleId)
			return false;
		return true;
	}
	
	
	
	
	
	
}
