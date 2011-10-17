/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.*;
import POJO.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author admin
 */
public class ProductBUS {
	/** Get all products **/
    public static List<Products> lstProduct(String lang) {
        return ProductDAO.lstProduct(lang);
    }
    
    /** Get all products **/
    public static List<Products> lstProductPaid(String lang) {
        return ProductDAO.lstProductPaid(lang);
    }
    
    public static List<Products> SearchProductkey(String key, int ManuID, Double priMin, Double priMax, String lang) {
        return ProductDAO.SearchProductkey(key, ManuID, priMin, priMax, lang);
    }
    
    public static List<Products> SearchProductkey(String key, int ManuID, Double priMin, Double priMax,int pageNumber, int pageSize, String lang) {
        return ProductDAO.SearchProductkey(key, ManuID, priMin, priMax,pageNumber,pageSize, lang);
    }
    
    /** Advanced search products **/
    public static  List<Products> advancedSearchProduct(String seller, String child, String sub, String key, Date from, Date to, String lang){
    	return ProductDAO.advancedSearchProduct(seller, child, sub, key, from, to, lang);
    }
    /** Get all products **/
    public static List<Products> lstProduct(int pageNumber, int pageSize, String lang) {
        return ProductDAO.lstProduct(pageNumber,pageSize, lang);
    }
    
    /** Get all products had paid**/
    public static List<Products> lstProductPaid(int pageNumber, int pageSize, String lang) {
        return ProductDAO.lstProductPaid(pageNumber,pageSize, lang);
    }
    
    //Get news products
    public static List<Products> lstProductNew(String lang) {
        return ProductDAO.lstProductNew(lang);
    }

    /** Get list products in CategoryChildId **/
    public static List<Products> lstProductPaid(CategoryChild cc, String lang) {
        return ProductDAO.lstProductPaid(cc, lang);
    }
    
    /** Get list products in CategoryChildId **/
    public static List<Products> lstProduct(CategoryChild cc,int pageNumber, int pageSize, String lang) {
        return ProductDAO.lstProduct(cc,pageNumber,pageSize, lang);
    }
    /** Get list products in CategoryChildId had paid **/
    public static List<Products> lstProductPaid(CategoryChild cc,int pageNumber, int pageSize, String lang) {
        return ProductDAO.lstProductPaid(cc,pageNumber,pageSize, lang);
    }
    /** Get list products in CategorySub **/
    public static List<Products> lstProduct(CategorySub cb, String lang) {
        return ProductDAO.lstProduct(cb, lang);
    }
    /** Get list products in CategorySub had paid**/
    public static List<Products> lstProductPaid(CategorySub cb, String lang) {
        return ProductDAO.lstProductPaid(cb, lang);
    }
    
    /** Get list products in CategorySub **/
    public static List<Products> lstProduct(CategorySub cb,int pageNumber, int pageSize, String lang) {
        return ProductDAO.lstProduct(cb,pageNumber,pageSize, lang);
    }
    /** Get list products in CategorySub had paid**/
    public static List<Products> lstProductPaid(CategorySub cb,int pageNumber, int pageSize, String lang) {
        return ProductDAO.lstProductPaid(cb,pageNumber,pageSize, lang);
    }
    /** Get list products in user **/
    public static List<Products> lstProduct(User user, String lang) {
        return ProductDAO.lstProduct(user, lang);
    }
    
    /** Get list products in user **/
    public static List<Products> lstProduct(User user,int pageNumber, int pageSize, String lang) {
        return ProductDAO.lstProduct(user,pageNumber,pageSize, lang);
    }
    
     /** Get list products in  news promotions **/
    public static List<Products> lstProduct_PromotionNews(String lang) {
        return ProductDAO.lstProduct_PromotionNews(lang);
    }
    /** Get list products betwen products and promotions **/
    public static List<Products> lstProduct_Promotion(String lang) {
        return ProductDAO.lstProduct_Promotion(lang);
    }
    
    
    /** Get products in ID **/
    public static Products getProducts( int id, String lang) {
        return ProductDAO.getProducts(id, lang);
    }
    
    /**Get product price max **/
    public static double maxPrice(String lang) {
    	return ProductDAO.maxPrice(lang);
    }
    
    /**Get product price min **/
    public static double minPrice(String lang) {
    	return ProductDAO.minPrice(lang);
    }

    /** Insert products **/
    public static boolean insertProducts(Products p, String lang) {
        return ProductDAO.insertProducts(p, lang);
    }

    /** Update products **/
    public static boolean updateProducts(Products p, String lang) {
        return ProductDAO.updateProducts(p, lang);
    }

    /** Delete products **/
    public static boolean deleteProducts(Products p, String lang) {
        return ProductDAO.deleteProducts(p, lang);
    }
    /**get listProduct by category**/
    public static List<Products> getListProductByCategory(String categoryID,String lang){
    	return ProductDAO.getListProductByCategory(categoryID,lang);
    }
    
    /**get 6 products for each category **/
    public static List<Products> getListProductByCategoryMax(String categoryID,int pageNumber,int pageSize,String lang){
    	return ProductDAO.getListProductByCategoryMax(categoryID,pageNumber,pageSize,lang);
    }
    
    /**get listProduct by category paging**/
    public static Map getListProductByCategoryPaging(String categoryID,int pageNumber,int pageSize,String lang){
    	return ProductDAO.getListProductByCategoryPaging(categoryID,pageNumber,pageSize,lang);
    }
    
    public static void main(String[] args) {
		Products p=new Products();
		p.setProductName("test");
		p.setSize("d");
		p.setColor("d");
		p.setDetail("d");
		p.setUnit("df");
		p.setUser(UserBUS.getUser("sell01", "MALL_EN"));
		p.setUploadDate(new Date());
		insertProducts(p, "MALL_EN");
	}
}
