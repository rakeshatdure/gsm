/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import BUS.DeliverCostBUS;
import BUS.ProductBUS;
import BUS.ProductorderBUS;
import BUS.ProductorderdetailBUS;
import BUS.UserBUS;
import POJO.*;
import POJO.View.ManageCancelView;
import UTIL.DateUtil;
import UTIL.MySqlDataAccessHelper;
/**
 *
 * @author admin
 */
public class ProductorderdetailDAO extends HibernateDAO{
	
	    
    //Get all Productorderdetail
    public static List<Productorderdetail> lstProductorderdetail(String lang) {
        return HibernateDAO.getList("from Productorderdetail", lang);
    }

     
    //Get Productorderdetail in ID
    public static Productorderdetail getProductorderdetail( int id, String lang ) {
        return (Productorderdetail)HibernateDAO.getObject(Productorderdetail.class, id, lang);
    }

    //Insert Productorderdetail
    public static boolean insertProductorderdetail(Productorderdetail p, String lang) {
        return HibernateDAO.insert(p, lang);
    }

    //Update Productorderdetail
    public static boolean updateProductorderdetail(Productorderdetail p, String lang) {
        return HibernateDAO.update(p, lang);
    }

    //Delete Productorderdetail
    public static boolean deleteProductorderdetail(Productorderdetail p, String lang) {
        return HibernateDAO.delete(p, lang);
    }
    
    //Get list Orderdetail in  Order
     public static List<Productorderdetail> lstOrderdetailBy(Productorder pd, String lang) {
         return HibernateDAO.getList("from Productorderdetail where productorder.productOrderId="+pd.getProductOrderId(), lang);
     }
    
   //Get list Productorderdetail in  user
    public static List<Productorderdetail> lstProductorderdetail(String user, String lang) {
        return HibernateDAO.getList("from Productorderdetail where productorder.user.account = '"+user+"'", lang);
    }
    
