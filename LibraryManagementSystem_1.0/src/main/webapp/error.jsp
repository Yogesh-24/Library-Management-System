<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <nav class="navbar">
        <h1>Library Management System</h1>
        <span class="page-title">Error</span>
    </nav>
    <div class="container">
        <h2>Something Went Wrong!</h2>
        <div class="error-message">
            <%
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null) {
            %>
            <p><%= errorMessage %></p>
            <%
                } else {
            %>
            <p>An unexpected error occurred. Please try again.</p>
            <%
                }
            %>
        </div>
        <a href="index.jsp" class="function-button">Back to Home</a>
    </div>
   
</body>
</html>
