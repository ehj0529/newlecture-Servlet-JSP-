package com.newlecture.web.controller.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;


@MultipartConfig(
	fileSizeThreshold = 1024*1024,
	maxFileSize = 1024*1024*50,
	maxRequestSize = 1024*1024*50*5
)
@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet {

		
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp").forward(request, response);
	}
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		
		System.out.print("title : ");
		System.out.println(title);
		
		String content = request.getParameter("content");
		String isOpen = request.getParameter("open");
		
		Collection<Part> parts = request.getParts();
		StringBuilder builder = new StringBuilder();
		
		for(Part p: parts) {
			
			if(!p.getName().equals("file")) continue;  // 파츠가 파일이 아니면  skip 아니면 아래로직 수행.
			if(p.getSize()==0) continue;  // 파츠가 파일이 사이즈가 0(자료가 없는 경우)이면 skip
		
			//Part filePart = request.getPart("file");
			Part filePart = p;
			String fileName = filePart.getSubmittedFileName();

			builder.append(fileName) ;
			builder.append(",") ;
			
			InputStream fis = filePart.getInputStream();  // input Stream을 담는 객체
			
			String realPath = request.getServletContext().getRealPath("/upload"); //실제 결로를 알려줌.
			System.out.println("realPath ::"+realPath );
			
			File path = new File(realPath); //폴더 객체를 생성함.
			if(!path.exists()) // 폴더 존재여부 체크.
				path.mkdirs(); // 폴더를 생성 함. 전체 폴더를 생성하는것.
			
			String filePath = realPath +File.separator +fileName;
			FileOutputStream fos = new FileOutputStream(filePath);  //이거는 디스크에 쓰는 거를  담는 객체
			
			int size;
			byte[] buf = new byte[1024];
			while( (size = fis.read(buf)) != -1)
				fos.write(buf,0,size); // 실제 서버에 write하는 기능.  
			
			fos.close();
			fis.close();
		}
		
		builder.delete(builder.length()-1, builder.length()) ;
		
		boolean pub = false;
		if(isOpen != null)
			pub = true;
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setPub(pub);
		notice.setWriterId("newlec");  // TODO 인증 처리자 개발시 적용될 예정 
		notice.setFiles(builder.toString());
		NoticeService service = new NoticeService();
		int result = service.insertNotice(notice);
		
		response.sendRedirect("list");
		
		
	}

}
