package com.library.dao;

import com.library.model.Book;
import com.library.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

	private Connection connection;

	public BookDAOImpl() {
		try {
			connection = DBUtil.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addBook(Book book) {
		String sql = "INSERT INTO Books (title, author, isbn, available) VALUES (?, ?, ?, true)";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setString(3, book.getIsbn());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Book searchBookByIsbn(String isbn) {
		String sql = "SELECT * FROM Books WHERE isbn = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, isbn);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new Book(rs.getInt("bookid"), rs.getString("title"), rs.getString("author"),
							rs.getString("isbn"), rs.getBoolean("available"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> getAvailableBooks() {
		List<Book> books = new ArrayList<>();
		String sql = "SELECT * FROM Books WHERE available = true";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				books.add(new Book(rs.getInt("bookId"), rs.getString("title"), rs.getString("author"),
						rs.getString("isbn"), rs.getBoolean("available")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public void updateBookAvailability(int bookId, boolean isAvailable) {
		String sql = "UPDATE Books SET available = ? WHERE book_id = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setBoolean(1, isAvailable);
			stmt.setInt(2, bookId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeBook(int bookId) {
		String sql = "DELETE FROM Books WHERE bookid = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, bookId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
