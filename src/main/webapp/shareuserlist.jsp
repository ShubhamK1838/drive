<%@page import="dbhelper.SpContext"%>
<%@page import="database.UserDao"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Base64"%>
<%@ page import="entities.User"%>
<%@ page import="jakarta.servlet.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin - Logged In Users</title>
<%@include file="commonheader.jsp"%>
<style>
body {
	font-family: Arial, sans-serif;
}

.users {
	max-width: 800px;
	margin: 0 auto;
	padding: 20px;
}

.user {
	display: flex;
	align-items: center;
	padding: 10px;
	border-bottom: 1px solid #ddd;
}

.user img {
	border-radius: 50%;
	width: 50px;
	height: 50px;
	margin-right: 20px;
}

.user p {
	margin: 0 10px;
	flex: 1;
}

.user button {
	padding: 5px 10px;
	background-color: red;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.user button:hover {
	background-color: darkred;
}
</style>
</head>
<body>

	<%
		 String fileid=request.getParameter("fileid");
		UserDao dao = SpContext.context.getBean("userDao", UserDao.class);
		List<User> list=dao.getUsers();
		
	%>

	<%@ include file="nav.jsp"%>
	<main class="main-content">
		<div class="users">
			<%
				for(User ele: list)
				{
					if(ele.getUserId()==user.getUserId())
						continue; 

			%>
			<div class="user" id="">
				<img
					src="data:image/jpeg;base64,<%=Base64.getEncoder().encodeToString(ele.getImage().getImage())%>"
					alt="User Image" class="user-image">
				<p><%=ele.getName()%></p>
				<p id="email"><%=ele.getEmail()%></p>
				<button onClick="shareFile('<%=ele.getUserId() %>', '<%=fileid%>')" class="btn-2">Share</button>
			</div>
			
			<% } %>

		</div>
		
		
		

	</main>
	<script>
		
		function shareFile(userid, fileid)
		{
			fetch("AddFileUserLIst?userid="+userid+"&&fileid="+fileid)
			.then(function(res){
				
				if(res.ok)
				{
					alert("File Shared..."); 	
				}
			})
			
			
		}
	</script>


</body>
</html>
