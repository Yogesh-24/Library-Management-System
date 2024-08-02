<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.library.model.Book" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Book</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <nav class="navbar">
        <h1>Library Management System</h1>
        <span class="page-title">Search Book</span>
    </nav>
    <div class="container">
        <h2>Search for a Book</h2>
        <form action="../BookServlet" method="post">
            <input type="hidden" name="action" value="search">
            <div class="form-group">
                <label for="isbn">ISBN:</label>
                <input type="text" id="isbn" name="isbn" required>
            </div>
            <input type="submit" class="submit-button" value="Search Book">
        </form>
        <%
            Book book = (Book) request.getAttribute("book");
            if (book != null) {
        %>
        <h2>Book Details</h2>
        <p>Book ID: <%= book.getBookId() %></p>
        <p>Title: <%= book.getTitle() %></p>
        <p>Author: <%= book.getAuthor() %></p>
        <p>ISBN: <%= book.getIsbn() %></p>
        <p>Availability: <%= book.isAvailable() ? "Available" : "Not Available" %></p>
        <form action="BookServlet" method="post">
            <input type="hidden" name="action" value="remove">
            <input type="hidden" name="bookId" value="<%= book.getBookId() %>">
            <input type="submit" class="submit-button" value="Remove Book">
        </form>
        <%
            }
        %>
    </div>
   
</body>
</html>
