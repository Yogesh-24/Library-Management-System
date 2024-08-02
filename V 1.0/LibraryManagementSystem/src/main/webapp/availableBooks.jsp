<%@ page import="java.util.List" %>
<%@ page import="com.library.model.Book" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Available Books</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <nav class="navbar">
        <h1>Library Management System</h1>
        <span class="page-title">Available Books</span>
    </nav>
    <div class="container">
        <h2>List of Available Books</h2>
        <table class="book-table">
            <tr>
                <th>Book ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>ISBN</th>
                <th>Availability</th>
            </tr>
            <%
                List<Book> availableBooks = (List<Book>) request.getAttribute("availableBooks");
                for (Book book : availableBooks) {
            %>
            <tr>
                <td><%= book.getBookId() %></td>
                <td><%= book.getTitle() %></td>
                <td><%= book.getAuthor() %></td>
                <td><%= book.getIsbn() %></td>
                <td><%= book.isAvailable() ? "Available" : "Not Available" %></td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
   
</body>
</html>
