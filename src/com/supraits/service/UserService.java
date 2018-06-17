
package com.supraits.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.supra.imanager.bean.LoginInput;
import com.supra.imanager.bean.LoginResult;
import com.supraits.viewBean.LoginBean;
import com.supraits.viewBean.RegisterBean;

/**
 * @author abhinav.gupta
 *
 */
public interface UserService
{
		public LoginBean isValidUser(LoginBean loginBean) throws SQLException;

		public boolean registerNewUser(RegisterBean registerBean);

		public List<String> getUserGroupList(String username);

		public Map<String, Boolean> getAccessMap(List<String> userGroups);
		
		public LoginResult isValidUserRest(LoginInput restLoginBean) throws SQLException;

	    public boolean isValidToken(String tokenToMatch);

		
		
}
