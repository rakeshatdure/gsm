/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import BUS.CategoryBUS;
import BUS.CategoryChildBUS;
import POJO.*;
import UTIL.MySqlDataAccessHelper;

import java.util.ArrayList;


import org.hibernate.Query;
/**
 *
 * @author admin
 */

public class ProductDAO extends HibernateDAO{
    
    //Get all products
    public static List<Products> lstProduct(String lang){
        return HibernateDAO.getList("from Products", lang);
    }
  //Get all products had paid
    public static List<Products> lstProductPaid(String lang){
    	SimpleDateFormat simp=new SimpleDateFormat("yyyy-MM-dd");
    	String currentDate=simp.format(new Date());
        return HibernateDAO.getList("select pro from Inventory inv,Products pro where pro.productId=inv.productId and inv.limitDate > '"+currentDate+"'", lang);
    }
    
   //Searchproduct with name
    public static  List<Products> SearchProductkey(String key,int ManuId,double prMin,double prMax, String lang){
        return HibernateDAO.getList("from Products where  productName like '%"+key+"%' or price between "+prMin+" and "+prMax+" or manufacturerId="+ManuId, lang);
    }
    
     //Get all products
    public static List<Products> lstProduct(int pageNumber, int pageSize, String lang){
        return HibernateDAO.getList("from Products",pageNumber,pageSize, lang);
    }
    //Get all products had paid
    public static List<Products> lstProductPaid(int pageNumber, int pageSize, String lang){
    	SimpleDateFormat simp=new SimpleDateFormat("yyyy-MM-dd");
    	String currentDate=simp.format(new Date());
        return HibernateDAO.getList("select pro from Inventory inv,Products pro where pro.productId=inv.productId and inv.limitDate > '"+currentDate+"'",pageNumber,pageSize, lang);
    }
    //Get news products
    public static List<Products> lstProductNew(String lang){
    	SimpleDateFormat simp=new SimpleDateFormat("yyyy-MM-dd");
    	String currentDate=simp.format(new Date());
        return HibernateDAO.getList("select p from Products p,Inventory inv where p.productId=inv.productId and inv.limitDate > '"+currentDate+"' order by p.uploadDate desc",0,12, lang);
    }

   
    //Get list products in CategoryChild
    public static List<Products> lstProduct(CategoryChild cc, String lang){
        return HibernateDAO.getList("from Products where categoryChildId='" + cc.getCategoryChildId()+"'", lang);
    }
    //Get list products in CategoryChild had paid
    public static List<Products> lstProductPaid(CategoryChild cc, String lang){
    	SimpleDateFormat simp=new SimpleDateFormat("yyyy-MM-dd");
    	String currentDate=simp.format(new Date());
        return HibernateDAO.getList("select pro from Inventory inv,Products pro where pro.productId=inv.productId and inv.limitDate > '"+currentDate+"' " +
        		" and pro.categoryChildId='" + cc.getCategoryChildId()+"'", lang);
    }
    
    //Get list products in CategoryChild
    public static List<Products> lstProduct(CategoryChild cc,int pageNumber, int pageSize, String lang){
        return HibernateDAO.getList("from Products where categoryChildId='" + cc.getCategoryChildId()+"'",pageNumber,pageSize, lang);
    }
    
  //Get list products in CategoryChild had payment
    public static List<Products> lstProductPaid(CategoryChild cc,int pageNumber, int pageSize, String lang){
    	SimpleDateFormat simp=new SimpleDateFormat("yyyy-MM-dd");
    	String currentDate=simp.format(new Date());
        return HibernateDAO.getList("select pro from Inventory inv,Products pro where pro.productId=inv.productId and inv.limitDate > '"+currentDate+"' " +
        		" and pro.categoryChildId='" + cc.getCategoryChildId()+"'",pageNumber,pageSize, lang);
    }
    
    //Get list products in CategorySub
    public static List<Products> lstProduct(CategorySub cb, String lang){
        return HibernateDAO.getList("from Products where categorySubId='" + cb.getCategorySubId()+"'", lang);
    }
    
    //Get list products in CategorySub had paid
    public static List<Products> lstProductPaid(CategorySub cb, String lang){
    	SimpleDateFormat simp=new SimpleDateFormat("yyyy-MM-dd");
    	String currentDate=simp.format(new Date());
        return HibernateDAO.getList("select pro from Inventory inv,Products pro where pro.productId=inv.productId and inv.limitDate > '"+currentDate+"'" +
        		" and pro.categorySubId='" + cb.getCategorySubId()+"'", lang);
    }
    //Get list products in CategorySub
    public static List<Products> lstProduct(CategorySub cb,int pageNumber, int pageSize, String lang){
        return HibernateDAO.getList("from Products where categorySubId='" + cb.getCategorySubId()+"'",pageNumber,pageSize, lang);
    }
    //Get list products in CategorySub had paid
    public static List<Products> lstProductPaid(CategorySub cb,int pageNumber, int pageSize, String lang){
    	SimpleDateFormat simp=new SimpleDateFormat("yyyy-MM-dd");
    	String currentDate=simp.format(new Date());
        return HibernateDAO.getList("select pro from Inventory inv,Products pro where pro.productId=inv.productId and inv.limitDate > '"+currentDate+"'" +
        		" and pro.categorySubId='" + cb.getCategorySubId()+"'",pageNumber,pageSize, lang);
    }
    
