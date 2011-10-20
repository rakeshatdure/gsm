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
public class CategoryBUS {
    
    /** Get all Category **/
    public static List<Category> lstCategory(String lang) {
        return CategoryDAO.lstCategory(lang);
    }

     
    /** Get Category in ID **/
    public static Category getCategory( String id,String lang) {
        return CategoryDAO.getCategory(id, lang);
    }

    /** Insert Category **/ 
    public static boolean insertCategory(Category p,String lang) {
        return CategoryDAO.insertCategory(p, lang);
    }

    /** Update Category **/
    public static boolean updateCategory(Category p, String lang) {
        return CategoryDAO.updateCategory(p, lang);
    }

    /** Delete Category **/
    public static boolean deleteCategory(Category p, String lang) {
        return CategoryDAO.deleteCategory(p, lang);
    }
    
}
