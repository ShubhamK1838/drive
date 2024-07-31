<%@page import="entities.File"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.Session"%>
<%@page import="database.FactoryProvider"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MyFiles</title>

<%@include file="commonheader.jsp"%>
<style>
.file-header {
		display: grid;
	grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr;
	
	}

.files {
	width: 100%;
}


.file {
	display: grid;
	grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr 1fr;
	background-color: azure;
	align-items: center;
	margin:4px;
    border-radius: 5px; ; 
}

.file p {
	padding-left: 10px;
	  word-wrap: break-word;
            white-space: pre-wrap; 
            overflow: hidden;
}

.file button {
	margin: 0px;
	min-width: 70px;
	margin-bottom: 5px;
	font-size:14px; 
	align-content:right; 
}
a{
	text-decoration:none; 
}
.onoff{
display:none; 
}

</style>
</head>
<body>

	<%@include file="nav.jsp"%>
	
	<div id="loader" class="onoff" style=" margin-top:40%;" >  
		<%@include file="loader.jsp" %>
	</div>
	<main class="main-content" id="body">

		<div class="files">

			<div class="file-header">
				<p>File Name</p>
				<p>File Type</p>
				<p>Added Date</p>
				<p>File Size</p>
			
			</div>

			<%
				User cur_user=(User)session.getAttribute("user"); 
				FactoryProvider fp=new FactoryProvider(); 
				Session s=fp.getSession();  
				
				Query query=s.createQuery("from File where userid=:usr"); 
				query.setParameter("usr", user.getUserId());
				
				List<File> files=query.list(); 
				
				for(File ele: files)
				{
					String dt=ele.getAddedDate().toString(); 
			%>
			<div class="file" id="<%=ele.getFileId()%>">
				<p><%=ele.getFileName() %></p>
				<p><%=ele.getFileType() %></p>
				<p><%=dt %></p>
				<p><%=ele.getSize() %></p>
				<a href="download?fileid=<%=ele.getFileId()%>">
				<button value="<%=ele.getUser()%>" class="btn">Download</button>
				</a>
				<%-- <a href="delete?file_name=<%=ele.getFileName()%>&file_id=<%=ele.getFileId() %>"> --%>
				<button onclick="delet('<%=ele.getFileId()%>')" style="background:red" value="" class="btn">Delete</button>
				<button onclick="openShareList('<%=ele.getFileId()%>')" style="background:green" value="" class="btn">Share</button>
				
			</div>
			<%
				}
				fp.close(); 
			%>
		</div>
	</main>
	
	<script>
	
	var loader=document.getElementById("loader"); 
	var body=document.getElementById("body"); 
	
	function on_loader_off()
	{
		loader.classList.toggle("onoff"); 
		body.classList.toggle("onoff"); 
	}
	
		function delet(file )
		{
			on_loader_off(); 
			var file_row=document.getElementById(file); 
			
			
			var cnf=confirm("Are You Sure To Delete File "+ file); 
			console.log(" file id "+ file+ " " + cnf);
			
			if(cnf==false) 
			{
				on_loader_off(); 
				return ; 
					
			}
			
			var url="delete?file_id="+file; 
			console.log(" the url " + url); 
			var fet=fetch(url); 
			
			fet.then((res)=>{
				
				console.log("Response get "); 
				return res.text(); 
				
			}).then((res)=>{
				
				console.log(" the result is " + res); 
				on_loader_off(); 
				file_row.remove(); 
				
				
			}).catch((error)=>{
				console.log("Network Issue..."); 
			})
			
		}
		
		function openShareList(file_id)
		{
			window.open("shareuserlist.jsp?fileid="+file_id, "_self");
		}
	</script>
</body>
</html>