    //Get list products in user
    public static List<Products> lstProduct(User user, String lang){    	
        return HibernateDAO.getList("from Products where user.account='" + user.getAccount()+"'", lang);
    }
    
    //Get list products in user
    public static List<Products> lstProduct(User user,int pageNumber, int pageSize, String lang){
        return HibernateDAO.getList("from Products where user.account='" + user.getAccount()+"'",pageNumber,pageSize, lang);
    }
    
    //Get list products in  news promotions
    public static List<Products> lstProduct_PromotionNews(String lang){
        ArrayList<Products> lstPr = new ArrayList<Products>();
        List<Promotions> lst = HibernateDAO.getList("from Promotions order by promotionId desc",0,10, lang);
        for(int i=0;i<lst.size();i++){
            Promotions pro = (Promotions)lst.get(i); 
            Products p = ProductDAO.getProducts(pro.getProductId(), lang);
            if(p != null){
                lstPr.add(p);
            }
        }
        return lstPr;
    }
    
     //Get list products in promotions
    public static List<Products> lstProduct_Promotion(String lang){
        ArrayList<Products> lstPr = new ArrayList<Products>();
        List<Products> lst = ProductDAO.lstProduct(lang);
        for(int i=0;i<lst.size();i++){
            Products p = (Products)lst.get(i);
            if(PromotionDAO.getPromotion(p, lang) != null){
                lstPr.add(p);
            }
           
        }
        return lstPr;
    }
    
    //Get product price max
    public static double maxPrice(String lang){
        List kq = HibernateDAO.getList("from Products order by price desc", lang);
        Products p = (Products)kq.get(0);
        
        return p.getPrice();
     

    }
    //Get product price min
    public static double minPrice(String lang){
        List kq = HibernateDAO.getList("from Products order by price asc", lang);
        Products p = (Products)kq.get(0);
        
        return p.getPrice();
     

    }

    //Searchproduct with name
    public static  List<Products> SearchProductkey(String key,int ManuId, Double min, Double max, String lang){
       
        String sql="from Products where  productName like '%"+key+"%'";
        if(ManuId!=0){
            sql+=" and manufacturerId="+ManuId;
        }if(min!=0.0 || max!=0.0){
            sql+=" and (price between "+min+" and "+max+")";
        }
        return HibernateDAO.getList(sql, lang);
                
    }
   //Searchproduct with name
    public static  List<Products> SearchProductkey(String key,int ManuId, Double min, Double max,int pageNumber, int pageSize, String lang){
       
        String sql="from Products where  productName like '%"+key+"%'";
        if(ManuId!=0){
            sql+=" and manufacturerId="+ManuId;
        }if(min!=0.0 || max!=0.0){
            sql+=" and (price between "+min+" and "+max+")";
        }
        Query q = (Query)HibernateDAO.getList(sql, lang);
        q.setFirstResult(pageNumber); 
        q.setMaxResults(pageSize);  
        
        return q.list();
    }
   
    //Advanced search products
    public static  List<Products> advancedSearchProduct(String seller, String child, String sub, String key, Date from, Date to, String lang){
       
        String sql="from Products where user.account='"+seller+"'";
        if(null != key && !key.isEmpty()){
        	sql+=" and productName like '%"+key+"%' or productId='"+key+"'";
        }
        if(sub.equals("0")){
            sql+=" and categoryChildId='"+child+"'";
        }else{
            sql+=" and categorySubId='"+sub+"'";
        }
        if(from != null && to != null){
            sql+=" and uploadDate >= '"+from+"' and uploadDate <= '"+to+"'";
        }
        return HibernateDAO.getList(sql, lang);
                
    }
    
    //Get products in ID
    public static Products getProducts( int id, String lang){
        return (Products)HibernateDAO.getObject(Products.class, id, lang);
    }

    //Insert products
    public static boolean insertProducts(Products p, String lang){
        return HibernateDAO.insert(p, lang);
    }

    //Update products
    public static boolean updateProducts(Products p, String lang){
        return HibernateDAO.update(p, lang);
    }

    //Delete products
    public static boolean deleteProducts(Products p, String lang){
        return HibernateDAO.delete(p, lang);
    }
    
