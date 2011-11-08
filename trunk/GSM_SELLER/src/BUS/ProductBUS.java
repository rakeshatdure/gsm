/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.*;
import POJO.*;
import POJO.View.ProductsView;
import UTIL.MySqlDataAccessHelper;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    
    // update product by user
    public static boolean updateProductByUser(float price,int amount,String lang,String account,int productID,String limitDate){
		boolean result = false;
		MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
		try {
			helper.open(lang);
			String sql = "update  products, inventory set products.Price = "+ price +" " +
					" , products.Amount="+amount+",inventory.LimitDate ='"+limitDate+"' where products.ProductID = "+ productID +" " +
							" and  products.Account='"+account+"' and products.ProductID = inventory.InventoryId";
			int rs = helper.executeUpdate(sql);
			System.out.println("----" + rs);
			if(rs > 0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
    // load all product inventory
    public static List<ProductInvetoryView> lstProductInvetory(String account,String lang){
    	List<ProductInvetoryView> lst = new ArrayList<ProductInvetoryView>();
    	MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
    	SimpleDateFormat simp=new SimpleDateFormat("yyyy-MM-dd");
    	String currentDate=simp.format(new Date());
    	try {
			helper.open(lang);
			String query = "SELECT prs.ProductID as productCode , prs.ProductName as productName," +
					"(SELECT man.ManufacturerName from  manufacturer man WHERE prs.ManufacturerId = man.ManufacturerId) as producer," +
					"(SELECT inv.InventoryId from  inventory inv WHERE inv.ProductId = prs.ProductID) as invetoryCode," +
					"(SELECT inst.StateName from invenstate inst  WHERE inst.StateId = iven.SateId and iven.ProductId = prs.ProductID ) as stateIventory," +
					" prs.Amount as amount,iven.LimitDate as expired " +
					" from products prs, inventory iven " +
					" WHERE prs.ProductID = iven.ProductId and prs.Account='"+ account+"' and iven.LimitDate < '" + currentDate + "'" ;
			
			ResultSet rs = helper.executeQuery(query);
			while (rs.next()) {
				ProductInvetoryView prsview = new ProductInvetoryView(rs.getInt("productCode"), rs.getString("productName"), 
						rs.getString("producer"), rs.getInt("invetoryCode"), rs.getString("stateIventory"), rs.getInt("amount"),rs.getDate("expired"));
				lst.add(prsview);
			}
		} catch (Exception e) {
			e.getMessage();
		}
    	return lst;
    }
    // load all product infor by user
    public static List<ProductsView> getAllProductByUser(String account,String lang){
    	List<ProductsView> lstProduct = new ArrayList<ProductsView>();
    	
    	MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
    	try {
			String query = "SELECT prs.ProductID as productId ,prs.ProductName as productName, " +
					" prs.Price as price,prs.Amount as amount , " +
					"(SELECT SUM((prs.Price * prs.Amount)+ dc.Cost + dc.FeeExtra) FROM delivercost dc" +
					"	 WHERE prs.DeliverCost=dc.DeliverCostID and prs.Account='"+account+"' ) as totalPrice," +
					"(SELECT cat.CategoryName  from category cat, categorychild catch" +
					" 	WHERE prs.CategoryChildId = catch.CategoryChildId and catch.CategoryId = cat.CategoryID) as categoryName," +
					"(SELECT catch.CategoryChildName  from categorychild catch WHERE prs.CategoryChildId = catch.CategoryChildId ) as categoryChild," +
					"(SELECT catsub.CategorySubName  from categorysub catsub" +
					" 	WHERE prs.CategorySubId= catsub.CategorySubId) as categorySub,inv.InventoryId as invetoryID," +
					" inv.LimitDate as limitDate, st.StateName as stateName " +
					" from products prs, inventory inv, `user` , invenstate st " +
					" WHERE prs.ProductID = inv.ProductId and`user`.Account='"+ account+"'  and inv.SateId = st.StateId ";
			
			helper.open(lang);

			ResultSet rs = helper.executeQuery(query);
			while (rs.next()) {
				ProductsView prView = new ProductsView(rs.getInt("productId"),rs.getString("productName"),rs.getFloat("price"),rs.getFloat("totalPrice"),rs.getInt("amount"),
						rs.getDate("limitDate"),rs.getString("categoryName"),rs.getString("categoryChild"),rs.getString("categorySub"),rs.getInt("invetoryID"),rs.getString("stateName"));
				lstProduct.add(prView);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return lstProduct;
    }
    // load all product selling
    public static List<ProductsView> getAllProductSellingByUser(String account,String lang){
    	List<ProductsView> lstProduct = new ArrayList<ProductsView>();
    	MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
    	try {
			String query = "SELECT prs.ProductID as productId ,prs.ProductName as productName, " +
					" prs.Price as price,prs.Amount as amount , " +
					" (SELECT SUM((prs.Price * prs.Amount)+ dc.Cost + dc.FeeExtra) FROM delivercost dc" +
					"	 WHERE prs.DeliverCost=dc.DeliverCostID and prs.Account='"+account+"' ) as totalPrice," +
					" (SELECT cat.CategoryName  from category cat, categorychild catch" +
					" 	WHERE prs.CategoryChildId = catch.CategoryChildId and catch.CategoryId = cat.CategoryID) as categoryName," +
					" (SELECT catch.CategoryChildName  from categorychild catch WHERE prs.CategoryChildId = catch.CategoryChildId ) as categoryChild," +
					" (SELECT catsub.CategorySubName  from categorysub catsub" +
					" 	WHERE prs.CategorySubId= catsub.CategorySubId) as categorySub,inv.InventoryId as invetoryID," +
					" inv.LimitDate as limitDate, st.StateName as stateName " +
					" from products prs, inventory inv,invenstate st " +
					" WHERE prs.ProductID = inv.ProductId and prs.Account='"+ account+"'  and inv.SateId = st.StateId AND st.StateName='OutputSto' ";
			
			helper.open(lang);

			ResultSet rs = helper.executeQuery(query);
			while (rs.next()) {
				ProductsView prView = new ProductsView(rs.getInt("productId"),rs.getString("productName"),rs.getFloat("price"),rs.getFloat("totalPrice"),rs.getInt("amount"),
						rs.getDate("limitDate"),rs.getString("categoryName"),rs.getString("categoryChild"),rs.getString("categorySub"),rs.getInt("invetoryID"),rs.getString("stateName"));
				lstProduct.add(prView);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return lstProduct;
    }
    
 // load all product infor by categoryName
    public static List<ProductsView> getAllProductByCategory(String account,String lang,String where){
    	List<ProductsView> lstProduct = new ArrayList<ProductsView>();
    	MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
    	try {
			String query = "SELECT prs.ProductID as productId ,prs.ProductName as productName, " +
					" prs.Price as price,prs.Amount as amount , " +
					"(SELECT SUM((prs.Price * prs.Amount)+ dc.Cost + dc.FeeExtra) FROM delivercost dc" +
					"	 WHERE prs.DeliverCost=dc.DeliverCostID and prs.Account='"+account+"' ) as totalPrice," +
					"(SELECT cat.CategoryName  from category cat, categorychild catch" +
					" 	WHERE prs.CategoryChildId = catch.CategoryChildId and catch.CategoryId = cat.CategoryID) as categoryName," +
					"(SELECT catch.CategoryChildName  from categorychild catch WHERE prs.CategoryChildId = catch.CategoryChildId ) as categoryChild," +
					"(SELECT catsub.CategorySubName  from categorysub catsub" +
					" 	WHERE prs.CategorySubId= catsub.CategorySubId) as categorySub,inv.InventoryId as invetoryID," +
					" inv.LimitDate as limitDate, st.StateName as stateName " +
					" from products prs, inventory inv, `user` , invenstate st " +
					" WHERE prs.ProductID = inv.ProductId and`user`.Account='"+ account+"'  and inv.SateId = st.StateId " ;
			
			helper.open(lang);

			ResultSet rs = helper.executeQuery(query+ where);
			while (rs.next()) {
				ProductsView prView = new ProductsView(rs.getInt("productId"),rs.getString("productName"),rs.getFloat("price"),rs.getFloat("totalPrice"),rs.getInt("amount"),
						rs.getDate("limitDate"),rs.getString("categoryName"),rs.getString("categoryChild"),rs.getString("categorySub"),rs.getInt("invetoryID"),rs.getString("stateName"));
				lstProduct.add(prView);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return lstProduct;
    }
}
