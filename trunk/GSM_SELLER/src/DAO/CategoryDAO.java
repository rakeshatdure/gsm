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
public class CategoryDAO extends HibernateDAO{
    
    //Get all Category
    public static List<Category> lstCategory(String lang) {
        return HibernateDAO.getList("from Category", lang);
    }
     
    //Get Category in ID
    public static Category getCategory( String id,String lang) {
        return (Category)HibernateDAO.getObject(Category.class, id, lang);
    }

    //Insert Category
    public static boolean insertCategory(Category p,String lang) {
        return HibernateDAO.insert(p, lang);
    }

    //Update Category
    public static boolean updateCategory(Category p,String lang) {
        return HibernateDAO.update(p, lang);
    }

    //Delete Category
    public static boolean deleteCategory(Category p,String lang) {
        return HibernateDAO.delete(p, lang);
    }
    
}
