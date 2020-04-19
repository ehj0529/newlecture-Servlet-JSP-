package com.newlecture.web.controller.admin.notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@WebServlet("/admin/board/notice/detail")
public class DetailController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id =  Integer.parseInt( request.getParameter("id") );
		
		NoticeService service = new NoticeService();
		Notice notice = service.getNotice(id);
		
		request.setAttribute("n", notice);
		
		//redirect --서블릿 호출시 다른 페이지 호출 하는 것. 재호출 등.
		//forward  --이어받아서 처리 하는 것 전달 하는 것으로 함.
		request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/detail.jsp").forward(request, response);
		
	}
}
