<%@page import="entities.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="css/styles.css">
<style> 
	.toggle{
		display:none; 
	}
	.grids{
		margin-top:60px; 
		display:grid; 
		grid-template-columns: auto auto; 
		gap:4px ;
		color:white;
	}
</style>
</head>
<body >
		
		
		<%
			 User user=(User)session.getAttribute("user"); 
		
			if(user==null)
			{
				response.sendRedirect("login.jsp"); 
			}
			
			
			
		%>
	
	<main class="container">
	<Div class="form">

		<span class="mainHeading">Enter OTP</span>
		<p class="otpSubheading">Click On Send Code To Receive the verification code to your
			Email</p>
	
		<div class="inputContainer">
			<input required="required" maxlength="1" type="text" class="otp-input" id="c1">
			<input required="required" maxlength="1" type="text" class="otp-input"id="c2"> 
			<input required="required" maxlength="1" type="text" class="otp-input" id="c3">
			<input required="required" maxlength="1" type="text" class="otp-input" id="c4">
			<input type="hidden" id="email"  name="user-email" value="<%=user!=null?user.getEmail():""%>">
		</div>
	
		<div class="grids">
		
		<button  style="height:40px" onClick="verifyCode()" class="btn-2"  id="verif" >Verify</button>
		<button id="sendCode"  onClick="sendCode()" class="btn-2">Send Code</button>
			
		</div>
		<hr>
	<p class="resendNote">
		
			<p id="timer"></p>
			<p style="color:red" id="error"></p>
	</p>
		

	
	</Div>
	</main>
	<script>
	
		var timer=document.getElementById("timer"); 
		var t=120; 
		var codebutton=document.getElementById("sendCode"); 
		var verif=document.getElementById("verif"); 
		var er=document.getElementById("error"); 
		
		
		function verifyCode()
		{
			var cod=getValue("c1")+""+getValue("c2")+""+getValue("c3")+""+getValue("c4"); 
			var email=getValue("email"); 
			
			if(cod.length<=3)
			{
				er.textContent="Enter the Code..."; 
				return ; 
			}
			fetch("ValidateEmailVerification?email="+email+"&&code="+cod)
			.then(function(res){
				
				if(res.ok)
				{
					window.open("home.jsp","_self"); 
				}else 
				{
					er.textContent="Enter Valid Code...";
				}
			})
			
		}
		function getValue(ele)
		{
			return document.getElementById(ele).value; 
		}
		
		function allowDeny()
		{
			codebutton.classList.toggle("toggle"); 
		}
		function sendCode()
		{
			console.log("code sending..."); 
			
			var email=getValue("email"); 
			if( email.length<=0) return; 
			
			 var res=fetch("SendOtp?email="+email); 
			allowDeny(); 
			var tmr=startTimer(); 
			
			
			setTimeout(function(){
				
				allowDeny(); 
				stopTimer(tmr); 
				timer.textContent=""; 
				
			}, 1000*2*60); 
			
			
		}
		
		function startTimer()
		{
			 t=120; 
			return setInterval(function (){
				timer.textContent=t--; 
			}, 1000); 
		}
		function stopTimer(ele)
		{
			clearTimeout(ele); 
		}
		
	
			
	</script>
</body>
</html>