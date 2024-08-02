package com.library.controller;

import com.library.dao.BookDAO;
import com.library.dao.BookDAOImpl;
import com.library.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private BookDAO bookDAO;

	@Override
	public void init() throws ServletException {
		bookDAO = new BookDAOImpl(); // Initializing the BookDAO implementation
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null) {
			response.sendRedirect("index.jsp"); // Redirect if action is null
			return;
		}

		switch (action) {
		case "add":
			addBook(request, response);
			break;
		case "search":
			searchBook(request, response);
			break;
		case "remove":
			removeBook(request, response);
			break;
		case "viewAll": // Handling the viewAll action
			viewAllBooks(request, response);
			break;
		default:
			response.sendRedirect("index.jsp"); // Handling unexpected actions
			break;
		}
	}

	private void addBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");

		// Validate input
		if (title == null || author == null || isbn == null || title.trim().isEmpty() || author.trim().isEmpty()
				|| isbn.trim().isEmpty()) {
			request.setAttribute("errorMessage", "All fields are required.");
			request.getRequestDispatcher("addBook.jsp").forward(request, response);
			return;
		}

		Book book = new Book(0, title, author, isbn, true);
		bookDAO.addBook(book);
		response.sendRedirect("index.jsp");
	}

	private void searchBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String isbn = request.getParameter("isbn");

		// Validate input
		if (isbn == null || isbn.trim().isEmpty()) {
			request.setAttribute("errorMessage", "ISBN is required.");
			request.getRequestDispatcher("searchBook.jsp").forward(request, response);
			return;
		}

		Book book = bookDAO.searchBookByIsbn(isbn);
		request.setAttribute("book", book);
		request.getRequestDispatcher("searchBook.jsp").forward(request, response);
	}

	private void removeBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			bookDAO.removeBook(bookId);
			response.sendRedirect("index.jsp");
		} catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "Invalid book ID.");
			request.getRequestDispatcher("error.jsp").forward(request, response); // Will redirect to error page if invalid Id
																					
		}
	}

	// Method to handle viewing all books
	private void viewAllBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Book> availableBooks = bookDAO.getAvailableBooks();
		request.setAttribute("availableBooks", availableBooks);
		request.getRequestDispatcher("availableBooks.jsp").forward(request, response);
		System.out.println(availableBooks.size());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		viewAllBooks(request, response); // Default to viewing all books if GET request..
	}
}
