<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.User" %>
<%@ page import="java.util.Base64" %>

<%

    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp"); // Redirect to login page if user is not logged in
        return;
    }
%>
    <header class="navbar">
        <div class="navbar-brand">
            <img src="logo.png" alt="Logo" class="logo">
            <span> CloudFileServer</span>
        </div>
        <nav class="navbar-nav">
            <a href="home.jsp">Home</a>
            <a href="uploadfile.jsp">Upload</a>
            <a href="MyFiles.jsp">My Files</a>
            <a href="edit.jsp">Setting</a>
            <a href="LogoutServlet">Logout</a>
        </nav>
        <div class="user-profile">
            <img src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(user.getImage().getImage()) %>" alt="User Image" class="user-image">
            <span class="user-name"><%= user.getName() %></span>
        </div>
    </header>
    