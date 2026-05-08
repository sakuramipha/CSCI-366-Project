
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
                    //customer methods here
                    break;
           
                case 2:
                    //customer membership methods here
                    
                    int choice = 0;
                    
                    Scanner customerMembershipScan = new Scanner(System.in);
                    
                    while(choice != -1){
                        
                        System.out.println("Welcome to the customer membership menu");
                        System.out.println("Press 1 to get the records associated with a membership level number");
                        System.out.println("Press 2 to update membership rewards");
                        System.out.println("Press -1 to leave this menu");
                        
                        
                        choice = customerMembershipScan.nextInt();
                        
                        if(choice == 1){
                            System.out.println("Enter a number to get the membership reward details");
                    
                            
	
                        int membershipNum = customerMembershipScan.nextInt();
            
                        CustomerMembership customerMembership = CustomerMembership.getCustomerMembershipByMembershipLevel(membershipNum);
            
                        System.out.println("Reward Level: " + customerMembership.getLevelRewards());
                        break;
                    }
                        
                        else if(choice == 2){
                            
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
                    break;
           
                case 4:
                     //movie assignment methods here
                    break;
            
                case 5:
                    //movie methods here
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
            System.out.println("Got a sql exception.");
            e.printStackTrace();
        } 

    }
    
    
    
    
}
