package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class Calc extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String xGet_ = request.getParameter("x");
		String yGet_ = request.getParameter("y");
		
		String btn = request.getParameter("button");
		
		//System.out.println("xGet_"+xGet_);
		//System.out.println("yGet_"+yGet_);
		int ix =0; 
		int iy =0;
		int iSum = 0;
		
		if(!xGet_.equals("")) ix = Integer.parseInt(xGet_);
		if(!yGet_.equals("")) iy = Integer.parseInt(yGet_);
		
		if(btn.equals("µ¡¼À"))
			iSum = ix + iy ;
		else
			iSum = ix - iy ;
		
		out.printf("°á°ú :: %d\n",iSum  );
		
	}

}
