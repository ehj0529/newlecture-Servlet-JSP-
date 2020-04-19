package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc3")
public class Calc3 extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		Cookie[] cookies = request.getCookies();

		String value = request.getParameter("value");
		String operator = request.getParameter("button");
		String dot = request.getParameter("dot");
		
		String exp ="";
		
		if(cookies != null)
			for(Cookie c: cookies) 
				//Cookie c = cookies[0];
				if(c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}

		if(operator != null && operator.equals("=")) {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			try {
				exp = String.valueOf( engine.eval(exp) );
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(operator != null && operator.equals("C")) {
			exp = "";
		}
		else {			
			exp += (value == null)?"":value;
			exp += (operator == null)?"":operator;
			exp += (dot == null)?"":dot;
		}
		
		Cookie expCookie = new Cookie("exp",exp);
		
		if(operator != null && operator.equals("C"))
			expCookie.setMaxAge(0);
		
		response.addCookie(expCookie);
		response.sendRedirect("calcpage");  //화면을 재동장 하는 거

/**************************		
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String v_ = request.getParameter("v");
		String btn = request.getParameter("button");
		
		int v =0; 
		if(!v_.equals("")) v = Integer.parseInt(v_);


		if(btn.equals("=")) {
			
			//int x = (int) application.getAttribute("value");
			//int x = (int) session.getAttribute("value");
			int x = 0;
			
			for(Cookie c: cookies) 
				//Cookie c = cookies[0];
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
					break;
				}
		
			int y = v;
			//String op = (String) application.getAttribute("btn");
			//String op = (String) session.getAttribute("btn");
			
			String op ="";
			for(Cookie c: cookies) 
				//Cookie c = cookies[0];
				if(c.getName().equals("btn")) {
					op = c.getValue();
					break;
				}
	
			int iSum = 0;
			if(op.equals("+"))
				iSum = x + y ;
			else
				iSum = x - y ;
			
			out.printf("결과 :: %d\n",iSum  );
			
		}
		else { //값을 저장하는 부분
			
//			application.setAttribute("value", v);
//			application.setAttribute("btn", btn);
			
//			session.setAttribute("value", v);
//			session.setAttribute("btn", btn);
			
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie btnCookie = new Cookie("btn", btn);
			valueCookie.setPath("/calc2"); //cookie 저장위치
			valueCookie.setMaxAge(24*60*60);//cookie 유효기간 설정 하루
			
			btnCookie.setPath("/Calc2");
			
			response.addCookie(valueCookie);
			response.addCookie(btnCookie);
			
			response.sendRedirect("calc2.html");  //화면을 재동장 하는 거
		}
**************************/
		
	}

}
