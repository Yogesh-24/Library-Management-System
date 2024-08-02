<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Library Management System</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <nav class="navbar">
        <h1>Library Management System</h1>
        <span class="page-title">Home</span>
    </nav>
    <div class="container">
        <h2>Select an Action</h2>
        <div class="functions">
            <a href="BookServlet?action=viewAll" class="function-button">View Available Books</a>
            <a href="addBook.jsp" class="function-button">Add Book</a>
            <a href="searchBook.jsp" class="function-button">Search Book</a>
        </div>
    </div>
  
</body>
</html>
