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
public class CategoryChildDAO extends HibernateDAO{
    
    //Get all CategoryChild
    public static List<CategoryChild> lstCategoryChild(String lang) {
        return HibernateDAO.getList("from CategoryChild",lang);
    }

    //Get all CategoryChilds  in Category
    public static List<CategoryChild> lstCategoryChild(Category cat,String lang) {
        return HibernateDAO.getList("from CategoryChild where categoryId='" + cat.getCategoryId()+"'",lang);
    }
     
    //Get CategoryChild in ID
    public static CategoryChild getCategoryChild(String id,String lang) {
        return (CategoryChild)HibernateDAO.getObject(CategoryChild.class, id,lang);
    }

    //Insert CategoryChild
    public static boolean insertCategoryChild(CategoryChild c,String lang) {
        return HibernateDAO.insert(c,lang);
    }

    //Update CategoryChild
    public static boolean updateCategoryChild(CategoryChild cat,String lang) {
        return HibernateDAO.update(cat,lang);
    }

    //Delete CategoryChild
    public static boolean deleteCategoryChild(CategoryChild cat, String lang) {
        return HibernateDAO.delete(cat,lang);
    }
    //Get all CategoryChilds  in Categoryid
    public static List<CategoryChild> lstCategoryChild(String categoryId,String lang) {
        return HibernateDAO.getList("from CategoryChild where categoryId='" + categoryId+"'",lang);
    }
}
