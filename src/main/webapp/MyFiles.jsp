<%@page import="database.FileDao"%>
<%@page import="dbhelper.SpContext"%>
<%@page import="entities.File"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.Session"%>
<%@page import="database.FactoryProvider"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MyFiles</title>
<%@include file="commonheader.jsp"%>
<style>
* {

	padding: 0;
	margin: 0;
	
	font-family: 'Courier New', Courier, monospace;
}

body {
	width: 100vw;
	height: 100vh;
	background-color: aliceblue;
}

.elements {
	display: flex;
	flex-wrap: wrap;
	gap: 10px;
	padding: 20px;
	justify-content: center; /* Center the items horizontally */
}

.entity {
	background-color: rgb(217, 237, 242);
	display: flex;
	flex-direction: column;
	height:auto; 
	width: 300px;
	border: solid 1px;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	
}


	.content-{
		width:300px; 
		height:200px;
		margin:auto; 
	}
 
.entity img, .entity video {
	width:100%;  
	height:100%;
	border-radius:3px; 
	padding:4px; 
}

.content-info {
	display: flex;
	flex-direction: column;
	 flex-wrap: wrap;
	padding: 10px; /* Increased padding for better spacing */
	border-bottom: solid 1px;
	
}

.content-info p {
	margin: 5px 0;
	font-size: 14px;
	color: #333;
	width:100%; 
	overlow:hidden;
 overflow: hidden;
	
	
}

.content-action {
	display: flex;
	justify-content: space-around;
	padding: 10px; /* Added padding for better spacing */
}

.content-action button {
	width: 80px;
	height: 40px;
	cursor: pointer;
	font-weight: bolder;
	background-color: rgb(11, 117, 216);
	color: white;
	border: none;
	border-radius: 5px;
	transition: background-color 0.3s;
}

.content-action button:hover {
	background-color: rgb(9, 105, 193);
}

.onoff {
	display: none;
}


</style>
</head>
<body>

	<%@include file="nav.jsp"%>

	<div id="loader" class="onoff" style="margin-top: 40%;">
		<%@include file="loader.jsp"%>
	</div>

	<main class="main-content" id="body">

		<div class="elements">
			<%
			FileDao dao = SpContext.context.getBean("fileDao", FileDao.class);
			List<File> files = dao.getUserFiles(user.getUserId());

			for (File ele : files) {
				String dt = ele.getAddedDate().toString();
			%>
			<div class="entity" id="<%=ele.getFileId()%>">
				<%-- <div class="content-">
					<%
					if (ele.getFileType().startsWith("image")) {
					%>
					<img src="Resource?id=<%=ele.getFileId()%>"
						alt="<%=ele.getFileName()%>">
					<%
					} else if (ele.getFileType().startsWith("video")) {
					%>
					<video controls>
						<source src="Resource?id=<%=ele.getFileId()%>"
							type="<%=ele.getFileType()%>">
						Your browser does not support the video tag.
					</video>
					<%
					}
					%>
				</div> --%>
				<div class="content-info">
					<p>
						Name:
						<%=ele.getFileName()%></p>
					<p>
						Size:
						<%=ele.getSize()%></p>
					<p>
						Date:
						<%=dt%></p>
					<p>
						Type:
						<%=ele.getFileType()%></p>
				</div>
				<div class="content-action">
					<a href="download?fileid=<%=ele.getFileId()%>">
						<button>Download</button>
					</a>
					<button onclick="delet('<%=ele.getFileId()%>')"
						style="background: red">Delete</button>
					<button onclick="openShareList('<%=ele.getFileId()%>')"
						style="background: green">Share</button>
				</div>
			</div>
			<%
			}
			%>
		</div>

	</main>

	<script>
        var loader = document.getElementById("loader"); 
        var body = document.getElementById("body"); 

        function on_loader_off() {
            loader.classList.toggle("onoff"); 
            body.classList.toggle("onoff"); 
        }

        function delet(file) {
            on_loader_off(); 
            var file_row = document.getElementById(file); 
            var cnf = confirm("Are You Sure To Delete File "); 
            console.log(" file id " + file + " " + cnf);

            if(cnf == false) {
                on_loader_off(); 
                return; 
            }

            var url = "delete?file_id=" + file; 
            console.log(" the url " + url); 
            var fet = fetch(url); 

            fet.then((res) => {
                console.log("Response get "); 
                return res.text(); 
            }).then((res) => {
                console.log(" the result is " + res); 
                on_loader_off(); 
                file_row.remove(); 
            }).catch((error) => {
                console.log("Network Issue..."); 
            })
        }

        function openShareList(file_id) {
            window.open("shareuserlist.jsp?fileid=" + file_id, "_self");
        }
    </script>
</body>
</html>
