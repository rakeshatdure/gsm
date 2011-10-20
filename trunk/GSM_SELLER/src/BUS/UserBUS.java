/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.*;
import POJO.*;

import java.util.List;
/**
 *
 * @author admin
 */
public class UserBUS {
 
	public static void main(String[] args){
		try{
			User user = UserBUS.getUser("sell01", "MALL_VN");
			user.getAccount();
			System.out.print("aa");
		}catch(Exception ex){
			System.out.print("bb");
		}
		
	}
	
    /** Get all user **/
    public static List<User> lstUser(String lang) {
        return UserDAO.lstUser(lang);
    }
    
    /** Get all user **/
    public static List<User> lstUser(int pageNumber, int pageSize, String lang) {
    	return UserDAO.lstUser(pageNumber, pageSize, lang);
    }

    /** Get user in accout **/
    public static User getUser( String accout, String lang) {
        return UserDAO.getUser(accout, lang);
    }

    /** test login **/
    public static User testLogin(String account, String password, String lang) {
         return UserDAO.testLogin(account, password, lang);
    }
    
  //Get list User in Role
    public static List<User> lstUser (Role r, String lang) {
    	return UserDAO.lstUser(r, lang);
    }
    //Get list User in Role
    public static List<User> lstUser (Role r,int pageNumber, int pageSize, String lang) {
    	return UserDAO.lstUser(r, pageNumber, pageSize, lang);
    }
    /** insert user **/
    public static boolean insertUser(User u, String lang) {
        return UserDAO.insertUser(u, lang);
    }

    //Update user
    public static boolean updateUser(User u, String lang) {
        return UserDAO.updateUser(u, lang);
    }

    //Delete user
    public static boolean deleteUser(User u, String lang) {
        return UserDAO.deleteUser(u, lang);
    }
    
}