    public static ArrayList<Productorderdetail> listProductSeller(String fromDate, String toDate, String AcountSeller, String lang) {
        ArrayList<Productorderdetail> lstProductSeller = new ArrayList<Productorderdetail>();
        MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
        try {
        	
        	
            String sql = "select "
                    + " pr.ProductID,pr.ProductName,pr.Price,prOrder.OrderDate,"
                    + " Sum(proDetail.ProductNumber) as Amount "
                    + " from products pr ,productorderdetail proDetail,productorder  prOrder "
                    + " where prOrder.Acount='" + AcountSeller + "' "
                    + " and proDetail.ProductId=pr.ProductID  "
                    + " and proDetail.ProductOrderId=prOrder.ProductOrderID"
                    + " and( prOrder.StateOrderID=4 or prOrder.StateOrderID=2) "
                    + " and (prOrder.OrderDate between '" + fromDate + "' and '" + toDate + "' ) "
                    + " group by  proDetail.ProductID ";
            
            helper.open(lang);
            
            ResultSet rs = helper.executeQuery(sql);
            while (rs.next()) {
                Productorderdetail pDetail = new Productorderdetail();

                Products product = new Products();
                Productorder prOder = new Productorder();

                pDetail.setProductorder(prOder);
                pDetail.setProducts(product);
                pDetail.setProductNumber(rs.getInt("Amount"));

                product.setProductId(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setPrice(rs.getFloat("Price"));

                prOder.setOrderDate(rs.getDate("OrderDate"));

                lstProductSeller.add(pDetail);
            }

        } catch (Exception ex) {
            ex.getMessage();
        }
        return lstProductSeller;
    }
    
    public static ArrayList<Productorderdetail> listProductSeller(String fromDate, String toDate, String AcountSeller,int pageNumber, int pageSize, String lang) {
        ArrayList<Productorderdetail> lstProductSeller = new ArrayList<Productorderdetail>();
        MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
        try {
        	
        	
            String sql = String.format("select "
                    + " pr.ProductID,pr.ProductName,pr.Price,prOrder.OrderDate,"
                    + " Sum(proDetail.ProductNumber) as Amount "
                    + " from products pr ,productorderdetail proDetail,productorder  prOrder "
                    + " where prOrder.Acount='" + AcountSeller + "' "
                    + " and proDetail.ProductId=pr.ProductID  "
                    + " and proDetail.ProductOrderId=prOrder.ProductOrderID"
                    + " and( prOrder.StateOrderID=4 or prOrder.StateOrderID=2) "
                    + " and (prOrder.OrderDate between '" + fromDate + "' and '" + toDate + "' ) "
                    + " group by  proDetail.ProductID LIMIT %d,%d",(pageNumber)*pageSize,pageSize);
            
            helper.open(lang);
            
            ResultSet rs = helper.executeQuery(sql);
            while (rs.next()) {
                Productorderdetail pDetail = new Productorderdetail();

                Products product = new Products();
                Productorder prOder = new Productorder();

                pDetail.setProductorder(prOder);
                pDetail.setProducts(product);
                pDetail.setProductNumber(rs.getInt("Amount"));

                product.setProductId(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setPrice(rs.getFloat("Price"));

                prOder.setOrderDate(rs.getDate("OrderDate"));

                lstProductSeller.add(pDetail);
            }

        } catch (Exception ex) {
            ex.getMessage();
        }
        return lstProductSeller;
    }
    
     public static ArrayList<Productorderdetail> listProductAcount( String AcountSeller, String lang) {
        ArrayList<Productorderdetail> lstProductSeller = new ArrayList<Productorderdetail>();
        MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
        try {
            String sql = "select "
                    + " pr.ProductID,pr.ProductName,pr.Price,prOrder.OrderDate,"
                    + " Sum(proDetail.ProductNumber) as Amount "
                    + " from products pr ,productorderdetail proDetail,productorder  prOrder "
                    + " where prOrder.Acount='" + AcountSeller + "' "
                    + " and proDetail.ProductId=pr.ProductID  "
                    + " and proDetail.ProductOrderId=prOrder.ProductOrderID"
                    + " and( prOrder.StateOrderID=4 or prOrder.StateOrderID=2) "
                    + " group by  proDetail.ProductID ";
            helper.open(lang);
            ResultSet rs = helper.executeQuery(sql);
            while (rs.next()) {
                Productorderdetail pDetail = new Productorderdetail();
                Products product = new Products();
                Productorder prOder = new Productorder();
                pDetail.setProductorder(prOder);
                pDetail.setProducts(product);
                pDetail.setProductNumber(rs.getInt("Amount"));
                product.setProductId(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setPrice(rs.getFloat("Price"));
                prOder.setOrderDate(rs.getDate("OrderDate"));
                lstProductSeller.add(pDetail);
            }

        } catch (Exception ex) {
            ex.getMessage();
        }
        return lstProductSeller;
    }
     
     public static ArrayList<Productorderdetail> listProductAcount( String AcountSeller, int pageNumber, int pageSize, String lang) {
         ArrayList<Productorderdetail> lstProductSeller = new ArrayList<Productorderdetail>();
         MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
         try {
             String sql = String.format("select "
                     + " pr.ProductID,pr.ProductName,pr.Price,prOrder.OrderDate,"
                     + " Sum(proDetail.ProductNumber) as Amount "
                     + " from products pr ,productorderdetail proDetail,productorder  prOrder "
                     + " where prOrder.Acount='" + AcountSeller + "' "
                     + " and proDetail.ProductId=pr.ProductID  "
                     + " and proDetail.ProductOrderId=prOrder.ProductOrderID"
                     + " and( prOrder.StateOrderID=4 or prOrder.StateOrderID=2) "
                     + " group by  proDetail.ProductID LIMIT %d,%d",(pageNumber)*pageSize,pageSize);
             helper.open(lang);
             ResultSet rs = helper.executeQuery(sql);
             while (rs.next()) {
                 Productorderdetail pDetail = new Productorderdetail();
                 Products product = new Products();
                 Productorder prOder = new Productorder();
                 pDetail.setProductorder(prOder);
                 pDetail.setProducts(product);
                 pDetail.setProductNumber(rs.getInt("Amount"));
                 product.setProductId(rs.getInt("ProductID"));
                 product.setProductName(rs.getString("ProductName"));
                 product.setPrice(rs.getFloat("Price"));
                 prOder.setOrderDate(rs.getDate("OrderDate"));
                 lstProductSeller.add(pDetail);
             }

         } catch (Exception ex) {
             ex.getMessage();
         }
         return lstProductSeller;
     }
     
     
     public static ArrayList<Productorderdetail> listProductStatisticsAdmin(String lang) {
         ArrayList<Productorderdetail> lstProductSeller = new ArrayList<Productorderdetail>();
         MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
         try {
             String sql = "select "
                     + " pr.ProductID,pr.ProductName,pr.Price,prOrder.OrderDate,pr.Account,"
                     + " Sum(proDetail.ProductNumber) as Amount "
                     + " from products pr ,productorderdetail proDetail,productorder  prOrder "
                     + " where  proDetail.ProductId=pr.ProductID  "
                     + " and proDetail.ProductOrderId=prOrder.ProductOrderID"
                     + " and( prOrder.StateOrderID=4 or prOrder.StateOrderID=2) "
                     + " group by  proDetail.ProductID ";
             helper.open(lang);
             ResultSet rs = helper.executeQuery(sql);
             while (rs.next()) {
                 Productorderdetail pDetail = new Productorderdetail();
                 Products product = new Products();
                 Productorder prOder = new Productorder();
                 User user = new User();
                 product.setUser(user);
                 
                 pDetail.setProductorder(prOder);
                 pDetail.setProducts(product);
                 pDetail.setProductNumber(rs.getInt("Amount"));
                 product.setProductId(rs.getInt("ProductID"));
                 product.setProductName(rs.getString("ProductName"));
                 product.setPrice(rs.getFloat("Price"));
                 user.setAccount(rs.getString("Account"));
                 prOder.setOrderDate(rs.getDate("OrderDate"));
                 
                 lstProductSeller.add(pDetail);
             }
             
         } catch (Exception ex) {
             ex.getMessage();
         }
         return lstProductSeller;
     }
     
     public static ArrayList<Productorderdetail> listProductStatisticsAdmin(int pageNumber, int pageSize, String lang) {
         ArrayList<Productorderdetail> lstProductSeller = new ArrayList<Productorderdetail>();
         MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
         try {
             String sql = String.format("select "
                     + " pr.ProductID,pr.ProductName,pr.Price,prOrder.OrderDate,pr.Account,"
                     + " Sum(proDetail.ProductNumber) as Amount "
                     + " from products pr ,productorderdetail proDetail,productorder  prOrder "
                     + " where  proDetail.ProductId=pr.ProductID  "
                     + " and proDetail.ProductOrderId=prOrder.ProductOrderID"
                     + " and( prOrder.StateOrderID=4 or prOrder.StateOrderID=2) "
                     + " group by  proDetail.ProductID LIMIT %d,%d",(pageNumber)*pageSize,pageSize);
             
             helper.open(lang);
             ResultSet rs = helper.executeQuery(sql);
             while (rs.next()) {
                 Productorderdetail pDetail = new Productorderdetail();
                 Products product = new Products();
                 Productorder prOder = new Productorder();
                 User user = new User();
                 product.setUser(user);
                 
                 pDetail.setProductorder(prOder);
                 pDetail.setProducts(product);
                 pDetail.setProductNumber(rs.getInt("Amount"));
                 product.setProductId(rs.getInt("ProductID"));
                 product.setProductName(rs.getString("ProductName"));
                 product.setPrice(rs.getFloat("Price"));
                 user.setAccount(rs.getString("Account"));
                 prOder.setOrderDate(rs.getDate("OrderDate"));
                 
                 lstProductSeller.add(pDetail);
             }
             
         } catch (Exception ex) {
             ex.getMessage();
         }
         return lstProductSeller;
     }
     
     
     public static ArrayList<Productorderdetail> listStisticsAdmin(String fromDate, String toDate,int pageNumber, int pageSize, String lang) {
         ArrayList<Productorderdetail> lstProductSeller = new ArrayList<Productorderdetail>();
         MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
         try {


             String sql = String.format("select "
                     + " pr.ProductID,pr.ProductName,pr.Price,prOrder.OrderDate,pr.Account,"
                     + " Sum(proDetail.ProductNumber) as Amount "
                     + " from products pr ,productorderdetail proDetail,productorder  prOrder "
                     + " where proDetail.ProductId=pr.ProductID  "
                     + " and proDetail.ProductOrderId=prOrder.ProductOrderID"
                     + " and( prOrder.StateOrderID=4 or prOrder.StateOrderID=2) "
                     + " and (prOrder.OrderDate between '" + fromDate + "' and '" + toDate + "' ) "
                     + " group by  proDetail.ProductID LIMIT %d,%d",(pageNumber)*pageSize,pageSize);
             helper.open(lang);
             ResultSet rs = helper.executeQuery(sql);
             while (rs.next()) {
                 Productorderdetail pDetail = new Productorderdetail();
                 Products product = new Products();
                 Productorder prOder = new Productorder();
                 User user =new User();
                 product.setUser(user);
                 pDetail.setProductorder(prOder);
                 pDetail.setProducts(product);
                 pDetail.setProductNumber(rs.getInt("Amount"));
                 product.setProductId(rs.getInt("ProductID"));
                 product.setProductName(rs.getString("ProductName"));
                 product.setPrice(rs.getFloat("Price"));
                 user.setAccount(rs.getString("Account"));
                 prOder.setOrderDate(rs.getDate("OrderDate"));
                 lstProductSeller.add(pDetail);
             }
         } catch (Exception ex) {
             ex.getMessage();
         }
         return lstProductSeller;
     }
     
     

     public static ArrayList<Productorderdetail> listStisticsAdmin(String fromDate, String toDate, String lang) {
         ArrayList<Productorderdetail> lstProductSeller = new ArrayList<Productorderdetail>();
         MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
         try {


             String sql = "select "
                     + " pr.ProductID,pr.ProductName,pr.Price,prOrder.OrderDate,pr.Account,"
                     + " Sum(proDetail.ProductNumber) as Amount "
                     + " from products pr ,productorderdetail proDetail,productorder  prOrder "
                     + " where proDetail.ProductId=pr.ProductID  "
                     + " and proDetail.ProductOrderId=prOrder.ProductOrderID"
                     + " and( prOrder.StateOrderID=4 or prOrder.StateOrderID=2) "
                     + " and (prOrder.OrderDate between '" + fromDate + "' and '" + toDate + "' ) "
                     + " group by  proDetail.ProductID ";
             helper.open(lang);
             ResultSet rs = helper.executeQuery(sql);
             while (rs.next()) {
                 Productorderdetail pDetail = new Productorderdetail();
                 Products product = new Products();
                 Productorder prOder = new Productorder();
                 User user =new User();
                 product.setUser(user);
                 pDetail.setProductorder(prOder);
                 pDetail.setProducts(product);
                 pDetail.setProductNumber(rs.getInt("Amount"));
                 product.setProductId(rs.getInt("ProductID"));
                 product.setProductName(rs.getString("ProductName"));
                 product.setPrice(rs.getFloat("Price"));
                 user.setAccount(rs.getString("Account"));
                 prOder.setOrderDate(rs.getDate("OrderDate"));
                 lstProductSeller.add(pDetail);
             }
         } catch (Exception ex) {
             ex.getMessage();
         }
         return lstProductSeller;
     }

     public static ArrayList<Productorderdetail> lstStaticAdminDateCurrent(String lang) {
         ArrayList<Productorderdetail> lstProductSeller = new ArrayList<Productorderdetail>();
         MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
         try {


             String sql = "select  pr.ProductID,pr.ProductName,pr.Price,prOrder.OrderDate,Sum(proDetail.ProductNumber) as Amount  "
                     + "from products pr ,productorderdetail proDetail,productorder  prOrder "
                     + "where proDetail.ProductId=pr.ProductID  "
                     + "and proDetail.ProductOrderId=prOrder.ProductOrderID "
                     + "and( prOrder.StateOrderID=4 or prOrder.StateOrderID=2) "
                     + "and prOrder.OrderDate=CURDATE() "
                     + "group by  proDetail.ProductID ";
             helper.open(lang);
             ResultSet rs = helper.executeQuery(sql);
             while (rs.next()) {
                 Productorderdetail pDetail = new Productorderdetail();
                 Products product = new Products();
                 Productorder prOder = new Productorder();
                 pDetail.setProductorder(prOder);
                 pDetail.setProducts(product);
                 pDetail.setProductNumber(rs.getInt("Amount"));
                 product.setProductId(rs.getInt("ProductID"));
                 product.setProductName(rs.getString("ProductName"));
                 product.setPrice(rs.getFloat("Price"));
                 
                 prOder.setOrderDate(rs.getDate("OrderDate"));
                 lstProductSeller.add(pDetail);
             }
         } catch (Exception ex) {
             ex.getMessage();
         }
         return lstProductSeller;
     }

     public static ArrayList<Productorderdetail> lstStaticAdminMonth(String month, String lang) {
         ArrayList<Productorderdetail> lstProductSeller = new ArrayList<Productorderdetail>();
         MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
         try {
             
             
             String sql = "select  pr.ProductID,pr.ProductName,pr.Price,prOrder.OrderDate, pr.Account,"
                     + "Sum(proDetail.ProductNumber) as Amount  "
                     + "from products pr ,productorderdetail proDetail,productorder  prOrder "
                     + "where proDetail.ProductId=pr.ProductID  "
                     + "and proDetail.ProductOrderId=prOrder.ProductOrderID "
                     + "and( prOrder.StateOrderID=4 or prOrder.StateOrderID=2)  "
                     + "and Month( prOrder.OrderDate) = '" + month + "'"
                     + " group by  proDetail.ProductID ";
             helper.open(lang);
             ResultSet rs = helper.executeQuery(sql);
             while (rs.next()) {
                 Productorderdetail pDetail = new Productorderdetail();
                 Products product = new Products();
                 Productorder prOder = new Productorder();
                 User user =new User();
                 product.setUser(user);
                 
                 pDetail.setProductorder(prOder);
                 pDetail.setProducts(product);
                 pDetail.setProductNumber(rs.getInt("Amount"));
                 product.setProductId(rs.getInt("ProductID"));
                 product.setProductName(rs.getString("ProductName"));
                 product.setPrice(rs.getFloat("Price"));
                 prOder.setOrderDate(rs.getDate("OrderDate"));
                 user.setAccount(rs.getString("Account"));
                 lstProductSeller.add(pDetail);
             }
         } catch (Exception ex) {
             ex.getMessage();
         }
         return lstProductSeller;
     }
     
     public static ArrayList<Productorderdetail> lstStaticAdminMonth(String month, int pageNumber, int pageSize, String lang) {
         ArrayList<Productorderdetail> lstProductSeller = new ArrayList<Productorderdetail>();
         MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
         try {
             
             
             String sql = String.format("select  pr.ProductID,pr.ProductName,pr.Price,prOrder.OrderDate, pr.Account,"
                     + "Sum(proDetail.ProductNumber) as Amount  "
                     + "from products pr ,productorderdetail proDetail,productorder  prOrder "
                     + "where proDetail.ProductId=pr.ProductID  "
                     + "and proDetail.ProductOrderId=prOrder.ProductOrderID "
                     + "and( prOrder.StateOrderID=4 or prOrder.StateOrderID=2)  "
                     + "and Month( prOrder.OrderDate) = '" + month + "'"
                     + " group by  proDetail.ProductID LIMIT %d,%d",(pageNumber)*pageSize,pageSize);
             helper.open(lang);
             ResultSet rs = helper.executeQuery(sql);
             while (rs.next()) {
                 Productorderdetail pDetail = new Productorderdetail();
                 Products product = new Products();
                 Productorder prOder = new Productorder();
                 User user =new User();
                 product.setUser(user);
                 
                 pDetail.setProductorder(prOder);
                 pDetail.setProducts(product);
                 pDetail.setProductNumber(rs.getInt("Amount"));
                 product.setProductId(rs.getInt("ProductID"));
                 product.setProductName(rs.getString("ProductName"));
                 product.setPrice(rs.getFloat("Price"));
                 prOder.setOrderDate(rs.getDate("OrderDate"));
                 user.setAccount(rs.getString("Account"));
                 lstProductSeller.add(pDetail);
             }
         } catch (Exception ex) {
             ex.getMessage();
         }
         return lstProductSeller;
     }

     public static ArrayList<Productorderdetail> lstStaticAdminYear(String year, String lang) {
         ArrayList<Productorderdetail> lstProductSeller = new ArrayList<Productorderdetail>();
         MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
         try {
             String sql = "select  pr.ProductID,pr.ProductName,pr.Price,prOrder.OrderDate,pr.Account, "
                     + "Sum(proDetail.ProductNumber) as Amount  "
                     + "from products pr ,productorderdetail proDetail,productorder  prOrder "
                     + "where proDetail.ProductId=pr.ProductID  "
                     + "and proDetail.ProductOrderId=prOrder.ProductOrderID "
                     + "and( prOrder.StateOrderID=4 or prOrder.StateOrderID=2)  "
                     + "and Year( prOrder.OrderDate) = '" + year + "'"
                     + "group by  proDetail.ProductID ";
             helper.open(lang);
             ResultSet rs = helper.executeQuery(sql);
             while (rs.next()) {
                 Productorderdetail pDetail = new Productorderdetail();
                 Products product = new Products();
                 Productorder prOder = new Productorder();
                 pDetail.setProductorder(prOder);
                 pDetail.setProducts(product);
                 User user=new User();
                 product.setUser(user);
                 pDetail.setProductNumber(rs.getInt("Amount"));
                 product.setProductId(rs.getInt("ProductID"));
                 product.setProductName(rs.getString("ProductName"));
                 product.setPrice(rs.getFloat("Price"));
                 prOder.setOrderDate(rs.getDate("OrderDate"));
                 user.setAccount(rs.getString("Account"));
                 lstProductSeller.add(pDetail);
             }
         } catch (Exception ex) {
             ex.getMessage();
         }
         return lstProductSeller;
     }
     
     public static ArrayList<Productorderdetail> lstStaticAdminYear(String year, int pageNumber, int pageSize, String lang) {
         ArrayList<Productorderdetail> lstProductSeller = new ArrayList<Productorderdetail>();
         MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
         try {
             String sql = String.format("select  pr.ProductID,pr.ProductName,pr.Price,prOrder.OrderDate,pr.Account, "
                     + "Sum(proDetail.ProductNumber) as Amount  "
                     + "from products pr ,productorderdetail proDetail,productorder  prOrder "
                     + "where proDetail.ProductId=pr.ProductID  "
                     + "and proDetail.ProductOrderId=prOrder.ProductOrderID "
                     + "and( prOrder.StateOrderID=4 or prOrder.StateOrderID=2)  "
                     + "and Year( prOrder.OrderDate) = '" + year + "'"
                     + "group by  proDetail.ProductID LIMIT %d,%d",(pageNumber)*pageSize,pageSize);
             helper.open(lang);
             ResultSet rs = helper.executeQuery(sql);
             while (rs.next()) {
                 Productorderdetail pDetail = new Productorderdetail();
                 Products product = new Products();
                 Productorder prOder = new Productorder();
                 pDetail.setProductorder(prOder);
                 pDetail.setProducts(product);
                 User user=new User();
                 product.setUser(user);
                 pDetail.setProductNumber(rs.getInt("Amount"));
                 product.setProductId(rs.getInt("ProductID"));
                 product.setProductName(rs.getString("ProductName"));
                 product.setPrice(rs.getFloat("Price"));
                 prOder.setOrderDate(rs.getDate("OrderDate"));
                 user.setAccount(rs.getString("Account"));
                 lstProductSeller.add(pDetail);
             }
         } catch (Exception ex) {
             ex.getMessage();
         }
         return lstProductSeller;
     }
     
     public static List<Productorderdetail> getListProductorderdetail(int id, String lang){
         
         String sql="from Productorderdetail where ProductOrderId="+id;
         return HibernateDAO.getList(sql, lang);
     }
     
     public static List<Productorderdetail> lstProductOrderDetailByOrderId(int id,String lang){
    	 return HibernateDAO.getList("FROM Productorderdetail WHERE productorder.productOrderId = "+id, lang);
     }
    
     //Get list ProductOrderDetail in OrderDetailStatus
     public static List<Productorderdetail> lstOrderDetailByStatus(Orderdetailstatus status, String lang) {
         return (List<Productorderdetail>)HibernateDAO.getList("from Productorderdetail where orderDetailStatusId=" + status.getOrderDetailStatusId(), lang);
     }
     
     //Get list ProductOrderDetail in OrderDetailStatus (pagging)
     public static List<Productorderdetail> lstOrderDetailByStatus(Orderdetailstatus status, int pageNumber, int pageSize, String lang) {
         return (List<Productorderdetail>)HibernateDAO.getList("from Productorderdetail where orderDetailStatusId=" + status.getOrderDetailStatusId(),pageNumber,pageSize, lang);
     }
     
    //Get list ProductOrderDetail in OrderDetailStatus and Product
     public static List<Productorderdetail> lstOrderDetailBy(Orderdetailstatus status,Products p, String lang) {
         return HibernateDAO.getList("from Productorderdetail where orderDetailStatusId=" + status.getOrderDetailStatusId()+"and products.productId="+ p.getProductId() , lang);
     }
     
     public static List<Productorderdetail>[] getListPODetailByBuyer(String account,String lang,String fromDate,String toDate,int pageNumber,int pageSize){
    	 String where="";
    	 if(fromDate!=null & toDate!=null){
    		 where=" and pod.productorder.orderDate between '"+fromDate+"' and '"+toDate+"'";
    		 
    	 }
    	 where+=" order by pod.productorder.orderDate desc ";
    	 return (List<Productorderdetail>[])HibernateDAO.getListSize("select pod from Productorderdetail pod where pod.productorder.user.account='"+account+"'" 
   			 +"  and ( pod.orderDetailStatusId ='1' or pod.orderDetailStatusId ='2' or pod.orderDetailStatusId ='5')"  +where,pageNumber,pageSize, lang);
     }
     
     public static List<Productorderdetail>[] getListPODetailApplyCancelByBuyer(String account,String lang,String fromDate,String toDate,int currentPage,int pageSize){
    	 String where="";
    	 if(fromDate!=null & toDate!=null){
    		 where=" and pod.productorder.orderDate between '"+fromDate+"' and '"+toDate+"'";
    	 }
    	 return (List<Productorderdetail>[])HibernateDAO.getListSize("select pod from Productorderdetail pod, Deliver d where pod.productOrderDetailId=d.productOrderDetail" +
     	 		" and pod.productorder.user.account='"+account+"'" 
   			 +" and (d.deliverstatusID ='1' or d.deliverstatusID ='2')  and ( pod.orderDetailStatusId ='1' or pod.orderDetailStatusId ='2')"  +where,currentPage,pageSize, lang);
     }
     
     public static List<Productorderdetail>[] getListPODetailCancelByBuyer(String account,String lang,String fromDate,String toDate,int currentPage,int pageSize){
    	 String where="";
    	 if(fromDate!=null & toDate!=null){
    		 where=" and productorder.orderDate between '"+fromDate+"' and '"+toDate+"'";
    	 }
    	 return (List<Productorderdetail>[])HibernateDAO.getListSize("from Productorderdetail where productorder.user.account='"+account+"'" 
    			 +" and orderDetailStatusId ='3'" +where,currentPage,pageSize, lang);
     }
     
     public static List<Productorderdetail>[] getListPODetailReturnExchangeByBuyer(String account,String lang,String fromDate,String toDate,int currentPage,int pageSize){
    	 String where="";
    	 if(fromDate!=null & toDate!=null){
    		 where=" and productorder.orderDate between '"+fromDate+"' and '"+toDate+"'";
    	 }
    	 return (List<Productorderdetail>[])HibernateDAO.getListSize("from Productorderdetail where productorder.user.account='"+account+"'" 
    			 +" and orderDetailStatusId ='4'" +where,currentPage,pageSize, lang);
     }
     
     public static List<Productorderdetail>[] getListPODetailShippingCompletedByBuyer(String account,String lang,String fromDate,String toDate,int currentPage,int pageSize){
    	 String where="";
    	 if(fromDate!=null & toDate!=null){
    		 where=" and pod.productorder.orderDate between '"+fromDate+"' and '"+toDate+"'";
    	 }
    	 return (List<Productorderdetail>[])HibernateDAO.getListSize("select pod from Productorderdetail pod, Deliver d where pod.productOrderDetailId=d.productOrderDetail" +
    	 		" and pod.productorder.user.account='"+account+"'" 
    			 +" and (d.deliverstatusID ='7')  and ( orderDetailStatusId ='2' or orderDetailStatusId ='5')" +where,currentPage,pageSize ,lang);
     }
     
     /**
 	 * Get the list productorderdetail of the seller
 	 * 
 	 * **/
     
     
     @SuppressWarnings("unchecked")
	public static Map getListManageCancel(String seller,String lang,String where,int currentPage,int pageSize){
    	 Map maps= (HashMap) HibernateDAO.getListMap("select pod.productOrderDetailId as productOrderDetailID, po.productOrderId as productOrderID," +
    	 		" pro.productId as  productID, cs.cancelStatusName as cancelStatus, oc.requestDate as  dateRequestCancel, oc.complete as dateFinishedCancel, po.orderDate as dateOrder," +
    	 		" pro.productName as  productName, pod.productNumber as amount, pro.price as price, " +
    	 		" buyer.fullName as  buyerName, buyer.address as buyerAddress, po.reciever as receiverName, po.address as receiverAddress, " +
    	 		" oc.orderCancelId as orderCancelID " +
    	 		" from Productorderdetail pod, Productorder po, Products pro, User buyer, Ordercancel oc, Cancelstatus cs " +
    	 		" where pod.productorder=po and pro=pod.products and po.user=buyer and pod.productOrderDetailId=oc.orderDetaiId and oc.cancelStatusId=cs.cancelStatusId " +
    	 		" and pro.user.account='"+seller+"' and pod.orderDetailStatusId ='3'"+where,currentPage,pageSize , lang);
    	 List<ManageCancelView> list=new ArrayList<ManageCancelView>();
 		for (Object[] obj : (List<Object[]>)(maps.get("list"))) {
 			ManageCancelView mcv=new ManageCancelView();
 			String price=obj[9]+"";
 			String quantity=obj[8]+"";
 			
 			mcv.setProductOrderDetailID(obj[0]+"");
 			mcv.setProductOrderID(obj[1]+"");
 			mcv.setProductID(obj[2]+"");
 			mcv.setCancelStatus(obj[3]+"");
 			mcv.setDateRequestCancel(obj[4]+"");
 			mcv.setDateFinishedCancel(obj[5]+"");
 			mcv.setDateOrder(obj[6]+"");
 			mcv.setProductName(obj[7]+"");
 			mcv.setAmount(obj[8]+"");
 			mcv.setPrice(obj[9]+"");
 			mcv.setBuyerName(obj[10]+"");
 			mcv.setBuyerAddress(obj[11]+"");
 			mcv.setReceiverName(obj[12]+"");
 			mcv.setReceiverAddress(obj[13]+"");
 			
 			float totalMoney=Float.parseFloat(price)*Float.parseFloat(quantity);
 			String productID=obj[2]+"";
 			Products product=ProductBUS.getProducts(Integer.parseInt(productID), lang);
 			Productorder pOrder=ProductorderBUS.getProductorder(Integer.parseInt(mcv.getProductOrderID()), lang); 			
 			float shippingCost=DeliverCostBUS.getDeliverCostByProduct(product, totalMoney,pOrder.getTransportId(), lang);
 			float total=totalMoney+shippingCost;
 			mcv.setShippingCost(shippingCost+"");
 			mcv.setSubTotal(total+"");
 			mcv.setOrderCancelID(obj[14]+"");
 			
 			list.add(mcv); 			
 			
 		} 		
 		maps.put("list", list);
 		return maps;
     }
     
     public static Map getListManageCancelWithSearch(String seller,String lang,String cancelStatusID,
    		 String selectDate,String fromDate,String toDate,String selectAdvanced,String textSearch,int currentPage,int pageSize){
    	String where="";
    	//select status    	
    	if(!("0".equals(cancelStatusID))){
    		where+="  and oc.cancelStatusId='"+cancelStatusID+"'";
    	}
    	//select date
    	if((""!=fromDate) && (""!=toDate)){
    		if("1".equals(selectDate)){
		   		
        		where+=" and oc.requestDate between '"+fromDate+"' and '"+toDate+"'";
        	}
        	else if("2".equals(selectDate)){
        		where+=" and po.orderDate between '"+fromDate+"' and '"+toDate+"'";
        	}
        	else if("3".equals(selectDate)){
        		where+=" and oc.complete between '"+fromDate+"' and '"+toDate+"'";
        	}
    	}   
    	//search advanced
    	if(""!=textSearch && "0"!=selectAdvanced){
    		if("1".equals(selectAdvanced)){
    			where+=" and pod.productOrderDetailId like '%"+textSearch+"%'";
    		}
    		else if("2".equals(selectAdvanced)){
    			where+=" and po.productOrderId='"+textSearch+"'";
    		}
    		else if("3".equals(selectAdvanced)){
    			where+=" and buyer.fullName='"+textSearch+"'";
    		}
    		else if("4".equals(selectAdvanced)){
    			where+=" and po.reciever='"+textSearch+"'";
    		}
    	}
    	
    	return  getListManageCancel(seller, lang, where,currentPage,pageSize);
     }
     
     public static void main(String[] args) {
    	    	 
//    	 String lang="MALL_EN";    	 
//    	 List<ManageCancelView> list=getListManageCancel("sell01", lang);
//    	 ManageCancelView mc=list.get(0);
//    	 System.out.println();
	}
    
}
