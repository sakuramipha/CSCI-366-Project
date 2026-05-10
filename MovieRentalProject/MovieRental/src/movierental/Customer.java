package movierental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {

    private int customerID;
    private String firstName;
    private String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getCustomerID() { return customerID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    public static boolean addCustomer(String firstName, String lastName, String email,
                                       String phoneNumber, int addressID, int levelOfMembership) throws SQLException {
        Connection connection = DBManager.getConnection();
        String sql = "INSERT INTO customer (firstname, lastname, email, phone_number, address_id, level_of_membership) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, firstName);
        stmt.setString(2, lastName);
        stmt.setString(3, email);
        stmt.setString(4, phoneNumber);
        stmt.setInt(5, addressID);
        stmt.setInt(6, levelOfMembership);
        int rows = stmt.executeUpdate();
        return rows > 0;
    }

    public static boolean removeCustomer(int customerID) throws SQLException {
        Connection connection = DBManager.getConnection();
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, customerID);
        int rows = stmt.executeUpdate();
        return rows > 0;
    }

    public static void getCustomerDetails(int customerID) throws SQLException {
        Connection connection = DBManager.getConnection();
        String sql = "SELECT c.firstname, c.lastname, a.city, a.state, cm.level_rewards "
                   + "FROM customer c "
                   + "JOIN address a ON c.address_id = a.address_id "
                   + "JOIN customer_membership cm ON c.level_of_membership = cm.level_of_membership "
                   + "WHERE c.customer_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, customerID);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("firstname") + " " + rs.getString("lastname")
                + " | " + rs.getString("city") + ", " + rs.getString("state")
                + " | " + rs.getString("level_rewards"));
        }
    }
    
    public static void getCustomerCountByMembership() throws SQLException {
        Connection connection = DBManager.getConnection();
        
        String get_customer_count = "SELECT level_of_membership, count(*) AS customer_count "
                + "FROM customer "
                + "GROUP BY level_of_membership "
                + "ORDER BY level_of_membership";
        
        PreparedStatement stmt_get_customer_count = connection.prepareStatement(get_customer_count);
        
        ResultSet rs = stmt_get_customer_count.executeQuery();
        while (rs.next()) {
            System.out.println(
                "Level " + rs.getInt("level_of_membership") + ": "
                + rs.getInt("customer_count") + " customer(s)"
            );
        }
    }
}