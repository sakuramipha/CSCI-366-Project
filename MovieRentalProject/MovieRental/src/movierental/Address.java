
package movierental;
        
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Address {
    private int addressID;
    private String street;
    private String city;
    private String state;
    private String zip;
    
    public Address (String street, String city, String state, String zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    
    public int getAddressID() {return this.addressID;}
    public String getStreet() {return this.street;}
    public String getCity() {return this.city;}
    public String getState() {return this.state;}
    public String getZip() {return this.zip;}
    
    public void setStreet(String street) {this.street = street;}
    public void setCity(String city) {this.city = city;}
    public void setState(String state) {this.state = state;}
    public void setZip(String zip) {this.zip = zip;}
    
    public static boolean addAddress(String street, String city, String state, String zip) throws SQLException{
        Connection connection = DBManager.getConnection();

        String insert_address = "INSERT INTO address (street, city, state, zip) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt_insert_address = connection.prepareStatement(insert_address);

        stmt_insert_address.setString(1, street);
        stmt_insert_address.setString(2, city);
        stmt_insert_address.setString(3, state);
        stmt_insert_address.setString(4, zip);

        int updatedRows = stmt_insert_address.executeUpdate();

        return updatedRows > 0;
    }

    public static boolean removeAddress(int addressID) throws SQLException{
        Connection connection = DBManager.getConnection();

        String delete_address = "DELETE FROM address WHERE address_id = ?";

        PreparedStatement stmt_delete_address = connection.prepareStatement(delete_address);

        stmt_delete_address.setInt(1, addressID);

        int deletedRows = stmt_delete_address.executeUpdate();

        return deletedRows > 0;
    }
    
    public static void getCustomerAddresses() throws SQLException {
        Connection connection = DBManager.getConnection();
        
        String get_customer_addresses = "SELECT * FROM customer_addresses";
        
        PreparedStatement stmt_get_customer_addresses = connection.prepareStatement(get_customer_addresses);
        
        ResultSet rs = stmt_get_customer_addresses.executeQuery();
        while (rs.next()) {
            System.out.println(
                      rs.getString("firstname") + " " + rs.getString("lastname")
            + " | " + rs.getString("street") + ", " + rs.getString("city") + ", " 
                    + rs.getString("state") + ", " + rs.getString("zip")
            );
        }
    }
}
