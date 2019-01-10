package com.hello.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloAction implements Action {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		
		String result = "OK";

		if (id.equals("aaa"))
			result = "No";
			
//		if(id.equals("aaa")) {
//			return "{ \"result\" : \"OK\"}"; 
//		} else {
//			return "{ \"result\" : \"No\"}"; 
//		}
//		
		return "{ \"result\" : \"" + result + "\" , \"message\": \"Hello\" }";
		
	}
}