    public static boolean updateProductAmount(Products product, String lang){
        boolean result=false;
         MySqlDataAccessHelper heper=new MySqlDataAccessHelper();
        try{
           // String sql="update bank set AccountNumber='"+bank.getAccountNumber()+"',Banking='"+bank.getBanking()+",'AccountHolder='"+bank.getAccountholders()+"' where BankId="+bank.getBankId();
            String sql="update products set Amount="+product.getAmount()+",Price="+product.getPrice()+"where ProductID="+product.getProductId();
            heper.open(lang);
            int n=heper.executeUpdate(sql);
            if(n==1){
                result=true;
            }
            heper.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    
   
    public static  List<Products> SearchProductAmount(String categoryId,String categorychildId,String categorysubId,String lang){
        return HibernateDAO.getList("from Products where categorySubId='"+categorysubId+"' or categoryChildId='"+categorychildId+"' or categoryId='"+categoryId, lang);
    }
    public static ArrayList<Products> lstProduct1(String username,String categorychild,String categorysub,String lang){
        ArrayList<Products> lstProduct= new ArrayList<Products>();
        MySqlDataAccessHelper helper=new MySqlDataAccessHelper();
        try{
            String sql="select products.ProductID,products.Amount,products.ProductName,products.Price "
                    + "from categorychild,categorysub,products "
                    + "where products.Account='"+username+"'"
                    + "and  (categorychild.CategoryChildId=products.CategoryChildId and  categorychild.CategoryChildId=categorysub.CategoryChildId and categorysub.CategorySubId=products.CategorySubId) "
                    + "and (categorychild.CategoryChildId='"+categorychild+"' or  categorysub.CategorySubId='"+categorysub+"')"
                    + "Group by products.ProductID";
            helper.open(lang);
            ResultSet rs=helper.executeQuery(sql);
            while(rs.next()){
                Products product=new Products();
                
                product.setProductId(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setPrice(rs.getFloat("Price"));
                product.setAmount(rs.getInt("Amount"));
               lstProduct.add(product);
             
            }
        }catch(Exception ex){
            ex.getMessage();
        }
                 
        return  lstProduct;
    }
    
    public static ArrayList<Products> lstProduct2(String username,String categorychild,String lang){
        ArrayList<Products> lstProduct= new ArrayList<Products>();
        MySqlDataAccessHelper helper=new MySqlDataAccessHelper();
        try{
            String sql="select products.ProductID,products.Amount,products.ProductName,products.Price "
                    + "from categorychild,categorysub,products "
                    + "where products.Account='"+username+"'"
                    + "and  categorychild.CategoryChildId=products.CategoryChildId "
                    + "and categorychild.CategoryChildId='"+categorychild+"' "
                    + "Group by products.ProductID";
            helper.open(lang);
            ResultSet rs=helper.executeQuery(sql);
            while(rs.next()){
                Products product=new Products();
                
                product.setProductId(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setPrice(rs.getFloat("Price"));
                product.setAmount(rs.getInt("Amount"));
               lstProduct.add(product);
             
            }
        }catch(Exception ex){
            ex.getMessage();
        }
                 
        return  lstProduct;
    }
    public static List<Products> lstInvenProduct(User user, String lang){
        return HibernateDAO.getList("from Products where user.account='" + user.getAccount()+"'", lang);
    }
    
	public static List<Products> getListProductByCategory(String categoryID,
			String lang) {
		SimpleDateFormat simp=new SimpleDateFormat("yyyy-MM-dd");
    	String currentDate=simp.format(new Date());
    	
		List<CategoryChild> listCategoryChilds=CategoryChildBUS.lstCategoryChild(categoryID, lang);
		
		List<Products> list=HibernateDAO.getList("select p from Products p,CategoryChild cac,Inventory inv where cac.categoryChildId=p.categoryChildId" +
				" and cac.categoryId='"+categoryID+"' and p.productId=inv.productId and inv.limitDate > '"+currentDate+"'", lang);
		
		return list;
	}
	
	public static List<Products> getListProductByCategoryMax(String categoryID,int firstResult,int maxResult,
			String lang) {
		SimpleDateFormat simp=new SimpleDateFormat("yyyy-MM-dd");
    	String currentDate=simp.format(new Date());
    	
		List<Products> list=HibernateDAO.getList("select p from Products p,CategoryChild cac,Inventory inv where cac.categoryChildId=p.categoryChildId" +
				" and cac.categoryId='"+categoryID+"' and p.productId=inv.productId and inv.limitDate > '"+currentDate+"'",firstResult,maxResult, lang);
		
		return list;
	}
	
	public static Map getListProductByCategoryPaging(String categoryID,int firstResult,int maxResult,
			String lang) {
		SimpleDateFormat simp=new SimpleDateFormat("yyyy-MM-dd");
    	String currentDate=simp.format(new Date());
    	
		return HibernateDAO.getListMap("select p from Products p,CategoryChild cac,Inventory inv where cac.categoryChildId=p.categoryChildId" +
				" and cac.categoryId='"+categoryID+"' and p.productId=inv.productId and inv.limitDate > '"+currentDate+"'",firstResult,maxResult, lang);
		
	}
	
	public static void main(String[] args) {
		
	}
    
}
