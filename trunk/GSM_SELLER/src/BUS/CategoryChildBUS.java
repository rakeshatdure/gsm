/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.*;
import POJO.*;
import UTIL.MySqlDataAccessHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
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
    
 // load all categoryChild by category and user
	public static List<CategoryChild> lstAllCategoryChild(String lang,String categoryID,String account){
		List<CategoryChild> listChild = new ArrayList<CategoryChild>();
		MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
		try{
			String sql = "SELECT catch.CategoryChildName,catch.CategoryChildId " +
					" FROM category cat, categorychild catch, products prs,`user` us" +
					" WHERE us.Account = prs.Account and prs.CategoryChildId = catch.CategoryChildId" +
					" and catch.CategoryId = cat.CategoryID " +
					"and us.Account = '" + account + "' and catch.CategoryId='" + categoryID + "' group by catch.CategoryChildName";
			helper.open(lang);

			ResultSet rs = helper.executeQuery(sql);
			while (rs.next()) {
				CategoryChild cat = new CategoryChild(rs.getString("CategoryChildId"),rs.getString("CategoryChildName"));
				listChild.add(cat);
			}
		}catch(Exception e){
			e.getMessage();
		}
		return listChild;
	}
}
