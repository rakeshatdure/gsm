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
	// load all category by user
	public static List<String> lstAllCategory(String lang,String account){
		List<String> list = new ArrayList<String>();
		MySqlDataAccessHelper helper = new MySqlDataAccessHelper();

		try {
			String sql = "select cat.CategoryName FROM category cat, categorychild catch, products prs, `user` us" +
			" WHERE us.Account = prs.Account and prs.CategoryChildId = catch.CategoryChildId" +
			" and catch.CategoryId = cat.CategoryID" +
			" and us.Account ='" + account + "'";
			helper.open(lang);

			ResultSet rs = helper.executeQuery(sql);
			while (rs.next()) {
				String s = new String(rs.getString("Banking"));
				list.add(s);
			}
		} catch (Exception ex) {
			ex.getMessage();
		}
		return list;
	}

}
