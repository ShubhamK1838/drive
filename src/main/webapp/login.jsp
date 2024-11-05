<%@page import="entities.User"%>
<%@page import="database.UserDao"%>
<%@page import="dbhelper.SpContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dbhelper.*" %>

<html >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="css/styles.css">
    
    <style>
    	#loader{
    	margin-top:30%;
    	display:none; 
    	}
    	
    </style>
</head>
<body  >
	
	<div id="loader" >
		<%@include file="loader.jsp"%>
	</div>
    <div class="container " id="login">
        <form  id="form1" class="form" method="post"  action="Login" id="login-form">
            <h2>Login</h2>
            <label for="email">Email</label>
            <input required type="email" id="email"  required name="email" >

            <label for="password">Password</label>
            <input required type="password" id="password" required  name="password" >

            <input  class="btn" type="submit" value="Login">
          
        </form>
        <button class="btn"  onclick="show()"> SignUp</button>
    </div>

    <div class="container hide" id ="signup" >
        <form id="form2" class="form" action="Signup" method="post" enctype="multipart/form-data" id="signup-form">
            <h2>Sign Up</h2>
            <label for="name">Name</label>
            <input required type="text" id="name" name="name" required>

            <label for="email">Email</label>
            <input required required type="email" id="email" name="email" required>

            <label for="password">Password</label>
            <input required required type="password" id="password" name="password" required>

            <label for="gender">Gender</label>
            <select required required id="gender" name="gender">
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="other">Other</option>
            </select>

            <label for="image">Profile Image</label>
            <input  required type="file" id="image" name="image">

            <button  class="btn" type="submit">Sign Up</button>
          
        </form>
        <button onclick="show()"  class="btn" > Login</button>
        <p id="servermessage"></p>
    </div>

    <p id="p" ></p>
    <script >
    	
    	
		var ld=  document.getElementById("loader"); 
        var btn1=document.getElementById("login"); 
        var btn2=document.getElementById("signup"); 
        
        var f1 = document.getElementById("form1"); 
        var f2 = document.getElementById("form2"); 
        f1.addEventListener("submit", function(event){
        	loader(); 
        }); 
        
        f2.addEventListener("submit", function(event){
        	loader(); 
        });  
        
        
        function show()
        {
            btn1.classList.toggle("hide"); 
            btn2.classList.toggle("hide"); 

        }
        
        function loader()
        {
        	btn1.style.display="none"; 
        	btn2.style.display="none"; 
        	ld.style.display="inline-block"; 
        	
        }

       
        
    </script>
</body>
</html>
