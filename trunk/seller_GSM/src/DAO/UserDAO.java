/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.util.List;
import POJO.*;
/**
 *
 * @author admin
 */
public class UserDAO extends HibernateDAO{
    
    //Get all user
    public static List<User> lstUser(String lang) {
        return HibernateDAO.getList("from User", lang);
    }
    
    //Get all user
    public static List<User> lstUser(int pageNumber, int pageSize, String lang) {
        return HibernateDAO.getList("from User",pageNumber,pageSize, lang);
    }

    //Get user in accout
    public static User getUser( String accout, String lang) {
        return (User)HibernateDAO.getObject(User.class, accout, lang);
    }

    //test login
    public static User testLogin(String account, String password, String lang) {

            List kq = HibernateDAO.getList("from User where account = '" + account + "' and pass = '" + password + "'", lang);
            if(kq.size() > 0)
                return (User)kq.get(0);
            return null;
    }
    
    //Get list User in Role
    public static List<User> lstUser (Role r, String lang) {
        return HibernateDAO.getList("from User where role.roleId=" + r.getRoleId(), lang);
    }
    
    //Get list User in Role
    public static List<User> lstUser (Role r,int pageNumber, int pageSize, String lang) {
        return HibernateDAO.getList("from User where role.roleId=" + r.getRoleId(),pageNumber,pageSize, lang);
    }
    
    //insert user
    public static boolean insertUser(User u, String lang) {
        return HibernateDAO.insert(u, lang);
    }

    //Update user
    public static boolean updateUser(User u, String lang) {
        return HibernateDAO.update(u, lang);
    }

    //Delete user
    public static boolean deleteUser(User u, String lang) {
        return HibernateDAO.delete(u, lang);
    }
    
}
