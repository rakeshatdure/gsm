/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.util.List;

import BUS.ExchangeOrderBUS;
import BUS.ProductBUS;
import BUS.ProductorderBUS;
import BUS.ProductorderdetailBUS;
import BUS.UserBUS;
import POJO.*;
import UTIL.MySqlDataAccessHelper;

import java.util.ArrayList;


import org.hibernate.Query;
/**
 *
 * @author admin
 */

public class ExchangeOrderDAO extends HibernateDAO{
    
    //Get all Exchange Order
    public static List<Exchangeorder> lstExchangeOrder(String lang){
        return HibernateDAO.getList("from Exchangeorder", lang);
    }
    
    public static Exchangeorder getExchangeorder(int id,String lang){
    	return (Exchangeorder) HibernateDAO.getObject(Exchangeorder.class, id, lang);
    }
    
  //Get list Exchange by Seller
    public static List<Exchangeorder> lstExchangeOrder(String user, String lang) {
        return HibernateDAO.getList("from Exchangeorder where orderDetail.products.user.account = '" +user+ "'", lang);
    }
    
    
    //Get list Exchange by Seller
      public static List<Exchangeorder> lstReturnOrder(String user, String lang) {
          return HibernateDAO.getList("from Exchangeorder where orderDetail.products.user.account = '" +user+ "'", lang);
      }
      
    
    public static List<Exchangeorder> lstExchangOrder(String user,int status, String lang){
    	return HibernateDAO.getList("from Exchangeorder where orderDetail.products.user.account = '" +user+ "' AND exchangeStatus.exchangeStatusId = "+status, lang);
    }
    public static boolean update(Exchangeorder exchangeOrder,String lang){
    	return HibernateDAO.update(exchangeOrder, lang);
    }
    
    public static boolean insert(Exchangeorder exchangeOrder, String lang){
    	return HibernateDAO.insert(exchangeOrder, lang);
    }

	public static Exchangeorder getExchangeOrderByPODetailID(int pODetailID,
			String lang) {
		List<Exchangeorder> list=HibernateDAO.getList("from Exchangeorder where orderDetail.productOrderDetailId = '" +pODetailID+ "'", lang);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
  
    
        
}
