<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>

	<%@include file="commonheader.jsp" %>	
</head>
<body>

	
	
	<%@include file="nav.jsp" %>	
	<main class="main-content">
        <h1>Welcome to Cloud Storage, <%= user.getName() %>!</h1>
        <p>Store and manage your files securely.</p>
      
    </main>
	
	
  </body>
</html>