package com.supraits.delegate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.supra.imanager.bean.LoginInput;
import com.supra.imanager.bean.LoginResult;
import com.supraits.service.UserService;
import com.supraits.viewBean.LoginBean;
import com.supraits.viewBean.RegisterBean;


public class LoginDelegate
{
		private UserService userService;

		public UserService getUserService()
		{
				return this.userService;
		}

		public void setUserService(UserService userService)
		{
				this.userService = userService;
		}
		public LoginBean isValidUser(LoginBean loginBean) throws SQLException
		{
		    return userService.isValidUser(loginBean);
		}
		public boolean registerNewUser(RegisterBean registerBean) {
			
			return userService.registerNewUser(registerBean);
		}

		public List<String> getUserGroupList(String username) {
			return userService.getUserGroupList(username);
		}

		public Map<String, Boolean> getAccessMap(List<String> userGroups) {
			return userService.getAccessMap(userGroups);
		}
		
		
		
		public LoginResult isValidUserRest(LoginInput restLoginBean) throws SQLException {
		    return userService.isValidUserRest(restLoginBean);
		}

		
		public boolean checkToken(String tokenToMatch) {
			// TODO Auto-generated method stub
			return userService.isValidToken(tokenToMatch);
		}
}
