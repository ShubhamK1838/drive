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
<title>Admin Page </title>
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
	<div class="users">
		<%
		User root=(User)session.getAttribute("user");
		if(root==null || root.getUserId()!=1)
		{
			response.sendRedirect("home.jsp"); 
		}
			
			
		List<HttpSession> loggedUsers = (List<HttpSession>) getServletContext().getAttribute("logged_users");

		if (loggedUsers != null ) {
			for (HttpSession sesion : loggedUsers) {
				try {
			User user = (User) sesion.getAttribute("user");
			if (user != null) {
		%>
		<div class="user" id="<%=user.getUserId()%>">
			<img
				src="data:image/jpeg;base64,<%=Base64.getEncoder().encodeToString(user.getImage().getImage())%>"
				alt="User Image" class="user-image">
			<p><%=user.getName()%></p>
			<p><%=sesion.getId()%></p>
			<button onclick="removeUser('<%=sesion.getId()%>')">Remove</button>
		</div>
		<%
		}
		} catch (IllegalStateException e) {
		// Handle the case where the session is already invalidated
		out.println("<p>Session for user has been invalidated.</p>");
		}
		}
		} else {
		%>
		<p>No logged in users found.</p>
		<%
		}
		%>
	</div>

	<script>
        function removeUser(userId) {
            if (confirm('Are you sure you want to remove this user?')) {
                // Show loader
                document.getElementById('loader').style.display = 'block';

                fetch("LogoutServlet?user_id=" + userId)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Network response was not ok');
                        }
                        return response.text();
                    })
                    .then(result => {
                        alert('User removed successfully');
                        location.reload(); // Reload the page to update the user list
                    })
                    .catch(error => {
                        console.error('There was a problem with the remove operation:', error);
                        alert('Failed to remove user');
                        document.getElementById('loader').style.display = 'none';
                    });
            }
        }
    </script>

	<!-- Loader (loader.jsp) -->
	<div id="loader" style="display: none;">
		<%@ include file="loader.jsp"%>
	</div>
</body>
</html>
