package com.newlecture.web.controller.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;

@WebServlet("/notice/list")
public class ListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		String field ="title";
		if(field_ != null && !field_.equals(""))
			field = field_;
		
		String query ="";
		if(query_ != null && !query_.equals(""))
			query = query_;

		int page = 1;
		if(page_ != null && !page_.equals(""))
			page = Integer.parseInt(page_);
		
		NoticeService service = new NoticeService();
		List<NoticeView> list = service.getNoticeList(field, query, page);
		int count = service.getNoticeCount(field, query);
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);

		
/*		
		List<Notice> list = new ArrayList<>();
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "SELECT * FROM NOTICE";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(url,"NEWLEC","newlec1");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while(rs.next()) {	

				int id 			= rs.getInt("ID");
				String title    = rs.getString("TITLE") ; 
				Date regdate    = rs.getDate("REGDATE") ;	
				String writerId = rs.getString("WRITER_ID") ; 
				int hit         = rs.getInt("HIT")  ;
				String files    = rs.getString("FILES") ; 		
				String content  = rs.getString("CONTENT") ;

				Notice notice = new Notice(
									id,
									title,
									regdate,
									writerId,
									hit,
									files,
									content
									);
				list.add(notice);
			} 
			
			rs.close();
			st.close();
			con.close();   
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/				
	}

}
