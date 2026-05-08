package movierental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieAssignment {

    private int movieAssignmentID;
    private int customerID;
    private int movieID;

    public MovieAssignment(int customerID, int movieID) {
        this.customerID = customerID;
        this.movieID = movieID;
    }

    public int getMovieAssignmentID() { return movieAssignmentID; }
    public int getCustomerID() { return customerID; }
    public int getMovieID() { return movieID; }

    public static boolean assignMovie(int customerID, int movieID, String rentalDate, String returnDate) throws SQLException {
    Connection connection = DBManager.getConnection();
    String sql = "INSERT INTO movie_assignment (customer_id, movie_id, rental_date, return_date) VALUES (?, ?, ?, ?)";
    PreparedStatement stmt = connection.prepareStatement(sql);
    stmt.setInt(1, customerID);
    stmt.setInt(2, movieID);
    stmt.setDate(3, java.sql.Date.valueOf(rentalDate));
    stmt.setDate(4, java.sql.Date.valueOf(returnDate));
    int rows = stmt.executeUpdate();
    return rows > 0;
    }

    public static boolean returnMovie(int movieAssignmentID) throws SQLException {
        Connection connection = DBManager.getConnection();
        String sql = "DELETE FROM movie_assignment WHERE movie_assignment_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, movieAssignmentID);
        int rows = stmt.executeUpdate();
        return rows > 0;
    }
    
    public static void getActiveRentals() throws SQLException {
    Connection connection = DBManager.getConnection();
    String sql = "SELECT ma.movie_assignment_id, c.firstname, c.lastname, m.movie_name, ma.rental_date, ma.return_date "
               + "FROM movie_assignment ma "
               + "JOIN customer c ON ma.customer_id = c.customer_id "
               + "JOIN movie m ON ma.movie_id = m.movie_id";
    PreparedStatement stmt = connection.prepareStatement(sql);
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
        System.out.println("ID: " + rs.getInt("movie_assignment_id")
            + " | " + rs.getString("firstname") + " " + rs.getString("lastname")
            + " | " + rs.getString("movie_name")
            + " | Rented: " + rs.getString("rental_date")
            + " | Due: " + rs.getString("return_date"));
    }
}
}