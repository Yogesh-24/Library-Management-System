import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(int bookId, String title, String author, String isbn, boolean isAvailable) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Books [Book ID=" + bookId + ", Title=" + title + ", Author=" + author + ", ISBN=" + isbn + ", Available=" + isAvailable + "]";
    }

    // Method to add a book to the database
    public static void addBook(String title, String author, String isbn) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO Books (title, author, isbn, is_available) VALUES (?, ?, ?, true)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, isbn);
            stmt.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(stmt);
            DBUtil.closeConnection(conn);
        }
    }

    // Method to search for a book by its ISBN
    public static Book searchBookByIsbn(String isbn) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM Books WHERE isbn = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, isbn);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getBoolean("is_available")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(stmt);
            DBUtil.closeConnection(conn);
        }
        return null;
    }

    // Method to retrieve all available books from the database
    public static List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM Books WHERE is_available = true";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getBoolean("is_available")
                );
                availableBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(stmt);
            DBUtil.closeConnection(conn);
        }
        return availableBooks;
    }

    // Method to update the availability of a book
    public static void updateBookAvailability(int bookId, boolean isAvailable) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE Books SET is_available = ? WHERE book_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, isAvailable);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();
            System.out.println("Book availability updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(stmt);
            DBUtil.closeConnection(conn);
        }
    }

    // Method to remove a book from the database
    public static void removeBook(int bookId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "DELETE FROM Books WHERE book_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book removed successfully.");
            } else {
                System.out.println("Book not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(stmt);
            DBUtil.closeConnection(conn);
        }
    }
}
