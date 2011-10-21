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
public class CategoryChildBUS {
    
    /** Get all CategoryChild **/
    public static List<CategoryChild> lstCategoryChild(String lang) {
        return CategoryChildDAO.lstCategoryChild(lang);
    }

    /** Get all CategoryChilds  in Category **/ 
    public static List<CategoryChild> lstCategoryChild(Category cat,String lang) {
        return CategoryChildDAO.lstCategoryChild(cat, lang);
    }
    
    /** Get all CategoryChilds  in Category **/ 
    public static List<CategoryChild> lstCategoryChild(String categoryID,String lang) {
        return CategoryChildDAO.lstCategoryChild(categoryID, lang);
    }
     
    /** Get CategoryChild in ID **/
    public static CategoryChild getCategoryChild(String id,String lang) {
        return CategoryChildDAO.getCategoryChild(id, lang);
    }

    /** Insert CategoryChild **/
    public static boolean insertCategoryChild(CategoryChild c,String lang) {
        return CategoryChildDAO.insertCategoryChild(c, lang);
    }

    /** Update CategoryChild **/
    public static boolean updateCategoryChild(CategoryChild cat,String lang) {
        return CategoryChildDAO.updateCategoryChild(cat, lang);
    }

    /** Delete CategoryChild **/
    public static boolean deleteCategoryChild(CategoryChild cat,String lang) {
        return CategoryChildDAO.deleteCategoryChild(cat, lang);
    }
    
    public static void main(String[] args) {
		System.out.println(getCategoryChild("1VI", "MALL_EN"));
	}
}
