<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Base64" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="commonheader.jsp" %>	
<style>
   
    .user-profile img {
        cursor: pointer;
    }
    .user-profile input[type="file"] {
        display:none; 
    }
   
</style>
</head>
<body>
<%@ include file="nav.jsp" %>	
<main class="main-content"> 
    <form class="content" method="post" action="UpdateUser" enctype="multipart/form-data">
        <%
            HashMap<String, String> map = new HashMap<>();
            map.put("Name", user.getName()); 
            map.put("Email", user.getEmail()); 
            map.put("Gender", user.getGender()); 
            map.put("Password", user.getPassword()); 
        %>
        <div class="group">

            <div class="user-profile" style=" border-radius:30px; width:80px; height:70px;">
                <input type="file" id="file" name="image">
                <img  style="border:dotted blue 4px;width:80px; height:70px; "
                 id="profileImage" src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(user.getImage().getImage()) %>" alt="User Image" class="user-image">
                 
            </div>
        </div>
        <%
            String[] fields = {"Name", "Email", "Gender", "Password"};
            for(String field : fields) {
        %>
        <div class="group">
            <label><%= field %></label>
            <input name="<%= field %>" required type="<%= "Password".equals(field) ? "password" : "text" %>" class="input" value="<%= map.get(field) %>">
            <span class="highlight"></span>
            <span class="bar"></span>
        </div>
        <%
            }
        %>
        <button class="custom-btn btn-2">S A V E</button>
    </form>
</main>
<script>
    document.getElementById('profileImage').addEventListener('click', function() {
        document.getElementById('file').click();
    });
   
</script>
</body>
</html>


<%-- 
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

	<%@include file="commonheader.jsp" %>	
</head>
<body>

	
	
	<%@include file="nav.jsp" %>	
	<main class="main-content"> 
       <form class="content" method="post" action="UpdateUser">
       	
       	<%
       
       		HashMap<String,String> map=new HashMap();
       		map.put("Name",user.getName()); 
       		map.put("Email", user.getEmail()); 
       		map.put("Gender", user.getGender()); 
       		map.put("Password", user.getPassword()); 
       	%>
       	
       		<%
       			String ar[]=new String[]{"Name","Email" , "Gender", "Password"};
       			for(String ele: ar)
       			{
       		%>
        	<div class="group">
            	<label><%=ele %></label>
            	<input name="<%=ele %>" required="" type="<%=(ele.equals("Password")?"password":"text") %>" class="input" value="<%=map.get(ele) %>">
            	<span class="highlight"></span>
            	<span class="bar"></span>
            	
        	</div>
        	<%
       			}
        	%>

        	<button class="custom-btn btn-2">S A V E</button>
    	</form>
    </main>
	
	
</body>
</html> --%>