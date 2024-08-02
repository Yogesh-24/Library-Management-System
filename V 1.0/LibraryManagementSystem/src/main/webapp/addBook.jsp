<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Book</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <nav class="navbar">
        <h1>Library Management System</h1>
        <span class="page-title">Add Book</span>
    </nav>
    <div class="container">
        <h2>Add a New Book</h2>
        <form action="BookServlet" method="post">
            <input type="hidden" name="action" value="add">
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" id="title" name="title" required>
            </div>
            <div class="form-group">
                <label for="author">Author:</label>
                <input type="text" id="author" name="author" required>
            </div>
            <div class="form-group">
                <label for="isbn">ISBN:</label>
                <input type="text" id="isbn" name="isbn" required>
            </div>
            <input type="submit" class="submit-button" value="Add Book">
        </form>
        <a href="index.jsp" class="back-button">Back to Home</a>
    </div>
   
</body>
</html>
