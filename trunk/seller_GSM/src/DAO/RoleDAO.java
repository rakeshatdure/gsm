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
public class RoleDAO extends HibernateDAO{
    
	//Get all Role
    public static List<Role> lstRole(String lang) {
        return HibernateDAO.getList("from Role", lang);
    }
    
    //Get role in ID
    public static Role getRole( int id, String lang) {
        return (Role)HibernateDAO.getObject(Role.class, id, lang);
    }
}
