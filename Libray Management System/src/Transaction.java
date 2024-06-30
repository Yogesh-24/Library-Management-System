import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Transaction {
    // Method to add a new transaction (borrow book)
    public static void addTransaction(int bookId, int memberId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO Transactions (book_id, member_id, borrow_date) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookId);
            stmt.setInt(2, memberId);
            stmt.setDate(3, Date.valueOf(LocalDate.now()));
            stmt.executeUpdate();
            System.out.println("Transaction added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(stmt);
            DBUtil.closeConnection(conn);
        }
    }

    // Method to update the return date of a transaction
    public static void updateReturnDate(int bookId, int memberId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE Transactions SET return_date = ? WHERE book_id = ? AND member_id = ? AND return_date IS NULL";
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            stmt.setInt(2, bookId);
            stmt.setInt(3, memberId);
            stmt.executeUpdate();
            System.out.println("Return date updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(stmt);
            DBUtil.closeConnection(conn);
        }
    }
}
