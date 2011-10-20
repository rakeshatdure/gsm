/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.*;
import POJO.*;

import java.util.Date;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.Out;
/**
 *
 * @author admin
 */
public class ExchangeOrderBUS {
    
    /** Get all products **/
    public static List<Exchangeorder> lstExchangeOrder(String lang) {
        return ExchangeOrderDAO.lstExchangeOrder(lang);
    }
    
    public static Exchangeorder getExchangeOrder (int id, String lang){
    	return ExchangeOrderDAO.getExchangeorder(id, lang);
    }
    /**Get ExchangeOrder by ProductOrderDetailID**/
    public static Exchangeorder getExchangeOrderByPODetailID (int pODetailID, String lang){
    	return ExchangeOrderDAO.getExchangeOrderByPODetailID(pODetailID, lang);
    }
    /** Get all exchange order by seller **/
    public static List<Exchangeorder> lstExchangeOrder(String user,String lang) {
        return ExchangeOrderDAO.lstExchangeOrder(user,lang);
    }
    /** Get all exchange order by seller **/
    public static List<Exchangeorder> lstReturnOrder(String user,String lang) {
        return ExchangeOrderDAO.lstReturnOrder(user,lang);
    }
    
    public static List<Exchangeorder> lstExchangeOrder(String user,int status,String lang) {
        return ExchangeOrderDAO.lstExchangOrder(user, status, lang);
    }
    
    public static boolean update(Exchangeorder exchangeOrder,String lang){
    	return ExchangeOrderDAO.update(exchangeOrder, lang);
    }
    
    public static boolean insert(Exchangeorder exchangeOrder, String lang){
    	return ExchangeOrderDAO.insert(exchangeOrder, lang);
    }
    
   
    
}
