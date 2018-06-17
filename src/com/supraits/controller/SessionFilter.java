package com.supraits.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

public class SessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException,
	ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String url = request.getRequestURI();

		response.setHeader("pragma", "no-cache");              
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
		response.setHeader("Expires", "0"); 
		response.setHeader("Access-Control-Allow-Origin", "*");
		HttpSession session = request.getSession(false);

		if(session==null && !url.contains("login") 
				&& !url.contains("v1") && !url.contains("resources")) {
			response.sendRedirect("redirect:/login");
			response.setHeader("message", "Session Timeout.");
			return;
		}
		else if(!url.contains("v1")){
			chain.doFilter(req, res);
		}


		/*if(!url.contains("v1/login") && url.contains("v1")) {
			//if url is not containing rest login
			String accessToken = request.getHeader("token");
			if(StringUtils.isNotBlank(accessToken)) {
				//Token Validity Test
//				if(loginDelegate.checkToken(accessToken)){
					chain.doFilter(req, res);
				}
				else {
					response.setHeader("message", "Unauthorized. Provide a valid access token.");
				}
			}
			else {
				//token is not coming
				response.setHeader("message", "Unauthorized. Provide a valid access token.");
				return;
			}
		}
		else {
			chain.doFilter(request, response);
		}
*/


		/*boolean loggedIn = session != null && session.getAttribute("loggedInUser") != null;
	            if(loggedIn){
	            	response.sendRedirect("redirect:/login");
	            	return;
	            }*/


		/*String loginURI = request.getContextPath() + "/login";

	            boolean loggedIn = session != null && session.getAttribute("loggedInUser") != null;
	            boolean loginRequest = request.getRequestURI().equals(loginURI);

	            if (loggedIn || loginRequest) {
	                chain.doFilter(request, response);
	            } else {
	                response.sendRedirect(loginURI);
	            }*/


	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
