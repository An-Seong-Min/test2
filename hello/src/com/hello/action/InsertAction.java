package com.hello.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hello.model.DBBean;

public class InsertAction implements Action {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String job = request.getParameter("job");
		String resultString = "NG";
		String generatedId= "";
		
		if( job != null) {
			DBBean db = DBBean.getInstance();
			int result = db.insert(job);
			//auto_increment 값을 받아오기 때문에 0 이 아닐때가 성공 , 실패 시 0 리턴
			if( result != 0) {
				generatedId = String.valueOf(result);
				resultString = "OK";
			} 
		}
		String json = "{ \"result\": \"";
		json += resultString;
		json += "\", \"id\": \"";
		json += generatedId;
		json += "\" }";
		
		return json;
	}

}
