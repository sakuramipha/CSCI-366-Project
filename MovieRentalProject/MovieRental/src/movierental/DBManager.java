
package movierental;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBManager {
    static String jdbcURL = "jdbc:postgresql://localhost:5432/movierental";
    static String username = "postgres";
    static String password = "#dis8bled101205*#";
    
    private static Connection connection; 
    
    public DBManager(){
        
    }
    
    public static Connection getConnection() {
        if (connection == null){
            setConnection();
        }
        return connection;
    }
    
    private static void setConnection(){
        try {
        Class.forName("org.postgresql.Driver");
            
        connection = DriverManager.getConnection(jdbcURL, username, password);
        }
        catch (ClassNotFoundException e){
            System.out.println("Cannot load the postgresql driver.");
        }
        catch (SQLException e) {
            System.out.println("Got a sql exception.");
            e.printStackTrace();
        }
       }
    
    
    
    
    }
    