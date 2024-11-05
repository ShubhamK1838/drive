<%@page import="entities.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%
		String sessionid=request.getParameter("sid");
		List<HttpSession> loggedUsers = (List<HttpSession>) getServletContext().getAttribute("logged_users");
		HttpSession sn=null; 
		for(HttpSession ele: loggedUsers)
		{
			if(ele.getId().equals(sessionid))
			{
				sn=ele;
				break; 
			}
			
		}
		if(sn!=null)
		{
			session.setAttribute("user",(User)sn.getAttribute("user")); 
			response.sendRedirect("home.jsp");
			
		}else 
		{
			response.sendRedirect("admin.jsp");
		}
		
		
			
	
	
	
	%>
</body>
</html>