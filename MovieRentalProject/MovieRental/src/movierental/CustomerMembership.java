
package movierental;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class CustomerMembership {
    
    private int membershipLevel;
    private String levelRewards;
    
    
    public CustomerMembership(String rewardLevel){
        this.levelRewards = rewardLevel;
    }
    
    
    public static CustomerMembership getCustomerMembershipByMembershipLevel(int membershipLevel) throws SQLException{
        CustomerMembership customerMembership = null;    
        Connection connection = DBManager.getConnection();
        
        String select_one_membership = "SELECT * FROM customer_membership " 
                            + "WHERE level_of_membership = ?";
        
            PreparedStatement stmt_select_one_membership = connection.prepareStatement(select_one_membership);
            
            stmt_select_one_membership.setInt(1, (membershipLevel));
            
            ResultSet rs_select_one_membership = stmt_select_one_membership.executeQuery();
            while (rs_select_one_membership.next()){
                String description = rs_select_one_membership.getString("level_rewards");
                customerMembership = new CustomerMembership(description);
            }
            return customerMembership;
    }
    
    public static boolean updateCustomerMembershipLevelRewards(String newLevelRewards, int membershipLevel) throws SQLException{
        
        Connection connection = DBManager.getConnection();
        
        String update_membership_reward = "UPDATE customer_membership " 
                + "SET level_rewards = ?"
                + " WHERE level_of_membership = ?";
        
        PreparedStatement stmt_update_membership = connection.prepareStatement(update_membership_reward);
        
        stmt_update_membership.setString(1, newLevelRewards);
        stmt_update_membership.setInt(2, membershipLevel);
        
        int updatedRows = stmt_update_membership.executeUpdate();
        
        return updatedRows > 0;
    }
    
    
    
    
    public int getMembershipLevel(){
        return membershipLevel;
    }
    
    public void setMembershipLevel(int membershipLevel){
        this.membershipLevel = membershipLevel;
    }
    
    
    public String getLevelRewards(){
        return levelRewards;
    }
    
    public void setLevelRewards(String levelRewards){
        this.levelRewards = levelRewards;
    }
    
}
