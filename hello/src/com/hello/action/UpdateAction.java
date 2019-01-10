package com.hello.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hello.model.DBBean;

public class UpdateAction implements Action {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String idString = request.getParameter("id");
		System.out.println(idString);
		if(idString != null) {
			int id = Integer.parseInt(idString);
			DBBean db = DBBean.getInstance();
			int result = db.update(id, true);
			
			if(result == 1)
				return "{ \"result\":  \"OK\" }";
		}
		
		return "{ \"result\":  \"NG\" }";
	}

}
