/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import java.util.List;

import DAO.*;
import POJO.*;

/**
 *
 * @author admin
 */
public class RoleBUS {
    
	/** Get all Role **/
    public static List<Role> lstRole(String lang) {
    	return RoleDAO.lstRole(lang);
    }
    /** Get Role in ID **/
    public static Role getRole( int id, String lang) {
        return RoleDAO.getRole(id, lang);
    }
    
}
