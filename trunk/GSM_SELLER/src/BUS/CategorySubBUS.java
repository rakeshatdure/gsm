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
public class CategorySubBUS {

	/** Get all CategorySub **/
	public static List<CategorySub> lstCategorySub(String lang) {
		return CategorySubDAO.lstCategorySub(lang);
	}

	/** Get all CategorySubs  in Category **/ 
	public static List<CategorySub> lstCategorySub(CategoryChild cc,String lang) {
		return CategorySubDAO.lstCategorySub(cc, lang);
	}

	/** Get CategorySub in ID **/
	public static CategorySub getCategorySub(String id,String lang) {
		return CategorySubDAO.getCategorySub(id, lang);
	}

	/** Insert CategorySub **/
	public static boolean insertCategorySub(CategorySub cb,String lang) {
		return CategorySubDAO.insertCategorySub(cb, lang);
	}

	/** Update CategorySub **/
	public static boolean updateCategorySub(CategorySub cb,String lang) {
		return CategorySubDAO.updateCategorySub(cb, lang);
	}

	/** Delete CategorySub **/
	public static boolean deleteCategorySub(CategorySub cb,String lang) {
		return CategorySubDAO.deleteCategorySub(cb, lang);
	}
	// load all categorySub by user
	public static List<CategorySub> lstAllCategorySub(String lang,String categoryID,String categoryChilId,String account){
		List<CategorySub> listChild = new ArrayList<CategorySub>();
		MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
		try{
			String sql = "SELECT catsb.CategorySubId, catsb.CategorySubName " +
			" FROM category cat, categorychild catch, products prs,`user` us, categorysub catsb " +
			" WHERE us.Account = prs.Account and prs.CategorySubId = catsb.CategorySubId " +
			" and catsb.CategoryChildId = catch.CategoryChildId " +
			"  and catch.CategoryId = cat.CategoryID  " +
			"and us.Account = '" + account + "' and catch.CategoryId='" + categoryID + "' group by catsb.CategorySubName";
			helper.open(lang);

			ResultSet rs = helper.executeQuery(sql);
			while (rs.next()) {
				CategorySub cat = new CategorySub(rs.getString("CategorySubId"),rs.getString("CategorySubName"));
				listChild.add(cat);
			}
		}catch(Exception e){
			e.getMessage();
		}
		return listChild;
	}
}
