/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import BUS.CategoryChildBUS;
import POJO.*;
/**
 *
 * @author admin
 */
public class CategorySubDAO extends HibernateDAO{
    
    //Get all CategorySub
    public static List<CategorySub> lstCategorySub(String lang) {
        return HibernateDAO.getList("from CategorySub",lang);
    }

    //Get all CategorySubs  in CategoryChild
    public static List<CategorySub> lstCategorySub(CategoryChild cc,String lang) {
        return HibernateDAO.getList("from CategorySub where categoryChildId='" + cc.getCategoryChildId()+"'",lang);
    }
    
  
    //Get CategorySub in ID
    public static CategorySub getCategorySub(String id,String lang) {
        return (CategorySub)HibernateDAO.getObject(CategorySub.class, id,lang);
    }

    //Insert CategorySub
    public static boolean insertCategorySub(CategorySub cb,String lang) {
        return HibernateDAO.insert(cb,lang);
    }

    //Update CategorySub
    public static boolean updateCategorySub(CategorySub cb,String lang) {
        return HibernateDAO.update(cb,lang);
    }

    //Delete CategorySub
    public static boolean deleteCategorySub(CategorySub cb, String lang) {
        return HibernateDAO.delete(cb,lang);
    }
    
    public static void main(String[] args) {
    	String lang="MALL_EN";
    	CategoryChild cc=CategoryChildBUS.getCategoryChild("1EN", lang);
	}
}
