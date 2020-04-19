<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- ------------------------------------------------- -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
pageContext.setAttribute("result", "page 객체에 추가 가능함.");
%>
<body>

	<%=request.getAttribute("result") %> 입니다.<br ><br > 
	${requestScope.result}<br >
	${name[0]}<br >
	${name[1]}<br >
	${notice.id}<br >
	${notice.title}<br >
	<br >${result}<br >
	${param.n ge 3}<br >
	${param.n/2}<br >
	${header.accept}<br >
	${empty param.n?'값이비어있습니다.':param.n }<br >
	
	
	
</body>
</html>