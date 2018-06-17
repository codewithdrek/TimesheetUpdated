package com.supraits.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.supra.imanager.bean.LoginInput;
import com.supra.imanager.bean.LoginResult;
import com.supraits.viewBean.LoginBean;
import com.supraits.viewBean.RegisterBean;

/**
 * @author abhinav.gupta
 * This interface will be used to communicate with the
 * Database
 */
public interface UserDao
{
		public LoginBean isValidUser(LoginBean loginBean) throws SQLException;

		boolean registerNewUser(RegisterBean registerBean);

		public List<String> getUserGroupList(String username);

		public Map<String, Boolean> getAccessMap(List<String> userGroups);
				
		public LoginResult isValidUserRest(LoginInput loginBean) throws SQLException;

		public boolean isValidTokenDb(String tokenToMatch);

}
