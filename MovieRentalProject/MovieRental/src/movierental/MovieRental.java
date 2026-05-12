
package movierental;

import java.sql.SQLException;
import java.util.Scanner;


public class MovieRental {

    
    public static void main(String[] args) {
        
        try{
        Scanner scan = new Scanner(System.in);
        
        int userNum = 0;
        
        OUTER:
        while (userNum != -1) {
            
            System.out.println("Welcome to the movie rental console");
            System.out.println("Enter the following numbers to access what you need");
            System.out.println("1 to access the Customer table");
            System.out.println("2 to access the Customer Membership table");
            System.out.println("3 to access the Address table");
            System.out.println("4 to access the Movie Assignment table");
            System.out.println("5 to access the Movie table");
            System.out.println("-1 to exit the console");
            
            userNum = scan.nextInt();
            
            switch (userNum) {
            
                case 1:
                        int customerChoice = 0;
                        Scanner customerScan = new Scanner(System.in);
                        
                        while(customerChoice != -1){
                        System.out.println("Welcome to the customer menu");
                        System.out.println("Press 1 to add a customer");
                        System.out.println("Press 2 to remove a customer");
                        System.out.println("Press 3 to view customer details");
                        System.out.println("Press -1 to leave this menu");
        
                        customerChoice = customerScan.nextInt();
                        customerScan.nextLine();
        
                        if(customerChoice == 1){
                            customerScan.nextLine();
                            System.out.println("Enter first name:");
                            String first = customerScan.nextLine();
                            System.out.println("Enter last name:");
                            String last = customerScan.nextLine();
                            System.out.println("Enter email:");
                            String email = customerScan.nextLine();
                            System.out.println("Enter phone number:");
                            String phone = customerScan.nextLine();
                            System.out.println("Enter address ID:");
                            int addrID = customerScan.nextInt();
                            System.out.println("Enter membership level:");
                            int memLevel = customerScan.nextInt();
            
                            boolean isAdded = Customer.addCustomer(first, last, email, phone, addrID, memLevel);
                            if(isAdded){ System.out.println("Customer added successfully"); }
                            else{ System.out.println("Customer addition didn't work"); }
                            break;
                        }
                        else if(customerChoice == 2){
                            System.out.println("Enter customer ID to remove:");
                            int custID = customerScan.nextInt();
                            boolean isRemoved = Customer.removeCustomer(custID);
                            if(isRemoved){ System.out.println("Customer removed successfully"); }
                            else{ System.out.println("Customer removal didn't work"); }
                            break;
                        }
                        else if(customerChoice == 3){
                            System.out.println("Enter customer ID:");
                            int custID = customerScan.nextInt();
                            Customer.getCustomerDetails(custID);
                            break;
                        }
                    }
                    break;
                    
           
                case 2:
                    //customer membership methods here
                    
                    int membershipChoice = 0;
                    
                    Scanner customerMembershipScan = new Scanner(System.in);
                    
                    while(membershipChoice != -1){
                        System.out.println("Welcome to the customer membership menu");
                        System.out.println("Press 1 to get the records associated with a membership level number");
                        System.out.println("Press 2 to update membership rewards");
                        System.out.println("Press -1 to leave this menu");
                        
                        membershipChoice = customerMembershipScan.nextInt();
                        customerMembershipScan.nextLine();
                        
                        if(membershipChoice == 1){
                            System.out.println("Enter a number to get the membership reward details");

                            int membershipNum = customerMembershipScan.nextInt();
                
                            CustomerMembership customerMembership = CustomerMembership.getCustomerMembershipByMembershipLevel(membershipNum);
                
                            System.out.println("Reward Level: " + customerMembership.getLevelRewards());
                            break;
                        }
                        
                        else if(membershipChoice == 2){
                            
                            customerMembershipScan.nextLine();
                            
                            System.out.println("Enter in the updated reward information");
                            
                            String setReward = customerMembershipScan.nextLine();
                            
                            System.out.println("Enter in the membership level ID that you'd like to update");
                            
                            int membershipNum = customerMembershipScan.nextInt();
                 
                            boolean isUpdated = CustomerMembership.updateCustomerMembershipLevelRewards(setReward, membershipNum);
                            
                            if(isUpdated){
                                System.out.println("The update worked successfully");
                            }
                            else{
                                System.out.println("The update didn't work");
                            }
                            break;
                        }  
                    }
                    break;
           
                case 3:  
                    //address methods here
                    int addressChoice = 0;
                    Scanner addressScan = new Scanner(System.in);

                    while (addressChoice!= -1){
                        System.out.println("Welcome to the address menu");
                        System.out.println("Press 1 to add an address");
                        System.out.println("Press 2 to remove an address");
                        System.out.println("Press 3 to update an address");

                        addressChoice = addressScan.nextInt();
                        addressScan.nextLine();

                        if (addressChoice == 1){
                            System.out.println("Enter street: ");
                            String street = addressScan.nextLine();

                            System.out.println("Enter city: ");
                            String city = addressScan.nextLine();

                            System.out.println("Enter state: ");
                            String state = addressScan.nextLine();

                            System.out.println("Enter zip: ");
                            String zip = addressScan.nextLine();

                            boolean isAddedAddress = Address.addAddress(street, city, state, zip);
                            if(isAddedAddress){
                                System.out.println("Address added successfully");
                            }
                            else{
                                System.out.println("Address addition didn't work");
                            }
                            break;
                        }

                        else if(addressChoice == 2){
                            System.out.println("Enter address ID: ");
                            int addressID = addressScan.nextInt();

                            boolean isRemovedAddress = Address.removeAddress(addressID);
                            if(isRemovedAddress){
                                System.out.println("Address removed successfully");
                            }
                            else{
                                System.out.println("Address removal didn't work");
                            }
                            break;
                        }
                        else if(addressChoice == 3){
                            Address.getCustomerAddresses();
                            break;
                        }
                    }
                    break;
           
                case 4:
                    int assignChoice = 0;
                    Scanner assignScan = new Scanner(System.in);
    
                    while(assignChoice != -1){
                        System.out.println("Welcome to the movie assignment menu");
                        System.out.println("Press 1 to rent a movie");
                        System.out.println("Press 2 to return a movie");
                        System.out.println("Press 3 to view all active rentals");
                        System.out.println("Press -1 to leave this menu");
        
                        assignChoice = assignScan.nextInt();
                        assignScan.nextLine();
        
                        if(assignChoice == 1){
                           System.out.println("Enter customer ID:");
                           int custID = assignScan.nextInt();
                           System.out.println("Enter movie ID:");
                           int movID = assignScan.nextInt();
                           assignScan.nextLine();
                            System.out.println("Enter rental date (YYYY-MM-DD):");
                            String rental = assignScan.nextLine();
                            System.out.println("Enter return date (YYYY-MM-DD):");
                            String ret = assignScan.nextLine();
            
                            boolean isAssigned = MovieAssignment.assignMovie(custID, movID, rental, ret);
                            if(isAssigned){ System.out.println("Movie rented successfully"); }
                            else{ System.out.println("Movie rental didn't work"); }
                            break;
                        }
                        else if(assignChoice == 2){
                            System.out.println("Enter movie assignment ID to return:");
                            int assignID = assignScan.nextInt();
                            boolean isReturned = MovieAssignment.returnMovie(assignID);
                            if(isReturned){ System.out.println("Movie returned successfully"); }
                            else{ System.out.println("Movie return didn't work"); }
                            break;
                        }
                        else if(assignChoice == 3){
                            MovieAssignment.getActiveRentals();
                            break;
                        }
                    }
                    break;
            
                case 5:
                    //movie methods here
                    int movieChoice = 0;

                    Scanner movieScan = new Scanner(System.in);

                    while (movieChoice != -1){
                        System.out.println("Welcome to the movie menu");
                        System.out.println("Press 1 to add a new movie");
                        System.out.println("Press 2 to delete a movie");
                        System.out.println("Press -1 to leave this menu");

                        movieChoice = movieScan.nextInt();
                        movieScan.nextLine();

                        if (movieChoice == 1){
                            movieScan.nextLine();

                            System.out.println("Enter movie name: ");
                            String movieName = movieScan.nextLine();

                            System.out.println("Enter movie description: ");
                            String movieDescription = movieScan.nextLine();

                            System.out.println("Enter movie stock: ");
                            int movieStock = movieScan.nextInt();

                            boolean isAdded = Movie.addMovie(movieName, movieDescription, movieStock);

                            if(isAdded){
                                System.out.println("Movie added successfully");
                            }
                            else{
                                System.out.println("Movie addition didn't work");
                            }
                            break;
                        }

                        else if(movieChoice == 2){
                            System.out.println("Enter movie ID to remove: ");

                            int movieID = movieScan.nextInt();

                            boolean isRemoved = Movie.removedMovie(movieID);

                            if(isRemoved){
                                System.out.println("Movie removed successfully");
                            }
                            else{
                                System.out.println("Movie removal didn't work");
                            }
                            break;
                        }
                    }
                    break;
                case -1:
                    break OUTER;
                
                default:
                    System.out.println("Input was invalid, try again.");
                    break;
            }
        }
        
        System.out.println("You have quit the console.");
        }
        
        catch (SQLException e) {
            System.out.println("Got an SQL exception.");
            e.printStackTrace();
        } 
    }
}
