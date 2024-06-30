import java.util.ArrayList;
import java.util.List;
import java.sql.*;



public class Member {
    private int memberId;
    private String name;
    private List<Book> borrowedBooks;

    public Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    // Method to add a member to the database
    public static void addMember(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO Members (name) VALUES (?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.executeUpdate();
            System.out.println("Member added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(stmt);
            DBUtil.closeConnection(conn);
        }
    }

    // Method to search for a member by their ID
    public static Member searchMemberById(int memberId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM Members WHERE member_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, memberId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new Member(
                    rs.getInt("member_id"),
                    rs.getString("name")
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

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.setAvailable(false);
            Book.updateBookAvailability(book.getBookId(), false);
            Transaction.addTransaction(book.getBookId(), this.memberId);
        } else {
            System.out.println("Book is currently not available.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setAvailable(true);
            Book.updateBookAvailability(book.getBookId(), true);
            Transaction.updateReturnDate(book.getBookId(), this.memberId);
        } else {
            System.out.println("This book was not borrowed by the member.");
        }
    }

    @Override
    public String toString() {
        return "Members [Member ID=" + memberId + ", Name=" + name + "]";
    }
}
