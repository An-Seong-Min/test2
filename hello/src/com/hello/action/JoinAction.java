package com.hello.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JoinAction implements Action {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//회원가입 을 위해 헤드창에있는 a링크 클릭시 get
		//가입하기로 누르면 post
		// request.getMethod() 라는걸 사용하면 get인지 post인지 확인 가능
		
		
			return "/join.jsp";
	
		
	}
}
